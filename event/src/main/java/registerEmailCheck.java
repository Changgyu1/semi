
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

        String email = request.getParameter("email");
    	ReservationSearchDAO rdao =new ReservationSearchDAO();
    	String redirectURL = rdao.registerEmailCheck(email);
    	response.sendRedirect(redirectURL);
    }
}