package myblog;

public class Comment {
	private String content;
	private User author;
	
	public Comment(String content, User author) {
		this.content = content;
		this.author = author;
	}
	
	public String getContent() {
		return content;
	}
	
	public User getAuthor() {
		return author;
	}
}
