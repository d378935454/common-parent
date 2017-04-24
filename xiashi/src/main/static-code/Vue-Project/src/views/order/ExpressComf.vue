<template>
  <div class="container-fluid">
    <form class="row">
      <div class="form-group">
        <label >订单号</label>
        <input type="text" :value="order.orderNo"  class="form-control"
               readonly>
      </div>
      <div class="form-group">
        <label >预计取货地点</label>
        <input type="text" :value="order.oldGetDate"  class="form-control"
               readonly>
      </div>
      <div class="form-group">
        <label >预计取货日期</label>
        <input type="text" :value="order.oldGetDate"  class="form-control"
               readonly>
      </div>
      <div class="form-group">
        <label >预计到货地点</label>
        <input type="text" :value="order.sendAddress"  class="form-control"
               readonly>
      </div>
      <div class="form-group">
        <label >预计到货日期</label>
        <input type="text" :value="order.sendDate"  class="form-control"
               readonly>
      </div>
      <div v-for="(item,index) in order.orderInfos" :key="order.orderInfos.id">
        <div class="form-group">
          <div class="col-xs-12">
            <label>产品名{{index + 1}}</label>
          </div>
          <input type="text" v-model="item.goodsInfo.name" class="form-control" readonly>
        </div>

        <div class="form-group">
          <label>数量</label>
          <input type="number" v-model="item.allNum" class="form-control" readonly>
        </div>
      </div>
      <button type="button" @click="onSubmit" class="btn btn-primary">确认</button>
    </form>
  </div>
</template>
<script>
    export default{
        data(){
            return {
              id:"",
              order: {}
            }
        },
      computed:{
      },
      created:function () {
        let $this=this
        $this.$store.dispatch('updateTitle', "确认物流单")
        $this.id=$this.$route.params.id
        $this.getOrderById($this.id)
          .then(res=>{
            $this.order=res.data.data
          })
      },
      methods:{
        onSubmit() {
          let $this = this
          $this.http.post('order/updateStateById?id='+$this.id+'&stateType=WAITCHECK&$state=REPOCONF')
            .then(response => {
              alert("物流确认完成")
//            $this.$router.go(-1)
              $this.$router.push({path: "/main"})
            })
            .catch(error => {
              console.log(error)
              $this.logining = false
              alert("服务器异常")
            })
        },
        getOrderById:function (id) {
          let $this=this
          return $this.http.get("order/getOrderById?id="+$this.id)
        }
      }
    }
</script>
<style lang="scss" scoped>

</style>
