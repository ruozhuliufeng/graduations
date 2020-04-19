package com.cslg.graduation;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.dto.UserSimilarityDTO;
import com.cslg.graduation.entity.Active;
import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.entity.Similarity;
import com.cslg.graduation.service.BlogService;
import com.cslg.graduation.service.ActiveService;
import com.cslg.graduation.service.SimilarityService;
import com.cslg.graduation.util.RecommentUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述 测试推荐模块中的一些功能
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 16:54
 */
@SpringBootTest
public class RecommendateTest {

    @Autowired
    private ActiveService activeService;
    @Autowired
    private SimilarityService similarityService;

    @Autowired
    private BlogService blogService;
    /**
     * 功能描述: 测试列出所有用户的购买行为的方法
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:56
     */
    @Test
    public void testListAlluserActive(){
        //1.查询出所有用户对所有博客的浏览记录
        List<Active> userActiveDTOList = activeService.listAllUserActive();
        //2.输出浏览记录列表
        for (Active userActiveDTO : userActiveDTOList){
            System.out.println(userActiveDTO.getUserId()+" "+userActiveDTO.getBlogId()+" "+userActiveDTO.getHits());
        }
    }
    /**
     * 功能描述: 测试组装用户行为数据的方法
     * @author : ruozhuliufeng
     * @date : 2020/4/12 17:05
     */
    @Test
    public void testAssembleUserBehavior(){
        // 1.查询所有的用户浏览记录
        List<Active> userActiveDTOList = activeService.listAllUserActive();

        // 2.调用推荐模块工具类的方法组装城一个ConcurrentHashMap来存储每个用户以及其对应的博客的点击量
        ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> activeMap = RecommentUtils.assembleUserBrower(userActiveDTOList);
        // 3.输出封装后的map的大小(也就是多少个用户的浏览记录)
        System.out.println(activeMap.size());
    }

    /**
     * 功能描述: 测试计算用户的相似度
     * @author : ruozhuliufeng
     * @date : 2020/4/12 21:34
     */
    @Test
    public void testCalcSimilarityBetweenUser(){
        //1.查询所有的用户浏览记录
        List<Active> userActiveDTOList = activeService.listAllUserActive();
        //2.调用推荐模块工具类的方法组装城一个ConcurrentHashMap来存储每个用户以及其对应的博客的点击量
        ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> activeMap = RecommentUtils.assembleUserBrower(userActiveDTOList);
        //3.调用推荐模块工具类的方法计算用户与用户之间的相似度
        List<Similarity> similarityList = RecommentUtils.calcSimilarityBetweenUsers(activeMap);
        //4.输出计算好的用户之间的相似度
        for (Similarity usim:similarityList){
            System.out.println(usim.getUserId()+"\t"+usim.getUserRefId()+"\t"+usim.getSimilarity());
            //5.如果用户之间的 相似度已经存在于数据库就修改，不存在就添加
            if (similarityService.isExistUserSimilarity(usim)){//修改
                boolean flag = similarityService.updateUserSimilarity(usim);
                if (flag){
                    System.out.println("修改数据成功");
                }else {
                    System.out.println("修改数据失败");
                }
            }else {//新增
                boolean flag = similarityService.saveUserSimilarity(usim);
                if (flag){
                    System.out.println("插入数据成功");
                }else {
                    System.out.println("插入数据失败");
                }
            }
        }
    }

    /**
     * 功能描述: 测试查询用户相似度集合列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 22:08
     */
    @Test
    public void testListUserSimilarity(){
        //1.查询出某个用户与其他用户的相似度列表
        List<Similarity> userSimilarityList = similarityService.listUserSimilarityByUId(5);
        //2.打印输出
        for (Similarity userSimilarityDTO:userSimilarityList){
            System.out.println(userSimilarityDTO.getUserId()+"\t"
            +userSimilarityDTO.getUserRefId()+"\t"
            +userSimilarityDTO.getSimilarity());
        }
    }

