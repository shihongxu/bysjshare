<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2018/12/26
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>

    <style>
        .heade{
            width:300px;
            height: 300px;
            background:#FFF;
            margin:0 auto;
            position:relative;
            top:50%;
            margin-top:-150px;
            border: 1px solid #F2F2F2;

        }

    </style>
</head>
<body style="background:#f1f1f1">
<div class="heade">
    <div id = "successimage" style="position:absolute; top:10%; left:32%;">
        <img src="/resources/image/成功图标.png" style="height: 100px; width:100px;">
    </div>

    <div id = "success" style="position:absolute; margin-top: 56%; margin-left: 32%; font-size: 25px; color: #5FB878;">
        注册成功！
    </div>

    <div style="margin-left: 155px; margin-top:240px;">
        <a href="/login/loginPage" style="text-decoration:none; ">
            ← 返回到登录页面
        </a>
        <br><a href="/homePage" style="text-decoration:none;">
            ← 返回到爱分享
        </a>
    </div>
</div>

</body>
</html>
