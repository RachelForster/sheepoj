<template>
<el-container>
  <el-aside>
    <el-aside>
      <el-card>
        <el-row>
          <el-button type="primary" icon="el-icon-success" round @click="open()"> 提交</el-button>
          <el-button round icon="el-icon-star-off">收藏</el-button>
        </el-row>
        <p>通过人数：</p>
        <p>标签：</p>
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
  </el-aside>
  <el-main>
    <div>
      <el-table
        :data="contests"
        stripe
        style="width: 100%"
        :default-sort = "{prop: 'status', order: 'ascending'}"
      >
        <el-table-column
          prop="status"
          label="状态"
          width="100"
        >
        </el-table-column>
        <el-table-column
          prop="name"
          label="竞赛名称"
          width="200">
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
          :current-page.sync="currentPage2"
          :page-sizes="[20,50,100]"
          :page-size="20"
          layout="sizes, prev, pager, next"
          :total="1000">
        </el-pagination>
      </div>

    </div>
  </el-main>
</el-container>
</template>

<script>
export default {
  name: "Submissions"
}
</script>

<style scoped>

</style>
