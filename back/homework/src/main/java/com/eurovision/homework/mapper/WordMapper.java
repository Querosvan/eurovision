package com.eurovision.homework.mapper;

import com.eurovision.homework.dto.WordDTO;
import com.eurovision.homework.model.Word;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordMapper {

  WordDTO mapWordFromDaoToDto(Word word);
}