    /**
     * 功能描述: 测试取出与指定用户相似度最高的前N个用户
     * @author : ruozhuliufeng
     * @date : 2020/4/12 22:14
     */
    @Test
    public void testGetTopNUser(){
        //查询出某个用户与其他用户的相似度列表
        List<Similarity> userSimilarityList = similarityService.listUserSimilarityByUId(6);
        //打印输出
        for (Similarity userSimilarityDTO:userSimilarityList){
            System.out.println(userSimilarityDTO.getUserId()+"\t"
                    +userSimilarityDTO.getUserRefId()+"\t"
                    +userSimilarityDTO.getSimilarity());
        }
        // 获得与id为6的用户的浏览行为最相似的前2个用户
        List<Integer> userIds = RecommentUtils.getSimilarityBetweenUsers(6,userSimilarityList,3);
        // 打印输出
        System.out.println("与"+6+"号用户最相似的前3个用户是：");
        for (Integer userRefId:userIds){
            System.out.println(userRefId);
        }

    }

    /**
     * 功能描述: 测试获得被推荐的博客id列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 22:20
     */
    @Test
    public void testGetRecommendateBlogs(){
        // 1.查询出某个用户与其他用户的相似度列表
        List<Similarity> userSimilarityList = similarityService.listUserSimilarityByUId(8);
        // 2.获得所有的用户的浏览记录
        List<Active> userActiveList = activeService.listAllUserActive();
        for (Similarity userSimilarityDTO:userSimilarityList){
            System.out.println(userSimilarityDTO.getUserId()+"\t"
                    +userSimilarityDTO.getUserRefId()+"\t"
                    +userSimilarityDTO.getSimilarity());
        }
        // 3.找出与id为8的用户浏览行为最相似的前3个用户
        List<Integer> userIds = RecommentUtils.getSimilarityBetweenUsers(8,userSimilarityList,3);
        // 打印输出
        System.out.println("与"+8+"号用户最相似的前3个用户是：");
        for (Integer userRefId:userIds){
            System.out.println(userRefId);
        }
        // 4.获得应该推荐给8号用户的博客列表
        List<Integer> recommendateBlog = RecommentUtils.getRecommendateBlog(8,userIds,userActiveList);
        for (Integer blogId:recommendateBlog){
            System.out.println("被推荐的博客id是："+blogId);
        }
    }

    /**
     * 功能描述: 测试获取被推荐的博客列表（从被推荐的博客列表中找出点击量最大的博客作为推荐的博客）
     * @author : ruozhuliufeng
     * @date : 2020/4/12 22:27
     */
    @Test
    public void testGetRecommendateBlog(){
        // 1.查询出某个用户与其他用户的相似度列表
        List<Similarity> userSimilarityList = similarityService.listUserSimilarityByUId(8);
        // 2.获得所有的用户的浏览记录
        List<Active> userActiveList = activeService.listAllUserActive();
        for (Similarity userSimilarityDTO:userSimilarityList){
            System.out.println(userSimilarityDTO.getUserId()+"\t"
                    +userSimilarityDTO.getUserRefId()+"\t"
                    +userSimilarityDTO.getSimilarity());
        }
        // 3.找出与id为8的用户浏览行为最相似的前3个用户
        List<Integer> userIds = RecommentUtils.getSimilarityBetweenUsers(11,userSimilarityList,3);
        // 打印输出
        System.out.println("与"+11+"号用户最相似的前3个用户是：");
        for (Integer userRefId:userIds){
            System.out.println(userRefId);
        }
        // 4.获得应该推荐给8号用户的博客列表
        List<Integer> recommendateBlog = RecommentUtils.getRecommendateBlog(11,userIds,userActiveList);
        for (Integer blogId:recommendateBlog){
            System.out.println("被推荐的博客id是："+blogId);
        }
        // 5.找出博客列表中的所有博客
        List<Blog> Blogs = new ArrayList<>();

        for(Integer blogId:recommendateBlog){
            Blog blog = blogService.findById(blogId);
            Blogs.add(blog);
        }
        Blog maxHitsBlog = RecommentUtils.findMaxHitsBlog(Blogs);
        List<Blog> recommendateBlogs = new ArrayList<>();
        recommendateBlogs.add(maxHitsBlog);
        // 6.打印输出
        for (Blog blog:recommendateBlogs){
            System.out.println("被推荐的博客："+blog);
        }
    }
}
