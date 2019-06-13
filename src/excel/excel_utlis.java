package excel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationContext;

import java.lang.reflect.Method;

@WebServlet("/excel_utlis")
public class excel_utlis extends HttpServlet {

    public excel_utlis() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class clazz = Class.forName("excel.excel_utlis");
			Method method = clazz.getMethod(request.getParameter("con"),HttpServletRequest.class,HttpServletResponse.class,ServletContext.class);
			method.invoke(clazz.newInstance(),request,response,this.getServletContext()); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void GetFlag(HttpServletRequest request, HttpServletResponse response,ServletContext context) {
		try {
			String id = request.getParameter("id");
			PrintWriter write = response.getWriter();
			try{
				context.getAttribute(id).toString();
			}catch(Exception e){
				context.setAttribute(id, "0");
			}
			write.write(context.getAttribute(id).toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void InitializeFlag(HttpServletRequest request, HttpServletResponse response,ServletContext context) {
		context.setAttribute(request.getParameter("id"), "0");
	}
	
	public void Xlsx2SQL(HttpServletRequest request, HttpServletResponse response,ServletContext context) {
		File file = new File("F:\\apache-tomcat-7.0.92\\www\\cache\\" + request.getParameter("id")+".xlsx");
		file.delete();
		
		try {
			PrintWriter write = response.getWriter();
			write.write("2");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
