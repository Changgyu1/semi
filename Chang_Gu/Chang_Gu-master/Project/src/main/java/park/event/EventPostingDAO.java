package park.event;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class EventPostingDAO {

	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "kiga";
	private static final String password = "kiga1234";

	public EventPostingDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int InsertEvent(String event_name, String event_day, String event_time, String event_location,
			double event_price, int event_age, Part event_imgs, String event_explain) throws IOException {

		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			String sql = "INSERT INTO EVENT(event_name, event_day, event_time, event_location, event_price,event_age, event_img,event_explain)"
					+ "VALUES(?, ?, ?, ?, ? ,? ,? ,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, event_name);
			ps.setString(2, event_day);
			ps.setString(3, event_time);
			ps.setString(4, event_location);
			ps.setDouble(5, event_price);
			ps.setInt(6, event_age);
			ps.setBinaryStream(7, event_imgs.getInputStream(), (int) event_imgs.getSize());
			ps.setString(8, event_explain);

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public EventPosting getEventInfoNotImage(int event_number) {
		EventPosting eventPosting = null;
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL, username, password);
			String sql = "SELECT * FROM event where event_number = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, event_number);
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				Blob blob = result.getBlob("event_img");
				event_number = result.getInt("event_number");
				String event_name = result.getString("event_name");
				String event_day = result.getString("event_day");
				String event_time = result.getString("event_time");
				String event_location = result.getString("event_location");
				double event_price = result.getDouble("event_price");
				int event_age = result.getInt("event_age");
				String event_explain = result.getString("event_explain");
				
				eventPosting = new EventPosting(event_number, event_name, event_day, event_time, event_location, event_price, event_age, event_explain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eventPosting;
	}
	
	public int updateEvent(int event_number, String event_name, String event_location, String event_day,
			String event_time, double event_price, int event_age, Part event_imgs, String event_explain) throws IOException {
		String sql = "UPDATE EVENT SET event_name = ?, event_location = ?, event_day = ?, event_time= ?, event_price = ?, event_age = ?, event_img = ?, event_explain = ? WHERE event_number = ?";
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL, username, password);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, event_name);
			ps.setString(2, event_location);
			ps.setString(3, event_day);
			ps.setString(4, event_time);
			ps.setDouble(5, event_price);
			ps.setInt(6, event_age);
			ps.setBinaryStream(7, event_imgs.getInputStream(), (int) event_imgs.getSize());
			ps.setString(8, event_explain);
			ps.setInt(9, event_number);
			return ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return -1; // DB 오류
	}
	
	public EventPosting getEventInfo(int event_number) {
		EventPosting eventPosting = null;

		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL, username, password);
			String sql = "SELECT * FROM event where event_number = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, event_number);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				Blob blob = result.getBlob("event_img");
				event_number = result.getInt("event_number");
				String event_name = result.getString("event_name");
				String event_day = result.getString("event_day");
				String event_time = result.getString("event_time");
				String event_location = result.getString("event_location");
				double event_price = result.getDouble("event_price");
				int event_age = result.getInt("event_age");
				String event_explain = result.getString("event_explain");
				byte[] imageBytes = blob.getBytes(1, (int) blob.length());

				String imageBase64 = java.util.Base64.getEncoder().encodeToString(imageBytes);
				String event_img = "data:image/jpeg;base64, " + imageBase64;

				eventPosting = new EventPosting(event_number, event_name, event_day, event_time, event_location,
						event_price, event_age, event_img, event_explain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eventPosting;
	}
	
	

	public int delete(int event_number) {
		String sqlEvent = "DELETE EVENT WHERE event_number = ?";
		String sqlQna = "DELETE QNA WHERE event_number = ?";
		String sqlReview = "DELETE REVIEW WHERE event_number = ?";
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL, username, password);
			PreparedStatement event = conn.prepareStatement(sqlEvent);
			PreparedStatement qna = conn.prepareStatement(sqlQna);
			PreparedStatement review = conn.prepareStatement(sqlReview);
			event.setInt(1, event_number);
			qna.setInt(1, event_number);
			review.setInt(1, event_number);
			return qna.executeUpdate() + review.executeUpdate() + event.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return -1; // DB 오류
	}



	public List<EventPosting> getAllProducts(int pageNumber, int pageSize) {
		List<EventPosting> EventPaginationList = new ArrayList<>();
		int start = EventPaginationUtil.paginationStart(pageNumber, pageSize);
		int end = EventPaginationUtil.paginationEnd(pageNumber, pageSize);

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, username, password);
			String sql = "SELECT * FROM (SELECT e.*, ROWNUM AS rnum FROM (SELECT * FROM event ORDER BY event_number DESC) e WHERE ROWNUM <= ?) WHERE rnum >= ?";
			/*
			 * String sql = "SELCT * FROM products " //products 테이블에서 +
			 * "ORDER BY product_id " //product_id 기준으로 정렬할 것 임 // 지정된 OFFSET FETCH NEXT페이지를
			 * 가지고 옴 + "OFFSET ? " // OFFSET : 가져오기를 시작할 행의 위치 나타냄 + "ROWS FETCH NEXT? "
			 * //FETCH NEXT : 다음에 가져올 행의 수 지정 + "ROWS ONLY";
			 */
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, end);
			ps.setInt(2, start);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EventPosting eventPagination = new EventPosting();
				eventPagination.setEvent_number(rs.getInt("event_number"));
				eventPagination.setEvent_name(rs.getString("event_name"));
				eventPagination.setEvent_day(rs.getString("event_day"));
				eventPagination.setEvent_time(rs.getString("event_time"));
				eventPagination.setEvent_location(rs.getString("event_location"));
				eventPagination.setEvent_price(rs.getDouble("event_price"));

				Blob blob = rs.getBlob("event_img"); // 이미지 인코딩
				byte[] imageBytes = blob.getBytes(1, (int) blob.length());
				String imageBase64 = java.util.Base64.getEncoder().encodeToString(imageBytes);
				String event_img = ("data:image/jpeg;base64, " + imageBase64);
				eventPagination.setEvent_img(event_img);
				eventPagination.setEvent_explain(rs.getString("event_explain"));
				EventPaginationList.add(eventPagination);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EventPaginationList;
	}

	// 2. 전체 제품 수를 가지고 오는 메서드
	public int getTotalProducts() {
		int totalProducts = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, username, password);

			String sql = "SELECT COUNT(*) AS total FROM event";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalProducts = rs.getInt("total");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalProducts;
	}

}
