<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="review.css">
</head>
<body>

	<!-- 로고 이미지 -->
	<img src="섬.png" id="logo">
	
	<!-- 로그인/회원가입 버튼 -->
	<div  style="margin-left:900px;">
		 <button type="button" onclick="location.href='login.jsp'" style="width:60px;">로그인</button>
		    <h>|</h>
		    <button type="button" onclick="location.href='logout.jsp'" style="width:100px;">회원가입</button>
	</div>
	
	<!-- 메뉴바 -->
  <div id="buttons">
        <button type="button" href="reviewmain">행사정보</button>
        <button type="button" href="링크 연결할 곳">예약조회</button>
        <button type="button" href="링크 연결할 곳">문의게시판</button>
        <button type="button" href="링크 연결할 곳">후기게시판</button>
    </div>

	<!-- 가운데 가장 큰 박스 -->
	<div id="background">
	
		<h2>리뷰 작성</h2>
		<!-- 안에 글넣는 하얀 박스-->
		<div id="reviewbox">
		<form  action="festival_review" method="post">
			
			<label for="review_title">제목</label><br>
			<input type="text" id="review_title" name="review_title" required placeholder="후기 제목을 입력해주세요." style="text-align:center;"><br>
			
			<label for="review" >내용</label><br>
			<input type="text" id="review" name="review" required placeholder="후기 내용을 입력해주세요." style="text-align:center;"><br>
			
			<input type="submit" value="리뷰 등록하기" >
		</form>
		</div>
	 </div>
	
	<!-- 페이지 넘기는 버튼-->
	<div id="Pagebutton">
        <button id="prevbutton">이전</button>
        
        <button id="nextbutton">다음</button>
    </div>
	

</body>
</html>