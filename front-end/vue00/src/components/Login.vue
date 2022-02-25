
<template>
  <el-container >
    <el-header style="background-color: #fffefd;text-align: left">
      <img style="max-height: 70px" src="../assets/SheepOJ.png"></img>

    </el-header>
    <el-main>

      <el-row>
        <el-col span="10">
          <img src="../assets/baonini.png">
        </el-col>

        <el-col span="10">
          <el-tabs type="border-card" style="height: 600px"  stretch>
            <el-tab-pane  label="登录" >

              <div  style="line-height: 500px; vertical-align: middle">
                <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-width="100px" class="demo-ruleForm">
                  <el-form-item label="用户名" prop="name">
                    <el-input v-model="loginForm.name"></el-input>
                  </el-form-item>
                  <el-form-item label="密码" prop="password">
                    <el-input v-model="loginForm.password" show-password></el-input>
                  </el-form-item>
                  <el-form-item style="text-align: left;">
                        <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
                  </el-form-item>
                </el-form>

              </div>
            </el-tab-pane>
            <el-tab-pane label="注册">
              <el-form :model="registerForm" status-icon :rules="registerRules" ref="registerForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="用户名" prop="name">
                  <el-input v-model="registerForm.name"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                  <el-input type="password" v-model="registerForm.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                  <el-input type="password" v-model="registerForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="submitForm('registerForm')">提交</el-button>
                  <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>

          </el-tabs>
        </el-col>
      </el-row>

    </el-main>
  </el-container>



</template>
<script>
import axios from "axios";

export default {
  name:"Login",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.registerForm.checkPass !== '') {
          this.$refs.registerForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {


      loginForm: {
        name: '',
        password: ''
      },
      registerForm: {
        name: '',
        password: '',
        checkPass: ''
      },
      loginRules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur' }

        ],
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' },

        ]
      },
      registerRules:{
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: true, message: '请再次输入密码', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const that=this;
      this.$refs[formName].validate((valid) => {
        if (valid) {

          if(formName=="registerForm")
          {
            try {
              axios.get("http://localhost:8081/register",
                {
                  params:{
                    userName:that.registerForm.name,
                    password:that.registerForm.password
                  }
                }).then(function (response){
                  alert(response.data.msg)
              })
            }catch (e){

            }
          }
          //登录
          else
          {
            axios.post("http://localhost:8081/login",
              {
                },
              {
                params:{
                  username:that.loginForm.name,
                  password:that.loginForm.password
                }

              }).then(
                function (response){

                  if(response.code==0)
                  {
                    window.localStorage.setItem("token",response.data);
                    that.$router.push({path:"/problemset"})
                  }
                }
            )
          }
        } else {
          alert('用户名密码不合法');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style  scoped>
::v-deep .el-tabs__nav-scroll{
  width: 50%!important;
  margin: 0 auto!important;
}
</style>
