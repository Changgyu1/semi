<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 행사 게시판</title>
	<link rel="stylesheet" href="./css/event.css">
	<style>
        .event_form{
            padding: 10px;
            margin: 10px;
            text-align: center;
            width: 103%;
        }
        #event_insert_form_name,#event_insert_form_location{
            padding: 5px;
            margin: 5px;
            width: 50%;
        }
        #event_insert_form_day,#event_insert_form_time{
            padding: 5px;
            margin: 5px;
            width: 30%;
        }
        #event_insert_form_price,#event_insert_form_age{
            padding: 5px;
            margin: 5px;
        }
        #event_insert_form_explain{
            width: 80%;
            height: 200px;
        }
        .event_insert_submit{
            margin: 5px;
        }
	</style>
</head>

<body>
	<div>
		<img src="./image/로고1.png" id="logo" onclick="location.href='home.jsp'">
 <!--로그인 버튼-->
	 	<div style="text-align: right; width: 1215px;">
		    <%  if(session.getAttribute("email")!=null){ %>
	 		<button type="button" onclick="location.href='logout.jsp'" style="background:none;border:none;width:75px;">로그아웃</button>
	 		 <h>|</h>
	    	<button type="button" onclick="location.href='mypageServlet?email=<%=session.getAttribute("email")%>'" style="background:none;border:none;width:100px;">마이페이지</button>
	 		<% }else{ %>
		 	<button type="button" onclick="location.href='login.jsp'" style="background:none;border:none;width:60px;">로그인</button>
		 	<h>|</h>
	    	<button type="button" onclick="location.href='join.jsp'" style="background:none;border:none;width:100px;">회원가입</button>
			<% } %>
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
		<h3 style="text-align:center;">행사글 작성</h3>
		<div id="whitebox">
			<div>
				<form action="EventServlet" enctype="multipart/form-data" method="post">
				<div class="event_form">
					<label for="event_name">행사 제목 : </label>
					<input type="text" placeholder="제목을 입력해주세요." id="event_insert_form_name" name="event_name" required style="text-align:center;"><br>
    				<label for="event_location">행사 장소 : </label>
					<input type="text" id="event_insert_form_location" name="event_location" required> <br>	
					<label for="event_day"> 행사 기간 : </label>
					<input type="text" id="event_insert_form_day" name="event_day" required>
					<label for="event_time">행사 시간 : </label>
					<input type="text" id="event_insert_form_time" name="event_time" required> <br>
					<label for="event_price">가 격 : </label>
					<input type="number" id="event_insert_form_price" name="event_price" required>
					<label for="event_age">연 령 : </label>
					<input type="number" id="event_insert_form_age" name="event_age" oninput='handleOnInput(this, 2)' required>
    				<label for="event_img">사진 : </label>
					<input type="file" multiple="multiple" name="event_img" id="event_insert_form_img" required><br>	
					<label for="event_explain">내용 : </label> <br>
					<textarea id="event_insert_form_explain" name="event_explain" required style="text-align:center;"> </textarea><br>
					<input type="submit" value="게시글 작성" name="add" class="event_insert_submit">
				</div>
			</form>
			</div>
		</div>
	</div>

	<button onclick="location.href='Event_List.jsp'">돌아가기</button>

</div>

<script>

function handleOnInput(el, maxlength) {
	  if(el.value.length > maxlength)  {
	    el.value 
	      = el.value.substr(0, maxlength);
	  }
	}
</script>
</body>
</html>