import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueCookie from 'vue-cookies'
import 'vue-awesome/icons'
import icon from 'vue-awesome/components/Icon'
import 'vuetify/dist/vuetify.min.css'
import Vuetify from 'vuetify'

Vue.component('icon',icon)
Vue.use(Vuetify)
Vue.config.productionTip = false
Vue.use(BootstrapVue)
Vue.use(VueAxios,axios)
Vue.axios.defaults.baseURL = 'http://localhost:8080/api'
Vue.router = router
Vue.use(VueCookie)
Vue.prototype.$EventBus = new Vue();

axios.interceptors.response.use(response => {
    return response.data;
  }, error => {
  if (error.response && error.response.data) {
    let errCode = error.response.status;
    if(errCode === 401){
      alert('권한이 없는 페이지입니다.');
      router.push({name:'login'})
    } else if(errCode === 404 || errCode === 405 || errCode === 500){
      switch (errCode){
        case 404 : alert("존재하지 않는 페이지입니다."); break;
        case 405 : alert("존재하지 않는 페이지입니다."); break;
        case 500 : alert("서버에 장애가 발생했습니다. 다시 처음부터 시도해주세요."); break;
      }
      router.beforeEach((to,from,next) => {
        next({
          query: {
            redirect: to.fullPath
          },
        })
      });
    }
  }
  return Promise.reject(error);
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
