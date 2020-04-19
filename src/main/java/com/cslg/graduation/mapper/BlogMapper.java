package com.cslg.graduation.mapper;

import com.cslg.graduation.entity.Blog;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogMapper extends Mapper<Blog> {

    //获得点击量前十的博客
    List<Blog> findMaxBlogs();
}
