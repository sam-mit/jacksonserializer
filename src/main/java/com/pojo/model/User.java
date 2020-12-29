package com.pojo.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Date;
import lombok.Data;

@Data
@JsonFilter("userFilter")
public class User {
  private Integer id;
  private String name;
  private Date dateOfBirth;
  private String city;
}
