package hj.event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReservationSearchDAO {
	private static final String jdbcURL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUserName="kiga";
	private static final String jdbcPassword="kiga1234"; 
	private static HttpServletResponse response;
	private static HttpServletRequest request;
		
	public ReservationSearchDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ReservationDTO getRInfo(int r_id, String email) {
		ReservationDTO r = null;
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			
			//������ȸ�� ã�� ��ȣ��, �̸���
			String sql = "select r.r_id, r.r_email, r.r_phonenumber,person, e.event_name, e.event_day, e.event_location ,(e.event_price*r.person)as total\r\n"
					+ "from reservation r, event e\r\n"
					+ "where r.event_number=e.event_number\r\n"
					+ "and r.r_id = ? and r_email=? ";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, r_id);
			ps.setString(2, email);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				int id = resultSet.getInt("r_id");
				String r_email = resultSet.getString("r_email");
				String phonenumber = resultSet.getString("r_phonenumber");
				int person = resultSet.getInt("person");				
				String event_name = resultSet.getString("event_name");
				String event_day = resultSet.getString("event_day");
				String event_location = resultSet.getString("event_location");
				String total = resultSet.getString("total");
				r = new ReservationDTO(id, r_email, phonenumber, person, event_name, event_day, event_location, total);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public void updateInfo(int r_id, int person) {
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			String sql = "update reservation set person = ? where r_id =?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, person );
			ps.setInt(2, r_id);
			
			ResultSet resultSet = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void mypage_info_update(String phonenumber, String email) throws IOException {
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			
			String sql = "update users set phonenumber = ? where email = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phonenumber);
			ps.setString(2, email);
			
			ps.executeUpdate();
			
			//response.sendRedirect("mypage_update_success.jsp");
		} catch (SQLException e) {
		
              response.sendRedirect("mypage_update_error.jsp");
         
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resign(String email, String password) {
		try {
			String sql = "DELETE Users WHERE email = ? AND password = ?";
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void login(String email, String password, HttpServletResponse response) {
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			
			//������ �����ϴ��� Ȯ��
			String sql = "select email, name, phonenumber from users where email=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				session.setAttribute("name", resultSet.getString("name"));
				session.setAttribute("phonenumber", resultSet.getString("phonenumber"));
				response.sendRedirect("login_success.jsp");
			}else {
				request.setAttribute("loginError", "true");
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
			
		
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		}
	}
}
