package cn.sgit.web.servlet;

import java.io.IOException;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//请求编码
		response.setContentType("text/html;charset=utf-8");//响应编码
		UserService userService=new UserService();
		//1、封装表单数据
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			//调用service的登录方法
			User user=userService.login(form);
			//没异常
			request.getSession().setAttribute("sessionUser", user);
			response.sendRedirect(request.getContextPath()+"/oa.jsp");
		} catch (UserException e) {
			//有异常
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/register.jsp").forward(request,response);
		}
	}

}
