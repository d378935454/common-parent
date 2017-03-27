// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import http from './http'
import 'element-ui/lib/theme-default/index.css'
import ElementUI from 'element-ui'

// 将axios挂载到prototype上，在组件中可以直接使用this.axios访问
Vue.prototype.http = http
Vue.config.productionTip = false
Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
