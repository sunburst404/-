<script>
import user from "@/views/User.vue";

export default {
  name: "Login",
  data(){
    return{
      user:{}
    }
  },
  methods:{
    login(){

    }
  }

}
</script>

<template>
<div class="wrapper">
  <div style="margin: 200px auto; background-color: #ffffff;
  width: 350px;height: 300px;padding: 20px;border-radius: 10px ">
    <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>SIGN UP</b></div>
    <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user"
               v-model="user.username" autocomplete="off"></el-input>
    <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user"
              show-password v-model="user.password" autocomplete="off"></el-input>
    <div style="margin: 10px 0;text-align: right">
      <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
      <el-button type="warning" size="small" autocomplete="off">注册</el-button>
    </div>

  </div>

</div>
</template>

<style scoped>
  .wrapper{
    height: 100vh;
    background-image: linear-gradient(to bottom right,#FC466B, #3F5EF8) ;
    overflow: hidden;
  }
</style>