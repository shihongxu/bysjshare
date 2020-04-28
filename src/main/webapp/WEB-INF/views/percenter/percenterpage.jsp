<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2019/3/13
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>个人中心</title>
    <style>

        .uploader-list {
            position: relative;
            margin-top: -25px;
            background-color: black;
            color: white;
            filter: alpha(Opacity=80);
            -moz-opacity: 0.5;
            opacity: 0.5;
            width: 100px;
            height: 25px;
            text-align: center;
            display: none;
        }

        .uploader-list {
            position: relative;
            background-color: black;
            color: white;
            filter: alpha(Opacity=80);
            -moz-opacity: 0.5;
            opacity: 0.5;
            width: 100px;
            text-align: right;
            height: 18px;
            margin-bottom: -18px;
            display: none;
        }

        .uploader-list .handle span {
            margin-right: 5px;
        }

        .uploader-list .handle span:hover {
            cursor: pointer;
        }

        .uploader-list {
            margin: 12px 0 0 15px;
            padding: 1px;
            float: left;
        }

    </style>
</head>
<body>
<jsp:include page="../public/head.jsp"></jsp:include>

<div  style="margin-left: 300px; position: relative;">
    <br><br><br><br>

    <%--个人资料编辑--%>
    <div id="percenterdata">
        <div style="border: 1px #009f95 solid; width: 400px; height: 600px; margin-left: 248px; margin-top: 13px;">
            <form action="/percenter/updatauser" method="post">
                <div class="layui-form-item" style="margin-left: -7px; margin-top: 15px; width: 400px">
                    <label class="layui-form-label">修改用户名:</label>
                    <div class="layui-input-block">
                        <input type="text" name="userName" lay-verify="title" autocomplete="off" placeholder="请输入用户名" class="layui-input" autofocus>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-left: -7px; margin-top: 15px; width: 400px">
                    <label class="layui-form-label">修改密码:</label>
                    <div class="layui-input-block">
                        <input type="password" name="newUserPassword" lay-verify="title" autocomplete="off" placeholder="请输入密码" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-left: -7px; margin-top: 15px; width: 400px">
                    <label class="layui-form-label">修改邮箱:</label>
                    <div class="layui-input-block">
                        <input type="text" name="userEmail" lay-verify="title" autocomplete="off" placeholder="请输入邮箱" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-left: -7px; margin-top: 15px; width: 400px">
                    <label class="layui-form-label">修改电话:</label>
                    <div class="layui-input-block">
                        <input type="tel" name="userTel" lay-verify="title" autocomplete="off" placeholder="请输入电话号码" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-left: -7px; margin-top: 15px; width: 400px">
                    <label class="layui-form-label" style="margin-top: -8px;">修改性别:</label>
                    <div class="layui-input-block" style="margin-top:5px">
                        <input type="radio" name="userSex" value="男" title="男" checked="">男
                        <input type="radio" name="userSex" value="女" title="女">女
                        <input type="radio" name="userSex" value="其他" title="其他">其他
                    </div>
                </div>


                <div class="layui-form-item" style="margin-left: -7px; margin-top: 15px; width: 400px">
                    <label class="layui-form-label" ><span style="color: red;">当前密码:</span></label>
                    <div class="layui-input-block">
                        <input type="password" name="userPassword" lay-verify="title" autocomplete="off" placeholder="请输入密码" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item" style="margin-top: 50px; margin-left: 20px">
                    <button class="layui-btn layui-btn-radius layui-btn-sm" lay-submit="">提交</button>
                    <button type="reset" class="layui-btn layui-btn-radius layui-btn-sm">重置</button>
                </div>

            </form>
        </div>

    </div>

</div>

<span hidden id="msg">${msg}</span>
</body>

<script>
</script>
<script>

    layui.use(['upload', 'laytpl', 'form'], function () {
        var $ = layui.jquery
            , upload = layui.upload
            , laytpl = layui.laytpl
            , form = layui.form;

        //由于页面是动态生成的，layUI的自动渲染就会失效，所以这时候需要手动渲染
        form.render();

    });

    //搜索选择框，，，加载
    layui.use('form', function () {
        var form = layui.form;
    });

    //弹出层
    var msg = $("#msg").text();
    if (msg.length != "") {
        layer.msg(msg, {icon: 6});
        $("#msg").text("");
    }
</script>
</html>
