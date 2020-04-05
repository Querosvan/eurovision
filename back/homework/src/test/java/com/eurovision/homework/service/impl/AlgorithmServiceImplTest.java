package com.eurovision.homework.service.impl;

import com.eurovision.homework.config.SpringTestConfiguration;
import com.eurovision.homework.dto.CityDTO;
import com.eurovision.homework.dto.WordDTO;
import com.eurovision.homework.service.CityService;
import com.eurovision.homework.service.WordService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SpringTestConfiguration.class})
public class AlgorithmServiceImplTest {

  @Spy
  @InjectMocks
  private AlgorithmServiceImpl algorithmServiceImpl;

  @Mock
  private CityService cityService;

  @Mock
  private WordService wordService;

  @BeforeAll
  public static void init() {

  }

  @Test
  public void test_getMostPermutableCity_recursive() {
    final CityDTO sevillaCityDTO = new CityDTO();
    sevillaCityDTO.setId(17);
    sevillaCityDTO.setName("Sevilla");
    final CityDTO houstonCityDTO = new CityDTO();
    houstonCityDTO.setId(14);
    houstonCityDTO.setName("Houston");

    final List<CityDTO> cityDTOlist = new ArrayList<>();
    cityDTOlist.add(sevillaCityDTO);
    cityDTOlist.add(houstonCityDTO);

    final List<WordDTO> sevillaWords = new ArrayList<>();
    sevillaWords.add(new WordDTO());
    final List<WordDTO> houstonWords = new ArrayList<>();
    houstonWords.add(new WordDTO());
    houstonWords.add(new WordDTO());
    houstonWords.add(new WordDTO());

    Mockito.when(this.cityService.findByNameLength(7)).thenReturn(cityDTOlist);
    Mockito.when(this.wordService.findByName(ArgumentMatchers.any())).thenReturn(sevillaWords).thenReturn(houstonWords);

    final CityDTO result = this.algorithmServiceImpl.getMostPermutableCity(true);

    Assertions.assertEquals(houstonCityDTO.getId(), result.getId());
    Assertions.assertEquals(houstonCityDTO.getName(), result.getName());
  }

  @Test
  public void test_getMostPermutableCity_non_recursive() {
    final CityDTO sevillaCityDTO = new CityDTO();
    sevillaCityDTO.setId(17);
    sevillaCityDTO.setName("Sevilla");
    final CityDTO houstonCityDTO = new CityDTO();
    houstonCityDTO.setId(14);
    houstonCityDTO.setName("Houston");

    final List<CityDTO> cityDTOlist = new ArrayList<>();
    cityDTOlist.add(sevillaCityDTO);
    cityDTOlist.add(houstonCityDTO);

    final List<WordDTO> sevillaWords = new ArrayList<>();
    sevillaWords.add(new WordDTO());
    final List<WordDTO> houstonWords = new ArrayList<>();
    houstonWords.add(new WordDTO());
    houstonWords.add(new WordDTO());
    houstonWords.add(new WordDTO());

    Mockito.when(this.cityService.findByNameLength(7)).thenReturn(cityDTOlist);
    Mockito.when(this.wordService.findByName(ArgumentMatchers.any())).thenReturn(sevillaWords).thenReturn(houstonWords);

    final CityDTO result = this.algorithmServiceImpl.getMostPermutableCity(false);

    Assertions.assertEquals(houstonCityDTO.getId(), result.getId());
    Assertions.assertEquals(houstonCityDTO.getName(), result.getName());
  }

}
