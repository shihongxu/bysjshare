<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2018/12/24
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
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
            width: 300px;
            height: 300px;
            margin: 0 auto; /*水平居中*/
            background: #FFF;
            position: relative;
            top: 50%; /*偏移*/
            margin-top: -200px;
            border: 1px solid #F2F2F2;
            }
        </style>
    </head>
<body style="background:#f1f1f1">
<div class="content">
    <div class=“box1” style="margin: 0 auto;">
        <form class="layui-form" action="/login/loginUser" method="post">
            <div class="layui-form-item" style="margin-top:30px">
                <div class="layui-input-block" style="margin-left: 20px">
                    <span style="color: #72777c">用户名</span><br>
                    <input style="height: 30px; width: 250px; margin-top: 5px" type="text" name="userName" lay-verify="title" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                </div>
                <div class="layui-input-block" style="margin-top: 10px; margin-left: 20px">
                    <span style="color: #72777c">密  码</span><br>
                    <input style="height: 30px; width: 250px; margin-top: 5px" type="password" name="userPassword" lay-verify="title" autocomplete="off" placeholder="请输入密码" class="layui-input">
                </div>
                <div class="layui-form-item" style="margin-top: 50px; margin-left: 20px">
                    <button class="layui-btn layui-btn-radius layui-btn-sm" lay-submit="">登录</button>
                    <button type="reset" class="layui-btn layui-btn-radius layui-btn-sm">重置</button>
                    <button type="button" class="layui-btn layui-btn-radius layui-btn-sm" onclick="register()">注册</button>
                </div>

            </div>
        </form>
    </div>
</div>
<div style="margin-left: 812px;margin-top:380px">
    <a href="#">
        忘记密码？
    </a>
    <br><a href="/homePage">
    ←返回到爱分享
    </a>
</div>
<span hidden id="msg">${msg}</span>
<span hidden id  = "nameNull">${nameNull}</span>
</body>

<script>

    //注册点击事件
    function register(){
        location.href="/login/register/registerPage";//当前页刷新
        //window.open("/login/register");//重新打开一个页面
    }

    //弹出层
    var msg = $("#msg").text();
    if (msg.length != "") {
        layer.msg(msg, {icon: 5});
        $("#msg").text("");
    }
</script>
</html>
