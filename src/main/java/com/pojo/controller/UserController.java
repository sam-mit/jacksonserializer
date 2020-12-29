package com.pojo.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pojo.model.User;
import java.time.Instant;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @GetMapping(value = "/user/{id}")
  public MappingJacksonValue getUser(@PathVariable("id") String id){
    SimpleBeanPropertyFilter simpleBeanPropertyFilter =
      SimpleBeanPropertyFilter.filterOutAllExcept("dateOfBirth", "city");
    FilterProvider filterProvider = new SimpleFilterProvider()
      .addFilter("userFilter", simpleBeanPropertyFilter);

    User user = new User();
    user.setId(Integer.valueOf(id));
    user.setCity("new york");
    user.setName("sam");
    user.setDateOfBirth(Date.from(Instant.now()));

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
    mappingJacksonValue.setFilters(filterProvider);
    return mappingJacksonValue;
  }

//  @RequestMapping(value = "/users", method= RequestMethod.GET)
//  MappingJacksonValue getAllUsers(){
//    SimpleBeanPropertyFilter simpleBeanPropertyFilter =
//      SimpleBeanPropertyFilter.serializeAllExcept("dateOfBirth", "city");
//    FilterProvider filterProvider = new SimpleFilterProvider()
//      .addFilter("userFilter", simpleBeanPropertyFilter);
//    List<User> userList = userService.getAllUsers();
//    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userList);
//    mappingJacksonValue.setFilters(filterProvider);
//    return mappingJacksonValue;
//  }
}
