package servlet;

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
 * Created by ZC on 2019/6/27.
 */
@WebServlet(name = "DeleteOrder",urlPatterns = "/deleteOrder")
public class DeleteOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        DbUtil db = new DbUtil();
        PrintWriter out = response.getWriter();
        String date = session.getAttribute("date").toString().trim();
        String doctor_name = request.getParameter("doctor_name").toString().trim();
        String time = session.getAttribute("time").toString().trim();
        String patientName = session.getAttribute("patientName").toString().trim();
        try {
            String departmentName = db.findDepartmentNameById(Integer.parseInt(session.getAttribute("depId").toString().trim()));
            //System.out.println(doctor_name+patientName+departmentName+date+time+"zzzzzzzzzzzzzz");
            if (0 != db.deleteOrder(doctor_name,patientName,departmentName,date,time)) {

                out.print("<script language='javascript' >alert('成功取消预约!');window.history.back();</script>");
            }else {
                out.print("<script language='javascript' >alert('您并没有预约，无法取消!');window.history.back();</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);

    }
}
