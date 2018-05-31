

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Wrapper;
 

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.plaf.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Window.Type;


public class NewUser extends JFrame 
{
	Example_Login l = new Example_Login();
	private JPanel contentPane; 
	private JTextField txtUser;
	private JButton btnSignup;
	protected java.lang.String Spassword;
	

	static final String DB_URL = "jdbc:mysql://localhost/database001";
	
	
	static final String USER = "root";
	static final String PASS = "";
	protected static final String String = null;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	

	void f2()
	{
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try  
				{
					
					NewUser frame = new NewUser();
                                        
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
	}
 
	
	public NewUser() //create constructor
	{
		//set title
		setTitle("New User Login");
		
		setSize(638, 595);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
                
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);
		

		txtUser = new JTextField();
		txtUser.setForeground(new Color(67,67,78));
		txtUser.setBackground(Color.LIGHT_GRAY);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(67,67,78));
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 22));
		passwordField.setBounds(254, 228, 313, 47);
		contentPane.add(passwordField);
		txtUser.setFont(new Font("Rockwell Condensed", Font.PLAIN, 22));

		txtUser.setBounds(254, 162, 313, 47);

		contentPane.add(txtUser);

		txtUser.setColumns(10);
		

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(new Color(67,67,78));
		lblUserName.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));

		lblUserName.setBounds(40, 170, 202, 39);

		contentPane.add(lblUserName);
		

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(67,67,78));
		lblPassword.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));

		lblPassword.setBounds(40, 236, 203, 39);

		contentPane.add(lblPassword);
		

		btnSignup = new JButton("SignUp");
		btnSignup.setSelectedIcon(new ImageIcon(NewUser.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		btnSignup.setForeground(new Color(67,67,78));
		btnSignup.setBackground(Color.LIGHT_GRAY);
		btnSignup.setFont(new Font("Times New Roman", Font.BOLD, 15));
		//add event handler on SignUp button
		btnSignup.addActionListener(new ActionListener() 
                {
		                public void actionPerformed(ActionEvent e) 
                                {				
				
				//Create wrapper object and define it null
				Wrapper conn = null;
				try  
                                {
                                
				String username = "";
				String password = "";
				String password_1 = "";
				
				
				username = txtUser.getText().trim();
				password = passwordField.getText().trim();
				password_1 = passwordField_1.getText().trim();
                                
				if (username.equals("")|| password.equals(""))
				{

					JOptionPane.showMessageDialog(null," username or password cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
				}
				else 
					if(password.equals(password_1))//else insert query is run properly
                                {
					String IQuery = "INSERT INTO `database001`.`loginaccount`(`username`,`password`,`ts`) VALUES('"+username+"', '"+password+"',current_timestamp)";
					System.out.println(IQuery);//print on console
					System.out.println("Connecting to a selected database...");
				

				conn = DriverManager.getConnection(DB_URL, USER, PASS);
					System.out.println("Connected database successfully...");
					
				((Connection)conn).createStatement().execute(IQuery);//select the rows

					String SMessage = "Record added for "+username;
					

	                    JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
					//close connection
					((java.sql.Connection)conn).close();
				}	
					else
					{
	                    JOptionPane.showMessageDialog(null,"password incorrect","Message",JOptionPane.PLAIN_MESSAGE);
					}
			}
				catch (SQLIntegrityConstraintViolationException b)
				{
					b.printStackTrace();
					JOptionPane.showMessageDialog(null,"username already exists ","Message",JOptionPane.WARNING_MESSAGE);
				}
			catch (SQLException se) 
			{

				se.printStackTrace();

			}
			catch (Exception a) //catch block
			{
				a.printStackTrace();
			}
		    }
		});

		btnSignup.setBounds(432, 395, 135, 36);

		contentPane.add(btnSignup);
		
		JLabel lblNewLabel = new JLabel("NEW USER SIGN UP");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setForeground(new Color(67,67,78));
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 44));
		lblNewLabel.setBounds(72, 67, 348, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Re-Enter Password\r\n");
		lblNewLabel_1.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(67,67,78));
		lblNewLabel_1.setBounds(40, 298, 202, 39);
		contentPane.add(lblNewLabel_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(new Color(67,67,78));
		passwordField_1.setFont(new Font("Rockwell Condensed", Font.PLAIN, 22));
		passwordField_1.setBackground(Color.LIGHT_GRAY);
		passwordField_1.setBounds(254, 297, 313, 47);
		contentPane.add(passwordField_1);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			l.f3();
			}
		});
		btnLogIn.setForeground(new Color(67,67,78));
		btnLogIn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnLogIn.setBackground(Color.LIGHT_GRAY);
		btnLogIn.setBounds(432, 443, 135, 39);
		contentPane.add(btnLogIn);
		
	
		}
}
