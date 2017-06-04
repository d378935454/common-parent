<template>
  <div class="container-fluid">
    <form class="row">
      <div class="form-group">
        <label>订单号</label>
        <input type="text" :value="order.orderNo" class="form-control"
               readonly>
      </div>
      <div class="form-group">
        <label>上传凭证照片</label>
        <img class="preview-img" style="width: 300px" v-for="(item, index) in img"  :src="item.src" @click="$preview.open(index, img)"/>
      </div>
      <div class="form-group">
        <label>送达时间</label>
        <input type="text" @click="openPicker('relSendDate')" v-model="order.relSendDate" class="form-control" readonly>
        <mt-datetime-picker
          type="datetime"
          ref="relSendDate"
          @confirm="confirm">
        </mt-datetime-picker>
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
        <div class="form-group">
          <label>送达数量</label>
          <input type="number" v-model.number="item.sendNum" class="form-control" >
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
        id: "",
        picker:"",
        order: {},
        img: []
      }
    },
    computed: {},
    created: async function () {
      let $this = this
      $this.$store.dispatch('updateTitle', "输入收货凭证信息")
      $this.id = $this.$route.params.id
      let res = await $this.getOrderById($this.id)
      $this.order = res.data.data
      $this.img=[{
        src: 'mobile/sosOutImg'+$this.order.picUrl,
        w: 600,
        h: 400
      }]
    },
    methods: {
      onSubmit() {
        let $this = this
        $this.$http.post('order/Over', $this.order)
          .then(response => {
              alert("输入收货凭证信息")
//            $this.$router.go(-1)
              $this.$router.push({path: "/main"})
          })
          .catch(error => {
            console.log(error)
            $this.logining = false
            alert("服务器异常")
          })
      },
      getOrderById: function (id) {
        let $this = this
        return $this.$http.get("order/getOrderById?id=" + id)
      },
      openPicker(picker) {
        this.$refs[picker].open();
        this.picker = picker
      },
      confirm: function (time) {
        time = this.util.formatDate.format(time, "yyyy-MM-dd hh:mm:ss")
        let $this = this
        switch ($this.picker) {
          case "relSendDate":
            $this.order.relSendDate = time
            break;
//          case "startdate":
//            $this.express.startdate = time
//            break;
        }
      }
    }
  }
</script>
<style lang="scss" scoped>

</style>
