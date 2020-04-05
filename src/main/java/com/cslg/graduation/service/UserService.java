package com.cslg.graduation.service;

import com.cslg.graduation.dto.UserDTO;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.User;

import java.util.List;
import java.util.Map;

/**
 * user业务逻辑层
 */
 public interface UserService {


     List<User> findAll();


     PageResult<User> findPage(int page, int size);


     List<User> findList(Map<String, Object> searchMap);


     PageResult<User> findPage(Map<String, Object> searchMap, int page, int size);


     User findById(Integer id);

     void add(User user);


     void update(User user);


     void delete(Integer id);

    User login(UserDTO userDTO);
}
