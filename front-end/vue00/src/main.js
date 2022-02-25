// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import './assets/css/iconfont.css'
import MathJax from './MathJax.js'
Vue.prototype.MathJax = MathJax

import axios from 'axios'
import ro from "element-ui/src/locale/lang/ro";
import {parseTemplateShortcut} from "autoprefixer/lib/hacks/grid-shorthand";
import ca from "element-ui/src/locale/lang/ca";
import breadcrumb from "element-ui/packages/breadcrumb";
Vue.config.productionTip = false
Vue.use(ElementUI)
/* eslint-disable no-new */

//请求拦截器，每个请求都加上认证头token
axios.interceptors.request.use((config) => {
  // 在发送请求之前做些什么
  // config中就是我们对应的配置选项
  // 获取当前的token
  const token = localStorage.getItem('token')
  // 获取到token后验证是否有效，如果有效才设置对应的头部
  config.headers.Authentication = token
  return config
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});

axios.interceptors.response.use(res => {
  // 请求成功对响应数据做处理

  // 该返回的数据则是axios.then(res)中接收的数据
  switch(res.data.code){
    case 0:break;
    case 110:{
      window.localStorage.removeItem("token");
      window.location.href="/login";
      break;
    }
    case 1:{
      alert(res.data.msg)
      break;
    }
    case 111:
    {
      window.localStorage.removeItem("token");
      window.location.href="/login";
      break;
    };
    case 10009:
      alert("权限不足!");
      break;
    default:break;
  }
  return res.data;
}, err => {
  // 在请求错误时要做的事儿

  // 该返回的数据则是axios.catch(err)中接收的数据
  return Promise.reject(err)
})
//用户认证前端代码
//如果已经有token了，就验证token的合法性
//如果没有token或者token失效，就直接跳转登录
router.beforeEach(((to, from, next) => {
  let token=window.localStorage.getItem("token")
  let whiteList=['/','/login','/problemset']
  //如果直接跳转到登录页面，就让它跳转,必须要先判断路径是不是到/login,如果不判断的话，就会导致递归栈溢出
  if(to.path.startsWith("/login"))
  {
    next();
  }
  else
  {
    //如果没有token，就跳转到登录页面
    if(!token)
    {
      router.push({path:'/login'})
    }
    //有token，验证合法性,由于设置了
    else
    {
      next();
    }
  }

}))
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
