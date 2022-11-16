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
import javax.swing.border.TitledBorder;

import controller.ControllerView;


/**
 * @author Jorsu
 *
 */
public class DepartamentEditForm extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NOM_DEPARTAMENT_ISBLACK = "El nom del departament s'ha d'omplenar";
	
	
	private JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JTextField tfnomDep;
	private JButton okButton, cancelButton;
	private JCheckBox chckbxEscola, chckbxDep, chckbxBeca, chckbxEstudiant, chckbxEmpleat, chckbxServei, chckbxSessio, chckbxInforme;
	private ControllerView controllerView;
	
	

	/**
	 * Create the dialog.
	 */
	public DepartamentEditForm(ControllerView controllerView, Boolean isAlta) {

		setControllerView(controllerView);
		setUndecorated(true);
		setType(Type.POPUP);
		setTitle("Departament");
		setBounds(100, 100, 280, 280);
		getContentPane().setLayout(new CardLayout(0, 0));
		contentPanel.setSize(new Dimension(280, 280));
		contentPanel.setPreferredSize(new Dimension(280, 280));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "name_119289035507000");
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formulari Departament", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 280, 280);
		contentPanel.add(panel);
		panel.setLayout(null);
		

		okButton = new JButton("GUARDAR");
		okButton.setBounds(23, 228, 107, 23);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isAlta) {
					if(getTfnomDep().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_DEPARTAMENT_ISBLACK);
					}else {
						getControllerView().altaDepartament();
						tancarAddForm();
					}
				}else {
					if(getTfnomDep().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_DEPARTAMENT_ISBLACK);
					}else {
						int fila = (int)(controllerView.getMainview().getDepartamentForm().getTable().getSelectedRow());
						int codi = (int)controllerView.getMainview().getDepartamentForm().getTable().getValueAt(fila, 1);
						getControllerView().modiDepartament(codi);	
						tancarAddForm();
					}
				}
			}
		});
		panel.add(okButton);

		
		cancelButton = new JButton("CANCELAR");
		cancelButton.setBounds(144, 228, 107, 23);		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tancarAddForm();
			}
		});
		panel.add(cancelButton);
		

		JLabel lblnomDep = new JLabel("Nom departament");
		lblnomDep.setBounds(23, 33, 228, 14);
		panel.add(lblnomDep);

		tfnomDep = new JTextField();
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
		
		chckbxInforme = new JCheckBox("Informe");
		chckbxInforme.setBounds(152, 181, 99, 23);
		panel.add(chckbxInforme);
		
		JLabel lblPermisos = new JLabel("Permisos");
		lblPermisos.setBounds(23, 82, 224, 14);
		panel.add(lblPermisos);
	}
	
	

	/**
	 *Metode per tancar formulari Edit
	 */
	public void tancarAddForm(){
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

	public JCheckBox getChckbxInforme() {
		return chckbxInforme;
	}

	public void setChckbxInforme(JCheckBox chckbxInforme) {
		this.chckbxInforme = chckbxInforme;
	}
}
