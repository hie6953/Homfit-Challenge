import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import API_BASE_URL from '@/config';

Vue.use(Vuex);
const SERVER_URL = API_BASE_URL;

export default new Vuex.Store({
  state: {
    accessToken: null,
    userEmail: '',
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getuserEmail(state) {
      return state.userEmail;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload['access-token'];
      state.userEmail = payload['user-email'];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userEmail = '';
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
