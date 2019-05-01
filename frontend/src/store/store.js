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
  },
  actions: {
    getAuth(){

    }
  }
});
