<template>
  <div>
    <Heade></Heade>
    <router-view></router-view>
    <!--<Tabbar></Tabbar>-->
  </div>
</template>

<script>
  import Tabbar from '../components/Tabbar.vue'
  import Heade from '../views/Head.vue'

  export default {
    components: {
      Tabbar,
      Heade
    }
  }

</script>

<style scoped>

</style>
