<%--
  Created by IntelliJ IDEA.
  User: tj
  Date: 2019/11/20
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/api/upload" method="post" enctype="multipart/form-data">
    <input type="file"  name="filename">
    <input type="submit" value="上传">
</form>
<p>${ msg}</p>
</body>
</html>
