package sa_event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String jdbcUsername = "kiga";
	String jdbcPassword = "kiga1234";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			String sql = "DELETE Users WHERE email = ? AND password = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ps.executeUpdate();
			
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("password", password);
			
			response.sendRedirect("UserDelete.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
