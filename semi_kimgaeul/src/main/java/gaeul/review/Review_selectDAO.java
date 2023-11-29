package gaeul.review;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class Review_selectDAO  {

	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String user="kiga";
	private String pw="kiga1234";
	private Connection con;
	private PreparedStatement ps;
	
	public Review_selectDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//리뷰번호로 검색 조회
	public ArrayList<festivalDTO> listreview(int review_number) {
	
		ArrayList<festivalDTO> reviewlist= new ArrayList<festivalDTO>();
		
		try {
			con=DriverManager.getConnection(url, user, pw);
			
			String sql="select e.event_name,r.review,r.review_title,r.review_date,r.review_number from review r join event e on r.event_number=e.event_number where r.review_number=?";
			
			ps=con.prepareStatement(sql);
			ps.setInt(1,review_number);
	
		
			System.out.println(review_number);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String event_name=rs.getString("event_name");
		    	String name=rs.getString("review");
		    	String title=rs.getString("review_title");
		    	Date date=rs.getDate("review_date");
		    	int rnum=rs.getInt("review_number");
		    	
		    	festivalDTO fr = new festivalDTO();
		    	fr.setReview(name);
		    	fr.setReview_title(title);
		    	fr.setReviewdate(date);
		    	fr.setEvent_name(event_name);
		    	fr.setReviewnumber(rnum);
		   
		    	
		    	reviewlist.add(fr);
		 
			}
			rs.close();
			ps.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return reviewlist;
	}

//이벤트 번호로 검색 조회
public ArrayList<festivalDTO> searchlist (int event_number) {
	
	ArrayList<festivalDTO> searchlist= new ArrayList<festivalDTO>();
	
	try {
		con=DriverManager.getConnection(url, user, pw);
		
		String sql="select e.event_name,r.review,r.review_title,r.review_date,r.review_number from review r join event e on r.event_number=e.event_number where e.event_number=?";
		
		ps=con.prepareStatement(sql);
		ps.setInt(1,event_number);

	
		System.out.println(event_number);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			String event_name=rs.getString("event_name");
	    	String name=rs.getString("review");
	    	String title=rs.getString("review_title");
	    	Date date=rs.getDate("review_date");
	    	int rnum=rs.getInt("review_number");
	    	
	    	festivalDTO fr = new festivalDTO();
	    	fr.setReview(name);
	    	fr.setReview_title(title);
	    	fr.setReviewdate(date);
	    	fr.setEvent_name(event_name);
	    	fr.setReviewnumber(rnum);
	   
	    	
	    	searchlist.add(fr);
	 
		}
		rs.close();
		ps.close();
		con.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return searchlist;
}

//리뷰 리스트 항상 조회
	public ArrayList<festivalDTO> List(){
		ArrayList<festivalDTO> list = new ArrayList<festivalDTO>();
		
		try {
			con=DriverManager.getConnection(url, user, pw);
			
			String sql="select * from review r join event e on r.event_number=e.event_number";
		
			
			ps=con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String event_name=rs.getString("event_name");
		    	String name=rs.getString("review");
		    	String title=rs.getString("review_title");
		    	Date date=rs.getDate("review_date");
		    	int rnum=rs.getInt("review_number");
		    	int eventnum = rs.getInt("event_number");
		    	
		    	festivalDTO fr = new festivalDTO();
		    	fr.setReview(name);
		    	fr.setReview_title(title);
		    	fr.setReviewdate(date);
		    	fr.setEvent_name(event_name);
		    	fr.setReviewnumber(rnum);
		    	fr.setEvent_number(eventnum);
		   
		    	list.add(fr);
		 
			}
			rs.close();
			ps.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	//리뷰 좋아요한 사람 조회
	public ArrayList<reviewlikeDTO> likenumber(int review_number) {
		 ArrayList<reviewlikeDTO> likenumber = new ArrayList<>();
		try {
			con=DriverManager.getConnection(url, user, pw);
			String sql="select * from kiga_like where review_number=? ";
			ps=con.prepareStatement(sql);
			ps.setInt(1, review_number);
			ResultSet rs = 	ps.executeQuery();
			
		 while(rs.next()) {
		 int rnum= rs.getInt("review_number");
		 String name=rs.getString("name");
		 int review_like=rs.getInt("review_like");
		 
		 reviewlikeDTO dto = new reviewlikeDTO();
		 dto.setName(name);
		 dto.setReview_like(review_like);
		 dto.setReview_number(rnum);
		 likenumber.add(dto);
		 System.out.println(name);
		 }
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return likenumber;
		
	}

	

	//좋아요 숫자 조회
	public int likecount(int review_number) {
		 int likecount = 0 ;
		try {
			System.out.println("보자보자 ~~"+review_number);
			con=DriverManager.getConnection(url, user, pw);
			String sql="select count(distinct name) as likecount from kiga_like where review_number=? ";
			ps=con.prepareStatement(sql);
			ps.setInt(1, review_number);
			ResultSet rs = 	ps.executeQuery();
			
			 if(rs.next()) {
			 int rnum= review_number;
			 likecount=rs.getInt("likecount");
			 
			 reviewlikeDTO dto = new reviewlikeDTO();
			 dto.setReview_like(likecount);
			 dto.setReview_number(rnum);
			 
			 }
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likecount;
	}
	
	
	//댓글 좋아요 수 조회
	public int commentlikecount(int comment_number) {
		 int likecount = 0 ;
		try {
			System.out.println("보자보자 ~~"+comment_number);
			con=DriverManager.getConnection(url, user, pw);
			String sql="select count(distinct name) as likecount from kiga_like where comment_number=? ";
			ps=con.prepareStatement(sql);
			ps.setInt(1, comment_number);
			ResultSet rs = 	ps.executeQuery();
			
			 if(rs.next()) {
			 int cnum= comment_number;
			 likecount=rs.getInt("likecount");
			 
			 reviewlikeDTO dto = new reviewlikeDTO();
			 dto.setReview_like(likecount);
			 dto.setReview_number(cnum);
			 
			 }
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likecount;
	}
	
	
	//댓글 리스트 항상 조회
	public ArrayList<commentDTO> commentlist(int rnum){
		ArrayList<commentDTO> listcomment = new ArrayList<commentDTO>();
	try {
		Class.forName("oracle.jdbc.OracleDriver");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		con=DriverManager.getConnection(url, user, pw);
		
		String sql="select * from review_comment c join review r on c.review_number= r.review_number where c.review_number=? order by comment_date";
	
		System.out.println("리뷰번호"+rnum);
		PreparedStatement ps = con.prepareStatement(sql);
	
		ps.setInt(1,rnum);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int cnum=rs.getInt("comment_number");
			Date date=rs.getDate("comment_date");
			int reviewnum=rs.getInt("review_number");
			String content=rs.getString("comment_content");
			int pw = rs.getInt("comment_password");
			
			commentDTO dto = new commentDTO();
			dto.setComment_content(content);
			dto.setComment_date(date);
			dto.setComment_number(cnum);
			dto.setReview_number(rnum);
			dto.setComment_password(pw);
			dto.setReview_number(reviewnum);
			
			listcomment.add(dto);
		}
		
		rs.close();
	
	} catch (SQLException e) {
		e.printStackTrace();
		}
	return listcomment;
	}
}


