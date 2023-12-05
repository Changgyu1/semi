<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sa_event.Qna" %>
<%@ page import="sa_event.QnaDAO" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의게시판</title>
	<link rel="stylesheet" href="./saCss/saCss.css">
</head>
<body>
	
<img src="./image/로고1.png" id="logo">
 <!--로그인 버튼-->
    <div style="text-align: right; width: 1215px;">
    <%
    	if(session.getAttribute("email")!=null){
    %>
	 	<button type="button" onclick="location.href='logout.jsp'" style="width:75px;">로그아웃</button>
	 	 <h>|</h>
	    <button type="button" onclick="location.href='mypageServlet?email=<%=session.getAttribute("email")%>'" style="width:100px;">마이페이지</button>
	<%
	 	}else{
	%>
		 <button type="button" onclick="location.href='login.jsp'" style="width:60px;">로그인</button>
		 <h>|</h>
	    <button type="button" onclick="location.href='join.jsp'" style="width:100px;">회원가입</button>
	<%
	 	}
	%>	    
	</div>
	<div id="buttons">
		<ul>
			<li onclick="location.href='Event_List.jsp'">행사정보</li>
			<li onclick="location.href='reservation_search.jsp'">예약조회</li>
			<li onclick="location.href='Qna_List.jsp'">QNA</li>
			<li onclick="location.href='review_list.jsp'">후기</li>
		</ul>
	</div>

	<%Qna qna = new Qna(); %>
	
		<div id="background">
			<h3 style="text-align:center;" value="<%=qna.getQna_number() %>">문의글 수정</h3>
		<div id="whitebox">
		
			
			<form action="QnaServlet" method="post">
			
				<label for="qna_title">제목 : </label>
				<input type="text" id="qna_title" name="qna_title" value="<%=session.getAttribute("qna_title") %>" style="text-align:center;" required><br>	
				
				<label for="qna_content">내용 : </label>
				<input type="text" id="qna_content" name="qna_content" style="text-align:center;" value="<%=session.getAttribute("qna_content") %>" required><br>
				
				<input type="hidden" id="qna_number" name="qna_number" value="<%=session.getAttribute("qna_number") %>">
					
				<input type="submit" name="UpdateQna" value="문의 수정하기" style="width: 95px; height: 34px; border-radius: 5px; text-align: auto;">
			</form>	
				<input type="submit" value="돌아가기" onclick="location.href='Qna_List.jsp'" style="width: 85px; height: 34px; border-radius: 5px;">
			<br>

		</div>
		</div>


</body>
</html>

