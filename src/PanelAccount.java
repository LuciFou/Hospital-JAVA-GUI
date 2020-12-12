import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;

public class PanelAccount extends JPanel {
	
	private Image img_save = new ImageIcon(PanelAccount.class.getResource("res/save.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_reset = new ImageIcon(PanelAccount.class.getResource("res/reset.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_delete = new ImageIcon(PanelAccount.class.getResource("res/delete.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	
	private JPasswordField txtNew;
	private JPasswordField txtConfirm;
	
	private JPanel pnlSave;
	private JPanel pnlReset;
	private JLabel lblReset;
	private JLabel lblIconReset;
	private JPanel pnlDelete;

	/**
	 * Create the panel.
	 */
	public PanelAccount() {
		setBackground(new Color(143, 188, 143));
		setBounds(10, 113, 660, 300);
		setLayout(null);
		setVisible(true);
		
		JLabel lblNew = new JLabel("New Password");
		lblNew.setForeground(new Color(47, 79, 79));
		lblNew.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNew.setBounds(38, 103, 129, 29);
		add(lblNew);
		
		txtNew = new JPasswordField();
		txtNew.setColumns(10);
		txtNew.setBackground(new Color(224, 255, 255));
		txtNew.setBounds(177, 103, 198, 29);
		add(txtNew);
		
		JLabel lblConfirm = new JLabel("Confirm Password");
		lblConfirm.setForeground(new Color(47, 79, 79));
		lblConfirm.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirm.setBounds(38, 148, 129, 29);
		add(lblConfirm);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setColumns(10);
		txtConfirm.setBackground(new Color(224, 255, 255));
		txtConfirm.setBounds(177, 148, 198, 29);
		add(txtConfirm);
		
		pnlSave = new JPanel();
		pnlSave.setBounds(270, 220, 105, 55);
		add(pnlSave);
		pnlSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String newp = txtNew.getText();
				String confirmp = txtConfirm.getText();
				
				
				if(newp == confirmp) {
					try {
						
						//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
						
						pst = con.prepareStatement("update user set password = ? where id = ?");
						
						pst.setString(1, newp);
						
						
						pst.executeUpdate();
						
	                        
	                    	JOptionPane.showMessageDialog(pnlSave, "Item Updated");
	                    	
	                        
	                        txtNew.setText("");
	                        txtConfirm.setText("");
	                        txtNew.requestFocus();
	                        
	                    
					} catch (SQLException ex) {
						System.out.println(ex);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(pnlSave, "Not compatible!!");
				}
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlSave.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlSave.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlSave.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlSave.setBackground(new Color(30, 60, 60));
			}
		});
		pnlSave.setLayout(null);
		pnlSave.setBackground(new Color(47, 79, 79));
		
		JLabel lblSave = new JLabel("SAVE");
		lblSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblSave.setForeground(Color.WHITE);
		lblSave.setFont(new Font("Arial", Font.BOLD, 14));
		lblSave.setBounds(44, 11, 61, 33);
		pnlSave.add(lblSave);
		
		JLabel lblIconSave = new JLabel("");
		lblIconSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconSave.setBounds(0, 0, 55, 55);
		pnlSave.add(lblIconSave);
		lblIconSave.setIcon(new ImageIcon(img_save));
		
		
		pnlReset = new JPanel();
		pnlReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNew.setText("");
				txtConfirm.setText("");
				txtNew.requestFocus();
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
		pnlReset.setBounds(160, 220, 100, 55);
		add(pnlReset);
		
		lblReset = new JLabel("RESET");
		lblReset.setHorizontalAlignment(SwingConstants.CENTER);
		lblReset.setForeground(Color.WHITE);
		lblReset.setFont(new Font("Arial", Font.BOLD, 14));
		lblReset.setBounds(45, 11, 55, 33);
		pnlReset.add(lblReset);
		
		lblIconReset = new JLabel("");
		lblIconReset.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconReset.setBounds(0, 0, 55, 55);
		pnlReset.add(lblIconReset);
		lblIconReset.setIcon(new ImageIcon(img_reset));
		
		
		pnlDelete = new JPanel();
		pnlDelete.setBounds(545, 220, 105, 55);
		add(pnlDelete);
		pnlDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from user where id = ?");
					
					pst.setInt(1, id);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(pnlDelete, "Account Deleted");
                    System.exit(0);
                    new Login().setVisible(true);
                    
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlDelete.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlDelete.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlDelete.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlDelete.setBackground(new Color(30, 60, 60));
			}
		});
		pnlDelete.setLayout(null);
		pnlDelete.setBackground(new Color(47, 79, 79));
		
