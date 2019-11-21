

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List; 
import java.util.ArrayList;
import java.sql.*;

public class FinanceSummary extends JFrame  implements ActionListener
{
	JLabel dateFLabel, nameLabel, phoneLabel, phoneFLabel, userIdLabel,
	totalBlnsLabel, balanceLabel,dateLabel, totalBazarLabel;
	JButton backBtn, signOutBtn, submitBtn;
	JTextField  userIdTField, balanceTField, dateTField,totalBazarTField, nameFLabel;
	JScrollPane scrollpane;
	ArrayList name;
	JComboBox nameJCBox;
	JPanel panel;
	double totalBazar, totalMeal, totalMealAll, totalBazarAll;
	JScrollPane j = new JScrollPane();
	
	public FinanceSummary()
	{
		super("Sample Management System - Entry Meal");
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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


		submitBtn = new JButton("See Blns");
		submitBtn.setBounds(100, 200, 100, 30);
		submitBtn.addActionListener(this);
		panel.add(submitBtn);


		userIdLabel = new JLabel("UserId: ");
		userIdLabel.setBounds(400, 100, 120, 30);
		panel.add(userIdLabel);



		userIdTField = new JTextField("Hannan");
		userIdTField.setBounds(530, 100, 80, 30);
		panel.add(userIdTField);


		totalBazarLabel = new JLabel("Total Blns: ");
		totalBazarLabel.setBounds(400, 150, 120, 30);
		panel.add(totalBazarLabel);



		totalBazarTField = new JTextField("10000");
		totalBazarTField.setBounds(530, 150, 80, 30);
		panel.add(totalBazarTField);



		balanceLabel = new JLabel("Balance: ");
		balanceLabel.setBounds(400, 200, 120, 30);
		panel.add(balanceLabel);



		balanceTField = new JTextField("-500");
		balanceTField.setBounds(530, 200, 80, 30);
		panel.add(balanceTField);


        nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(50, 350, 80, 30);
		panel.add(nameLabel);

		
		nameFLabel = new JTextField("RSR's System");
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
			ManagerHome mh7 = new ManagerHome();
			mh7.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(submitBtn.getText()))
		{
			double mealrate = 0.0;
			double bill = 0.0;
			double balance = 0.0;


			double myDouble = 42.99;



			summationOfBazar();
			summationOfMeal();
			summationOfAllMeal();
			summationOfAllBazar();

			mealrate = totalBazarAll / totalMealAll;
			bill = mealrate * totalMeal;
			balance = totalBazar - bill;

			userIdTField.setText(nameJCBox.getSelectedItem().toString());
			totalBazarTField.setText(Double.toString(totalBazar));

			String formatted = String.format("%.2f", balance);

			balanceTField.setText(formatted);

			//balanceTField.setText(Double.toString(balance));
		}
		else if(text.equals(signOutBtn.getText()))
		{
			LogIn lg11 = new LogIn();
			lg11.setVisible(true);
			this.setVisible(false);
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


	public void summationOfBazar()
	{
		String name = nameJCBox.getSelectedItem().toString();
		String query = "SELECT `Cost` FROM `Bazar` where `UserId` = '"+name+"';";     
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
			totalBazar = 0.0;
						
			while(rs.next())
			{
                double bazar = Double.parseDouble(rs.getString("Cost"));
                totalBazar = totalBazar + bazar;
               
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
  


   public void summationOfMeal()
	{
		String name = nameJCBox.getSelectedItem().toString();
		String query = "SELECT `TotalMeal` FROM `Meal` where `UserId` = '"+name+"';";     
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
			totalMeal = 0.0;
						
			while(rs.next())
			{
                double mealNumber = Double.parseDouble(rs.getString("TotalMeal"));
                totalMeal = totalMeal + mealNumber;
               
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


	public void summationOfAllBazar()
	{
		String name = nameJCBox.getSelectedItem().toString();
		String query = "SELECT `Cost` FROM `Bazar`;";     
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
			totalBazarAll = 0.0;
						
			while(rs.next())
			{
                double bazar = Double.parseDouble(rs.getString("Cost"));
                totalBazarAll = totalBazarAll + bazar;
               
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


	public void summationOfAllMeal()
	{
		String name = nameJCBox.getSelectedItem().toString();
		String query = "SELECT `TotalMeal` FROM `Meal`;";     
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
			totalMealAll = 0.0;
						
			while(rs.next())
			{
                double mealNumber = Double.parseDouble(rs.getString("TotalMeal"));
                totalMealAll = totalMealAll + mealNumber;
               
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

	

	
	
}