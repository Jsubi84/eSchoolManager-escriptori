package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class DepartamentFilterForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public DepartamentFilterForm() {
		setPreferredSize(new Dimension(818, 524));
		setSize(new Dimension(818, 524));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 818, 524);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitolDepartament = new JLabel("Departament");
		lblTitolDepartament.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblTitolDepartament.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolDepartament.setFont(new Font("Dubai", Font.BOLD, 21));
		lblTitolDepartament.setBounds(10, 11, 798, 41);
		panel.add(lblTitolDepartament);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 219, 818, 305);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}
}
