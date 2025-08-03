# 就业数据分析管理系统

## 项目简介
本项目是一个基于Spring Boot和Vue.js开发的就业数据分析与管理系统，旨在帮助用户分析和管理就业相关数据。系统包括数据可视化展示、城市分析、行业分析、薪资分析和经验分析等功能，为就业决策提供数据支持。

## 技术栈

### 后端
- Spring Boot 2.5.9
- MyBatis
- MySQL
- Java 1.8

### 前端
- Vue.js 2.6
- Element UI
- ECharts 5.6
- Data-View 2.10
- Axios

## 功能模块
- 用户管理
- 数据大屏展示
- 城市分析
- 行业分析
- 薪资分析
- 经验分析
- 职位信息
- 通知公告

## 系统架构
系统采用前后端分离架构：
- 前端：Vue.js + Element UI + ECharts
- 后端：Spring Boot + MyBatis
- 数据库：MySQL

## 项目结构
```
manager/
├── springboot/            # 后端项目
│   ├── src/               # 源代码
│   │   ├── main/java/     # Java代码
│   │   └── resources/     # 配置文件
│   └── pom.xml            # Maven配置文件
│
├── vue/                   # 前端项目
│   ├── public/            # 静态资源
│   ├── src/               # 源代码
│   │   ├── assets/        # 资源文件
│   │   ├── components/    # 组件
│   │   ├── router/        # 路由配置
│   │   ├── utils/         # 工具类
│   │   └── views/         # 页面
│   └── package.json       # npm配置文件
│
└── files/                 # 上传文件存储目录
```

## 安装与运行

### 后端
1. 克隆项目到本地
2. 导入数据库脚本（位于项目根目录下的sql文件夹）
3. 修改`springboot/src/main/resources/application.yml`中的数据库配置
4. 进入springboot目录，执行以下命令：
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
5. 后端服务默认运行在 http://localhost:9090

### 前端
1. 进入vue目录，执行以下命令：
   ```bash
   npm install
   npm run serve
   ```
2. 前端服务默认运行在 http://localhost:8080

## 注意事项
- 需要JDK 1.8或以上版本
- 需要Node.js 12.0或以上版本
- 需要MySQL 5.7或以上版本

## 贡献指南
1. Fork本仓库
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个Pull Request

## 开源协议
MIT
