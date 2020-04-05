package com.eurovision.homework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WordDTO {
  Integer id;
  String name;
}
