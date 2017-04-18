<template>
    <div class="login-body">
      <header class="login-header">
        <b>欢迎登录</b>
      </header>
      <form class="form">
        <div class="form-group">
          <label for="user">用户名</label>
          <input type="email" class="form-control" id="user" placeholder="请输入用户名">
        </div>
        <div class="form-group">
          <label for="pswd">密码</label>
          <input type="password" class="form-control" id="pswd" placeholder="请输入密码">
        </div>
        <button type="button" @click="handleSubmit2" class="btn btn-primary cursor ">登录</button>
      </form>
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

<style>
  /* ==============初始化样式============== */
  *{box-sizing:border-box;-moz-box-sizing:border-box; -webkit-box-sizing:border-box; outline: none;}
  body,h1,h2,h3,h4,h5,h6,hr,p,blockquote,dl,dt,dd,ul,ol,li,pre,form,fieldset,legend,button,input,textarea,th,td { margin: 0; padding: 0; border: 0;}
  html,body { height: 100%; }
  body,button,input,select,textarea { font-size: 12px; font-family: 'Microsoft Yahei','微软雅黑', \5FAE\8F6F\96C5\9ED1,'宋体',\5b8b\4f53,arial,'Hiragino Sans GB',Tahoma,Arial,Helvetica; }
  ul,ol,li,dl,dd,dt{list-style: none;}
  em { font-style: normal; }
  a { text-decoration: none; }
  a:hover { text-decoration: none; }
  legend { color: #000; }
  fieldset,img { border: 0; }
  label,button { cursor: pointer; }
  /* dfa */
  html,body,form,fieldset,p,div,h1,h2,h3,h4,h5,h6{-webkit-text-size-adjust:none;}
  article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{display:block;}
  input[type="submit"],input[type="reset"],input[type="button"],button,input[type="search"],select,input[type=number],input[type=number]{-webkit-appearance: none;}
  input[type='number'],input[type='tel'] {-moz-appearance:textfield;}
  input[type=number]::-webkit-inner-spin-button,input[type=number]::-webkit-outer-spin-button {-webkit-appearance: none ;margin: 0;}{-moz-appearance:textfield;}
  input[type=number]::-webkit-inner-spin-button,input[type=number]::-webkit-outer-spin-button {-webkit-appearance: none ;margin: 0;}
  /*解决IE图片缩放失真*/
  img { -ms-interpolation-mode: bicubic; }
  /*清除浮动*/
  .clearfix:after { content: ''; display: block; height: 0; clear: both; }
  .clearfix { zoom: 1; line-height: normal; }
  .clear { clear: both; }
  /*连续英文、数字换行*/
  .wordwrap { word-break: break-all; word-wrap: break-word; }
  /*单行文字超出显示省略号*/
  .omg{overflow: hidden; text-overflow: ellipsis; white-space:nowrap;}
  /*ios去除点击时的灰色遮罩*/
  a,button,input,textarea,select{-webkit-tap-highlight-color: rgba(255,255,255,0);}
  *{
    -webkit-touch-callout:none;  /*系统默认菜单被禁用*/
    -webkit-user-select:none; /*webkit浏览器*/
    user-select:none;
  }
  input{
    -webkit-user-select:auto; /*webkit浏览器*/
  }

  .login-header {
    width: 100%;
    text-align: center;
    padding:20% 0 10%;
  }

  .login-header b {
    width: 100%;
    font-size: 16px;
    text-align: center;
  }




.form{
  margin: 0 10%;
}
  .btn-primary{
    width: 100%;
  }
</style>
