package com.eurovision.homework.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "word")
public class Word {

  @Id
  Integer id;

  String name;
}
