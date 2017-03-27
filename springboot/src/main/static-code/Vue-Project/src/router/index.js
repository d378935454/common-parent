import Vue from 'vue'
import Router from 'vue-router'
import Hello from '../components/main/Hello'
import Form from '../components/main/From'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/form',
      name: 'form',
      component: Form
    }
  ]
})
