package queen.controller;

import queen.common.entity.UserInfo;
import queen.service.ReadConfigServcie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/loginServlet.view",loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
    ReadConfigServcie userinfoServcie = new ReadConfigServcie();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("myname");
        String password= request.getParameter("mypass");
        UserInfo userInfo = userinfoServcie.getUserinfo();
        if(userInfo.getUserId().equals(id) && userInfo.getPassword().equals(password)){
            request.getSession().setAttribute("userinfo",userInfo);
            request.getRequestDispatcher("showFileServlet").forward(request,response);
        }else {
            request.getSession().setAttribute("loginmsg","miss id or password");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
