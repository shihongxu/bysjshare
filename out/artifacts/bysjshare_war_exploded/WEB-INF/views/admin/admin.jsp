<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2019/5/2
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="\resources\layui\layui.all.js"></script>
    <%--<script src="\resources\myjs\initarticle1.js"></script>--%>
    <script src="\resources\layui\layer-v3.1.1\layer-v3.1.1\layer\layer.js"></script>
    <script src="\resources\layui\lay\modules\element.js"></script>
    <script src="\resources\jquery-3.1.0.min.js" type=" text/javascript"></script>
    <link rel="stylesheet" href="\resources\layui\css\layui.css" media="all">
</head>
<body>


<div class="layui-header" id="leftdh" style="position:fixed;width: 100%;">
    <ul class="layui-nav layui-bg-cyan">
        <li class="layui-nav-item" id="fristpage"><a href="/homepage">首页</a></li>
        <li class="layui-nav-item" id="allarticle">
            <a href="javascript:;">全部文章</a>
            <dl class="layui-nav-child">
                <dd><a href ="/slarticletp?flagpage=1">经典文学</a>
                <dd><a href ="/slarticletp?flagpage=2">网络小说</a>
                <dd><a href ="/slarticletp?flagpage=3">软件工具</a></dd>
                <dd><a href ="/slarticletp?flagpage=4">免费资源</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item" id="myshare"><a href="/myshare/mypublish">我要分享</a></li>
        <li class="layui-nav-item" id="blogroll"><a href="https://portal.qiniu.com/bucket/image/index">友情链接</a></li>
        <li class="layui-nav-item" id="track"><a href="/myarticle/myarticle">我的足迹</a></li>
        <li class="layui-nav-item" id="contactauthor">
            <a href="javascript:;">联系本站作者</a>
            <dl class="layui-nav-child">
                <dd><a href="/contaauthor/contaauthorwechat">微信</a></dd>
            </dl>
        </li>
    </ul>
    <c:if test="${tokenUser.userName == null}">
        <ul class="layui-nav layui-layout-right layui-bg-cyan">
            <li class="layui-nav-item">
                <a onclick="login()">
                    登录
                </a>
            </li>
            <li class="layui-nav-item">
                <a onclick="register()">
                    注册
                </a>
            </li>
        </ul>
    </c:if>
    <c:if test="${tokenUser.userName != null}">
        <ul class="layui-nav layui-layout-right layui-bg-cyan">
            <li class="layui-nav-item">
                <a href="/percenter/percenterpage">
                        ${tokenUser.userName}
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="/logout">
                    退出
                </a>
            </li>
        </ul>
    </c:if>
</div>
<br>
<br>
<br>

<%--左侧导航模块--%>
<div class="leftdh" id="dhleft" style="width: 200px;height:350px;margin-top:10px;">
    <ul class="layui-nav layui-nav-tree layui-inline layui-bg-cyan" lay-filter="demo" style="margin-right: 10px;">
        <li class="layui-nav-item layui-nav-itemed">
            <a href="javascript:;">后台管理</a>
            <dl class="layui-nav-child">
                <dd id="yhgl"><a href="/percenter/percenterpage">用户管理</a></dd>
                <dd id="article"><a href="/admin/articlestatistics">文章内容统计</a></dd>
            </dl>
        </li>
    </ul>
</div>

<%--中间表格模块--%>
<div id="centerbg" style=" width:86.35%; height: 500px; margin-left: 204px;">
    <div class="centerHead" style="height:40px;">
        <div style="margin-top:4px;margin-left: 10px;">
            <span style="font-size: 18px;color: #2F4056;">用户管理</span>
        </div>
    </div>
    <div class="datagrid">
        <table class="layui-hide" id="test"></table>

    </div>


</div>

</body>

<script>
    layui.use('element', function(){

        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        element.init();//如果不加上这个二级菜单就不会显示

        //监听导航点击
        element.on('nav(demo)', function(elem){
            layer.msg(elem.text());
        });

    });

    //设置表格模块的位置
    var leftdhHeight = document.getElementById("dhleft").offsetHeight;
    var centerbgHeigth = document.getElementById("centerbg");
    centerbgHeigth.style.marginTop=-leftdhHeight;

</script>

<script src="//res.layui.com/layui/dist/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use('table', function(){
        var table = layui.table
            ,form = layui.form;

        table.render({
            elem: '#test'
            ,url:'/admin/geruserdata'
            ,cellMinWidth: 80
            ,toolbar: '#toolbarDemo'
            ,limit:3
            ,cols: [[
                {type:'numbers'}
                ,{type: 'checkbox'}
                ,{field:'userName', title:'用户名', width:150, unresize: true, sort: true}
                ,{field:'userEmail', title:'用户邮箱', templet: '#usernameTpl'}
                ,{field:'userTel', title: '联系电话', minWidth:120, sort: true}
                ,{field:'date', title:'注册时间', sort: true}
                ,{field:'userRole', title:'用户权限'}
                ,{field:'userSex', title:'性别', width:85}
                ,{field:'userFlag', title:'用户状态', width:110}
                ,{field:'articleCount', title:'分享文章条数'}
                ,{field:'commentCount', title:'评论条数'}
            ]]
            ,page: true//开启分页
        });
    });

    var flag = '${flag}';
    if (flag == 1){
        document.getElementById("yhgl").className += " layui-this";
    }if (flag == 2){
        document.getElementById("article").className += "layui-this";
    }


</script>


</html>
