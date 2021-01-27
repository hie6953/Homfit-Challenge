import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import '@/assets/css/main.css';
import VueCookie from 'vue-cookie';

Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.use(VueCookie);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  created() {
    const login = localStorage.getItem('loginInfo');
    if (login) {
      const userData = JSON.parse(login);
      this.$store.commit('AutoLogin', userData);
    }
  },
  render: (h) => h(App),
}).$mount('#app');
