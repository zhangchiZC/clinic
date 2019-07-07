<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/25
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");%>
<html>
<head>
    <title>选择时间</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
上午:
<a href="timeSelect?time=am1">8--10</a>
<a href="timeSelect?time=am2">10--12</a>
<br><br><br><br>
下午:
<a href="timeSelect?time=pm1">13--15</a>
<a href="timeSelect?time=pm2">15--17</a>
</body>
</html>
