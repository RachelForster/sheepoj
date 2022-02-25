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
    <el-main>
      <div>
        <el-table
          :data="pageInfo.list"
          stripe
          style="width: 100%"
          :default-sort = "{prop: 'problem_id', order: 'ascending'}"
        >
          <el-table-column
            prop="problemId"
            label="题号"
            sortable
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
            @click="clickNameColumn"
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
        <div class="block" style="text-align: left">

          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-sizes="[20,50,100]"
            :page-size.sync="pageInfo.pageSize"
            layout="sizes, prev, pager, next"
            :total.sync="pageInfo.total">
          </el-pagination>
        </div>

      </div>

    </el-main>
  </el-container>


</template>

<script>
import axios from "axios";

export default {
  name: "ProblemSet",
  data() {
    return {
      pageInfo: {

      }
      ,
      currentPage:1,
      word:null,
      tag:null,
    }
  },
  methods: {

    searchEnter:function (e)
    {
      var keyCode = e.keyCode;
      if (keyCode == 13) {
        this.getByPage(1,20)
      }
    },
    getByPage:function (page,limit){
      const that=this
      axios.get("http://localhost:8081/problemset",{
        params:{
          page:page,
          limit:limit,
          word:that.word,
          tag:that.tag
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
    clickNameColumn:function ()
    {

    },
  },
  created() {

    if(this.$route.query.tag)
      this.tag=this.$route.query.tag

    this.$watch(
      () => this.$route.query,
      (toQuery, previousQuery) => {
        // 对路由变化做出响应...
        this.getByPage(toQuery.page,toQuery.limit)
      }
    )
    this.getByPage(1,20)

  }
}
</script>

<style scoped>

</style>
