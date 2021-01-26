import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import API_BASE_URL from '@/config/index.js';

Vue.use(Vuex);
const VUE_APP_SERVER_URL = "http://localhost:8080/";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    accessToken: null,
    userId: '',
    userName: '',
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.userId;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload['access-token'];
      state.userId = payload['user-id'];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = '';
    }
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/user/login`, user)
        .then((response) => {
          context.commit('LOGIN', response.data);
          axios.defaults.headers.common[
            'access-token'
          ] = `${response.data['access-token']}`;
        });
    },
    LOGOUT(context) {
      context.commit('LOGOUT');
      axios.defaults.headers.common['access-token'] = undefined;
    },
  },
  modules: {},
});
