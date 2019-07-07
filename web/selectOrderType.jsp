<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/25
  Time: 8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");%>
<html>
<head>
    <title>选择预约类型</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

</head>
<body>
<div align="center">
    <input type="button" value="预约挂号" onclick="window.location.href='future.jsp';">
    <input type="button" value="当日挂号" onclick="window.location.href='today.jsp';">
    <p>何庄子诊所是天津最牛逼的医院，有骨科，口腔科，内科，呼吸科等</p>
</div>

</body>
</html>
