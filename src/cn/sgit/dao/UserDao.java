package cn.sgit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.sgit.entity.User;
import cn.sgit.utils.JdbcUtils;


public class UserDao {


	public User findByUsername(String username){
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		//1、注册驱动2、得到连接
		try {
			con=JdbcUtils.getConnection();
			String sql="SELECT * FROM t_user WHERE username=?";
			pre=con.prepareStatement(sql);

			pre.setString(1, username);

			rs=pre.executeQuery();
	
			if(rs==null){
				return null;
			}
			if(rs.next()){
				User user=new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.free(con, pre, rs);
		}
		return null;
	}

	public void add(User user){
		Connection con=null;
		PreparedStatement pre=null;

		try {
			con=JdbcUtils.getConnection();
			String sql="INSERT INTO t_user VALUES(?,?)";
			pre=con.prepareStatement(sql);

			pre.setString(1, user.getUsername());
			pre.setString(2, user.getPassword());
			
			pre.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.free(con, pre, null);
		}
	}
}
