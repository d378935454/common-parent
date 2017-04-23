<template>
  <div class="container-fluid">
      <mt-cell v-for="i in orderList" :title="i.orderNo"  :key="i.id" label="描述信息" is-link :to="'RepoComf/'+i.id"></mt-cell>
  </div>
</template>
<script>
  export default{
    data(){
      return {
          type:"",
        orderList: []
      }
    },
    created: function () {
        let $this=this
      $this.$store.dispatch('updateTitle', "确认采购单")
      $this.type=$this.$route.params.type
      $this.getOrderListByType($this.type)
        .then(res=>{
          $this.orderList=res.data.data
        })
    },
    methods:{
        getOrderListByType:function (type) {
          let $this=this
          return $this.http.get("order/getOrderListByType?type="+$this.type)

        }
    }
  }
</script>
<style lang="scss" scoped>

</style>
