package com.cslg.graduation.service;


import com.cslg.graduation.entity.Content;
import com.cslg.graduation.entity.PageResult;

import java.util.List;
import java.util.Map;


/**
 * content业务逻辑层
 */
 public interface ContentService {


     List<Content> findAll();


     PageResult<Content> findPage(int page, int size);


     List<Content> findList(Map<String, Object> searchMap);


     PageResult<Content> findPage(Map<String, Object> searchMap, int page, int size);


     Content findById(Integer id);

     void add(Content content);


     void update(Content content);


     void delete(Integer id);

}
