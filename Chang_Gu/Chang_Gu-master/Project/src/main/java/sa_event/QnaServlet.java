package sa_event;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import park.event.EventPosting;
import park.event.EventPostingDAO;

/**
 * Servlet implementation class QnaServlet
 */
@WebServlet("/QnaServlet")
public class QnaServlet extends HttpServlet {
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Delete = request.getParameter("Delete");
		String UpdateQna = request.getParameter("UpdateQna");
		String Insert = request.getParameter("Insert");
		String NormalInsertQna = request.getParameter("NormalInsertQna");
		String QnaInsertComment = request.getParameter("QnaInsertComment");
		String DeleteComment = request.getParameter("DeleteComment");
		String UpdateInfoQna = request.getParameter("UpdateInfoQna");
		String insertLike = request.getParameter("insertLike");
		
		
		if(Delete != null && Delete.equals("삭제하기")) {
			DeleteQna(request, response);		
		} else if (UpdateQna != null && UpdateQna.equals("문의 수정하기")) {
			UpdateQna(request, response);
		} else if (Insert != null && Insert.equals("작성완료")){
			InsertQna(request, response);
		} else if (NormalInsertQna != null && NormalInsertQna.equals("문의하기")){
			NormalInsertQna(request, response);			
		} else if (QnaInsertComment != null && QnaInsertComment.equals("댓글작성")) {
			QnaInsertComment(request, response);
		} else if (DeleteComment != null && DeleteComment.equals("댓글삭제")){
			DeleteComment(request, response);
		} else if(UpdateInfoQna != null && UpdateInfoQna.equals("수정하기")) {
			UpdateInfoQna(request, response);
		} else if(insertLike != null && insertLike.equals("👍🏻도움돼요")) {
			insertLike(request, response);
		} else if(insertLike != null && insertLike.equals("👎🏻도움안돼요")) {
			insertLike(request, response);
		} else {
			return;
		}
	}
		//System.out.println(Insert);
		
		//UpdateQna(request, response);
		//DeleteQna(request, response);


	public void UpdateQna(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
		
		QnaDAO qnaDAO = new QnaDAO();
		int result = qnaDAO.updateQna(qna_title, qna_content, qna_number);
		
		HttpSession session = request.getSession();
		Qna qna = new Qna();
		
		session.setAttribute("qna_number", qna.getQna_number());
		session.setAttribute("qna_title", qna.getQna_title());
		session.setAttribute("qna_content", qna.getQna_content());

		if (result == -1) { // 글삭제 실패시
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('문의글 수정에 실패했습니다.')");
			// 이전 페이지로 사용자를 보냄
			script.println("</script>");
			response.sendRedirect("Qna_List.jsp");
		} else { // 글삭제 성공시
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('문의글이 수정되었습니다.')");
			// 문의게시판으로 이동
			script.println("</script>");
			response.sendRedirect("QnaUpdate_success.jsp");
		}
		
	}
	
	public void UpdateInfoQna(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
	
		QnaDAO qnaDAO = new QnaDAO();
	
		HttpSession session = request.getSession();
		Qna qna = qnaDAO.getQnaInfoALl(qna_number);
		
		session.setAttribute("qna_number", qna.getQna_number());
		session.setAttribute("event_number", qna.getEvent_number());
		session.setAttribute("qna_title", qna.getQna_title());
		session.setAttribute("qna_content", qna.getQna_content());
		session.setAttribute("qna_password", qna.getQna_password());
		session.setAttribute("qna_name", qna.getQna_name());
		session.setAttribute("qna_date", qna.getQna_date());
		session.setAttribute("qna_email", qna.getQna_email());

		response.sendRedirect("Qna_Update.jsp");
	
	}
	
	
	public void DeleteQna(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		
		QnaDAO qnaDAO = new QnaDAO();
		int result = qnaDAO.DeleteQna(qna_number);
		
		if(result == -1) {
			response.sendRedirect("Qna_List.jsp");
		} else {
			response.sendRedirect("QnaDelete_success.jsp");
		}
		
	}
	
	public void InsertQna(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int event_number = Integer.parseInt(request.getParameter("event_number"));
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
		int qna_password = Integer.parseInt(request.getParameter("qna_password"));
		String qna_name = request.getParameter("qna_name");
		String qna_email = request.getParameter("qna_email");
		
		QnaDAO qnaDAO = new QnaDAO();
		int result = qnaDAO.InsertQna(event_number, qna_title, qna_content, qna_password, qna_name, qna_email);
		
		
		if(result == -1) {
			response.sendRedirect("Qna_List.jsp");
		} else {
			response.sendRedirect("QnaWrite_success.jsp");
		}
	}
	
	public void NormalInsertQna(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
		int qna_password = Integer.parseInt(request.getParameter("qna_password"));
		String qna_name = request.getParameter("qna_name");
		String qna_email = request.getParameter("qna_email");
		
		QnaDAO qnaDAO = new QnaDAO();
		int result = qnaDAO.NormalInsertQna(qna_title, qna_content, qna_password, qna_name, qna_email);
		
		
		if(result == -1) {
			response.sendRedirect("Qna_List.jsp");
		} else {
			response.sendRedirect("QnaWrite_success.jsp");
		}

	}
	public void QnaInsertComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		String qna_comment_content = request.getParameter("qna_comment_content");
		int qna_comment_password = Integer.parseInt(request.getParameter("qna_comment_password"));
		
		QnaCommentDAO qnaCommentDAO = new QnaCommentDAO();
		int result = qnaCommentDAO.QnaInsertComment(qna_number, qna_comment_content, qna_comment_password);
		
		if(result == -1) {
			response.sendRedirect("Qna_List.jsp");
		} else {
			response.sendRedirect("Qna_Detail.jsp?qna_number=" + qna_number);
		}
		
	}
	public void DeleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qna_comment_password = Integer.parseInt(request.getParameter("qna_comment_password"));
		int qna_comment_number = Integer.parseInt(request.getParameter("qna_comment_number"));
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		
		QnaCommentDAO qnaCommentDAO = new QnaCommentDAO();
		int result = qnaCommentDAO.DeleteComment(qna_comment_password, qna_comment_number);
		
		if(result == -1) {
			response.sendRedirect("Qna_List.jsp");
		} else {
			
			response.sendRedirect("Qna_Detail.jsp?qna_number=" + qna_number);
		}
		
	}
	public void insertLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		String likename = request.getParameter("likename");
		String unlikename = request.getParameter("unlikename");
		
		QnaDAO qnaDAO = new QnaDAO();
		int result = qnaDAO.insertLike(qna_number, likename, unlikename);
		
		if(result == -1) {
			response.sendRedirect("Qna_List.jsp");
		} else {
			response.sendRedirect("Qna_Detail.jsp?qna_number=" + qna_number);
		}
		
	}
	
}
