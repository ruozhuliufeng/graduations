# graduations
springboot+freemarker 毕业设计
## 前端页面
- / :index.ftl 主页
- /login :login.ftl 登录页
- /register : register.ftl 注册页
- /info : /page/info.ftl 介绍页
- /err : err.ftl：错误页
- /user/center :/page/center.ftl 个人中心页
- /user/userUpdate :/page/userUpdate.ftl :个人中心修改页
- /blog/ : /forum/main.ftl 博客页
- /blog/topicAdd : /forum/topicAdd.ftl 博客添加页
- /bolg/topicDetails : /forum/topicDetails.ftl :博客详情页
### 后台页面
- /admin/advice : /admin/advice.ftl :建议管理(删除)
- /admin/category : /admin/category.ftl :分类管理(添加、修改、删除)
- /admin/categoryAdd :/admin/categoryAdd.ftl :分类添加
- /admin/categroyUpdate : /admin/categoryUpdate.ftl 分类修改
-  /admin/comment : /admin/comment.ftl :评论管理(删除)
- /admin/content : /admin/content.ftl :兴趣内容管理(添加、修改、删除)
- /admin/contentAdd : /admin/ContentAdd.ftl :兴趣内容添加
- /admin/contentUpdate : /admin/contentUpdate.ftl :兴趣内容修改
- /admin/hobby :/admin/hobby.ftl ：兴趣名称
- /admin/hobbyAdd : /admin/hobbyAdd.ftl :兴趣添加
- /admin/hobbyUpdate : /admin/hobbyUpdate.ftl :兴趣修改
- /admin/index : /admin/index.ftl ：后台首页
- /admin/login : /admin/login.ftl : 后台登录
- /admin/stage : /admin/stage.ftl : 阶段管理(添加、删除)
- /admin/stageAdd : /admin/stageAdd.ftl : 阶段添加
- /admin/topic : /admin/topic.ftl : 博客管理(修改、删除)
- /admin/topicUpdate : /admin/topicUpdate.ftl :博客修改
- /admin/user : /admin/user.ftl :用户管理(修改权限、删除)
- /admin/userUpdate : /admin/userUpdate.ftl ：用户修改

## 推荐算法相关
- com.cslg.graduation.dto.UserActiveDTO: 用户与博客点击量
- com.cslg.graduation.dto.UserSimilarityDTO: 用户与用户之间的相似度
- com.cslg.graduation.mapper.ActiveMapper: 用户与博客点击量处理的mapper
- com.cslg.graduation.mapper.SimilarityMapper: 用户与用户之间的相似度的处理
- com.cslg.graduation.service.ActiveService: 用户与博客点击量处理的Service
- ActiveServiceImpl: 用户与博客点击量处理的Service具体实现类
- com.cslg.graduation.service.SimilarityService: 用户与用户之间的相似度的Service
- SimilarityServiceImpl: 用户与用户之间的相似度的Service具体实现类
- com.cslg.graduation.util.RecommmentUtils: 推荐模块工具类

## 待完成的功能


- 分页模块
    - 通过DTO获取分页数据
    - 分页查询，获得的list存入Session
- 新用户的推荐问题
    - 1.获得点击量最高的
    - 2.如果通过推荐算法获得的博客列表为空，推荐点击量高的
- 用户与用户的相似度
    - 监听器，每天定时计算相似度
- 用户与博客的点击量处理
- 博客图片上传到七牛云
- 根据用户的浏览内容推荐兴趣
  - 当前博客的分类
## 存在的问题及解决

- 博客推荐功能
  - 解决思路：
    - 1.获得用户与博客的点击量，建立用户id-博客id-点击量的表
    - 2.根据余弦相似，数值越大，越接近1
    - 3.将用户封装成key，博客与点击量封装成map，作为用户id的value，再封装成用户浏览行为的activeMap
    - 4.根据用户的浏览行为的map，计算用户与用户之间的余弦相似度，并保存在用户id-被引用用户id-相似度表中
    - 5.根据推荐用户id,需要参考的浏览行为列表，前N个用户，获得与推荐用户相似度最高的N个用户
    - 6.根据推荐用户id，与推荐用户相似的用户列表，推荐用户的浏览行为，获得推荐的博客id列表
- 兴趣推荐功能
  - 解决思路
    - 1.获得用户与兴趣的关注度，建立用户id-兴趣id-关注度的表
    - 2.根据余弦相似，数值越大，越接近1
    - 3.将用户封装成key，兴趣与兴趣点击量封装成map，作为用户id的value，在封装成用户兴趣的activeMap
    - 4.
- 工具类中计算相似度，存入数据库库中为1
  - 解决：设置小数点后显示10位
- 自定义mapper方法时无法查询数据库或查询出来的值为null
  - 解决：为查询出来的列定义别名，和需要返回的DTO名称一致
- 学习系统问题          
  - 解决：
    - 1.获取用户所处的阶段，如果获取高于当前阶段的内容，设置值为空，否则设置当前阶段的内容状态为未完成
    - 2.获取用户发布的所有博客信息，只需要博客中的userId(用户id)和hId(兴趣内容id),若兴趣内容id存在，则设置兴趣内容状态为已完成


## IDEA插件
- Leetcode editor
- Jrebel for Intellij
  - https://jrebel.qekang.com/
