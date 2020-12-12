
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.text.*;
import java.awt.event.*;


public class Patient extends JFrame {
	
	private Image img_add = new ImageIcon(Patient.class.getResource("res/plus.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_update = new ImageIcon(Patient.class.getResource("res/update.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_delete = new ImageIcon(Patient.class.getResource("res/delete.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_cancel = new ImageIcon(Patient.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPatientName;
	private JTextField txtPhone;
	private JTable table;
	private JLabel lblNum;
	/*
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnExit;
	*/
	private JLabel lblPatientNo;
	private JLabel lblPatientName;
	private JLabel lblPhone;
	private JLabel lblAddress;
	private JTextArea txtAddress;
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
					Patient frame = new Patient();
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
	public Patient() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Patient.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 490);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPatientRG = new JLabel("PATIENT REGISTRATION");
		lblPatientRG.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPatientRG.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientRG.setForeground(new Color(47, 79, 79));
		lblPatientRG.setFont(new Font("Dialog", Font.BOLD, 38));
		lblPatientRG.setBounds(10, 11, 830, 57);
		contentPane.add(lblPatientRG);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 79, 830, 334);
		contentPane.add(panel_1);
		panel_1.setBackground(new Color(47, 79, 79));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Patient Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 204, 51)));
		panel_1.setLayout(null);
		
		lblPatientNo = new JLabel("Patient No");
		lblPatientNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPatientNo.setForeground(Color.WHITE);
		lblPatientNo.setBounds(28, 55, 88, 29);
		panel_1.add(lblPatientNo);
		
		lblPatientName = new JLabel("Patient Name");
		lblPatientName.setForeground(Color.WHITE);
		lblPatientName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPatientName.setBounds(28, 108, 88, 29);
		panel_1.add(lblPatientName);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhone.setBounds(28, 165, 88, 29);
		panel_1.add(lblPhone);
		
		lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(28, 219, 88, 29);
		panel_1.add(lblAddress);
		
		lblNum = new JLabel("Num");
		lblNum.setForeground(Color.ORANGE);
		lblNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum.setBounds(167, 55, 88, 29);
		panel_1.add(lblNum);
		
		txtPatientName = new JTextField();
		txtPatientName.setBackground(new Color(204, 204, 102));
		txtPatientName.setBounds(167, 108, 198, 29);
		panel_1.add(txtPatientName);
		txtPatientName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBackground(new Color(204, 204, 102));
		txtPhone.setColumns(10);
		txtPhone.setBounds(167, 165, 198, 29);
		panel_1.add(txtPhone);
		
		txtAddress = new JTextArea();
		txtAddress.setBackground(new Color(204, 204, 102));
		txtAddress.setBounds(167, 219, 198, 72);
		panel_1.add(txtAddress);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE, 2));
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int SelectIndex = table.getSelectedRow();
				
				lblNum.setText(d1.getValueAt(SelectIndex, 0).toString());
				txtPatientName.setText(d1.getValueAt(SelectIndex, 1).toString());
				txtPhone.setText(d1.getValueAt(SelectIndex, 2).toString());
				txtAddress.setText(d1.getValueAt(SelectIndex, 3).toString());
				
				pnlAdd.setEnabled(false);
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
				"Patient No", "Patient Name", "Phone", "Address"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		
		/*
		btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 424, 89, 31);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(109, 424, 89, 31);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(208, 424, 89, 31);
		contentPane.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(307, 424, 89, 31);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pnum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from patient where patientno = ?");
					
					pst.setString(1, pnum);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(btnDelete, "Patient Deleted");
                    AutoID();
                    txtPatientName.setText("");
                    txtPhone.setText("");
                    txtAddress.setText("");
                    txtPatientName.requestFocus();
                    patientTable();
                    btnAdd.setEnabled(true);
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String pname = txtPatientName.getText();
				String phone = txtPhone.getText();
				String address = txtAddress.getText();
				String pnum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("update patient set name = ? , phone = ? , address = ? where patientno = ?");
					
					pst.setString(1, pname);
					pst.setString(2, phone);
					pst.setString(3, address);
					pst.setString(4, pnum);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(btnUpdate, "Patient Updated");
                    AutoID();
                    txtPatientName.setText("");
                    txtPhone.setText("");
                    txtAddress.setText("");
                    txtPatientName.requestFocus();
                    patientTable();
                    btnAdd.setEnabled(true);
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pnum = lblNum.getText();
				String pname = txtPatientName.getText();
				String phone = txtPhone.getText();
				String address = txtAddress.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into patient(patientno,name,phone,address)values(?,?,?,?)");
					pst.setString(1, pnum);
					pst.setString(2, pname);
					pst.setString(3, phone);
					pst.setString(4, address);
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnAdd, "Patient Inserted");
                    	AutoID();
                        txtPatientName.setText("");
                        txtPhone.setText("");
                        txtAddress.setText("");
                        txtPatientName.requestFocus();
                        patientTable();
                    
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
				String pnum = lblNum.getText();
				String pname = txtPatientName.getText();
				String phone = txtPhone.getText();
				String address = txtAddress.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into patient(patientno,name,phone,address)values(?,?,?,?)");
					pst.setString(1, pnum);
					pst.setString(2, pname);
					pst.setString(3, phone);
					pst.setString(4, address);
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(pnlAdd, "Patient Added");
                    	AutoID();
                        txtPatientName.setText("");
                        txtPhone.setText("");
                        txtAddress.setText("");
                        txtPatientName.requestFocus();
                        patientTable();
                    
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
				String pname = txtPatientName.getText();
				String phone = txtPhone.getText();
				String address = txtAddress.getText();
				String pnum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("update patient set name = ? , phone = ? , address = ? where patientno = ?");
					
					pst.setString(1, pname);
					pst.setString(2, phone);
					pst.setString(3, address);
					pst.setString(4, pnum);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(pnlUpdate, "Patient Updated");
                    AutoID();
                    txtPatientName.setText("");
                    txtPhone.setText("");
                    txtAddress.setText("");
                    txtPatientName.requestFocus();
                    patientTable();
                    pnlAdd.setEnabled(true);
                    
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
				
				String pnum = lblNum.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from patient where patientno = ?");
					
					pst.setString(1, pnum);
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(pnlDelete, "Patient Deleted");
                    AutoID();
                    txtPatientName.setText("");
                    txtPhone.setText("");
                    txtAddress.setText("");
                    txtPatientName.requestFocus();
                    patientTable();
                    pnlAdd.setEnabled(true);
                    
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
		table.getColumnModel().getColumn(1).setPreferredWidth(153);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(92);
		table.getColumnModel().getColumn(2).setMinWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(138);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		
		
		Connect();
		AutoID();
		patientTable();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JLabel lblPatientRG;
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
			rs = s.executeQuery("select MAX(patientno) from patient");
			rs.next();
			rs.getString("MAX(patientno)");
			
			if(rs.getString("MAX(patientno)") == null) {
				lblNum.setText("PS001");
			}
			else {
				long id = Long.parseLong(rs.getString("MAX(patientno)").substring(2, rs.getString("MAX(patientno)").length()));
				id++;
				lblNum.setText("PS" + String.format("%03d", id));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void patientTable() {
		try {
			pst = con.prepareStatement("select * from patient");
			rs = pst.executeQuery();
			ResultSetMetaData Rsm = rs.getMetaData();
			int c;
			c = Rsm.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				Vector v2 = new Vector();
				for(int i = 1; i <= c; i++) {
					v2.add(rs.getString("patientno"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("phone"));
					v2.add(rs.getString("address"));
				}
				
				df.addRow(v2);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
