<template>
  <div id="app">
    <Menu></Menu>

    <el-row>
    <!-- 左侧导航 -->
      <el-col :span="4"><left-menu></left-menu></el-col>
      <!-- 右侧主内容区 -->
      <el-col :span="20"><router-view class="view"></router-view></el-col>
    </el-row>


  </div>
</template>

<script>
  import Menu from './components/Menu'
  import LeftMenu from './components/LeftMenu'

  export default {
    components: {
      Menu,
      LeftMenu
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
