package com.cslg.graduation.service.impl;

import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.mapper.BlogMapper;
import com.cslg.graduation.service.BlogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Blog> findAll() {
        return blogMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Blog> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Blog> blogs = (Page<Blog>) blogMapper.selectAll();
        return new PageResult<Blog>(blogs.getTotal(),blogs.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Blog> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return blogMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Blog> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Blog> blogs = (Page<Blog>) blogMapper.selectByExample(example);
        return new PageResult<Blog>(blogs.getTotal(),blogs.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Blog findById(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param blog
     */
    @Override
    public void add(Blog blog) {
        blogMapper.insert(blog);
    }

    /**
     * 修改
     * @param blog
     */
    @Override
    public void update(Blog blog) {
        blogMapper.updateByPrimaryKeySelective(blog);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        blogMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Blog.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 博文标题
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
            }
            // 博文内容
            if(searchMap.get("content")!=null && !"".equals(searchMap.get("content"))){
                criteria.andLike("content","%"+searchMap.get("content")+"%");
            }

            // 博文id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 所属分类
            if(searchMap.get("cid")!=null ){
                criteria.andEqualTo("cid",searchMap.get("cid"));
            }
            // 用户id
            if(searchMap.get("userId")!=null ){
                criteria.andEqualTo("userId",searchMap.get("userId"));
            }
            // 是否精华：0 不精华 1 精华
            if(searchMap.get("good")!=null ){
                criteria.andEqualTo("good",searchMap.get("good"));
            }
            // 是否置顶：0 不置顶 1 置顶
            if(searchMap.get("top")!=null ){
                criteria.andEqualTo("top",searchMap.get("top"));
            }

        }
        return example;
    }

}
