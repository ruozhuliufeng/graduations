package com.cslg.graduation.service;

import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.Stage;

import java.util.List;
import java.util.Map;


/**
 * stage业务逻辑层
 */
 public interface StageService {


     List<Stage> findAll();


     PageResult<Stage> findPage(int page, int size);


     List<Stage> findList(Map<String, Object> searchMap);


     PageResult<Stage> findPage(Map<String, Object> searchMap, int page, int size);


     Stage findById(Integer id);

     void add(Stage stage);


     void update(Stage stage);


     void delete(Integer id);

}
