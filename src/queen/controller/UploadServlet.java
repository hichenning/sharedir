package queen.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import queen.common.define.FileType;
import queen.service.ReadConfigServcie;

@WebServlet(name = "UploadServlet",urlPatterns = "/UploadServlet.action",loadOnStartup = 1)
public class UploadServlet extends HttpServlet {
    ReadConfigServcie readConfigServcie = new ReadConfigServcie();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        servletFileUpload.setHeaderEncoding("utf-8");
        HttpSession session = request.getSession();
        String uploadfile = (String) session.getAttribute("currentDir");
        if(uploadfile == null){
            uploadfile = readConfigServcie.getSharePath();
        }
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            if(!fileItems.isEmpty()){
                for(FileItem fileItem : fileItems){
                    if(!fileItem.isFormField()){
                        String filename = new File(fileItem.getName()).getName();
                        String filePaht = uploadfile + File.separator + filename;
                        File storageFile = new File(filePaht);
                        fileItem.write(storageFile);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("getFilsList?fatherPath="+uploadfile).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
