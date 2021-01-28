import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '@/store';
import MainPage from '../views/MainPage';

Vue.use(VueRouter);

const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;
  if (store.getters.getAccessToken) {
    return next();
  } else next('/login' + nextRoute);
};

const routes = [
  {
    path: '/login/:nextRoute',
    name: 'login',
    component: () => import('../views/Login.vue'),
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue'),
  },
  {
    path: '/forgot-password',
    name: 'forgot-password',
    component: () => import('../views/ForgotPassword.vue'),
  },
  {
    path: '/forgot-password-code',
    name: 'forgot-password-code',
    component: () => import('../views/ForgotPasswordCode.vue'),
  },
  {
    path: '/forgot-password-final',
    name: 'forgot-password-final',
    component: () => import('../views/ForgotPasswordFinal.vue'),
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('../views/SignUp.vue'),
  },
  {
    path: '/challengelist',
    name: 'ChallengeList',
    component: () => import('../views/ChallengeList.vue'),
  },
  {
    path: '/challengelist/:category_number',
    name: 'ChallengeList',
    component: () => import('../views/ChallengeList.vue'),
  },
  {
    path: '/challenge-creating',
    name: 'ChallengeCreating',
    component: () => import('../views/ChallengeCreating.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/',
    name: 'MainPage',
    component: MainPage,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  if (
    from.name == 'ChallengeCreating' &&
    !confirm('나가시면 챌린지 개설이 종료됩니다.\n정말 나가시겠습니까?')
  ) {
    next(false);
  } else {
    next();
  }
});

export default router;
