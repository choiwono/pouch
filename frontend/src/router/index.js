import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Account/Login'
import HelloWorld from "@/components/HelloWorld";
import Join from '@/components/Account/Join'

Vue.use(Router)

export default new Router({
  mode:'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/login/',
      name: '로그인',
      component: Login
    },
    {
      path: '/home/',
      name: '홈',
      component: HelloWorld
    },
    {
      path: '/join/',
      name: '회원가입',
      component: Join
    }
  ]
})
