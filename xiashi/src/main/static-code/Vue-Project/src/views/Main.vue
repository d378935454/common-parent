<template>
  <div>
    <Heade></Heade>
    <transition name="fade"
                mode="out-in">
    <router-view></router-view>
    </transition>
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
