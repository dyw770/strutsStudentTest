package cn.dyw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.dyw.entity.Student;
import cn.dyw.utils.ConGet;

public class InfoDao {

	private Connection con;

	public InfoDao() {
		con = new ConGet().getCon();
	}

	public boolean writeInfo(Student s) {
		
		try {
			PreparedStatement pst = con.prepareStatement("INSERT struts.`student` "
					+ "(`name`,`age`,`sex`,`interesters`,`description`,`date`,`instructor`) "
					+ "VALUE (?,?,?,?,?,?,?)");
			pst.setString(1, s.getName());
			pst.setInt(2, s.getAge());
			pst.setInt(3, s.getSex());
			pst.setString(4, s.getInteresters());
			pst.setString(5, s.getJieSao());
			pst.setString(6, s.getDate());
			pst.setInt(7, s.getFuDaoYuan());
			int re = pst.executeUpdate();
			if(re == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
