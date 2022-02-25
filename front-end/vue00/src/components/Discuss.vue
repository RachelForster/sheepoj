<template>

  <el-container style="height: max-content;">
    <el-aside>
      <el-card style="" class="box-card">
        <div slot="header" class="clearfix" >
          <span>近期竞赛</span>
          <el-button style="float: right; padding: 3px 0" type="text">报名</el-button>
        </div>
        <div id="recent_contest">

        </div>
      </el-card>
      <el-calendar  v-model="date">
      </el-calendar>

    </el-aside>
    <el-main >
      <el-container>
        <el-header>
          <el-row style="font-size: 18px">
            <el-col span="3" style="text-align: left">
              <span style="color: indianred" class="iconfont icon-remen"></span>
              <el-link @click="order='discuss_num';getByPage(1,5)"> 最热 </el-link>

            </el-col>
            <el-col span="9" style="text-align: left">
              <el-link @click="order='date';getByPage(1,5)"> 最新 </el-link>

            </el-col>

            <el-col style="text-align: right" span="8">
              <el-input
                v-model="search"

                placeholder="输入关键字搜索"
              >
                <el-button slot="append" icon="el-icon-search"></el-button>

              </el-input>
            </el-col>
            <el-col span="4">
              <el-button @click="discussFormVisible=true" type="primary" icon="el-icon-s-promotion">
                发起讨论
              </el-button>
            </el-col>
          </el-row>
        </el-header>
        <div class="block" style="text-align: left">

          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-sizes="[5,10,20]"
            :page-size.sync="pageInfo.pageSize"
            layout="sizes, prev, pager, next"
            :total.sync="pageInfo.total">
          </el-pagination>
        </div>
        <el-main>
          <el-card  style="margin: 10px" v-for="item in pageInfo.list" class="box-card">
            <div @click="$router.push({path:'/discuss/'+item.discussId})">
              <div  style="text-align: left" slot="header" class="clearfix" >
                <span style="color: lightskyblue">{{item.publisherId}}：</span><span>{{item.title}}</span>
              </div>
              <div style="line-height:2.0;color: darkgray; margin: 10px; text-align: left">
                {{item.content}}
              </div>
            </div>

            <el-row>
              <el-col style="text-align: left;color: indianred;" span="3">
                <i class="iconfont" @click="putLike(item,$event);"
                   v-bind:class="{'icon-aixin':!item.isLiked,'icon-aixin1':item.isLiked}">&nbsp;{{item.likeNum}}</i>
              </el-col>
              <el-col style="text-align: left;color: #797070;" span="2">
                <i class="iconfont icon-pinglun1">&nbsp;{{item.discussNum}}</i>
              </el-col>
              <el-col span="19" style=" color: #646464; font-size:12px;text-align: right;">
                <span>{{item.date}}</span>
              </el-col>
            </el-row>
          </el-card>
        </el-main>
      </el-container>
      <el-dialog title="发起讨论" :visible.sync="discussFormVisible">
        <el-form :model="discussForm" style="text-align: left">

          <el-form-item label="标题">
            <el-input v-model="discussForm.title" aria-placeholder="请输入标题"></el-input>
          </el-form-item>
          <el-form-item label="内容">
            <el-input style="height: 450px"   :autosize="{ minRows: 23, maxRows: 23}" type="textarea" v-model="discussForm.content" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="discussFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="publishDiscuss();discussFormVisible= false">确 定</el-button>
        </div>
      </el-dialog>

    </el-main>
  </el-container>
</template>

<script>
import axios from "axios";

export default {
  name: "Discuss",
  data(){
    return{
      discussFormVisible:false,
      discussForm:{
        title:"",
        content:""
      },
      pageInfo:{},
      currentPage:1,
      word:null,
      order:null,
    }
  },
  methods:{
    searchEnter:function (e)
    {
      var keyCode = e.keyCode;
      if (keyCode == 13) {
        this.getByPage(1,20)
      }
    },
    publishDiscuss:function (){
      const that = this;
      axios.post("http://localhost:8081/discuss/publish",{},
        {
          params:{
            title:that.discussForm.title,
            content:that.discussForm.content
          }
        }).then(function (response)
      {
        alert(reponse.msg);
      })
    },
    putLike:function (item,e)
    {
      if(item.isLiked) {
        item.likeNum += 1;
        return;
      }
      //alert(item.discussId)
      const that=this;
      axios.get("http://localhost:8081/discuss/"+item.discussId+"/likes").then(function (response){
        item.isLiked=true;
        item.likeNum+=1;
      });
    }
    ,
    getByPage:function (page,limit){
      const that=this
      axios.get("http://localhost:8081/discuss",{
        params:{
          page:page,
          limit:limit,
          word:that.word,
          order:that.order
        }
      }).then(
        function (response)
        {
          that.pageInfo=response.data
          that.currentPage=page
        }
      )
    },
    handleSizeChange:function (limit){
      this.getByPage(this.currentPage,limit)
    },
    handleCurrentChange:function (page){
      this.getByPage(page,this.pageInfo.pageSize)
    },
  },
  created() {
    this.$watch(
      () => this.$route.query,
      (toQuery, previousQuery) => {
        // 对路由变化做出响应...

        this.getByPage(toQuery.page,toQuery.limit)
      }
    )
    this.getByPage(1,5)
  }
}
</script>

<style scoped>
.iconfont{
  font-size: 20px;
}
</style>
