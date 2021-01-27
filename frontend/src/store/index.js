import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: null,
    userEmail: '',  //dao랑 변수명 같게
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserEmail(state) {
      return state.userEmail;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload['access-token'];
      state.userEmail = payload['user-email'];
      sessionStorage.setItem('loginInfo', JSON.stringify(payload));
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userEmail = '';
      sessionStorage.removeItem('loginInfo');
    }
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/user/login`, user)
        .then((response) => {
          console.log(response.data.message );
          if (response.data.message == "success") {
            context.commit('LOGIN', response.data);
            document.cookie = `access-token=${response.data['access-token']}`;
            axios.defaults.headers.common[
              'access-token'
            ] = `${response.data['access-token']}`;
            return response;
          } else {
            alert("아이디 및 비밀번호를 다시 확인해주세요.");
          }
        })
    },
    LOGOUT(context) {
      context.commit('LOGOUT');
      axios.defaults.headers.common['access-token'] = undefined;
    },
    
  },
  modules: {},
});
