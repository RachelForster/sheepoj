import Vue from 'vue'
import Router from 'vue-router'
import Navigation from "../components/Navigation";
import ProblemSet from "../components/ProblemSet";
import Login from "../components/Login"
import Discuss from "../components/Discuss";
import Contest from "../components/Contest";
import Problem from "../components/Problem";
import Submissions from "../components/Submissions";
import ContestDetail from "../components/ContestDetail";
import DiscussDetail from "../components/DiscussDetail";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
    },

    {
      path: '/',
      name: 'Navigation',
      component: Navigation,

      //子路由
      children:[
        {
          path: '/problemset',
          name: 'ProblemSet',
          component: ProblemSet,

        },
        {
          path: '/discuss',
          name: 'Discuss',
          component: Discuss,

        },
        {
          path: '/contests',
          name: 'Contest',
          component: Contest,

        },
        {
          path: '/problem/:problem_id',
          name: 'Problem',
          component: Problem,
        },
        {
          path: '/submissions',
          name: 'Submissions',
          component: Submissions,
        }
        ,{
          path: '/contest/:id',
          name: 'ContestDetail',
          component: ContestDetail,
        }
        ,{
          path: '/discuss/:id',
          name: 'DiscussDetail',
          component: DiscussDetail
        }

      ]
    }




  ]
})
