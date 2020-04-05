package com.cslg.graduation.service.impl;

import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.Stage;
import com.cslg.graduation.mapper.StageMapper;
import com.cslg.graduation.service.StageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class StageServiceImpl implements StageService {

    @Autowired
    private StageMapper stageMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Stage> findAll() {
        return stageMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Stage> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Stage> stages = (Page<Stage>) stageMapper.selectAll();
        return new PageResult<Stage>(stages.getTotal(),stages.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Stage> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return stageMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Stage> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Stage> stages = (Page<Stage>) stageMapper.selectByExample(example);
        return new PageResult<Stage>(stages.getTotal(),stages.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Stage findById(Integer id) {
        return stageMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param stage
     */
    @Override
    public void add(Stage stage) {
        stageMapper.insert(stage);
    }

    /**
     * 修改
     * @param stage
     */
    @Override
    public void update(Stage stage) {
        stageMapper.updateByPrimaryKeySelective(stage);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        stageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */

    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Stage.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 阶段名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }

            // 阶段id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
