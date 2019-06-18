package cn.sgit.service;

import cn.sgit.dao.UserDao;
import cn.sgit.entity.User;

public class UserService {

	private UserDao userDao=new UserDao();

	public void regist(User user) throws UserException{

		User _user=userDao.findByUsername(user.getUsername());
		if(_user!=null) 
			throw new UserException("用户名"+user.getUsername()+"，已经被注册！");
		userDao.add(user);	
	}
	public User login(User form) throws UserException{
		User user=userDao.findByUsername(form.getUsername());
		if(user==null) throw new UserException(" 用户名不存在!");
		if(!form.getPassword().equals(user.getPassword()))
			throw new UserException(" 用户名或密码错误!");
		return user;
		
	}
	public void findAllDb(User user) {
		User _user =userDao.findAllDb(user.getUsername(), user.getType());
		
		
	}
}
