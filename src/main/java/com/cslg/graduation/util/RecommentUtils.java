package com.cslg.graduation.util;

import com.cslg.graduation.entity.Active;
import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.dto.UserSimilarityDTO;
import com.cslg.graduation.entity.Similarity;

import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述 协同过滤推荐工具类
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 10:01
 */
public class RecommentUtils {

    /**
     * 功能描述: 更新用户的浏览行为，最终插入数据库中
     * @param userId 1 用户ID
     * @param blogId 2 博客ID
     * @return : boolean true 更新成功 flase 更新失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:45
     */
    public static boolean updateBrowseBehavior(Integer userId,Integer blogId){
        boolean flag = false;
        // TODO
        return flag;
    }

    /**
     * 功能描述: 将用户的浏览行为组装城一个map,key为userId,value也是一个map，
     * 这个map记录的是博客的id以及对应的点击量
     * @param activeList 1 用户的浏览行为列表
     * @return : 组装好的用户的购买行为的map集合
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:55
     */
    public static ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> assembleUserBrower(List<Active> activeList) {
        ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> activeMap = new ConcurrentHashMap<>();
        //遍历查询到的用户点击行为数据
        for (Active active:activeList){
            // 获取用户id
            Integer userId = active.getUserId();
            // 获取博客id
            Integer blogId = active.getBlogId();
            // 获取博客点击量
            Integer hits = active.getHits();

            //判断activeMap中是否存在了该userId的信息，如果存在，则进行更新
            if (activeMap.containsKey(userId)){
                ConcurrentHashMap<Integer,Integer> tempMap = activeMap.get(userId);
                tempMap.put(blogId,hits);
                activeMap.put(userId,tempMap);
            }else {
                //不存在则直接put进
                ConcurrentHashMap<Integer,Integer> blogMap = new ConcurrentHashMap<>();
                blogMap.put(blogId,hits);
                activeMap.put(userId,blogMap);
            }

        }
        return activeMap;
    }

    /**
     * 功能描述: 计算用户与用户之间的相似性，返回计算出的用户与用户之间的相似度对象
     * @param activeMap 用户对博客的浏览行为的一个map集合
     * @return : 计算出的用户与用户之间的相似度的对象存储形式
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:23
     */
    public static List<Similarity> calcSimilarityBetweenUsers(ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> activeMap){
        //用户之间的相似度对集合
        List<Similarity> similarityList = new ArrayList<>();
        //获得所有键的集合
        Set<Integer> userSet = activeMap.keySet();
        //吧这些集合放入到ArrayList中
        List<Integer> userIdList = new ArrayList<>(userSet);

        //小于两个说明当前map集合中只有一个map集合的浏览行为，或者1个都没有，直接返回
        if(userIdList.size()<2){
            return similarityList;
        }
        //计算所有用户之间的相似度对
        for (int i = 0; i < userIdList.size()-1; i++) {
            for (int j = 0; j < userIdList.size(); j++) {
                //分别获取两个用户对每个二级类目的点击量
                ConcurrentHashMap<Integer,Integer> userBlogMap = activeMap.get(userIdList.get(i));
                ConcurrentHashMap<Integer,Integer> userRefBlogMap = activeMap.get(userIdList.get(j));
                //获取两个map中二级类目id的集合
                Set<Integer> key1Set = userBlogMap.keySet();
                Set<Integer> key2Set = userRefBlogMap.keySet();
                Iterator<Integer> it1 = key1Set.iterator();
                Iterator<Integer> it2 = key2Set.iterator();
                //两个用户之间的相似度
                double similarity = 0.0;
                //余弦相似度公式中的分子
                double molecule = 0.0;
                //余弦相似度公式中的分母
                double denominator = 1.0;
                // 余弦相似度公式中分母根号下的两个向量的模的值
                double vector1 = 0.0;
                double vector2 = 0.0;
                while(it1.hasNext() && it2.hasNext()){
                    Integer it1Id = it1.next();
                    Integer it2Id = it2.next();
                    //获取博客对应的点击次数
                    Integer hits1 = userBlogMap.get(it1Id);
                    Integer hits2 = userRefBlogMap.get(it2Id);
                    //累加分子
                    molecule += hits1*hits2;
                    //累加分母中的两个向量的模
                    vector1 += Math.pow(hits1,2);
                    vector2 += Math.pow(hits2,2);
                }
                //计算分母
                denominator = Math.sqrt(vector1)*Math.sqrt(vector2);
                //计算整体相似度
                similarity = molecule / denominator;
                //创建用户相似度对象
                Similarity similarity1 = new Similarity();
                similarity1.setUserId(userIdList.get(i));
                similarity1.setUserRefId(userIdList.get(j));
                similarity1.setSimilarity(similarity);
                //将计算出的用户以及用户之间的相似度对象存入List集合
                similarityList.add(similarity1);
            }
        }
        return similarityList;
    }

