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
	
	public boolean setIsDone() {
		this.isDone = true;
		return isDone;
	}
	
	@Override
	public String toString() {
		return (isDone? "[완료] " : "[미완료] ") + task;
	}
}
