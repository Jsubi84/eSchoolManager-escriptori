package view;

import java.awt.Dimension;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import controller.ControllerView;


import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



/**
 * @author Jordi Subirana
 *
 */
public class FacturaGeneForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ControllerView controllerView;

	
	/**
	 * Create the panel.
	 */
	public FacturaGeneForm(ControllerView controllerView) {
		
		setControllerView(controllerView);
		setPreferredSize(new Dimension(818, 524));
		setSize(new Dimension(818, 524));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 818, 524);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFactura = new JLabel("Factura");
		lblFactura.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Dubai", Font.BOLD, 21));
		lblFactura.setBounds(10, 11, 798, 41);
		panel.add(lblFactura);
		
		JButton btnGene = new JButton("GENERAR");
		btnGene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnGene.setBounds(10, 63, 250, 23);
		panel.add(btnGene);
		
		
	}


	/**
	 * Getters i Setters
	 */

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}
		
}
