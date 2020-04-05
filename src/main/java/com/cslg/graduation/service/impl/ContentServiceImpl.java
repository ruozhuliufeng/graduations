package com.cslg.graduation.service.impl;

import com.cslg.graduation.entity.Content;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.mapper.ContentMapper;
import com.cslg.graduation.service.ContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Content> findAll() {
        return contentMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Content> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Content> contents = (Page<Content>) contentMapper.selectAll();
        return new PageResult<Content>(contents.getTotal(),contents.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Content> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return contentMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Content> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Content> contents = (Page<Content>) contentMapper.selectByExample(example);
        return new PageResult<Content>(contents.getTotal(),contents.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Content findById(Integer id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param content
     */
    @Override
    public void add(Content content) {
        contentMapper.insert(content);
    }

    /**
     * 修改
     * @param content
     */
    @Override
    public void update(Content content) {
        contentMapper.updateByPrimaryKeySelective(content);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        contentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Content.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 内容名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 分享文件链接
            if(searchMap.get("clink")!=null && !"".equals(searchMap.get("clink"))){
                criteria.andLike("clink","%"+searchMap.get("clink")+"%");
            }

            // 内容id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 是否完成 0 未完成 1 已完成
            if(searchMap.get("status")!=null ){
                criteria.andEqualTo("status",searchMap.get("status"));
            }
            // 所属阶段
            if(searchMap.get("sid")!=null ){
                criteria.andEqualTo("sid",searchMap.get("sid"));
            }

        }
        return example;
    }

}
