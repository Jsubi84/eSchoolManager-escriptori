package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class SessioFilterForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SessioFilterForm() {
		setPreferredSize(new Dimension(818, 524));
		setSize(new Dimension(818, 524));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 818, 524);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblEmpleat = new JLabel("Sessio");
		lblEmpleat.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblEmpleat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpleat.setFont(new Font("Dubai", Font.BOLD, 21));
		lblEmpleat.setBounds(10, 11, 798, 41);
		panel.add(lblEmpleat);

	}

}
