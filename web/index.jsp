<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/24
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");%>
<html>
<head>
    <title>何庄子诊所</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
<%--<input type="button" onclick="window.location.href='patient.jsp'" value="患者系统"/><br>--%>
<%--<input type="button" onclick="window.location.href='staff.jsp'" value="员工系统">--%>
<jsp:forward page="patient.jsp"></jsp:forward>
</body>
</html>
