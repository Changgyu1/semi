package gaeul.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Review_updateDAO {
	
	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String user="kiga";
	private String pw="kiga1234";
	private Connection con;
	private PreparedStatement ps;

	
	public int reviewUpdate(String updateTitle,String updateReview,int updatenumber) {
		try {
			con = DriverManager.getConnection(url, user, pw);
			String sql="update review set review_title=? , review=? where review_number=?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1,updateTitle);
			ps.setString(2,updateReview);
			ps.setInt(3,updatenumber);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
