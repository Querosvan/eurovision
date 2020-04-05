package com.eurovision.homework.controller;

import com.eurovision.homework.dto.CityDTO;
import com.eurovision.homework.service.AlgorithmService;
import com.eurovision.homework.service.CityService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
public class CityControllerTest {

  private static String pageUri = "/api/cities/queryByPage";

  private static String permutableUri = "/api/cities/mostPermutable";

  @Mock
  private CityService cityService;

  @Mock
  private AlgorithmService algorithmService;

  private final PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver();

  private MockMvc mockMvc;

  @BeforeEach
  public void init() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new CityController(this.cityService, this.algorithmService))
        .setCustomArgumentResolvers(this.pageableArgumentResolver).build();
  }

  @Test
  public void test_queryByPage() throws Exception {
    final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(CityControllerTest.pageUri).param("page", "0")
        .param("size", "5").accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

    final CityDTO helloCityDTO = new CityDTO();
    helloCityDTO.setId(1);
    helloCityDTO.setName("Hello");
    final CityDTO textCityDTO = new CityDTO();
    textCityDTO.setId(2);
    textCityDTO.setName("Text");

    final Pageable pageable = PageRequest.of(0, 5);
    final List<CityDTO> cities = new ArrayList<>();
    cities.add(helloCityDTO);
    cities.add(textCityDTO);
    final Page<CityDTO> citiesPages = new PageImpl<>(cities);

    final ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

    Mockito.when(this.cityService.findAllByPage(pageable)).thenReturn(citiesPages);

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(0))
        .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfElements").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(2))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print());

    Mockito.verify(this.cityService).findAllByPage(pageableCaptor.capture());
    final Pageable pageableReturn = pageableCaptor.getValue();

    Assertions.assertEquals(0, pageableReturn.getPageNumber());
    Assertions.assertEquals(5, pageableReturn.getPageSize());
  }

  @Test
  public void test_mostPermutable_recursive() throws Exception {
    final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(CityControllerTest.permutableUri).param("recursive", "true")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

    final CityDTO cityDTO = new CityDTO();
    cityDTO.setId(45);
    cityDTO.setName("Madrid");
    Mockito.when(this.algorithmService.getMostPermutableCity(true)).thenReturn(cityDTO);

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cityDTO.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(cityDTO.getName())).andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_mostPermutable_non_recursive() throws Exception {
    final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(CityControllerTest.permutableUri).param("recursive", "false")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

    final CityDTO cityDTO = new CityDTO();
    cityDTO.setId(45);
    cityDTO.setName("Madrid");
    Mockito.when(this.algorithmService.getMostPermutableCity(false)).thenReturn(cityDTO);

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cityDTO.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(cityDTO.getName())).andDo(MockMvcResultHandlers.print());
  }
}
