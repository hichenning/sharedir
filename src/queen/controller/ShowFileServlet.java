package queen.controller;

import queen.service.ReadConfigServcie;
import queen.service.Resouse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "ShowFileServlet",urlPatterns = "/showFileServlet",loadOnStartup = 1)
public class ShowFileServlet extends javax.servlet.http.HttpServlet {
    Resouse resouse = new Resouse();
    ReadConfigServcie readConfigServcie = new ReadConfigServcie();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String data = resouse.getChildList();
        Properties properties = readConfigServcie.getConfigProperties();
        String  sharepath = properties.getProperty("sharepath");
        request.getSession().setAttribute("currentDir",sharepath);
        request.getSession().setAttribute("data",data);
        request.getRequestDispatcher("/home.jsp").forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
