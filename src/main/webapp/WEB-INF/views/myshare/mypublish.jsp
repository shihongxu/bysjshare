<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2019/3/13
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我要分享</title>
    <style>
        .myshare{
            height:120%;
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
            height: 50%;
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
<script>

</script>
<body>

<jsp:include page="../public/head.jsp"></jsp:include>
<div>
<br>
<br>
<br>
<br>
<br>
    <form action="/myshare/addarticledata" method="post">
    <%--<form action="" method="post">--%>
        <div>
            <div class="myshare">
                <div class="articleTitle">
                    <input id="titleName" type="text" name="articleName" autocomplete="off" placeholder="请输入分享标题" class="layui-input" autofocus>
                </div>
                <div >
                    <textarea id="contentsName" class="articleContents_c" placeholder="   请输入内容" name="articleContents"></textarea>
                </div>

                <%--<div class="layui-upload" style="margin-top: 10px; margin-left: 15px; width: 90%">--%>
                    <%--<button type="button" class="layui-btn" id="test2">多图片上传</button>--%>
                    <%--<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">--%>
                        <%--预览图：--%>
                        <%--<div class="layui-upload-list" id="demo2"></div>--%>
                    <%--</blockquote>--%>
                <%--</div>--%>


                <div class="layui-upload ">

                    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                        <div class="layui-upload-list" id="imgs" style="height: 100px;">
                        </div>
                        <div class="layui-upload-list" id="imgs2" style="height: 100px; display: none">
                        </div>
                    </blockquote>
                    <div class="mark_button" style="margin-left: 33px; margin-top:10px; ">
                        <button type="button" class="layui-btn layui-btn-radius layui-btn-sm" id="sele_imgs">选择图片</button>
                        <button type="button" class="layui-btn layui-btn-radius layui-btn-sm" id="upload_imgs" disabled>开始上传</button>

                        <button type="button" class="layui-btn layui-btn-radius layui-btn-sm" id="dele_imgs">删除选中图片</button>
                    </div>

                </div>

                <div class="layui-inline layui-form" style="margin-left: -6px; margin-top: 15px; float:left;">
                    <label class="layui-form-label">分享类型</label>
                    <div class="layui-input-block">
                        <select name="articleType" lay-verify="required" lay-search="">
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

<%--弹出层--%>
<span hidden id="msg">${msg}</span>

</body>
<script src="\resources\layui\layui.js"></script>

<script id="img_template" type="text/html">
    <div class="upload-img" filename="{{ d.index }}">
        <input type="checkbox" name="" lay-skin="primary" style="float: left">
        <img src="{{  d.result }}" alt="{{ d.name }}" class="layui-upload-img" style=" float: left; height: 100px; width: 100px;">
    </div>
</script>
<script id="img_template2" type="text/html" >
    <div class="upload-img" filename="{{ d.index }}" >
        <input type="hidden" name="photoUrl"  id="photoUrl" value="{{  d.url }}"/>
        <img src="{{  d.url }}" alt="{{ d.name }}" class="layui-upload-img" style=" float: left; height: 100px; width: 100px; margin-left:3px; ">
    </div>
</script>

<script>

    layui.use(['upload', 'laytpl', 'form'], function () {
        var $ = layui.jquery
            , upload = layui.upload
            , laytpl = layui.laytpl
            , form = layui.form;

        //批量删除 单击事件
        $('#dele_imgs').click(function () {
            $('input:checked').each(function (index, value) {
                var filename=$(this).parent().attr("filename");
                delete imgFiles[filename];
                $(this).parent().remove()
            });
        });


        var imgFiles;

        //多图片上传
        var uploadInst = upload.render({
            elem: '#sele_imgs'  //开始
            , acceptMime: 'image/*'
            , url: 'uploadImgQiniu'
            , auto: false
            , bindAction: '#upload_imgs'
            , multiple: true
            , size: 1024 * 12
            , choose: function (obj) {  //选择图片后事件
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                imgFiles = files;

                $('#upload_imgs').prop('disabled',false);

                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    var data = {
                        index: index,
                        name: file.name,
                        result: result
                    };

                    //将预览html 追加
                    laytpl(img_template.innerHTML).render(data, function (html) {
                        $('#imgs').append(html);
                    });

                    //绑定单击事件
                    $('#imgs div:last-child>img').click(function () {
                        var isChecked = $(this).siblings("input").prop("checked");
                        $(this).siblings("input").prop("checked", !isChecked);
                        return false;
                    });

                });
            }
            , before: function (obj) { //上传前回函数
                layer.load(); //上传loading
            }
            , done: function (res,index,upload) {    //上传完毕后事件

                var purl={
                    url : res.url,
                    name : res.url,
                    index : index
                };



                layer.closeAll('loading'); //关闭loading
                //上传完毕

                $('#imgs').html("");//清空操作

                document.getElementById("imgs").style.display="none";
                document.getElementById("imgs2").style.display="block";

                top.layer.msg("上传成功！");

                laytpl(img_template2.innerHTML).render(purl, function (html) {
                    $('#imgs2').append(html);
                });

                return delete imgFiles[index]; //删除文件队列已经上传成功的文件

            }
            , error: function (index, upload) {

                layer.closeAll('loading'); //关闭loading

                top.layer.msg("上传失败！");

            }
        });

    });
