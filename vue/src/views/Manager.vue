<template>
  <div class="manager-container">
    <!--  头部  -->
    <div class="manager-header">
      <div class="manager-header-left">
        <img src="@/assets/imgs/logo.png" />
        <div class="title">招聘数据分析</div>
      </div>

      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }">{{ $route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>



<!--      头部相关设置-->
      <div class="manager-header-right">
        <div style="display: flex; align-items: center;">
          <el-button round style="margin-right: 10px;"   @click="$router.push('/biScreen')"
          >可视化大屏
          </el-button>
          <el-dropdown placement="bottom">
            <div class="avatar">
             <div>
<!--               <el-avatar :src="'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'"></el-avatar>-->
             </div>
              <img :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
              <div>{{ user.name || '管理员' }}</div>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item>
              <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>

    <!--  主体  -->
    <div class="manager-main">
      <!--  侧边栏  -->
      <div class="manager-main-left">
        <el-menu :default-openeds="['info', 'user', 'Data','list']" router style="border: none" :default-active="$route.path">
          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>

          <el-submenu index="info">
            <template slot="title">
              <i class="el-icon-menu"></i><span>信息管理</span>
            </template>
            <el-menu-item index="/notice">公告信息</el-menu-item>
          </el-submenu>

          <el-submenu index="list">
            <template slot="title">
              <i class="el-icon-menu"></i><span>数据列表</span>
            </template>
            <el-menu-item index="/jobInfo">职位列表</el-menu-item>
          </el-submenu>

          <el-submenu index="Data">
            <template slot="title">
              <i class="el-icon-menu"></i><span>数据可视化</span>
            </template>
            <el-menu-item index="/salaryAnalysis">薪资分析</el-menu-item>
            <el-menu-item index="/experienceAnalysis">经历分析</el-menu-item>
            <el-menu-item index="/industryAnalysis">行业分析</el-menu-item>
            <el-menu-item index="/cityAnalysis">城市分析</el-menu-item>
          </el-submenu>



          <el-submenu index="user">
            <template slot="title">
              <i class="el-icon-menu"></i><span>用户管理</span>
            </template>
            <el-menu-item index="/admin">管理员信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!--  数据表格  -->
      <div class="manager-main-right">
        <router-view @update:user="updateUser" />
      </div>
    </div>

  </div>
</template>

<script>

export default {
  name: "Manager",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    if (!this.user.id) {
      this.$router.push('/login')
    }
  },
  methods: {
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{ }')   // 重新获取下用户的最新信息
    },
    goToPerson() {
      if (this.user.role === 'ADMIN') {
        this.$router.push('/adminPerson')
      }
    },
    logout() {
      localStorage.removeItem('xm-user')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/manager.css";
</style>