

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class DeleteMember extends JFrame implements ActionListener
{
	JLabel nameSearchLabel, dateFLabel, nameLabel, phoneLabel, nameFLabel, phoneFLabel, 
	nameDLabel, phoneDLabel, infoDLabel, desigDLabel;
	JButton backBtn, signOutBtn, submitBtn, deleteBtn;
	JTextField textField, nameDFLabel, phoneDFLabel, desigDFLabel, infoDFLabel;
	JTextArea textArea;
	JScrollPane scrollpane;
	JPanel panel;
	JScrollPane j = new JScrollPane();
	
	public DeleteMember()
	{
		super("Sample Management System - Delete Member");
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);


		backBtn = new JButton("Back");
		backBtn.setBounds(50, 50, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);


		signOutBtn = new JButton("SignOut");
		signOutBtn.setBounds(670, 50, 80, 30);
		signOutBtn.addActionListener(this);
		panel.add(signOutBtn);
 

		nameSearchLabel = new JLabel("Name: ");
		nameSearchLabel.setBounds(100, 100, 60, 30);
		panel.add(nameSearchLabel);

		
		textField = new JTextField("");
		textField.setBounds(150, 100, 200, 30);
		panel.add(textField);



		submitBtn = new JButton("Search");
		submitBtn.setBounds(100, 140, 100, 30);
		submitBtn.addActionListener(this);
		panel.add(submitBtn);



		nameDLabel = new JLabel("Name: ");
		nameDLabel.setBounds(400, 100, 80, 30);
		panel.add(nameDLabel);

		
		nameDFLabel = new JTextField("Hannan");
		nameDFLabel.setBounds(490, 100, 150, 30);
		panel.add(nameDFLabel);


		phoneDLabel = new JLabel("Phone: ");
		phoneDLabel.setBounds(400, 140, 80, 30);
		panel.add(phoneDLabel);

		
		phoneDFLabel = new JTextField("+8801865 965478");
		phoneDFLabel.setBounds(490, 140, 150, 30);
		panel.add(phoneDFLabel);


		desigDLabel = new JLabel("Desig: ");
		desigDLabel.setBounds(400, 180, 80, 30);
		panel.add(desigDLabel);

		
		desigDFLabel = new JTextField("... ... ... ...");
		desigDFLabel.setBounds(490, 180, 150, 30);
		panel.add(desigDFLabel);


		infoDLabel = new JLabel("Info: ");
		infoDLabel.setBounds(400, 220, 100, 30);
		panel.add(infoDLabel);

		
		infoDFLabel = new JTextField("... ... ... ...");
		infoDFLabel.setBounds(490, 220, 150, 80);
		panel.add(infoDFLabel);


		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(400, 315, 100, 30);
		deleteBtn.addActionListener(this);
		panel.add(deleteBtn);


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


	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(backBtn.getText()))
		{
			ManagerHome mh2 = new ManagerHome();
			mh2.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(signOutBtn.getText()))
		{
			LogIn ln2 = new LogIn();
			ln2.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(deleteBtn.getText()))
		{
			String userId = textField.getText();
			deleteMember(userId);
		}
		else if(text.equals(submitBtn.getText()))
		{
			String userId = textField.getText();
			searchUseID(userId);
		}
		else{}
	}

	
	public void searchUseID(String nam)
	{
		//String nam = "Tanvir";
		String query = "SELECT `Name`, `PhoneNumber`, `Designation`, `Info` FROM `member` where `UserId` = '"+nam+"' ;";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sayed","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
			
					
			while(rs.next())
			{   
				
                String name = rs.getString("Name");
                String phone = rs.getString("PhoneNumber");
                String desig = rs.getString("Designation");
                String info = rs.getString("Info");

				nameDFLabel.setText(name);
				phoneDFLabel.setText(phone);
				desigDFLabel.setText(desig);
				infoDFLabel.setText(info);
				
			}
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
	}


	public void deleteMember(String nam)
	{
		
		String query1 = "delete FROM `member` where `UserId` = '"+nam+"' ;";    
		String query2 = "delete FROM `login` where `userId` = '"+nam+"' ;"; 
		System.out.println(query1);
		System.out.println(query2);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sayed", "root", "");
			Statement stm = con.createStatement();
			
			stm.executeUpdate(query1);
			stm.executeUpdate(query2);
			System.out.println("Tanvir");
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Success !!!");
			
			
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
	}


	/*public void deleteMember(String nam)
	{
		//String nam = "Tanvir";
		String query1 = "delete `Name` FROM `member` where `UserId` = '"+nam+"' ;";    
		String query2 = "delete `userId` FROM `login` where `UserId` = '"+nam+"' ;";   
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query1);
		System.out.println(query2);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sayed","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeUpdate(query1);//getting result
			rs = st.executeUpdate(query2);//getting result
			System.out.println("Deleted Successfully");
			JOptionPane.showMessageDialog(this,"Deleted Successfully"); 
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
	}*/
	
	
}

	
