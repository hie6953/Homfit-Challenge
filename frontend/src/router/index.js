import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/forgot-password',
    name: 'forgot-password',
    component: () => import('../views/ForgotPassword.vue')
  },
  {
    path: '/forgot-password-code',
    name: 'forgot-password-code',
    component: () => import('../views/ForgotPasswordCode.vue')
  },
  {
    path: '/forgot-password-final',
    name: 'forgot-password-final',
    component: () => import('../views/ForgotPasswordFinal.vue')
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('../views/SignUp.vue')
  },
  {
    path: '/challengelist',
    name: 'ChallengeList',
    component: () => import('../views/ChallengeList.vue')
  },

];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
