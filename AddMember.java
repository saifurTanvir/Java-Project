
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class AddMember extends JFrame implements ActionListener
{
	JLabel useIdLabel, nameELabel, phoneELabel, infoLavel, nameLabel, nameFLabel, phoneLabel, phoneFLabel, passLabel;
	JTextField useIdFLabel, nameETF, phoneETF, infoTF;
	JButton addBtn, exitBtn, backBtn, signOutBtn;
	JPasswordField passField;
	JComboBox nameJCBox;
	JScrollPane scrollpane;
	JPanel panel, panel2;
	String userId;
	
	
	public AddMember()
	{
		super("Sample Management System - Add Member");
		
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		scrollpane = new JScrollPane();

		panel = new JPanel();
		panel.setLayout(null);

		String designation[] = {"Manager", "Member"};

		backBtn = new JButton("Back");
		backBtn.setBounds(50, 50, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);


		signOutBtn = new JButton("SignOut");
		signOutBtn.setBounds(670, 50, 80, 30);
		signOutBtn.addActionListener(this);
		panel.add(signOutBtn);


		useIdLabel = new JLabel("Username: ");
		useIdLabel.setBounds(250, 90, 80, 30);
		panel.add(useIdLabel);
		
		useIdFLabel = new JTextField();
		useIdFLabel.setBounds(340, 90, 200, 30);
		panel.add(useIdFLabel);

		nameELabel = new JLabel("Name: ");
		nameELabel.setBounds(250, 130, 80, 30);
		panel.add(nameELabel);

		
		nameETF = new JTextField();
		nameETF.setBounds(340, 130, 200, 30);
		panel.add(nameETF);


		phoneELabel = new JLabel("Phone: ");
		phoneELabel.setBounds(250, 170, 80, 30);
		panel.add(phoneELabel);
		
		phoneETF = new JTextField();
		phoneETF.setBounds(340, 170, 200, 30);
		panel.add(phoneETF);


		passLabel = new JLabel("Password: ");
		passLabel.setBounds(250, 210, 80, 30);
		panel.add(passLabel);
		
		passField = new JPasswordField();
		passField.setBounds(340, 210, 200, 30);
		panel.add(passField);



		infoLavel = new JLabel("Info: ");
		infoLavel.setBounds(250, 265, 80, 30);
		panel.add(infoLavel);

		
		infoTF = new JTextField();
		infoTF.setBounds(340, 265, 200, 100);
		panel.add(infoTF);



		nameJCBox = new JComboBox(designation);
		nameJCBox.setBounds(250, 370, 80, 30);
		panel.add(nameJCBox);

		
		addBtn = new JButton("Add");
		addBtn.setBounds(460, 370, 80, 30);
		addBtn.addActionListener(this);
		panel.add(addBtn);


		nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(50, 400, 80, 30);
		panel.add(nameLabel);

		
		nameFLabel = new JLabel("RSR's System");
		nameFLabel.setBounds(140, 400, 200, 30);
		panel.add(nameFLabel);


		phoneLabel = new JLabel("Phone: ");
		phoneLabel.setBounds(490, 400, 80, 30);
		panel.add(phoneLabel);

		
		phoneFLabel = new JLabel("+8801793915354");
		phoneFLabel.setBounds(580, 400, 200, 30);
		panel.add(phoneFLabel);
		

		this.add(panel);
	}

  

	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(backBtn.getText()))
		{
			ManagerHome mh1 = new ManagerHome();
			mh1.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(signOutBtn.getText()))
		{
			LogIn lg1 = new LogIn();
			lg1.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(addBtn.getText()))
		{
			insertIntoDB();
		}
		
		else{}
	}



	public void insertIntoDB()
	{
		String newId = useIdFLabel.getText();
		String newPass = passField.getText();
		String eName = nameETF.getText();
		String phnNo = phoneETF.getText();
		String role = nameJCBox.getSelectedItem().toString();
		String info = infoTF.getText();
		int status = 0;


		String name = nameJCBox.getSelectedItem().toString();

		if(name.equals("Member")){
			status = 0;
		}
		else if(name.equals("Manager")){
			status = 1;
		}
		
		
		String query1 = "INSERT INTO member VALUES ('"+newId+"','"+eName+"','"+ phnNo+"','"+role+"','"+info+"');";
		String query2 = "INSERT INTO login VALUES ('"+newId+"','"+newPass+"',"+status+");";
		System.out.println(query1);
		System.out.println(query2);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sayed","root","");
			System.out.println("Okay");
			Statement stm = con.createStatement();
			System.out.println("Okay2");
			stm.executeUpdate(query1);
			System.out.println("Okay3");
			stm.executeUpdate(query2);
			System.out.println("Okay4");
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Success !!!");
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
    }	


	
	
	
}

