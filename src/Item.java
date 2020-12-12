
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.text.*;
import java.awt.event.*;


public class Item extends JFrame {
	
	
	private Image img_add = new ImageIcon(Item.class.getResource("res/plus.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_update = new ImageIcon(Item.class.getResource("res/update.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_delete = new ImageIcon(Item.class.getResource("res/delete.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_cancel = new ImageIcon(Item.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtItemName;
	private JTextField txtDes;
	private JTable table;
	private JLabel lblNum;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnExit;
	private JLabel lblItemId;
	private JLabel lblItemName;
	private JLabel lblDescription;
	private JLabel lblSellprice;
	private JScrollPane scrollPane;
	
	private JPanel pnlAdd;
	private JPanel pnlUpdate;
	private JPanel pnlDelete;
	private JPanel pnlCancel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item frame = new Item();
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
	public Item() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Item.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 490);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCreateItem = new JLabel("CREATE ITEM");
		lblCreateItem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCreateItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateItem.setForeground(new Color(47, 79, 79));
		lblCreateItem.setFont(new Font("Dialog", Font.BOLD, 38));
		lblCreateItem.setBounds(10, 11, 830, 57);
		contentPane.add(lblCreateItem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 79, 830, 334);
		contentPane.add(panel_1);
		panel_1.setBackground(new Color(47, 79, 79));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Create Item", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 204, 51)));
		panel_1.setLayout(null);
		
		lblItemId = new JLabel("Item id");
		lblItemId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblItemId.setForeground(Color.WHITE);
		lblItemId.setBounds(28, 45, 88, 29);
		panel_1.add(lblItemId);
		
		lblItemName = new JLabel("Item Name");
		lblItemName.setForeground(Color.WHITE);
		lblItemName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblItemName.setBounds(28, 90, 88, 29);
		panel_1.add(lblItemName);
		
		lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescription.setBounds(28, 135, 88, 29);
		panel_1.add(lblDescription);
		
		lblSellprice = new JLabel("SellPrice");
		lblSellprice.setForeground(Color.WHITE);
		lblSellprice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSellprice.setBounds(28, 180, 88, 29);
		panel_1.add(lblSellprice);
		
		lblNum = new JLabel("Num");
		lblNum.setForeground(Color.ORANGE);
		lblNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum.setBounds(167, 45, 88, 29);
		panel_1.add(lblNum);
		
		txtItemName = new JTextField();
		txtItemName.setBackground(new Color(204, 204, 102));
		txtItemName.setBounds(167, 90, 198, 29);
		panel_1.add(txtItemName);
		txtItemName.setColumns(10);
		
		txtDes = new JTextField();
		txtDes.setBackground(new Color(204, 204, 102));
		txtDes.setColumns(10);
		txtDes.setBounds(167, 135, 198, 29);
		panel_1.add(txtDes);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE, 2));
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int SelectIndex = table.getSelectedRow();
				
				lblNum.setText(d1.getValueAt(SelectIndex, 0).toString());
				txtItemName.setText(d1.getValueAt(SelectIndex, 1).toString());
				txtDes.setText(d1.getValueAt(SelectIndex, 2).toString());
				txtSellprice.setText(d1.getValueAt(SelectIndex, 3).toString());
				txtBuyprice.setText(d1.getValueAt(SelectIndex, 4).toString());
				txtQty.setText(d1.getValueAt(SelectIndex, 5).toString());
				
				btnAdd.setEnabled(false);
			}
		});
		scrollPane.setBounds(375, 11, 445, 312);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item No", "Item Name", "Description", "SellPrice", "BuyPrice", "Qty" 
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lblBuyprice = new JLabel("BuyPrice");
		lblBuyprice.setForeground(Color.WHITE);
		lblBuyprice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBuyprice.setBounds(28, 225, 88, 29);
		panel_1.add(lblBuyprice);
		
		JLabel lblQty = new JLabel("Quantity");
		lblQty.setForeground(Color.WHITE);
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQty.setBounds(28, 270, 88, 29);
		panel_1.add(lblQty);
		
		txtSellprice = new JTextField();
		txtSellprice.setBackground(new Color(204, 204, 102));
		txtSellprice.setColumns(10);
		txtSellprice.setBounds(167, 180, 198, 29);
		panel_1.add(txtSellprice);
		
		txtBuyprice = new JTextField();
		txtBuyprice.setBackground(new Color(204, 204, 102));
		txtBuyprice.setColumns(10);
		txtBuyprice.setBounds(167, 225, 198, 29);
		panel_1.add(txtBuyprice);
		
		txtQty = new JTextField();
		txtQty.setBackground(new Color(204, 204, 102));
		txtQty.setColumns(10);
		txtQty.setBounds(167, 270, 198, 29);
		panel_1.add(txtQty);
		
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
		
		
		pnlAdd = new JPanel();
		pnlAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
                    
				} catch (SQLException ex) {
					System.out.println(ex);
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
		pnlAdd.setBounds(10, 424, 105, 55);
		contentPane.add(pnlAdd);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setForeground(Color.WHITE);
		lblAdd.setFont(new Font("Arial", Font.BOLD, 14));
		lblAdd.setBounds(44, 11, 61, 33);
		pnlAdd.add(lblAdd);
		
		JLabel lblIconAdd = new JLabel("");
		lblIconAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconAdd.setBounds(0, 0, 55, 55);
		pnlAdd.add(lblIconAdd);
		lblIconAdd.setIcon(new ImageIcon(img_add));
		
		pnlUpdate = new JPanel();
		pnlUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlUpdate.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlUpdate.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlUpdate.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlUpdate.setBackground(new Color(30, 60, 60));
			}
		});
		pnlUpdate.setLayout(null);
		pnlUpdate.setBackground(new Color(47, 79, 79));
		pnlUpdate.setBounds(125, 424, 105, 55);
		contentPane.add(pnlUpdate);
		
		JLabel lblUpdate = new JLabel("UPDATE");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setForeground(Color.WHITE);
		lblUpdate.setFont(new Font("Arial", Font.BOLD, 14));
		lblUpdate.setBounds(44, 11, 61, 33);
		pnlUpdate.add(lblUpdate);
		
		JLabel lblIconUpdate = new JLabel("");
		lblIconUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUpdate.setBounds(0, 0, 55, 55);
		pnlUpdate.add(lblIconUpdate);
		lblIconUpdate.setIcon(new ImageIcon(img_update));
		
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
                    txtSellprice.setText("");
                    txtBuyprice.setText("");
                    txtQty.setText("");
                    txtItemName.requestFocus();
                    itemTable();
                    
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
		pnlCancel.setBounds(355, 424, 105, 55);
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
		lblX.setBounds(815, 0, 35, 35);
		contentPane.add(lblX);
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setMinWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setMinWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setMinWidth(50);
		
		
		Connect();
		AutoID();
		itemTable();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtSellprice;
	private JTextField txtBuyprice;
	private JTextField txtQty;
	private JLabel lblCreateItem;
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
	public void AutoID() {
		try {
			Statement s = con.createStatement();
			rs = s.executeQuery("select MAX(itemid) from item");
			rs.next();
			rs.getString("MAX(itemid)");
			
			if(rs.getString("MAX(itemid)") == null) {
				lblNum.setText("IU001");
			}
			else {
				long id = Long.parseLong(rs.getString("MAX(itemid)").substring(2, rs.getString("MAX(itemid)").length()));
				id++;
				lblNum.setText("IU" + String.format("%03d", id));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void itemTable() {
		try {
			pst = con.prepareStatement("select * from item");
			rs = pst.executeQuery();
			ResultSetMetaData Rsm = rs.getMetaData();
			int c;
			c = Rsm.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				Vector v2 = new Vector();
				for(int i = 1; i <= c; i++) {
					v2.add(rs.getString("itemid"));
					v2.add(rs.getString("itemname"));
					v2.add(rs.getString("description"));
					v2.add(rs.getString("sellprice"));
					v2.add(rs.getString("buyprice"));
					v2.add(rs.getString("qty"));
				}
				
				df.addRow(v2);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
