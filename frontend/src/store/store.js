import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export const store = new Vuex.Store({
  state : {
    token: '',
    categories: [],
    categoryId: '',
    url: '',
    searchCategory : []
  },
  getters: {
    getToken: function(state){
      return state.token;
    }
  },
  mutations: {
    loginToken : (state, payload) => {
      state.token = payload;
    },
    /*logOut: (state) => {
      if(state.token){
        state.token = '';
        alert('로그아웃 되었습니다.');
      }
    }*/
  },
  /*getters: {
    getToken : (state) => {
      return state.token;
    }
  }*/
});
