package com.eurovision.homework.service.impl;

import com.eurovision.homework.dto.CityDTO;
import com.eurovision.homework.dto.WordDTO;
import com.eurovision.homework.service.AlgorithmService;
import com.eurovision.homework.service.CityService;
import com.eurovision.homework.service.WordService;
import com.eurovision.homework.util.combinator.Combination;
import com.eurovision.homework.util.combinator.RecursiveCombination;
import com.eurovision.homework.util.permutator.Permutation;
import com.eurovision.homework.util.permutator.RecursivePermutation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlgorithmServiceImpl implements AlgorithmService {
  private static final int CITY_LENGTH = 7;

  @Autowired
  private CityService cityService;

  @Autowired
  private WordService wordService;

  /**
   * {@inheritDoc}
   */
  @Override
  public CityDTO getMostPermutableCity(final boolean recursive) {

    final List<CityDTO> cityList = this.cityService.findByNameLength(AlgorithmServiceImpl.CITY_LENGTH);
    AlgorithmServiceImpl.log.info(cityList.size() + " cities with length: " + AlgorithmServiceImpl.CITY_LENGTH);

    final HashMap<CityDTO, Integer> citiesPermutations = this.getAllCitiesPermutations(cityList, recursive);
    AlgorithmServiceImpl.log.info("Words Record: " + citiesPermutations.toString());

    return this.getCityWithMostOptions(citiesPermutations);

  }

  /**
   * Get all permutations for cities.
   *
   * @param cityList list of cities.
   * @param recursive select algorithm, recursive or not
   * @return HashMap with City and number of words found.
   */
  private HashMap<CityDTO, Integer> getAllCitiesPermutations(final List<CityDTO> cityList, final boolean recursive) {
    final HashMap<CityDTO, Integer> citiesPermutations = new HashMap<>();
    cityList.forEach(city -> {
      final List<String> cityCombinations = this.getCityCombinations(city, recursive);

      final List<String> cityPermutations = this.getCityPermutationsFromList(cityCombinations, recursive);
      AlgorithmServiceImpl.log.debug("Permutations for [" + city.getName() + "]: " + cityPermutations);

      final List<WordDTO> wordsFound = this.wordService.findByName(cityPermutations);
      AlgorithmServiceImpl.log.debug("Words for [" + city.getName() + "]: " + wordsFound);

      citiesPermutations.put(city, wordsFound.size());
    });
    return citiesPermutations;
  }

  /**
   * Calculates permutations for words.
   *
   * @param cityCombinations List of words to permute.
   * @param recursive select algorithm, recursive or not
   * @return List of permutations.
   */
  private List<String> getCityPermutationsFromList(final List<String> cityCombinations, final boolean recursive) {

    final List<String> cityPermutations = new ArrayList<>();
    cityCombinations.stream().forEach(combination -> {
      if (recursive) {
        RecursivePermutation.generatePermutations(combination).stream().sequential()
            .collect(Collectors.toCollection(() -> cityPermutations));
      } else {
        new Permutation(combination).stream().sequential().collect(Collectors.toCollection(() -> cityPermutations));
      }

    });
    return cityPermutations;
  }

  /**
   * Calculates combinations for a city.
   *
   * @param city The city
   * @param recursive select algorithm, recursive or not
   * @return List of combinations with the city name.
   */
  private List<String> getCityCombinations(final CityDTO city, final boolean recursive) {
    if (recursive) {
      return Stream.of(RecursiveCombination.combinations(city.getName(), 5), RecursiveCombination.combinations(city.getName(), 6),
          RecursiveCombination.combinations(city.getName(), 7)).flatMap(Collection::stream).collect(Collectors.toList());
    } else {
      return Stream
          .of(new Combination(city.getName().toLowerCase(), 5).stream().collect(Collectors.toList()),
              new Combination(city.getName().toLowerCase(), 6).stream().collect(Collectors.toList()),
              new Combination(city.getName().toLowerCase(), 7).stream().collect(Collectors.toList()))
          .flatMap(Collection::stream).collect(Collectors.toList());
    }
  }

  /**
   * Calculates city with more words found.
   *
   * @param citiesPermutations map of cities
   * @return City with most words found.
   */
  private CityDTO getCityWithMostOptions(final HashMap<CityDTO, Integer> citiesPermutations) {
    final Optional<Entry<CityDTO, Integer>> maxEntry =
        citiesPermutations.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
    return maxEntry.isPresent() ? maxEntry.get().getKey() : new CityDTO();
  }

}
