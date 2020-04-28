<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2018/12/19
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留下足迹 | 爱上分享</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="\resources\jquery-3.1.0.min.js" type=" text/javascript"></script>

    <script src="\resources\layui\layui.all.js"></script>
    <%--<script src="\resources\myjs\initarticle1.js"></script>--%>
    <script src="\resources\layui\layer-v3.1.1\layer-v3.1.1\layer\layer.js"></script>
    <script src="\resources\layui\lay\modules\element.js"></script>
    <link rel="stylesheet" href="\resources\layui\css\layui.css" media="all">

    <style>
        a.ared:hover
        {
            color:red;
        }
    </style>
</head>
<body>
<div class="layui-header" style="position:fixed;width: 100%;">
    <ul class="layui-nav layui-bg-green">
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
        <%--<li class="layui-nav-item" id="aboutweb">--%>
            <%--<a href="javascript:;">关于本站</a>--%>
            <%--<dl class="layui-nav-child">--%>
                <%--<dd><a href="">常见问题</a></dd>--%>
                <%--<dd><a href="">投放广告</a></dd>--%>
                <%--<dd><a href="">关于作者</a></dd>--%>
            <%--</dl>--%>
        <%--</li>--%>
        <li class="layui-nav-item" id="contactauthor">
            <a href="javascript:;">联系本站作者</a>
            <dl class="layui-nav-child">
                <dd><a href="/contaauthor/contaauthorwechat">微信</a></dd>
            </dl>
        </li>
    </ul>
    <c:if test="${tokenUser.userName == null}">
        <ul class="layui-nav layui-layout-right layui-bg-green">
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
        <ul class="layui-nav layui-layout-right layui-bg-green">
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

<%--<div onclick="downloadExcel()">--%>
    <%--导出Excel--%>
<%--</div>--%>

<script>
    layui.use('element', function(){

        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        element.init();//如果不加上这个二级菜单就不会显示

        //监听导航点击
        element.on('nav(demo)', function(elem){
            layer.msg(elem.text());
        });

    });

    function flag(){
        var flag = "${pageFlag}";
        if(flag == "全部文章"){
            document.getElementById("allarticle").className += ' layui-this';
        }if(flag == "我要分享"){
            document.getElementById("myshare").className += ' layui-this';
        }if(flag == "友情链接"){
            document.getElementById("blogroll").className += ' layui-this';
        }if(flag == "我的分享"){
            document.getElementById("track").className += ' layui-this';
        }if(flag == "关于本站"){
            document.getElementById("aboutweb").className += ' layui-this';
        }if(flag == "联系作者"){
            document.getElementById("contactauthor").className += ' layui-this';
        }if(flag == "首页"){
            document.getElementById("fristpage").className += ' layui-this';
        }
    }
    window.onload=flag();


</script>

<script type="text/javascript">

    //登录点击事件
    function login(){
        window.open("/login/loginPage");
    }
    //注册点击事件
    function register(){
        window.open("/login/register/registerPage");
    }

    // //导出Excel
    // function downloadExcel(){
    //     window.open("/homePage/exportExcel");
    // }
</script>

