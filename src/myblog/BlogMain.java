package myblog;

import java.util.Scanner;

public class BlogMain {
	public static User AuthorId;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserList userList = new UserList();
		PostList postList = new PostList();
		
		while (true) {
			System.out.println("=== My Blog ===");
			System.out.println("1.로그인");
			System.out.println("2.회원가입");
			System.out.println("3.프로그램 종료");
			System.out.print("선택 : ");
	
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
					System.out.println("<< 로그인 >>");
					System.out.print("사용자ID:");
					String userId = scanner.nextLine();
					System.out.print("비밀번호:");
					String userPassword = scanner.nextLine();
					User foundUser = userList.findUserId(userId, userPassword);
					if (foundUser != null) {
						System.out.println(foundUser.getUserName() + "님 환영합니다.");
						AuthorId = foundUser;
						content();
					}
					break;
				case 2:
					System.out.println("<< 회원가입 >>");
					System.out.print("새로운 사용자ID:");
					String newUserID = scanner.nextLine();
					System.out.print("새로운 사용자명:");
					String newUserName = scanner.nextLine();
					System.out.print("새로운 비밀번호:");
					String newUserPassword = scanner.nextLine();
					userList.addUserInfo(newUserID, newUserName, newUserPassword);
					break;
				case 3:
					System.out.println("프로그램을 종료합니다.");
					scanner.close();
					System.exit(0);
					break;
			}
		}
	}
	
	
	public static void content() {
		Scanner scanner = new Scanner(System.in);
		PostList postList = new PostList();
		
		while (true) {
			System.out.println("=== 게시판 ===");
			System.out.println("1.게시글 등록");
			System.out.println("2.게시글 조회");
			System.out.println("3.게시글 삭제");
			System.out.println("4.프로그램 종료");
			System.out.print("선택 : ");
	
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
					System.out.println("<< 게시글 >>");
					System.out.print("게시글 제목:");
					String title = scanner.nextLine();
					System.out.print("게시글 내용:");
					String content = scanner.nextLine();
					postList.addContent(title, content, AuthorId);
					break;
				case 2:
					System.out.println("<< 게시글 조회 >>");
					postList.displayContent();
					System.out.print("게시글 번호 (0:취소):");
					int selectNum = scanner.nextInt();
					if (selectNum == 0) {
						break;
					} else {
						comment(selectNum);
					}
//					System.out.print("게시글 번호:");
//					int selectNum = scanner.nextInt();
//					postList.removeContent(selectNum - 1);
					break;
				case 3:
					System.out.println("<< 게시글 삭제 >>");
					postList.displayContent();
					System.out.print("삭제글 번호:");
					int deleteNum = scanner.nextInt();
					postList.removeContent(deleteNum - 1);
					break;
				case 4:
					System.out.println("프로그램을 종료합니다.");
					scanner.close();
					System.exit(0);
					break;
			}
		}
	}
	
	public static void comment(int selectNum) {
		Scanner scanner = new Scanner(System.in);
		PostList postList = new PostList();
		
		while (true) {
			System.out.println("1.댓글 달기");
			System.out.println("2.뒤로가기");
			System.out.print("선택 : ");
	
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
					System.out.print("댓글내용:");
					String comment = scanner.nextLine();
					postList.addComment(selectNum, comment, AuthorId);
					break;
				case 2:
					break;
			}
		}

	}

}
