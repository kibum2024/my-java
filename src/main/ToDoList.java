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
	}
	
}
