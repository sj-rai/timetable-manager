
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollBar;

import java.awt.Scrollbar;

import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import java.awt.Color;

import javax.swing.SpringLayout;

import java.awt.Button;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;



public class Time_t {

	
	/**
	 * @wbp.parser.entryPoint
	 */
	void funct1()
	{
	
	 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		Example_Login l = new Example_Login();

     final String DB_URL = "jdbc:mysql://localhost/time_table_db";

    
     final String USER = "root" ;
     final String PASS = "";
     String[] columnNames = {"Subject", "Class", "From", "To"," Day"};
    
    
	
     
		JFrame f =new JFrame("");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("time table");
		f.getContentPane().setBackground(new Color(67,67,78));
		f.getContentPane().setLayout(null);
		
		
		
		JComboBox staff = new JComboBox();
		staff.setForeground(Color.LIGHT_GRAY);
		staff.setBounds(104, 100, 277, 37);
		staff.setBackground(new Color(67,67,78));
		staff.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
		staff.addItem("");
		staff.addItem("1");
		staff.addItem("2");
		staff.addItem("3");
		staff.addItem("4");
		staff.addItem("5");
		
		
		f.getContentPane().add(staff);
		
		JLabel lblNewLabel = new JLabel("Select Staff");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(104, 51, 156, 37);
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 28));
		f.getContentPane().add(lblNewLabel);
		
		JLabel lblSelectDay = new JLabel("Select day");
		lblSelectDay.setForeground(Color.LIGHT_GRAY);
		lblSelectDay.setBackground(new Color(253, 245, 230));
		lblSelectDay.setBounds(487, 51, 171, 37);
		lblSelectDay.setFont(new Font("Rockwell Condensed", Font.PLAIN, 28));
		f.getContentPane().add(lblSelectDay);
		
		JComboBox day = new JComboBox();
		day.setForeground(Color.LIGHT_GRAY);
		day.setBounds(487, 100, 277, 37);
		day.setBackground(new Color(67,67,78));
		day.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
		day.addItem("");
		day.addItem("monday");
		day.addItem("tuesday");
		day.addItem("wednesday");
		day.addItem("thursday");
		day.addItem("friday");
		day.addItem("saturday");
		day.addItem("All Days");
		f.getContentPane().add(day);
		
										
		
		
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.setForeground(Color.LIGHT_GRAY);
		btnNewButton.setBackground(new Color(67,67,78));
		
		
		
		
		
		
		
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
	//	f.getContentPane().add(table);
		scroll.setBounds(104, 282, 660, 303);
		
		
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				f.setVisible(false);	
				for(int i = model.getRowCount()-1;i>=0;i--)
				{
					model.removeRow(i);
				}
			

				f.setVisible(true);
					
					
				scroll.setVisible(false);	
	
				String q1 = new String();
				String s1 ="";
				String s2 ="";
				String s3 ="";
				String s4 ="";
				String s5 ="";
				int f00 = 0;

				Object dayTxt = day.getSelectedItem();

				System.out.println(dayTxt);

				Connection conn = null;
				String staffTxt = (String)staff.getSelectedItem();
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
					
					
					
					
				
					PreparedStatement st = (PreparedStatement) conn.prepareStatement("SELECT `course`.`course_name` as `course`, `class`.`class_name` as `class`, `time`.`start_time` as `stime`, `time`.`end_time` as `etime` ,`days`.`day` as `dy` FROM `stafftimetable` inner join `course` on `stafftimetable`.`courseid`=`course`.`course_id` inner join `class` on `stafftimetable`.`classid`=`class`.`class_id` inner join `time` on `stafftimetable`.`timeid`=`time`.`id` inner join `days` on `stafftimetable`.`dayid`=`days`.`id` WHERE `stafftimetable`.`staffid` ="+ id);

							
					if(dayTxt.equals("All Days"))	
					{
					st = (PreparedStatement) conn.prepareStatement("SELECT `course`.`course_name` as `course`, `class`.`class_name` as `class`, `time`.`start_time` as `stime`, `time`.`end_time` as `etime` ,`days`.`day` as `dy` FROM `stafftimetable` inner join `course` on `stafftimetable`.`courseid`=`course`.`course_id` inner join `class` on `stafftimetable`.`classid`=`class`.`class_id` inner join `time` on `stafftimetable`.`timeid`=`time`.`id` inner join `days` on `stafftimetable`.`dayid`=`days`.`id` WHERE `stafftimetable`.`staffid` ="+ id);
		
					}
					else
					{
						
							PreparedStatement st3 = (PreparedStatement) conn.prepareStatement("SELECT `days`.`id` from `days` where `days`.`day` like '"+dayTxt+"'");

									ResultSet rs3 = st3.executeQuery();
									int id3=0;
									while(rs3.next())
									{

									String id4 = new String();
										id4 = rs3.getString(1);

									id3 = Integer.parseInt(id4);
							
									}
									
					 st = (PreparedStatement) conn.prepareStatement("SELECT `course`.`course_name` as `course`, `class`.`class_name` as `class`, `time`.`start_time` as `stime`, `time`.`end_time` as `etime` ,`days`.`day` as `dy` FROM `stafftimetable` inner join `course` on `stafftimetable`.`courseid`=`course`.`course_id` inner join `class` on `stafftimetable`.`classid`=`class`.`class_id` inner join `time` on `stafftimetable`.`timeid`=`time`.`id` inner join `days` on `stafftimetable`.`dayid`=`days`.`id` WHERE `stafftimetable`.`staffid` ="+ id+" and `stafftimetable`.`dayid` ="+ id3);
					}
					ResultSet rs = st.executeQuery();    
					while(rs.next()){

						s1 = rs.getString(1);
						s2 = rs.getString(2);
						s3 = rs.getString(3);
						s4 = rs.getString(4);
						s5 = rs.getString(5);

					
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
		btnNewButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 24));
		btnNewButton.setBounds(348, 183, 171, 37);
		f.getContentPane().add(btnNewButton);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBackground(new Color(67,67,78));
		btnLogOut.setForeground(Color.LIGHT_GRAY);
		btnLogOut.setFont(new Font("Rockwell Condensed", Font.PLAIN, 16));
		btnLogOut.setBounds(682, 614, 120, 37);
		f.getContentPane().add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			
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

