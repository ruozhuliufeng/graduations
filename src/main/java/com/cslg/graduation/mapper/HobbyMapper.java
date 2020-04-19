package com.cslg.graduation.mapper;

import com.cslg.graduation.entity.Hobby;
import tk.mybatis.mapper.common.Mapper;

public interface HobbyMapper extends Mapper<Hobby> {

    //获得点击量最大的兴趣
    Hobby findMaxhobby();
}
