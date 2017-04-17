<template>
  <form role="form">
    <div class="form-group">
      <label for="username">用户名：</label>
      <input placeholder="输入用户名" type="text"
             v-model.trim="username"
             id="username" class="form-control">
    </div>
    <div class="form-group">
      <label for="password">密码</label>
      <input type="password" class="form-control"
             v-model="password"
             id="password">
    </div>
    <input class="btn btn-default" type="button" value="Input" @click="handleSubmit2">
    <!--<el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px"-->
    <!--class="demo-ruleForm login-container">-->
    <!--<h3 class="title">系统登录</h3>-->
    <!--<el-form-item prop="account">-->
    <!--<el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="账号"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item prop="checkPass">-->
    <!--<el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="密码"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>-->
    <!--<el-form-item style="width:100%;">-->
    <!--<el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">登录-->

    <!--</el-button>-->
    <!--&lt;!&ndash;<el-button @click.native.prevent="handleReset2">重置</el-button>&ndash;&gt;-->
    <!--</el-form-item>-->
    <!--</el-form>-->
  </form>
</template>

<script>
  import {requestLogin} from '../api/api'
  // import NProgress from 'nprogress'
  export default {
    data () {
      return {
        logining: false,
        username: 'admin',
        password: ''
      }
    },
    methods: {
      handleReset2 () {
        this.$refs.ruleForm2.resetFields()
      },
      handleSubmit2 (ev) {
          debugger
        let $this = this
        if(!$this.username||!$this.password){
            alert("用户名和密码不可为空")
            return
        }
        // _this.$router.replace('/table');
        // NProgress.start();
        let loginParams = { accountName: $this.username, password: $this.password }
        $this.http.post('/mobile/check-login', qs.stringify(loginParams))
          .then(response => {
            $this.logining = false
            // NProgress.done();
            let { msg, code, data } = response.data
            if (code !== 1) {
              $this.$message({
                message: msg,
                type: 'error'
              })
            } else {
              sessionStorage.setItem('user', JSON.stringify(data))
              $this.$router.push({ path: '/main' })
            }
          }).catch(error => {
            console.log(error)
            $this.logining = false
            $this.$message({
              message: "服务器异常",
              type: 'error'
            })
          }
        )


      }
    }
  }

</script>

<style scoped>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .remember {
    margin: 0px 0px 35px 0px;
  }
</style>
