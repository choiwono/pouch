import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Account/Login'
import Home from "@/components/Home";
import Join from '@/components/Account/Join'

import Search from '@/components/Search'

import Categories from "../components/Contents/Categories";


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
      component: Home
    },
    {
      path: '/join/',
      name: 'join',
      component: Join
    },
    {

      path: '/categories/search/',
      name: 'search',
      component: Search
    },{
      path: '/categories/:id',
      name: 'categories',
      component: Categories

    }
  ]
})

router.beforeEach((to,from,next) => {
  const authUser = window.$cookies.get('Token');

  if(to.meta.requiresAuth){
    console.log(to);
    console.log(from);

    if(authUser == null){
      router.push('/login');
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router


