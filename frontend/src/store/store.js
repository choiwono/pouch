import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

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
    },
  },
});
