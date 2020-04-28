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

    <style>
        #hleft{
            position:fixed;
            margin-top: 5px;
            margin-left: 25px;
            width:300px;
            height:600px;
            float: left;
            /*border: 1px #009f95 solid;*/
        }
        .hcenter{
            /*width: 76.4%;*/
            width: 52%;
            margin-left:350px;
            /*border: 1px red solid;*/
        }

        .select{
            width: 800px;
            height:50px;
            margin-top:5px;
            margin-left:5px;
            /*border: 1px #009f95 solid;*/
        }
        .hright{
            position: absolute;
            /*margin-top: 100px;*/
            margin-left: 78%;
            height:850px;
            /*float:left;*/
            width:331px;
            /*border: 1px #009f95 solid;*/
            /*background: red;*/
        }

        .right2{
            background-image: url(/resources/image/支付宝红包扫一扫.jpg);
            background-position:center;
            background-repeat:repeat-y;
            background-size: cover;
        }


        .right3{
            background-image: url(/resources/image/微电系公众号.jpg);
            background-position:center;
            background-repeat:repeat-y;
            background-size: cover;
        }

        .right4{
            background-image: url(/resources/image/科城学风建设.jpg);
            background-position:center;
            background-repeat:repeat-y;
            background-size: cover;
        }

        a.ared:hover
        {
            color:red;
        }
    </style>
</head>
<body>
    <jsp:include page="public/head.jsp"></jsp:include>
    <br><br><br><br><br>

    <div id="hleft">
        <div style="margin-left: 40px; margin-top: 10px; border: 1px #009f95 solid; width: 200px;">
            <span style="margin-left: 10px;">最新分享:</span>
            <div id="newArticle">

            </div>
        </div>
    </div>

    <div class="hcenter" id="hegthCenter">

        <%--搜索框--%>
        <div class="select">
            <input id="selectArticleName" style="width: 615px; height: 46px; margin-left: 45px;margin-top: 2px; display:inline-block;" type="text" name="articleName" lay-verify="title" autocomplete="off" placeholder="搜索" class="layui-input">
            <button class="layui-btn" style="display:inline-block; height: 46px;" onclick="selectArticles()">搜索一下</button>
        </div>

        <%--轮播图--%>
        <div style="margin-left:51px; margin-top:10px; width: 707px; border: 1px #009f95 solid;">
            <div class="layui-carousel" id="test1" lay-filter="test1">
                <div carousel-item="" id="lunbo">
                </div>
            </div>
        </div>

        <div id="harticle">
        </div>

    </div>

    <div class="hright" id="righth">
        <div style="width: 240px; height:46px; border: 1px #009f95 solid; margin-left: 24px;margin-top:10px;text-align: center;">
            <span style="font-size: 32px; ">广告位招商</span>
        </div>

        <div class="right2" style="width: 240px; height:350px; border: 1px #009f95 solid; margin-left: 24px;margin-top:10px;">
            <span style="font-size: 10px; ">广告</span>
        </div>

        <div class="right3" style="width: 240px; height:350px; border: 1px #009f95 solid; margin-left: 24px;margin-top:10px;">
            <span style="font-size: 10px; ">广告</span>
        </div>
        <div  style="width: 240px; height:22px; margin-left: 24px;margin-top:10px;">
            <span style="font-size: 10px; ">广告</span>
            <a class='ared' href="https://download.csdn.net/my/score">获取更多免费java学习资料</a>
        </div>
        <div class="right4" style="width: 240px; height:350px; border: 1px #009f95 solid; margin-left: 24px;margin-top:10px;">
            <span style="font-size: 10px; ">广告</span>
        </div>
    </div>


</body>

<script id="img_template2" type="text/html" >
    <div class="" >
        <img src="{{ d }}" class="layui-upload-img">
    </div>
</script>

<script>
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
            ,form = layui.form;
        var laytpl = layui.laytpl;

        //常规轮播
        var ins = carousel.render({
            elem: '#test1'
            ,arrow: 'always'
            ,width: '707px'
            ,height: '280px'
        });

        $.get("/homepage/selectlbimg",function (data) {
            console.log("============222222"+data[0].photoUrl);


            $("#lunbo").html("<div><img style=\"width: 707px; height: 280px;\" src="+data[0].photoUrl + "></div>" +
                                            "<div><img style=\"width: 707px; height: 280px;\" src="+data[1].photoUrl + "></div>" +
                                            "<div><img style=\"width: 707px; height: 280px;\" src="+data[2].photoUrl + "></div>" +
                                            "<div><img style=\"width: 707px; height: 280px;\" src="+data[3].photoUrl + "></div>" +
                                            "<div><img style=\"width: 707px; height: 280px;\" src="+data[4].photoUrl + "></div>");

            //下面这步很重要
            ins.reload({elem: '#test1'});//重置轮播图
        });
    });
</script>
<script>
    var articleType = '${flagPage}';
    if(articleType == 1){
        console.log("-===============:" + articleType);
        window.onload = articleData(1, 10, "", "经典文学");
    }else if(articleType == 2){
        window.onload = articleData(1, 10, "", "网络小说");
    }else if(articleType == 3){
        window.onload = articleData(1, 10, "", "软件工具");
    }else if(articleType == 4){
        window.onload = articleData(1, 10, "", "免费资源");
    }else{
        window.onload = articleData(1, 10, "", "");

    }
    //初始化页面
    // window.onload = articleData(1, 10, "", "");
</script>



<script>
    /**
     *
     * @param page  搜索下一页
     * @param pageSize
     */
    function nextPage(page, pageSize, articleName) {



        var page1 = page + 1;
        articleData(page1, pageSize, "", "");
    }

    /**
     * 搜索上一页
     * @param page
     * @param pageSize
     */
    function backPage(page, pageSize, articleName){
        if (page == 1){
            return;
        }else{
            var page1 = page - 1;
            articleData(page1, pageSize, "","");
        }

    }

    /**
     * 搜索任何一页
     * @param page
     * @param pageSize
     */
    function selectPage(page, pageSize, articleName){
        var selectpage =  $('#selectpage').val();
        articleData(selectpage, pageSize, "", "");
    }

    /**
     * 搜索
     */
    function selectArticles(){
        var selectArticleName = $('#selectArticleName').val();//获取前端搜索框的输入值

        articleData(1, 10, selectArticleName, "");

    }

    /**
     * 点击文章标题
     */
    function articleTitle(articleTitle){
        console.log("+++++++++++++:"  + articleTitle);

        $.ajax({
            url : "/article/articletitle",
            data:{
                'articleUuid' : articleTitle
            },
            async : false,
            type : "post",
            success : function (data) {
                console.log("==============:"+data);
                window.open("'"+ data + "'");
            },
        });

    }


    //设置右边广告模块的margin-top值
    var hcenterHeigth = document.getElementById("hegthCenter").offsetHeight;//获取中间模块的高度，将这个高度给右边模块的margin-top
    console.log("=================hcenterHeigth:" + hcenterHeigth);
    var hright = document.getElementById("righth");
    hright.style.marginTop=-hcenterHeigth;

</script>

</html>
