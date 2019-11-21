
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MealHistoryForManager extends JFrame 
{
	JLabel dateLabel, dateFLabel, nameLabel, phoneLabel, nameFLabel, phoneFLabel;
	JButton backBtn, signOutBtn, submitBtn;
	JTextField textField;
	JTextArea textArea;
	JScrollPane scrollpane;
	JPanel panel;
	JScrollPane j = new JScrollPane();
	
	public MealHistoryForManager()
	{
		super("Sample Management System - Meal History(M)");
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);


		backBtn = new JButton("Back");
		backBtn.setBounds(50, 50, 80, 30);
		//loginBtn.addActionListener(this);
		
		panel.add(backBtn);


		signOutBtn = new JButton("SignOut");
		signOutBtn.setBounds(670, 50, 80, 30);
		//loginBtn.addActionListener(this);
		panel.add(signOutBtn);


		dateLabel = new JLabel("Name: ");
		dateLabel.setBounds(100, 100, 60, 30);
		panel.add(dateLabel);

		
		textField = new JTextField("Hannan");
		textField.setBounds(150, 100, 150, 30);
		panel.add(textField);



		submitBtn = new JButton("Search");
		submitBtn.setBounds(100, 140, 100, 30);
		//loginBtn.addActionListener(this);
		panel.add(submitBtn);


		textArea = new JTextArea("I am so happy!");
        textArea.setForeground(Color.red);
        textArea.setText(textArea.getText() + "\nAny Problem!");
        scrollpane = new JScrollPane(textArea);
        scrollpane.setBounds(400, 100, 350, 250);
        panel.add(scrollpane);


        nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(50, 350, 80, 30);
		panel.add(nameLabel);

		
		nameFLabel = new JLabel("RSR's System");
		nameFLabel.setBounds(140, 350, 200, 30);
		panel.add(nameFLabel);


		phoneLabel = new JLabel("Phone: ");
		phoneLabel.setBounds(490, 350, 80, 30);
		panel.add(phoneLabel);

		
		phoneFLabel = new JLabel("+8801793915354");
		phoneFLabel.setBounds(580, 350, 200, 30);
		panel.add(phoneFLabel);

		


		this.add(panel);
	}
	
	
}