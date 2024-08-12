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
			Post selectedPost = posts.get(selectNum);
			Comment newComment = new Comment(paramComment, author);
			selectedPost.getComments().add(newComment);
			System.out.println("댓글이 작성되었습니다.");
		} else {
			System.out.println("잘못된 게시글 번호입니다.");
		}
	}

	public void displayComment(int selectNum) {
		if (selectNum >= 0 && selectNum < posts.size()) {
			Post selectedPost = posts.get(selectNum);
			if (selectedPost != null) {
				System.out.println("== 게시글 ==");
				System.out.println("게시글 제  목: " + posts.get(selectNum).getTitle());
				System.out.println("게시글 작성자: " + posts.get(selectNum).getAuthor().getUserName());
				System.out.println("게시글 내  용: " + posts.get(selectNum).getContent());
				System.out.println("");
				System.out.println("== 댓  글 ==");
				List<Comment> comments = selectedPost.getComments();
				if (comments != null && comments.size() > 0) {
					AtomicInteger index = new AtomicInteger(1);
					comments.forEach(comment ->
							System.out.println(index.getAndIncrement() + ". " + comment.getContent() + " | " + comment.getAuthor().getUserName())
					);
					System.out.println("");
				} else {
					System.out.println("댓글이 없습니다.");
				}
			}
		} else {
			System.out.println("선택된 게시글이 존재하지 않습니다.");
		}
	}
}
