

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List; 
import java.util.ArrayList;
import java.sql.*;

public class EditMeal extends JFrame  implements ActionListener
{
	JLabel dateLabel, dateFLabel, nameLabel, phoneLabel, nameFLabel, phoneFLabel,
	userIdLabel, bFastLabel, lunchLabel, dinnerLabel;
	JButton backBtn, signOutBtn, submitBtn, addBtn;
	JTextField dateTField, bFastTField, lunchTField, dinnerTField;
	JScrollPane scrollpane;
	ArrayList name;
	JComboBox nameJCBox;
	JPanel panel;
	boolean flag1;
	JScrollPane j = new JScrollPane();
	
	public EditMeal()
	{
		super("Sample Management System - Entry Meal");
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		flag1 = false;
		
		panel = new JPanel();
		panel.setLayout(null);

	 	name = new ArrayList<String>(); 

		name.add("Name");

		loadName();


		backBtn = new JButton("Back");
		backBtn.setBounds(50, 50, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);


		signOutBtn = new JButton("SignOut");
		signOutBtn.setBounds(670, 50, 80, 30);
		signOutBtn.addActionListener(this);
		panel.add(signOutBtn);


		dateLabel = new JLabel("Date: ");
		dateLabel.setBounds(100, 100, 60, 30);
		panel.add(dateLabel);

		
		dateTField = new JTextField("17-12-2018");
		dateTField.setBounds(150, 100, 150, 30);
		panel.add(dateTField);


		nameJCBox = new JComboBox(name.toArray());
		nameJCBox.setBounds(100, 150, 80, 30);
		panel.add(nameJCBox);


		submitBtn = new JButton("Search");
		submitBtn.setBounds(100, 200, 100, 30);
		submitBtn.addActionListener(this);
		panel.add(submitBtn);


		bFastLabel = new JLabel("Breakfast: ");
		bFastLabel.setBounds(400, 100, 120, 30);
		panel.add(bFastLabel);



		bFastTField = new JTextField("0");
		bFastTField.setBounds(530, 100, 30, 30);
		panel.add(bFastTField);


		lunchLabel = new JLabel("Lunch: ");
		lunchLabel.setBounds(400, 150, 120, 30);
		panel.add(lunchLabel);


		lunchTField = new JTextField("0");
		lunchTField.setBounds(530, 150, 30, 30);
		panel.add(lunchTField);


		dinnerLabel = new JLabel("Dinner: ");
		dinnerLabel.setBounds(400, 200, 120, 30);
		panel.add(dinnerLabel);



		dinnerTField = new JTextField("0");
		dinnerTField.setBounds(530, 200, 30, 30);
		panel.add(dinnerTField);



		addBtn = new JButton("Add");
		addBtn.setBounds(400, 250, 80, 30);
		addBtn.addActionListener(this);
		panel.add(addBtn);


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
			ManagerHome mh4 = new ManagerHome();
			mh4.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(signOutBtn.getText()))
		{
			LogIn lg4 = new LogIn();
			lg4.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(submitBtn.getText()))
		{
			searchInDatabase();

		}
		else if(text.equals(addBtn.getText()))
		{
			if(flag1 == true){
				String name2 = nameJCBox.getSelectedItem().toString();
				String date2 = dateTField.getText();
				deleteMeal(name2, date2);
			}
			insertIntoDB();
		}
		
		else{}
	}


	public void loadName()
	{
		String query = "SELECT `Name` FROM `member`;";     
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
                String userName = rs.getString("Name");

                System.out.println(userName + "\n");
                name.add(userName);
               
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
  

	public void insertIntoDB()
	{
		String name = nameJCBox.getSelectedItem().toString();
		String date = dateTField.getText();
		int bFast = Integer.parseInt(bFastTField.getText());
		int lunch = Integer.parseInt(lunchTField.getText());
		int dinner = Integer.parseInt(dinnerTField.getText());
		int totalMeal = bFast + lunch + dinner;
		
		
		
		String query1 = "INSERT INTO Meal VALUES ('"+name+"','"+date+"',"+ bFast+","+lunch+","+dinner+","+totalMeal+");";
		System.out.println(query1);

        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sayed","root","");
			System.out.println("Okay");
			Statement stm = con.createStatement();
			System.out.println("Okay2");
			stm.executeUpdate(query1);
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



    public void searchInDatabase()
	{	
		String name1 = nameJCBox.getSelectedItem().toString();
		String date1 = dateTField.getText();

		String query = "SELECT `bFast`, `lunch`, `dinner` FROM `Meal` where `UserId` = '"+name1+"' AND `date` = '"+date1+"';";     
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
					
			rs.next();
            String breakfast1 = rs.getString("BFast");
            bFastTField.setText(breakfast1);


            //rs.next();
            String lunch1 = rs.getString("Lunch");
            lunchTField.setText(lunch1);


            //rs.next();
            String dinner1 = rs.getString("Dinner");
            dinnerTField.setText(dinner1);

            flag1 = true;
               
			
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


	public void deleteMeal(String name3, String date3)
	{
		
		String query1 = "delete FROM `Meal` where `UserId` = '"+name3+"' AND `Date` = '"+date3+"'  ;";    
		System.out.println(query1);
		
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sayed", "root", "");
			Statement stm = con.createStatement();
			
			stm.executeUpdate(query1);
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