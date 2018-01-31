package queen.controller;


import queen.service.ReadConfigServcie;
import queen.service.Resouse;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Properties;

@WebServlet(name = "ShowChildFiles",urlPatterns = "/getFilsList",loadOnStartup = 1)
public class ShowChildFiles extends HttpServlet {
    Resouse resouse = new Resouse();
    ReadConfigServcie readConfigServcie = new ReadConfigServcie();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("fatherPath");
        Properties properties = readConfigServcie.getConfigProperties();
        String  sharepath = properties.getProperty("sharepath");
        System.out.println("filePath : "+filePath);
        if(filePath.indexOf(sharepath) == -1){
            request.getSession().setAttribute("loginmsg","非法路径");
            request.getRequestDispatcher("index.jsp").forward(request,response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("currentDir",filePath);
        if(filePath != null && !"".equals(filePath)) {
            //filePath = filePath.replace("%20"," ");
            File file = new File(filePath);
            if(file.isDirectory()){
                String data = resouse.getChildList(filePath);
                if(data != null){
                    request.getSession().setAttribute("data",data);
                    request.getRequestDispatcher("home.jsp").forward(request,response);
                }
            }else {
                download(response,filePath);
            }
        }
//        String data = resouse.getChildList(filePath);
//        if(data != null){
//            request.getSession().setAttribute("data",data);
//            request.getRequestDispatcher("home.jsp").forward(request,response);
//        }
//        download(response,"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void download(HttpServletResponse response,String filePath) throws UnsupportedEncodingException {
        //filePath = "E:/EBook/《东方快车谋杀案》.txt";
        File file = new File(filePath);
        String[] names = filePath.split("/");
        String downloadFileName = names[names.length-1];
        //推测 浏览器解析url使用iso编码格式
        downloadFileName = new String(downloadFileName.getBytes("UTF-8"), "iso-8859-1");
        response.setHeader("Content-Disposition","attachment;filename="+downloadFileName);
        try {
            OutputStream outputStream = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int k = 0;
            while ((k = fileInputStream.read(buffer,0,buffer.length)) != -1){
                outputStream.write(buffer,0,k);
            }
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;

    }
}
