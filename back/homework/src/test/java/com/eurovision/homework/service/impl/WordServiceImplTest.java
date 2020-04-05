package com.eurovision.homework.service.impl;


import com.eurovision.homework.config.SpringTestConfiguration;
import com.eurovision.homework.dto.WordDTO;
import com.eurovision.homework.mapper.WordMapper;
import com.eurovision.homework.model.Word;
import com.eurovision.homework.repository.WordRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SpringTestConfiguration.class})
public class WordServiceImplTest {

  @Spy
  @InjectMocks
  private WordServiceImpl wordServiceImpl;


  @Mock
  private WordMapper wordMapper;


  @Mock
  private WordRepository wordRepository;

  @BeforeAll
  public static void init() {

  }

  @Test
  public void test_findByName() {
    final List<Word> words = new ArrayList<>();
    final Word helloWord = new Word();
    helloWord.setId(1);
    helloWord.setName("Hello");
    words.add(helloWord);
    final Word textWord = new Word();
    textWord.setId(2);
    textWord.setName("Text");
    words.add(textWord);

    final WordDTO helloWordDTO = new WordDTO();
    helloWordDTO.setId(1);
    helloWordDTO.setName("Hello");
    final WordDTO textWordDTO = new WordDTO();
    textWordDTO.setId(2);
    textWordDTO.setName("Text");

    Mockito.when(this.wordRepository.findByNameInIgnoreCase(Arrays.asList("hello", "world", "text"))).thenReturn(words);
    Mockito.when(this.wordMapper.mapWordFromDaoToDto(helloWord)).thenReturn(helloWordDTO);
    Mockito.when(this.wordMapper.mapWordFromDaoToDto(textWord)).thenReturn(textWordDTO);

    final List<WordDTO> result = this.wordServiceImpl.findByName(Arrays.asList("hello", "world", "text"));
    Assertions.assertEquals(2, result.size());
    Assertions.assertEquals(helloWordDTO, result.get(0));
    Assertions.assertEquals(textWordDTO, result.get(1));
  }

}
