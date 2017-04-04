<template>
  <mt-index-list>
    <mt-index-section  :index="one.name" v-for="one in menus" :key="one.id">
      <mt-cell v-for="two in one.menus" :key="two.id" :title="two.name" @click="$router.push(two.href)"></mt-cell>
    </mt-index-section>
  </mt-index-list>
</template>

<script>
  export default {
    data() {
      return {
       menus:[]
      };
    },
    methods: {
    },
    created:function () {
      let $this=this;
      $this.menus=JSON.parse(sessionStorage.getItem("user")).menus

    }
  };
</script>
<style>

</style>
