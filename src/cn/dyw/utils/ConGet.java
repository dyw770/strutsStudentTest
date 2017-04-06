package cn.dyw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ��ȡ���ݿ�����
 * @author dyw
 *
 */
public class ConGet {
	
	private String user;
	
	private String pwd;
	
	private String url;

	public ConGet() {
		user = "root";
		pwd = "20152046";
		url = "jdbc:mysql:///struts";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getCon(){
		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
