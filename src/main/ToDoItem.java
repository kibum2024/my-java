package main;

public class ToDoItem {
	private String task;
	private boolean isDone;
	
	public ToDoItem(String task) {
		this.task = task;
		this.isDone = false; 
	}
	
	public String getTask() {
		return task;
	}
	
	public boolean getIsDone() {
		return isDone;
	}
}
