import javax.swing.JFrame;
import javax.swing.JLabel;

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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;



public class Example_Login {
	
	static public String uname = new String();
	static NewUser nu = new NewUser();
	static Time_t t = new Time_t();
	static Time_t2 t2 = new Time_t2();
	private static JPasswordField password;
	private static JTextField username;
	public static void main(String[] args){
		f3();
	}
		public static void f3()
		{
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame f =new JFrame("login");
		f.setTitle("Login");
		f.getContentPane().setForeground(new Color(0x111111));
		f.getContentPane().setBackground(new Color(0x43434e));
		
		f.setSize(638, 595);
		f.getContentPane().setLayout(null);
		
			
			JLabel lblNewLabel = new JLabel("Username");
			lblNewLabel.setForeground(Color.LIGHT_GRAY);
			lblNewLabel.setBounds(115, 168, 132, 32);
			lblNewLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 28));
			f.getContentPane().add(lblNewLabel);
		
		username = new JTextField();
		username.setForeground(Color.LIGHT_GRAY);
		username.setBackground(new Color(67, 67, 78));
		username.setBounds(277, 168, 339, 44);
		username.setFont(new Font("Rockwell Condensed", Font.PLAIN, 22));
		f.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setBounds(115, 239, 132, 31);
		lblPassword.setFont(new Font("Rockwell Condensed", Font.PLAIN, 28));
		f.getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setForeground(Color.LIGHT_GRAY);
		password.setBackground(new Color(67, 67, 78));
		password.setBounds(277, 235, 339, 44);
		password.setFont(new Font("Rockwell Condensed", Font.BOLD, 22));
		f.getContentPane().add(password);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setSelectedIcon(new ImageIcon(Example_Login.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		btnNewButton.setForeground((new Color(67,67,78)));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(417, 306, 199, 34);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uname = username.getText();
				String pwd = password.getText();
				String url = "jdbc:mysql://localhost/database001";
				String user = "root";
				String user1="";
				String pass1="";
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection)DriverManager.getConnection(url, user,"");
					
					PreparedStatement st = (PreparedStatement) con.prepareStatement("select * from `loginaccount`");
					ResultSet rs = st.executeQuery();
				
				
					int fl=0;
				while(rs.next()){
					user1=rs.getString(1);
					pass1=rs.getString(2);
						
				
				

				if(uname.equals(user1) && pwd.equals(pass1))
				{
				fl=1;
				break;

				}
				else 
					{
					fl=0;
					}
				}
				if(fl==1) 
				{
					JOptionPane.showMessageDialog(f,"succesfully logged in");
					if(uname.equals("admin"))
					{
					t2.funct1();	
					}
					else
					{
					t.funct1();
					}
				}
				else
					JOptionPane.showMessageDialog(f,"invalid username or password");
				}
				
					
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}});
		btnNewButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
		f.getContentPane().add(btnNewButton);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(Color.LIGHT_GRAY);
		lblWelcome.setFont(new Font("Rockwell Condensed", Font.PLAIN, 54));
		lblWelcome.setBounds(115, 68, 244, 50);
		f.getContentPane().add(lblWelcome);
		
		JLabel lblNewUser = new JLabel("New User ?");
		lblNewUser.setForeground(Color.LIGHT_GRAY);
		lblNewUser.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
		lblNewUser.setBounds(131, 451, 77, 22);
		f.getContentPane().add(lblNewUser);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nu.f2();
			}
		});
		btnSignUp.setForeground(new Color(67,67,78));
		btnSignUp.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		btnSignUp.setBackground(Color.LIGHT_GRAY);
		btnSignUp.setBounds(220, 449, 194, 28);
		f.getContentPane().add(btnSignUp);
		f.setVisible(true);
		}
	}

