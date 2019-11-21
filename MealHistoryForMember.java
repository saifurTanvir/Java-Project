
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List; 
import java.util.ArrayList;
import java.sql.*;


public class MealHistoryForMember extends JFrame  implements ActionListener
{
	JLabel  title, dateFLabel, userIdLabel, upperLabel, totalBazarLabel, totalBazarFLabel ;
	JButton backBtn, signOutBtn, submitBtn;
	JTextArea textArea;
	JScrollPane scrollpane;
	JPanel panel;
	JScrollPane j = new JScrollPane();
	Double allMeal;
	String userId2;
	public MealHistoryForMember(String name6)
	{
		super("Sample Management System - Entry Meal");
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		panel = new JPanel();
		panel.setLayout(null);
		userId2 = name6;


		title = new JLabel("BAZAR HISTORY");
		title.setBounds(350, 80, 350, 30);
		panel.add(title);


		backBtn = new JButton("Back");
		backBtn.setBounds(50, 50, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);


		signOutBtn = new JButton("Sign");
		signOutBtn.setBounds(670, 50, 80, 30);
		signOutBtn.addActionListener(this);
		panel.add(signOutBtn);



		textArea = new JTextArea("");
        scrollpane = new JScrollPane(textArea);
        scrollpane.setBounds(250, 120, 300, 230);
        panel.add(scrollpane);


        totalBazarLabel = new JLabel("Total     Bazar: ");
		totalBazarLabel.setBounds(250, 365, 150, 30);
		panel.add(totalBazarLabel);


		totalBazarFLabel = new JLabel("Total Bazar: ");
		totalBazarFLabel.setBounds(370, 365, 120, 30);
		panel.add(totalBazarFLabel);
		
		
		loadBazar();
		
		this.add(panel);
	}



	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
  
		if(text.equals(backBtn.getText()))
		{
			MemberHome mh6 = new MemberHome(userId2);
			mh6.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(signOutBtn.getText()))
		{
			LogIn lg11 = new LogIn();
			lg11.setVisible(true);
			this.setVisible(false);
		}
		else{} 
	}

  

  	


	public void loadBazar()
	{ 
		String query = "SELECT `Date`, `BFast`, `Lunch`, `Dinner`, `TotalMeal` FROM `Meal` where `UserId` = '"+userId2+"';";  
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
			textArea.setText("Date       BFast  Lunch   Dinner");
			allMeal = 0.0;
			while(rs.next())
			{
				String date2 = rs.getString("Date");
                String bFast = rs.getString("BFast");
                String lunch = rs.getString("Lunch");
                String dinner = rs.getString("Dinner");
                String totalMeal = rs.getString("TotalMeal");
                allMeal = Double.parseDouble(totalMeal) + allMeal;

                System.out.println(date2 + "\n");
                textArea.setText(textArea.getText() + "\n" + date2 + "     " + bFast + "        " + lunch + "        " +  dinner);
               
			}

			totalBazarFLabel.setText(allMeal.toString());

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