<%@page import="hj.event.ReservationSearchDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session.invalidate();
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	ReservationSearchDAO rdao = new ReservationSearchDAO();
	rdao.resign(email, password);
%>

<script>
    alert("회원탈퇴 되었습니다.");
    window.location.href = "home.jsp";
</script>