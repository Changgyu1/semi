package sa_event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QnaLikeDAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "kiga";
	private static final String jdbcPassword = "kiga1234";
	private Connection connection;
	
	public int LikeSelect (int qna_number) {
		int qnalike = 0;
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT COUNT(qna_number) as qnalike FROM qna_like WHERE qna_number = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, qna_number);
			
			ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int qna_num = qna_number;
			int qlike = rs.getInt("qnalike");
			
			QnaLike q = new QnaLike();
			
			q.setQna_number(qna_num);
			q.setQna_number(qnalike);
		}
		
	}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return qnalike;
	}
	
}