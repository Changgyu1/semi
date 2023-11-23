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



public class selectDAO  {

	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String user="kiga";
	private String pw="kiga1234";
	private Connection con;
	private PreparedStatement ps;
	
	public selectDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//리뷰 제목으로 검색
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

	

	
	public int likecount(int review_number) {
		 int likecount = 0 ;
		try {
			System.out.println("보자보자 ~~"+review_number);
			con=DriverManager.getConnection(url, user, pw);
			String sql="select count(review_number) as likecount from kiga_like where review_number=? ";
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
}


