package com.cslg.graduation.util;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.dto.UserSimilarityDTO;
import com.cslg.graduation.entity.Attention;
import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.entity.Focus;
import com.cslg.graduation.entity.Hobby;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/15 12:03
 */
public class HobbyRecommentUtils {


    /**
     * 功能描述: 将用户的浏览行为组装城一个map,key为userId,value也是一个map，
     * 这个map记录的是兴趣的id以及对应的点击量
     * @return : 组装好的用户的购买行为的map集合
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:55
     */
    public static ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> assembleUserBrower(List<Focus> focusList) {
        ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> activeMap = new ConcurrentHashMap<>();
        //遍历查询到的用户点击行为数据
        for (Focus userActive:focusList){
            // 获取用户id
            Integer userId = userActive.getUserId();
            // 获取博客id
            Integer blogId = userActive.getHobbyId();
            // 获取博客点击量
            Integer hits = userActive.getHits();

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
    public static List<Attention> calcSimilarityBetweenUsers(ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> activeMap){
        //用户之间的相似度对集合
        List<Attention> attentionList = new ArrayList<>();
        //获得所有键的集合
        Set<Integer> userSet = activeMap.keySet();
        //吧这些集合放入到ArrayList中
        List<Integer> userIdList = new ArrayList<>(userSet);

        //小于两个说明当前map集合中只有一个map集合的浏览行为，或者1个都没有，直接返回
        if(userIdList.size()<2){
            return attentionList;
        }
        //计算所有用户之间的相似度对
        for (int i = 0; i < userIdList.size()-1; i++) {
            for (int j = 0; j < userIdList.size(); j++) {
                //分别获取两个用户对每个兴趣的点击量
                ConcurrentHashMap<Integer,Integer> userBlogMap = activeMap.get(userIdList.get(i));
                ConcurrentHashMap<Integer,Integer> userRefBlogMap = activeMap.get(userIdList.get(j));
                //获取两个map中兴趣id的集合
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
                    //获取兴趣对应的点击次数
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
                Attention attention = new Attention();
                attention.setUserId(userIdList.get(i));
                attention.setUserRefId(userIdList.get(j));
                attention.setAttention(similarity);
                //将计算出的用户以及用户之间的相似度对象存入List集合
                attentionList.add(attention);
            }
        }
        return attentionList;
    }



    /**
     * 功能描述: 找出与userId浏览行为最相似的topN个用户
     * @param userId 需要参考的用户id
     * @param attentionList 参考的用户行为列表
     * @param topN 与userId相似用户的数量
     * @return : 与userId最相似的topN个用户
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:49
     */
    public static List<Integer> getSimilarityBetweenUsers(Integer userId,List<Attention> attentionList,Integer topN){
        //用来记录与userId相似度最高的前N个用户的id
        List<Integer> similarityList = new ArrayList<>(topN);

        //堆排序找出最高的前N个用户，建立小根堆，遍历的时候当前的这个相似度必堆顶元素大就踢掉堆顶的值，
        // 把这个数入堆（把小的都删除干净，所以要建立小根堆）
        PriorityQueue<Attention> minHeap = new PriorityQueue<Attention>((o1, o2) -> {
            if (o1.getAttention() - o2.getAttention()>0){
                return 1;
            }else if (o1.getAttention() - o2.getAttention() == 0){
                return 0;
            }else {
                return -1;
            }
        });
        for (Attention attention:attentionList){
            if (minHeap.size()<topN){
                minHeap.offer(attention);
                System.out.println(minHeap.peek().getAttention());
            }else if (minHeap.peek().getAttention()<attention.getAttention()){
                minHeap.poll();
                minHeap.offer(attention);
            }
        }
        //把得到的最大的相似度的用户id取出来（不要取自己）
        for (Attention attention:minHeap){
            similarityList.add(attention.getUserId().equals(userId) ? attention.getUserRefId():attention.getUserId());
        }
        return similarityList;
    }



    /**
     * 功能描述: 到similarUserList中的用户方位的二级类目中查找userId不经常点的博客获得被推荐的博客id
     * @param userId 被推荐博客的用户id
     * @param similarUserList 与userId相似的用户集合
     * @param focusList 所有用户的浏览行为
     * @return : 可以推荐给userId的博客id列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:27
     */
    public static List<Integer> getRecommendateHobby(Integer userId,List<Integer> similarUserList,List<Focus> focusList){
        List<Integer> recommeddateHobbyList = new ArrayList<>();
        //userId的浏览行为列表
        List<Focus> userIdFocusList = findUserBrowerBehavior(userId,focusList);
        //对userId的浏览行为按照兴趣id排个序，方便后续与推荐的用户的浏览行为中的兴趣点击次数直接相减
        //避免时间复杂度为o(2)
        userIdFocusList.sort((Comparator.comparing(Focus::getHobbyId)));

        //1.从与userId浏览行为相似的每个用户中找出一个推荐的兴趣
        for (Integer refId : similarUserList){
            //计算当前用户所点击的兴趣次数与被推荐的用户所点击的兴趣的次数的差值
            //知道当前这个用户的浏览行为
            List<Focus> currFocusList = findUserBrowerBehavior(refId,focusList);
            //排序
            currFocusList.sort(Comparator.comparing(Focus::getHobbyId));

            // 记录差值最大的博客id
            Integer maxBlog = null;
            //记录最大的差值
            double maxDifference = 0.0;
            for (int i = 0; i < currFocusList.size(); i++) {
                //求出点击量差值最大的兴趣，即为要推荐的兴趣
                double difference = Math.abs(currFocusList.get(i).getHits() - userIdFocusList.get(i).getHits());
                if (difference>maxDifference){
                    maxDifference = difference;
                    maxBlog = currFocusList.get(i).getHobbyId();
                }

            }
            recommeddateHobbyList.add(maxBlog);
        }
        return recommeddateHobbyList;
    }



    /**
     * 功能描述:  找到当前用户的浏览行为列表
     * @param userId 当前用户id
     * @param focusList 所有用户的浏览行为列表
     * @return : 返回当前用户的浏览行为列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:30
     */
    public static List<Focus> findUserBrowerBehavior(Integer userId,List<Focus> focusList){
        List<Focus> currFocusList = new ArrayList<>();
        for (Focus focus:focusList){
            if (focus.getUserId().equals(userId)){
                currFocusList.add(focus);
            }
        }
        return currFocusList;
    }



    /**
     * 功能描述: 找到当前兴趣列表中点击量最高的兴趣
     * @param hobbyList 兴趣列表
     * @return : 点击量最高的兴趣
     * @author : ruozhuliufeng
     * @date : 2020/4/12 13:32
     */
    public static Hobby findMaxHitsHobby(List<Hobby> hobbyList){
        if (hobbyList==null || hobbyList.size()==0){
            return null;
        }
        //记录当前最大的点击量
        Integer maxHits = 1;
        //记录当前点击量最大的兴趣
        Hobby hobby = new Hobby();
        for (Hobby temp:hobbyList){
            if (temp.getHits()>=maxHits){
                maxHits = temp.getHits();
                hobby = temp;
            }
        }
        return hobby;
    }
}
