/*
 * @Author: zyw
 * @Date: 2023-12-21 08:26:38
 * @LastEditors: zyw
 * @LastEditTime: 2023-12-21 10:24:51
 * @Description: 请填写简介
 */
import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    redirect:"/home",
    component: () => import("../views/Manage.vue"),
    children:[
      {path: 'user', name: 'USer', component: () => import("../views/User.vue")},
      {path: 'home', name: 'Home', component: () => import("../views/Home.vue")},
      {path: 'mhsd', name: 'Mhsd', component: () => import("../views/Mhsd.vue")},
      {path: 'person', name: 'Person', component: () => import("../views/Person.vue")},
      {path: 'addone', name: 'Addone', component: () => import("../views/Addone.vue")},
      {path: 'addxml', name: 'Addxml', component: () => import("../views/Addxml.vue")},
      {path: 'map', name: 'Map', component: () => import("../views/Map.vue")},


    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'Login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
