// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import http from './http'
import MintUI from 'mint-ui';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'mint-ui/lib/style.css';
import qs from 'qs'
import util from './common/js/util'

import iView from 'iview';
import 'iview/dist/styles/iview.css';    // 使用 CSS
import VuePreview from 'vue-preview'
Vue.use(VuePreview)
// 将axios挂载到prototype上，在组件中可以直接使用this.axios访问
// Vue.prototype.http =
Vue.use(http)
Vue.prototype.util = util
Vue.config.productionTip = false
Vue.use(MintUI)
Vue.use(iView)
window.qs=qs
router.beforeEach((to, from, next) => {
  // NProgress.start();
  if (to.path === '/login') {
    sessionStorage.removeItem('user')
  }
  let user = JSON.parse(sessionStorage.getItem('user'))
  if (!user && to.path !== '/login') {
    next({ path: '/login' })
  } else {
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
