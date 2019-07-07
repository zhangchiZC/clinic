<%@ page import="util.DbUtil" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/25
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <%
        DbUtil db = new DbUtil();
        PrintWriter printWriter = response.getWriter();
    %>
</head>
<body>
<%
    for (String i:db.findAllDepartment()) {
       // for(String x:db.findAllDepartmentId()) {
            printWriter.print("<a href=\"" + "forwardToDoctor?depId="+db.findIdByDepartmentName(i)+ "\">" + i + "</a>"+"&nbsp;&nbsp;&nbsp;");

      //  }

    }
%>


</body>
</html>
