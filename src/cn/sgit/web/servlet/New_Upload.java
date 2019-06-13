package cn.sgit.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/New_Upload")
public class New_Upload extends HttpServlet {

    public New_Upload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter write = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			for(FileItem  fileitem :fileItemList){
				if(fileitem.isFormField()){
					write.write("fileitem.getName()");
					System.out.println("filename: " + fileitem.getName());
					File file = new File("F:\\apache-tomcat-7.0.92\\www\\cache\\" + fileitem.getName());
					fileitem.write(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