		JLabel lblDelete = new JLabel("DELETE");
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setForeground(Color.WHITE);
		lblDelete.setFont(new Font("Arial", Font.BOLD, 14));
		lblDelete.setBounds(44, 11, 61, 33);
		pnlDelete.add(lblDelete);
		
		JLabel lblIconDelete = new JLabel("");
		lblIconDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDelete.setBounds(0, 0, 55, 55);
		pnlDelete.add(lblIconDelete);
		lblIconDelete.setIcon(new ImageIcon(img_delete));
		
		JPanel pnlLine = new JPanel();
		pnlLine.setBorder(null);
		pnlLine.setBackground(new Color(47, 79, 79));
		pnlLine.setBounds(385, 0, 4, 334);
		add(pnlLine);
		
		JLabel lblChangePassword = new JLabel("CHANGE PASSWORD");
		lblChangePassword.setForeground(new Color(255, 255, 255));
		lblChangePassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblChangePassword.setBounds(10, 50, 214, 29);
		add(lblChangePassword);
		
		JLabel lblDeleteAccount = new JLabel("DELETE ACCOUNT");
		lblDeleteAccount.setForeground(Color.WHITE);
		lblDeleteAccount.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDeleteAccount.setBounds(399, 50, 214, 29);
		add(lblDeleteAccount);
		
