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
Vue.axios.defaults.baseURL = 'http://localhost:3000/api'
Vue.router = router
Vue.use(VueCookie)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
