<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2018/11/22
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello word!</title>
    <script src="\resources\jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="\resources\layui\lay\dest\layui.all.js"></script>
    <link rel="stylesheet" href="\resources\layui\css\layui.css">

</head>
<body>
    <div onclick="test()" style="width: 100px; height: 100px; background: red">
        test
    </div>

    <fieldset class="layui-elem-field site-demo-button" style="margin-top: 30px;margin-bottom: 30px;">
        <legend>按钮主题</legend>
        <div>
            <button class="layui-btn layui-btn-primary">原始按钮</button>
            <button class="layui-btn">默认按钮</button>
            <button class="layui-btn layui-btn-normal">百搭按钮</button>
            <button class="layui-btn layui-btn-warm">暖色按钮</button>
            <button class="layui-btn layui-btn-danger">警告按钮</button>
            <button class="layui-btn layui-btn-disabled">禁用按钮</button>
        </div>
    </fieldset>


</body>

<script>

    function test(){
        $.ajax({
            url : "/login",
            data:{'name': '张三'},
            type:"GET",
            dataType:"json",
            success :function (data) {
                alert("id:"+ data.id + "name:"+ data.name + "age:" + data.age );
            },

        });
    }

</script>
</html>
