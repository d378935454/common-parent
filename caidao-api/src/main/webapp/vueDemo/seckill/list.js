/**
 * Created by yuzhou on 16/9/5.
 */

define(['vue', 'text!tpl/seckill/list.html', 'service/seckillService'], function (Vue, listHtml, seckillService) {

    return Vue.extend({
        template: listHtml,
        data: function () {
            return {
                seckillList:[]
            }
        },
        mounted:async function(){
            var $this=this;
            // $this.$http.get('/seckill.htmls')
            //     .then(response => {
            //         debugger;
            //         if (response.code) {
            //             $this.seckillList= response.body
            //         } else {
            //             alert(response.data);
            //             return []
            //         }
            //         return (response.json())
            //     }).catch(err=>{
            //         console.log(err);
            // })
            $this.seckillList=await seckillService.all();
            debugger
        },
        methods: {
            showDetail : function (seckill) {
                this.$router.go({
                    name: 'seckillDetail',
                    params: { seckillId: seckill.id }
                })
            }

        }
    })
});