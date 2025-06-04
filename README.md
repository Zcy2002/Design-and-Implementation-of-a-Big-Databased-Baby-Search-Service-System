宝贝寻亲服务系统（Baby Search Service System）

[构建状态] Build Passing

[语言] Java 1.8+, Spring Boot 2.7+, Hadoop 3.x, Hive 2.x, MySQL 5.7

项目简介

近年来，随着社会流动性增强与人口规模扩大，儿童失踪、拐卖等问题频发，传统寻亲手段信息分散、效率低下，难以满足现实需求。本系统综合运用 Hadoop、Hive、Spring Boot 等主流技术，搭建起一个数据驱动的智能寻亲服务平台。

系统主要涵盖三类用户角色：
- 寻亲者：填写亲人信息，发起寻亲请求；
- 志愿者：查看寻亲信息，参与线索收集与协助；
- 管理员：全面管理用户、寻亲数据、系统配置等。

项目功能
1. 用户功能
- 寻亲信息发布
- 可视化大屏查看
- 信息管理与编辑
- 紧急寻亲模块
- 感谢区留言与致谢

2. 志愿者功能
- 志愿者登录/注册
- 寻亲信息查看与筛选
- 线索提交与管理

3. 管理员功能
- 用户、志愿者管理
- 寻亲信息的增删查改
- 平台数据监控与后台管理

技术架构

前端 HTML + CSS + JavaScript + ECharts

后端 Spring Boot

数据库 MySQL

大数据组件 Hadoop、Hive（用于存储与分析大规模寻亲数据）

可视化 ECharts 实现数据展示大屏

开发工具IDEA、Navicat、Hadoop环境

项目结构

![image](https://github.com/user-attachments/assets/2e1ecfc2-f7f6-4a8f-882a-d1005b371824)

快速启动
环境依赖：
- JDK 1.8+
- Hadoop
- Hive
- MySQL 5.7+

启动步骤：

1. 配置 MySQL 数据库并导入初始数据表；
2. 配置 Hadoop、Hive 环境，运行 data/ 目录下的 Hive 脚本导入样例数据；
3. 启动后端服务：
4. 启动前端页面，使用浏览器访问首页（如为静态页面可直接双击打开）。

致谢
特别感谢在寻亲过程中贡献线索的每一位热心人士。

