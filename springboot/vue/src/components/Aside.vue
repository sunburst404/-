<template>

    <el-menu :default-openeds="['1', '3']" style="min-height :100%; overflow-x: hidden; border-right-color:transparent "
             background-color="#5959AB"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :isCollapse="isCollapse"
             router
             @select="handleSelect"
    >
      <div style="height: 60px; line-height: 60px; text-align: center">
        <img src="../assets/logo.png" alt="" style="width: 18px; position:relative ; top: 5px; margin-right: 5px">
        <b style="color: white " v-show="logoTextShow">灾情管理系统</b>
      </div>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-position"></i><span slot="title">数据查看</span>
        </template>
        <el-menu-item-group>
          <template slot="title">表格查看</template>
          <el-menu-item index="/">表格显示</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="图表查看">
          <el-menu-item index="/mhsd">统计图查看</el-menu-item>
          <el-menu-item index="1-3">地图查看</el-menu-item>
        </el-menu-item-group>

      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-circle-plus-outline"></i>
          <span slot="title">新增</span></template>
        <el-menu-item-group>
          <template slot="title">数据导入</template>
          <el-menu-item index="2-1">单个信息导入</el-menu-item>
          <el-menu-item index="2-2">xml文件导入</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="数据导出">
          <el-menu-item index="2-3">导出为xml文件</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-user"></i>
          <span slot="title">个人空间</span></template>
          <el-menu-item index="3-1">个人信息管理</el-menu-item>
          <el-menu-item index="/user">用户信息管理</el-menu-item>

      </el-submenu>
    </el-menu>

</template>
<script>
  export default {
    name: "Aside",
    props:{
      isCollapse: Boolean,
      logoTextShow: Boolean,
    },
    methods:{
      handleSelect(index){

      }
    }
  }
 </script>
<style scoped>

</style>