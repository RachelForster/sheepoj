<template>

  <el-container>

    <el-aside>
      <el-card>
        <h1>{{contest.name}}</h1>
        <el-row>
          <el-button type="primary" icon="el-icon-success" round> 虚拟参加</el-button>
        </el-row>
        <p>参加人数：{{contest.participation}}</p>
        <p>通过题数：</p>
      </el-card>
      <el-card style="" class="box-card">
        <div slot="header" class="clearfix" >
          <span>近期竞赛</span>
          <el-button style="float: right; padding: 3px 0" type="text">报名</el-button>
        </div>
        <div id="recent_contest">

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
    <el-main>
         <el-tabs v-model="activeName" @tab-click="handleClick">
           <el-tab-pane label="题目" name="first">
             <el-table
               :data="problemList"
               stripe
               style="width: 100%"
               :default-sort = "{prop: 'problem_id', order: 'ascending'}"
             >
               <el-table-column
                 prop="problemId"
                 label="题号"
                 width="100">
               </el-table-column>
               <el-table-column
                 prop="status"
                 label="状态"
                 width="100">
                 <template slot-scope="scope">
             <span v-if="scope.row.status==1">
               <div style=" font-size:20px;color: #70e770"><i class="el-icon-check"></i></div>
             </span>
                   <span v-if="scope.row.status==0">
                 <div>--</div>
             </span>
                 </template>
               </el-table-column>
               <el-table-column
                 prop="name"
                 label="题目名称"
                 width="200">
                 <template slot-scope="scope">
                   <el-link   @click="$router.push({path:'/problem/'+scope.row.problemId})">{{scope.row.name}}</el-link>
                 </template>
               </el-table-column>

               <el-table-column
                 prop="passNum"
                 label="通过人数"
                 width="100"
                 sortable
               >
               </el-table-column>

               <el-table-column
                 prop="level"
                 label="难度"
                 sortable
               >

               </el-table-column>

               <el-table-column
                 align="right">
                 <template slot="header" slot-scope="scope" >
                   <el-row>
                     <el-input
                       v-model="word"
                       size="mini"
                       placeholder="输入关键字搜索"
                       @keyup.enter.native="searchEnter($event)">


                       <el-button @click="getByPage(1,20)" slot="append" icon="el-icon-search"></el-button>

                     </el-input>


                   </el-row>

                 </template>
                 <template slot-scope="scope">
                   <el-link type="primary">
                     查看提交记录
                   </el-link>
                 </template>
               </el-table-column>


             </el-table>
           </el-tab-pane>
           <el-tab-pane label="提交记录" name="second">

           </el-tab-pane>
           <el-tab-pane label="排名" name="third">

           </el-tab-pane>
         </el-tabs>
    </el-main>

  </el-container>

</template>
<script>

import axios from "axios";
export default {
  name: "ContestDetail",
  data(){
    return{
      activeName:"first",
      contest:{},
      problemList:[]
    }
  },
  methods:{
    getContestById:function()
    {
      const that=this;
      axios.get("http://localhost:8081/contest/"+this.$route.params.id,{}).then(
        function (response){
          that.contest=response.data
        }
      )
    },
    getProblemList:function ()
    {
      const that=this;
      axios.get("http://localhost:8081/contest/"+this.$route.params.id+"/problemlist",{}).then(
        function (response){
          that.problemList=response.data
        }
      )
    }
  },
  created() {
    this.getProblemList()
    this.getContestById()
  }
}
</script>

<style scoped>

</style>