    /**
     * 功能描述: 找出与userId浏览行为最相似的topN个用户
     * @param userId 需要参考的用户id
     * @param userSimilarityList 参考的用户行为列表
     * @param topN 与userId相似用户的数量
     * @return : 与userId最相似的topN个用户
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:49
     */
    public static List<Integer> getSimilarityBetweenUsers(Integer userId,List<Similarity> similarityList,Integer topN){
        //用来记录与userId相似度最高的前N个用户的id
        List<Integer> similarities = new ArrayList<>(topN);

        //堆排序找出最高的前N个用户，建立小根堆，遍历的时候当前的这个相似度必堆顶元素大就踢掉堆顶的值，
        // 把这个数入堆（把小的都删除干净，所以要建立小根堆）
        PriorityQueue<Similarity> minHeap = new PriorityQueue<Similarity>((o1, o2) -> {
            if (o1.getSimilarity() - o2.getSimilarity()>0){
                return 1;
            }else if (o1.getSimilarity() - o2.getSimilarity() == 0){
                return 0;
            }else {
                return -1;
            }
        });
        for (Similarity similarity:similarityList){
            if (minHeap.size()<topN){
                minHeap.offer(similarity);
                System.out.println(minHeap.peek().getSimilarity());
            }else if (minHeap.peek().getSimilarity()<similarity.getSimilarity()){
                minHeap.poll();
                minHeap.offer(similarity);
            }
        }
        //把得到的最大的相似度的用户id取出来（不要取自己）
        for (Similarity similarity:minHeap){
            similarities.add(similarity.getUserId().equals(userId) ? similarity.getUserRefId():similarity.getUserId());
        }
        return similarities;
    }

    /**
     * 功能描述: 到similarUserList中的用户方位的二级类目中查找userId不经常点的博客获得被推荐的博客id
     * @param userId 被推荐博客的用户id
     * @param similarUserList 与userId相似的用户集合
     * @param userActiveList 所有用户的浏览行为
     * @return : 可以推荐给userId的博客id列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:27
     */
    public static List<Integer> getRecommendateBlog(Integer userId,List<Integer> similarUserList,List<Active> activeList){
        List<Integer> recommeddateBlogList = new ArrayList<>();
        //userId的浏览行为列表
        List<Active> userIdActiveList = findUserBrowerBehavior(userId,activeList);
        //对userId的浏览行为按照博客id排个序，方便后续与推荐的用户的浏览行为中的博客点击次数直接相减
        //避免时间复杂度为o(2)
        Collections.sort(userIdActiveList,(Comparator.comparing(Active::getBlogId)));

        //1.从与userId浏览行为相似的每个用户中找出一个推荐的博客
        for (Integer refId : similarUserList){
            //计算当前用户所点击的博客次数与被推荐的用户所点击的博客的次数的差值
            //知道当前这个用户的浏览行为
            List<Active> currActiveList = findUserBrowerBehavior(refId,activeList);
            //排序
            Collections.sort(currActiveList, Comparator.comparing(Active::getBlogId));

            // 记录差值最大的博客id
            Integer maxBlog = null;
            //记录最大的差值
            double maxDifference = 0.0;
            for (int i = 0; i < currActiveList.size(); i++) {
                //求出点击量差值最大的博客，即为要推荐的博客
                double difference = Math.abs(currActiveList.get(i).getHits() - userIdActiveList.get(i).getHits());
                if (difference>maxDifference){
                    maxDifference = difference;
                    maxBlog = currActiveList.get(i).getBlogId();
                }

            }
            recommeddateBlogList.add(maxBlog);
        }
        return recommeddateBlogList;
    }

    /**
     * 功能描述:  找到当前用户的浏览行为列表
     * @param userId 当前用户id
     * @param userActiveList 所有用户的浏览行为列表
     * @return : 返回当前用户的浏览行为列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:30
     */
    public static List<Active> findUserBrowerBehavior(Integer userId,List<Active> activeList){
        List<Active> currActiveList = new ArrayList<>();
        for (Active active:activeList){
            if (active.getUserId().equals(userId)){
                currActiveList.add(active);
            }
        }
        return currActiveList;
    }

    /**
     * 功能描述: 找到当前博客列表中点击量最高的博客
     * @param blogList 博客列表
     * @return : 点击量最高的博客
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:32
     */
    public static Blog findMaxHitsBlog(List<Blog> blogList){
        if (blogList==null || blogList.size()==0){
            return null;
        }
        //记录当前最大的点击量
        Integer maxHits = 1;
        //记录当前点击量最大的博客
        Blog blog = new Blog();
        for (Blog temp:blogList){
            if (temp.getHits()>=maxHits){
                maxHits = temp.getHits();
                blog = temp;
            }
        }
        return blog;
    }
}
