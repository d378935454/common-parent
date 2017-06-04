<template>
  <div>
    <mt-cell  v-for="i in menus" :title="i.name"  :key="i.id" label="描述信息" is-link :to="'main/'+i.id"></mt-cell>
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

    },
    methods: {
    },
    watch: {
//      '$route' (to, from) {
//          debugger
//        this.id=to.params.id
//      }
    },
    created:function () {
      let $this=this;
      this.$store.dispatch('updateTitle',"一级目录")
      $this.menus=JSON.parse(sessionStorage.getItem("user")).menus

                                                                                                          }
  }
</script>
<style>

</style>