		JTextPane txtpnIMsg = new JTextPane();
		txtpnIMsg.setForeground(new Color(47, 79, 79));
		txtpnIMsg.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnIMsg.setBackground(new Color(143, 188, 143));
		txtpnIMsg.setText("\r\n\r\nIf you want to delete your account, click the button below...");
		txtpnIMsg.setBounds(399, 103, 241, 106);
		add(txtpnIMsg);
		
		
	}
	
	int id;
	int newid;
	
	
	public PanelAccount(int id, String username, String usertype) {
		setBackground(new Color(143, 188, 143));
		setBounds(10, 113, 660, 300);
		setLayout(null);
		setVisible(true);
		
		JLabel lblNew = new JLabel("New Password");
		lblNew.setForeground(new Color(47, 79, 79));
		lblNew.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNew.setBounds(38, 103, 129, 29);
		add(lblNew);
		
		txtNew = new JPasswordField();
		txtNew.setColumns(10);
		txtNew.setBackground(new Color(224, 255, 255));
		txtNew.setBounds(177, 103, 198, 29);
		add(txtNew);
		
		JLabel lblConfirm = new JLabel("Confirm Password");
		lblConfirm.setForeground(new Color(47, 79, 79));
		lblConfirm.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirm.setBounds(38, 148, 129, 29);
		add(lblConfirm);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setColumns(10);
		txtConfirm.setBackground(new Color(224, 255, 255));
		txtConfirm.setBounds(177, 148, 198, 29);
		add(txtConfirm);
		
		pnlSave = new JPanel();
		pnlSave.setBounds(270, 220, 105, 55);
		add(pnlSave);
		pnlSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String newp = new String(txtNew.getPassword());
				String confirmp = new String(txtConfirm.getPassword());
				
				
				
					try {
						
						//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
						
						pst = con.prepareStatement("update user set password = ? where username = ?");
						
						pst.setString(1, newp);
						pst.setString(2, username);
						
						
						if(newp.equals(confirmp)) {
						pst.executeUpdate();
						
	                        
	                    	JOptionPane.showMessageDialog(pnlSave, "Password has been successfully changed");
	                    	
	                        
	                        txtNew.setText("");
	                        txtConfirm.setText("");
	                        txtNew.requestFocus();
						}
						else {
							JOptionPane.showMessageDialog(pnlSave, "Not Compatible");
						}
	                    
					} catch (SQLException ex) {
						System.out.println(ex);
					}
				
				
					
				
				
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlSave.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlSave.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlSave.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlSave.setBackground(new Color(30, 60, 60));
			}
		});
		pnlSave.setLayout(null);
		pnlSave.setBackground(new Color(47, 79, 79));
		
		JLabel lblSave = new JLabel("SAVE");
		lblSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblSave.setForeground(Color.WHITE);
		lblSave.setFont(new Font("Arial", Font.BOLD, 14));
		lblSave.setBounds(44, 11, 61, 33);
		pnlSave.add(lblSave);
		
		JLabel lblIconSave = new JLabel("");
		lblIconSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconSave.setBounds(0, 0, 55, 55);
		pnlSave.add(lblIconSave);
		lblIconSave.setIcon(new ImageIcon(img_save));
		
		
		pnlReset = new JPanel();
		pnlReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNew.setText("");
				txtConfirm.setText("");
				txtNew.requestFocus();
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
		pnlReset.setBounds(160, 220, 100, 55);
		add(pnlReset);
		
		lblReset = new JLabel("RESET");
		lblReset.setHorizontalAlignment(SwingConstants.CENTER);
		lblReset.setForeground(Color.WHITE);
		lblReset.setFont(new Font("Arial", Font.BOLD, 14));
		lblReset.setBounds(45, 11, 55, 33);
		pnlReset.add(lblReset);
		
		lblIconReset = new JLabel("");
		lblIconReset.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconReset.setBounds(0, 0, 55, 55);
		pnlReset.add(lblIconReset);
		lblIconReset.setIcon(new ImageIcon(img_reset));
		
		
		pnlDelete = new JPanel();
		pnlDelete.setBounds(545, 220, 105, 55);
		add(pnlDelete);
		pnlDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Main m = new Main(id,username,usertype);
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from user where username = ?");
					
					pst.setString(1, m.lblUserN.getText());
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(pnlDelete, "Account Deleted");
                    setVisible(false);
                    new Login().setVisible(true);
                    
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlDelete.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlDelete.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlDelete.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlDelete.setBackground(new Color(30, 60, 60));
			}
		});
		pnlDelete.setLayout(null);
		pnlDelete.setBackground(new Color(47, 79, 79));
		
		JLabel lblDelete = new JLabel("DELETE");
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setForeground(Color.WHITE);
		lblDelete.setFont(new Font("Arial", Font.BOLD, 14));
		lblDelete.setBounds(44, 11, 61, 33);
		pnlDelete.add(lblDelete);
		
		JLabel lblIconDelete = new JLabel("");
		lblIconDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDelete.setBounds(0, 0, 55, 55);
		pnlDelete.add(lblIconDelete);
		lblIconDelete.setIcon(new ImageIcon(img_delete));
		
		JPanel pnlLine = new JPanel();
		pnlLine.setBorder(null);
		pnlLine.setBackground(new Color(47, 79, 79));
		pnlLine.setBounds(385, 0, 4, 334);
		add(pnlLine);
		
		JLabel lblChangePassword = new JLabel("CHANGE PASSWORD");
		lblChangePassword.setForeground(new Color(255, 255, 255));
		lblChangePassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblChangePassword.setBounds(10, 50, 214, 29);
		add(lblChangePassword);
		
		JLabel lblDeleteAccount = new JLabel("DELETE ACCOUNT");
		lblDeleteAccount.setForeground(Color.WHITE);
		lblDeleteAccount.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDeleteAccount.setBounds(399, 50, 214, 29);
		add(lblDeleteAccount);
		
		JTextPane txtpnIMsg = new JTextPane();
		txtpnIMsg.setForeground(new Color(47, 79, 79));
		txtpnIMsg.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnIMsg.setBackground(new Color(143, 188, 143));
		txtpnIMsg.setText("\r\n\r\nIf you want to delete your account, click the button below...");
		txtpnIMsg.setBounds(399, 103, 241, 106);
		add(txtpnIMsg);
		
		


		this.id = id;
		newid = id;
		
		Connect();
		
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
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
}
