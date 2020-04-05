package com.eurovision.homework.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cities")
public class City {

  @Id
  Integer id;

  String name;
}
