package com.eurovision.homework.repository;

import com.eurovision.homework.model.Word;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

  /**
   * Get all the words in the list.
   *
   * @param names Name list to search
   * @return List of words contained in the list
   */
  List<Word> findByNameInIgnoreCase(List<String> names);

}
