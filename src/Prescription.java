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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Prescription extends JFrame {
	
	private Image img_addfile = new ImageIcon(Prescription.class.getResource("res/addfile.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_cancel = new ImageIcon(Prescription.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTextField txtChannelNo;
	private JTextField txtDType;
	String id;
	String docname;
	
	String newid;
	String newdocname;
	private JLabel lblNum;
	
	private JPanel pnlCreate;
	private JPanel pnlCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prescription frame = new Prescription();
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
	public Prescription() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Prescription.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 470);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Prescription", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 204, 51)));
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(10, 46, 640, 347);
		contentPane.add(panel);
		
		JLabel lblChannelNo = new JLabel("Channel No");
		lblChannelNo.setForeground(Color.WHITE);
		lblChannelNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChannelNo.setBounds(70, 105, 105, 29);
		panel.add(lblChannelNo);
		
		JLabel lblDType = new JLabel("Disease Type");
		lblDType.setForeground(Color.WHITE);
		lblDType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDType.setBounds(70, 150, 105, 29);
		panel.add(lblDType);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescription.setBounds(70, 195, 105, 29);
		panel.add(lblDescription);
		
		txtChannelNo = new JTextField();
		txtChannelNo.setBackground(new Color(204, 204, 102));
		txtChannelNo.setColumns(10);
		txtChannelNo.setBounds(240, 105, 287, 29);
		panel.add(txtChannelNo);
		
		txtDType = new JTextField();
		txtDType.setBackground(new Color(204, 204, 102));
		txtDType.setColumns(10);
		txtDType.setBounds(240, 150, 287, 29);
		panel.add(txtDType);
		
		JTextArea txtDes = new JTextArea();
		txtDes.setBackground(new Color(204, 204, 102));
		txtDes.setBounds(240, 195, 287, 100);
		panel.add(txtDes);
		
		lblNum = new JLabel("Num");
		lblNum.setForeground(Color.ORANGE);
		lblNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum.setBounds(240, 60, 88, 29);
		panel.add(lblNum);
		
		JLabel lblPrescriptionNo = new JLabel("Prescription No");
		lblPrescriptionNo.setForeground(Color.WHITE);
		lblPrescriptionNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrescriptionNo.setBounds(70, 60, 105, 29);
		panel.add(lblPrescriptionNo);
		
		/*
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pnum = lblNum.getText();
				String chno = txtChannelNo.getText();
				String dtype = txtDType.getText();
				String des = txtDes.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into prescription(pid,channelid,doctorname,dtype,description)values(?,?,?,?,?)");
					pst.setString(1, pnum);
					pst.setString(2, chno);
					pst.setString(3, newdocname);
					pst.setString(4, dtype);
					pst.setString(5, des);
					
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnCreate, "Prescription Inserted");
                    	AutoID();
                        
                        txtChannelNo.setText("");
                        txtDType.setText("");
                        txtDes.setText("");
                        txtDType.requestFocus();
                        
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		btnCreate.setBounds(520, 414, 130, 45);
		contentPane.add(btnCreate);
		*/
		
		pnlCreate = new JPanel();
		pnlCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pnum = lblNum.getText();
				String chno = txtChannelNo.getText();
				String dtype = txtDType.getText();
				String des = txtDes.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into prescription(pid,channelid,doctorname,dtype,description)values(?,?,?,?,?)");
					pst.setString(1, pnum);
					pst.setString(2, chno);
					pst.setString(3, newdocname);
					pst.setString(4, dtype);
					pst.setString(5, des);
					
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(pnlCreate, "Prescription Inserted");
                    	AutoID();
                        
                        txtChannelNo.setText("");
                        txtDType.setText("");
                        txtDes.setText("");
                        txtDType.requestFocus();
                        
                    
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
		pnlCreate.setBounds(430, 404, 105, 55);
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
		pnlCancel.setBounds(545, 404, 105, 55);
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
		lblX.setBounds(625, 0, 35, 35);
		contentPane.add(lblX);
	}
	
	public Prescription(String chno, String dname) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Prescription.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 470);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Prescription", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 204, 51)));
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(10, 46, 640, 347);
		contentPane.add(panel);
		
		JLabel lblChannelNo = new JLabel("Channel No");
		lblChannelNo.setForeground(Color.WHITE);
		lblChannelNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChannelNo.setBounds(70, 105, 105, 29);
		panel.add(lblChannelNo);
		
		JLabel lblDType = new JLabel("Disease Type");
		lblDType.setForeground(Color.WHITE);
		lblDType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDType.setBounds(70, 150, 105, 29);
		panel.add(lblDType);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescription.setBounds(70, 195, 105, 29);
		panel.add(lblDescription);
		
		txtChannelNo = new JTextField();
		txtChannelNo.setColumns(10);
		txtChannelNo.setBounds(240, 105, 287, 29);
		panel.add(txtChannelNo);
		
		txtDType = new JTextField();
		txtDType.setColumns(10);
		txtDType.setBounds(240, 150, 287, 29);
		panel.add(txtDType);
		
		JTextArea txtDes = new JTextArea();
		txtDes.setBounds(240, 195, 287, 100);
		panel.add(txtDes);
		
		lblNum = new JLabel("Num");
		lblNum.setForeground(Color.ORANGE);
		lblNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum.setBounds(240, 60, 88, 29);
		panel.add(lblNum);
		
		JLabel lblPrescriptionNo = new JLabel("Prescription No");
		lblPrescriptionNo.setForeground(Color.WHITE);
		lblPrescriptionNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrescriptionNo.setBounds(70, 60, 105, 29);
		panel.add(lblPrescriptionNo);
		
		/*
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pnum = lblNum.getText();
				String chno = txtChannelNo.getText();
				String dtype = txtDType.getText();
				String des = txtDes.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into prescription(pid,channelid,doctorname,dtype,description)values(?,?,?,?,?)");
					pst.setString(1, pnum);
					pst.setString(2, chno);
					pst.setString(3, newdocname);
					pst.setString(4, dtype);
					pst.setString(5, des);
					
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(btnCreate, "Prescription Inserted");
                    	AutoID();
                        
                        txtChannelNo.setText("");
                        txtDType.setText("");
                        txtDes.setText("");
                        txtDType.requestFocus();
                        
                    
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		btnCreate.setBounds(520, 414, 130, 45);
		contentPane.add(btnCreate);
		*/
		
		pnlCreate = new JPanel();
		pnlCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pnum = lblNum.getText();
				String chno = txtChannelNo.getText();
				String dtype = txtDType.getText();
				String des = txtDes.getText();
				
				try {
					
					//String query = "insert into patient values('" + pnum + "','" + pname + "','" + phone + "','" + address + "')";
					
					pst = con.prepareStatement("insert into prescription(pid,channelid,doctorname,dtype,description)values(?,?,?,?,?)");
					pst.setString(1, pnum);
					pst.setString(2, chno);
					pst.setString(3, newdocname);
					pst.setString(4, dtype);
					pst.setString(5, des);
					
					pst.executeUpdate();
					
                        
                    	JOptionPane.showMessageDialog(pnlCreate, "Prescription Inserted");
                    	AutoID();
                        
                        txtChannelNo.setText("");
                        txtDType.setText("");
                        txtDes.setText("");
                        txtDType.requestFocus();
                        
                    
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
		pnlCreate.setBounds(430, 404, 105, 55);
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
		pnlCancel.setBounds(545, 404, 105, 55);
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
		lblX.setBounds(625, 0, 35, 35);
		contentPane.add(lblX);
		
		
		this.id = chno;
		this.docname = dname;
		
		
		newid = id;
		newdocname = docname;
		
		//JOptionPane.showMessageDialog(this, newid);
		//JOptionPane.showMessageDialog(this, newdocname);
		
		setVisible(true);
		txtChannelNo.setText(newid);
		txtChannelNo.setEnabled(false);
		
		Connect();
		AutoID();
		
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
	
	public void AutoID() {
		try {
			Statement s = con.createStatement();
			rs = s.executeQuery("select MAX(pid) from prescription");
			rs.next();
			rs.getString("MAX(pid)");
			
			if(rs.getString("MAX(pid)") == null) {
				lblNum.setText("PC001");
			}
			else {
				long id = Long.parseLong(rs.getString("MAX(pid)").substring(2, rs.getString("MAX(pid)").length()));
				id++;
				lblNum.setText("PC" + String.format("%03d", id));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}
