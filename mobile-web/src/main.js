
import Vue from 'vue';
import { router } from './config/router';
import './config/rem';
import App from './App.vue';
import VueLazyload from 'vue-lazyload'
import components from './config/components.js';
import { Dialog } from 'vant';

Vue.use(components);

Vue.use(VueLazyload);
Vue.use(Dialog);

new Vue({
  router,
  el: '#app',
  render: h => h(App)
});