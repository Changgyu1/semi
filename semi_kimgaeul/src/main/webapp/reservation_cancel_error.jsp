<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>����</title>
    <link rel="stylesheet" href="./hjCss/main.css">
	<link rel="stylesheet" href="./hjCss/reservationCss.css">
</head>
<body>
<div id="wrapper">
    <!--�ΰ��̹���-->
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
	
		<h2 style="padding-top:15px">����</h2>
		<!-- �ȿ� �۳ִ� �Ͼ� �ڽ�-->
		<div id="reviewbox">
            <h1>���� ��� ����</h1>
		<p>���� ��Ұ� �ȵǴ� ���� �� �Դϴ�.</p>

        </div>
	 </div>
</div>	

</body>
</html>