package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ToDo {
	public JFrame frame;
	private TaskPanel taskPanelOrigin = new TaskPanel();

	public static void main(String[] args) {
		new ToDo();
	}

	private ToDo() {
		// Creates frame
		frame = new JFrame("ToDo");
		// Sets frame properties
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(500, 500));
		frame.setLayout(new BorderLayout());

		// Adds ButtonPanel to the frame
//		frame.add(new ButtonPanel(taskPanelOrigin), BorderLayout.NORTH);
		ButtonPanel buttonPanel = new ButtonPanel(taskPanelOrigin);
		frame.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new GridLayout(2, 3)); 

		// Adds scrollable TaskPanel to the frame
		taskPanelOrigin.setLayout(new BoxLayout(taskPanelOrigin, BoxLayout.Y_AXIS));
		frame.add(taskPanelOrigin, BorderLayout.CENTER);
		JScrollPane scrollWindowForTasks = new JScrollPane(taskPanelOrigin);
		frame.add(scrollWindowForTasks);

		// adds label with x of y tasks completed in a panel
		frame.add(FinishedTasksPanel.completedTasks(), BorderLayout.SOUTH);
		FinishedTasksPanel finishedTasksPanel = new FinishedTasksPanel(taskPanelOrigin);
		taskPanelOrigin.tasksCompleted();
		finishedTasksPanel.completedTasksMsg();

		frame.setVisible(true);
	}
}
