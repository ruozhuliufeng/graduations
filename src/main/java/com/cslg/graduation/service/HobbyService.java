package com.cslg.graduation.service;

import com.cslg.graduation.entity.Hobby;
import com.cslg.graduation.entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * hobby业务逻辑层
 */
 public interface HobbyService {


     List<Hobby> findAll();


     List<Hobby> findList(Map<String, Object> searchMap);



     Hobby findById(Integer id);

     void add(Hobby hobby);


     void update(Hobby hobby);


     void delete(Integer id);

     Hobby findMaxHobby();
}
