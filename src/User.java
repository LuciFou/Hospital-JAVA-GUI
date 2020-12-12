

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.logging.*;
import java.awt.event.*;


public class User extends JFrame {
	
	
	private Image img_logo = new ImageIcon(User.class.getResource("res/adduser.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	private Image img_add = new ImageIcon(User.class.getResource("res/plus.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_reset = new ImageIcon(User.class.getResource("res/reset.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_cancel = new ImageIcon(User.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JComboBox txtUserType;
	private JButton btnAdd;
	private JButton btnCancel;
	private JLabel lblName;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblUserType;
	private JLabel lblLoginMsgName = new JLabel("");
	private JLabel lblLoginMsgUsername = new JLabel("");
	private JLabel lblLoginMsgPassword = new JLabel("");
	private JLabel lblLoginMsgUsertype = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public User() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(User.class.getResource("/res/hospital.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblName = new JLabel("Name");
		lblName.setBounds(190, 150, 90, 35);
		contentPane.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(190, 200, 90, 35);
		contentPane.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(190, 250, 90, 35);
		contentPane.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblUserType = new JLabel("User Type");
		lblUserType.setBounds(190, 300, 90, 35);
		contentPane.add(lblUserType);
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserType.setForeground(new Color(255, 255, 255));
		lblUserType.setHorizontalAlignment(SwingConstants.LEFT);
		
		/*
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(190, 365, 155, 55);
		contentPane.add(btnAdd);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a=JOptionPane.showConfirmDialog(btnAdd,"Are you sure?");
				if(a==JOptionPane.YES_OPTION) {
					String name = txtName.getText();
					String username = txtUsername.getText();
					String password = txtPassword.getText();
					String usertype = txtUserType.getSelectedItem().toString();
					//String mobileNumber = mob.getText();
					//int len = mobileNumber.length();
					
					
					try {
						Connect();
						pst = con.prepareStatement("insert into user(name,username,password,usertype)values(?,?,?,?)");
						pst.setString(1, name);
						pst.setString(2, username);
						pst.setString(3, password);
						pst.setString(4, usertype);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(btnAdd, "User Added");
						txtName.setText("");
						txtUsername.setText("");
						txtPassword.setText("");
						txtUserType.setSelectedIndex(-1);
						txtName.requestFocus();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					/*try {
						Class.forName("com.mysql.jdbc.Driver");
						
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

						String query = "INSERT INTO user values('" + name + "','" + username + "','" + password + "','" + usertype + "')";

						Statement sta = connection.createStatement();
						int x = sta.executeUpdate(query);
						if (x == 0) {
							JOptionPane.showMessageDialog(btnAdd, "This username already exists");
						} else {
							JOptionPane.showMessageDialog(btnAdd, "User Added");
							txtName.setText("");
							txtUsername.setText("");
							txtPassword.setText("");
							txtUserType.setSelectedIndex(-1);
							txtName.requestFocus();
						}
						connection.close();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(355, 365, 155, 55);
		contentPane.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.exit(0);
				setVisible(false);
			}
		});
		*/
		
		
		txtName = new JTextField();
		txtName.setBackground(new Color(95, 158, 160));
		txtName.setBounds(280, 150, 230, 35);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(95, 158, 160));
		txtUsername.setBounds(280, 200, 230, 35);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(95, 158, 160));
		txtPassword.setBounds(280, 250, 230, 35);
		contentPane.add(txtPassword);
		
		txtUserType = new JComboBox();
		txtUserType.setBackground(new Color(95, 158, 160));
		txtUserType.setBounds(280, 300, 230, 35);
		contentPane.add(txtUserType);
		txtUserType.setModel(new DefaultComboBoxModel(new String[] {"", "Doctor", "Pharmacist", "Receptionist"}));
		
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setBounds(190, 11, 320, 134);
		contentPane.add(lblIconLogo);
		lblIconLogo.setIcon(new ImageIcon(img_logo));
		
		
		lblLoginMsgName.setForeground(new Color(128, 0, 0));
		lblLoginMsgName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMsgName.setBounds(290, 185, 220, 15);
		contentPane.add(lblLoginMsgName);
		
		
		JPanel pnlAdd = new JPanel();
		pnlAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
					String name = txtName.getText();
					String username = txtUsername.getText();
					String password = txtPassword.getText();
					String usertype = txtUserType.getSelectedItem().toString();
					
					
					if(name.equals(""))
			        {
			            //JOptionPane.showMessageDialog(null, "Enter your name");
						lblLoginMsgUsername.setText("");
						lblLoginMsgPassword.setText("");
						lblLoginMsgUsertype.setText("");
			            lblLoginMsgName.setText("Enter your name!");
			        }
					
					
					else if(username.equals(""))
			        {
			            //JOptionPane.showMessageDialog(null, "Add a username");
						lblLoginMsgName.setText("");
						lblLoginMsgPassword.setText("");
						lblLoginMsgUsertype.setText("");
			            lblLoginMsgUsername.setText("Add a username!");
			        }
			        
			        else if(password.equals(""))
			        {
			            //JOptionPane.showMessageDialog(null, "Add a password");
			        	lblLoginMsgUsername.setText("");
			        	lblLoginMsgName.setText("");
						lblLoginMsgUsertype.setText("");
			            lblLoginMsgPassword.setText("Add a password!");
			        }
			        else if(usertype.equals(""))
			        {
			            //JOptionPane.showMessageDialog(null, "Choose your profession");
			        	lblLoginMsgUsername.setText("");
			        	lblLoginMsgPassword.setText("");
						lblLoginMsgName.setText("");
			            lblLoginMsgUsertype.setText("Choose your profession!");
			        }
					
					
					//if(name == "" | username == "" | password == "" | usertype == "") {
	                	//JOptionPane.showMessageDialog(pnlAdd, "Please input all requirements!!");
	                	//lblLoginMsg.setText("Please input all requirements!!");
	                //}
					/*
			        else if(checkUsername(username))
			        {
			            JOptionPane.showMessageDialog(null, "This Username Already Exists");
			        }
					*/
					else if(JOptionPane.showConfirmDialog(btnAdd,"Are you sure?") == JOptionPane.YES_OPTION) {
					
					try {
						Connect();
						pst = con.prepareStatement("insert into user(name,username,password,usertype)values(?,?,?,?)");
						pst.setString(1, name);
						pst.setString(2, username);
						pst.setString(3, password);
						pst.setString(4, usertype);
						pst.executeUpdate();
						
						
						
						JOptionPane.showMessageDialog(btnAdd, "User Added");
						txtName.setText("");
						txtUsername.setText("");
						txtPassword.setText("");
						txtUserType.setSelectedIndex(0);
						txtName.requestFocus();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*
					try {
						Class.forName("com.mysql.jdbc.Driver");
						
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

						String query = "INSERT INTO user values('" + name + "','" + username + "','" + password + "','" + usertype + "')";

						Statement sta = connection.createStatement();
						int x = sta.executeUpdate(query);
						if (x == 0) {
							JOptionPane.showMessageDialog(btnAdd, "This username already exists");
						} else {
							JOptionPane.showMessageDialog(btnAdd, "User Added");
							txtName.setText("");
							txtUsername.setText("");
							txtPassword.setText("");
							txtUserType.setSelectedIndex(-1);
							txtName.requestFocus();
						}
						connection.close();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					*/
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlAdd.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlAdd.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlAdd.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlAdd.setBackground(new Color(30, 60, 60));
			}
		});
		pnlAdd.setLayout(null);
		pnlAdd.setBackground(new Color(47, 79, 79));
		pnlAdd.setBounds(190, 365, 100, 55);
		contentPane.add(pnlAdd);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdd.setForeground(Color.WHITE);
		lblAdd.setFont(new Font("Arial", Font.BOLD, 14));
		lblAdd.setBounds(8, 11, 82, 33);
		pnlAdd.add(lblAdd);
		
		JLabel lblIconAdd = new JLabel("");
		lblIconAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconAdd.setBounds(0, 0, 55, 55);
		pnlAdd.add(lblIconAdd);
		lblIconAdd.setIcon(new ImageIcon(img_add));
		
		pnlReset = new JPanel();
		pnlReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtName.setText("");
				txtUsername.setText("");
				txtPassword.setText("");
				txtUserType.setSelectedIndex(0);
				txtName.requestFocus();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlReset.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlReset.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlReset.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlReset.setBackground(new Color(30, 60, 60));
			}
		});
		pnlReset.setLayout(null);
		pnlReset.setBackground(new Color(47, 79, 79));
		pnlReset.setBounds(300, 365, 100, 55);
		contentPane.add(pnlReset);
		
		lblReset = new JLabel("RESET");
		lblReset.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReset.setForeground(Color.WHITE);
		lblReset.setFont(new Font("Arial", Font.BOLD, 14));
		lblReset.setBounds(8, 11, 82, 33);
		pnlReset.add(lblReset);
		
		lblIconReset = new JLabel("");
		lblIconReset.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconReset.setBounds(0, 0, 55, 55);
		pnlReset.add(lblIconReset);
		lblIconReset.setIcon(new ImageIcon(img_reset));
		
		pnlCancel = new JPanel();
		pnlCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlCancel.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlCancel.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlCancel.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlCancel.setBackground(new Color(30, 60, 60));
			}
		});
		pnlCancel.setLayout(null);
		pnlCancel.setBackground(new Color(47, 79, 79));
		pnlCancel.setBounds(410, 365, 100, 55);
		contentPane.add(pnlCancel);
		
		lblCancel = new JLabel("CANCEL");
		lblCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCancel.setForeground(Color.WHITE);
		lblCancel.setFont(new Font("Arial", Font.BOLD, 14));
		lblCancel.setBounds(8, 11, 82, 33);
		pnlCancel.add(lblCancel);
		
		lblIconCancel = new JLabel("");
		lblIconCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconCancel.setBounds(0, 0, 55, 55);
		pnlCancel.add(lblIconCancel);
		lblIconCancel.setIcon(new ImageIcon(img_cancel));
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblX.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblX.setForeground(Color.WHITE);
			}
		});
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(665, 0, 35, 35);
		contentPane.add(lblX);
		
		
		JLabel lblMin = new JLabel("-");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				setState(JFrame.ICONIFIED);
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblMin.setForeground(Color.DARK_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblMin.setForeground(Color.WHITE);
			}
		});
		lblMin.setForeground(Color.WHITE);
		lblMin.setFont(new Font("Comic Sans MS", Font.PLAIN, 38));
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setBounds(620, 0, 35, 35);
		contentPane.add(lblMin);
		
		
		lblLoginMsgUsername.setForeground(new Color(128, 0, 0));
		lblLoginMsgUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMsgUsername.setBounds(290, 235, 220, 15);
		contentPane.add(lblLoginMsgUsername);
		
		
		lblLoginMsgPassword.setForeground(new Color(128, 0, 0));
		lblLoginMsgPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMsgPassword.setBounds(290, 285, 220, 15);
		contentPane.add(lblLoginMsgPassword);
		
		
		lblLoginMsgUsertype.setForeground(new Color(128, 0, 0));
		lblLoginMsgUsertype.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMsgUsertype.setBounds(290, 335, 220, 15);
		contentPane.add(lblLoginMsgUsertype);
		
		
		
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JPanel pnlReset;
	private JLabel lblReset;
	private JLabel lblIconReset;
	private JPanel pnlCancel;
	private JLabel lblCancel;
	private JLabel lblIconCancel;
	private JLabel label;
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/*
	public boolean checkUsername(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM hospital WHERE username =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                checkUser = true;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
         return checkUser;
    }
    */
}
