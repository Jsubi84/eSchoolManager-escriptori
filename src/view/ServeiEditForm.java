package view;

import java.awt.CardLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerView;
import util.Convert;


/**
 * @author Jordi Subirana
 *
 */
public class ServeiEditForm extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final Short ALTA = 1;
	private static final Short MODIFICAR = 2;
	private static final Short LLEGIR = 3;
	private static final String ISBLACK = "Tots el camps s'han d'omplir";
	private static final String DURADA_SERVEI = "La durada d'un servei ha de ser un numero de minuts, un numero enter";
	private static final String COST_SERVEI = "El cost d'un servei ha de ser numeric amb o sense coma";
	
	private JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JTextField tfnomServei;
	private JButton okButton, cancelButton;
	private ControllerView controllerView;
	private JTextField tfCodi;
	private JTextField tfDurada;
	private JTextField tfCost;
	
	

	/**
	 * Create the dialog.
	 */
	public ServeiEditForm(MainView parent, boolean modal ,ControllerView controllerView, Short mode) {
		super(parent, modal);
		
		setResizable(false);
		setLocationRelativeTo(null);

		setControllerView(controllerView);
		setType(Type.UTILITY);
		setTitle("Servei");
		setBounds(100, 100, 280, 303);
		getContentPane().setLayout(new CardLayout(0, 0));
		contentPanel.setSize(new Dimension(280, 280));
		contentPanel.setPreferredSize(new Dimension(280, 280));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "name_119289035507000");
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 266, 266);
		contentPanel.add(panel);
		panel.setLayout(null);
		

		okButton = new JButton("GUARDAR");
		okButton.setBounds(23, 228, 107, 23);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == ALTA) {
					if(getTfnomServei().getText().isBlank() ||getTfDurada().getText().isBlank()|| getTfCost().getText().isBlank()){
						controllerView.missatgeIncidencia(ISBLACK);
					}else if (Convert.isNotNumericInt(getTfDurada().getText())) {
						controllerView.missatgeIncidencia(DURADA_SERVEI);
					}else if (Convert.isNotNumericDouble(getTfCost().getText())) {
						controllerView.missatgeIncidencia(COST_SERVEI);
					}else {//Fem l'alta del servei borrem taula i refresquem
						getControllerView().altaServei();
						getControllerView().getMainview().getServeiForm().recarregarTaula(); 
						tancarForm();
					}
				}else if (mode == MODIFICAR){
					if(getTfnomServei().getText().isBlank() ||getTfDurada().getText().isBlank()|| getTfCost().getText().isBlank()){
						controllerView.missatgeIncidencia(ISBLACK);
					}else if (Convert.isNotNumericInt(getTfDurada().getText())) {
						controllerView.missatgeIncidencia(DURADA_SERVEI);
					}else if (Convert.isNotNumericDouble(getTfCost().getText())) {
						controllerView.missatgeIncidencia(COST_SERVEI);						
					}else {
						getControllerView().modiServei(Integer.parseInt(getTfCodi().getText()));
						getControllerView().getMainview().getServeiForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == LLEGIR){ 
					getControllerView().getMainview().getServeiForm().llegirServei();
				}
			}
		});
		panel.add(okButton);

		
		cancelButton = new JButton("CANCELAR");
		cancelButton.setBounds(144, 228, 107, 23);		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tancarForm();
			}
		});
		panel.add(cancelButton);
		

		JLabel lblnomServei = new JLabel("Servei");
		lblnomServei.setBounds(66, 42, 149, 14);
		panel.add(lblnomServei);

		tfnomServei = new JTextField();
		tfnomServei.setBounds(66, 64, 149, 20);
		panel.add(tfnomServei);
		tfnomServei.setColumns(10);
		
		JLabel lblDurarda = new JLabel("Durada");
		lblDurarda.setBounds(66, 91, 149, 14);
		panel.add(lblDurarda);
		
		tfCodi = new JTextField();
		tfCodi.setVisible(false);
		tfCodi.setBounds(215, 11, 36, 20);
		panel.add(tfCodi);
		tfCodi.setColumns(10);
		
		tfDurada = new JTextField();
		tfDurada.setColumns(10);
		tfDurada.setBounds(66, 116, 149, 20);
		panel.add(tfDurada);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(66, 147, 149, 14);
		panel.add(lblCost);
		
		tfCost = new JTextField();
		tfCost.setColumns(10);
		tfCost.setBounds(66, 172, 149, 20);
		panel.add(tfCost);
		
	}

	/**
	 *Metode per tancar formulari Edit
	 */
	public void tancarForm(){
		this.dispose();
	}

	/**
	 * Getters i Setters
	 *
	 */
	public JTextField getTfnomDep() {
		return tfnomServei;
	}

	public void setTfnomDep(JTextField jTextField) {
		this.tfnomServei = jTextField;
	}

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}

	public JTextField getTfCodi() {
		return tfCodi;
	}

	public void setTfCodi(JTextField tfCodi) {
		this.tfCodi = tfCodi;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JTextField getTfnomServei() {
		return tfnomServei;
	}

	public void setTfnomServei(JTextField tfnomServei) {
		this.tfnomServei = tfnomServei;
	}

	public JTextField getTfDurada() {
		return tfDurada;
	}

	public void setTfDurada(JTextField tfDurada) {
		this.tfDurada = tfDurada;
	}

	public JTextField getTfCost() {
		return tfCost;
	}

	public void setTfCost(JTextField tfCost) {
		this.tfCost = tfCost;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
}
