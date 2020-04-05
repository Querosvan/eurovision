package com.eurovision.homework.service;

import com.eurovision.homework.dto.WordDTO;
import java.util.List;

public interface WordService {

  /**
   * Get all words from DB in the list as parameter.
   *
   * @param names List to compare with
   * @return Words in list
   */
  List<WordDTO> findByName(List<String> names);

}
