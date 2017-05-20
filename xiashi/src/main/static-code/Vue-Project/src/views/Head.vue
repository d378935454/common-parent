<template>
  <mt-header  :title="title">
    <mt-button v-on:click="goback" icon="back" slot="left">返回</mt-button>
    <!--<mt-button icon="more" slot="right"></mt-button>-->
  </mt-header>
</template>
<script>
    export default{
        data(){
            return {

            }
        },
      computed:{
        title:function(){
            return this.$store.state.a.title
        }
      },
      methods:{
            goback:function () {
              this.$router.go(-1)
            }
      }
    }
</script>
<style>

</style>
