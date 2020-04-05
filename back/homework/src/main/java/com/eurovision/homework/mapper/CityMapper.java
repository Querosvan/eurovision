package com.eurovision.homework.mapper;

import com.eurovision.homework.dto.CityDTO;
import com.eurovision.homework.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

  CityDTO mapCityFromDaoToDto(City city);
}
