package gaeul.review;

public class reviewlikeDTO {

	private int review_number;
	private String name;
	private int review_like;
	private int likecount;
	private int comment_number;
	
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getReview_number() {
		return review_number;
	}
	public int getComment_number() {
		return comment_number;
	}
	public void setComment_number(int comment_number) {
		this.comment_number = comment_number;
	}
	public void setReview_number(int review_number) {
		this.review_number = review_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getReview_like() {
		return review_like;
	}
	public void setReview_like(int review_like) {
		this.review_like = review_like;
	}
}
