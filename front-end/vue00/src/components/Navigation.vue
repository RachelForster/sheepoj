<template>
  <div>
    <el-container>
<!--      顶部导航-->
      <el-header style="background: white">
              <el-row gutter="24">
                <el-col span="5" >
                    <img style="max-height: 70px" src="../assets/SheepOJ.png"></img>

                </el-col>
                <el-col span="15">
                  <div id="rightnav">
                    <el-menu :default-active="activeIndex" text-color="light-blue"  router mode="horizontal" @select="handleSelect">
                      <el-menu-item index="/problemset">题库</el-menu-item>

                      <el-menu-item index="/contests" >竞赛</el-menu-item>
                      <el-menu-item index="/discuss">讨论</el-menu-item>
                      <el-submenu index="2">
                        <template slot="title">我的</template>
                        <el-menu-item index="submissions">历史提交</el-menu-item>
                        <el-menu-item index="2-2">选项2</el-menu-item>
                        <el-menu-item index="2-3">选项3</el-menu-item>
                        <el-submenu index="2-4">
                          <template slot="title">选项4</template>
                          <el-menu-item index="2-4-1">选项1</el-menu-item>
                          <el-menu-item index="2-4-2">选项2</el-menu-item>
                          <el-menu-item index="2-4-3">选项3</el-menu-item>
                        </el-submenu>
                      </el-submenu>

                    </el-menu>
                  </div>

                </el-col>
                <el-col span="4">
                  <div v-if="isLogin()"  >
                    <router-link to="/login" >
                      <el-button type="primary" round >登录/注册</el-button>
                    </router-link>
                  </div>
                  <div style="color: #837a7a" v-else>
                    欢迎，{{currentUserName}}
                  </div>

                </el-col>
              </el-row>

      </el-header>

<!--      主页面-->
      <el-main id="mainpage">
        <router-view></router-view>
      </el-main>


    </el-container>

  </div>


</template>
<style>
#recent_contest
{
  background-color: rgb(255, 208, 0);

  background-image:url("../assets/sheepwink.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  height: 230px;
}
.el-calendar-table .el-calendar-day{
  height: 50px;
  background-color: #f9eded;
}
</style>
<style scoped>


#mainpage
{
  padding-left: 150px;
  padding-right: 150px;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}


.el-header{
  text-align: center;
  line-height: 60px;

}
.el-footer {
  background-color: #e5e5e5;
  color: #646464;
  height: 400px;

}
.el-aside {

  text-align: center;
  height: 1000px;
}


.el-container{
  height: 2000px;
}
/*.el-container:nth-child(5) .el-aside,*/
/*.el-container:nth-child(6) .el-aside {*/
/*  line-height: 260px;*/
/*}*/

/*.el-container:nth-child(7) .el-aside {*/
/*  line-height: 320px;*/
/*}*/
</style>
<script>
import axios from "axios";
import router from "../router";

export default {
  name: "Navigation",
  data() {
    return {
      date:new Date(),
      activeIndex: '1',
      activeIndex2: '1',
      currentUserName :''
    };
  },
  methods:{
    isLogin:function ()
    {
      const that=this;
      let token=localStorage.getItem("token");
      console.log(token)
      console.log(!token)
      if(!token)
        return true;
      else {
        axios.get("http://localhost:8081/user").then(
          function (response)
          {
            console.log(response)
            that.currentUserName=response.data;
          }
        )
        return false;
      }

    }
},
  created() {
    router.push({path:"/problemset"})
  }
}
</script>
