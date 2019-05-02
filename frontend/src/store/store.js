import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(Vuex);
Vue.use(VueAxios,axios);

export const store = new Vuex.Store({
  state : {
    categories: [],
    category:[],
    categoryId: '',
    url: '',
    paramsId: '',
    auth: '',
    searchCategory : []
  },
  getters: {
    getCategories: (state) => {
      return state.categories;
    },
    getCategory: (state) => {
      return state.category;
    },
    getAuth: (state) => {
      return state.auth;
    }
  },
  mutations: {
    changeCategories: (state,payload) => {
      return state.categories = payload.arr;
    },
    changeAuth: (state,payload) => {
      return state.auth = payload.value;
    }
  },
  actions: {

  }
});
