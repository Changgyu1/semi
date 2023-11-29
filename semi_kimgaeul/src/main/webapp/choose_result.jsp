<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="gaeul.review.festivalDTO" %>
     <%@ page import="gaeul.review.Review_selectDAO" %>
     <%@ page import="java.util.*" %>
     <%@ page import="gaeul.review.commentDTO" %>
      <%@ page import="gaeul.review.reviewlikeDTO" %>
      <%@ page import="gaeul.review.Review_deleteDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>GO, festival 후기 상세보기</title>
<link rel="stylesheet" href="review.css">
</head>
<body>
		<!-- 로고 이미지 -->
	<img src="로고1.png" id="logo">


	 <div style="text-align: right; width: 1215px;">
    <%

        if(session.getAttribute("email")!=null){
    %>
	 	<button type="button" onclick="location.href='logout.jsp'" style="background:none;border:none;width:75px;">로그아웃</button>
	 	 <h>|</h>
	     <button type="button" onclick="location.href='mypageServlet?email=<%=session.getAttribute("email")%>'" style="background:none;border:none;width:100px;">마이페이지</button>
	 <%

	 	 }else{
	 %>
		 <button type="button" onclick="location.href='login.jsp'" style="width:60px;background:none;border:none;">로그인</button>
		 <h>|</h>
	    <button type="button" onclick="location.href='join.jsp'" style="width:100px;background:none;border:none;">회원가입</button>
	    
	  <%

	    	  	  }
	    	  %>
	    
	</div>

	<div id="buttons">
	<!--로그인 버튼-->
   <ul style="list-style:none;">
			<li style="float:left;margin-left:170px;" onclick="location.href='Event_List.jsp'">행사정보</li>
			<li style="float:left;margin-left:170px;" onclick="location.href='reservation_search.jsp'">예약조회</li>
			<li style="float:left;margin-left:170px;" onclick="location.href='Qna_List.jsp'">QNA</li>
			<li style="float:left;margin-left:170px;" onclick="location.href='review_list.jsp'">후기</li>
		</ul>
	</div>
 	 	<%
 	 	 	 	 	Review_selectDAO dao = new Review_selectDAO();
 	 	 			festivalDTO friview = new festivalDTO();
 	 	 			int review_number = Integer.parseInt(request.getParameter("review_number"));
 	 	 			ArrayList<festivalDTO> reviewlist=dao.listreview(review_number);

 	 	 			ArrayList<reviewlikeDTO> likenumber=dao.likenumber(review_number);
 	 	 			reviewlikeDTO rdto = new reviewlikeDTO();

 	 	 			int heart =dao.likecount(review_number);
 	 	 			
 	 	 			
 	 	    		//검색창에서 리뷰 검색결과
 	 	    		int rnum=0;
 	 	            for(int i=0; i<reviewlist.size(); i++){
 	 	            	festivalDTO riviewarray= (festivalDTO) reviewlist.get(i);
 	 	            	rnum = riviewarray.getReviewnumber();
 	 	            	String content=riviewarray.getReview();
 	 	            	String title=riviewarray.getReview_title();
 	 	            	Date date=riviewarray.getReviewdate();
 	 	            	String eventname = riviewarray.getEvent_name();
 	 	%>
		        
		        
		        <!-- 가운데 가장 큰 박스 -->
					<div id="background" style="height:550px;">
					<h3 style="text-align:center">[<%=eventname %>]</h3>
					
					<!-- 댓글 박스 -->
			 	 	<div id="reviewbox">
		         	
			        <div style=" font-size:14px;box-shadow:0px 1px 3px gray; width:900px; margin-left:auto; margin-right:auto; height:160px;">
			        <%
					if(session.getAttribute("email")!=null){
				    %>
				    <!-- 게시물 좋아요 부분 -->
				        <form action="Review_Servlet" method="post">
				        <input type="hidden" id="review_number" name="review_number" value="<%=rnum%>">
				        <input type="hidden" id="review_number" name="review_number" value="<%=rnum%>">
				       <p title="좋아요는 작성자에게 큰 힘이 됩니다."  style="float:right;margin-right:20px;"><input type=submit id="commentlike" name="reviewlike" value="♥" style="font-weight:900;font-size:14px;color:red;"><%=heart%></p>
				        </form>
				        
	    
					<%
				    }else{
				    	%>
				    	<!-- 비로그인 시 게시물 좋아요 부분(좋아요 누르기 안됨 개수 조회만 가능)-->
				    	<p title="좋아요는 작성자에게 큰 힘이 됩니다." style="float:right;margin-right:20px;"><input type=submit id="commentlike"  name="reviewlike" value="♥ <%=heart%>" style="font-weight:900;font-size:14px;color:red;"></p>
				   <% 
				    }
					%>
					
			        <p style="float:left;font-size:17px;font-weight:600;margin-left:20px;"> <%= title %></p>
			        <p style="float:right;margin-right:20px; margin-top:17px;">작성일 : <%=date%></p>
			        <p style="float:left; background:rgb(247, 247, 249);width:850px;height:80px;margin-left:24px;padding-top:15px;margin-top:1px; "><%=content %></p>
			       
			        <br>
					<%
					}
            
	 				for(int i=0; i<likenumber.size(); i++){
					reviewlikeDTO lknumber = (reviewlikeDTO) likenumber.get(i);
					int reviewnum=lknumber.getReview_number();
					String name=lknumber.getName();
					int number = lknumber.getReview_like();
	    			}
				   %>
			    
			    <%
			    if(session.getAttribute("email")!=null){
			    %>
				
				<input style="margin-top:5px; width:85px;float:right;" type="submit" value="리뷰 삭제" onclick="location.href='review_delete.jsp?rnum=<%=rnum%>'">		
				<input style="margin-top:5px; width:85px; float:right; margin-right:10px;" type="submit" value="리뷰 수정" onclick="location.href='review_update.jsp?rnum=<%=rnum%>'">
			
			
				<div style="height:100px;">	
				<!-- 댓글서블렛으로 데이터 전송 -->
				<form action="Review_Servlet" method="post">
				<!-- 댓글입력창 -->
		 	 	<input id="comment_content" type="text" name="comment_content" style="text-align:center;float:left;border:1px solid gray;border-radius:5px;height:70px;margin-top: 10px;margin-bottom: 10px; width:700px" required placeholder="댓글을 입력해주세요.">
		 	 	<br>
		 	 	<br>
		 	 	<!-- 비밀번호 입력창 -->
		 	 	<input type="password" name="comment_password" style="text-align:center; float:right;border:1px solid gray;border-radius:5px;height:20px;width:175px; margin-top:5px;" required placeholder= "비밀번호를 입력해주세요.">
		 	 	<input type="hidden" name="review_number" value="<%=rnum%>">
		 	 	<!-- 댓글등록 버튼 -->
		 	 	<input type="submit" name="commentinsert" value="댓글등록하기" style="float:right;border-radius:5px;margin-top:4px;height:26px;width:180px;">
		 	 	</form>
		 	 	</div>			
				<%
			    }else{
				%>
				<br>
				
				<!-- 댓글서블렛으로 데이터 전송 -->
				<form action="Review_Servlet" method="post">
				<!-- 댓글입력창 -->
		 	 	<input id="comment_content" type="text" name="comment_content" style="float:left;border:1px solid gray;border-radius:5px;height:70px;margin-top: 10px;margin-bottom: 10px; width:700px" required placeholder="댓글을 입력해주세요.">
		 	 	<br>
		 	 	<br>
		 	 	<!-- 비밀번호 입력창 -->
		 	 	<input type="password" id="comment_password" name="comment_password" required placeholder= "비밀번호">
		 	 	<input type="hidden" name="review_number" value="<%=rnum%>">
		 	 	<!-- 댓글등록 버튼 -->
		 	 	<input type="submit" name="commentinsert" value="댓글등록하기" style="float:right;border-radius:5px;margin-top:4px;height:26px;width:180px;">
		 	 	</form>

				<%
			    }
				%>
				
				<div class="commentbox" >
				<!-- 댓글이 담길 곳 -->
				<table style="text-align:left;padding-left:15px;">
 				<%
				
				ArrayList<commentDTO> listcomment = dao.commentlist(rnum);
				
				//댓글 검색결과
				int conum=0;
				for(int i=0; i<listcomment.size(); i++){
					commentDTO riviewarray= (commentDTO) listcomment.get(i);
					conum=riviewarray.getComment_number();
					String content=riviewarray.getComment_content();
					Date codate =riviewarray.getComment_date();
				%>
				<%
				int commentlike=dao.commentlikecount(conum);
					if(session.getAttribute("email")!=null){
				    %>
				    <tr style="padding:10px;">
						<td style="width:720px;border:none;background-color:rgb(243, 241, 241);font-size:13px;"><%=content %></td>
						<td style="width:80px;border:none;background-color:rgb(243, 241, 241);font-size:12px;"><%=codate %></td>
						
						<!-- 로그인 시 댓글 좋아요 부분 -->
						<form action="Review_Servlet" method="post">
						<input type="hidden" id="review_number" name="review_number" value="<%=rnum%>">
				        <input type="hidden" id="comment_number" name="comment_number" value="<%=conum%>">
				        <input type="hidden" id="name" name="name" value="<%=session.getAttribute("name")%>">
				       <td style="height:14px;"><input type=submit id="commentlike" name="commentlike" value="♥" style="font-weight:400;font-size:14px;"><%=commentlike%></td>
				        </form>
						<td style="border:none;background-color:rgb(243, 241, 241);"><input type="submit" name="commentdelete" value="삭제하기"  onclick="location.href='comment_delete.jsp?conum=<%=conum%>'"></td>	
					</tr>
				        
					<%
				    }else{
				    	%>
				    	<!-- 비로그인 시 댓글 좋아요 부분 -->
				    	<tr style="padding:10px;">
						<td style="width:720px;border:none;background-color:rgb(243, 241, 241);font-size:13px;"><%=content %></td>
						<td style="width:80px;border:none;background-color:rgb(243, 241, 241);font-size:12px;"><%=codate %></td>
						<td style="height:14px;"><input type=submit id="commentlike" name="commentlike" value="♥<%=commentlike %>" style="font-weight:400;font-size:14px;color:red"></td>
						<td style="border:none;background-color:rgb(243, 241, 241);"><div></div><input type="submit" name="commentdelete" value="삭제하기"  onclick="location.href='comment_delete.jsp?conum=<%=conum%>'"></td>	
					</tr>
				   <% 
				    }
					%>
				<%
				}
				%>
				</table>
  			  </div>
  			  
  			  <br>

 	 	</div>
	</div>
	
	</div>
    
</body>

</html>