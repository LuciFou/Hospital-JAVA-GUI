
import java.awt.*;

import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;


public class Settings extends JFrame {
	
	
	private Image img_cancel = new ImageIcon(Settings.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSettings;
	private JPanel pnlCancel;
	
	
	private PanelAbout panelAbout;
	private PanelAccount panelAccount;
	//private PanelTheme panelTheme;
	
	private String MenuItem;

	private JPanel pnlAbout;

	private JPanel pnlAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
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
	public Settings() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Settings.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 490);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panelAbout = new PanelAbout();
		panelAccount = new PanelAccount();
		//panelTheme = new PanelTheme();
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(47, 79, 79));
		pnlMenu.setBounds(10, 79, 660, 35);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);
		
		lblSettings = new JLabel("SETTINGS");
		lblSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(new Color(47, 79, 79));
		lblSettings.setFont(new Font("Dialog", Font.BOLD, 38));
		lblSettings.setBounds(10, 11, 650, 57);
		contentPane.add(lblSettings);
		
		
		pnlAbout = new JPanel();
		pnlAbout.addMouseListener(new PanelButtonMouseAdapter(pnlAbout) {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(new Color(60, 179, 113));
				menuClicked(panelAbout);
				MenuItem = "General";
			}
		});
		pnlAbout.setBackground(new Color(60, 179, 113));
		pnlAbout.setBounds(0, 0, 330, 35);
		pnlMenu.add(pnlAbout);
		pnlAbout.setLayout(null);
		
		JLabel lblAbout = new JLabel("ABOUT");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setForeground(Color.WHITE);
		lblAbout.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAbout.setBounds(10, 11, 310, 13);
		pnlAbout.add(lblAbout);
		
		pnlAccount = new JPanel();
		pnlAccount.addMouseListener(new PanelButtonMouseAdapter(pnlAccount) {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(new Color(60, 179, 113));
				menuClicked(panelAccount);
				MenuItem = "Account";
			}
		});
		pnlAccount.setBackground(new Color(47, 79, 79));
		pnlAccount.setBounds(330, 0, 330, 35);
		pnlMenu.add(pnlAccount);
		pnlAccount.setLayout(null);
		
		JLabel lblAccount = new JLabel("ACCOUNT");
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccount.setForeground(Color.WHITE);
		lblAccount.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAccount.setBounds(10, 11, 310, 13);
		pnlAccount.add(lblAccount);
		
		/*
		btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 437, 89, 31);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(109, 437, 89, 31);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(208, 437, 89, 31);
		contentPane.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(307, 437, 89, 31);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String inum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from item where itemid = ?");
					
					pst.setString(1, inum);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(btnDelete, "Item Deleted");
                    AutoID();
                    txtItemName.setText("");
                    txtDes.setText("");
                    txtSellprice.setText("");
                    txtBuyprice.setText("");
                    txtQty.setText("");
                    txtItemName.requestFocus();
                    itemTable();
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String iname = txtItemName.getText();
				String ides = txtDes.getText();
				String sprice = txtSellprice.getText();
				String bprice = txtBuyprice.getText();
				String qty = txtQty.getText();
				String inum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("update item set itemname = ? , description = ? , sellprice = ? , buyprice = ? , qty = ? where itemid = ?");
					
					pst.setString(1, iname);
					pst.setString(2, ides);
					pst.setString(3, sprice);
					pst.setString(4, bprice);
					pst.setString(5, qty);
					pst.setString(6, inum);
					
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnAdd, "Item Updated");
                    	AutoID();
                        txtItemName.setText("");
                        txtDes.setText("");
                        txtSellprice.setText("");
                        txtBuyprice.setText("");
                        txtQty.setText("");
                        txtItemName.requestFocus();
                        itemTable();
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inum = lblNum.getText();
				String iname = txtItemName.getText();
				String ides = txtDes.getText();
				String sprice = txtSellprice.getText();
				String bprice = txtBuyprice.getText();
				String qty = txtQty.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into item(itemid,itemname,description,sellprice,buyprice,qty)values(?,?,?,?,?,?)");
					pst.setString(1, inum);
					pst.setString(2, iname);
					pst.setString(3, ides);
					pst.setString(4, sprice);
					pst.setString(5, bprice);
					pst.setString(6, qty);
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnAdd, "Item Inserted");
                    	AutoID();
                        txtItemName.setText("");
                        txtDes.setText("");
                        txtSellprice.setText("");
                        txtBuyprice.setText("");
                        txtQty.setText("");
                        txtItemName.requestFocus();
                        itemTable();
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		*/
		
		
		
		/*
		pnlDelete = new JPanel();
		pnlDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String inum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from item where itemid = ?");
					
					pst.setString(1, inum);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(btnDelete, "Item Deleted");
                    AutoID();
                    txtItemName.setText("");
                    txtDes.setText("");
                    txtOldPassword.setText("");
                    txtNewPassword.setText("");
                    txtConfirmPassword.setText("");
                    txtItemName.requestFocus();
                    
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
		pnlDelete.setBounds(240, 424, 105, 55);
		contentPane.add(pnlDelete);
		
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
		*/
		
		
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
		pnlCancel.setBounds(565, 424, 105, 55);
		contentPane.add(pnlCancel);
		
		JLabel lblCancel = new JLabel("CANCEL");
		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancel.setForeground(Color.WHITE);
		lblCancel.setFont(new Font("Arial", Font.BOLD, 14));
		lblCancel.setBounds(43, 11, 62, 33);
		pnlCancel.add(lblCancel);
		
		JLabel lblIconCancel = new JLabel("");
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
				lblX.setForeground((new Color(47, 79, 79)));
			}
		});
		lblX.setForeground(new Color(47, 79, 79));
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(645, 0, 35, 35);
		contentPane.add(lblX);
		
		
		
		contentPane.add(panelAbout);
		contentPane.add(panelAccount);
		//contentPane.add(panelTheme);
		
		
		
		menuClicked(panelAbout);
		
		
	}
	
	int id;
	int newid;
	
	
	public Settings(int id, String username, String usertype) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Settings.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 490);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panelAbout = new PanelAbout();
		panelAccount = new PanelAccount(id, username, usertype);
		//panelTheme = new PanelTheme();
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(47, 79, 79));
		pnlMenu.setBounds(10, 79, 660, 35);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);
		
		lblSettings = new JLabel("SETTINGS");
		lblSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(new Color(47, 79, 79));
		lblSettings.setFont(new Font("Dialog", Font.BOLD, 38));
		lblSettings.setBounds(10, 11, 650, 57);
		contentPane.add(lblSettings);
		
		
		pnlAbout = new JPanel();
		pnlAbout.addMouseListener(new PanelButtonMouseAdapter(pnlAbout) {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(new Color(60, 179, 113));
				menuClicked(panelAbout);
				MenuItem = "General";
			}
		});
		pnlAbout.setBackground(new Color(60, 179, 113));
		pnlAbout.setBounds(0, 0, 330, 35);
		pnlMenu.add(pnlAbout);
		pnlAbout.setLayout(null);
		
		JLabel lblAbout = new JLabel("ABOUT");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setForeground(Color.WHITE);
		lblAbout.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAbout.setBounds(10, 11, 310, 13);
		pnlAbout.add(lblAbout);
		
		pnlAccount = new JPanel();
		pnlAccount.addMouseListener(new PanelButtonMouseAdapter(pnlAccount) {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(new Color(60, 179, 113));
				menuClicked(panelAccount);
				MenuItem = "Account";
			}
		});
		pnlAccount.setBackground(new Color(47, 79, 79));
		pnlAccount.setBounds(330, 0, 330, 35);
		pnlMenu.add(pnlAccount);
		pnlAccount.setLayout(null);
		
		JLabel lblAccount = new JLabel("ACCOUNT");
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccount.setForeground(Color.WHITE);
		lblAccount.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAccount.setBounds(10, 11, 310, 13);
		pnlAccount.add(lblAccount);
		
		
		/*
		pnlTheme = new JPanel();
		pnlTheme.addMouseListener(new PanelButtonMouseAdapter(pnlTheme) {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(new Color(60, 179, 113));
				menuClicked(panelTheme);
				MenuItem = "Theme";
			}
		});
		pnlTheme.setBackground(new Color(47, 79, 79));
		pnlTheme.setBounds(440, 0, 220, 35);
		pnlMenu.add(pnlTheme);
		pnlTheme.setLayout(null);
		
		JLabel lblTheme = new JLabel("THEMES");
		lblTheme.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheme.setForeground(Color.WHITE);
		lblTheme.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTheme.setBounds(10, 11, 200, 13);
		pnlTheme.add(lblTheme);
		*/
		
		/*
		btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 437, 89, 31);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(109, 437, 89, 31);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(208, 437, 89, 31);
		contentPane.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(307, 437, 89, 31);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String inum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from item where itemid = ?");
					
					pst.setString(1, inum);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(btnDelete, "Item Deleted");
                    AutoID();
                    txtItemName.setText("");
                    txtDes.setText("");
                    txtSellprice.setText("");
                    txtBuyprice.setText("");
                    txtQty.setText("");
                    txtItemName.requestFocus();
                    itemTable();
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String iname = txtItemName.getText();
				String ides = txtDes.getText();
				String sprice = txtSellprice.getText();
				String bprice = txtBuyprice.getText();
				String qty = txtQty.getText();
				String inum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("update item set itemname = ? , description = ? , sellprice = ? , buyprice = ? , qty = ? where itemid = ?");
					
					pst.setString(1, iname);
					pst.setString(2, ides);
					pst.setString(3, sprice);
					pst.setString(4, bprice);
					pst.setString(5, qty);
					pst.setString(6, inum);
					
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnAdd, "Item Updated");
                    	AutoID();
                        txtItemName.setText("");
                        txtDes.setText("");
                        txtSellprice.setText("");
                        txtBuyprice.setText("");
                        txtQty.setText("");
                        txtItemName.requestFocus();
                        itemTable();
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inum = lblNum.getText();
				String iname = txtItemName.getText();
				String ides = txtDes.getText();
				String sprice = txtSellprice.getText();
				String bprice = txtBuyprice.getText();
				String qty = txtQty.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into item(itemid,itemname,description,sellprice,buyprice,qty)values(?,?,?,?,?,?)");
					pst.setString(1, inum);
					pst.setString(2, iname);
					pst.setString(3, ides);
					pst.setString(4, sprice);
					pst.setString(5, bprice);
					pst.setString(6, qty);
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnAdd, "Item Inserted");
                    	AutoID();
                        txtItemName.setText("");
                        txtDes.setText("");
                        txtSellprice.setText("");
                        txtBuyprice.setText("");
                        txtQty.setText("");
                        txtItemName.requestFocus();
                        itemTable();
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		*/
		
		
		
		/*
		pnlDelete = new JPanel();
		pnlDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String inum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from item where itemid = ?");
					
					pst.setString(1, inum);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(btnDelete, "Item Deleted");
                    AutoID();
                    txtItemName.setText("");
                    txtDes.setText("");
                    txtOldPassword.setText("");
                    txtNewPassword.setText("");
                    txtConfirmPassword.setText("");
                    txtItemName.requestFocus();
                    
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
		pnlDelete.setBounds(240, 424, 105, 55);
		contentPane.add(pnlDelete);
		
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
		*/
		
		
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
		pnlCancel.setBounds(565, 424, 105, 55);
		contentPane.add(pnlCancel);
		
		JLabel lblCancel = new JLabel("CANCEL");
		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancel.setForeground(Color.WHITE);
		lblCancel.setFont(new Font("Arial", Font.BOLD, 14));
		lblCancel.setBounds(43, 11, 62, 33);
		pnlCancel.add(lblCancel);
		
		JLabel lblIconCancel = new JLabel("");
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
				lblX.setForeground((new Color(47, 79, 79)));
			}
		});
		lblX.setForeground(new Color(47, 79, 79));
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(645, 0, 35, 35);
		contentPane.add(lblX);
		
		
		
		contentPane.add(panelAbout);
		contentPane.add(panelAccount);
		//contentPane.add(panelTheme);
		
		
		
		menuClicked(panelAbout);
		
		
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
	
	
	public void menuClicked(JPanel panel) {
		panelAbout.setVisible(false);
		panelAccount.setVisible(false);
		//panelTheme.setVisible(false);
		panel.setVisible(true);
		
	}
	
	private class PanelButtonMouseAdapter extends MouseAdapter {
		
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
		}
		public void mouseExited(MouseEvent e) {
			if(MenuItem == "General") {
				pnlAbout.setBackground(new Color(60, 179, 113));
				pnlAccount.setBackground(new Color(47, 79, 79));
				//pnlTheme.setBackground(new Color(47, 79, 79));
			}
			else if(MenuItem == "Account") {
				pnlAccount.setBackground(new Color(60, 179, 113));
				pnlAbout.setBackground(new Color(47, 79, 79));
				//pnlTheme.setBackground(new Color(47, 79, 79));
			}
			/*
			else if(MenuItem == "Theme") {
				pnlTheme.setBackground(new Color(60, 179, 113));
				pnlAbout.setBackground(new Color(47, 79, 79));
				pnlAccount.setBackground(new Color(47, 79, 79));
			}
			*/
		}
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(60, 179, 113));
		}
		public void mouseReleased(MouseEvent e) {
			if(MenuItem == "General") {
				pnlAbout.setBackground(new Color(60, 179, 113));
				pnlAccount.setBackground(new Color(47, 79, 79));
				//pnlTheme.setBackground(new Color(47, 79, 79));
			}
			else if(MenuItem == "Account") {
				pnlAccount.setBackground(new Color(60, 179, 113));
				pnlAbout.setBackground(new Color(47, 79, 79));
				//pnlTheme.setBackground(new Color(47, 79, 79));
			}
			/*
			else if(MenuItem == "Theme") {
				pnlTheme.setBackground(new Color(60, 179, 113));
				pnlAbout.setBackground(new Color(47, 79, 79));
				pnlAccount.setBackground(new Color(47, 79, 79));
			}
			*/
		}
		
	}
	
}
