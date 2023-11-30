package gaeul.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Review_deleteDAO {


	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String user="kiga";
	private String pw="kiga1234";
	private Connection con;
	private PreparedStatement ps;
	
	
	//리뷰 삭제하기
	public int reviewDelete(int deletereview) {
		try {
			con=DriverManager.getConnection(url, user, pw);
			String sql="delete from review where review_number=?";
			ps=con.prepareStatement(sql);
			ps.setObject(1,deletereview);
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//댓글 삭제하기
	public int commentDelete(int comment_password,int comment_number) {
		try {
			con=DriverManager.getConnection(url, user, pw);
			String sql="delete from review_comment where comment_password=? and comment_number=?";

			ps=con.prepareStatement(sql);
			ps.setInt(1,comment_password);
			ps.setInt(2, comment_number);
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
