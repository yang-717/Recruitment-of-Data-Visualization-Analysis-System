# 打包项目到 GitHub 的完整步骤流程

## 1. 安装 Git
确保本地已安装 Git。可在终端输入 `git --version` 检查。

## 2. 初始化本地仓库
在项目根目录下打开终端，执行：
```bash
git init
```

## 3. 添加所有文件到暂存区
```bash
git add .
```

## 4. 提交到本地仓库
```bash
git commit -m "初始化项目"
```

## 5. 在 GitHub 创建远程仓库
- 登录 GitHub
- 点击右上角 "+"，选择 "New repository"
- 填写仓库名称等信息，点击 "Create repository"

## 6. 关联远程仓库
复制 GitHub 仓库地址（如 `https://github.com/用户名/仓库名.git`），在终端执行：
```bash
git remote add origin https://github.com/用户名/仓库名.git
```

## 7. 推送到 GitHub
首次推送：
```bash
git branch -M main
git push -u origin main
```

## 8. 后续更新
每次修改后，重复以下命令：
```bash
git add .
git commit -m "描述本次修改"
git push
```

## 9. 检查结果
在 GitHub 仓库页面确认文件已上传。

---
如遇问题可参考 GitHub 官方文档或使用 `git status`、`git log` 等命令排查。

