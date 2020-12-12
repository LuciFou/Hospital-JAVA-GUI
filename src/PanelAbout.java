import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.DropMode;

public class PanelAbout extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAbout() {
		setBackground(new Color(143, 188, 143));
		setBounds(10, 113, 660, 300);
		setLayout(null);
		
		JLabel lblThisApplicationIs = new JLabel("This application is made to assure Hospital management.");
		lblThisApplicationIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisApplicationIs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThisApplicationIs.setForeground(new Color(0, 0, 0));
		lblThisApplicationIs.setBounds(10, 11, 640, 278);
		add(lblThisApplicationIs);
		setVisible(true);
	}
}