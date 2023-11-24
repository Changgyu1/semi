package hj.event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

public class ReservationSearchDAO {
	private static final String jdbcURL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUserName="kiga";
	private static final String jdbcPassword="kiga1234"; 
		
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
	
	
	public void mypage_info_update(String phonenumber, String email, HttpServletRequest request) throws IOException {
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			
			String sql = "update users set phonenumber = ? where email = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phonenumber);
			ps.setString(2, email);
			
			ps.executeUpdate();
			HttpSession session = request.getSession();
            session.setAttribute("phonenumber", phonenumber);
			//response.sendRedirect("mypage_update_success.jsp");
		} catch (SQLException e) {
		
              //response.sendRedirect("mypage_update_error.jsp");
         
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
	
	public void reservationCancel(int r_id) {
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			
			String sql = "delete from reservation where r_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r_id);
			
			ps.executeUpdate();
			//response.sendRedirect("reservation_cancel_success.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//response.sendRedirect("reservation_cancel_error.jsp");
			e.printStackTrace();
		}
	}

	public void reservationInsert(String r_email, String r_phonenumber, int person, int event_number, HttpServletRequest request) {
		Connection connection;
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			String sql = "insert into reservation (r_email, r_phonenumber, person, event_number)"
					+"values(?,?,?,?)";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setString(1, r_email);
			preparedStatment.setString(2, r_phonenumber);
			preparedStatment.setInt(3, person);
			preparedStatment.setInt(4, event_number);
			
			preparedStatment.executeUpdate();
			
			request.getSession().setAttribute("r_email", r_email);
			request.getSession().setAttribute("r_phonenumber", r_phonenumber);
			request.getSession().setAttribute("person", person);
			request.getSession().setAttribute("event_number", event_number);
			
			String sql2 = "select r_id from reservation where rownum =1 order by r_id desc";
			PreparedStatement preparedStatment2 = connection.prepareStatement(sql2);
			ResultSet resultSet = preparedStatment2.executeQuery();
			if(resultSet.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("r_id", resultSet.getInt("r_id"));
			}
		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public ArrayList<ReservationDTO> mypage(String email, HttpServletRequest request){
			 ArrayList<ReservationDTO> rdto = null;
			try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			String query = "SELECT name, email, phonenumber FROM users WHERE email = ?";
			String sql = "select r.r_id, r.r_email, r.r_phonenumber, person, e.event_name, e.event_day, e.event_location, (e.event_price*r.person) as total "
					+ "from reservation r, event e "
					+ "where r.event_number=e.event_number AND r_email=? order by r.r_id";

			
			  PreparedStatement preparedStatement = connection.prepareStatement(query);
		      PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
		      preparedStatement.setString(1, email);
		      preparedStatement2.setString(1, email);
		        
		    ResultSet resultSet = preparedStatement.executeQuery();
	        ResultSet resultSet2 = preparedStatement2.executeQuery();
	        
	        if (resultSet.next()) {
	            // Retrieve user information
	        	HttpSession session = request.getSession();
				session.setAttribute("email", email);
				session.setAttribute("name", resultSet.getString("name"));
				session.setAttribute("phonenumber", resultSet.getString("phonenumber"));
				
				
	        }

	        rdto = new ArrayList<>();
	        
	        while(resultSet2.next()) {
	        	int id = resultSet2.getInt("r_id");
				String r_email = resultSet2.getString("r_email");
				String phonenumber = resultSet2.getString("r_phonenumber");
				int person = resultSet2.getInt("person");				
				String event_name = resultSet2.getString("event_name");
				String event_day = resultSet2.getString("event_day");
				String event_location = resultSet2.getString("event_location");
				String total = resultSet2.getString("total");
				ReservationDTO ReservationDTO = new ReservationDTO(id, r_email, phonenumber, person, event_name, event_day, event_location, total);
				
				rdto.add(ReservationDTO);
	        }
	        
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  rdto;
	}
		
	public void login(String email, String password, HttpServletRequest request) {
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
				
			}else {
				request.setAttribute("loginError", "true");
				//request.getRequestDispatcher("home.jsp").forward(request, null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String registerEmailCheck(String email) {
        String redirectURL =null;
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			String sql = "SELECT email FROM USERS WHERE email = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            int isNull = 0;
    
            
            if (rs.next()) {
            	isNull = 2;
            	System.out.println("isTrue :" +isNull);
                redirectURL = "join.jsp?isTrue="+isNull;
            } else {
            	isNull=1;
            	System.out.println("isTrue :" +isNull);
            	redirectURL = "join.jsp?email=" + email + "&isTrue=" + isNull;
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return redirectURL;
	}
	

}
