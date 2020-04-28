<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2018/12/24
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册新用户</title>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="\resources\layui\layui.all.js"></script>
    <script src="\resources\jquery-3.1.0.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="\resources\layui\css\layui.css">

    <style>
        html,body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }
        .content {
            width: 400px;
            height: 500px;
            margin: 0 auto; /*水平居中*/
            background: #FFF;
            position: relative;
            top: 50%; /*偏移*/
            margin-top: -251px;
            border: 1px solid #F2F2F2;
        }
    </style>
</head>
<body style="background:#f1f1f1">

    <div class="content">
        <div class="head" style="text-align: center; font-size: 20px; margin-top:8px;">
            注册爱分享通行证
        </div>

        <form action="/login/register/addUser" method="post">
            <div class="layui-form-item" style="margin-left: -33px; margin-top: 15px; width: 400px">
                <label class="layui-form-label">用户名:</label>
                <div class="layui-input-block">
                    <input type="text" name="userName" lay-verify="title" autocomplete="off" placeholder="(必填项)请输入用户名" class="layui-input" autofocus>
                </div>
            </div>
            <div class="layui-form-item" style="margin-left: -33px; margin-top: 15px; width: 400px">
                <label class="layui-form-label">密码:</label>
                <div class="layui-input-block">
                    <input type="password" name="userPassword" lay-verify="title" autocomplete="off" placeholder="(必填项)请输入密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="margin-left: -33px; margin-top: 15px; width: 400px">
                <label class="layui-form-label">邮箱:</label>
                <div class="layui-input-block">
                    <input type="text" name="userEmail" lay-verify="title" autocomplete="off" placeholder="请输入邮箱" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="margin-left: -33px; margin-top: 15px; width: 400px">
                <label class="layui-form-label">电话:</label>
                <div class="layui-input-block">
                    <input type="tel" name="userTel" lay-verify="title" autocomplete="off" placeholder="请输入电话号码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="margin-left: -33px; margin-top: 15px; width: 400px">
                <label class="layui-form-label" style="margin-top: -8px;">性别:</label>
                <div class="layui-input-block" style="margin-top:5px">
                    <input type="radio" name="userSex" value="男" title="男" checked="">男
                    <input type="radio" name="userSex" value="女" title="女">女
                    <input type="radio" name="userSex" value="其他" title="其他">其他
                </div>
            </div>

            <div class="layui-form-item" style="margin-top: 50px; margin-left: 20px">
                <button class="layui-btn layui-btn-radius layui-btn-sm" lay-submit="">提交</button>
                <button type="reset" class="layui-btn layui-btn-radius layui-btn-sm">重置</button>
            </div>

            <div style="margin-left: 272px; margin-top:36px;">
                <a href="/login/loginPage">
                    ← 返回到登录页面
                </a>
                <br><a href="/homePage">
                    ← 返回到爱分享
                </a>

                <span hidden id="msg">${msg}</span>
                <span hidden id  = "nameNull">${nameNull}</span>
            </div>
        </form>
    </div>

</body>

<script>
    layui.use('form',function(){
        var form = layui.form;

        //刷新界面 所有元素

        form.render();

    });

    //弹出层
    var msg = $("#msg").text();
    if (msg.length != "") {
        layer.msg(msg, {icon: 5});
        $("#msg").text("");
    }

</script>
</html>
