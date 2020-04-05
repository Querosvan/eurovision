package com.eurovision.homework.service;

import com.eurovision.homework.dto.CityDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

  /**
   * Find all cities in a page.
   *
   * @param pageable Page parameters.
   * @return City Page
   */
  Page<CityDTO> findAllByPage(Pageable pageable);

  /**
   * Find all cities with the required name length.
   *
   * @param length Name length to filter by
   * @return List of cities
   */
  List<CityDTO> findByNameLength(int length);

}
