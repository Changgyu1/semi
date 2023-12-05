package sa_event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class QnaDAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "kiga";
	private static final String jdbcPassword = "kiga1234";
	private Connection connection;
	
	public QnaDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public int InsertQna(int event_number, String qna_title, String qna_content, int qna_password, String qna_name, String qna_email) {
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "INSERT INTO QNA (event_number, qna_title, qna_content, qna_password, qna_name, qna_date, qna_email) VALUES (?,?,?,?,?,sysdate,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, event_number);
			ps.setString(2, qna_title);
			ps.setString(3, qna_content);
			ps.setInt(4, qna_password);
			ps.setString(5, qna_name);
			ps.setString(6, qna_email);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return -1;
		
	}
	
	public int NormalInsertQna(String qna_title, String qna_content, int qna_password, String qna_name, String qna_email) {
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "INSERT INTO QNA (qna_title, qna_content, qna_password, qna_name, qna_date, qna_email) VALUES (?,?,?,?,sysdate,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, qna_title);
			ps.setString(2, qna_content);
			ps.setInt(3, qna_password);
			ps.setString(4, qna_name);
			ps.setString(5, qna_email);
			
			return ps.executeUpdate(); //성공할 경우 +1
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -1;
	}	


	
	public ArrayList<Qna> getAllQna(int qna_number) {
		
		ArrayList<Qna> qnas = new ArrayList<Qna>();
		
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM qna WHERE qna_number = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, qna_number);
			
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				qna_number = resultSet.getInt("qna_number");
				int event_number = resultSet.getInt("event_number");
				String qna_title = resultSet.getString("qna_title");
				String qna_content = resultSet.getString("qna_content");
				int qna_password = resultSet.getInt("qna_password");
				String qna_name = resultSet.getString("qna_name");
				Date qna_date = resultSet.getDate("qna_date");
				String qna_email = resultSet.getString("qna_email");
				
				Qna qna = new Qna();
				qna.setQna_number(qna_number);
				qna.setEvent_number(event_number);
				qna.setQna_title(qna_title);
				qna.setQna_content(qna_content);
				qna.setQna_password(qna_password);
				qna.setQna_name(qna_name);
				qna.setQna_date(qna_date);
				qna.setQna_email(qna_email);
				
				qnas.add(qna);

			}
			resultSet.close();
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return qnas;
	}
	
	//QnaList
	public ArrayList<Qna> getChoiceQna(){
		
		ArrayList<Qna> qnas = new ArrayList<Qna>();
		
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM qna ORDER BY qna_number ASC";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int qna_number = rs.getInt("qna_number");
				int event_number = rs.getInt("event_number");
				String qna_title = rs.getString("qna_title");
				String qna_name = rs.getString("qna_name");
				String qna_email = rs.getString("qna_email");
				
				Qna qna = new Qna();
				qna.setQna_number(qna_number);
				qna.setEvent_number(event_number);
				qna.setQna_title(qna_title);
				qna.setQna_name(qna_name);
				qna.setQna_email(qna_email);
				
				qnas.add(qna);
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return qnas;
		
	}
	public int DeleteQna(int qna_number) {
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "DELETE qna WHERE qna_number = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, qna_number);
			
			return ps.executeUpdate();
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return -1;

	}
	
	public Qna getQnaInfoALl(int qna_number) {
		Qna qna = null;
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM qna where qna_number = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, qna_number);
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				qna_number = resultSet.getInt("qna_number");
				int event_number = resultSet.getInt("event_number");
				String qna_title = resultSet.getString("qna_title");
				String qna_content = resultSet.getString("qna_content");
				int qna_password = resultSet.getInt("qna_password");
				String qna_name = resultSet.getString("qna_name");
				Date qna_date = resultSet.getDate("qna_date");
				String qna_email = resultSet.getString("qna_email");
				
				 
				qna = new Qna(qna_number, event_number, qna_title, qna_content, qna_password, qna_name, qna_date, qna_email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qna;
	}
	
	public int updateQna(String qna_title, String qna_content, int qna_number) {
	    String sql = "UPDATE qna SET qna_title = ?, qna_content = ? WHERE qna_number = ?";
	    try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, qna_title);
	        ps.setString(2, qna_content);
	        ps.setInt(3, qna_number);
	        return ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
		
	public int insertLike(int qna_number, String likename, String unlikename) {
	
			String sql="INSERT INTO QNA_LIKE (qna_number, likename, unlikename, like_date) VALUES (?,?,?,sysdate)";
			try {
				Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, qna_number);
				ps.setString(2, likename);
				ps.setString(3, unlikename);
								
				return ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return -1;		
		
	}
	
	public int selectLike(int qna_number) {
		int qnalike = 0;
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT COUNT(DISTINCT likename) as qnalike FROM qna_like WHERE qna_number = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, qna_number);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				qnalike = rs.getInt("qnalike");
				
				QnaLike q = new QnaLike();
				
				q.setQna_number(qna_number);
				q.setQnaCount(qnalike);
			}
	
		}	
		catch (SQLException e) {
			e.printStackTrace();
		}
		return qnalike;
	}
	
	public int selectUnlike(int qna_number) {
		int qnaunlike = 0;
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT COUNT(DISTINCT unlikename) as qnaunlike FROM qna_like WHERE qna_number = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, qna_number);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				qnaunlike = rs.getInt("qnaunlike");
				
				QnaLike q = new QnaLike();
				
				q.setQna_number(qna_number);
				q.setQnaCount(qnaunlike);
			}
	
		}	
		catch (SQLException e) {
			e.printStackTrace();
		}
		return qnaunlike;
	}
	
}

	

