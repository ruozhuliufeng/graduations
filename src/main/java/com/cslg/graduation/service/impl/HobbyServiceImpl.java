package com.cslg.graduation.service.impl;

import com.cslg.graduation.entity.Hobby;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.mapper.HobbyMapper;
import com.cslg.graduation.service.HobbyService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class HobbyServiceImpl implements HobbyService {

    @Autowired
    private HobbyMapper hobbyMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Hobby> findAll() {
        return hobbyMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Hobby> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Hobby> hobbys = (Page<Hobby>) hobbyMapper.selectAll();
        return new PageResult<Hobby>(hobbys.getTotal(),hobbys.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Hobby> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return hobbyMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Hobby> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Hobby> hobbys = (Page<Hobby>) hobbyMapper.selectByExample(example);
        return new PageResult<Hobby>(hobbys.getTotal(),hobbys.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Hobby findById(Integer id) {
        return hobbyMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param hobby
     */
    @Override
    public void add(Hobby hobby) {
        hobbyMapper.insert(hobby);
    }

    /**
     * 修改
     * @param hobby
     */
    @Override
    public void update(Hobby hobby) {
        hobbyMapper.updateByPrimaryKeySelective(hobby);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        hobbyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Hobby.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 兴趣名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 兴趣阶段
            if(searchMap.get("sid")!=null && !"".equals(searchMap.get("sid"))){
                criteria.andLike("sid","%"+searchMap.get("sid")+"%");
            }

            // 兴趣id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
