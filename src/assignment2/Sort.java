package assignment2;

import java.util.Comparator;

import se.his.it401g.todo.Task;
public class Sort  {    
	// Sorting alphabetic (ascending) order
    public static Comparator<Task> AlphabeticOrder = new Comparator<Task>() {
    	public int compare(Task t1, Task t2) {
    		String taskText1 = t1.getText().toLowerCase();
    		String taskText2 = t2.getText().toLowerCase();

    		return taskText1.compareTo(taskText2);
    	}
    };

    // Sorting according to task type
    public static Comparator<Task> taskTypeOrder = new Comparator<Task>() {
    	public int compare(Task t1, Task t2) {
    		return t1.getTaskType().compareTo(t2.getTaskType());
    	}
    };
   
   // Sorting according to if task has been completed or not
   public static Comparator<Task> completedTasksOrder = new Comparator<Task>() {
	   public int compare(Task t1, Task t2) {
		   return Boolean.compare(t1.isComplete(), t2.isComplete());
	   }
   };
}
