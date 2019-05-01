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
import Notifications from 'vue-notification'
import { store } from './store/store'

Vue.component('icon',icon);
Vue.use(Vuetify);
Vue.config.productionTip = false
Vue.use(BootstrapVue);
Vue.use(VueAxios);
Vue.axios.defaults.baseURL = 'http://localhost:8080/api'
Vue.router = router;
Vue.use(VueCookie);
Vue.use(Notifications);
Vue.prototype.$axios = axios;
Vue.prototype.$EventBus = new Vue();
Vue.prototype.$flash = Notifications;

axios.interceptors.response.use(response => {
    return response.data;
  }, error => {
  if (error.response && error.response.data) {
    let errCode = error.response.status;
    if(errCode === 401){
      //alert('권한이 없는 페이지입니다.');
      router.push({name:'login'})
    } else if(errCode === 404 || errCode === 405 || errCode === 500){
      switch (errCode){
        case 404 : alert("존재하지 않는 페이지입니다."); break;
        case 405 : alert("존재하지 않는 페이지입니다."); break;
        case 500 : alert("서버에서 데이터를 요청할 수 없습니다."); break;
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
  store,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})
