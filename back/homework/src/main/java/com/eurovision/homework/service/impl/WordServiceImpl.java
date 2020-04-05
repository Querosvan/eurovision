package com.eurovision.homework.service.impl;

import com.eurovision.homework.dto.WordDTO;
import com.eurovision.homework.mapper.WordMapper;
import com.eurovision.homework.repository.WordRepository;
import com.eurovision.homework.service.WordService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

  @Autowired
  private WordMapper wordMapper;

  @Autowired
  private WordRepository wordRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<WordDTO> findByName(final List<String> names) {
    return this.wordRepository.findByNameInIgnoreCase(names).stream().map(this.wordMapper::mapWordFromDaoToDto)
        .collect(Collectors.toList());
  }

}
