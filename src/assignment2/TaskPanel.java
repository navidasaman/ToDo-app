package assignment2;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JPanel;
import se.his.it401g.todo.HomeTask;
import se.his.it401g.todo.StudyTask;
import se.his.it401g.todo.Task;
import se.his.it401g.todo.TaskListener;

public class TaskPanel extends JPanel implements TaskListener {
	private static final long serialVersionUID = 1L;
	private ArrayList<Task> taskArrayList = new ArrayList<Task>();
	private Task homeTask;
	private Task priorityTask;
	private Task studyTask;
	private int quantityCompletedTasks;
	protected JLabel tasksCompletedMessage = new JLabel();

	// Create a new HomeTask
	public void createHomeTask() {
		homeTask = new HomeTask();
		this.add(homeTask.getGuiComponent());
		taskArrayList.add(homeTask);
		homeTask.setTaskListener(this);
	}

	// Create a new StudyTask
	public void createStudyTask() {
		studyTask = new StudyTask();
		this.add(studyTask.getGuiComponent());
		taskArrayList.add(studyTask);
		studyTask.setTaskListener(this);
	}

	// Create a new A PrioTask
	public void createMyCustomTask() {
		priorityTask = new MyCustomTask();
		this.add(priorityTask.getGuiComponent());
		taskArrayList.add(priorityTask);
		priorityTask.setTaskListener(this);
	}

	// Method for sorting the tasks in alphabetical order
	public void sortAlpha() {
		// Collection of tasks in taskList is sorted in alphabetical order using a
		// comparator
		Collections.sort(taskArrayList, Sort.AlphabeticOrder);
		// Adding each task object to the ArrayList "taskList" in the newly sorted
		// order.
		for (Task task : taskArrayList) {
			this.add(task.getGuiComponent());
		}
	}

	// Method used when wanting to sort tasks according to task type
	public void sortType() {
		Collections.sort(taskArrayList, Sort.taskTypeOrder);
		for (Task task : taskArrayList) {
			this.add(task.getGuiComponent());
		}
	}

	// Method used when wanting to sort tasks according to their completion status
	public void sortCompletedTasks() {
		Collections.sort(taskArrayList, Sort.completedTasksOrder);
		for (Task task : taskArrayList) {
			this.add(task.getGuiComponent());
		}
	}

	@Override
	public void taskChanged(Task t) {
		// TODO Auto-generated method stub
	}

	// Method used to communicate tasks status
	public JLabel tasksCompleted() {
		tasksCompletedMessage.setText(quantityCompletedTasks + " out of " + taskArrayList.size() + " tasks completed.");
		return tasksCompletedMessage;
	}

	// Method incrementing the quantity of completed tasks when the 'checked box'
	// has
	// been checked and communicating the updated status of tasks that had been
	// completed
	@Override
	public void taskCompleted(Task t) {
		quantityCompletedTasks += 1;
		revalidate();
		repaint();
		tasksCompleted();
	}

	// Method decrementing the quantity of tasks when the 'checked box' has been
	// unchecked and communicating the updated quantity of uncompleted tasks.
	@Override
	public void taskUncompleted(Task t) {
		quantityCompletedTasks -= 1;
		revalidate();
		repaint();
		tasksCompleted();
	}

	@Override
	public void taskCreated(Task t) {
		// TODO Auto-generated method stub
	}

	// Method keeping track of when a task is removed
	@Override
	public void taskRemoved(Task t) {
		if (t.isComplete()) {
			quantityCompletedTasks -= 1;
		}
		// Remove quantity of tasks in array
		remove(t.getGuiComponent());
		taskArrayList.remove(t);
		revalidate();
		repaint();
		tasksCompleted();
	}
}
