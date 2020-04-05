package com.cslg.graduation.service.impl;

import com.cslg.graduation.dto.UserDTO;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.User;
import com.cslg.graduation.mapper.UserMapper;
import com.cslg.graduation.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<User> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<User> users = (Page<User>) userMapper.selectAll();
        return new PageResult<User>(users.getTotal(),users.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<User> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return userMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<User> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<User> users = (Page<User>) userMapper.selectByExample(example);
        return new PageResult<User>(users.getTotal(),users.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param user
     */
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    /**
     * 修改
     * @param user
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User login(UserDTO userDTO) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",userDTO.getUsername());
        map.put("password",userDTO.getPassword());
        Example example = createExample(map);
        List<User> users = userMapper.selectByExample(example);
        if (users !=null && users.size()>0){
            return users.get(0);
        }
        return null;
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 用户名
            if(searchMap.get("username")!=null && !"".equals(searchMap.get("username"))){
                criteria.andLike("username","%"+searchMap.get("username")+"%");
            }
            // 密码
            if(searchMap.get("password")!=null && !"".equals(searchMap.get("password"))){
                criteria.andLike("password","%"+searchMap.get("password")+"%");
            }
            // 邮箱
            if(searchMap.get("email")!=null && !"".equals(searchMap.get("email"))){
                criteria.andLike("email","%"+searchMap.get("email")+"%");
            }
            // 兴趣名称
            if(searchMap.get("hname")!=null && !"".equals(searchMap.get("hname"))){
                criteria.andLike("hname","%"+searchMap.get("hname")+"%");
            }
            // 兴趣阶段
            if(searchMap.get("sname")!=null && !"".equals(searchMap.get("sname"))){
                criteria.andLike("sname","%"+searchMap.get("sname")+"%");
            }

            // 用户id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 激活状态：0 待激活 1 已激活
            if(searchMap.get("status")!=null ){
                criteria.andEqualTo("status",searchMap.get("status"));
            }
            // 用户类型：0 普通用户 1 管理员
            if(searchMap.get("type")!=null ){
                criteria.andEqualTo("type",searchMap.get("type"));
            }

        }
        return example;
    }

}
