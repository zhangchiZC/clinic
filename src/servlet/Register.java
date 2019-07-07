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
//患者注册
@WebServlet(name = "Register",urlPatterns = "/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String validation = request.getParameter("validation");

        Patient patient = new Patient();
        patient.setUsername(username);
        patient.setPassword(password);

        DbUtil db = new DbUtil();

        HttpSession session = request.getSession();
        String code = session.getAttribute("code").toString().trim();
        System.out.println(code+"aaaa"+validation);

        if (code.equals(validation)) {
            try {
                db.addPatient(patient);
                String a="<script language='javascript' charset=\"utf-8\">alert('注册成功');</script>";
                out.println(a);
                response.sendRedirect("patient.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String a="<script language='javascript'>alert('error');window.history.back();</script>";
            out.println(a);

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);

    }
}
