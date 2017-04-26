<template>
  <div class="container-fluid">
    <form class="row">
      <div class="form-group">
        <label >订单号</label>
        <input type="text" :value="order.orderNo"  class="form-control"
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
          <label>计划数量</label>
          <input type="number" v-model="item.allNum" class="form-control" readonly>
        </div>
        <div class="form-group">
          <label>质检数量</label>
          <input type="number" v-model.number="item.checkNum" class="form-control" readonly>
        </div>
      </div>
      <div class="form-group">
        <label>上传收货凭证照片</label>
        <input type="file" accept="image/*"  ref="pic">
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
        $this.$store.dispatch('updateTitle', "上传凭证")
        $this.id=$this.$route.params.id
        $this.getOrderById($this.id)
          .then(res=>{
            $this.order=res.data.data
          })
      },
      methods:{
        onSubmit() {
          let $this = this
          let pic=$this.$refs.pic[0].files[0];
          let form = new FormData();
          form.append("file",pic)
          form.append("id",$this.id)
          form.append("orderNo",$this.order.orderNo)
          $this.http.post('order/upPic',form,
            {headers: {'Content-Type': 'multipart/form-data'}})
            .then(response => {
//              alert("质检完成")
//            $this.$router.go(-1)
//              $this.$router.push({path: "/main"})
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
