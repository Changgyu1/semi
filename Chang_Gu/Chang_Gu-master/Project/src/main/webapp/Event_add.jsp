<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 행사 게시판</title>
<link rel="stylesheet" href="./cgCss/event.css">
<style>
        .event_form{
            padding: 10px;
            margin: 10px;
            text-align: center;
            width: 103%;
        }
        #event_name,#event_location{
            padding: 5px;
            margin: 5px;
            width: 50%;
        }
        #event_day,#event_time{
            padding: 5px;
            margin: 5px;
            width: 30%;
        }
        #event_price,#event_age{
            padding: 5px;
            margin: 5px;
        }
        #event_explain{
            width: 80%;
            height: 200px;
        }
        .sel{
            margin: 5px;
        }
        label{
        margin: 10px;
       
        }
</style>
</head>
<body>
<div class="my-div">
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
	    <button type="button" onclick="location.href='register.jsp'" style="width:100px;">회원가입</button>
	  <%
    	}
	  %>
	    
	</div>
	<!-- 메뉴바 -->

    
    <!-- 가운데 가장 큰 박스 -->
	<div id="background">
	
	<h3 style="text-align:center;">행사글 작성</h3>
	<div id="whitebox">
	<div> 
<form action="EventServlet" method="post" enctype="multipart/form-data">
	<div class="event_form">
	<label for="event_name">행사 제목 : </label>
	<input type="text" placeholder="제목을 입력해주세요." id="event_name" name="event_name" required style="text-align:center;"><br>
	
    <label for="event_location">행사 장소 : </label>
	<input type="text" id="event_location" name="event_location" required> <br>
		
	<label for="event_day"> 행사 기간 : </label>
	<input type="text" id="event_day" name="event_day" required>
	
	<label for="event_time">행사 시간 : </label>
	<input type="text" id="event_time" name="event_time" required> <br>
	

	
	<label for="event_price">가 격 : </label>
	<input type="number" id="event_price" name="event_price" required>
	
	<label for="event_age">연 령 : </label>
	<input type="number" id="event_age" name="event_age">
    <label for="event_img">사진 : </label>
	<input type="file" name="event_img" id="event_img" required><br>
	
	<label for="event_explain">내용 : </label> <br>
	<textarea type="text"  id="event_explain" name="event_explain" required style="text-align:center;"> </textarea><br>
	
	
	
	<input type="submit" value="게시글 작성" class="sel">
	</div>
	</div>
</form>
<button onclick="location.href='Event_List.jsp'">돌아가기</button>

	</div>
	</div>
</div>

</body>
</html>