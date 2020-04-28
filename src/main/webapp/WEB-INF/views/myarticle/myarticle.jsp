<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2019/4/6
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的分享</title>

    <style>
        .myshare{
            /*height:120%;*/
            width: 60%;
            margin: 0 auto;
            margin-top:10px;
            border: 1px #C9C9C9 solid;
        }
        .articleTitle{
            margin-top: 5px;
            margin-left:5px;
            margin-right:5px;
        }
        .articleContents_c{
            height: 400px;
            width: 98.8%;
            margin-top: 5px;
            margin-left:5px;
            margin-right:5px;
            border: 1px #C9C9C9 solid;
        }

        .uploader-list .info {
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

        .uploader-list .handle {
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

        .uploader-list .file-iteme {
            margin: 12px 0 0 15px;
            padding: 1px;
            float: left;
        }
    </style>
</head>
<body>

<jsp:include page="../public/head.jsp"></jsp:include>

<div style="margin-left: 100px;">
    <br><br><br><br>

    <%--文章内容--%>
    <div id="publishpage">
    </div>

    <%--修改文章内容--%>
    <div id="updataArticle" style="display: none">

        <form action="/myarticle/updataarticle" method="post">
            <%--<form action="" method="post">--%>
            <div>
                <div class="myshare">
                    <input id="articleUuid" type="hidden" name="articleUuid">
                    <div class="articleTitle">
                        <input id="titleName" type="text" name="articleName" autocomplete="off" placeholder="请输入分享标题" class="layui-input" autofocus>
                    </div>
                    <div >
                        <textarea id="contentsName" class="articleContents_c" placeholder="   请输入内容" name="articleContents"></textarea>
                    </div>

                    <div class="layui-inline layui-form" style="margin-left: -6px; margin-top: 15px; float:left;">
                        <label class="layui-form-label">分享类型</label>
                        <div class="layui-input-block">
                            <select id="articleType" name="articleType" lay-verify="required" lay-search="">
                                <option value="">直接选择或搜索选择</option>
                                <option value="经典文学">经典文学</option>
                                <option value="网络小说">网络小说</option>
                                <option value="软件工具">软件工具</option>
                                <option value="免费资源">免费资源</option>
                            </select>
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin-left: -80px; margin-top: 15px; width: 400px; float: left;">
                        <div class="layui-input-block" style="margin-top:5px">
                            <input type="radio" name="articlePublish" value="发布" title="发布">发布
                            <input type="radio" name="articlePublish" value="草稿" title="草稿">保存草稿
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin-top: -9px; margin-left: 30px">
                        <button class="layui-btn layui-btn-radius layui-btn-sm" lay-submit="5464" >提交编辑</button>
                        <button class="layui-btn layui-btn-radius layui-btn-sm" lay-submit="5464" onclick="">返回</button>
                    </div>
                </div>
            </div>


        </form>
    </div>
</div>
<span hidden id="msg">${msg}</span>

</body>
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

    window.onload = mypublish(1, 3);

    //加载我的文章
    function mypublish(page, size){

        $.ajax({
            url : "/myarticle/mypublish",
            data : {
                'page': page,
                'size' : size
            },
            type : "get",
            dataType : "json",
            success :function (data) {
                console.log("data:" + data);

                var article = document.getElementById('publishpage');
                var html = "";
                for (var i = 0; i < data.length; i++){
                    // console.log(data);
                    // console.log("===============:" + data[i].photoUrl2.length);
                    // console.log("+++++++++++++++:" + article);
                    html += "<div style='width : 714px; margin-left: 314px; margin-top:30px;'>";
                    // html += "<h2 style=''>" + "\xa0" + data[i].articleName + "</h2>";

                    //添加标题
                    html += "<h2 style='font-size: 24px;font-weight: bold;line-height: 24px;height: 24px; margin-bottom: 4px; overflow: hidden;white-space: nowrap;margin-top:5px;'>" + "\xa0" + "<a href="+ "/article/articletitle?articleUuid=" + data[i].articleUuid  + ">" + data[i].articleName + "</a>"+"<span style='font-size: 17px;color: blue;font-weight: normal;' onclick=updataArtcile('" + data[i].articleUuid + "')>" + "\xa0\xa0\xa0\xa0"  + "编辑" + "</span>"+ "<span style='font-size: 17px;color: red;font-weight: normal;' onclick=deleteArtcile('" + data[i].articleUuid + "')>" + "\xa0\xa0\xa0\xa0"  + "删除" + "</span>"+"</h2>";

                    //添加文章创建时间和 作者
                    var date = new Date(data[i].articleBeginDate);
                    html += "<br/><p style='font-size: 13px; color: #4f4f4f; font-weight: 400;line-height: 26px;margin: -18px 0 -9px;overflow-x: auto;'>"+ "\xa0\xa0\xa0\xa0" + "文章作者:" + data[i].userName + "\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0" + "创建时间:" + date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() +
                        "\xa0\xa0\xa0\xa0\xa0\xa0" + "文章类型:" + data[i].articleType + "\xa0\xa0\xa0\xa0\xa0\xa0" + "文章状态:" + data[i].articlePublish + "<span>"+ "\xa0\xa0\xa0\xa0" + "阅读:" + data[i].viewsNumber +"</span>"+ "<span>"+ "\xa0\xa0\xa0\xa0" + "评论:"+ data[i].commentNumber +"</span>" +"</p>" ;

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
                var weibu = "<div style='width: 450px; height: 25px; margin-left: 496px;margin-top:5px;margin-bottom: 50px; display:inline-block'><div style='width:35px; height:20px; display:inline-block; margin-bottom: 4px;' onclick='backPage("+page +","+ size +")'><img style='width: 35px; height: 20px; display:inline-block; margin-bottom: 4px;' src='" + "/resources/image/上一页小图标.jpg" + "'/></div>";
                weibu += "<span>\xa0\xa0\xa0"+ page +"\xa0\xa0\xa0</span><div style='height: 20px;  display:inline-block;margin-bottom: 4px;' onclick='nextPage("+page+","+ size +")'><img style='width: 35px; height: 20px; display:inline-block; margin-bottom: 4px;' src='"+ "/resources/image/下一页小图标.jpg" +"'/></div>";
                weibu += "<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px;'> \xa0\xa0\xa0" + "第:\xa0\xa0" + "<input id='selectpage' type='text' onkeyup=\"this.value=this.value.replace(/\\D|^0/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D|^0/g,'')\" style='display:inline-block;width: 40px; height: 20px; ' placeholder=\"1\" min=\"1\" max=\""+ (data[0].total)/10+ " \"/>" + "\xa0\xa0页\xa0\xa0"+"<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px; margin-bottom: 2px;'><button class='layui-btn layui-btn-sm layui-btn-normal' style='background: #009688;' type='button' onclick='selectPage("+page+","+ size +")'>"+ "确定" +"</button></div></div>";
                weibu += "<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px;'>"+ "\xa0\xa0\xa0共:"+ data[0].total + "条" + "</div>";
                weibu += "</div>";

                html += weibu;

                // var height = article.offsetHeight;//获取article的高度
                // var hright = document.getElementById("hright");
                // hright.style.marginTop = '-' + height + "px";
                article.innerHTML = html;
            }

        });

    }

    /**
     *
     * @param page  搜索下一页
     * @param pageSize
     */
    function nextPage(page, pageSize) {
        var page1 = page + 1;
        mypublish(page1, pageSize);
    }

    /**
     * 搜索上一页
     * @param page
     * @param pageSize
     */
    function backPage(page, pageSize){
        if (page == 1){
            return;
        }else{
            var page1 = page - 1;
            mypublish(page1, pageSize);
        }

    }

    /**
     * 搜索任何一页
     * @param page
     * @param pageSize
     */
    function selectPage(page, pageSize){
        var selectpage =  $('#selectpage').val();
        mypublish(selectpage, pageSize);
    }


    /**
     * 修改文章内容
     * @param articleuuid
     */
    function updataArtcile(articleuuid){
        console.log("=======================:" + articleuuid);
        var form = form = layui.form;

        $.ajax({
            url : "/myarticle/getarticle",
            data : {
                'articleUuid' : articleuuid
            },
            type : "get",
            dataType : "json",
            success : function (data) {

                //document.getElementById('percenterdata').style.display = "none";
                document.getElementById('publishpage').style.display = "none";
                document.getElementById('updataArticle').style.display = "block";

                //设置文章uuid
                var articleUuid = document.getElementById('articleUuid');
                articleUuid.value = data.articleUuid;

                //设置标题
                var titleName = document.getElementById('titleName');
                titleName.value = data.articleName;

                //设置文章内容
                var contentsName = document.getElementById('contentsName');
                contentsName.value = data.articleContents;

                console.log("data: " + data.articleName);

            }

        });
    }

    //删除文章
    function deleteArtcile(articleuuid){
        $.ajax({
            url : "/myarticle/deletearticle",
            data : {
                'articleUuid' : articleuuid
            },
            type : "get",
            success : function (data) {
                window.location.reload();
                console.log(data);
            }
        });
    }
</script>
</html>
