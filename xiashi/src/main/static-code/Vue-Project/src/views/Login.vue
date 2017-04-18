<template>
  <div>
    <div class="login-header">
      <p>登录ERP系统</p>
      <div class="logo"></div>
    </div>
    <div class="box-size login-form">
      <span class="fr login-btn"></span>
      <h1>系统登录</h1>
      <form class="login-form-list" id="form-id" action="">
        <ul class="login-ul">
          <li class="box-size">
            <div class="box-size">
              <i class="iconfont icon1">&#xe610;</i>
              <input id="phone" name="phone" class="field-text box-size f-input" type="tel" datatype="m" placeholder="手机号码"  >
              <button type="button" class="blue-color getyzm" autocomplete="off">获取验证码</button>
            </div>
          </li>
          <li class="box-size">
            <div>
              <i class="iconfont icon1">&#xe611;</i>
              <input id="password" name="password" class="field-text box-size f-input" type="password" placeholder="密码" >
            </div>
          </li>
          <li class="box-size">
            <div>
              <i class="iconfont icon1">&#xe60b;</i>
              <input id="code" name="code" class="field-text box-size f-input" type="number" placeholder="验证码" >
            </div>
          </li>
          <li class="box-size" style="height:0.58rem;padding-top:0;padding-bottom:0">
            <div>
              <input class="box-size blue-color u-tj_btn" type="submit" value="登录" autocomplete="off" style="padding:0;" />
            </div>
          </li>
        </ul>
      </form>
    </div>
  </div>
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

<style >
  .login-header {
    width: 100%;
    text-align: center;
  }

  .login-header p {
    width: 100%;
    font-size: 0.24rem;
    color: #fff;
    text-align: center;
  }

  .logo {
    display: inline-block;
    margin-top: 0.38rem;
    width: 3.67rem;
    height: 0.68rem;
    /*background: url('../images/icon/logo.png') 0 0 no-repeat;*/
    -webkit-background-size: 100% 100%;
    background-size: 100% 100%;
  }

  .login-form {
    margin: 0.6rem auto 0;
    width: 4.45rem;
    height: 4.95rem;
    border-radius: 0.05rem;
    border: 1px solid #2f2f2f;
    padding: 0.15rem 0.27rem;
    /*background: url('../images/img/loginformbg.gif') 0 0 repeat;*/
  }

  .login-form span.login-btn {
    display: block;
    width: 0.24rem;
    height: 0.24rem;
    /*background: url('../images/icon/login_btn.png') 0 0 no-repeat;*/
    -webkit-background-size: 100% 100%;
    background-size: 100% 100%;
  }

  .login-form h1 {
    margin-top: 0.15rem;
    font-size: 0.3rem;
    color: #fff;
    text-align: left;
  }

  .login-form-list {
    margin-top: 0.38rem;
    width: 3.9rem;
  }

  .login-ul {
    width: 100%;
  }

  .login-ul li {
    margin-bottom: 0.18rem;
    width: 100%;
    border: 1px solid #353535;
    border-radius: 0.03rem;
    background-color: #1B1B1B;
  }

  .login-ul li div {
    width: 100%;
    position: relative;
  }

  .login-ul li div i.icon1 {
    position: absolute;
    top: 50%;
    left: 0.1rem;
    transform: translateY(-50%);
    color: #AAAAAA;
    font-size: 0.24rem;
  }

  .login-ul li:first-child div input {
    padding-right: 1.4rem;
  }

  .login-ul li div input {
    padding: 0.18rem 0 0.18rem 0.46rem;
    width: 100%;
    background: none;
    font-size: 0.18rem;
    color: #fff;
    outline: none;
  }

  .login-ul li:last-child {
    margin-top: 0.42rem;
  }

  .login-ul li:last-child div input {
    padding-left: 0;
    border-radius: 3px;
    background-color: #4c88f5;
    font-size: 0.18rem;
    color: #fff;
    line-height: 0.58rem;
    text-align: center;
  }

  .login-ul li div button {
    position: absolute;
    top: 50%;
    right: 0.02rem;
    padding: 0.02rem 0.14rem;
    transform: translateY(-50%);
    border-radius: 0.03rem;
    background-color: #4c88f5;
    font-size: 0.18rem;
    color: #fff;
    line-height: 0.34rem;
    text-align: center;
  }

  .login-ul li div button.disabled {
    background-color: #4E4E4E;
  }




  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 100%;
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
