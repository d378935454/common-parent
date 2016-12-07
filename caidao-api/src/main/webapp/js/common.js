/**
 * Created by bean on 2016/5/18.
 */
var hashMapping = [
    {value: "#/div1.html", tagertUrl: "../div1.html"}
]
$(function () {
    $(window).on("hashchange load", function () {
        var hash = hashMapping.filter(function (val) {
            return val.value === window.location.hash.split("?")[0];
        })
        if (hash[0]) {
            $("#page_load").load(hash[0].tagertUrl)
        }
    })
})
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var rs = window.location.href.split("?");
    var r = rs[rs.length - 1];
    if (!r) {
        return null;
    }
    r = r.match(reg);
    if (r != null) {
        return decodeURI(r[2])
    }
    return null;
}
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] != undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || "");
        } else {
            o[this.name] = this.value || "";
        }
    });
    return o;
}

function a() {
    var a = $(this);
    var b = a.parent();
    if (0 != b.closest("#choose-color").length || !b.is(".disabled") && !b.is(".selected")) {
        b.parent().find(".selected").attr("class", "item"), b.attr("class", "item selected");
        var c = x.getSelectedColorSizeSkuId();
        c > 0 ? window.location = c + ".html" : x.changeColorSize(!1)
    }
}
