import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class Inventory extends JFrame {
	
	private Image img_add = new ImageIcon(Inventory.class.getResource("res/plus.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_update = new ImageIcon(Inventory.class.getResource("res/update.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTextField txtDrugC;
	private JTextField txtDrugN;
	private JTable table;
	private JTextField txtTotalCost;
	private JTextField txtPay;
	private JTextField txtBalance;
	private JLabel lblNum;
	private JSpinner txtQty;
	private JPanel pnlAdd;
	private JPanel pnlSalesUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory();
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
	public Inventory() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inventory.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 580);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 204, 51)));
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(10, 75, 742, 428);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrescriptionId = new JLabel("Prescription ID");
		lblPrescriptionId.setForeground(Color.WHITE);
		lblPrescriptionId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrescriptionId.setBounds(56, 256, 105, 29);
		panel.add(lblPrescriptionId);
		
		JLabel lblDrugC = new JLabel("Drug Code");
		lblDrugC.setForeground(Color.WHITE);
		lblDrugC.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDrugC.setBounds(56, 296, 105, 29);
		panel.add(lblDrugC);
		
		JLabel lblDrugN = new JLabel("Drug Name");
		lblDrugN.setForeground(Color.WHITE);
		lblDrugN.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDrugN.setBounds(56, 336, 105, 29);
		panel.add(lblDrugN);
		
		JLabel lblQty = new JLabel("Quantity");
		lblQty.setForeground(Color.WHITE);
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQty.setBounds(56, 376, 105, 29);
		panel.add(lblQty);
		
		lblNum = new JLabel("Num");
		lblNum.setForeground(Color.ORANGE);
		lblNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum.setBounds(200, 256, 88, 29);
		panel.add(lblNum);
		
		txtDrugC = new JTextField();
		txtDrugC.setBackground(new Color(204, 204, 102));
		txtDrugC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					String dcode = txtDrugC.getText();
					
					try {
						pst = con.prepareStatement("select * from item where itemid = ?");
						pst.setString(1, dcode);
						rs = pst.executeQuery();
						
						if(rs.next() == false) {
							JOptionPane.showMessageDialog(txtDrugC, "Drug Not Found");
						}
						else {
							String dname = rs.getString("itemname");
							txtDrugN.setText(dname.trim());
							txtQty.requestFocus();
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		});
		txtDrugC.setColumns(10);
		txtDrugC.setBounds(200, 296, 169, 29);
		panel.add(txtDrugC);
		
		txtDrugN = new JTextField();
		txtDrugN.setBackground(new Color(204, 204, 102));
		txtDrugN.setColumns(10);
		txtDrugN.setBounds(200, 337, 169, 29);
		panel.add(txtDrugN);
		
		txtQty = new JSpinner();
		txtQty.setBackground(new Color(204, 204, 102));
		txtQty.setBounds(200, 376, 63, 29);
		panel.add(txtQty);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE, 2));
		scrollPane.setBounds(10, 25, 722, 215);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Prescription ID", "Drug Code", "Drug Name", "Qty", "Price", "Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lblTotalcost = new JLabel("TotalCost");
		lblTotalcost.setForeground(Color.WHITE);
		lblTotalcost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalcost.setBounds(443, 296, 105, 29);
		panel.add(lblTotalcost);
		
		JLabel lblPay = new JLabel("Pay");
		lblPay.setForeground(Color.WHITE);
		lblPay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPay.setBounds(443, 336, 105, 29);
		panel.add(lblPay);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setForeground(Color.WHITE);
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBalance.setBounds(443, 376, 105, 29);
		panel.add(lblBalance);
		
		txtTotalCost = new JTextField();
		txtTotalCost.setBackground(new Color(204, 204, 102));
		txtTotalCost.setColumns(10);
		txtTotalCost.setBounds(558, 296, 123, 29);
		panel.add(txtTotalCost);
		
		txtPay = new JTextField();
		txtPay.setBackground(new Color(204, 204, 102));
		txtPay.setColumns(10);
		txtPay.setBounds(558, 336, 123, 29);
		panel.add(txtPay);
		
		txtBalance = new JTextField();
		txtBalance.setBackground(new Color(204, 204, 102));
		txtBalance.setColumns(10);
		txtBalance.setBounds(558, 377, 123, 29);
		panel.add(txtBalance);
		
		/*
		JButton btnSalesUpdate = new JButton("Sales Update");
		btnSalesUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int pay = (Integer.parseInt(txtPay.getText()));
				int totcost = (Integer.parseInt(txtTotalCost.getText()));
				
				
				int bal = pay - totcost;
				
				txtBalance.setText(String.valueOf(bal));
				
				sales();
				
			}
		});
		btnSalesUpdate.setBounds(602, 193, 114, 40);
		panel.add(btnSalesUpdate);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dcode = txtDrugC.getText();
				try {
					pst = con.prepareStatement("select * from item where itemid = ?");
					pst.setString(1, dcode);
					rs = pst.executeQuery();
					
					while(rs.next()) {
						int currentqty;
						int sellprice;
						int qty;
						
						currentqty = rs.getInt("qty");
						sellprice = rs.getInt("sellprice");
						
						qty = Integer.parseInt(txtQty.getValue().toString());
						
						int tot = sellprice * qty;
						
						if(qty >= currentqty) {
							JOptionPane.showMessageDialog(btnAdd, "Available Item" + currentqty);
							JOptionPane.showMessageDialog(btnAdd, "Quantity not Enough");
						}
						else {
							DefaultTableModel DF = (DefaultTableModel)table.getModel();
							DF.addRow(new Object[] {
									lblNum.getText(),
									txtDrugC.getText(),
									txtDrugN.getText(),
									txtQty.getValue().toString(),
									sellprice,
									tot,
							});
							
							int sum = 0;
							
							for(int i = 0; i < table.getRowCount(); i++) {
								
								sum = sum + Integer.parseInt(table.getValueAt(i, 5).toString());
								
							}
							
							txtTotalCost.setText(Integer.toString(sum));
							txtDrugC.setText("");
							txtDrugN.setText("");
							txtQty.setValue(0);
							txtDrugC.requestFocus();
							
						}
						
						
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(249, 193, 96, 40);
		panel.add(btnAdd);
		*/
		
		JLabel lblSales = new JLabel("SALES");
		lblSales.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSales.setForeground(new Color(47, 79, 79));
		lblSales.setFont(new Font("Dialog", Font.BOLD, 38));
		lblSales.setBounds(10, 11, 742, 57);
		contentPane.add(lblSales);
		
		pnlAdd = new JPanel();
		pnlAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String dcode = txtDrugC.getText();
				try {
					pst = con.prepareStatement("select * from item where itemid = ?");
					pst.setString(1, dcode);
					rs = pst.executeQuery();
					
					while(rs.next()) {
						int currentqty;
						int sellprice;
						int qty;
						
						currentqty = rs.getInt("qty");
						sellprice = rs.getInt("sellprice");
						
						qty = Integer.parseInt(txtQty.getValue().toString());
						
						int tot = sellprice * qty;
						
						if(qty >= currentqty) {
							JOptionPane.showMessageDialog(pnlAdd, "Available Item" + currentqty);
							JOptionPane.showMessageDialog(pnlAdd, "Quantity not Enough");
						}
						else {
							DefaultTableModel DF = (DefaultTableModel)table.getModel();
							DF.addRow(new Object[] {
									lblNum.getText(),
									txtDrugC.getText(),
									txtDrugN.getText(),
									txtQty.getValue().toString(),
									sellprice,
									tot,
							});
							
							int sum = 0;
							
							for(int i = 0; i < table.getRowCount(); i++) {
								
								sum = sum + Integer.parseInt(table.getValueAt(i, 5).toString());
								
							}
							
							txtTotalCost.setText(Integer.toString(sum));
							txtDrugC.setText("");
							txtDrugN.setText("");
							txtQty.setValue(0);
							txtDrugC.requestFocus();
							
						}
						
						
					}
					
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
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
		pnlAdd.setBounds(274, 514, 105, 55);
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
		
		
		pnlSalesUpdate = new JPanel();
		pnlSalesUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pay = (Integer.parseInt(txtPay.getText()));
				int totcost = (Integer.parseInt(txtTotalCost.getText()));
				
				
				int bal = pay - totcost;
				
				txtBalance.setText(String.valueOf(bal));
				
				sales();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlSalesUpdate.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlSalesUpdate.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlSalesUpdate.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlSalesUpdate.setBackground(new Color(30, 60, 60));
			}
		});
		pnlSalesUpdate.setLayout(null);
		pnlSalesUpdate.setBackground(new Color(47, 79, 79));
		pnlSalesUpdate.setBounds(536, 514, 155, 55);
		contentPane.add(pnlSalesUpdate);
		
		JLabel lblSalesUpdate = new JLabel("SALES UPDATE");
		lblSalesUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesUpdate.setForeground(Color.WHITE);
		lblSalesUpdate.setFont(new Font("Arial", Font.BOLD, 14));
		lblSalesUpdate.setBounds(44, 11, 111, 33);
		pnlSalesUpdate.add(lblSalesUpdate);
		
		JLabel lblIconSalesUpdate = new JLabel("");
		lblIconSalesUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconSalesUpdate.setBounds(0, 0, 55, 55);
		pnlSalesUpdate.add(lblIconSalesUpdate);
		lblIconSalesUpdate.setIcon(new ImageIcon(img_update));
		
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
		lblX.setBounds(727, 0, 35, 35);
		contentPane.add(lblX);
	}
	
	
	String pnoo;
	String npno;
	
	public Inventory(String pno) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inventory.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 580);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 204, 51)));
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(10, 75, 742, 428);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrescriptionId = new JLabel("Prescription ID");
		lblPrescriptionId.setForeground(Color.WHITE);
		lblPrescriptionId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrescriptionId.setBounds(56, 256, 105, 29);
		panel.add(lblPrescriptionId);
		
		JLabel lblDrugC = new JLabel("Drug Code");
		lblDrugC.setForeground(Color.WHITE);
		lblDrugC.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDrugC.setBounds(56, 296, 105, 29);
		panel.add(lblDrugC);
		
		JLabel lblDrugN = new JLabel("Drug Name");
		lblDrugN.setForeground(Color.WHITE);
		lblDrugN.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDrugN.setBounds(56, 336, 105, 29);
		panel.add(lblDrugN);
		
		JLabel lblQty = new JLabel("Quantity");
		lblQty.setForeground(Color.WHITE);
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQty.setBounds(56, 376, 105, 29);
		panel.add(lblQty);
		
		lblNum = new JLabel("Num");
		lblNum.setForeground(Color.ORANGE);
		lblNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum.setBounds(200, 256, 88, 29);
		panel.add(lblNum);
		
		txtDrugC = new JTextField();
		txtDrugC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					String dcode = txtDrugC.getText();
					
					try {
						pst = con.prepareStatement("select * from item where itemid = ?");
						pst.setString(1, dcode);
						rs = pst.executeQuery();
						
						if(rs.next() == false) {
							JOptionPane.showMessageDialog(txtDrugC, "Drug Not Found");
						}
						else {
							String dname = rs.getString("itemname");
							txtDrugN.setText(dname.trim());
							txtQty.requestFocus();
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		});
		txtDrugC.setColumns(10);
		txtDrugC.setBounds(200, 296, 169, 29);
		panel.add(txtDrugC);
		
		txtDrugN = new JTextField();
		txtDrugN.setColumns(10);
		txtDrugN.setBounds(200, 337, 169, 29);
		panel.add(txtDrugN);
		
		txtQty = new JSpinner();
		txtQty.setBounds(200, 376, 63, 29);
		panel.add(txtQty);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 722, 215);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Prescription ID", "Drug Code", "Drug Name", "Qty", "Price", "Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lblTotalcost = new JLabel("TotalCost");
		lblTotalcost.setForeground(Color.WHITE);
		lblTotalcost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalcost.setBounds(443, 296, 105, 29);
		panel.add(lblTotalcost);
		
		JLabel lblPay = new JLabel("Pay");
		lblPay.setForeground(Color.WHITE);
		lblPay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPay.setBounds(443, 336, 105, 29);
		panel.add(lblPay);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setForeground(Color.WHITE);
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBalance.setBounds(443, 376, 105, 29);
		panel.add(lblBalance);
		
		txtTotalCost = new JTextField();
		txtTotalCost.setColumns(10);
		txtTotalCost.setBounds(558, 296, 123, 29);
		panel.add(txtTotalCost);
		
		txtPay = new JTextField();
		txtPay.setColumns(10);
		txtPay.setBounds(558, 336, 123, 29);
		panel.add(txtPay);
		
		txtBalance = new JTextField();
		txtBalance.setColumns(10);
		txtBalance.setBounds(558, 377, 123, 29);
		panel.add(txtBalance);
		
		/*
		JButton btnSalesUpdate = new JButton("Sales Update");
		btnSalesUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int pay = (Integer.parseInt(txtPay.getText()));
				int totcost = (Integer.parseInt(txtTotalCost.getText()));
				
				
				int bal = pay - totcost;
				
				txtBalance.setText(String.valueOf(bal));
				
				sales();
				
			}
		});
		btnSalesUpdate.setBounds(602, 193, 114, 40);
		panel.add(btnSalesUpdate);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dcode = txtDrugC.getText();
				try {
					pst = con.prepareStatement("select * from item where itemid = ?");
					pst.setString(1, dcode);
					rs = pst.executeQuery();
					
					while(rs.next()) {
						int currentqty;
						int sellprice;
						int qty;
						
						currentqty = rs.getInt("qty");
						sellprice = rs.getInt("sellprice");
						
						qty = Integer.parseInt(txtQty.getValue().toString());
						
						int tot = sellprice * qty;
						
						if(qty >= currentqty) {
							JOptionPane.showMessageDialog(btnAdd, "Available Item" + currentqty);
							JOptionPane.showMessageDialog(btnAdd, "Quantity not Enough");
						}
						else {
							DefaultTableModel DF = (DefaultTableModel)table.getModel();
							DF.addRow(new Object[] {
									lblNum.getText(),
									txtDrugC.getText(),
									txtDrugN.getText(),
									txtQty.getValue().toString(),
									sellprice,
									tot,
							});
							
							int sum = 0;
							
							for(int i = 0; i < table.getRowCount(); i++) {
								
								sum = sum + Integer.parseInt(table.getValueAt(i, 5).toString());
								
							}
							
							txtTotalCost.setText(Integer.toString(sum));
							txtDrugC.setText("");
							txtDrugN.setText("");
							txtQty.setValue(0);
							txtDrugC.requestFocus();
							
						}
						
						
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(249, 193, 96, 40);
		panel.add(btnAdd);
		*/
		
		JLabel lblSales = new JLabel("SALES");
		lblSales.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSales.setForeground(new Color(47, 79, 79));
		lblSales.setFont(new Font("Dialog", Font.BOLD, 38));
		lblSales.setBounds(10, 11, 742, 57);
		contentPane.add(lblSales);
		
		pnlAdd = new JPanel();
		pnlAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String dcode = txtDrugC.getText();
				try {
					pst = con.prepareStatement("select * from item where itemid = ?");
					pst.setString(1, dcode);
					rs = pst.executeQuery();
					
					while(rs.next()) {
						int currentqty;
						int sellprice;
						int qty;
						
						currentqty = rs.getInt("qty");
						sellprice = rs.getInt("sellprice");
						
						qty = Integer.parseInt(txtQty.getValue().toString());
						
						int tot = sellprice * qty;
						
						if(qty >= currentqty) {
							JOptionPane.showMessageDialog(pnlAdd, "Available Item" + currentqty);
							JOptionPane.showMessageDialog(pnlAdd, "Quantity not Enough");
						}
						else {
							DefaultTableModel DF = (DefaultTableModel)table.getModel();
							DF.addRow(new Object[] {
									lblNum.getText(),
									txtDrugC.getText(),
									txtDrugN.getText(),
									txtQty.getValue().toString(),
									sellprice,
									tot,
							});
							
							int sum = 0;
							
							for(int i = 0; i < table.getRowCount(); i++) {
								
								sum = sum + Integer.parseInt(table.getValueAt(i, 5).toString());
								
							}
							
							txtTotalCost.setText(Integer.toString(sum));
							txtDrugC.setText("");
							txtDrugN.setText("");
							txtQty.setValue(0);
							txtDrugC.requestFocus();
							
						}
						
						
					}
					
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
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
		pnlAdd.setBounds(274, 514, 105, 55);
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
		
		
		pnlSalesUpdate = new JPanel();
		pnlSalesUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pay = (Integer.parseInt(txtPay.getText()));
				int totcost = (Integer.parseInt(txtTotalCost.getText()));
				
				
				int bal = pay - totcost;
				
				txtBalance.setText(String.valueOf(bal));
				
				sales();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlSalesUpdate.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlSalesUpdate.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlSalesUpdate.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlSalesUpdate.setBackground(new Color(30, 60, 60));
			}
		});
		pnlSalesUpdate.setLayout(null);
		pnlSalesUpdate.setBackground(new Color(47, 79, 79));
		pnlSalesUpdate.setBounds(536, 514, 155, 55);
		contentPane.add(pnlSalesUpdate);
		
		JLabel lblSalesUpdate = new JLabel("SALES UPDATE");
		lblSalesUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesUpdate.setForeground(Color.WHITE);
		lblSalesUpdate.setFont(new Font("Arial", Font.BOLD, 14));
		lblSalesUpdate.setBounds(44, 11, 111, 33);
		pnlSalesUpdate.add(lblSalesUpdate);
		
		JLabel lblIconSalesUpdate = new JLabel("");
		lblIconSalesUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconSalesUpdate.setBounds(0, 0, 55, 55);
		pnlSalesUpdate.add(lblIconSalesUpdate);
		lblIconSalesUpdate.setIcon(new ImageIcon(img_update));
		
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
		lblX.setBounds(727, 0, 35, 35);
		contentPane.add(lblX);
		
		
		
		this.pnoo = pno;
		npno = pno;
		
		lblNum.setText(npno);
		
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
	
	public void sales() {
		
		DateTimeFormatter daa = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String date = daa.format(now);
		
		
		String subtot = txtTotalCost.getText();
		String pay = txtPay.getText();
		String balance = txtBalance.getText();
		
		
		int lastinsertid = 0;
		
		try {
			
			String query = "insert into sales(date,subtotal,pay,balance)values(?,?,?,?)";
			pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, date);
			pst.setString(2, subtot);
			pst.setString(3, pay);
			pst.setString(4, balance);
			
			pst.executeUpdate();
			
			rs = pst.getGeneratedKeys();
			
			if(rs.next()) {
				lastinsertid = rs.getInt(1);
			}
			
			int rows = table.getColumnCount();
			
			String query1 = "insert into sale_product(sales_id,prod_id,sellprice,qty,total)values(?,?,?,?,?)";
			pst = con.prepareStatement(query1);
			String pres_id;
			String item_id;
			String item_name;
			int price;
			//int qty;
			String qty;
			int total = 0;
			
			for(int i = 0; i < table.getRowCount(); i++) {
				pres_id = (String)table.getValueAt(i, 0);
				item_id = (String)table.getValueAt(i, 1);
				//qty = (int)table.getValueAt(i, 3);
				qty = table.getValueAt(i, 3).toString();
				int qty1 = Integer.parseInt(qty);
				price = (int)table.getValueAt(i, 4);
				total = (int)table.getValueAt(i, 5);
				
				pst.setInt(1, lastinsertid);
				pst.setString(2, item_id);
				pst.setInt(3, price);
				//pst.setInt(4, qty);
				pst.setInt(4, qty1);
				pst.setInt(5, total);
				
				
				JOptionPane.showMessageDialog(this, "Record Saved");
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
