package com.eurovision.homework.service.impl;

import com.eurovision.homework.dto.CityDTO;
import com.eurovision.homework.mapper.CityMapper;
import com.eurovision.homework.repository.CityRepository;
import com.eurovision.homework.service.CityService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

  @Autowired
  private CityMapper cityMapper;

  @Autowired
  private CityRepository cityRepository;


  /**
   * {@inheritDoc}
   */
  @Override
  public Page<CityDTO> findAllByPage(final Pageable pageable) {
    return this.cityRepository.findAll(pageable).map(this.cityMapper::mapCityFromDaoToDto);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public List<CityDTO> findByNameLength(final int length) {
    return this.cityRepository.findByNameLength(length).stream().map(this.cityMapper::mapCityFromDaoToDto).collect(Collectors.toList());
  }

}
