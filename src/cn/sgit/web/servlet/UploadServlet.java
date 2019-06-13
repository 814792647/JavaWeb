package cn.sgit.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
		response.setContentType("text/html;charset=utf-8");
		DiskFileItemFactory factory=new DiskFileItemFactory();
		File f =new File("E:\\upload");
		if(!f.exists()){
			f.mkdirs();
		}
		//设置文件缓存路径
		factory.setRepository(f);
		//创建ServletFileUpload对象
		ServletFileUpload fileupload=new ServletFileUpload(factory);
		//设置字符编码
		fileupload.setHeaderEncoding("utf-8");
		//解析request,得到fileItem对象
		
			List<FileItem> fileitems=fileupload.parseRequest(request);
			PrintWriter write=response.getWriter();
			//遍历集合
			for(FileItem  fileitem :fileitems){
				//判断是否为普通字段
				if(fileitem.isFormField()){
					//获得字段名
					String name =fileitem.getFieldName();
					if(name.equals(name)){
						if(!fileitem.getString().equals("")){
							String value =fileitem.getString("utf-8");
							write.print("上传者"+value+"<br/>");
						}
						}
					}else{
						//获取上传的文件名
						String  filename= fileitem.getName();
						//处理上传文件
						if(fileitem !=null&& !fileitem.equals("")){
							write.print("上传的文件名称是："+filename+"<br/>");
							//截取出文件名
							filename =filename.substring(filename.lastIndexOf("\\")+1);
							//文件名需要唯一
							filename =UUID.randomUUID().toString()+"_"+filename;
							//在服务器创建同名文件
							String webpath="/upload/";
							String filepath =getServletContext().getRealPath(webpath+filename);
							//创建文件
							File file=new File(filepath);
							file.getParentFile().mkdirs();
							file.createNewFile();
							InputStream in=fileitem.getInputStream();
							FileOutputStream out=new FileOutputStream(file);
							byte[]buffer= new byte[1024];//每次读取1个字节
							int len;
							//开始读取上传文件的字节，并输出到服务期端的上传文件输出流中
							while((len=in.read(buffer))>0){
								out.write(buffer,0,len);
								//关闭流
								in.close();
								out.close();
								//删除临时文件
								fileitem.delete();
								write.print("上传文件成功！<br/>");						
							}
						}
					}
				}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		doGet(request,response);
	}

}
