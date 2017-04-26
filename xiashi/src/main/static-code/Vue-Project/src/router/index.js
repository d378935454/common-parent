import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login.vue'
import NotFound from '../views/404.vue'
import CreateOrder from '../views/order/CreateOrder.vue'
import RepoComf from '../views/order/RepoComf.vue'
import ExpressComf from '../views/order/ExpressComf.vue'
import OrderList from '../views/order/OrderList.vue'
import Check from '../views/order/Check.vue'
import UpPic from '../views/order/UpPic.vue'
import Over from '../views/order/Over.vue'
// import echarts from '../views/charts/echarts.vue'
import Menu from "../views/Menu.vue"
import MenuTwo from "../views/MenuTwo.vue"
import main from "../views/Main.vue"
Vue.use(Router)

let routes = [
  {
    path: '/login',
    component: Login,
    name: ''
  },
  {
    path: '/404',
    component: NotFound,
    name: ''
  },
  {
    path: '/main',
    component: main,
    name: '',
    children: [
      { path: '', component: Menu },
      { path: ':id', component: MenuTwo },
      { path: '/CreateOrder', component: CreateOrder },
      { path: '/OrderList/:type', component: OrderList },
      { path: '/RepoComf/:id', component: RepoComf },
      { path: '/ExpressComf/:id', component: ExpressComf },
      { path: '/Check/:id', component: Check },
      { path: '/UpPic/:id', component: UpPic },
      { path: '/Over/:id', component: Over },
    ]
  },
  // { path: '/main', component: Main },
  // {
  //   path: '/',
  //   component: Home,
  //   name: '导航一',
  //   iconCls: 'el-icon-message', // 图标样式class
  //   children: [
  //     {path: '/main', component: Main, name: '主页', hidden: true},
  //     {path: '/table', component: Table, name: 'Table'},
  //     {path: '/form', component: Form, name: 'Form'},
  //     {path: '/user', component: user, name: '列表'}
  //   ]
  // },
  // {
  //   path: '/',
  //   component: Home,
  //   name: '导航二',
  //   iconCls: 'fa fa-id-card-o',
  //   children: [
  //     {path: '/page4', component: Page4, name: '页面4'},
  //     {path: '/page5', component: Page5, name: '页面5'}
  //   ]
  // },
  // {
  //   path: '/',
  //   component: Home,
  //   name: '',
  //   iconCls: 'fa fa-address-card',
  //   leaf: true, // 只有一个节点
  //   children: [
  //     {path: '/page6', component: Page6, name: '导航三'}
  //   ]
  // },
  // {
  //   path: '/',
  //   component: Home,
  //   name: 'Charts',
  //   iconCls: 'fa fa-bar-chart',
  //   children: [
  //     {path: '/echarts', component: echarts, name: 'echarts'}
  //   ]
  // },
  {
    path: '*',
    hidden: true,
    redirect: { path: '/404' }
  }
]
export default new Router({
  routes
})