<script>

    //立即执行函数
    function articleData(page1, pageSize1, selectArticleName, articleType){

        $.ajax({
            url:"/homepage/selectarticledata",
            type:"get",
            data:{
                'page' : page1,
                'size' : pageSize1,
                'articleName' : selectArticleName,
                'articleType' : articleType
            },
            async : false,
            success : function (data) {
                var article = document.getElementById('harticle');
                var html = "";
                for (var i = 0; i < data.length; i++){
                    // console.log(data);
                    // console.log("===============:" + data[i].photoUrl2.length);
                    // console.log("+++++++++++++++:" + article);
                    html += "<div style='width : 714px; margin-left: 50px; margin-top:30px;'>";
                    // html += "<h2 style=''>" + "\xa0" + data[i].articleName + "</h2>";

                    //添加标题
                    html += "<h2 style='font-size: 24px;font-weight: bold;line-height: 24px;height: 24px; margin-bottom: 4px; overflow: hidden;white-space: nowrap;margin-top:5px;'>" + "\xa0" + "<a class='ared' href="+ "/article/articletitle?articleUuid=" + data[i].articleUuid  + ">" + data[i].articleName + "</a></h2>";

                    //添加文章创建时间和 作者
                    var date = new Date(data[i].articleBeginDate);
                    html += "<br/><p style='font-size: 13px; color: #4f4f4f; font-weight: 400;line-height: 26px;margin: -18px 0 -9px;overflow-x: auto;'>"+ "" + "文章作者:" + data[i].userName + "\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0" + "创建时间:" + date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() +
                        "\xa0\xa0\xa0\xa0\xa0\xa0" + "文章类型:" + data[i].articleType + "\xa0\xa0\xa0\xa0\xa0\xa0" +"阅读:"+ data[i].viewsNumber + "次" + "\xa0\xa0\xa0\xa0\xa0\xa0" + "评论:" + data[i].commentNumber + "次" + "</p>" ;

                    html += "<hr/>";

                    //添加图片
                    html += "<div style='margin-bottom: 5px;'>";
                    if(data[i].photoUrl2 != null){
                        if(data[i].photoUrl2.length == 4){
                            for(var j = 0; j < data[i].photoUrl2.length; j ++){
                                html += "<img style='width: 350px; height: 250px; float: left; margin-left:5px; margin-top:5px;' src='" + data[i].photoUrl2[j] + "'/>";
                                if (j == 1 || j == 3){
                                    html += "<br/>";
                                }
                            }
                        }if (data[i].photoUrl2.length == 3){
                            for(var j = 0; j < data[i].photoUrl2.length; j ++){
                                if (j < 2) {
                                    html += "<img style='width: 350px; height: 250px; float: left; margin-left:5px; margin-top:5px;' src='" + data[i].photoUrl2[j] + "'/>";
                                }else{
                                    html += "<br/>";
                                    html += "<img style='width: 705px; height: 250px; float: left; margin-left:5px; margin-top:5px;' src='" + data[i].photoUrl2[j] + "'/>";
                                }
                            }
                        }if(data[i].photoUrl2.length == 2){
                            for(var j = 0; j < data[i].photoUrl2.length; j ++){
                                html += "<img style='width: 350px; height: 250px; float: left; margin-left:5px; margin-top:5px;' src='" + data[i].photoUrl2[j] + "'/>";

                            }                        }
                        if (data[i].photoUrl2.length == 1){
                            // console.log("5555555555555555555555555:" + data[i].photoUrl2[0].length);
                            html += "<img style='width: 705px; height: 250px; float: left; margin-left:5px; margin-top:5px;' src='" + data[i].photoUrl2[0] + "'/>";
                        }
                    }

                    html += "</div>";

                    //添加文章内容
                    html += "<hr/><hr/><p style='font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px;margin: 0 0 16px;overflow-x: auto;'>" + "\xa0\xa0\xa0\xa0" + data[i].articleContents +"</p>";

                    html += "</div>";

                }

                //添加分页
                var weibu = "<div style='width: 450px; height: 25px; margin-left: 258px;margin-top:5px;margin-bottom: 30px; display:inline-block'><div style='width:35px; height:20px; display:inline-block; margin-bottom: 4px;' onclick='backPage("+page1 +","+ pageSize1+ "," + "" +")'><img style='width: 35px; height: 20px; display:inline-block; margin-bottom: 4px;' src='" + "/resources/image/上一页小图标.jpg" + "'/></div>";
                weibu += "<span>\xa0\xa0\xa0"+ page1 +"\xa0\xa0\xa0</span><div style='height: 20px;  display:inline-block;margin-bottom: 4px;' onclick='nextPage("+page1+","+ pageSize1+ "," + "" +")'><img style='width: 35px; height: 20px; display:inline-block; margin-bottom: 4px;' src='"+ "/resources/image/下一页小图标.jpg" +"'/></div>";
                weibu += "<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px;'> \xa0\xa0\xa0" + "第:\xa0\xa0" + "<input id='selectpage' type='text' onkeyup=\"this.value=this.value.replace(/\\D|^0/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D|^0/g,'')\" style='display:inline-block;width: 40px; height: 20px; ' placeholder=\"1\" min=\"1\" max=\""+ (data[0].total)/10+ " \"/>" + "\xa0\xa0页\xa0\xa0"+"<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px; margin-bottom: 2px;'><button class='layui-btn layui-btn-sm layui-btn-normal' style='background: #009688;' type='button' onclick='selectPage("+page1+","+ pageSize1+ "," + "" +")'>"+ "确定" +"</button></div></div>";
                if (data.length == 0){
                    weibu += "<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px;'>"+ "\xa0\xa0\xa0共:"+ 0 + "条" + "</div>";
                }else{
                    weibu += "<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px;'>"+ "\xa0\xa0\xa0共:"+ data[0].total + "条" + "</div>";
                }

                weibu += "</div>";

                html += weibu;

                // var height = article.offsetHeight;//获取article的高度
                // var hright = document.getElementById("hright");
                // hright.style.marginTop = '-' + height + "px";
                article.innerHTML = html;
            },
        });

        //获取最新的文章标题、时间、文章类型
        $.ajax({
            url : "/homepage/newarticle",
            async : false,
            type : "get",
            // dataType : "json",
            success : function (data) {
                console.log("newArticle--------:" + data[0].articleName);

                var newArticle = document.getElementById('newArticle');
                var html = "";

                for(var i = 0; i < data.length; i ++){
                    html += "<div style='margin-left : 53px; margin-top:10px;'>" + "<a class='ared' href=" + " /article/articletitle?articleUuid=" + data[i].articleUuid + ">" + data[i].articleName + " - " + "<span style='font-size: 13px;'>" + data[i].userName + "</span>" + "</a>" + "</div>";
                }
                newArticle.innerHTML = html;
            },
        });


    }
</script>
</body>
</html>
