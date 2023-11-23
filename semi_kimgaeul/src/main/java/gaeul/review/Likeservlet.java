package gaeul.review;

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
import javax.servlet.http.HttpSession;

@WebServlet("/Likeservlet")
public class Likeservlet extends HttpServlet {
	
	private String url="jdbc:oracle:thin:@localhost:1521:xe";
	private String user="kiga";
	private String pw="kiga1234";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection(url, user, pw);
			String sql="insert into kiga_like(review_number,name) values(?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			String name=request.getParameter("name");
			int review_number = Integer.parseInt(request.getParameter("review_number"));
			ps.setInt(1, review_number);
			ps.setString(2,name);
			ps.executeUpdate();
			System.out.println(name);
			
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			
			response.sendRedirect("choose_result.jsp?review_number="+review_number);
		} catch (SQLException e) {
			response.sendRedirect("review_error.jsp");
			e.printStackTrace();
		}

		
		
	}

}
