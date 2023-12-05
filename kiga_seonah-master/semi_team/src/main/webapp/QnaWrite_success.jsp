<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sa_event.Qna" %>
<%@ page import="sa_event.QnaDAO" %>
<%@ page import="java.io.PrintWriter" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의작성 성공</title>
	<link rel="stylesheet" href="./saCss/saCss.css">
</head>
	<body>
		<script>
		alert('게시글 작성 완료');
		location.href = 'Qna_List.jsp'
		</script>
	</body>
</html>