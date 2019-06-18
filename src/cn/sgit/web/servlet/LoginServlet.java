package cn.sgit.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.sgit.entity.User;
import cn.sgit.service.UserException;
import cn.sgit.service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//请求编码
		response.setContentType("text/html;charset=utf-8");//响应编码
		UserService userService=new UserService();
		//1、封装表单数据
		User form = new User();
		PrintWriter write = response.getWriter();
		try {
			//调用service的登录方法
			form.setUsername(request.getParameter("username"));
			form.setPassword(request.getParameter("password"));
			User user=userService.login(form);
			//没异常
			write.write("1");
		} catch (UserException e) {
			//有异常
			write.write(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
