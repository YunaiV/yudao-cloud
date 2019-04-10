import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

// 挂载
export default new Vuex.Store({
  state: {
    addressData: {
    },
  },
  mutations: {
    changeAddressData: function (state, payload) {
      state.addressData = payload;
    }
  },
  actions: {

  },
  getters: {

  }
});
