package servlet;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import util.AliyunSmsUtils;
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

import static util.AliyunSmsUtils.sendSms;

/**
 * Created by ZC on 2019/6/26.
 */
@WebServlet(name = "OrderDoctor", urlPatterns = "/orderDoctor")
public class OrderDoctor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        DbUtil db = new DbUtil();
        String date = session.getAttribute("date").toString().trim();
        String doctor_name = request.getParameter("doctor_name").toString().trim();
        String time = session.getAttribute("time").toString().trim();
        String patientName = session.getAttribute("patientName").toString().trim();
        //String phone = session.getAttribute("phone").toString().trim();

        PrintWriter out = response.getWriter();
        try {
            String departmentName = db.findDepartmentNameById(Integer.parseInt(session.getAttribute("depId").toString().trim()));
            db.addOrder(date,doctor_name,patientName,departmentName,time);
            out.print("<script language='javascript' >alert('预约成功!');window.history.back();</script>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("<script language='javascript' >alert('预约失败!');window.history.back();</script>");
        }
//        SendSmsResponse re = null;
//        try {
//            re = sendSms(phone,"SMS_169110084",doctor_name,date,time);
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }

//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + re.getCode());
//        System.out.println("Message=" + re.getMessage());
//        System.out.println("RequestId=" + re.getRequestId());
//        System.out.println("BizId=" + re.getBizId());


//        PrintWriter printWriter = response.getWriter();
//        printWriter.print(doctor_name);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
