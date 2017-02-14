<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="Loading/css/newton.css">
    <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/select2.min.css" rel="stylesheet"/>
    <style type="text/css">
        .red {
            background-color: red;
        }

        .white {
            background-color: beige;
        }
    </style>
</head>
<body>

<div class="wapper">
    <div class="newtonBall-base"></div>
    <div id="newtonBall-left" class="newtonBall-left"></div>
    <div id="newtonBall-right" class="newtonBall-right"></div>
</div>

<h1>Common Page</h1>

<p>每个人都能访问的页面.</p>

<%--<span class="glyphicon glyphicon-search"></span>--%>
<a href="../spring3-security-integration/main/admin"> Go AdminPage </a>
<br/>
<a onclick='windows.location.href=$(this).prop("href");$(this).prop("href","javascript:")' href="javascript:">a测试</a>

<form id="uploadForm" action="machine/upStockExcel.htmls" method="post" enctype="multipart/form-data">
    库存Excel：<input type="file" name="file" multiple="multiple"><input value="tijiao" type="submit">
</form>
<input type="button" value="导入库存" onclick="upExcel();">

<input type="button" value="增加页" onclick="adddiv()">

<div id="titles">
</div>
<div id="table">

</div>
<input type="text" disabled id="text" value="1">
<select id="sle" style="width:20em">
    <option>哈哈</option>
    <option>妈妈</option>
    <option>爸爸</option>
    <option>爸妈</option>
</select>

<div id="app">
    <span v-bind:title="message">
    Hover your mouse over me for a few seconds to see my dynamically bound title!
  </span>
    <%--<ol>--%>
    <%--<li v-for="todo in todos">--%>
    <%--{{ todo.text }}--%>
    <%--</li>--%>
    <%--</ol>--%>
    <todo-item v-for="item in todos" v-bind:todo="item"></todo-item>
</div>
</body>
<%--<a href="pages/div1.html">跳到主页面</a>--%>

<script src="js/vue/vue.js"></script>
<script src="js/jquery/jquery-2.0.3.min.js"></script>
<script src="js/select2.min.js"></script>
<%--<script src="http://code.angularjs.org/angular-1.0.1.min.js"></script>--%>
<script src="js/bootstrap/js/bootstrap.min.js"></script>
<script>
    var token = "asdad";
    $(document).ajaxComplete(function () {
//        alert("asdasd");
    });
    //    Vue.use(VeeValidate);
    $(document).ready(function () {
        $('#sle').select2();
    });
    Vue.config.devtools = true;
    Vue.component("todo-item", {
        props: ['todo'],
        template: '<li>{{todo.text}}</li>'
    });

    var app = new Vue({
        el: '#app',
        data: {
            message: 'You loaded this page on ' + new Date(),
            todos: [
                {text: 'Learn JavaScript'},
                {text: 'Learn Vue'},
                {text: 'Build something awesome'}
            ]
        }
    });
    function adddiv() {
        debugger;
        var l = $("#titles").find("button").length;
        var titles = $("#titles");
        var $table = $("#table");
        titles.append(
                $("<button type='button' >").addClass("white").prop("id", "title" + l).attr("index", l).append($div[l])
        );
        $table.append($("<div>").prop("id", "body" + l).append("第" + $div[l] + "页内容"));
        $("#title" + l).click(
                function () {
                    clickdiv(this)
                }
        ).click();

    }
    function clickdiv(e) {
        var $this = $(e);
        var index = $this.attr("index");
        $this.removeClass("red").addClass("white").siblings().removeClass("white").addClass("red");
        $("#body" + index).show().siblings().hide();
    }
    var $div = ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十"];

    function upExcel() {
        var formData = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: 'machine/upStockExcel.htmls',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            dataType: "json",
            success: function (returndata) {
//                alert(returndata);
            },
            error: function (returndata) {
//                alert(returndata.responseText);
            }
        });
    }
    function hehe() {
        $.ajax({
                    url: "ZY.htmls?vimid=0002",
                    contentType: "application/json",
                    type: 'POST',
                    data: 'hgICBQJBMw02ODAyMjE4MTExMTEwDOaWsOaYjOicnOaiqARudWxsAzQ0MAEyDjIwMTYwODA1MTUzMjAwATABMQ==',
                    success: function (data) {
                    }
                }
        )
    }
    function hehe1() {
        $.ajax({
                    url: 'machine/test.htmls',
                    contentType: "application/json",
                    type: 'POST',
                    data: JSON.stringify({aaa: "aaa", bbb: "bbbb"}),
                    dataType: "json",
                    beforeSend: function (request) {                       //添加请求响应头中的自定义token
                        request.setRequestHeader("auth_token", token);
                    },
                    success: function (data) {
                    }
                }
        )
    }
    function getToken() {
        $.ajax({
            url: 'machine/getToken.htmls',
            contentType: "application/json",
            type: 'POST',
            data: JSON.stringify({aaa: "aaa", bbb: "bbbb"}),
            dataType: "json",
            success: function (data) {
                debugger;
                token = data.body;
            }
        })
    }
    function a() {
        alert(token)
    }
    var name = "world";
    $(window).load(function () {
        console.log(name);
        if (typeof name === "undefined") {
            var name = "aaa";
            console.log(name)
        } else {
            console.log(name)
        }
    });
</script>
</html>
