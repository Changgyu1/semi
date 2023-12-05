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

/**
 * Servlet implementation class QnaLikeInsertServlet
 */
@WebServlet("/QnaLikeInsertServlet")
public class QnaLikeInsertServlet extends HttpServlet {
	String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String jdbcUsername = "kiga";
	String jdbcPassword = "kiga1234";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			int qna_number = Integer.parseInt(request.getParameter("qna_number"));
			String name = request.getParameter("name");
			
			String sql = "INSERT INTO qna_like (qna_number, name) VALUES (?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, qna_number);
			ps.setString(2, name);
			
			System.out.println(qna_number);
			System.out.println(name);
			
			ps.executeUpdate();
			
			request.getSession().setAttribute("qna_number", qna_number);
			request.getSession().setAttribute("name", name);
			
			response.sendRedirect("Qna_Detail.jsp?qna_number="+qna_number);
			
		} catch (SQLException e) {
			response.sendRedirect("");
			e.printStackTrace();
		}
		
	}

}
