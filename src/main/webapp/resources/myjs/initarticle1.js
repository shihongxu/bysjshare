
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
            var weibu = "<div style='width: 450px; height: 25px; margin-left: 258px;margin-top:5px;margin-bottom: 10px; display:inline-block'><div style='width:35px; height:20px; display:inline-block; margin-bottom: 4px;' onclick='backPage("+page1 +","+ pageSize1+ "," + "" +")'><img style='width: 35px; height: 20px; display:inline-block; margin-bottom: 4px;' src='" + "/resources/image/上一页小图标.jpg" + "'/></div>";
            weibu += "<span>\xa0\xa0\xa0"+ page1 +"\xa0\xa0\xa0</span><div style='height: 20px;  display:inline-block;margin-bottom: 4px;' onclick='nextPage("+page1+","+ pageSize1+ "," + "" +")'><img style='width: 35px; height: 20px; display:inline-block; margin-bottom: 4px;' src='"+ "/resources/image/下一页小图标.jpg" +"'/></div>";
            weibu += "<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px;'> \xa0\xa0\xa0" + "第:\xa0\xa0" + "<input id='selectpage' type='number' placeholder=\"1\" style='display:inline-block;width: 40px; height: 20px; '/>" + "\xa0\xa0页\xa0\xa0"+"<div style='height: 20px;  display:inline-block; font-size: 16px; color: #4f4f4f; font-weight: 400;line-height: 26px; margin-top: 2px; margin-bottom: 2px;'><button class='layui-btn layui-btn-sm layui-btn-normal' style='background: #009688;' type='button' onclick='selectPage("+page1+","+ pageSize1+ "," + "" +")'>"+ "确定" +"</button></div></div>";
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
                html += "<div style='margin-left : 53px; margin-top:10px;'>" + "<a class='ared' onmouseover=\"this.style.color='red';\" href=" + " /article/articletitle?articleUuid=" + data[i].articleUuid + ">" + data[i].articleName + " - " + "<span style='font-size: 13px;'>" + data[i].userName + "</span>" + "</a>" + "</div>";
            }
            newArticle.innerHTML = html;
        },
    });


}