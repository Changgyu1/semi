<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>GO, festival ��� ����</title>
<link rel="stylesheet" href="review.css">
</head>
<body>

	<!-- �ΰ� �̹��� -->
	<img src="�ΰ�1.png" id="logo">


	 <div style="text-align: right; width: 1215px;">
    <%
    if(session.getAttribute("email")!=null){
    %>
	 	<button type="button" onclick="location.href='logout.jsp'" style="background:none;border:none;width:75px;">�α׾ƿ�</button>
	 	 <h>|</h>
	    <button type="button" onclick="location.href='mypageServlet?email=<%=session.getAttribute("email")%>'" style="background:none;border:none;width:100px;">����������</button>
	 <%
	 }else{
	 %>
		 <button type="button" onclick="location.href='login.jsp'" style="background:none;border:none;width:60px;">�α���</button>
		 <h>|</h>
	    <button type="button" onclick="location.href='join.jsp'" style="background:none;border:none;width:100px;">ȸ������</button>
	  <%
	  }
	  %>
	    
	</div>

	<div id="buttons">
	<!--�α��� ��ư-->
   <ul style="list-style:none;">
			<li style="float:left;margin-left:170px;" onclick="location.href='Event_List.jsp'">�������</li>
		   <li style="float:left;margin-left:170px;" onclick="location.href='reservation_search.jsp'">������ȸ</li>
		   <li style="float:left;margin-left:170px;" onclick="location.href='Qna_List.jsp'">QNA</li>
			<li style="float:left;margin-left:170px;" onclick="location.href='review_list.jsp'">�ı�</li>
		</ul>
	</div>
	
	<!-- ��� ���� ū �ڽ� -->
	<div id="background" style="height:520px;">
		<div id="reviewbox" >
		
		
		<br>
		<br>
		<br>
		<br>
		<br>
		
		<!--  ��� ���� ��ư�� ��й�ȣ �Է�ĭ -->
			<div style="border:1px solid gray; border-radius:10px;box-shadow: 0px 1px 5px gray; height:350px;width:600px;margin-left:auto;margin-right:auto;margin-top:-60px;">
			<!-- �Ķ� â -->
			<div style="background-color:blue;height:30px;width:600px;border-radius:10px 10px 0px 0px;box-shadow:0px 2px 0px black;"> 
			<button style="height:20px;font-weight:900;color:white;background-color:red;border-radius:3px;float:right;margin-right:6px;margin-top:5px;box-shadow:1px 2px 0px black;"
			onclick="location.href='review_list.jsp'">X</button>
			</div>
			<div >
			<br>
				<h2>����� �����Ͻðڽ��ϱ�?</h2>
				<h5>������ ���Ͻø� ��� �ۼ��� �����ߴ� ��й�ȣ�� �Է����ּ���. </h5>
				<h5 style="color:red;">* ���� �� �ǵ��� �� �����ϴ�.</h5>
				<h6>����ϱ⸦ ���� �� �ı� �Խ��� �������� �̵��մϴ�.</h6> 
				
				<!-- ���� ��й�ȣ ���� �� -->
					<form action="Review_Servlet" method="post">
						<input type="password" name="comment_password" style="border:1px solid gray; border-radius:2px;width:80px;height:25px;text-align:center;" placeholder="��й�ȣ">
						<br>
						<br>
						<input type="hidden" name=comment_number value="<%=request.getParameter("conum")%>">
						<input type="submit" name="commentdelete" value="��ۻ����ϱ�" style="font-size:14px;border:1px solid gray; width:70px;border-radius:3px;height:25px;" >
					</form>
				<br>
				<button type="button" style="border:1px solid gray; width:70px;border-radius:3px;height:25px;" onclick="location.href='review_list.jsp'">����ϱ�</button>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>