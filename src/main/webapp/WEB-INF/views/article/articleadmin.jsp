<%--
  Created by IntelliJ IDEA.
  User: Axu
  Date: 2019/3/30
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章内容</title>

    <style>
        .aleft{
            width:300px;
            height: 500px;
            float : left;
            margin-left: 10px;
            border: 1px green solid;
        }
        .acenter{
            width:100%;
            margin-left: 340px;
            /*border: 1px green solid;*/
        }

        .articleContents_c{
            height: 10%;
            width: 98.8%;
            margin-top: 5px;
            margin-left:5px;
            margin-right:5px;
            margin-bottom: 8px;
            border: 1px #C9C9C9 solid;
        }
        a.ared:hover
        {
            color:red;
        }

    </style>
</head>
<body style="background: #f5f6f7;">
<jsp:include page="../public/head.jsp"/>
<br>
<br>
<br>
<br>
<br>
<div >
    <div class="aleft" style="background: #ffffff;">
        <div style="margin-left: 32px; margin-top: 10px; color: #3d3d3d;font-weight: 700; font-size: 22px;">
            作者:<span style="margin-left: 30px;">${article.userName}</span>
        </div>
        <hr>
        <div style="margin-left:30px; color: #333;">
            <h3 style="font-size: 17px;">他的分享:</h3>
            <div id="userAarticleName"></div>
        </div>

    </div>
    <div class="acenter">
        <div style="width: 750px; margin-left: 70px;margin-top: 10px; background: #ffffff; border: 1px green solid;">
            <h1 style="font-size: 24px; margin-left: 19px;">${article.articleName}<a href="/myarticle/deletearticleadmin?articleUuid=${article.articleUuid}"><span style='font-size: 17px;color: red;font-weight: normal;'>  &nbsp;&nbsp;删除</span></a></h1>

            <div style="font-size: 13px; color: #4f4f4f;font-weight: 400;  line-height: 26px;overflow-x: auto; margin-left: 22px;">
                <span>文章类型:&nbsp;&nbsp;${article.articleType}</span><span id="publishDate"> &nbsp;&nbsp; &nbsp;发表时间:  </span>
            </div>
            <div id="imgUrl"></div>
            <hr>
            <div>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;</span> ${article.articleContents}
                    <div style="margin-left: 3px; margin-right: 3px; margin-bottom: 10px; ">
                </div>
            </div>
        </div>


        <%--评论区--%>
        <div style="width: 750px; margin-left: 70px;margin-top: 10px; background: #ffffff; border: 1px green solid;margin-bottom: 50px;">
            <form action="/article/comment" method="post">

                <div >
                    <%--评论内容--%>
                    <textarea id="commentContents" class="articleContents_c" placeholder="   对作者说点什么吧" name="commentContents"></textarea>
                    <%--文章作者name--%>
                    <input id="articleUserName" type="hidden" name="articleUserName"  value="${article.userName}"/>
                    <%--文章作者uuid--%>
                    <input id="articleUserUuid" type="hidden" name="articleUserUuid"  value="${article.userUuid}"/>
                    <%--文章uuid--%>
                    <input id="articleUuid" type="hidden" name="articleUuid"  value="${article.articleUuid}"/>
                    <%--文章name--%>
                    <input id="articleName" type="hidden" name="articleName"  value="${article.articleName}"/>
                    <%--被回复人uuid--%>
                    <input id="commentUserUuid" type="hidden" name="commentUserUuid"/>
                    <%--被回复人name--%>
                    <input id="commentUserName" type="hidden" name="commentUserName"/>

                </div>

                <div style="margin-left:678px;margin-bottom :10px;">
                    <button class="layui-btn layui-btn-radius layui-btn-sm" lay-submit="5464">发表评论</button>
                </div>

                <div>
                    <div id="comment">

                    </div>
                </div>
            </form>


        </div>
    </div>
</div>

</body>

