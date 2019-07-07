package servlet;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static util.AliyunSmsUtils.getNewcode;
import  static util.AliyunSmsUtils.sendSms;
import static util.AliyunSmsUtils.setNewcode;

/**
 * Created by ZC on 2019/6/24.
 */
@WebServlet(name = "SendCode",urlPatterns = "/sendCode")
public class SendCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();


        String phone = request.getParameter("phone").trim();
        session.setAttribute("phone",phone);
        System.out.println(phone);


        setNewcode();
        String code = Integer.toString(getNewcode());


        session.setAttribute("code",code);
        SendSmsResponse re = null;
        try {
            re = sendSms(phone,"SMS_168725374",code);
        } catch (ClientException e) {
            e.printStackTrace();
        }

//        SendSmsResponse response = sendSms(phone,code);
        System.out.println("刚刚发的验证码"+code);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + re.getCode());
        System.out.println("Message=" + re.getMessage());
        System.out.println("RequestId=" + re.getRequestId());
        System.out.println("BizId=" + re.getBizId());




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);

    }
}
