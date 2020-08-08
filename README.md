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
- 介绍页面显示问题
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
- 放到云服务器上运行时配置项目路径名(不需要)
    - 解决：在application.yml中配置serve.sevelt.contet-path: /路径名
        - 前端页面的所有链接需要更改为/项目路径名/请求链接，后端请求不需要改动
- 服务器上的相关配置
    - java启动jar包：


       nohup java -jar demo-0.0.1-SNAPSHOT.jar --server.port=8000  > log.file  2>&1 &
       上面的2 和 1 的意思如下:
       0    标准输入（一般是键盘）
       1    标准输出（一般是显示屏，是用户终端控制台）
       2    标准错误（错误信息输出）
       将运行的jar 错误日志信息输出到log.file文件中，然后（>&1）就是继续输出到标准输出(前面加的&，是为了让系统识别是标准输出)，最后一个&,表示在后台运行。
     
 　　
    - 配置nginx
    
        在nginx中配置多个项目时，有两种方式：
        1.nginx按照不同的目录分发给不同的项目
        2.启用二级域名，不同的项目分配给不同的二级域名
        
        1.nginx按照不同的目录分发给不同的项目 实现：
        server {
        listen 80;
        server_name example.com;
        
        location ^~ /project1 {
        proxy_pass http://127.0.0.1:8081/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
        
        location ^~ /project2 {
        proxy_pass http://127.0.0.1:8082/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
        
        location / {
        proxy_pass http://127.0.0.1:8080/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
        }
        
        这里配置了三个项目：
        http://example.com/project1路径分发到http://localhost:8081
        http://example.com/project2路径分发到http://localhost:8082
        其他路径分发到http://localhost:8080
        
        2.启用二级域名，不同的项目分配不同的二级域名
        
        project1
        
        server {
        listen 80;
        server_name project1.example.com;
        location / {
        proxy_pass http://127.0.0.1:8081;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
        }
        
         
        
        project2
        
        server {
        listen 80;
        server_name project2.example.com;
        location / {
        proxy_pass http://127.0.0.1:8082;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
        }
        
         
        
        注意：这三个项目属于不同的域名，项目之间通过http访问会存在跨域问题。
        
     
 


## IDEA插件
- Leetcode editor
- Jrebel for Intellij
  - https://jrebel.qekang.com/
