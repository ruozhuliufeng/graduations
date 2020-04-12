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
- com.cslg.graduation.mapper.UserActiveMapper: 用户与博客点击量处理的mapper
- com.cslg.graduation.mapper.UserSimilarityMapper: 用户与用户之间的相似度的处理
- com.cslg.graduation.service.UserActiveService: 用户与博客点击量处理的Service
- com.cslg.graduation.service.impl.UserActiveServiceImpl: 用户与博客点击量处理的Service具体实现类
- com.cslg.graduation.service.UserSimilarityService: 用户与用户之间的相似度的Service
- com.cslg.graduation.service.impl.UserSimilarityServiceImpl: 用户与用户之间的相似度的Service具体实现类
- com.cslg.graduation.util.RecommmentUtils: 推荐模块工具类

## 待完成的功能
- 分页模块
- 新用户的推荐问题
- 用户与用户的相似度
    - 监听器，每天定时计算相似度
- 用户与博客的点击量处理
## 存在的问题及解决
- 工具类中计算相似度，存入数据库库中为1
  - 解决：设置小数点后显示10位
- 自定义mapper方法时无法查询数据库或查询出来的值为null
  - 解决：为查询出来的列定义别名，和需要返回的DTO名称一致
