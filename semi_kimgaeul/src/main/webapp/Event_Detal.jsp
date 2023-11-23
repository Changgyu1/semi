<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="park.event.EventPosting"%>
<%@ page import="park.event.EventPostingDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="./css/event.css">
<style>
	.sese{
	margin: 30px;
	padding: 30px;
	float: left;
	height: 500px;
	}
	.sese2{
		padding: 10px;
		text-align: center;
	}
	.sese3{
		position: absolute;
        padding: 20px;
        top: 50%;
        left: 75%; 
        transform: translate(-50%, -50%);
	}
</style>
</head>
<body>
	<% 
		String EventPostingValue = request.getParameter("event_number");
		int event_number = Integer.parseInt(EventPostingValue);
		
		EventPostingDAO eventPostingDAO = new EventPostingDAO();
		
		EventPosting eventPosting = eventPostingDAO.getEventInfo(event_number);
	%>
	<div class="diva">
		<!-- 로고 이미지 -->
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

	<!-- 메뉴바 -->
	<div id="buttons">
		<ul>
			<li onclick="location.href='review_list.jsp'">후기</li>
			<li onclick="location.href='Qna_List.jsp'">QNA</li>
			<li onclick="location.href='reservation_search.jsp'">예약조회</li>
			<li onclick="location.href='Event_List.jsp'">행사정보</li>
		</ul>
	</div>


		<!-- 가운데 가장 큰 박스 -->
		<div id="background">
            <h1 class="as"></h1>
			<img src="">
		

				<div class="sese">
					<img src="<%=eventPostingDAO.Image(event_number) %>" alt="event_img" weight="300px", height="300px">
                    

			</div>
			
            <h1><%=eventPosting.getEvent_name() %></h1>
            <br>
            			<div class="sese3">
					<table class="sese2">
						<thead>
							<tr>
								<th class="jb-th-1"> <img src="./image/지도아이콘.png" style="width: 20px; height: 20px;"> 장 소 :</th>
								<th><%=eventPosting.getEvent_location() %></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td> <img src="./image/지도아이콘.png" style="width: 20px; height: 20px;" > 행사 기간 :</td>
								<td><%=eventPosting.getEvent_day() %></td>
							  </tr>
							  <tr>
								<td> <img src="./image/지도아이콘.png" style="width: 20px; height: 20px;" > 진행 시간 :</td>
								<td><%=eventPosting.getEvent_time() %></td>
							  </tr>
							  <tr>
								<td>연 령 :</td>
								<td><%=eventPosting.getEvent_age() %></td>
							  </tr>
							  <tr>
								<td>가 격 :</td>
								<td><%=eventPosting.getEvent_price() %></td>
							  </tr>
							  <tr>
								<td>행사 설명 :</td>
								<td> <%=eventPosting.getEvent_explain() %></td>
							  </tr>
						</tbody>
					</table>
					<input type="button" value="예약하기" onclick="location.href='reservation.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
				</div>
            </div>
            
            
           	  <div style="text-align: right; width: 1215px;">
           	  
				    <%//로그인시 후기 보기만 가능
				    if(session.getAttribute("email")!=null){
				    %>
				    <input type="submit" value="후기보기" onclick="location.href='review_searchlist.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
				    <input type="submit" value="후기 작성하기" onclick="location.href='review_write.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
					 <%//비로그인 시 후기 작성,후기 보기 가능
					 }else{
					 %>
				    <input type="submit" value="후기보기" onclick="location.href='review_searchlist.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
					 
					  <%
					  }
					  %>
				</div>
            
            
           
				<input type="submit" value="목록가기" onclick="location.href='Event_List.jsp'">
				<input type="submit" value="수정하기"
					onclick="location.href='Event_Update.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
				<input type="submit" value="삭제하기"
					onclick="location.href='Event_delete_success.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
			</div>
          

		<!-- 페이지 넘기는 버튼-->

	</div>
			<div id="Pagebutton">
			<button id="prevbutton">이전</button>

			<button id="nextbutton">다음</button>
		</div>

</body>
</html>