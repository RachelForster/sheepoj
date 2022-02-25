<template>

  <el-container style="height: max-content">

    <el-aside>
      <el-card>
        <el-row>
          <el-button type="primary" icon="el-icon-success" round @click="codeFormVisible=true;"> 提交</el-button>
          <el-button round icon="el-icon-star-off">收藏</el-button>
        </el-row>
        <p>通过人数：{{problem.passNum}}</p>
        <p>标签:
        </p>
        <button_group style="line-height: 2;text-align: left">
          <el-button round  size="small" v-for="label in labels" v-text="label" @click="clickLabel(label)" >
          </el-button>
        </button_group>

      </el-card>
        <el-card style="" class="box-card">
          <div slot="header" class="clearfix" >
            <span>本题状态</span>
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
      <el-main style="height: max-content;margin: 20px">
        <el-card>
          <h1>{{problem.name}}</h1>
          <div style="font-size:15px;line-height: 2;color: #646464">
            <br>时间限制：{{problem.timeLimit}}
            <br>内存限制：{{problem.memoryLimit}}
          </div>

          <div v-html="problem.description" style="text-align: left;line-height: 2;color: grey">
          </div>
        </el-card>

        <el-dialog title="提交代码" :visible.sync="codeFormVisible">
          <el-form :model="codeForm" style="text-align: left">

            <el-form-item label="语言">
              <el-select v-model="codeForm.language" placeholder="请选择语言">
                <el-option label="C++" value="C++"></el-option>
                <el-option label="JAVA" value="JAVA"></el-option>
              </el-select>
            </el-form-item >
            <el-form-item label="代码">
              <el-input style="height: 450px"   :autosize="{ minRows: 23, maxRows: 23}" type="textarea" v-model="codeForm.code" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="codeFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="codeFormVisible = false">确 定</el-button>
          </div>
        </el-dialog>
      </el-main>


  </el-container>

</template>

<script>
import axios from "axios";
import TheLatex2Math from "./gs";
import ProblemSet from "./ProblemSet";
import router from "../router";
export default {
  name: "Problem",
  comments:{
    TheLatex2Math
  },

  methods: {

    getProblem()
    {
      const that =this
      axios.get("http://localhost:8081/problem/"+that.$route.params.problem_id).then(function (response){
          that.problem=response.data;
          var s=""
          var strlist=[];
          strlist=that.problem.description.split("\n")
          for (var i=0;i<strlist.length;i++){
            if(strlist[i].length==0)
              continue;
            if(strlist[i]=="输入格式"||strlist[i]=="输出格式"||strlist[i]=="题目描述"||strlist[i]=="样例"||strlist[i]=="数据范围")
            {
              s+="<p><b>"+strlist[i]+"</b></p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

            }
            else s+=strlist[i];
          }
          that.problem.description=s
          console.log(that.problem)
        })
      axios.get("http://localhost:8081/labels",
        {
          params:{
            problem_id:that.$route.params.problem_id
          }
        }
      ).then(function (response){
        that.labels=response.data;
      })

    }
    ,
    clickLabel:function (label)
    {
      router.push("/problemset?tag="+label)
    }
  },
  data(){
    return{
      //题目数据
      problem:{
        name:"wsdh"
      },

      //标签
      labels:[],
      //提交代码提示框
      codeFormVisible:false,
      formLabelWidth: '400px',
      codeForm:{
        language:'',
        code:''
      }
    }
  }
  ,created() {

    const that=this;
    this.$watch(
      () => this.$route.params,
      (toParams, previousParams) => {
        // 对路由变化做出响应...
        this.getProblem()
      },
    )
    this.getProblem()


  }


}
</script>

<style scoped>

el-main{
  margin: 10px;
}
p{
  line-height: 2.0;
  color: grey;
}
</style>
