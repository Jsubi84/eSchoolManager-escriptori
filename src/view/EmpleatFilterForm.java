package view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class EmpleatFilterForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public EmpleatFilterForm() {
		setPreferredSize(new Dimension(818, 524));
		setSize(new Dimension(818, 524));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 818, 524);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblEmpleat = new JLabel("Empleat");
		lblEmpleat.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblEmpleat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpleat.setFont(new Font("Dubai", Font.BOLD, 21));
		lblEmpleat.setBounds(10, 11, 798, 41);
		panel.add(lblEmpleat);

	}
}
