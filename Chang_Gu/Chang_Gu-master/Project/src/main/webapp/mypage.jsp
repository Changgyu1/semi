<%@page import="java.sql.PreparedStatement"%>
<%@page import="sa_event.User" %>
<%@page import=" java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>GO, FESTIVAL 마이페이지</title>
<link rel="stylesheet" href="./css/saCss.css">
</head>

<body>
    <!-- 이미지 로고 -->
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

<!-- 메뉴바 -->
  <div id="buttons">
		<ul>
			<li onclick="location.href='review_list.jsp'">후기</li>
			<li onclick="location.href='Qna_List.jsp'">QNA</li>
			<li onclick="location.href='reservation_search.jsp'">예약조회</li>
			<li onclick="location.href='Event_List.jsp'">행사정보</li>
		</ul>
	</div>

     <div id="background">
     <h3 style="text-align:center;padding-top:20px;">마이페이지</h3>
	     <div id="whitebox">

		     <h2>회원 정보</h2>
		     <div>
		     	<%
					String email = (String) session.getAttribute("email");
					String name = (String) session.getAttribute("name");
					String phonenumber = (String) session.getAttribute("phonenumber");
				%>
				
				<p style="text-align:center;"> 이메일: <%=email %></p>
				<p style="text-align:center;"> 이름: <%=name %></p>
				<p style="text-align:center;"> 전화번호: <%=phonenumber %></p>
		     </div>
		     
		     <form action="UserDeleteServlet" method="post">
		     <h3 style="text-align:center;">회원 탈퇴를 원하시면 이메일과 비밀번호를 입력해주세요.</h3>
		     
		     <input type="text" name="email" placeholder="이메일" style="padding:5px; margin-left:500px;"><br>
			 <input type="text" name="password" placeholder="비밀번호" style="padding:5px;  margin-left:400px;"><br>
			 <p>
			 <input type="submit" value="탈퇴"
			 onclick="location.href='UserDelete.jsp?email=<%=request.getParameter("email") %>'">
			 </p>
			 </form>
		     </div>
	      </div>  
      </div> 
</body>
</html>