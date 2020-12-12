import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;

public class viewChannel extends JFrame {
	
	private Image img_presc = new ImageIcon(viewChannel.class.getResource("res/presc.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_close = new ImageIcon(viewChannel.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTable table;
	private JPanel pnlClose;
	private JPanel pnlPresc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewChannel frame = new viewChannel();
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
	public viewChannel() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewChannel.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 580);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 79, 720, 425);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(47, 79, 79), 2), new LineBorder(new Color(255, 255, 255), 2)));
		panel_1.setBackground(new Color(47, 79, 79));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE, 2));
		scrollPane.setBounds(10, 11, 700, 403);
		panel_1.add(scrollPane);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		
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
		
		JLabel lblViewChannel = new JLabel("VIEW CHANNEL");
		lblViewChannel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblViewChannel.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewChannel.setForeground(new Color(47, 79, 79));
		lblViewChannel.setFont(new Font("Dialog", Font.BOLD, 38));
		lblViewChannel.setBounds(10, 11, 720, 57);
		contentPane.add(lblViewChannel);
		
		/*
		JButton btnPrescription = new JButton("Prescription");
		btnPrescription.setBounds(349, 524, 148, 45);
		contentPane.add(btnPrescription);
		
		btnPrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String chno = d1.getValueAt(selectedIndex, 0).toString();
				String docname = d1.getValueAt(selectedIndex, 1).toString();
				
				new Prescription(chno, docname).setVisible(true);
				
			}
		});
		*/
		
		pnlPresc = new JPanel();
		pnlPresc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String chno = d1.getValueAt(selectedIndex, 0).toString();
				String docname = d1.getValueAt(selectedIndex, 1).toString();
				
				new Prescription(chno, docname).setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlPresc.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlPresc.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlPresc.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlPresc.setBackground(new Color(30, 60, 60));
			}
		});
		pnlPresc.setLayout(null);
		pnlPresc.setBackground(new Color(47, 79, 79));
		pnlPresc.setBounds(450, 515, 165, 55);
		contentPane.add(pnlPresc);
		
		JLabel lblPresc = new JLabel("PRESCRIPTION");
		lblPresc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPresc.setForeground(Color.WHITE);
		lblPresc.setFont(new Font("Arial", Font.BOLD, 14));
		lblPresc.setBounds(37, 11, 128, 33);
		pnlPresc.add(lblPresc);
		
		JLabel lblIconPresc = new JLabel("");
		lblIconPresc.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconPresc.setBounds(0, 0, 55, 55);
		pnlPresc.add(lblIconPresc);
		lblIconPresc.setIcon(new ImageIcon(img_presc));
		
		
		pnlClose = new JPanel();
		pnlClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlClose.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlClose.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlClose.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlClose.setBackground(new Color(30, 60, 60));
			}
		});
		pnlClose.setLayout(null);
		pnlClose.setBackground(new Color(47, 79, 79));
		pnlClose.setBounds(625, 514, 105, 55);
		contentPane.add(pnlClose);
		
		JLabel lblClose = new JLabel("CLOSE");
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Arial", Font.BOLD, 14));
		lblClose.setBounds(43, 11, 62, 33);
		pnlClose.add(lblClose);
		
		JLabel lblIconClose = new JLabel("");
		lblIconClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconClose.setBounds(0, 0, 55, 55);
		pnlClose.add(lblIconClose);
		lblIconClose.setIcon(new ImageIcon(img_close));
		
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
		lblX.setBounds(705, 0, 35, 35);
		contentPane.add(lblX);
		
		
		
	}
	
	int id;
	int newid;
	
	public viewChannel(int id) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewChannel.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 580);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 79, 720, 425);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(47, 79, 79), 2), new LineBorder(new Color(255, 255, 255), 2)));
		panel_1.setBackground(new Color(47, 79, 79));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE, 2));
		scrollPane.setBounds(10, 11, 700, 403);
		panel_1.add(scrollPane);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		
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
		
		JLabel lblViewChannel = new JLabel("VIEW CHANNEL");
		lblViewChannel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblViewChannel.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewChannel.setForeground(new Color(47, 79, 79));
		lblViewChannel.setFont(new Font("Dialog", Font.BOLD, 38));
		lblViewChannel.setBounds(10, 11, 720, 57);
		contentPane.add(lblViewChannel);
		
		/*
		JButton btnPrescription = new JButton("Prescription");
		btnPrescription.setBounds(349, 524, 148, 45);
		contentPane.add(btnPrescription);
		
		btnPrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String chno = d1.getValueAt(selectedIndex, 0).toString();
				String docname = d1.getValueAt(selectedIndex, 1).toString();
				
				new Prescription(chno, docname).setVisible(true);
				
			}
		});
		*/
		
		pnlPresc = new JPanel();
		pnlPresc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String chno = d1.getValueAt(selectedIndex, 0).toString();
				String docname = d1.getValueAt(selectedIndex, 1).toString();
				
				new Prescription(chno, docname).setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlPresc.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlPresc.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlPresc.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlPresc.setBackground(new Color(30, 60, 60));
			}
		});
		pnlPresc.setLayout(null);
		pnlPresc.setBackground(new Color(47, 79, 79));
		pnlPresc.setBounds(450, 515, 165, 55);
		contentPane.add(pnlPresc);
		
		JLabel lblPresc = new JLabel("PRESCRIPTION");
		lblPresc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPresc.setForeground(Color.WHITE);
		lblPresc.setFont(new Font("Arial", Font.BOLD, 14));
		lblPresc.setBounds(37, 11, 128, 33);
		pnlPresc.add(lblPresc);
		
		JLabel lblIconPresc = new JLabel("");
		lblIconPresc.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconPresc.setBounds(0, 0, 55, 55);
		pnlPresc.add(lblIconPresc);
		lblIconPresc.setIcon(new ImageIcon(img_presc));
		
		
		pnlClose = new JPanel();
		pnlClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlClose.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlClose.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlClose.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlClose.setBackground(new Color(30, 60, 60));
			}
		});
		pnlClose.setLayout(null);
		pnlClose.setBackground(new Color(47, 79, 79));
		pnlClose.setBounds(625, 514, 105, 55);
		contentPane.add(pnlClose);
		
		JLabel lblClose = new JLabel("CLOSE");
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Arial", Font.BOLD, 14));
		lblClose.setBounds(43, 11, 62, 33);
		pnlClose.add(lblClose);
		
		JLabel lblIconClose = new JLabel("");
		lblIconClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconClose.setBounds(0, 0, 55, 55);
		pnlClose.add(lblIconClose);
		lblIconClose.setIcon(new ImageIcon(img_close));
		
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
		lblX.setBounds(705, 0, 35, 35);
		contentPane.add(lblX);
		
		this.id = id;
		newid = id;
		Connect();
		channelTable();
		
		
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
	
	public void channelTable() {
		try {
			pst = con.prepareStatement("select channel.channelno,doctor.name,patient.name,channel.roomno,channel.chdate from doctor INNER JOIN channel on channel.doctorname = doctor.doctorno INNER JOIN patient on channel.patientname = patient.patientno where doctor.log_id = ? ");
			pst.setInt(1, newid);
			rs = pst.executeQuery();
			ResultSetMetaData Rsm = rs.getMetaData();
			int c;
			c = Rsm.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				Vector v2 = new Vector();
				for(int i = 1; i <= c; i++) {
					v2.add(rs.getString(1));
					v2.add(rs.getString(2));
					v2.add(rs.getString(3));
					v2.add(rs.getString(4));
					v2.add(rs.getString(5));
				}
				
				df.addRow(v2);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
