package cn.dyw.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.dyw.dao.InfoDao;
import cn.dyw.entity.News;
import cn.dyw.entity.Student;
import cn.dyw.entity.User;
import cn.dyw.utils.ConGet;

/**
 * �����û���action
 * @author dyw
 *
 */
public class UserAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//�û�
	private User user;
	
	//����
	private News news;
	
	
	private Student student;
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}




	private ConGet conGet = new ConGet();
	
	/**
	 * �����û���¼����
	 * @return
	 */
	public String loginAction(){
		Connection con = conGet.getCon();
		ActionContext context = ActionContext.getContext();
		String sql = "SELECT * FROM `user` WHERE `name`= ? AND `pwd` = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPwd());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				user.setId(rs.getInt(1));
				user.setIsAdmin(rs.getInt(4));
			}
			con.close();
			if(user.getId() != 0){
				context.put("user", user);
				context.getSession().put("user", user);
				if(user.getIsAdmin() == 1){
					return INPUT;
				} else {
					return SUCCESS;
				}
			} else {
				context.put("msg", "��������û�������");
				return ERROR;
			}
			
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return ERROR;
	}
	
	
	/**
	 * 
	 * ��������
	 * @return
	 */
	public String publish(){
		
		ActionContext context = ActionContext.getContext();
		if(news.getTitle().equals("")){
			context.put("msg", "�����Ǳ����");
			return ERROR;
		}
		User user1 = (User) context.getSession().get("user");
		ConGet conGet = new ConGet();
		String sql = "INSERT `news` (`title`,`context`,`time`,`author_id`) VALUE (?,?,?,?)";
		Connection con = conGet.getCon();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, news.getTitle());
			pst.setString(2, news.getContext());
			pst.setString(3, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			pst.setInt(4, user1.getId());
			int re = pst.executeUpdate();
			con.close();
			if(re == 1){
				context.put("msg", "�����ɹ�");
				return SUCCESS;
			} else {
				context.put("msg", "����ʧ��");
				return ERROR;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			context.put("msg", "����ʧ��");
			return ERROR;
		}
		
	}
	
	/**
	 * 
	 * д��Ϣ
	 * @return
	 */
	public String infoWrite(){
		ActionContext context = ActionContext.getContext();
		if(student == null){
			System.out.println("s:null");
			context.put("msg", "����");
			return ERROR;
		}
		
		boolean re = new InfoDao().writeInfo(student);
		
		if(re){
			context.put("msg", "�ɹ�");
			return SUCCESS;
		} else {
			context.put("msg", "����");
			return ERROR;
		}
			
	}
}
