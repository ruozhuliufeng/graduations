package com.cslg.graduation.service;


import com.cslg.graduation.entity.Advice;

import java.util.List;

/**
 * advice业务逻辑层
 */
public interface AdviceService {

    void add(Advice advice);

    void deleteById(Integer id);

    List<Advice> findAll();
}
