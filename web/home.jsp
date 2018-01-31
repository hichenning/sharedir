<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Home</title>
    <style>
        #header, #menu{
            margin-left: 30px;
        }

    </style>
</head>
<body>
<div id="header">我的文件夹</div>
<div id="menu">
    <br/><hr>
    <a style="margin-right: 20px;" href='' onclick='javascript:history.go(-1)' >后退</a>
    <hr>
    <form method="post" enctype="multipart/form-data" action="UploadServlet.action">
        <input type="file" multiple  name="uploadfile">
        <button type="submit" >上传</button>
    </form>
    <p>当前文件夹：${sessionScope.currentDir}<p>
</div>

<div id="root" style="margin-left: 30px"></div>
</body>
<script>
    var data = '${sessionScope.data}';
    var jsonData = JSON.parse(data);
    var html = "";
    var length = jsonData.options.length;
    console.info(length);
    for(var x =0 ; x < length; x++){
        console.info(typeof jsonData.options[x].filePath);
        var filepath = jsonData.options[x].filePath;
        html += "<a href=getFilsList?fatherPath="+filepath + ">"+ jsonData.options[x].fileName + "</a><br/>";
    }
    var root = document.getElementById("root");
    root.innerHTML = html;
</script>
</html>
