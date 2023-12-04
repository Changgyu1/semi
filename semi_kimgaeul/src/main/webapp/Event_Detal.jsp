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
		<!-- �ΰ� �̹��� -->
		<img src="./image/�ΰ�1.png" id="logo">

 <!--�α��� ��ư-->
    <div style="text-align: right; width: 1215px;">
    <%
    	if(session.getAttribute("email")!=null){
    %>
	 	<button type="button" onclick="location.href='logout.jsp'" style="width:75px;">�α׾ƿ�</button>
	 	 <h>|</h>
	    <button type="button" onclick="location.href='mypageServlet?email=<%=session.getAttribute("email")%>'" style="width:100px;">����������</button>
	 <%
    	}else{
	 %>
		 <button type="button" onclick="location.href='login.jsp'" style="width:60px;">�α���</button>
		 <h>|</h>
	    <button type="button" onclick="location.href='join.jsp'" style="width:100px;">ȸ������</button>
	  <%
    	}
	  %>
	    
	</div>

	<!-- �޴��� -->
	<div id="buttons">
		<ul>
			<li onclick="location.href='review_list.jsp'">�ı�</li>
			<li onclick="location.href='Qna_List.jsp'">QNA</li>
			<li onclick="location.href='reservation_search.jsp'">������ȸ</li>
			<li onclick="location.href='Event_List.jsp'">�������</li>
		</ul>
	</div>


		<!-- ��� ���� ū �ڽ� -->
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
								<th class="jb-th-1"> <img src="./image/����������.png" style="width: 20px; height: 20px;"> �� �� :</th>
								<th><%=eventPosting.getEvent_location() %></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td> <img src="./image/����������.png" style="width: 20px; height: 20px;" > ��� �Ⱓ :</td>
								<td><%=eventPosting.getEvent_day() %></td>
							  </tr>
							  <tr>
								<td> <img src="./image/����������.png" style="width: 20px; height: 20px;" > ���� �ð� :</td>
								<td><%=eventPosting.getEvent_time() %></td>
							  </tr>
							  <tr>
								<td>�� �� :</td>
								<td><%=eventPosting.getEvent_age() %></td>
							  </tr>
							  <tr>
								<td>�� �� :</td>
								<td><%=eventPosting.getEvent_price() %></td>
							  </tr>
							  <tr>
								<td>��� ���� :</td>
								<td> <%=eventPosting.getEvent_explain() %></td>
							  </tr>
						</tbody>
					</table>
					<input type="button" value="�����ϱ�" onclick="location.href='reservation.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
				</div>
            </div>
            
            
           	  <div style="text-align: right; width: 1215px;">
           	  
				    <%//�α��ν� �ı� ���⸸ ����
				    if(session.getAttribute("email")!=null){
				    %>
				    <input type="submit" value="�ı⺸��" onclick="location.href='review_searchlist.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
				    <input type="submit" value="�ı� �ۼ��ϱ�" onclick="location.href='review_write.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
					 <%//��α��� �� �ı� �ۼ�,�ı� ���� ����
					 }else{
					 %>
				    <input type="submit" value="�ı⺸��" onclick="location.href='review_searchlist.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
					 
					  <%
					  }
					  %>
				</div>
            
            
           
				<input type="submit" value="��ϰ���" onclick="location.href='Event_List.jsp'">
				<input type="submit" value="�����ϱ�"
					onclick="location.href='Event_Update.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
				<input type="submit" value="�����ϱ�"
					onclick="location.href='Event_delete_success.jsp?event_number=<%=eventPosting.getEvent_number()%>'">
			</div>
          

		<!-- ������ �ѱ�� ��ư-->

	</div>
			<div id="Pagebutton">
			<button id="prevbutton">����</button>

			<button id="nextbutton">����</button>
		</div>

</body>
</html>