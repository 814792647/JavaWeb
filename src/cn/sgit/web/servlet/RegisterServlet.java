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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//POST请求编码
		response.setContentType("text/html;charset=utf-8");//响应编码
		UserService userService=new UserService();
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			//调用service的注册方法
			userService.regist(form);
			//如果这个方法没有问题（异常），输出“注册成功”
			response.getWriter().print("注册成功！");
		} catch (UserException e) {
			//如果这个方法有问题（异常），把错误信息保存到request域中，转发到register.jsp
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request,response);
		}
	}
}
