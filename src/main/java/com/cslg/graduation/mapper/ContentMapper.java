package com.cslg.graduation.mapper;

import com.cslg.graduation.dto.ContentDTO;
import com.cslg.graduation.entity.Content;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ContentMapper extends Mapper<Content> {
    List<ContentDTO> findContentList();
}
