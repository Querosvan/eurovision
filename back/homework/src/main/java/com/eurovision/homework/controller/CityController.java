package com.eurovision.homework.controller;

import com.eurovision.homework.dto.CityDTO;
import com.eurovision.homework.service.AlgorithmService;
import com.eurovision.homework.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/cities")
public class CityController {

  @Autowired
  private final CityService cityService;

  @Autowired
  private final AlgorithmService algorithmService;

  /**
   * Get all cities by page.
   *
   * @param pageable Page parameters
   * @return Page of cities
   */
  @GetMapping(value = "/queryByPage")
  public ResponseEntity<Page<CityDTO>> getCities(@PageableDefault(page = 0, size = 5) final Pageable pageable) {
    return ResponseEntity.ok().body(this.cityService.findAllByPage(pageable));
  }

  /**
   * Get the most permutable city.
   *
   * @param recursive Determines the use of recursive algorithm
   * @return the most permutable City
   */
  @GetMapping(value = "/mostPermutable")
  public ResponseEntity<CityDTO> getMostPermutableCity(@RequestParam(defaultValue = "false") final boolean recursive) {
    return ResponseEntity.ok().body(this.algorithmService.getMostPermutableCity(recursive));
  }
}
