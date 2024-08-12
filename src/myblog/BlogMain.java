package myblog;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BlogMain {
	private static User AuthorId;
	private static final Scanner scanner = new Scanner(System.in);
	private static final PostList postList = new PostList();

	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
		UserList userList = new UserList();
//		PostList postList = new PostList();
		
		while (true) {
			System.out.println("=== My Blog ===");
			System.out.println("1.로그인");
			System.out.println("2.회원가입");
			System.out.println("3.프로그램 종료");
			System.out.print("선택 : ");

			int choice = 0;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("잘못된 입력입니다. 숫자를 입력해야 합니다.");
			} finally {
				scanner.nextLine();  // 버퍼를 비워 다음 입력을 받을 준비를 함
			}
			
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
//		Scanner scanner = new Scanner(System.in);
//		PostList postList = new PostList();
		
		while (true) {
			System.out.println("=== 게시판 ===");
			System.out.println("1.게시글 등록");
			System.out.println("2.게시글 조회");
			System.out.println("3.게시글 삭제");
			System.out.println("4.프로그램 종료");
			System.out.print("선택 : ");

			int choice = 0;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("잘못된 입력입니다. 숫자를 입력해야 합니다.");
			} finally {
				scanner.nextLine();  // 버퍼를 비워 다음 입력을 받을 준비를 함
			}
			
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
					System.out.print("게시글 번호선택(댓글작성) (0:취소):");
					int selectNum = scanner.nextInt();
					if (selectNum == 0) {
						break;
					} else {
						comment(selectNum - 1);
					}
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
		boolean commentChk = true;

		while (commentChk) {
            postList.displayComment(selectNum);
            System.out.println("1.댓글 달기");
            System.out.println("2.뒤로가기");
            System.out.print("선택 : ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해야 합니다.");
            } finally {
                scanner.nextLine();  // 버퍼를 비워 다음 입력을 받을 준비를 함
            }

            switch (choice) {
                case 1:
                    System.out.print("댓글내용:");
                    String comment = scanner.nextLine();
                    postList.addComment(selectNum, comment, AuthorId);
                    break;
                case 2:
                    commentChk = false;
                    break;
            }
        }

	}

}
