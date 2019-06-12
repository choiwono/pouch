import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Account/Login'
import Home from "@/components/Home";
import Join from '@/components/Account/Join'
import FindPswd from '@/components/Account/FindPswd'
import Search from '@/components/Contents/Search'
import Messages from '@/components/Contents/Messages'

import Categories from "../components/Contents/Categories";
import axios from 'axios';

Vue.use(Router)

const router = new Router({
  mode:'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'main',
      component: Home,
      meta: { requiresAuth: true }
    },
    {
      path: '/login/',
      name: 'login',
      component: Login
    },
    {
      path: '/home/',
      name: 'home',
      component: Home,
      meta: { requiresAuth: true }
    },
    {
      path: '/join/',
      name: 'join',
      component: Join
    },
    {
      path: '/findpswd/',
      name: 'findpswd',
      component: FindPswd
    },
    {
      path: '/categories/search/',
      name: 'search',
      component: Search,
      meta: { requiresAuth: true }
    },
    {
      path: '/categories/:id',
      name: 'categories',
      component: Categories,
      meta: { requiresAuth: true },
      children: [
        {
          path:'tag/:tagId',
          name:'categoriesByTag',
          component: Categories
        }
      ]
    },
    {
      path: '/messages/',
      name: 'messages',
      component: Messages,
      meta: { requiresAuth: true }
    },
  ]
});

router.beforeEach((to,from,next) => {
  const authUser = localStorage.getItem('pouch_user');

  if (to.meta.requiresAuth) {
    if(authUser != null){
      axios.post("/accounts/auth").then((result)=> {
        if (result.roles.length === 0) {
          alert('로그인이 필요한 페이지입니다.');
          router.push('/login');
        } else {
          next();
        }
      })
    } else {
      router.push('login');
    }
  } else if(to.name === null) {
    alert('존재하지 않는 페이지입니다.');
    next({
      query: {
        redirect: history.back()
      },
    })
  } else {
    next();
  }

  // axios.get("/accounts/messages").then((result)=>{
  //   if(result>0){
  //     this.$store.messageFlag=true;
  //   }
  // })

});

export default router


