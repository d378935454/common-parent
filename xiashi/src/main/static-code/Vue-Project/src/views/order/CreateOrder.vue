<template>
  <div class="container-fluid">
    <form class="row">
      <div class="form-group">
        <label for="order_no">编号</label>
        <input type="text" class="form-control" id="order_no" placeholder="">
      </div>
      <div class="form-group">
        <label for="send_date">预计到货日期</label>
        <input type="text" :value="send_date" @click="openPicker('send_date')" class="form-control" id="send_date"
               readonly>
        <mt-datetime-picker
          type="datetiem"
          ref="send_date"
          @confirm="confirm">
        </mt-datetime-picker>
      </div>
      <div class="form-group">
        <label for="send_address">预计到货地点</label>
        <input type="password" class="form-control" id="send_address" placeholder="">
      </div>
      <div class="form-group">
        <label for="price">实际价格</label>
        <input type="number" class="form-control" id="price" placeholder="">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        order_no: "",
        send_date: "",
        picker: ""
      }
    },
    created: function () {
      this.$store.dispatch('updateTitle', "采购单输入")
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      confirm: function (time) {
        time = this.util.formatDate.format(time, "yyyy-MM-dd hh:mm:ss")
        let $this = this
        switch ($this.picker) {
          case "send_date":
            $this.send_date = time
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
