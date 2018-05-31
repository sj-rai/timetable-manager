import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;


public class Update_1 {
	private JTextField textField;
	private JTextField textField_1;

	
	/**
	 * @wbp.parser.entryPoint
	 */
	void funct1()
	{
		Example_Login l = new Example_Login();

	 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
     final String DB_URL = "jdbc:mysql://localhost/time_table_db";

    
     final String USER = "root" ;
     final String PASS = "";
     String[] columnNames = {"Subject", "Class", "From", "To"," Day"};
    
    
	
     
		JFrame f =new JFrame("");
		f.getContentPane().setForeground(new Color(160, 82, 45));
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setTitle("time table");
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		f.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Time Table");
		lblNewLabel.setForeground(new Color(67,67,78));
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 32));
		lblNewLabel.setBounds(308, 25, 302, 54);
		f.getContentPane().add(lblNewLabel);
		
		JLabel lblSelectStaff = new JLabel("Select Staff");
		lblSelectStaff.setForeground(new Color(67,67,78));
		lblSelectStaff.setFont(new Font("Rockwell Condensed", Font.PLAIN, 24));
		lblSelectStaff.setBounds(31, 110, 170, 38);
		f.getContentPane().add(lblSelectStaff);
		
		JComboBox staff = new JComboBox();
		staff.setForeground(new Color(67,67,78));
		staff.setBounds(226, 115, 277, 30);
		staff.setBackground(Color.LIGHT_GRAY);
		staff.setFont(new Font("Rockwell Condensed", Font.PLAIN, 22));
		staff.addItem("");
		staff.addItem("1");
		staff.addItem("2");
		staff.addItem("3");
		staff.addItem("4");
		staff.addItem("5");
		
		
		
		
		f.getContentPane().add(staff);
		
		JLabel lblSelectDay = new JLabel("Select Day");
		lblSelectDay.setForeground(new Color(67,67,78));
		lblSelectDay.setFont(new Font("Rockwell Condensed", Font.PLAIN, 24));
		lblSelectDay.setBounds(31, 159, 170, 38);
		f.getContentPane().add(lblSelectDay);
		
		
		JComboBox day = new JComboBox();
		day.setForeground(new Color(67,67,78));
		day.setBounds(226, 165, 277, 30);
		day.setBackground(Color.LIGHT_GRAY);
		day.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
		day.addItem("");
		day.addItem("monday");
		day.addItem("tuesday");
		day.addItem("wednesday");
		day.addItem("thursday");
		day.addItem("friday");
		day.addItem("saturday");
		
		
		f.getContentPane().add(day);
		
		
			DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		JTable table = new JTable();
		table.setModel(model); 
		table.setForeground(new Color(67,67,78));
	
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 

		scroll.setBounds(84, 242, 410, 200);

		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(608, 244, 200, 135);
		f.getContentPane().add(scrollPane);
		scrollPane.setVisible(false);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		textArea.setForeground(new Color(160, 82, 45));
		textArea.setBackground(new Color(255, 255, 204));
		textArea.setVisible(false);
		
		
		
		
	
		
		JButton btnNewButton = new JButton("Check current \r\ntime table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Object dayTxt = day.getSelectedItem();
				Connection conn = null;
				String staffTxt = (String)staff.getSelectedItem();
		    	try 
		    	{
		    		for(int i = model.getRowCount()-1;i>=0;i--)
					{
						model.removeRow(i);
					}
		    
		    		Class.forName("com.mysql.jdbc.Driver");
		    		
		    		System.out.println("Connecting to a selected database...");
		    		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		                    
		    		System.out.println("Connected database successfully...");
					PreparedStatement st2 = (PreparedStatement) conn.prepareStatement("SELECT `staff`.`id_s` from `staff` where `staff`.`name_s` like '"+staffTxt+"'");
			
					ResultSet rs2 = st2.executeQuery();
					int id=0;
					while(rs2.next())
					{
			
					String id2 = new String();
						id2 = rs2.getString(1);
			
					id = Integer.parseInt(id2);
			
					}
				
					PreparedStatement st3 = (PreparedStatement) conn.prepareStatement("SELECT `days`.`id` from `days` where `days`.`day` like '"+dayTxt+"'");

							ResultSet rs3 = st3.executeQuery();
							int id3=0;
							while(rs3.next())
							{

							String id4 = new String();
								id4 = rs3.getString(1);

							id3 = Integer.parseInt(id4);
							}
							PreparedStatement st = (PreparedStatement) conn.prepareStatement("SELECT `course`.`course_name` as `course`, `class`.`class_name` as `class`, `time`.`start_time` as `stime`, `time`.`end_time` as `etime` ,`days`.`day` as `dy` FROM `stafftimetable` inner join `course` on `stafftimetable`.`courseid`=`course`.`course_id` inner join `class` on `stafftimetable`.`classid`=`class`.`class_id` inner join `time` on `stafftimetable`.`timeid`=`time`.`id` inner join `days` on `stafftimetable`.`dayid`=`days`.`id` WHERE `stafftimetable`.`staffid` ="+ id+" and `stafftimetable`.`dayid` ="+ id3);

							
							 
							 
							 ResultSet rs = st.executeQuery(); 
							
								while(rs.next()){
						
									String s1 = rs.getString(1);
									String s2 = rs.getString(2);
									String s3 = rs.getString(3);
									String s4 = rs.getString(4);
									String s5 = rs.getString(5);
									//System.out.print((String)rs.getString(1));
								
									model.addRow(new Object[]{s1, s2, s3, s4,s5});
								}
			
								scroll.setVisible(true);
								f.getContentPane().add(scroll);
								f.setVisible(true);
								f.setSize(922,724);
								
					    	
					    	}
					    	catch(SQLException se) 
					    	{
					    		
					    		se.printStackTrace();
					    	}
					    	catch(Exception e) 
					    	{
					    		
					    		e.printStackTrace();
					    	}
					    	finally  
					    	{
					    		
					    		try  
					    		{
					    			if(conn!=null)
					    			conn.close(); 
					    		}
					    		catch(SQLException se)
					    		{
					    			se.printStackTrace();
					    		}
					    	}
							
						
						}
			
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(new Color(67,67,78));
		btnNewButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		btnNewButton.setBounds(608, 123, 200, 66);
		f.getContentPane().add(btnNewButton);
		

		
		JLabel lblChangeFrom = new JLabel("Change class from");
		lblChangeFrom.setForeground(new Color(67,67,78));
		lblChangeFrom.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		lblChangeFrom.setBounds(31, 529, 154, 30);
		f.getContentPane().add(lblChangeFrom);
		
		textField = new JTextField();
		textField.setForeground(new Color(67,67,78));
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		textField.setBounds(188, 529, 94, 27);
		f.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("to");
		lblNewLabel_3.setForeground(new Color(67,67,78));
		lblNewLabel_3.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(305, 529, 61, 27);
		f.getContentPane().add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setForeground(new Color(67,67,78));
		textField_1.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		textField_1.setBounds(362, 529, 94, 30);
		f.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Update");
		
		
		
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Connection conn = null;
				
				String staffTxt = (String)staff.getSelectedItem();
				String dayTxt = (String)day.getSelectedItem();

				String up1 = textField.getText();
				String up2 = textField_1.getText();
				
				
				
				try 
		    	{
		    		
		    		Class.forName("com.mysql.jdbc.Driver");
		    		
		    		System.out.println("Connecting to a selected database...");
		    		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		                    
		    		System.out.println("Connected database successfully...");
					PreparedStatement st2 = (PreparedStatement) conn.prepareStatement("SELECT `staff`.`id_s` from `staff` where `staff`.`name_s` like '"+staffTxt+"'");
			
					ResultSet rs2 = st2.executeQuery();
					int id=0;
					while(rs2.next())
					{
			
					String id2 = new String();
						id2 = rs2.getString(1);
			
					id = Integer.parseInt(id2);
			
					}
					System.out.println(id);
					
					
					
					
				
		
						
					PreparedStatement st3 = (PreparedStatement) conn.prepareStatement("SELECT `days`.`id` from `days` where `days`.`day` like '"+dayTxt+"'");

							ResultSet rs3 = st3.executeQuery();
							int id3=0;
							while(rs3.next())
							{

							String id4 = new String();
								id4 = rs3.getString(1);

							id3 = Integer.parseInt(id4);
							}
									System.out.println(id3);
								

									{
										JOptionPane.showMessageDialog(f,"Time table updated");

									PreparedStatement st4 = (PreparedStatement) conn.prepareStatement("SELECT `class`.`class_id` from `class` where `class`.`class_name` like '"+up1+"'");
									
									ResultSet rs4 = st4.executeQuery();
									int id8=0;
									while(rs4.next())
									{
							
									String id9 = new String();
										id9 = rs4.getString(1);
							
									id8 = Integer.parseInt(id9);
							
									}
									System.out.println(id8);
									
									
									PreparedStatement st5 = (PreparedStatement) conn.prepareStatement("SELECT `class`.`class_id` from `class` where `class`.`class_name` like '"+up2+"'");
									
									ResultSet rs5 = st5.executeQuery();
									int id10=0;
									while(rs5.next())
									{
							
									String id11 = new String();
										id11 = rs5.getString(1);
							
									id10 = Integer.parseInt(id11);
							
									}
									System.out.println(id10);
									
									PreparedStatement st6 = (PreparedStatement) conn.prepareStatement("SELECT `course`.`course_name` as `course`,`course`.`course_id` as `cid`, `class`.`class_name` as `class`, `time`.`start_time` as `stime`, `time`.`end_time` as `etime` ,`days`.`day` as `dy` FROM `stafftimetable` inner join `course` on `stafftimetable`.`courseid`=`course`.`course_id` inner join `class` on `stafftimetable`.`classid`=`class`.`class_id` inner join `time` on `stafftimetable`.`timeid`=`time`.`id` inner join `days` on `stafftimetable`.`dayid`=`days`.`id` WHERE `stafftimetable`.`staffid` ="+ id+" and `stafftimetable`.`dayid` ="+ id3);
									
									 ResultSet rs6 = st6.executeQuery(); 
										int id100=0;
										while(rs6.next()){
											
											String s2 = rs6.getString(2);
											
										id100 = Integer.parseInt(s2);

										}
										System.out.println(id100);

			
									
					 PreparedStatement st = (PreparedStatement) conn.prepareStatement("UPDATE `stafftimetable` SET `classid`="+ id10+" ,`courseid`="+ id100+" WHERE `classid` ="+ id8+"   and `dayid`="+ id3+" and `staffid` ="+ id);
					
				  st.executeUpdate();    
					
					scroll.setVisible(true);
					f.getContentPane().add(scroll);
					f.setVisible(true);
					f.setSize(922,724);
					
		    	
		    	}
		    	catch(SQLException se) 
		    	{
		    		
		    		se.printStackTrace();
		    	}
		    	catch(Exception e) 
		    	{
		    		
		    		e.printStackTrace();
		    	}
		    	finally  
		    	{
		    		
		    		try  
		    		{
		    			if(conn!=null)
		    			conn.close(); 
		    		}
		    		catch(SQLException se)
		    		{
		    			se.printStackTrace();
		    		}
		    	}
				
				
				
				
				
				
				
			}
		});
		
		
		
		
		
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setForeground(new Color(67,67,78));
		btnNewButton_1.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		btnNewButton_1.setBounds(335, 590, 121, 28);
		f.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Log Out");
		btnNewButton_2.setForeground(new Color(67,67,78));
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		btnNewButton_2.setBounds(687, 590, 121, 28);
		f.getContentPane().add(btnNewButton_2);
btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//f.getDefaultCloseOperation();
				f.setVisible(false);
			l.f3();	
			}
		});

		
		f.setSize(922, 724);
		f.setVisible(true);
		
		
		

    	System.out.println("Goodbye!"); 
		}
}

