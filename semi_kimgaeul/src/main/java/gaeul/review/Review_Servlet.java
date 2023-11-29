package gaeul.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Review_Servlet
 */
@WebServlet("/Review_Servlet")
public class Review_Servlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//리뷰 작성 버튼 name값 받아오기
		String reviewinsert=request.getParameter("reviewinsert");
		//리뷰 수정 버튼 name값 받아오기
		String reviewupdate=request.getParameter("reviewupdate");
		//리뷰 삭제 버튼 name값 받아오기
		String reviewdelete=request.getParameter("reviewdelete");
		//댓글 작성 버튼 name값 받아오기
		String commentinsert=request.getParameter("commentinsert");
		//댓글 삭제 버튼 name값 받아오기
		String commentdelete = request.getParameter("commentdelete");
		//리뷰 좋아요 누르기 버튼 name값 받아오기
		String reviewlike = request.getParameter("reviewlike");
		//댓글 좋아요 누르기 버튼 name값 받아오기
		String commentlike = request.getParameter("commentlike");
		
		//리뷰 작성
		if(reviewinsert!=null && reviewinsert.equals("리뷰등록하기")) {
			ReviewInsert(request,response);
			
		//리뷰 수정	
		}else if(reviewupdate!=null && reviewupdate.equals("리뷰수정하기")) {
			ReviewUpdate(request,response);
			
		//리뷰 삭제	
		}else if(reviewdelete!=null && reviewdelete.equals("리뷰삭제하기")) {
			ReviewDelete(request,response);
			
		//댓글 작성	
		}else if(commentinsert!=null && commentinsert.equals("댓글등록하기")) {
			CommentInsert(request,response);
			
		//댓글 삭제	
		}else if(commentdelete!=null && commentdelete.equals("댓글삭제하기")) {
			CommentDelete(request,response);
			
		//리뷰 좋아요 누르기	
		}else if(reviewlike!=null && reviewlike.equals("♥")){
			ReviewLike(request,response);
			
		//댓글 좋아요 누르기	
		}else if(commentlike!=null && commentlike.equals("♥")){
			CommentLike(request,response);
			
		}else {
			return;
		}
	}

	//리뷰 추가 DAO에 파라미터 값넣기
	public void ReviewInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String review =request.getParameter("review");
		String review_title =request.getParameter("review_title");
		int event_number =Integer.parseInt(request.getParameter("event_number"));
		
		Review_InsertDAO dao = new Review_InsertDAO();
		int result = dao.reviewInsert(review, review_title, event_number);
		
		if(result==-1) {
			response.sendRedirect("review_error.jsp");
		}else{
			response.sendRedirect("review_succes.jsp");
		}
	}
	
	//리뷰 수정 DAO에 파라미터 값넣기
	public void ReviewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String review =request.getParameter("review");
		String review_title =request.getParameter("review_title");
		int review_number =Integer.parseInt(request.getParameter("review_number"));
		
		Review_updateDAO dao = new Review_updateDAO();
		int result = dao.reviewUpdate(review_title, review, review_number);
		
		if(result==-1) {
			response.sendRedirect("review_error.jsp");
		}else{
			response.sendRedirect("review_succes.jsp");
		}
	}

	//리뷰 삭제 DAO에 파라미터 값넣기
	public void ReviewDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int review_number =Integer.parseInt(request.getParameter("review_number"));
		
		Review_deleteDAO dao = new Review_deleteDAO();
		int result = dao.reviewDelete(review_number);
		
		if(result==-1) {
			response.sendRedirect("review_error.jsp");
		}else{
			response.sendRedirect("review_succes.jsp");
		}
	}
	
	//댓글 추가 DAO에 파라미터 값넣기
	public void CommentInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comment_content =request.getParameter("comment_content");
		int review_number =Integer.parseInt(request.getParameter("review_number"));
		int comment_password =Integer.parseInt(request.getParameter("comment_password"));
		
		Review_InsertDAO dao = new Review_InsertDAO();
		int result = dao.commentInsert(comment_content, review_number, comment_password);
		
		if(result==-1) {
			response.sendRedirect("review_error.jsp");
		}else {
			response.sendRedirect("choose_result.jsp?review_number="+review_number);
		}
	}

	//댓글 삭제 DAO에 파라미터 값넣기
	public void CommentDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int comment_password =Integer.parseInt(request.getParameter("comment_password"));
		int comment_number =Integer.parseInt(request.getParameter("comment_number"));
		
		Review_deleteDAO dao = new Review_deleteDAO();
		int result = dao.commentDelete(comment_password, comment_number);
		
		if(result==-1) {
			response.sendRedirect("review_error.jsp");
		}else{
			response.sendRedirect("review_succes.jsp");
		}
	}
	
	//리뷰 좋아요 누르기 DAO에 파라미터 값넣기
	public void ReviewLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name=request.getParameter("name");
		int review_number = Integer.parseInt(request.getParameter("review_number"));
		
		Review_InsertDAO dao = new Review_InsertDAO();
		int result = dao.reviewLike(review_number, name);
		
		if(result==-1) {
			response.sendRedirect("review_error.jsp");
		}else{
			response.sendRedirect("choose_result.jsp?review_number="+review_number);
		}
	}
	
	//댓글 좋아요 누르기 DAO에 파라미터 값넣기
		public void CommentLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			int comment_number = Integer.parseInt(request.getParameter("comment_number"));
			int review_number = Integer.parseInt(request.getParameter("review_number"));
			String name = request.getParameter("name");
			
			Review_InsertDAO dao = new Review_InsertDAO();
			int result = dao.commentLike(comment_number,name);
			
			if(result==-1) {
				response.sendRedirect("review_error.jsp");
			}else{
				response.sendRedirect("choose_result.jsp?review_number="+review_number);
			}
		}
}
