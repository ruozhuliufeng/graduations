package com.cslg.graduation.service.impl;
import com.cslg.graduation.entity.Advice;
import com.cslg.graduation.mapper.AdviceMapper;
import com.cslg.graduation.service.AdviceService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public void add(Advice advice) {

        adviceMapper.insert(advice);
    }
}
