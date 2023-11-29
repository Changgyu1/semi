package gaeul.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Review_InsertDAO {

	String festivalurl="jdbc:oracle:thin:@localhost:1521:xe";   
	String festivaluser="kiga";  
	String festivalpassword="kiga1234";   
	private Connection con;
	
	//리뷰 작성하기
	public int reviewInsert(String review, String review_title, int event_number) {
		
		try {
			con = DriverManager.getConnection(festivalurl, festivaluser, festivalpassword);
			
			String sql="insert into review (review,review_title,event_number,review_date) values(?,?,?,sysdate)";
			
			PreparedStatement ps =con.prepareStatement(sql);
			
			ps.setString(1, review);
			ps.setString(2, review_title);
			ps.setInt(3, event_number);
		
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return -1;
		
	}
	
	//댓글 작성하기
	public int commentInsert(String comment_content, int review_number, int comment_password) {
		try {
			con = DriverManager.getConnection(festivalurl, festivaluser, festivalpassword);
			String sql="insert into review_comment(comment_content,review_number,comment_password,comment_date) "
					+ "values(?,?,?,sysdate)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,comment_content);
			ps.setInt(2,review_number);
			ps.setInt(3,comment_password);
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	
	//리뷰 좋아요 누르기
	public int reviewLike(int review_number,String name) {
		try {
			con = DriverManager.getConnection(festivalurl, festivaluser, festivalpassword);
			String sql="insert into kiga_like(review_number,name) values(?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, review_number);
			ps.setString(2,name);
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	//댓글 좋아요 누르기
	public int commentLike(int comment_number,String name) {
		try {
			con = DriverManager.getConnection(festivalurl, festivaluser, festivalpassword);
			String sql="insert into kiga_like(comment_number,name) values(?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, comment_number);
			ps.setString(2, name);
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
}
