<template>
    <el-container style="height: 100%; border: 1px solid #eee; min-height: 100vh">
<!--      导航栏-->
      <el-aside :width="sideWidth +'px'" style="background-color: rgb(238,242,246);box-shadow:2px 0 6px rgb(0,21,41) ">
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
              <el-menu-item index="/map">地图查看</el-menu-item>
            </el-menu-item-group>

          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-circle-plus-outline"></i>
              <span slot="title">新增</span></template>
            <el-menu-item-group>
              <template slot="title">数据导入</template>
              <el-menu-item index="/addone">单个信息导入</el-menu-item>
              <el-menu-item index="/addxml">xml文件导入</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="数据导出">
              <el-menu-item index="2-3">导出为xml文件</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-user"></i>
              <span slot="title">个人空间</span></template>
            <el-menu-item index="/person">个人信息管理</el-menu-item>
            <el-menu-item index="/user">用户信息管理</el-menu-item>

          </el-submenu>
        </el-menu>
      </el-aside>


      <el-container>
<!-- header-->
        <el-header style=" border-bottom: 1px solid #cccccc; ">
          <div style=" font-size: 12px;line-height: 60px;display: flex;">
            <div style="flex:1; font-size: 18px">
              <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
            </div>

            <el-dropdown style="width: 100px; cursor: pointer;">
              <span>王小虎</span>
              <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item>退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>
        <!--主体-->

        <el-main>
<!--    搜索栏      -->
<!--          当前页面子路由在router-view下展示-->
        <router-view />
        </el-main>
      </el-container>
    </el-container>

</template>

<script>
// @ is an alias to /src
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";

export default {
  name: 'HomeView',

  data(){

    return{
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse:false,

      sideWidth:200,
      logoTextShow:true,
      headerBg:'headerBg'

    }
  },
  created() {
    //请求分页查询
   this.load()
  },
  components: {
    Aside,
    Header
  },


  methods:{
    collapse(){
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){
        this.sideWidth=64
        this.collapseBtnClass='el-icon-s-unfold'
        this.logoTextShow=false
      }
      else {
        this.sideWidth=200
        this.collapseBtnClass='el-icon-s-fold'
        this.logoTextShow=true
      }
    },

  }

}
</script>
<style>
  .headerBg{
    background: #eee!important;
  }
</style>