package com.eurovision.homework.service.impl;

import com.eurovision.homework.config.SpringTestConfiguration;
import com.eurovision.homework.dto.CityDTO;
import com.eurovision.homework.mapper.CityMapper;
import com.eurovision.homework.model.City;
import com.eurovision.homework.repository.CityRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SpringTestConfiguration.class})
public class CityServiceImplTest {
  @Spy
  @InjectMocks
  private CityServiceImpl cityServiceImpl;


  @Mock
  private CityMapper cityMapper;


  @Mock
  private CityRepository cityRepository;

  @BeforeAll
  public static void init() {

  }

  @Test
  public void test_findAllByPage() {
    final List<City> cities = new ArrayList<>();
    final City helloCity = new City();
    helloCity.setId(1);
    helloCity.setName("Hello");
    cities.add(helloCity);
    final City textCity = new City();
    textCity.setId(2);
    textCity.setName("Text");
    cities.add(textCity);

    final CityDTO helloCityDTO = new CityDTO();
    helloCityDTO.setId(1);
    helloCityDTO.setName("Hello");
    final CityDTO textCityDTO = new CityDTO();
    textCityDTO.setId(2);
    textCityDTO.setName("Text");

    final Pageable pageable = PageRequest.of(1, 5);
    final Page<City> citiesPages = new PageImpl<>(cities);

    Mockito.when(this.cityRepository.findAll(pageable)).thenReturn(citiesPages);
    Mockito.when(this.cityMapper.mapCityFromDaoToDto(helloCity)).thenReturn(helloCityDTO);
    Mockito.when(this.cityMapper.mapCityFromDaoToDto(textCity)).thenReturn(textCityDTO);

    final Page<CityDTO> result = this.cityServiceImpl.findAllByPage(pageable);
    Assertions.assertEquals(2, result.getNumberOfElements());
    Assertions.assertEquals(helloCityDTO, result.getContent().get(0));
    Assertions.assertEquals(textCityDTO, result.getContent().get(1));
  }

  @Test
  public void test_findByNameLength() {
    final int length = 5;

    final List<City> cities = new ArrayList<>();
    final City helloCity = new City();
    helloCity.setId(1);
    helloCity.setName("Hello");
    cities.add(helloCity);
    final City textCity = new City();
    textCity.setId(2);
    textCity.setName("Text");
    cities.add(textCity);

    final CityDTO helloCityDTO = new CityDTO();
    helloCityDTO.setId(1);
    helloCityDTO.setName("Hello");
    final CityDTO textCityDTO = new CityDTO();
    textCityDTO.setId(2);
    textCityDTO.setName("Text");

    Mockito.when(this.cityRepository.findByNameLength(length)).thenReturn(cities);
    Mockito.when(this.cityMapper.mapCityFromDaoToDto(helloCity)).thenReturn(helloCityDTO);
    Mockito.when(this.cityMapper.mapCityFromDaoToDto(textCity)).thenReturn(textCityDTO);

    final List<CityDTO> result = this.cityServiceImpl.findByNameLength(length);
    Assertions.assertEquals(2, result.size());
    Assertions.assertEquals(helloCityDTO, result.get(0));
    Assertions.assertEquals(textCityDTO, result.get(1));
  }
}
