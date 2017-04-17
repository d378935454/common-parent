/**
 * Created by bean on 2017/2/19.
 */

import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'
import actions from './actions'

Vue.use(Vuex)
const state = {
  totalTime: 0,
  list: [],
  title:""
}
const moduleA = {
  state,
  mutations,
  actions
}
export default new Vuex.Store({
  modules: {
    a: moduleA
  }
})
