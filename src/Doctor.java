
import java.awt.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.text.*;

import java.awt.event.*;


public class Doctor extends JFrame {
	
	private Image img_add = new ImageIcon(Doctor.class.getResource("res/plus.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_update = new ImageIcon(Doctor.class.getResource("res/update.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_delete = new ImageIcon(Doctor.class.getResource("res/delete.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_cancel = new ImageIcon(Doctor.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDoctorName;
	private JLabel lblNum;
	/*
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnExit;
	*/
	private JLabel lblDoctorNo;
	private JLabel lblDoctorName;
	private JLabel lblPhone;
	private JLabel lblChfee;
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
					Doctor frame = new Doctor();
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
	public Doctor() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Doctor.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDoctorRG = new JLabel("DOCTOR REGISTRATION");
		lblDoctorRG.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDoctorRG.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorRG.setForeground(new Color(47, 79, 79));
		lblDoctorRG.setFont(new Font("Dialog", Font.BOLD, 38));
		lblDoctorRG.setBounds(10, 11, 830, 62);
		contentPane.add(lblDoctorRG);
		
		JPanel pnlDoctorRG = new JPanel();
		pnlDoctorRG.setBounds(10, 84, 830, 389);
		contentPane.add(pnlDoctorRG);
		pnlDoctorRG.setBackground(new Color(47, 79, 79));
		pnlDoctorRG.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Doctor Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 204, 51)));
		pnlDoctorRG.setLayout(null);
		
		lblDoctorNo = new JLabel("Doctor No");
		lblDoctorNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoctorNo.setForeground(Color.WHITE);
		lblDoctorNo.setBounds(28, 57, 88, 29);
		pnlDoctorRG.add(lblDoctorNo);
		
		lblDoctorName = new JLabel("Doctor Name");
		lblDoctorName.setForeground(Color.WHITE);
		lblDoctorName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoctorName.setBounds(28, 97, 88, 29);
		pnlDoctorRG.add(lblDoctorName);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhone.setBounds(28, 257, 88, 29);
		pnlDoctorRG.add(lblPhone);
		
		lblChfee = new JLabel("Channel Fee");
		lblChfee.setForeground(Color.WHITE);
		lblChfee.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChfee.setBounds(28, 217, 88, 29);
		pnlDoctorRG.add(lblChfee);
		
		lblNum = new JLabel("Num");
		lblNum.setForeground(Color.ORANGE);
		lblNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum.setBounds(170, 57, 88, 29);
		pnlDoctorRG.add(lblNum);
		
		txtDoctorName = new JTextField();
		txtDoctorName.setBackground(new Color(204, 204, 102));
		txtDoctorName.setBounds(167, 97, 198, 29);
		pnlDoctorRG.add(txtDoctorName);
		txtDoctorName.setColumns(10);
		
		JLabel lblSpec = new JLabel("Speciality");
		lblSpec.setForeground(Color.WHITE);
		lblSpec.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpec.setBounds(28, 137, 88, 29);
		pnlDoctorRG.add(lblSpec);
		
		txtSpec = new JTextField();
		txtSpec.setBackground(new Color(204, 204, 102));
		txtSpec.setColumns(10);
		txtSpec.setBounds(167, 137, 198, 29);
		pnlDoctorRG.add(txtSpec);
		
		JLabel lblQ = new JLabel("Qualification");
		lblQ.setForeground(Color.WHITE);
		lblQ.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQ.setBounds(28, 177, 88, 29);
		pnlDoctorRG.add(lblQ);
		
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setForeground(Color.WHITE);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRoomNo.setBounds(28, 297, 88, 29);
		pnlDoctorRG.add(lblRoomNo);
		
		txtQ = new JTextField();
		txtQ.setBackground(new Color(204, 204, 102));
		txtQ.setColumns(10);
		txtQ.setBounds(167, 177, 198, 29);
		pnlDoctorRG.add(txtQ);
		
		txtChfee = new JTextField();
		txtChfee.setBackground(new Color(204, 204, 102));
		txtChfee.setColumns(10);
		txtChfee.setBounds(167, 217, 198, 29);
		pnlDoctorRG.add(txtChfee);
		
		txtPhone = new JTextField();
		txtPhone.setBackground(new Color(204, 204, 102));
		txtPhone.setColumns(10);
		txtPhone.setBounds(167, 257, 198, 29);
		pnlDoctorRG.add(txtPhone);
		
		JSpinner txtRoom = new JSpinner();
		txtRoom.setBackground(new Color(204, 204, 102));
		txtRoom.setBounds(167, 297, 55, 29);
		pnlDoctorRG.add(txtRoom);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE, 2));
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int SelectIndex = table.getSelectedRow();
				
				lblNum.setText(d1.getValueAt(SelectIndex, 0).toString());
				txtDoctorName.setText(d1.getValueAt(SelectIndex, 1).toString());
				txtSpec.setText(d1.getValueAt(SelectIndex, 2).toString());
				txtQ.setText(d1.getValueAt(SelectIndex, 3).toString());
				txtChfee.setText(d1.getValueAt(SelectIndex, 4).toString());
				txtPhone.setText(d1.getValueAt(SelectIndex, 5).toString());
				txtRoom.setValue(Integer.parseInt(d1.getValueAt(SelectIndex, 6).toString()));
				
				pnlAdd.setEnabled(false);
			}
		});
		scrollPane.setBounds(375, 11, 445, 367);
		pnlDoctorRG.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Doctor No", "Doctor Name", "Speciality", "Qualification", "Channel Fee", "Phone", "Room No"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		/*btnAdd = new JButton("Add");
		btnAdd.setBounds(30, 518, 89, 31);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(129, 518, 89, 31);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(228, 518, 89, 31);
		contentPane.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(327, 518, 89, 31);
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
					
					pst = con.prepareStatement("delete from doctor where doctorno = ?");
					
					pst.setString(1, pnum);
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnDelete, "Doctor Deleted");
                    	AutoID();
                    	txtDoctorName.setText("");
                        txtSpec.setText("");
                        txtQ.setText("");
                        txtChfee.setText("");
                        txtPhone.setText("");
                        txtRoom.setValue(0);
                        txtDoctorName.requestFocus();
                        doctorTable();
                        btnAdd.setEnabled(true);
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String pnum = lblNum.getText();
				String pname = txtDoctorName.getText();
				String spl = txtSpec.getText();
				String qul = txtQ.getText();
				String chfee = txtChfee.getText();
				String phone = txtPhone.getText();
				String rno = txtRoom.getValue().toString();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("update doctor set name = ? , spec = ? , qualification = ? , chfee = ? , phone = ? , room = ? where doctorno = ?");
					
					pst.setString(1, pname);
					pst.setString(2, spl);
					pst.setString(3, qul);
					pst.setString(4, chfee);
					pst.setString(5, phone);
					pst.setString(6, rno);
					pst.setString(7, pnum);
					pst.executeUpdate();
					
                        
					JOptionPane.showMessageDialog(btnUpdate, "Patient Updated");
                	AutoID();
                    txtDoctorName.setText("");
                    txtSpec.setText("");
                    txtQ.setText("");
                    txtChfee.setText("");
                    txtPhone.setText("");
                    txtRoom.setValue(0);
                    txtDoctorName.requestFocus();
                    doctorTable();
                    btnAdd.setEnabled(true);
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pnum = lblNum.getText();
				String pname = txtDoctorName.getText();
				String spl = txtSpec.getText();
				String qul = txtQ.getText();
				String chfee = txtChfee.getText();
				String phone = txtPhone.getText();
				String rno = txtRoom.getValue().toString();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into doctor(doctorno,name,spec,qualification,chfee,phone,room)values(?,?,?,?,?,?,?)");
					pst.setString(1, pnum);
					pst.setString(2, pname);
					pst.setString(3, spl);
					pst.setString(4, qul);
					pst.setString(5, chfee);
					pst.setString(6, phone);
					pst.setString(7, rno);
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnAdd, "Doctor Inserted");
                    	AutoID();
                        txtDoctorName.setText("");
                        txtSpec.setText("");
                        txtQ.setText("");
                        txtChfee.setText("");
                        txtPhone.setText("");
                        txtRoom.setValue(0);
                        txtDoctorName.requestFocus();
                        doctorTable();
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});*/
		
		pnlAdd = new JPanel();
		pnlAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pnum = lblNum.getText();
				String pname = txtDoctorName.getText();
				String spl = txtSpec.getText();
				String qul = txtQ.getText();
				String chfee = txtChfee.getText();
				String phone = txtPhone.getText();
				String rno = txtRoom.getValue().toString();
				
				try {
					
					pst = con.prepareStatement("insert into doctor(doctorno,name,spec,qualification,chfee,phone,room)values(?,?,?,?,?,?,?)");
					pst.setString(1, pnum);
					pst.setString(2, pname);
					pst.setString(3, spl);
					pst.setString(4, qul);
					pst.setString(5, chfee);
					pst.setString(6, phone);
					pst.setString(7, rno);
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(pnlAdd, "Doctor Added");
                    	AutoID();
                        txtDoctorName.setText("");
                        txtSpec.setText("");
                        txtQ.setText("");
                        txtChfee.setText("");
                        txtPhone.setText("");
                        txtRoom.setValue(0);
                        txtDoctorName.requestFocus();
                        doctorTable();
                    
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
		pnlAdd.setBounds(10, 484, 105, 55);
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
				String pnum = lblNum.getText();
				String pname = txtDoctorName.getText();
				String spl = txtSpec.getText();
				String qul = txtQ.getText();
				String chfee = txtChfee.getText();
				String phone = txtPhone.getText();
				String rno = txtRoom.getValue().toString();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("update doctor set name = ? , spec = ? , qualification = ? , chfee = ? , phone = ? , room = ? where doctorno = ?");
					
					pst.setString(1, pname);
					pst.setString(2, spl);
					pst.setString(3, qul);
					pst.setString(4, chfee);
					pst.setString(5, phone);
					pst.setString(6, rno);
					pst.setString(7, pnum);
					pst.executeUpdate();
					
                        
					JOptionPane.showMessageDialog(pnlUpdate, "Doctor Updated");
                	AutoID();
                    txtDoctorName.setText("");
                    txtSpec.setText("");
                    txtQ.setText("");
                    txtChfee.setText("");
                    txtPhone.setText("");
                    txtRoom.setValue(0);
                    txtDoctorName.requestFocus();
                    doctorTable();
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
		pnlUpdate.setBounds(125, 484, 105, 55);
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
					
					pst = con.prepareStatement("delete from doctor where doctorno = ?");
					
					pst.setString(1, pnum);
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(pnlDelete, "Doctor Deleted");
                    	AutoID();
                    	txtDoctorName.setText("");
                        txtSpec.setText("");
                        txtQ.setText("");
                        txtChfee.setText("");
                        txtPhone.setText("");
                        txtRoom.setValue(0);
                        txtDoctorName.requestFocus();
                        doctorTable();
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
		pnlDelete.setBounds(240, 484, 105, 55);
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
		pnlCancel.setBounds(355, 484, 105, 55);
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
		
		
		Connect();
		AutoID();
		doctorTable();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtSpec;
	private JTextField txtQ;
	private JTextField txtChfee;
	private JTextField txtPhone;
	private JTable table;
	private JScrollPane scrollPane;
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
			rs = s.executeQuery("select MAX(doctorno) from doctor");
			rs.next();
			rs.getString("MAX(doctorno)");
			
			if(rs.getString("MAX(doctorno)") == null) {
				lblNum.setText("DS001");
			}
			else {
				long id = Long.parseLong(rs.getString("MAX(doctorno)").substring(2, rs.getString("MAX(doctorno)").length()));
				id++;
				lblNum.setText("DS" + String.format("%03d", id));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void doctorTable() {
		try {
			pst = con.prepareStatement("select * from doctor");
			rs = pst.executeQuery();
			ResultSetMetaData Rsm = rs.getMetaData();
			int c;
			c = Rsm.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				Vector v2 = new Vector();
				for(int i = 1; i <= c; i++) {
					v2.add(rs.getString("doctorno"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("spec"));
					v2.add(rs.getString("qualification"));
					v2.add(rs.getString("chfee"));
					v2.add(rs.getString("phone"));
					v2.add(rs.getString("room"));
				}
				
				df.addRow(v2);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
