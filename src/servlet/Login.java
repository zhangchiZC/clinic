package servlet;

import po.Patient;
import util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by ZC on 2019/6/24.
 */

//患者登录
@WebServlet(name = "Login",urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Patient patient = new Patient();
        patient.setPassword(password);
        patient.setUsername(username);
        PrintWriter out = response.getWriter();

        DbUtil db = new DbUtil();
        try {
            if (db.findUser(patient)){
                session.setAttribute("patientName",username);
                //request.getRequestDispatcher("/selectOrderType.jsp").forward(request,response);
                request.getRequestDispatcher("/registration.jsp").forward(request,response);
            }else{
                out.print("<script language='javascript' charset=\"utf-8\">alert('账号或密码错误!');window.history.back();</script>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);

    }
}
