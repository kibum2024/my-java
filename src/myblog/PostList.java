package myblog;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

public class PostList {
	private List<Post> posts;
	
	public PostList() {
		posts = new ArrayList<>();
	}
	
	public void addContent(String title, String content, User author) {
		Post post = new Post(title, content, author);
		posts.add(post);
		System.out.println("게시글이 작성되었습니다.");
	}
	
	public void displayContent() {
		if (posts.isEmpty()) {
			System.out.println("등록된 게시글이 없습니다.");
		} else {
			AtomicInteger index = new AtomicInteger(1);
			posts.forEach(post -> System.out.println(index.getAndIncrement() + ". " + post.getTitle() + " | " + post.getAuthor().getUserName() + " | "));
		}
	}

	public void removeContent(int deleteNum) {
		if (deleteNum >= 0 && deleteNum < posts.size()) {
			System.out.println((deleteNum + 1) + ". " + posts.get(deleteNum).getTitle() + "삭제되었습니다.");
			posts.remove(deleteNum);
		}
	}

	public void addComment(int selectNum, String paramComment, User author) {
		if (selectNum >= 0 && selectNum < posts.size()) {
			Comment comment = new Comment(paramComment, author);
//			posts.get(selectNum).add(comment);
			System.out.println("댓글이 작성되었습니다.");
		}
	}
	
}
