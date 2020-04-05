package com.cslg.graduation.service;


import com.cslg.graduation.entity.Comment;
import com.cslg.graduation.entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * comment业务逻辑层
 */
public interface CommentService {


    List<Comment> findAll();


    PageResult<Comment> findPage(int page, int size);


    List<Comment> findList(Map<String, Object> searchMap);


    PageResult<Comment> findPage(Map<String, Object> searchMap, int page, int size);


    Comment findById(Integer id);

    void add(Comment comment);


    public void update(Comment comment);


    public void delete(Integer id);

}
