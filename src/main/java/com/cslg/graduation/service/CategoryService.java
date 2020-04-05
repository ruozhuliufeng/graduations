package com.cslg.graduation.service;

import com.cslg.graduation.entity.Category;
import com.cslg.graduation.entity.PageResult;

import java.util.List;
import java.util.Map;


/**
 * category业务逻辑层
 */
public interface CategoryService {


    List<Category> findAll();


    PageResult<Category> findPage(int page, int size);


    List<Category> findList(Map<String, Object> searchMap);


    PageResult<Category> findPage(Map<String, Object> searchMap, int page, int size);


    Category findById(Integer id);

     void add(Category category);


     void update(Category category);


     void delete(Integer id);

}
