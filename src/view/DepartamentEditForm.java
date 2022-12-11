package view;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.ControllerView;


/**
 * @author Jordi Subirana
 *
 */
public class DepartamentEditForm extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Short ALTA = 1;
	private static final Short MODIFICAR = 2;
	private static final Short LLEGIR = 3;
	private static final String NOM_DEPARTAMENT_ISBLACK = "El nom del departament s'ha d'omplir";
	
	
	private JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JTextField tfnomDep, tfCodi;
	private JButton okButton, cancelButton;
	private JCheckBox chckbxEscola, chckbxDep, chckbxBeca, chckbxEstudiant, chckbxEmpleat, chckbxServei, chckbxSessio, chckbxfactura;
	private ControllerView controllerView;
	
	

	/**
	 * Create the dialog.
	 */
	public DepartamentEditForm(MainView parent, boolean modal ,ControllerView controllerView, Short mode) {
		super(parent, modal);
		
		setResizable(false);
		setLocationRelativeTo(null);

		setControllerView(controllerView);
		setType(Type.UTILITY);
		setTitle("Departament");
		setBounds(100, 100, 280, 303);
		getContentPane().setLayout(new CardLayout(0, 0));
		contentPanel.setSize(new Dimension(280, 280));
		contentPanel.setPreferredSize(new Dimension(280, 280));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "name_119289035507000");
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 280, 280);
		contentPanel.add(panel);
		panel.setLayout(null);
		

		okButton = new JButton("GUARDAR");
		okButton.setBounds(23, 228, 107, 23);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == ALTA) {
					if(getTfnomDep().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_DEPARTAMENT_ISBLACK);			
					}else {//Fem l'alta del departament borrem taula i refresquem
						getControllerView().altaDepartament();
						getControllerView().getMainview().getDepartamentForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == MODIFICAR){
					if(getTfnomDep().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_DEPARTAMENT_ISBLACK);
					}else {
						getControllerView().modiDepartament(Integer.parseInt(getTfCodi().getText()));
						getControllerView().getMainview().getDepartamentForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == LLEGIR){ 
					getControllerView().getMainview().getDepartamentForm().llegirDepartament();
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
		

		JLabel lblnomDep = new JLabel("Nom departament");
		lblnomDep.setBounds(23, 33, 228, 14);
		panel.add(lblnomDep);

		tfnomDep = new JTextField();
		tfnomDep.setBorder(null);
		tfnomDep.setBounds(23, 55, 228, 20);
		panel.add(tfnomDep);
		tfnomDep.setColumns(10);

		chckbxEscola = new JCheckBox("Escola");
		chckbxEscola.setBounds(23, 103, 99, 23);
		panel.add(chckbxEscola);

		chckbxDep = new JCheckBox("Departament");
		chckbxDep.setBounds(23, 129, 107, 23);
		panel.add(chckbxDep);

		chckbxEmpleat = new JCheckBox("Empleat");
		chckbxEmpleat.setBounds(23, 155, 99, 23);
		panel.add(chckbxEmpleat);

		chckbxEstudiant = new JCheckBox("Estudiant");
		chckbxEstudiant.setBounds(23, 181, 99, 23);
		panel.add(chckbxEstudiant);
		
		chckbxServei = new JCheckBox("Servei");
		chckbxServei.setBounds(152, 103, 99, 23);
		panel.add(chckbxServei);
		
		chckbxBeca = new JCheckBox("Beca");
		chckbxBeca.setBounds(152, 129, 99, 23);
		panel.add(chckbxBeca);
		
		chckbxSessio = new JCheckBox("Sessio");
		chckbxSessio.setBounds(152, 155, 99, 23);
		panel.add(chckbxSessio);
		
		chckbxfactura = new JCheckBox("Factura");
		chckbxfactura.setBounds(152, 181, 99, 23);
		panel.add(chckbxfactura);
		
		JLabel lblPermisos = new JLabel("Permisos");
		lblPermisos.setBounds(23, 82, 224, 14);
		panel.add(lblPermisos);
		
		tfCodi = new JTextField();
		tfCodi.setBorder(null);
		tfCodi.setVisible(false);
		tfCodi.setBounds(215, 11, 36, 20);
		panel.add(tfCodi);
		tfCodi.setColumns(10);
		
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
		return tfnomDep;
	}

	public void setTfnomDep(JTextField jTextField) {
		this.tfnomDep = jTextField;
	}

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}

	public JCheckBox getChckbxEscola() {
		return chckbxEscola;
	}

	public void setChckbxEscola(JCheckBox chckbxEscola) {
		this.chckbxEscola = chckbxEscola;
	}

	public JCheckBox getChckbxDep() {
		return chckbxDep;
	}

	public void setChckbxDep(JCheckBox chckbxDep) {
		this.chckbxDep = chckbxDep;
	}

	public JCheckBox getChckbxBeca() {
		return chckbxBeca;
	}

	public void setChckbxBeca(JCheckBox chckbxBeca) {
		this.chckbxBeca = chckbxBeca;
	}

	public JCheckBox getChckbxEstudiant() {
		return chckbxEstudiant;
	}

	public void setChckbxEstudiant(JCheckBox chckbxEstudiant) {
		this.chckbxEstudiant = chckbxEstudiant;
	}

	public JCheckBox getChckbxEmpleat() {
		return chckbxEmpleat;
	}

	public void setChckbxEmpleat(JCheckBox chckbxEmpleat) {
		this.chckbxEmpleat = chckbxEmpleat;
	}

	public JCheckBox getChckbxServei() {
		return chckbxServei;
	}

	public void setChckbxServei(JCheckBox chckbxServei) {
		this.chckbxServei = chckbxServei;
	}

	public JCheckBox getChckbxSessio() {
		return chckbxSessio;
	}

	public void setChckbxSessio(JCheckBox chckbxSessio) {
		this.chckbxSessio = chckbxSessio;
	}

	public JCheckBox getChckbxFactura() {
		return chckbxfactura;
	}

	public void setChckbxFactura(JCheckBox chckbxFactura) {
		this.chckbxfactura = chckbxFactura;
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

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
}
