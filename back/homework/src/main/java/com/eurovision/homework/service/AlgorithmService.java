package com.eurovision.homework.service;

import com.eurovision.homework.dto.CityDTO;

public interface AlgorithmService {

  /**
   * Get most permutable city.
   *
   * @param recursive Use recursive algorithm or not
   * @return most permutable City.
   */
  CityDTO getMostPermutableCity(boolean recursive);

}
