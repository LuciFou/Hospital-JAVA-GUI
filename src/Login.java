
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login extends JFrame {

	private Image img_logo = new ImageIcon(Login.class.getResource("res/hospital.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	private Image img_username = new ImageIcon(Login.class.getResource("res/user.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_password = new ImageIcon(Login.class.getResource("res/lock1.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_type = new ImageIcon(Login.class.getResource("res/work.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_log_in = new ImageIcon(Login.class.getResource("res/key.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_register = new ImageIcon(Login.class.getResource("res/register.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_update = new ImageIcon(Login.class.getResource("res/update.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblLoginMsgUsername = new JLabel("");
	private JLabel lblLoginMsgPassword = new JLabel("");
	private JLabel lblLoginMsgUsertype = new JLabel("");
	private JLabel lblLoginMsg = new JLabel("");
	private JComboBox txtUsertype;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/res/hospital11.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		
		JPanel pnlUsername = new JPanel();
		pnlUsername.setBackground(new Color(95, 158, 160));
		pnlUsername.setBounds(155, 150, 250, 40);
		contentPane.add(pnlUsername);
		pnlUsername.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(95, 158, 160));
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtUsername.getText().equals("Username")) {
					txtUsername.setText("");
				}
				else {
					txtUsername.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtUsername.getText().equals("")) {
					txtUsername.setText("Username");
				}
			}
		});
		txtUsername.setBorder(null);
		txtUsername.setText("Username");
		txtUsername.setBounds(10, 11, 170, 20);
		pnlUsername.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblIconUsername = new JLabel("");
		lblIconUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUsername.setBounds(204, 0, 46, 40);
		lblIconUsername.setIcon(new ImageIcon(img_username));
		pnlUsername.add(lblIconUsername);
		
		JPanel pnlPassword = new JPanel();
		pnlPassword.setBackground(new Color(95, 158, 160));
		pnlPassword.setBounds(155, 205, 250, 40);
		contentPane.add(pnlPassword);
		pnlPassword.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(95, 158, 160));
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtPassword.getText().equals("Password")) {
					txtPassword.setEchoChar('●');
					txtPassword.setText("");
				}
				else {
					txtPassword.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("Password");
					txtPassword.setEchoChar((char)0);
				}
			}
		});
		txtPassword.setBorder(null);
		txtPassword.setEchoChar((char)0);
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPassword.setText("Password");
		txtPassword.setBounds(10, 11, 170, 20);
		pnlPassword.add(txtPassword);
		
		JLabel lblIconPassword = new JLabel("");
		lblIconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconPassword.setBounds(204, 0, 46, 40);
		lblIconPassword.setIcon(new ImageIcon(img_password));
		pnlPassword.add(lblIconPassword);
		
		JPanel pnlbtnLogin = new JPanel();
		pnlbtnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String username = txtUsername.getText();
                String password = txtPassword.getText();
                String usertype = txtUsertype.getSelectedItem().toString();
                
                
                if(username.equals("") || username.equals("Username"))
		        {
		            //JOptionPane.showMessageDialog(null, "Add a username");
					lblLoginMsg.setText("");
					lblLoginMsgPassword.setText("");
					lblLoginMsgUsertype.setText("");
		            lblLoginMsgUsername.setText("Username required!");
		        }
		        
		        else if(password.equals("") || password.equals("Password"))
		        {
		            //JOptionPane.showMessageDialog(null, "Add a password");
		        	lblLoginMsgUsername.setText("");
		        	lblLoginMsg.setText("");
					lblLoginMsgUsertype.setText("");
		            lblLoginMsgPassword.setText("Password required!");
		        }
		        else if(usertype.equals("") || usertype.equals("User Type"))
		        {
		            //JOptionPane.showMessageDialog(null, "Choose your profession");
		        	lblLoginMsgUsername.setText("");
		        	lblLoginMsgPassword.setText("");
					lblLoginMsg.setText("");
		            lblLoginMsgUsertype.setText("Profession required!");
		        }
                
		        else {
		        
                try {
					
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
					
					PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT * FROM user WHERE username=? && password=? && usertype=?");
					
					pst.setString(1, username);
					pst.setString(2, password);
					pst.setString(3, usertype);
					ResultSet rs = pst.executeQuery();
                    
                    if(rs.next()) {
                    	
                    	
                    	int userid = rs.getInt("id");
                    	Main m = new Main(userid, username, usertype);
                        m.setVisible(true);
                        /*m.pack();
                        m.setLocationRelativeTo(null);
                        m.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        m.lblUserN.setText(username);
                        m.lblType.setText(usertype);*/
                        
                        dispose();
                    	JOptionPane.showMessageDialog(pnlbtnLogin, "Login Successful");
                    }
                    
                    else {
                        //JOptionPane.showMessageDialog(pnlbtnLogin, "No such user found.", "Message", JOptionPane.ERROR_MESSAGE);
                    	lblLoginMsgUsername.setText("");
    		        	lblLoginMsgPassword.setText("");
    					lblLoginMsgUsertype.setText("");
                        lblLoginMsg.setText("No such user found!");
                        txtUsername.setText("");
                        txtPassword.setText("");
                        txtUsertype.setSelectedIndex(0);
                        txtUsername.requestFocus();
                    }
                                    
                    rs.close();
                    pst.close();
                    con.close();
                    
                } catch (Exception ex) {
                	ex.printStackTrace();
                }
		        }
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlbtnLogin.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlbtnLogin.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlbtnLogin.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlbtnLogin.setBackground(new Color(30, 60, 60));
			}
		});
		pnlbtnLogin.setBackground(new Color(47, 79, 79));
		pnlbtnLogin.setBounds(155, 320, 140, 51);
		contentPane.add(pnlbtnLogin);
		pnlbtnLogin.setLayout(null);
		
		JLabel lblLogIn = new JLabel("LOG IN");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setForeground(Color.WHITE);
		lblLogIn.setFont(new Font("Arial", Font.BOLD, 14));
		lblLogIn.setBounds(39, 11, 91, 29);
		pnlbtnLogin.add(lblLogIn);
		
		JLabel lblIconLogin = new JLabel("");
		lblIconLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogin.setBounds(0, 0, 51, 51);
		lblIconLogin.setIcon(new ImageIcon(img_log_in));
		pnlbtnLogin.add(lblIconLogin);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
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
		lblX.setBounds(565, 0, 35, 35);
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
		lblMin.setBounds(530, 0, 35, 35);
		contentPane.add(lblMin);
		
		
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setBounds(176, 25, 250, 114);
		contentPane.add(lblIconLogo);
		lblIconLogo.setIcon(new ImageIcon(img_logo));
		
		lblLoginMsg.setForeground(new Color(128, 0, 0));
		lblLoginMsg.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMsg.setBounds(165, 300, 250, 15);
		contentPane.add(lblLoginMsg);
		
		JPanel pnlUsertype = new JPanel();
		pnlUsertype.setLayout(null);
		pnlUsertype.setBackground(new Color(95, 158, 160));
		pnlUsertype.setBounds(155, 260, 250, 40);
		contentPane.add(pnlUsertype);
		
		txtUsertype = new JComboBox();
		txtUsertype.setBackground(new Color(95, 158, 160));
		txtUsertype.setModel(new DefaultComboBoxModel(new String[] {"User Type", "Doctor", "Pharmacist", "Receptionist"}));
		txtUsertype.setBorder(null);
		txtUsertype.setBounds(10, 0, 189, 40);
		pnlUsertype.add(txtUsertype);
		
		JLabel lblIconType = new JLabel("");
		lblIconType.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconType.setBounds(204, 0, 46, 40);
		lblIconType.setIcon(new ImageIcon(img_type));
		pnlUsertype.add(lblIconType);
		
		JPanel pnlRegister = new JPanel();
		pnlRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				User u = new User();
				u.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlRegister.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlRegister.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlRegister.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlRegister.setBackground(new Color(30, 60, 60));
			}
		});
		pnlRegister.setLayout(null);
		pnlRegister.setBackground(new Color(47, 79, 79));
		pnlRegister.setBounds(305, 320, 140, 51);
		contentPane.add(pnlRegister);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setFont(new Font("Arial", Font.BOLD, 14));
		lblRegister.setBounds(39, 11, 91, 29);
		pnlRegister.add(lblRegister);
		
		JLabel lblIconRegister = new JLabel("");
		lblIconRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconRegister.setBounds(0, 0, 51, 51);
		lblIconRegister.setIcon(new ImageIcon(img_register));
		pnlRegister.add(lblIconRegister);
		
		JPanel pnlRetryUsername = new JPanel();
		pnlRetryUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtUsername.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlRetryUsername.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlRetryUsername.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlRetryUsername.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlRetryUsername.setBackground(new Color(30, 60, 60));
			}
		});
		pnlRetryUsername.setLayout(null);
		pnlRetryUsername.setBackground(new Color(47, 79, 79));
		pnlRetryUsername.setBounds(405, 150, 40, 40);
		contentPane.add(pnlRetryUsername);
		
		JLabel lblIconRetryU = new JLabel("");
		lblIconRetryU.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconRetryU.setBounds(0, 0, 40, 40);
		lblIconRetryU.setIcon(new ImageIcon(img_update));
		pnlRetryUsername.add(lblIconRetryU);
		
		JPanel pnlRetryPassword = new JPanel();
		pnlRetryPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtPassword.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlRetryPassword.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlRetryPassword.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlRetryPassword.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlRetryPassword.setBackground(new Color(30, 60, 60));
			}
		});
		pnlRetryPassword.setLayout(null);
		pnlRetryPassword.setBackground(new Color(47, 79, 79));
		pnlRetryPassword.setBounds(405, 205, 40, 40);
		contentPane.add(pnlRetryPassword);
		
		JLabel lblIconRetryP = new JLabel("");
		lblIconRetryP.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconRetryP.setBounds(0, 0, 40, 40);
		lblIconRetryP.setIcon(new ImageIcon(img_update));
		pnlRetryPassword.add(lblIconRetryP);
		
		JPanel pnlRetryType = new JPanel();
		pnlRetryType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtUsertype.setSelectedIndex(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlRetryType.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlRetryType.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlRetryType.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlRetryType.setBackground(new Color(30, 60, 60));
			}
		});
		pnlRetryType.setLayout(null);
		pnlRetryType.setBackground(new Color(47, 79, 79));
		pnlRetryType.setBounds(405, 260, 40, 40);
		contentPane.add(pnlRetryType);
		
		JLabel lblIconRetryT = new JLabel("");
		lblIconRetryT.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconRetryT.setBounds(0, 0, 40, 40);
		lblIconRetryT.setIcon(new ImageIcon(img_update));
		pnlRetryType.add(lblIconRetryT);
		
		
		
		lblLoginMsgUsername.setForeground(new Color(128, 0, 0));
		lblLoginMsgUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMsgUsername.setBounds(165, 190, 220, 15);
		contentPane.add(lblLoginMsgUsername);
		
		
		lblLoginMsgPassword.setForeground(new Color(128, 0, 0));
		lblLoginMsgPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMsgPassword.setBounds(165, 245, 220, 15);
		contentPane.add(lblLoginMsgPassword);
		
		
		lblLoginMsgUsertype.setForeground(new Color(128, 0, 0));
		lblLoginMsgUsertype.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMsgUsertype.setBounds(165, 300, 220, 15);
		contentPane.add(lblLoginMsgUsertype);
		
		
	}
}
