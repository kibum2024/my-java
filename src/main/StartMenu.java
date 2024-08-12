package main;

import java.util.Scanner;

public class StartMenu {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ToDoList toDoList = new ToDoList();

		while (true) {
			System.out.println("\nTo-Do 리스트 애플리케이션");
			System.out.println("1. 할 일 추가");
			System.out.println("2. 할 일 목록 보기");
			System.out.println("3. 할 일 완료 표시");
			System.out.println("4. 할 일 삭제");
			System.out.println("5. 종료");
			System.out.println("선택: ");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1: 
					System.out.println("추가할 할 일:");
					String task = scanner.nextLine();
					toDoList.addItem(task);
					break;
				case 2: 
					toDoList.displayItems();
					break;
				case 3: 
					System.out.println("완료할 항목 번호");
					toDoList.displayItems();
					int selectNum = scanner.nextInt() - 1;
					toDoList.markAsDone(selectNum);
					break;
				case 4: 
					System.out.println("삭제할 항목 번호");
					toDoList.displayItems();
					int deleteNum = scanner.nextInt() - 1;
					toDoList.deleteAsDone(deleteNum);
					break;
				case 5: 
					System.out.println("프로그램을 종료합니다.");
					scanner.close();
					System.exit(0);
					break;
				default:
					System.out.println("잘 못된 선택입니다. 다시 선택하세요.");
			}
		}
	}
}
