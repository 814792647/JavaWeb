package cn.sgit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

	private static Properties props=null;
	
	static{
		try {
			InputStream in=JdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			props=new Properties();
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			//类的加载
			Class.forName(props.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection
				(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
	}
	public static void free(Connection con,PreparedStatement pre,ResultSet rs){
		//6、释放资源(必须要执行的步骤)
		try {//为什么要加catch以后讲
			if(rs!=null){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(pre!=null){
					pre.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(con!=null){
					try{
						con.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}	
		}			
	}
}
