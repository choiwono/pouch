import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export const store = new Vuex.Store({
  state : {
    token: '',
    categories: [],
    category:[],
    categoryId: '',
    url: '',
    paramsId: '',
    searchCategory : []
  },
  getters: {
    getCategories: (state) => {
      return state.categories;
    },
    getCategory: (state) => {
      return state.category;
    }
  },
  mutations: {
    changeCategories: (state,payload) => {
      return state.categories = payload.arr;
    }
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
