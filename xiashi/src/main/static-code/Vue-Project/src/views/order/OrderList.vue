<template>
  <div class="container-fluid">
    <mt-cell v-for="i in orderList" :title="i.orderNo" :key="i.id" label="描述信息" is-link
             :to="to+i.id"></mt-cell>
  </div>
</template>
<script>
  export default{
    data(){
      return {
        type: "",
        to: "",
        orderList: []
      }
    },
    created: function () {
      let $this = this
      let tittle;
      switch ($this.$route.params.type) {
        case "CREATED":
          tittle = "确认采购单"
          $this.to='RepoComf/'
              break;
        case "REPOCONF":
          tittle = "确认物流单"
          $this.to='ExpressComf/'
          break;
        case "WAITCHECK":
          tittle = "质检订单"
          $this.to='Check/'
          break;
        case "CHECKED":
          tittle = "上传收货凭证照片"
          $this.to='UpPic/'
          break;
        case "UPPIC":
          tittle = "输入收货凭证信息"
          $this.to='Over/'
          break;
      }
      $this.$store.dispatch('updateTitle', tittle)
      $this.type = $this.$route.params.type
      $this.getOrderListByType($this.type)
        .then(res => {
          $this.orderList = res.data.data
        })
    },
    methods: {
      getOrderListByType: function (type) {
        let $this = this
        return $this.$http.get("order/getOrderListByType?type=" + $this.type)

      }
    }
  }
</script>
<style lang="scss" scoped>

</style>
