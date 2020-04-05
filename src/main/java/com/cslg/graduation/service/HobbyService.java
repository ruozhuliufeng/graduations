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


     PageResult<Hobby> findPage(int page, int size);


     List<Hobby> findList(Map<String, Object> searchMap);


     PageResult<Hobby> findPage(Map<String, Object> searchMap, int page, int size);


     Hobby findById(Integer id);

     void add(Hobby hobby);


     void update(Hobby hobby);


     void delete(Integer id);

}
