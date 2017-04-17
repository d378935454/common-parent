<template>
  <div>
    <mt-cell v-for="i in menu" :title="i.name" :key="i.id" label="描述信息" is-link  :to="i.href"></mt-cell>
  </div>
</template>

<script>
  export default {
    data() {
      return {
          id:"",
       menus:[]
      }
    },
    computed:{
      menu:function () {
      let id= this.id
       let menu= this.menus.filter(function (i,n) {

          return i.id==id
        })[0]
        this.$store.dispatch('updateTitle',menu.name)
        return menu.menus
      }
    },
    methods: {
    },
    watch: {
      '$route' (to, from) {
          debugger
        this.id=to.params.id
      }
    },
    created:function () {
      let $this=this;
      $this.id=this.$route.params.id
      $this.menus=JSON.parse(sessionStorage.getItem("user")).menus

    }
  }
</script>
<style>

</style>
