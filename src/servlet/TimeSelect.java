package servlet;

import po.Doctor;
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
 * Created by ZC on 2019/6/25.
 */
@WebServlet(name = "TimeSelect", urlPatterns = "/timeSelect")
public class TimeSelect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String time = request.getParameter("time");
        HttpSession session = request.getSession();
        session.setAttribute("time", time);

        //PrintWriter out = response.getWriter();
        String depId = session.getAttribute("depId").toString().trim();
        String date = session.getAttribute("date").toString().trim();
        String t = session.getAttribute("time").toString().trim();
        //out.print(depId+"   "+date+"   "+t);
        System.out.println(depId + "   " + date + "   " + t);
        request.getRequestDispatcher("doctorInfoAndOrder.jsp").forward(request,response);


//        DbUtil db = new DbUtil();//秀自己一碧莲 注意看出诊表的时间和科室
//        try {
//            for (Doctor doctor : db.findAllByDoctor_idJoinWorkday(date, t, Integer.parseInt(depId))) {
//                out.print(doctor);
//                System.out.println(doctor);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
