package assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import se.his.it401g.todo.Task;
import se.his.it401g.todo.TaskInputListener;
import se.his.it401g.todo.TaskListener;

/**
 * Implements a simple home task type, following the Task.java interface class.
 *  
 * This file licensed under the <a href="https://creativecommons.org/licenses/by/4.0/">Creative Commons (CC) BY 4.0 license</a>.
 * 
 * @author Dr. Erik Billing, University of Skovde
 *
 */
public class MyCustomTask extends JPanel implements Task, ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The editable text field. 
	 */
	private JTextField text;
	
	/**
	 * The non editable text label.
	 */
	private JLabel textLabel;
	
	/**
	 * Check box holding the completion status. 
	 */
	JCheckBox completed = new JCheckBox();
	
	/**
	 * The task listener used for reporting changes to the main application. 
	 */
	private TaskListener listener;
	
	/**
	 * This is the constructor for the task, initiating the GUI component for the task. Several listeners are used to react to various events in the GUI.  
	 */
	
	private JPanel center;
	
	private Color uncompletedTaskColor = new Color(245, 117, 117);
	private Color completedTaskColor = new Color(51, 255, 153);
	
	public MyCustomTask(){
		super(new BorderLayout());
		this.text = new JTextField("New task",20);
		this.textLabel = new JLabel();	
		this.textLabel.setVisible(false);
		center = new JPanel();
		center.add(text);
		center.add(textLabel);		
		add(center);

		TaskInputListener inputListener = new TaskInputListener(this, text, textLabel);
		this.text.addKeyListener(inputListener);
		this.textLabel.addMouseListener(inputListener);
		
		JButton remove = new JButton("Remove");
		add(remove,BorderLayout.EAST);
		remove.addActionListener(inputListener);
		
		add(completed,BorderLayout.WEST);
		completed.addItemListener(inputListener);

        setMaximumSize(new Dimension(1000,80));
        setBorder(new TitledBorder(getTaskType()));

        // Custom background color set to uncompleted task
		center.setBackground(uncompletedTaskColor);
		this.setBackground(uncompletedTaskColor);
		completed.setBackground(uncompletedTaskColor);
		
		// Date JSpinner 
		Date date = new Date();
		SpinnerModel dateSpinner = new SpinnerDateModel(date, null, null, Calendar.DATE);
		JSpinner spinnerDate = new JSpinner(dateSpinner);
		this.add(spinnerDate, BorderLayout.SOUTH);
		completed.addChangeListener(this); 		
	}

	@Override
	public String getText() {
		return text.getText();
	}

	@Override
	public String getTaskType() {
		return "A PrioTask";
	}

	@Override
	public void setTaskListener(TaskListener t) {
		listener = t;		
	}

	@Override
	public TaskListener getTaskListener() {
		return listener;
	}

	@Override
	public boolean isComplete() {
		return completed.isSelected();
	}

	@Override
	public Component getGuiComponent() {
		// Since this class extends JPanel, it is itself a GUI component, and thus we can return "this". 
		return this;
	}

	// Background color of task changes upon in-/completion 
	@Override
	public void stateChanged(ChangeEvent e) {
		if(completed.isSelected()) {
			center.setBackground(completedTaskColor);
			this.setBackground(completedTaskColor);
			completed.setBackground(completedTaskColor);
		}else {
			center.setBackground(uncompletedTaskColor);
			this.setBackground(uncompletedTaskColor);
			completed.setBackground(uncompletedTaskColor);
		}
	}
}
