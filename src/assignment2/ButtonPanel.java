package assignment2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ButtonPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton buttonCustomTask;
	private JButton buttonHomeTask;
	private JButton buttonSortAlphabetically;
	private JButton buttonSortCompletedTasks;
	private JButton buttonSortTaskType;
	private JButton buttonStudyTask; 
	
	// a final taskpanel, so everything happens on this panel
	final TaskPanel taskPanel; 

	public ButtonPanel(TaskPanel taskPanelOrigin) {
		// ButtonPanel properties
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		setPreferredSize(new Dimension(1000, 80));
		setBackground(Color.white);
		// final taskPanel 'tasks' equals the sent in parameter 'taskPanel'
		taskPanel = taskPanelOrigin;

		// Buttons for creating new task		
		buttonStudyTask = taskButton("StudyTask");
		add(buttonStudyTask);
		
		buttonHomeTask = taskButton("HomeTask");
		add(buttonHomeTask);
		
		buttonCustomTask = taskButton("A PrioTask");
		add(buttonCustomTask);
		
		// Buttons for sorting
		buttonSortAlphabetically = sortButton("Sort A to Z"); 
		add(buttonSortAlphabetically);
		
		buttonSortCompletedTasks = sortButton("Un/checked"); 
		add(buttonSortCompletedTasks);
		
		buttonSortTaskType = sortButton("Sort Type"); 
		add(buttonSortTaskType);
	}

	// Creates a new button
	private JButton button(String buttonTitle, Color backgroundColor) {
		JButton createButton = new JButton(buttonTitle);
		createButton.setBackground(backgroundColor);
		createButton.addActionListener(this);
		return createButton;
	}

	private JButton sortButton(String buttonTitle) {
		return button(buttonTitle, Color.LIGHT_GRAY);
	}
	
	private JButton taskButton(String buttonTitle) {
		return button(buttonTitle, Color.getHSBColor(195, 50, 10));
	}
	
	
	// Listens to which button that is pressed
	@Override
	public void actionPerformed(ActionEvent btnPressed) {
		try {
			if (btnPressed.getSource().equals(buttonStudyTask)) {
				taskPanel.createStudyTask();
			} 
			else if (btnPressed.getSource().equals(buttonHomeTask)) {
				taskPanel.createHomeTask();
			} 
			else if (btnPressed.getSource().equals(buttonCustomTask)) {
				taskPanel.createMyCustomTask();
			} 
			else if (btnPressed.getSource().equals(buttonSortAlphabetically)) {
				taskPanel.sortAlpha();
			} 
			else if (btnPressed.getSource().equals(buttonSortTaskType)) {
				taskPanel.sortType();
			} 
			else if (btnPressed.getSource().equals(buttonSortCompletedTasks)) {
				taskPanel.sortCompletedTasks();
			} 
			else {
				// Error message in form of a dialog
				JFrame frame = new JFrame("frame");
				JDialog errorDialogBox = new JDialog(frame, "Error");
				JLabel errorLabel = new JLabel("Non-identifiable action. Please restart the program", SwingConstants.CENTER);
				errorDialogBox.add(errorLabel);
				errorDialogBox.setSize(200, 200);
				errorDialogBox.setBounds(450, 150, 500, 100);
				errorDialogBox.setVisible(true);
			}
		} finally {
			// Updates and communicates task status
			taskPanel.tasksCompleted();
			revalidate();
			repaint();
		}
	}
}
