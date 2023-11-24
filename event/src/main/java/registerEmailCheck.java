
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hj.event.ReservationSearchDAO;


@WebServlet("/registerEmailCheck")
public class registerEmailCheck extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       /* String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String jdbcUsername = "kiga";
        String jdbcPassword = "kiga1234";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            String email = request.getParameter("email");
            String sql = "SELECT email FROM USERS WHERE email = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            int isNull = 0;
            String redirectURL;
            
            if (rs.next()) {
            	isNull = 2;
            	System.out.println("isTrue :" +isNull);
                redirectURL = "join.jsp?isTrue="+isNull;
            } else {
            	isNull=1;
            	System.out.println("isTrue :" +isNull);
            	redirectURL = "join.jsp?email=" + email + "&isTrue=" + isNull;
            }

            response.sendRedirect(redirectURL);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/
        String email = request.getParameter("email");
    	ReservationSearchDAO rdao =new ReservationSearchDAO();
    	String redirectURL = rdao.registerEmailCheck(email);
    	//System.out.println(redirectURL);
    	response.sendRedirect(redirectURL);
    }
}