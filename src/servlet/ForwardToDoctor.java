package servlet;

import com.sun.istack.internal.Nullable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ZC on 2019/6/25.
 */

@WebServlet(name = "ForwardToDoctor", urlPatterns = "/forwardToDoctor")

public class ForwardToDoctor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();


        String depId = request.getParameter("depId").trim();
        session.setAttribute("depId",depId);


        //request.getRequestDispatcher("timeSelect.jsp").forward(request,response);
        request.getRequestDispatcher("dateSelect.jsp").forward(request,response);
        System.out.print("aaa"+depId);
        // System.out.println("aaaa"+depId+"    "+time);

       // request.getRequestDispatcher().forward(request,response);
//        switch (Integer.parseInt(depId)){
//            case 1:
//                request.getRequestDispatcher().forward(request,response);
//        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
