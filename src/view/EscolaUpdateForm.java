package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EscolaUpdateForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public EscolaUpdateForm() {
		setPreferredSize(new Dimension(818, 535));
		setSize(new Dimension(818, 535));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 818, 535);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitolDepartament = new JLabel("Escola");
		lblTitolDepartament.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolDepartament.setFont(new Font("Dubai", Font.BOLD, 21));
		lblTitolDepartament.setBounds(10, 11, 798, 41);
		panel.add(lblTitolDepartament);

	}
}
