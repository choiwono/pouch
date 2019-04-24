import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Account/Login'
import Home from "@/components/Home";
import Join from '@/components/Account/Join'

import Search from '@/components/Contents/Search'
import { store } from '../store/store';
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
      component: Home,
      meta: { requiresAuth: true }
    },
    {
      path: '/join/',
      name: 'join',
      component: Join
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
    }
  ]
})

router.beforeEach((to,from,next) => {
  const authUser = localStorage.getItem('pouch_user');
  if(to.meta.requiresAuth){
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


