<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sa_event.Qna"%>
<%@ page import="sa_event.QnaDAO"%>
<%@ page import="sa_event.QnaLike"%>
<%@ page import="java.util.*"%>
<%@ page import="sa_event.QnaComment"%>
<%@ page import="sa_event.QnaCommentDAO"%>

<%
QnaCommentDAO qnacommentDAO = new QnaCommentDAO();
int qna_number = Integer.parseInt((request.getParameter("qna_number")));
ArrayList<QnaComment> commentList = qnacommentDAO.getQnaCommentByqna_number(qna_number);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 상세 페이지</title>
<link rel="stylesheet" href="./saCss/saCss.css">
<style>
        .test77{
            list-style-type: none;
    float: left;
    width: 1000px;
        }
       .test7{
       list-style-type: none;
       float: left;
       }
</style>
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
    <div id="buttons">
        <ul>
            <li onclick="location.href='Event_List.jsp'">행사정보</li>
            <li onclick="location.href='reservation_search.jsp'">예약조회</li>
            <li onclick="location.href='Qna_List.jsp'">QNA</li>
            <li onclick="location.href='review_list.jsp'">후기</li>
        </ul>
    </div>

    <h3 style="text-align:center;">문의 상세 정보</h3>

        <div id="background">
            <div id="list">
                <table>
                    <tr>
                        <th>문의번호</th>

                        <th>제목</th>
                        <th>내용</th>
                        <th>이름</th>
                        <th>작성일</th>

                        <th>이메일</th>
                    </tr>

                    <tr style="height:30px;">
                        <td style="background-color:white;"> <%=session.getAttribute("qna_number") %> </td>
                        <td style="background-color:white;"> <%=session.getAttribute("qna_title") %> </td>
                        <td style="background-color:white;"> <%=session.getAttribute("qna_content") %> </td>
                        <td style="background-color:white;"> <%=session.getAttribute("qna_name") %> </td>
                        <td style="background-color:white;"> <%=session.getAttribute("qna_date") %> </td>
                        <td style="background-color:white;"> <%=session.getAttribute("qna_email") %> </td>
                    </tr>
                </table>
                <br><br>

                <div class="test">
                    			
					<form action="QnaServlet" method="post">
						<input type="hidden" name="qna_number" value="<%= session.getAttribute("qna_number")%>">
							
	                    <input type="submit" name="Delete" value="삭제하기" style="width: 85px; height: 34px; border-radius: 5px;">
						
	                    <input type="submit" name="UpdateInfoQna" value="수정하기" style="width: 85px; height: 34px; border-radius: 5px;"> 
                    </form>
                    	<input type="submit" value="돌아가기" onclick="location.href='Qna_List.jsp'" style="width: 85px; height: 34px; border-radius: 5px;">
                </div>
                
                
                <%
                
                QnaDAO qnaDAO = new QnaDAO();
                
             
				int qnalike = qnaDAO.selectLike(qna_number);     
				int unlike= qnaDAO.selectUnlike(qna_number);

                %>

		
		        
                <% if(session.getAttribute("email")!=null){ %>
                    <div class="test77">
 
                        <form action="QnaServlet" method="post" class="test7">
                            <input type="hidden" name="qna_number" value="<%=session.getAttribute("qna_number") %>">
                            <input type="hidden" name="likename" value="<%=session.getAttribute("name") %>">
                            <input type="submit" name="insertLike" value="👍🏻도움돼요"><%=qnalike%>
                		</form>
                		
                		<form action="QnaServlet" method="post" class="test7">
                            <input type="hidden" name="qna_number" value="<%=session.getAttribute("qna_number") %>">
                            <input type="hidden" name="unlikename" value="<%=session.getAttribute("name") %>">
                            <input type="submit" name="insertLike" value="👎🏻도움안돼요"><%=unlike%>
                		</form>
                	 
                    </div>
                <% } %>
                    <br><br><br>


                    <br><br><br><br>
                    <!--  댓글 목록 표시 -->
                    <div class="commentBox">
                        <div class="commentScrollBox" style="overflow-y:scroll;">
                            <table id="commnetlist" style="overflow-y:scroll;">
                           
                                <% for(QnaComment qc : commentList) { %>
                                <tr>
                                    <td style="width:500px; text-align: left;">
                                        <%= qc.getQna_comment_content() %>
                                    </td>
                                    <td style="text-align: center;">
                                        <%= qc.getQna_comment_date() %>
                                            <form  action="QnaComment_Delete.jsp?qna_comment_number=<%=qc.getQna_comment_number() %>" method="post">
                                            
                                                <input type="hidden" name="qna_number" value="<%=session.getAttribute("qna_number") %>">
                                                
                                                <input type="submit" id="commentdeleteButton" name="DeleteComment" value="댓글삭제" 
                                                style="width: 85px; height: 34px; border-radius: 5px;">
                                            </form>
                                    </td>

                                <tr>
                                    <% } %>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <br><br><br>
                    <div id="commentFormbox">
						<form action="QnaServlet" method="post" style="text-align: -webkit-auto;">
                        	<input type=hidden name="qna_number" value="<%=session.getAttribute("qna_number") %>">

                            <label for="qna_comment_content"> 댓글 내용 : </label>
                            <input style="width: 600px; height: 40px; border-radius: 5px;" type="text" id="qna_commemt_content" name="qna_comment_content" required>
                            <br>
                            <br>
                            <label for="qna_comment_password">비밀번호 : </label>
                            <input style=" width: 200px; height: 30px; border-radius: 5px;" type="password" id="qna_comment_password" name="qna_comment_password" required>

                            <input type="submit" name="QnaInsertComment" value="댓글작성" style="width: 85px; height: 34px; border-radius: 5px;">
                       </form>
                    </div>
            </div>
            <script>
            function click(){
            	
            	
            	
            }
            </script>
</body>
</html>
