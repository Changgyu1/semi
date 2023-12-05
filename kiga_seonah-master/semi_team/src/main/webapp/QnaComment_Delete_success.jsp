<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sa_event.Qna" %>
<%@ page import="sa_event.QnaDAO" %>
<%@ page import="sa_event.QnaCommentDAO" %> 
<%@ page import="sa_event.QnaComment" %>
<%@ page import = "java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>댓글삭제 성공</title>
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

	<%
	int qua_comment_password = Integer.parseInt((request.getParameter("qna_comment_password")));
	int qna_comment_number = Integer.parseInt((request.getParameter("qna_comment_number")));
		
	QnaCommentDAO qnaCommentDAO = new QnaCommentDAO();
	
	int result = qnaCommentDAO.DeleteComment(qua_comment_password, qna_comment_number);
	
	if (result == -1) { // 글삭제 실패시
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('댓글 삭제에 실패했습니다.')");
		script.println("location.href = 'Qna_List.jsp'"); // 이전 페이지로 사용자를 보냄
		script.println("</script>");
	} else { // 글삭제 성공시
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('댓글 삭제에 성공하였습니다.')");
		script.println("location.href = 'Qna_List.jsp"); // 문의게시판으로 이동
		script.println("</script>");
	}
	
%>


<button type="button" onclick="location.href='Qna_List.jsp'">돌아가기</button>

</body>
</html>