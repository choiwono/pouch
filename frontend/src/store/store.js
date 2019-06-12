import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import VueAxios from 'vue-axios';
import Notifications from 'vue-notification'
import createPersistedState from 'vuex-persistedstate';

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
    searchCategory : [],
    //TODO messageFlag
    // messageFlag: true
  },
  plugins: [createPersistedState()],
  getters: {
    getCategories: (state) => {
      return state.categories;
    },
    getCategory: (state) => {
      return state.category;
    },
    getAuth: (state) => {
      return state.auth;
    },
    getCategoryId: (state) => {
      return state.categoryId;
    },
    getLinks: (state,payload) => {

    },
  },

  mutations: {
    changeCategories: (state,payload) => {
      return state.categories = payload.arr;
    },
    changeAuth: (state,payload) => {
      return state.auth = payload.value;
    },
    addLinkList: (state,payload) => {
      state.category.links.push(payload.link);
    },
    changeLinkTitle: (state,payload) => {
      let links = state.category.links;
      for(let i=0; i<links.length; i++){
        if(links[i].id === payload.id) {
          links[i].title = payload.title;
        }
      }
    },
  },
  actions: {

  }
});
