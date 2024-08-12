package myblog;

import java.util.List;

public class Post {
	private String title;
	private String content;
	private User author;
	private List<Comment> comments;
	private String comment;
	
	public Post(String title, String content, User author) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.comments = null;
	}
	
	public Post(String comment, User author) {
		this.comment = comment;
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public String getComment() {
		return comment;
	}

}
