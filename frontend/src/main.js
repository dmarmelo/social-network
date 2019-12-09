import Vue from 'vue'
import Vuex from 'vuex'
import router from './router';
import {sync} from 'vuex-router-sync'
import store from './store'
import App from './App.vue'
import './quasar'
import ApiService from "./services/api.service";
import {TokenService} from "./services/storage.service";
import VueMeta from "vue-meta";

Vue.config.productionTip = false

// Set the base URL of the API
ApiService.init(process.env.VUE_APP_ROOT_API)

// If token exists set header

if (TokenService.getToken()) {
  ApiService.setHeader()
}

Vue.use(VueMeta, {
  // optional pluginOptions
  refreshOnceOnNavigation: true
})

//Sync vue-router's current $route as part of vuex store's state.
sync(store, router)

Vue.use(Vuex)

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