<script>

    //加载该用户发表的文章name
    function selectUserArticleName(){
        var userName = '${tokenUser.userName}';
        var publishDate = document.getElementById('publishDate');
        var pdate = new Date('${article.articleBeginDate}');
        publishDate.append(pdate.getFullYear() + "-" + (pdate.getMonth() + 1) + "-" + (pdate.getDate()-1));


        $.ajax({
            url : "/article/selectUserArticleName",
            data :{
                'userName' : '${article.userName}',
                'articleFlag' : 1,
                'articleUuid' : '${article.articleUuid}'
            },
            async : true,
            success : function (data) {
                var userAarticleName = document.getElementById('userAarticleName');
                var photoUrl = document.getElementById('imgUrl');
                var articleComment = document.getElementById('comment');

                var html = "";
                var htmlUrl = "";
                var htmlComment = "";//评论区拼接的HTML

                var listName = data.listName;//文章名
                var listPhotoUrl = data.photoUrl;//图片地址
                var commentList = data.commentList;//文章的一级评论


                //添加图片
                htmlUrl += "<div style='margin-bottom: 5px;'>";
                if(listPhotoUrl.length % 2 == 0){//图片是偶数张
                    if(listPhotoUrl.length == 2){//图片张数等于2时
                        for(var i = 0 ; i < listPhotoUrl.length; i++){
                            htmlUrl += "<img style='width: 367px; height: 410px; float: left; margin-left:5px; margin-top:5px;' src='" + listPhotoUrl[i].photoUrl + "'/>";
                        }
                    }else{
                        for(var i = 0 ; i < listPhotoUrl.length; i++){
                            htmlUrl += "<img style='width: 367px; height: 250px; float: left; margin-left:5px; margin-top:5px;' src='" + listPhotoUrl[i].photoUrl + "'/>";
                        }
                    }
                }if(listPhotoUrl.length % 2 == 1){//图片是奇数张
                    if(listPhotoUrl.length == 1) {//只有一张的时候
                        htmlUrl += "<img style='width: 739px; height: 355px; float: left; margin-left:5px; margin-top:5px;' src='" + listPhotoUrl[listPhotoUrl.length-1].photoUrl + "'/>";
                    }else{
                        for(var i = 0 ; i < (listPhotoUrl.length - 1); i++){
                            htmlUrl += "<img style='width: 367px; height: 250px; float: left; margin-left:5px; margin-top:5px;' src='" + listPhotoUrl[i].photoUrl + "'/>";
                        }
                        htmlUrl += "<img style='width: 739px; height: 250px; float: left; margin-left:5px; margin-top:5px;' src='" + listPhotoUrl[listPhotoUrl.length-1].photoUrl + "'/>";
                    }
                }

                htmlUrl += "</div>"
                photoUrl.innerHTML = htmlUrl;


                //添加文章评论
                if(commentList.length > 0){
                    for(var i = 0; i < commentList.length; i ++){
                        htmlComment += "<div style='margin-bottom : 5px; margin-left:5px;'>"+ "<span>" + commentList[i].userName + "</span>";

                        if(commentList[i].commentUserName != null && commentList[i].commentUserName != ''){
                            console.log("____________:" + commentList[i].commentUserName);
                            htmlComment += "\xa0\xa0" + "<span style='font-size: 12px;'>" + "回复" + "</span>" + "\xa0\xa0<span>"+ commentList[i].commentUserName +"</span>";
                        }
                        htmlComment += ":<span style='color: #4f4f4f'>" + commentList[i].commentContents + "</span>" +
                            "<span style='float:right;margin-right: 20px; '>";


                        if(userName == commentList[i].userName){//判断是否有删除权限
                            htmlComment += "<a onclick='deleteComment(\""+ commentList[i].commentUuid + "\"" + ",\""+ commentList[i].articleUuid +"\")'>" + "删除" +"</a>";
                        }
                        htmlComment += "\xa0\xa0\xa0\xa0\xa0\xa0" +"<a onclick='replay(\""+ commentList[i].userUuid +"\",\""+ commentList[i].userName +"\")'>" + "回复" +"</a></span>" + "</div>";

                        htmlComment += "<hr width=100% >";
                    }
                }
                articleComment.innerHTML = htmlComment;


                //添加文章名字
                for(var i = 0; i < 11; i++){

                    var date = new Date(listName[i].articleBeginDate);//格式化时间

                    html += "<div style='margin-top:5px; margin-left:40px;'>"+ "<a href="+ "/article/articletitle?articleUuid=" + listName[i].articleUuid + ">"+ listName[i].articleName +"</a> " +
                        "<span style='font-size: 10px; margin-left: 10px;'>"+ "创建时间:" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() +"<span></div>";

                    if(i == 10){
                        html += "<div style='margin-top:5px; margin-left:40px;'>" + "......" + "</div>";
                    }

                    userAarticleName.innerHTML = html;
                }

            }

        });
    }
    window.onload = selectUserArticleName();


    //删除文章
    function deleteArtcile(articleuuid){
        $.ajax({
            url : "/myarticle/deletearticleadmin",
            data : {
                'articleUuid' : articleuuid
            },
            type : "get",
            success : function (data) {
                console.log(data);
                window.open(data);
            }
        });
    }
</script>

<%--评论区--%>
<script>

    /*点击回复一会光标定位到回复框*/
    function replay(commentUserUuid, commentUserName) {

        console.log(commentUserUuid+"=======" + commentUserName);

        //聚焦定位
        var oInput = document.getElementById('commentContents');
        oInput.focus();

        var commentUserUuidInput = document.getElementById('commentUserUuid').value = commentUserUuid;
        var commentUserName = document.getElementById('commentUserName').value = commentUserName;
    }

    //删除评论
    function deleteComment(commentUuid, articleUuid){

        console.log("============:"+commentUuid);

        $.ajax({

            url: "/article/deletecomment",
            type : "get",

            data : {
                'commentUuid' : commentUuid,
                'articleUuid': articleUuid
            },
            success : function(data){
                // window.location.reload();
                console.log("成功!");
                window.location.href="/article/articletitle?articleUuid="+articleUuid;
            },



        });
    }

</script>

</html>
