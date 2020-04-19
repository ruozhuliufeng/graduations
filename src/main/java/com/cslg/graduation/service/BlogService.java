package com.cslg.graduation.service;

import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.entity.PageResult;

import java.util.List;
import java.util.Map;


/**
 * blog业务逻辑层
 */
public interface BlogService {


    List<Blog> findAll();


    PageResult<Blog> findPage(int page, int size);


    List<Blog> findList(Map<String, Object> searchMap);


    PageResult<Blog> findPage(Map<String, Object> searchMap, int page, int size);


    Blog findById(Integer id);

    void add(Blog blog);


    void update(Blog blog);


    void delete(Integer id);

    List<Blog> findMaxBlogs();
}
