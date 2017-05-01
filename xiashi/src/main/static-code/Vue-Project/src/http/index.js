/**
 * Created by ppctest02 on 2017/3/13.
 */
import axios from 'axios'

const http = axios.create({
  // baseURL: 'http://localhost:8088',
  baseURL: 'mobile',
  timeout: 2000,
  headers: {'X-Custom-Header': 'foobar'}
})

// http request 拦截器
http.interceptors.request.use(config => {
  return config
},
  err => {
    return Promise.reject(err)
  })

// // http response 拦截器
axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    if (error.response) {
      switch (error.response.status) {

      }
    }
    return Promise.reject(error.response.data)
  })

export default {
  install: function(Vue,) {
    Object.defineProperty(Vue.prototype, '$http', { value: http });
  }
}
