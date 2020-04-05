package com.eurovision.homework.repository;

import com.eurovision.homework.model.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

  /**
   * Get all cities with the name length
   *
   * @param length Length to find by
   * @return Cities with the name length specified
   */
  @Query("SELECT c FROM City c WHERE LENGTH(c.name) = :length")
  List<City> findByNameLength(@Param("length") int length);
}
