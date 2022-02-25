<template>
  <el-container style="height: max-content">
    <el-aside>
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
    </el-aside>
    <el-main>
      <div>
        <el-table
          :data="pageInfo.list"
          stripe
          style="width: 100%"
          :default-sort = "{prop: 'contest_id', order: 'ascending'}"
        >
          <el-table-column
            prop="status"
            label="状态"
            width="100"
          >
          </el-table-column>

          <el-table-column
              prop="contestId"
              label="竞赛编号"
              width="100"
            >
          </el-table-column>
          <el-table-column
            prop="name"
            label="竞赛名称"
            width="200">
            <template slot-scope="scope">
              <el-link   @click="$router.push({path:'/contest/'+scope.row.contestId})">{{scope.row.name}}</el-link>
            </template>
          </el-table-column>

          <el-table-column
            prop="participation"
            label="参加人数"
            width="100"
            sortable
          >
          </el-table-column>
          <el-table-column
            prop="start"
            label="开始时间"
            width="150"
            sortable
          >

          </el-table-column>

          <el-table-column
            prop="length"
            label="时长"
            width="100"
            sortable
          >
          </el-table-column>


          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope" >
              <el-row>
                <el-input
                  v-model="search"
                  size="mini"
                  placeholder="输入关键字搜索"
                >
                  <el-button slot="append" icon="el-icon-search"></el-button>

                </el-input>


              </el-row>

            </template>
            <template slot-scope="scope">
              <el-link type="primary">
                虚拟参加
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
            :page-size="20"
            layout="sizes, prev, pager, next"
            :total="pageInfo.total">
          </el-pagination>
        </div>

      </div>

    </el-main>
  </el-container>
</template>

<script>
import axios from "axios";

export default {
  name: "Contest",
  data(){
    return {
      pageInfo:{},
      currentPage:1,


    }
  }
  ,
  methods:{
    getByPage:function (page,limit){
      const that=this
      axios.get("http://localhost:8081/contests",{
        params:{
          page:page,
          limit:limit,

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
    }
  },
  created() {
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
