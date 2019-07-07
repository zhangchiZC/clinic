<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/24
  Time: 13:45
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
    <title>注册</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script src="js/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="js/layer.js" charset="utf-8"></script>
    <script charset="utf-8">
        function check() {
            var username = $("#usercode").val();
            var password = $("#password").val();
            var validation = $("#validation").val();
            if (username == "" || password == "") {
                alert("账号或密码不能为空！");
                return false;
            }
            if(validation ==""){
                alert("验证码不能为空!");
                return false;
            }

            return true;
        }

        function sendMessage() {

            $.ajax({
                type:"post",
                url:"<%=basePath%>sendCode",
                data:{"phone":$("#phone").val()}

            });

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
                            欢迎使用患者注册系统
                        </font>
                    </legend>

                    <form action="register" method="post" onsubmit="return check()">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
                        账&nbsp;&nbsp;&nbsp;号：<input id="username" type="text" name="username"/>
                        <br/><br/>
                        密&nbsp;&nbsp;&nbsp;码：<input id="password" type="password" name="password"/>
                        <br/><br/>
                        &nbsp;&nbsp;&nbsp;&nbsp;手机号：<input id="phone" type="text" name="phone" style="width: 90px"/>
                        <input id="btnSendCode" name="btnSendCode" type="button" value="获取验证码" onclick="sendMessage();">
                        <br/><br/>
                        验证码: <input id="validation" type="text" name="validation">
                        <br/><br/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="注册"/><br>


                    </form>
                </fieldset>
            </div>
        </tr>
    </table>
</div>
</body>
</html>
