import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Channel extends JFrame {
	
	private Image img_addfile = new ImageIcon(Channel.class.getResource("res/addfile.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_delete = new ImageIcon(Channel.class.getResource("res/delete.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_cancel = new ImageIcon(Channel.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNum;
	private JComboBox cbxDoctor;
	private JComboBox cbxPatient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Channel frame = new Channel();
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
	public Channel() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Channel.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlChannelRG = new JPanel();
		pnlChannelRG.setBounds(10, 84, 830, 339);
		contentPane.add(pnlChannelRG);
		pnlChannelRG.setLayout(null);
		pnlChannelRG.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Channel Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 204, 51)));
		pnlChannelRG.setBackground(new Color(47, 79, 79));
		
		JLabel lblChannelNo = new JLabel("Channel No");
		lblChannelNo.setForeground(Color.WHITE);
		lblChannelNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChannelNo.setBounds(28, 55, 88, 29);
		pnlChannelRG.add(lblChannelNo);
		
		JLabel lblDoctorName = new JLabel("Doctor Name");
		lblDoctorName.setForeground(Color.WHITE);
		lblDoctorName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoctorName.setBounds(28, 108, 88, 29);
		pnlChannelRG.add(lblDoctorName);
		
		JLabel lblPatientName = new JLabel("Patient Name");
		lblPatientName.setForeground(Color.WHITE);
		lblPatientName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPatientName.setBounds(28, 165, 88, 29);
		pnlChannelRG.add(lblPatientName);
		
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setForeground(Color.WHITE);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRoomNo.setBounds(28, 218, 88, 29);
		pnlChannelRG.add(lblRoomNo);
		
		lblNum = new JLabel("Num");
		lblNum.setForeground(Color.ORANGE);
		lblNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum.setBounds(167, 55, 88, 29);
		pnlChannelRG.add(lblNum);
		
		JLabel lblChannelDate = new JLabel("Channel Date");
		lblChannelDate.setForeground(Color.WHITE);
		lblChannelDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChannelDate.setBounds(28, 265, 88, 29);
		pnlChannelRG.add(lblChannelDate);
		
		cbxDoctor = new JComboBox();
		cbxDoctor.setBackground(new Color(204, 204, 102));
		cbxDoctor.setBounds(167, 108, 155, 29);
		pnlChannelRG.add(cbxDoctor);
		
		cbxPatient = new JComboBox();
		cbxPatient.setBackground(new Color(204, 204, 102));
		cbxPatient.setBounds(167, 165, 155, 29);
		pnlChannelRG.add(cbxPatient);
		
		JSpinner txtRoom = new JSpinner();
		txtRoom.setBackground(new Color(204, 204, 102));
		txtRoom.setBounds(167, 218, 50, 29);
		pnlChannelRG.add(txtRoom);
		
		JDateChooser txtDate = new JDateChooser();
		txtDate.setBackground(new Color(204, 204, 102));
		txtDate.setBounds(167, 265, 155, 29);
		pnlChannelRG.add(txtDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selectIndex = table.getSelectedRow();
				
				chnum = d1.getValueAt(selectIndex, 0).toString();
				
				//JOptionPane.showMessageDialog(scrollPane, chnum);
				
			}
		});
		scrollPane.setBounds(332, 11, 488, 312);
		pnlChannelRG.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Channel No", "Doctor Name", "Patient Name", "Room No", "Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		/*JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(10, 429, 148, 45);
		contentPane.add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chnum = lblNum.getText();
				Doctor d = (Doctor) cbxDoctor.getSelectedItem();
				Patient p = (Patient) cbxPatient.getSelectedItem();
				String rno = txtRoom.getValue().toString();
				
				SimpleDateFormat dateformat = new SimpleDateFormat("yyy-MM-dd");
				String date = dateformat.format(txtDate.getDate());
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into channel(channelno,doctorname,patientname,roomno,chdate)values(?,?,?,?,?)");
					pst.setString(1, chnum);
					pst.setString(2, d.id);
					pst.setString(3, p.id);
					pst.setString(4, rno);
					pst.setString(5, date);
					
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(btnCreate, "Channel Created");
                    AutoID();
                    lblNum.setText("");
                    cbxDoctor.setSelectedIndex(-1);
                    cbxPatient.setSelectedIndex(-1);
                    txtRoom.setValue(0);
                    channelTable();
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(168, 429, 148, 45);
		contentPane.add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from channel where channelno = ?");
					pst.setString(1, chnum);
					
					pst.executeUpdate();
					
                
            JOptionPane.showMessageDialog(btnDelete, "Channel Deleted");
            AutoID();
            lblNum.setText("");
            cbxDoctor.setSelectedIndex(-1);
            cbxPatient.setSelectedIndex(-1);
            txtRoom.setValue(0);
            channelTable();
            
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		});*/
				
		JLabel lblChannel = new JLabel("CHANNEL");
		lblChannel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblChannel.setHorizontalAlignment(SwingConstants.CENTER);
		lblChannel.setForeground(new Color(47, 79, 79));
		lblChannel.setFont(new Font("Dialog", Font.BOLD, 38));
		lblChannel.setBounds(10, 11, 830, 62);
		contentPane.add(lblChannel);
		
		/* Add - Delete - Cancel - Exit */
		
		JPanel pnlCreate = new JPanel();
		pnlCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String chnum = lblNum.getText();
				Doctor d = (Doctor) cbxDoctor.getSelectedItem();
				Patient p = (Patient) cbxPatient.getSelectedItem();
				String rno = txtRoom.getValue().toString();
				
				SimpleDateFormat dateformat = new SimpleDateFormat("yyy-MM-dd");
				String date = dateformat.format(txtDate.getDate());
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into channel(channelno,doctorname,patientname,roomno,chdate)values(?,?,?,?,?)");
					pst.setString(1, chnum);
					pst.setString(2, d.id);
					pst.setString(3, p.id);
					pst.setString(4, rno);
					pst.setString(5, date);
					
					pst.executeUpdate();
					
                        
                    JOptionPane.showMessageDialog(pnlCreate, "Channel Created");
                    AutoID();
                    lblNum.setText("");
                    cbxDoctor.setSelectedIndex(0);
                    cbxPatient.setSelectedIndex(0);
                    txtRoom.setValue(0);
                    channelTable();
                    
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlCreate.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlCreate.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlCreate.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlCreate.setBackground(new Color(30, 60, 60));
			}
		});
		pnlCreate.setLayout(null);
		pnlCreate.setBackground(new Color(47, 79, 79));
		pnlCreate.setBounds(10, 434, 105, 55);
		contentPane.add(pnlCreate);
		
		JLabel lblCreate = new JLabel("CREATE");
		lblCreate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreate.setForeground(Color.WHITE);
		lblCreate.setFont(new Font("Arial", Font.BOLD, 14));
		lblCreate.setBounds(44, 11, 61, 33);
		pnlCreate.add(lblCreate);
		
		JLabel lblIconCreate = new JLabel("");
		lblIconCreate.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconCreate.setBounds(0, 0, 55, 55);
		pnlCreate.add(lblIconCreate);
		lblIconCreate.setIcon(new ImageIcon(img_addfile));
		
		JPanel pnlDelete = new JPanel();
		pnlDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("delete from channel where channelno = ?");
					pst.setString(1, chnum);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(pnlDelete, "Channel Deleted");
					AutoID();
					lblNum.setText("");
					cbxDoctor.setSelectedIndex(0);
					cbxPatient.setSelectedIndex(0);
					txtRoom.setValue(0);
					channelTable();
            
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
		pnlDelete.setBounds(125, 434, 105, 55);
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
		
		JPanel pnlCancel = new JPanel();
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
		pnlCancel.setBounds(240, 434, 105, 55);
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
		
		/* Add - Delete - Cancel - Exit */
				
		
		Connect();
		AutoID();
		loadDoctor();
		loadPatient();
		channelTable();
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String chnum;
	
	public class Patient {
		
		String id;
		String name;
		
		public Patient(String id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
		
	}
	
	public void loadPatient() {
		
		try {
			pst = con.prepareStatement("select * from patient");
			rs = pst.executeQuery();
			cbxPatient.removeAll();
			
			while(rs.next()) {
				cbxPatient.addItem(new Patient(rs.getString(1), rs.getString(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public class Doctor {
		
		String id;
		String name;
		
		public Doctor(String id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
		
	}
	
	public void loadDoctor() {
		
		try {
			pst = con.prepareStatement("select * from doctor");
			rs = pst.executeQuery();
			cbxDoctor.removeAll();
			
			while(rs.next()) {
				cbxDoctor.addItem(new Doctor(rs.getString(1), rs.getString(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
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
			rs = s.executeQuery("select MAX(channelno) from channel");
			rs.next();
			rs.getString("MAX(channelno)");
			
			if(rs.getString("MAX(channelno)") == null) {
				lblNum.setText("CH001");
			}
			else {
				long id = Long.parseLong(rs.getString("MAX(channelno)").substring(2, rs.getString("MAX(channelno)").length()));
				id++;
				lblNum.setText("CH" + String.format("%03d", id));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void channelTable() {
		try {
			pst = con.prepareStatement("select * from channel");
			rs = pst.executeQuery();
			ResultSetMetaData Rsm = rs.getMetaData();
			int c;
			c = Rsm.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				Vector v2 = new Vector();
				for(int i = 1; i <= c; i++) {
					v2.add(rs.getString("channelno"));
					v2.add(rs.getString("doctorname"));
					v2.add(rs.getString("patientname"));
					v2.add(rs.getString("roomno"));
					v2.add(rs.getString("chdate"));
				}
				
				df.addRow(v2);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
