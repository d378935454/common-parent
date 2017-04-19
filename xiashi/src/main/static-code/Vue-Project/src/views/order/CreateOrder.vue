<template>
  <div class="container-fluid">
    <form class="row">
      <div class="form-group">
        <label for="order_no">编号</label>
        <input type="text" v-model="order.orderNo" class="form-control" id="order_no" placeholder="">
      </div>
      <div class="form-group">
        <label for="send_date">预计到货日期</label>
        <input type="text" :value="order.sendDate" @click="openPicker('send_date')" class="form-control" id="send_date"
               readonly>
        <mt-datetime-picker
          type="datetiem"
          ref="send_date"
          @confirm="confirm">
        </mt-datetime-picker>
      </div>
      <div class="form-group">
        <label for="send_address">预计到货地点</label>
        <input type="text" v-model="order.sendAddress" class="form-control" id="send_address" placeholder="">
      </div>
      <div class="form-group">
        <label for="express_no">物流单号</label>
        <input type="text" v-model="express.expressNo" class="form-control" id="express_no" placeholder="">
      </div>
      <div class="form-group">
        <label for="people_name">物流联系人</label>
        <input type="text" v-model="express.peopleName" class="form-control" id="people_name" placeholder="">
      </div>
      <div class="form-group">
        <label for="mobile">物流联系人电话</label>
        <input type="text" v-model="express.mobile" class="form-control" id="mobile" placeholder="">
      </div>
      <div class="form-group">
        <label for="old_senddate">物流取货时间</label>
        <input type="text" @click="openPicker('old_senddate')" v-model="express.oldSenddate" class="form-control"
               id="old_senddate" readonly>
        <mt-datetime-picker
          type="datetiem"
          ref="old_senddate"
          @confirm="confirm">
        </mt-datetime-picker>
      </div>
      <div class="form-group">
        <label for="startdate">物流送货时间</label>
        <input type="text" @click="openPicker('startdate')" v-model="express.startdate" class="form-control"
               id="startdate" readonly>
        <mt-datetime-picker
          type="datetiem"
          ref="startdate"
          @confirm="confirm">
        </mt-datetime-picker>
      </div>
      <div v-for="(item,index) in order.orderInfos">
        <div class="form-group">
          <div class="col-xs-12">
            <label>产品名{{index + 1}}</label>
            <button style="float: right" type="button" @click="order.order_info.splice(index,1)"
                    class="btn btn-warning">删除
            </button>
          </div>
          <Select v-model="item.goodsInfo.id" filterable>
            <Option v-for="item in goodsInfoList" :value="item.id" :key="item">{{ item.name }}</Option>
          </Select>
        </div>

        <div class="form-group">
          <label>数量</label>
          <input type="number" v-model="item.allNum" class="form-control" placeholder="">
        </div>
      </div>
      <div style="text-align: center">
        <button type="button" @click="order.orderInfos.push({goodsInfo: {id:''}, allNum: ''})"
                class="btn btn-info">
          添加产品
        </button>
      </div>
      <button type="button" @click="onSubmit" class="btn btn-primary">保存</button>
    </form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        goodsInfoList: [],
        picker: "",
        selected: "",
        order: {
          orderNo: "",
          sendDate: "",
          sendAddress: "",
          orderInfos: [
            {
              goodsInfo: {
                id: ""
              }, allNum: ""
            }
          ],
        },
        express: {
          expressNo: "",
          peopleName: "",
          mobile: "",
          oldSenddate: "",
          startdate: ""
        }
      }
    },
    created: function () {
      let $this = this
      $this.$store.dispatch('updateTitle', "采购单输入")
      $this.http.get('mobile/goods/get-all-goods-info',)
        .then(response => {
          $this.goodsInfoList = response.data.data
        }).catch(error => {
        console.log(error)
        $this.logining = false
        alert("服务器异常")
      })
    },
    computed: function () {

    },
    methods: {
      onSubmit() {
          let $this=this
        $this.http.post('mobile/order/create-order',{order:$this.order,express:$this.express})
          .then(response => {
            debugger
          })
          .catch(error => {
            console.log(error)
            $this.logining = false
            alert("服务器异常")
          })
        console.log('submit!');
      },
      confirm: function (time) {
        time = this.util.formatDate.format(time, "yyyy-MM-dd hh:mm:ss")
        let $this = this
        switch ($this.picker) {
          case "send_date":
            $this.order.sendDate = time
            break;
          case "old_senddate":
            $this.express.oldSenddate = time
            break;
          case "startdate":
            $this.express.startdate = time
            break;
        }
      },
      openPicker(picker) {
        this.$refs[picker].open();
        this.picker = picker
      }
    },

  }

</script>
