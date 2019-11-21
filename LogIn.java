

import java.lang.*;
import javax.swing.*; 
import java.awt.event.*;
import java.sql.*;

public class LogIn extends JFrame implements ActionListener
{
	JLabel title, userLabel, passLabel;
	JTextField userTF;
	JPasswordField passPF;
	JButton loginBtn, exitBtn;
	JPanel panel;
	
	public LogIn()
	{
		super("Sample Management System - Login Window");
		
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		title = new JLabel("Meal management System");
		title.setBounds(300, 50, 350, 30);
		panel.add(title);
		
		userLabel = new JLabel("User ID: ");
		userLabel.setBounds(300, 100, 110, 30);
		panel.add(userLabel);
		
		userTF = new JTextField();
		userTF.setBounds(380, 100, 100, 30);
		panel.add(userTF);
		
		passLabel = new JLabel("Password: ");
		passLabel.setBounds(300, 150, 110, 30);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(380, 150, 100, 30);
		panel.add(passPF);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(300, 200, 85, 30);
		loginBtn.addActionListener(this);
		panel.add(loginBtn);
		
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(395, 200, 85, 30);
		exitBtn.addActionListener(this);
		panel.add(exitBtn);
		
		this.add(panel);
	}



	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(loginBtn.getText()))
		{
			checkLogin();
		}
		else if(text.equals(exitBtn.getText()))
		{
			System.exit(0);
		}
		else{}
	}


	
	public void checkLogin()
	{
		String query = "SELECT `userId`, `password`, `status` FROM `login`;";     
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
			
			boolean flag = false;			
			while(rs.next())
			{
                String userId = rs.getString("userId");
                String password = rs.getString("password");
				int status = rs.getInt("status");
				
				if(userId.equals(userTF.getText()) && password.equals(passPF.getText()))
				{
					flag=true;
					if(status==0)
					{
						MemberHome mh = new MemberHome(userId);
						mh.setVisible(true);
						this.setVisible(false);
					}
					else if(status==1)
					{
						ManagerHome ch = new ManagerHome();
						ch.setVisible(true);
						this.setVisible(false);
					}
					else{}
				}
			}
			if(!flag)
			{
				JOptionPane.showMessageDialog(this,"Invalid ID or Password"); 
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