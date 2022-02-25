<template>
  <el-container>

    <el-aside>
      <el-card>
        <el-row>
          <el-button type="primary" icon="el-icon-success" round @click="codeFormVisible=true;"> 点赞</el-button>
          <el-button round icon="el-icon-star-off">收藏</el-button>
        </el-row>
      </el-card>
      <el-card style="" class="box-card">
        <div slot="header" class="clearfix" >
          <span>本题状态</span>
        </div>
        <div id="recent_contest">
            最热讨论
        </div>
      </el-card>
      <el-card style="" class="box-card">
        <div slot="header" class="clearfix" >
          <span>历史提交</span>
        </div>
        <div>

        </div>
      </el-card>

    </el-aside>
    <el-main style="height: max-content">
      <el-card>
        <h1>{{discuss.title}}</h1>
        <div style="text-align: left;line-height: 2;color: #646464">
          {{discuss.content}}
        </div>

        <div>
          <el-row></el-row>
        </div>
      </el-card>

    </el-main>

  </el-container>

</template>

<script>

import axios from "axios";

export default {
  name: "DiscussDetail",
  components: {},
  data(){
    return{
      discuss:{},
      comments:[]
    }
  },
  methods:{

    getDiscuss()
    {
      const that =this
      axios.get("http://localhost:8081/discuss/"+that.$route.params.id).then(function (response){
        that.discuss=response.data;
      })
    }
    ,
  }
  ,created() {
    this.$watch(
      () => this.$route.params,
      (toParams, previousParams) => {
        // 对路由变化做出响应...
        this.getDiscuss()
      },
    )
    this.getDiscuss();

  }
}
</script>

<style scoped>

</style>
