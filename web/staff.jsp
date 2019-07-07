<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/24
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");%>
<html>
<head>
    <title>员工</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script type="text/javascript" charset="utf-8">
        // 判断是登录账号和密码是否为空
        function check() {
            if (document.getElementById("username").value == "" || document.getElementById("password").value == "") {
                alert("账号或密码不能为空！");
                return false;
            }
            return true;
        }

    </script>
</head>
<body leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<div ALIGN="center">
    <table border="0" width="1140px" cellspacing="0" cellpadding="0"
           id="table1">
        <tr>
            <td height="93"></td>
            <td></td>
        </tr>
        <tr>
            <div align="center">
                <fieldset style="width: auto; margin: 250px 500px;">
                    <legend>
                        <font style="font-size:15px" face="宋体">
                            欢迎使用员工系统
                        </font>
                    </legend>

                    <form action="login" method="post" onsubmit="return check()" name="form1">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
                        账&nbsp;号：<input id="username" type="text" name="username"/>
                        <br/><br/>
                        密&nbsp;码：<input id="password" type="password" name="password"/>
                        <br/><br/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="submit" value="登录" />
                    </form>
                </fieldset>
            </div>
        </tr>
    </table>
</div>
</body>
</html>
