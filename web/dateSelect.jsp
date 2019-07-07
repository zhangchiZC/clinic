<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/25
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<%--<%--%>
    <%--DateFormat dateFormat = DateFormat.getDateInstance();--%>
    <%--String date = dateFormat.format(new Date());--%>

    <%--//out.print(date);--%>
    <%--for (int i=0;i<7;i++){--%>
        <%--out.print(Integer.parseInt(date.split("-")[2])+i);--%>
    <%--}--%>
<%--%>--%>
<%
    for (int i=0;i<7;i++) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +i);
        date = calendar.getTime();
        //System.out.println(date);
        DateFormat format = DateFormat.getDateInstance();
        String da = format.format(date);
        //out.print(da+" &nbsp;&nbsp;&nbsp;&nbsp;");
        out.print("<a href=\"dateSelect?date="+da+"\">"+da+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    }

%>


</body>
</html>
