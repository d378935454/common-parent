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
        'axios': '../../js/vue/axios.min',
        'vue-strap': '../../js/vue/vue-strap'
    },
    shim: {},
    map: {}
};

require.config(requireConfig);
require(['vue', 'vue-router', 'axios', 'plugin/vue-cookie', 'app'], function (Vue, VueRouter, axios, VueCookie, App) {
    Vue.config.devtools = true;
    Vue.use(VueCookie);

    // 配置自定义过滤器
    Vue.filter('localDateString', function (value) {
        return new Date(value).toLocaleString()
    });
    const instance = axios.create({
        baseURL: 'http://localhost:8081',
        timeout: 1000,
        headers: {'X-Custom-Header': 'foobar'}
    });

    //可以设置cookies
    instance.defaults.withCredentials =true;
    //添加请求拦截器
    instance.interceptors.request.use(function (config) {
        //在发送请求之前做某事
        return config;
    }, function (error) {
        //请求错误时做些事
        return Promise.reject(error);
    });

//添加响应拦截器
    instance.interceptors.response.use(function (response) {
        //对响应数据做些事
        if (response.status == 401) {
            router.push({path: '/login'});
        }
        return response.data;
    }, function (error) {
        //请求错误时做些事
        return Promise.reject(error);
    });
    // 配置resouce访问
    Vue.prototype.$http = instance;
    // 配置路由
    Vue.use(VueRouter);
    const routes = [,
        {
            path: '/',
            component: App,
            children: [
                {
                    path: '/',
                    component: function (resolve) {
                        require(['seckill/list'], function (SeckillList) {
                            resolve(SeckillList)
                        })
                    }
                },
                {
                    path: '/show/:seckillId',
                    name: 'seckillDetail',
                    component: function (resolve) {
                        require(['seckill/detail'], function (SeckillDetail) {
                            resolve(SeckillDetail)
                        })
                    }
                }]
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
    ];
    var router = new VueRouter({routes});

    var vm = new Vue({
        router
    }).$mount('#app');
    // window.router = router
});
