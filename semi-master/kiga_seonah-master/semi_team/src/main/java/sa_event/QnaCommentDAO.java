package sa_event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

import oracle.sql.DATE;

public class QnaCommentDAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "kiga";
	private static final String jdbcPassword = "kiga1234";
	
	
	public QnaCommentDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public int QnaInsertComment(int qna_number, String qna_comment_content, int qna_comment_password) {
	
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "INSERT INTO qna_comment(qna_number, qna_comment_content, qna_comment_date, qna_comment_password) VALUES (?,?,sysdate,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, qna_number);
			ps.setString(2, qna_comment_content);
			ps.setInt(3, qna_comment_password);
			return ps.executeUpdate();
			
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }return -1;
		
	
		
}
	//QnaDetail - commentList
	public ArrayList<QnaComment> getQnaCommentByqna_number(int qna_number) {
		ArrayList<QnaComment> commentList = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM qna_comment WHERE qna_number = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, qna_number);	
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				qna_number = rs.getInt("qna_number");
				int qna_comment_number = rs.getInt("qna_comment_number");
				String qna_comment_content = rs.getString("qna_comment_content");
				Date qna_comment_date = rs.getDate("qna_comment_date");
				int qna_comment_password = rs.getInt("qna_comment_password");
				
				QnaComment comment = new QnaComment(qna_comment_number, qna_number, qna_comment_content, qna_comment_date, qna_comment_password);

				commentList.add(comment);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block0 
			e.printStackTrace();
		}return commentList;
		
	} 
	public int DeleteComment(int qna_comment_password, int qna_comment_number) {
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "DELETE FROM qna_comment WHERE qna_comment_password=? AND qna_comment_number=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, qna_comment_password);
			ps.setInt(2, qna_comment_number);
			
			return ps.executeUpdate();
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return -1;

	}
	
}
