package com.eurovision.homework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CityDTO {
  Integer id;
  String name;
}
