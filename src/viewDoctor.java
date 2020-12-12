import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

public class viewDoctor extends JFrame {
	
	private Image img_close = new ImageIcon(viewDoctor.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTable table;
	private JPanel pnlClose;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewDoctor frame = new viewDoctor();
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
	public viewDoctor() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewDoctor.class.getResource("/res/hospital11.png")));
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
		panel_1.setLayout(null);
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(47, 79, 79), 2), new LineBorder(new Color(255, 255, 255), 2)));
		panel_1.setBackground(new Color(47, 79, 79));
		panel_1.setBounds(10, 78, 720, 425);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(255, 200, 0), 2));
		scrollPane.setBounds(10, 11, 700, 403);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Doctor No", "Doctor Name", "Speciality", "Qualification", "Fee", "Phone", "Room No"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lblDoctorsList = new JLabel("DOCTORS LIST");
		lblDoctorsList.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDoctorsList.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorsList.setForeground(new Color(47, 79, 79));
		lblDoctorsList.setFont(new Font("Dialog", Font.BOLD, 38));
		lblDoctorsList.setBounds(10, 11, 720, 57);
		contentPane.add(lblDoctorsList);
		
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
		
		
		Connect();
		doctorTable();
		
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