</script>


<script>

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

    // //点击提交
    // function mysharePublish(articlePublish){
    //
    //     //两种方式取到<input> 里面的值
    //     //方式一：
    //     // var articleName1 = document.getElementById("titleName").value;
    //     // alert("-----------------");
    //     //方式二（原生的js方式）：
    //     var articleName = $("#titleName").val();//文章标题
    //     var contentsName = $("#contentsName").val();//文章内容
    //     // layer.alert('我是内容1', {icon: 1, title:'标题'});
    //
    //
    //     $.ajax({
    //         url:"/myshare/addarticledata",
    //         type:"post",
    //         data:{
    //             'articleName' : articleName,
    //             'articleContents' : contentsName
    //         },
    //         async : true,
    //         success : function (data) {
    //
    //             var data1 = 1;
    //             if(data){
    //                 // alert("5555555555");
    //                 layer.msg("添加成功！");
    //             }
    //
    //         }
    //     });
    // }


    // function showPreview(obj) {
    //     var str = obj.value;
    //
    //     alert("==================" + obj);
    //
    //     $.ajax({
    //         url:"/myshare/uploadImgQiniu",
    //         type:"post",
    //         data: obj,
    //         async : true,
    //         success : function (data) {
    //
    //             var data1 = 1;
    //             if(data){
    //                 alert("5555555555");
    //
    //                 document.getElementById("perviewImg").innerHTML =
    //                     "<img src = '" + str + "'/>";
    //             }
    //
    //         }
    //     });
    //
    //
    // }


    // //用于进行图片上传，返回地址
    // function setImg(obj){
    //     var f=$(obj).val();
    //     alert(f);
    //     console.log(obj);
    //     if(f == null || f ==undefined || f == ''){
    //         return false;
    //     }
    //     if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
    //     {
    //         alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
    //         $(obj).val('');
    //         return false;
    //     }
    //     var data = new FormData();
    //     console.log(data);
    //     $.each($(obj)[0].files,function(i,file){
    //         data.append('file', file);
    //     });
    //     console.log(data);
    //     $.ajax({
    //         type: "POST",
    //         url: "/myshare/uploadImgQiniu",
    //         data: data,
    //         cache: false,
    //         contentType: false,    //不可缺
    //         processData: false,    //不可缺
    //         dataType:"json",
    //         success: function(ret) {
    //             console.log(ret);
    //             if(ret.code==0){
    //                 $("#photoUrl").val(ret.url);//将地址存储好
    //                 $("#photourlShow").attr("src",ret.url);//显示图片
    //                 alertOk(ret.message);
    //             }else{
    //                 alertError(ret.message);
    //                 $("#url").val("");
    //                 $(obj).val('');
    //             }
    //         },
    //         error: function(XMLHttpRequest, textStatus, errorThrown) {
    //             alert("上传失败，请检查网络后重试");
    //         }
    //     });
    // }



</script>


</html>