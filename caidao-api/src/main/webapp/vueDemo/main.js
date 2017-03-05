/**
 * reuqire js 入口模块
 * Created by yuzhou on 16/9/5.
 */

var requireConfig = {
    baseUrl: '../../vueDemo',
    paths: {
        'text': '../js/requirejs/text',
        'vue': '../../js/vue/vue',
        'vue-router': '../../js/vue/vue-router',
        'vue-resource': '../../js/vue/vue-resource',
        'vue-strap': '../../js/vue/vue-strap'
    },
    shim: {},
    map: {}
}

require.config(requireConfig)
require(['vue', 'vue-router', 'vue-resource', 'plugin/vue-cookie', 'app'], function (Vue, VueRouter, VueResource, VueCookie, App) {
    Vue.use(VueCookie)

    // 配置自定义过滤器
    Vue.filter('localDateString', function (value) {
        return new Date(value).toLocaleString()
    })

    // 配置resouce访问
    Vue.use(VueResource)
    Vue.http.options.root = "http://localhost:28080/"

    // 配置路由
    Vue.use(VueRouter)
    const routes = [,
        {
            path: '/',
            component: App,
            name: 'seckill'
            // children: [
            //     {
            //         path: '/',
            //         component: function (resolve) {
            //             require(['seckill/list'], function (SeckillList) {
            //                 resolve(SeckillList)
            //             })
            //         }
            //     },
            //     {
            //         path: '/show/:seckillId',
            //         name: 'seckillDetail',
            //         component: function (resolve) {
            //             require(['seckill/detail'], function (SeckillDetail) {
            //                 resolve(SeckillDetail)
            //             })
            //         }
            //     }]
        },
        {
            path: '/login',
            component: function (resolve) {
                require(['login'], function (Login) {
                    resolve(Login)
                })
            }
        }
        ,
        {
            path: '/error',
            component: function (resolve) {
                require(['error'], function (Error) {
                    resolve(Error)
                })
            }
        }
    ]
    var router = new VueRouter({routes})

    var vm=new Vue({
         router
    }).$mount('#app')
    debugger
    window.router = router
})
