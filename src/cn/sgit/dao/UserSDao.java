package cn.sgit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import cn.sgit.entity.SUser;
import cn.sgit.entity.User;

public class UserSDao {
	
	public List<SUser> findAll(){
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			con =JdbcUtils.getConnection();
			String sql="SELECT * FROM kpi_user";
			pre=con.prepareStatement(sql);
			rs =pre.executeQuery();
			if(rs==null){
				return null;
			}
			while(rs.next()){
				String username=rs.getString("username");
				String bm=rs.getString("bm");
				String zw=rs.getString("zw");
				SUser suser =new SUser();
				suser.setBm(bm);
				suser.setUsername(username);
				suser.setZw(zw);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
		
		}
	}

