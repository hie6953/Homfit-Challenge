import Vue from 'vue';
import VueRouter from 'vue-router';
// import store from '@/store';
import MainPage from '../views/MainPage';

Vue.use(VueRouter);

const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;
  if (sessionStorage.getItem('loginInfo')) {
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
    path: '/challengelist/:category_number/:category_sort',
    name: 'ChallengeListCategory',
    component: () => import('../views/ChallengeList.vue'),
  },
  {
    path: '/feed',
    name: 'MainFeedPage',
    component: () => import('../views/MainFeedPage.vue'),
  },
  {
    path: '/feedcardlistchat',
    name: 'FeedCardListChat',
    component: () => import('../views/FeedCardListChat.vue'),
  },
  {
    path: '/feedcardlist',
    name: 'FeedCardList',
    component: () => import('../views/FeedCardList.vue'),
  },
  {
    path: '/challenge-creating',
    name: 'ChallengeCreating',
    component: () => import('../views/ChallengeCreating.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/challenge-more-info/:challenge_id',
    name: 'ChallengeMoreInfo',
    component: () => import('../views/ChallengeMoreInfo.vue'),
  },
  {
    path:'/challenge-edit/:challenge_id',
    name:"ChallengeEdit",
    component: () => import('../views/ChallengeEdit.vue'),
  },
  {
    path:'/challenge-recommend',
    name:"ChallengeRecommend",
    component: () => import('../views/ChallengeRecommend.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: () => import('../views/MyPage.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/editprofile',
    name: 'ProfileEdit',
    component: () => import('../views/ProfileEdit.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/editprofilepassconfirm',
    name: 'ProfileEditPasswordConfirm',
    component: () => import('../views/ProfileEditPasswordConfirm.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/mypoint',
    name: 'MyPoint',
    component: () => import('../views/MyPoint.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/challengemanage',
    name: 'ChallengeManage',
    component: () => import('../views/ChallengeManage.vue'),
    beforeEnter: requireAuth(),
  },
 
  {
    path: '/search',
    name: 'Search',
    component: () => import('../views/Search.vue'),
  },
  {
    path: '/certificationphoto',
    name: 'CertificationPhoto',
    component: () => import('../views/CertificationPhoto.vue'),
  },

  {
    path: '/',
    name: 'MainPage',
    component: MainPage,
  },
  {
    path: '/mypage/statistics',
    name: 'Statistics',
    component: () => import('../views/StatisticsPage.vue'),
    beforeEnter: requireAuth(),
  },
  {

    path: '/bookmark',
    name: 'Bookmark',
    component: () => import('../views/Bookmark.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/participated/:challenge_id',
    name: 'ParticipatedChallenge',
    component: () => import('../views/ParticipatedChallenge.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path: '/certify/:challenge_id',
    name: 'Certify',
    component: () => import('../views/Certify.vue'),
    beforeEnter: requireAuth(),
  },
  {
    path:'/404',
    name:"notFound",
    component: () => import('../views/NotFound.vue'),
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: "/404"
  },
  
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior () {
    // 라우터 이동 시 원하는 위치 설정하기
    return { x: 0, y: 0 } 
  },
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
