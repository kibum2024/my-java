package main;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
	private List<ToDoItem> items;
	
	public ToDoList() {
		items = new ArrayList<>();
	}
	
	public void addItem(String task) {
		items.add(new ToDoItem(task));
		System.out.println("추가됨 : " + task);
	}
	
	public void displayItems() {
		if (items.isEmpty()) {
			System.out.println("등록한 할 일이 없습니다.");
		} else {
			System.out.println("To-Do 목록");
			for (int i = 0; i < items.size(); i++) {
				System.out.println((i + 1) + ". " + items.get(i));
			}
		}
	}
	
	public void markAsDone(int selectNum) {
		if (selectNum >= 0 && selectNum < items.size()) {
			items.get(selectNum).setIsDone();
			System.out.println((selectNum + 1) + ". " + items.get(selectNum) + "완료되었습니다.");
		}
	}
	
	public void deleteAsDone(int deleteNum) {
		if (deleteNum >= 0 && deleteNum < items.size()) {
			System.out.println((deleteNum + 1) + ". " + items.get(deleteNum) + "삭제되었습니다.");
			items.remove(deleteNum);
		}
	}
}
