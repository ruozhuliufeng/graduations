package com.cslg.graduation.service.impl;

import com.cslg.graduation.entity.Comment;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.mapper.CommentMapper;
import com.cslg.graduation.service.CommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Comment> findAll() {
        return commentMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Comment> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Comment> comments = (Page<Comment>) commentMapper.selectAll();
        return new PageResult<Comment>(comments.getTotal(),comments.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Comment> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return commentMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Comment> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Comment> comments = (Page<Comment>) commentMapper.selectByExample(example);
        return new PageResult<Comment>(comments.getTotal(),comments.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Comment findById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param comment
     */
    @Override
    public void add(Comment comment) {
        commentMapper.insert(comment);
    }

    /**
     * 修改
     * @param comment
     */
    @Override
    public void update(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 评论内容
            if(searchMap.get("content")!=null && !"".equals(searchMap.get("content"))){
                criteria.andLike("content","%"+searchMap.get("content")+"%");
            }
            // 评论标题
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
            }

            //  评论id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 博客id
            if(searchMap.get("topicId")!=null ){
                criteria.andEqualTo("topicId",searchMap.get("topicId"));
            }
            // 用户id
            if(searchMap.get("userId")!=null ){
                criteria.andEqualTo("userId",searchMap.get("userId"));
            }

        }
        return example;
    }

}
