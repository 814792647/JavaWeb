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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//POST请求编码
		response.setContentType("text/html;charset=utf-8");//响应编码
		UserService userService=new UserService();
		User form= new User();
		PrintWriter write = response.getWriter();
		try {
				form.setUsername(request.getParameter("username"));
				form.setPassword(request.getParameter("password"));
				userService.regist(form);
				//如果这个方法没有问题（异常），输出“注册成功”
				write.write("{ \"status\":\"2\"}");
		} catch (UserException e) {
			
			write.write("{ \"status\":\"3\",\"msg\": \"用户名已存在\"}");
			e.printStackTrace();
		}
	}
}
