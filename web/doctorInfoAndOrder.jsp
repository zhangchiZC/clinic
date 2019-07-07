<%@ page import="util.DbUtil" %>
<%@ page import="po.Doctor" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/26
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
    DbUtil db = new DbUtil();
%>

<html>
<head>
    <title>医生预约</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script src="js/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script charset="utf-8">
        <%--$("input").click(function () {--%>
        <%--var doctorName = $(this).attr("id").text;--%>
        <%--alert(doctorName);--%>
        <%--console.log(doctorName);--%>
        <%--$.ajax({--%>
        <%--type:"get",--%>
        <%--url:"<%=basePath%>orderDoctor",--%>
        <%--data:{--%>
        <%--"date":<%=session.getAttribute("date")%>,--%>
        <%--"time":<%=session.getAttribute("time")%>,--%>
        <%--"departmentName":<%=db.findDepartmentNameById(Integer.parseInt(session.getAttribute("depId").toString()))%>,--%>
        <%--"doctorName":doctorName,--%>
        <%--"patientName":<%=session.getAttribute("patientName")%>--%>
        <%--}--%>

        <%--});--%>
        <%--})--%>
        //var  doctorName="xxx";
        <%--function getButtonId() {--%>
        <%--$("input").onclick(function () {--%>
        <%--return $(this).attr("id");--%>
        <%--})--%>

        <%----%>
        <%--}--%>
        <%--function sendOrder() {--%>

        <%--$.ajax({--%>
        <%--type:"post",--%>
        <%--url:"<%=basePath%>orderDoctor",--%>
        <%--data:{--%>
        <%--"date":<%=session.getAttribute("date")%>,--%>
        <%--"time":<%=session.getAttribute("time")%>,--%>
        <%--"departmentName":<%=db.findDepartmentNameById(Integer.parseInt(session.getAttribute("depId").toString()))%>,--%>
        <%--"doctorName":getButtonId().toString(),--%>
        <%--"patientName":<%=session.getAttribute("patientName")%>--%>
        <%--}--%>

        <%--});--%>


        <%--}--%>
    </script>
</head>
<body>

<%
    String depId = session.getAttribute("depId").toString().trim();
    String date = session.getAttribute("date").toString().trim();
    String t = session.getAttribute("time").toString().trim();


    System.out.println(date + depId + t);
    System.out.println(db.findAllByDoctor_idJoinWorkday("2019-06-27", "am1", Integer.parseInt(depId)).size());

    for (Doctor doctor : db.findAllByDoctor_idJoinWorkday(date, t, Integer.parseInt(depId))) {
        System.out.println(doctor);
    }

    Date d = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(d);
    d = calendar.getTime();
    DateFormat format = DateFormat.getDateInstance();
    String today = format.format(d);
    //System.out.println(today);


%>
<table border="1">
    <%
        List<String> doctorNames = new ArrayList<>();

        for (Doctor doctor : db.findAllByDoctor_idJoinWorkday(date, t, Integer.parseInt(depId))) {
            doctorNames.add(doctor.getName());
            session.setAttribute("doctorNames", doctorNames);
            System.out.println(doctor);
    %>
    <tr>
        <td><img src="<%=doctor.getPhotoPath()%>" height="10%" width="10%"></td>
        <td><%=doctor.getName()%></td>
        <td><%=doctor.getDegree()%></td>
        <td><%=doctor.getSex()%></td>
        <td><%=doctor.getTel()%></td>
        <td><%=doctor.getIntroduction()%></td>
        <%
            if (today.equals(date)) {
        %>
        <td><a href="orderDoctor?doctor_name=<%=doctor.getName()%>"><span>挂号</span></a></td>
        <%--<td><input type="button" value="预约"  onclick="sendOrder()" id="<%=doctor.getName()%>" name="<%=doctor.getName()%>"></td>--%>
        <%
        } else {
        %>
        <td><a href="orderDoctor?doctor_name=<%=doctor.getName()%>"><span>挂号</span></a></td>
        <td><a href="deleteOrder?doctor_name=<%=doctor.getName()%>"><span>取消挂号</span></a></td>
        <%
            }
        %>
    </tr>
    <%

        }
        //System.out.println(doctorNames.get(0));

    %>


</table>
</body>
</html>
