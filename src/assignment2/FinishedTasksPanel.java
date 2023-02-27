package assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinishedTasksPanel {
	private static JPanel labelPanel = new JPanel();
	final TaskPanel taskPanel;

	public FinishedTasksPanel(TaskPanel taskPanelOrigin) {
		taskPanel = taskPanelOrigin;
	}

	// Sets labelPanel properties
	public static Component completedTasks() {
		labelPanel.setPreferredSize(new Dimension(1000, 30));
		return labelPanel;
	}

	// Creates a label for tasks in-/completed
	public JLabel completedTasksMsg() {
		JLabel tasksCompletedMsg = new JLabel();
		labelPanel.add(taskPanel.tasksCompletedMessage, BorderLayout.SOUTH);
		labelPanel.setBackground(Color.white);

		return tasksCompletedMsg;
	}
}
