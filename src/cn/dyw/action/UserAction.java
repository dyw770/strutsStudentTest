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
 * 处理用户的action
 * @author dyw
 *
 */
public class UserAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用户
	private User user;
	
	//新闻
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
	 * 处理用户登录请求
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
				context.put("msg", "密码或者用户名错误");
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
	 * 发布新闻
	 * @return
	 */
	public String publish(){
		
		ActionContext context = ActionContext.getContext();
		if(news.getTitle().equals("")){
			context.put("msg", "标题是必须的");
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
				context.put("msg", "发布成功");
				return SUCCESS;
			} else {
				context.put("msg", "发布失败");
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
			context.put("msg", "发布失败");
			return ERROR;
		}
		
	}
	
	/**
	 * 
	 * 写信息
	 * @return
	 */
	public String infoWrite(){
		ActionContext context = ActionContext.getContext();
		if(student == null){
			System.out.println("s:null");
			context.put("msg", "错误");
			return ERROR;
		}
		
		boolean re = new InfoDao().writeInfo(student);
		
		if(re){
			context.put("msg", "成功");
			return SUCCESS;
		} else {
			context.put("msg", "错误");
			return ERROR;
		}
			
	}
}
