import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    accessToken: null,
    userEmail: '', //dao랑 변수명 같게
    userNickName: '',
    userUid: '',
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserEmail(state) {
      return state.userEmail;
    },
    getUserNickName(state) {
      return state.userNickName;
    },
    getUserUid(state) {
      return state.userUid;
    },
  },
  mutations: {
    LOGIN(state, payload) {
      console.log('payload = ' + payload);
      state.accessToken = payload['access-token'];
      state.userEmail = payload['user-email'];
      state.userNickName = payload['nickName'];
      state.userUid = payload['uid'];
      sessionStorage.setItem('loginInfo', JSON.stringify(payload));
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userEmail = '';
      state.userNickName = '';
      state.userUid = '';
      sessionStorage.removeItem('loginInfo');
    },
  },
  actions: {
    LOGIN(context, user) {
      return axios.post(`${SERVER_URL}/user/login`, user).then((response) => {
        console.log(response.data.message);
        if (response.data.message == 'success') {
          context.commit('LOGIN', response.data);
          document.cookie = `access-token=${response.data['access-token']}`;
          axios.defaults.headers.common[
            'access-token'
          ] = `${response.data['access-token']}`;
          return response;
        } else {
          alert('아이디 및 비밀번호를 다시 확인해주세요.');
        }
      });
    },
    LOGOUT(context) {
      context.commit('LOGOUT');
      axios.defaults.headers.common['access-token'] = undefined;
    },
  },
  modules: {},
});
