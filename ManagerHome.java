
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class ManagerHome extends JFrame implements ActionListener
{
	JLabel label, totalMealLabel, totalMealFLabel, nameLabel, phoneLabel, nameFLabel, phoneFLabel;
	JButton backBtn, signOutBtn, entryMealBtn, entryBazarBtn, editMealBtn, editBazarBtn, mealHistoryBtn,
	 bazarHistoryBtn, addMemberBtn, deleteMemberBtn, financeHistoryBtn, deleteAllBtn;
	JPanel panel;
	String userId, idM, nameM, phoneM, desigM, infoM;
	
	public ManagerHome()
	{
		super("Sample Management System - Manager Home");
		
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);


		nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(50, 50, 80, 30);
		//nameLabel.addActionListener(this);
		panel.add(nameLabel);

		
		nameFLabel = new JLabel("RSR's System");
		nameFLabel.setBounds(140, 50, 200, 30);
		//nameFLabel.addActionListener(this);
		panel.add(nameFLabel);


		phoneLabel = new JLabel("Phone: ");
		phoneLabel.setBounds(540, 50, 80, 30);
		//phoneLabel.addActionListener(this);
		panel.add(phoneLabel);

		
		phoneFLabel = new JLabel("+8801793915354");
		phoneFLabel.setBounds(630, 50, 200, 30);
		//phoneFLabel.addActionListener(this);
		panel.add(phoneFLabel);


		entryMealBtn = new JButton("Entry Meal");
		entryMealBtn.setBounds(150, 100, 130, 30);
		entryMealBtn.addActionListener(this);
		panel.add(entryMealBtn);


		entryBazarBtn = new JButton("Entry Bazar");
		entryBazarBtn.setBounds(550, 100, 130, 30);
		entryBazarBtn.addActionListener(this);
		panel.add(entryBazarBtn);


		editMealBtn = new JButton("Edit Meal");
		editMealBtn.setBounds(150, 150, 130, 30);
		editMealBtn.addActionListener(this);
		panel.add(editMealBtn);


		editBazarBtn = new JButton("Edit Bazar");
		editBazarBtn.setBounds(550, 150, 130, 30);
		editBazarBtn.addActionListener(this);
		panel.add(editBazarBtn);


		mealHistoryBtn = new JButton("Meal History");
		mealHistoryBtn.setBounds(150, 200, 130, 30);
		mealHistoryBtn.addActionListener(this);
		panel.add(mealHistoryBtn);



		bazarHistoryBtn = new JButton("Bazar History");
		bazarHistoryBtn.setBounds(550, 200, 130, 30);
		bazarHistoryBtn.addActionListener(this);
		panel.add(bazarHistoryBtn);


		addMemberBtn = new JButton("Add Member");
		addMemberBtn.setBounds(150, 250, 130, 30);
		addMemberBtn.addActionListener(this);
		panel.add(addMemberBtn);


		deleteMemberBtn = new JButton("Delete member");
		deleteMemberBtn.setBounds(550, 250, 130, 30);
		deleteMemberBtn.addActionListener(this);
		panel.add(deleteMemberBtn);


		financeHistoryBtn = new JButton("FInance History");
		financeHistoryBtn.setBounds(315, 130, 200, 30);
		financeHistoryBtn.addActionListener(this);
		panel.add(financeHistoryBtn);


		deleteAllBtn = new JButton("Delete All Info");
		deleteAllBtn.setBounds(315, 200, 200, 30);
		deleteAllBtn.addActionListener(this);
		panel.add(deleteAllBtn);


		backBtn = new JButton("Back");
		backBtn.setBounds(50, 350, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);


		signOutBtn = new JButton("SignOut");
		signOutBtn.setBounds(670, 350, 80, 30);
		signOutBtn.addActionListener(this);
		panel.add(signOutBtn);

	
		this.add(panel);
	}


	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();


		if(text.equals(backBtn.getText()))
		{
			LogIn ln8 = new LogIn();
			ln8.setVisible(true);
			this.setVisible(false);	
		}

		else if(text.equals(signOutBtn.getText()))
		{
			LogIn ln9 = new LogIn();
			ln9.setVisible(true);
			this.setVisible(false);
		}
		
		else if(text.equals(entryMealBtn.getText()))
		{
			EntryMeal em9 = new EntryMeal();
			em9.setVisible(true);
			this.setVisible(false);
		}

		else if(text.equals(entryBazarBtn.getText()))
		{
			EntryBazar eb9 = new EntryBazar();
			eb9.setVisible(true);
			this.setVisible(false);
		}

		else if(text.equals(editMealBtn.getText()))
		{
			EditMeal edm9 = new EditMeal();
			edm9.setVisible(true);
			this.setVisible(false);
		}

		else if(text.equals(editBazarBtn.getText()))
		{
			EditBazar edb9 = new EditBazar();
			edb9.setVisible(true);
			this.setVisible(false);
		}

		else if(text.equals(mealHistoryBtn.getText()))
		{
			MealHistory mh9 = new MealHistory();
			mh9.setVisible(true);
			this.setVisible(false);
		}

		else if(text.equals(bazarHistoryBtn.getText()))
		{
			BalanceHistory bh9 = new BalanceHistory();
			bh9.setVisible(true);
			this.setVisible(false);
		}

		else if(text.equals(addMemberBtn.getText()))
		{
			AddMember am9 = new AddMember();
			am9.setVisible(true);
			this.setVisible(false);
		}

		else if(text.equals(deleteMemberBtn.getText()))
		{
			DeleteMember dm9 = new DeleteMember();
			dm9.setVisible(true);
			this.setVisible(false);
		}

		else if(text.equals(financeHistoryBtn.getText()))
		{
			FinanceSummary fs9 = new FinanceSummary();
			fs9.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(deleteAllBtn.getText()))
		{
			deleteAllInfo();
		}
		else{}
	}

	
	public void deleteAllInfo(){

		preserveManagerAtMember();

		String query1 = "delete FROM `member` ;";    
		String query2 = "delete FROM `login` ;"; 
		String query3 = "delete FROM `Bazar` ;";
		String query4 = "delete FROM `Meal` ;";
		System.out.println(query1);
		System.out.println(query2);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sayed", "root", "");
			Statement stm = con.createStatement();
			

			stm.executeUpdate(query1);
			System.out.println("Tanvir");
			stm.executeUpdate(query2);
			stm.executeUpdate(query3);
			stm.executeUpdate(query4);
			
			stm.close();
			con.close();

			insertIntoDB();
			JOptionPane.showMessageDialog(this, "Success !!!");
			
			
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }

	}



    public void preserveManagerAtMember()
	{	String manager2 = "Manager";

		String query = "SELECT `UserId`, `Name`, `PhoneNumber`, `Designation`, `Info` FROM `member` where `Designation` = '"+manager2+"' ;";     
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
					
			while(rs.next()){
				idM = rs.getString("UserId");
				nameM = rs.getString("Name");
				phoneM = rs.getString("PhoneNumber");
				desigM = rs.getString("Designation");
				infoM = rs.getString("Info");
				System.out.println(idM + "   "+ nameM + "    "+desigM);
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
		
		int status = 1;

		
		
		
		String query1 = "INSERT INTO member VALUES ('"+idM+"','"+nameM+"','"+ phoneM+"','"+desigM+"','"+infoM+"');";
		String query2 = "INSERT INTO login VALUES ('"+idM+"','"+idM+"',"+status+");";
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