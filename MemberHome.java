

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class MemberHome extends JFrame  implements ActionListener
{
	JLabel label, totalMealLabel, pMcostLabel, totalCostLavel, totalBalanceLabel, totalMealFLabel, pMcostFLabel, totalCostFLavel, totalBalanceFLabel, nameLabel, phoneLabel, nameFLabel, phoneFLabel;
	JButton backBtn, signOutBtn, balanceHistoryBtn, mealHistoryBtn;
	JPanel panel;
	String userId1;
	double totalBazar, totalMeal, totalMealAll, totalBazarAll;
	
	public MemberHome(String name5)
	{
		super("Sample Management System - Member Home");
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		userId1 = name5;

		summationOfBazar();
		summationOfMeal();
		summationOfAllMeal();
		summationOfAllBazar();

		totalMealLabel = new JLabel("Total Meal: ");
		totalMealLabel.setBounds(150, 100, 150, 30);
		panel.add(totalMealLabel);

		
		totalMealFLabel = new JLabel("100");
		totalMealFLabel.setBounds(310, 100, 100, 30);
		String formatted1 = String.format("%.2f", totalMeal);
		totalMealFLabel.setText(formatted1);
		panel.add(totalMealFLabel);


		pMcostLabel = new JLabel("Meal Rate: ");
		pMcostLabel.setBounds(150, 150, 150, 30);
		panel.add(pMcostLabel);

		
		pMcostFLabel = new JLabel("100");
		pMcostFLabel.setBounds(310, 150, 150, 30);
		double mealRate = totalBazarAll / totalMealAll;
		String formatted2 = String.format("%.2f", mealRate);
		pMcostFLabel.setText(formatted2);
		panel.add(pMcostFLabel);


		totalCostLavel = new JLabel("Total Cost: ");
		totalCostLavel.setBounds(150, 200, 150, 30);
		panel.add(totalCostLavel);

		
		totalCostFLavel = new JLabel("100");
		totalCostFLavel.setBounds(310, 200, 100, 30);
		String formatted3 = String.format("%.2f", totalBazar);
		totalCostFLavel.setText(formatted3);
		panel.add(totalCostFLavel);


		totalBalanceLabel = new JLabel("Total Blns: ");
		totalBalanceLabel.setBounds(150, 250, 150, 30);
		panel.add(totalBalanceLabel);

		
		totalBalanceFLabel = new JLabel("100");
		totalBalanceFLabel.setBounds(310, 250, 100, 30);
		double cost = mealRate * totalMeal;
		double blns = totalBazar - cost;
		String formatted4 = String.format("%.2f", blns);
		totalBalanceFLabel.setText(formatted4);
		panel.add(totalBalanceFLabel);


		backBtn = new JButton("Back");
		backBtn.setBounds(50, 50, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);


		signOutBtn = new JButton("SignOut");
		signOutBtn.setBounds(670, 50, 80, 30);
		signOutBtn.addActionListener(this);
		panel.add(signOutBtn);

		
		balanceHistoryBtn = new JButton("Balance History");
		balanceHistoryBtn.setBounds(480, 160, 150, 30);
		balanceHistoryBtn.addActionListener(this);
		panel.add(balanceHistoryBtn);


		mealHistoryBtn = new JButton("Meal History");
		mealHistoryBtn.setBounds(480, 210, 150, 30);
		mealHistoryBtn.addActionListener(this);
		panel.add(mealHistoryBtn);



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
			LogIn lg2 = new LogIn();
			lg2.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(signOutBtn.getText()))
		{
			LogIn lg1 = new LogIn();
			lg1.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(balanceHistoryBtn.getText()))
		{
			BalanceHistoryForMember mh = new BalanceHistoryForMember(userId1);
			mh.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(mealHistoryBtn.getText()))
		{
			MealHistoryForMember meh = new MealHistoryForMember(userId1);
			meh.setVisible(true);
			this.setVisible(false);
		}
		
		else{}
	}

	public void summationOfBazar()
	{
		String query = "SELECT `Cost` FROM `Bazar` where `UserId` = '"+userId1+"';";     
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
		String query = "SELECT `TotalMeal` FROM `Meal` where `UserId` = '"+userId1+"';";     
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