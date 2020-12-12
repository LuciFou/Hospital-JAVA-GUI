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
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;

public class Report extends JFrame {
	
	private Image img_close = new ImageIcon(Report.class.getResource("res/cancel.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

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
					Report frame = new Report();
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
	public Report() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Report.class.getResource("/res/hospital11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 580);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(47, 79, 79), 2), new LineBorder(new Color(255, 255, 255), 2)));
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(10, 78, 720, 425);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(255, 200, 0), 2));
		scrollPane.setBounds(10, 11, 700, 403);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Date", "SubTotal", "Pay", "Balance"
			}
		));
		
		/*
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(599, 396, 108, 50);
		panel.add(btnNewButton);
		*/
		
		JLabel lblSalesReport = new JLabel("SALES REPORT");
		lblSalesReport.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSalesReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesReport.setForeground(new Color(47, 79, 79));
		lblSalesReport.setFont(new Font("Dialog", Font.BOLD, 38));
		lblSalesReport.setBounds(10, 11, 720, 57);
		contentPane.add(lblSalesReport);
		
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
		salesTable();
		
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
	
	public void salesTable() {
		try {
			pst = con.prepareStatement("select * from sales");
			
			rs = pst.executeQuery();
			ResultSetMetaData Rsm = rs.getMetaData();
			int c;
			c = Rsm.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				Vector v2 = new Vector();
				for(int i = 1; i <= c; i++) {
					v2.add(rs.getString("id"));
					v2.add(rs.getString("date"));
					v2.add(rs.getString("subtotal"));
					v2.add(rs.getString("pay"));
					v2.add(rs.getString("balance"));
				}
				
				df.addRow(v2);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
