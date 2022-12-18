package view;

import java.awt.CardLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerView;
import model.Departament;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;


/**
 * @author Jordi Subirana
 *
 */
public class EmpleatEditForm extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Short ALTA = 1;
	private static final Short MODIFICAR = 2;
	private static final Short LLEGIR = 3;
	private static final Short MODIUSER = 4;
	private static final String NOM_EMPLEAT_ISBLACK = "El nom del empleat s'ha d'omplenar";
	private static final String DATA_N_EMPLEAT_ISBLACK = "La data de neixament s'ha d'omplenar";
	private static final String DEPARTAMENT_ISBLACK = "L'empleat ha de pertanyer a un departament";
	
	
	private JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JComboBox<String> cbDepts;
	private JButton okButton, cancelButton;
	private JCheckBox ckActiu;
	private ControllerView controllerView;
	private JLabel lblDataNx, lblCognoms, lblTelefon, lblAdreca,lblnom,lblEmail,lblContrasenya,lblUsuari,lblCodi,lblDNI, lblDepartament;
	private JFormattedTextField ftfDNI, ftfDataNa;
	private JTextField tfnom,tfCodi,tfCognoms,tfTelefon,tfAdreca,tfEmail,tfContrasenya,tfUsuari; 
	

	/**
	 * Create the dialog.
	 */
	public EmpleatEditForm(MainView parent, boolean modal,ControllerView controllerView, Short mode) {
		super(parent, modal);		
		
		setResizable(false);
		setLocationRelativeTo(null);

		setControllerView(controllerView);
		setType(Type.UTILITY);
		setTitle("Empleat");
		setBounds(100, 100, 333, 377);
		getContentPane().setLayout(new CardLayout(0, 0));
		contentPanel.setSize(new Dimension(280, 500));
		contentPanel.setPreferredSize(new Dimension(280, 500));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "name_119289035507000");
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setSize(new Dimension(240, 500));
		panel.setPreferredSize(new Dimension(240, 500));
		panel.setBorder(null);
		panel.setBounds(0, 0, 319, 340);
		contentPanel.add(panel);
		panel.setLayout(null);
		

		okButton = new JButton("GUARDAR");
		okButton.setBounds(24, 307, 138, 23);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == ALTA) {
					if(getTfnom().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_EMPLEAT_ISBLACK);
					}else if(getFtfDataNa().getText().isBlank()) {
						controllerView.missatgeIncidencia(DATA_N_EMPLEAT_ISBLACK);
					}else if(cbDepts.getSelectedIndex() == 0) {
						controllerView.missatgeIncidencia(DEPARTAMENT_ISBLACK);
					}else {//Fem l'alta del empleat borrem taula i refresquem
						getControllerView().altaEmpleat();
						getControllerView().getMainview().getEmpleatForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == MODIFICAR || mode == MODIUSER){
					if(getTfnom().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_EMPLEAT_ISBLACK);
					}else if(getFtfDataNa().getText().isBlank()) {
						controllerView.missatgeIncidencia(DATA_N_EMPLEAT_ISBLACK);
					}else if(cbDepts.getSelectedIndex() == 0) {
						controllerView.missatgeIncidencia(DEPARTAMENT_ISBLACK);						
					}else {//Fem modificacio del empleat
						getControllerView().modiEmpleat(Integer.parseInt(tfCodi.getText()), mode);
						if (mode == MODIFICAR) {
							getControllerView().getMainview().getEmpleatForm().recarregarTaula();							
						}
						tancarForm();
					}
				}else if (mode == LLEGIR){ 
					getControllerView().getMainview().getEmpleatForm().llegirEmpleat();
				}
			}
		});
		panel.add(okButton);

		
		cancelButton = new JButton("CANCELAR");
		cancelButton.setBounds(172, 307, 138, 23);		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tancarForm();
			}
		});
		panel.add(cancelButton);
		

		lblnom = new JLabel("Nom");
		lblnom.setBounds(23, 80, 138, 14);
		panel.add(lblnom);

		tfnom = new JTextField();
		tfnom.setBorder(null);
		tfnom.setBounds(23, 97, 138, 20);
		panel.add(tfnom);
		tfnom.setColumns(10);

		ckActiu = new JCheckBox("Actiu");
		ckActiu.setBounds(23, 9, 59, 23);
		panel.add(ckActiu);
		
		tfCodi = new JTextField();
		tfCodi.setBorder(null);
		tfCodi.setEditable(false);
		tfCodi.setHorizontalAlignment(SwingConstants.CENTER);
		tfCodi.setBounds(133, 7, 28, 22);
		panel.add(tfCodi);
		tfCodi.setColumns(10);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(23, 39, 138, 14);
		panel.add(lblDNI);
		
		//https://docs.oracle.com/javase/1.5.0/docs/api/javax/swing/text/MaskFormatter.html
		
		MaskFormatter dataN = null;
		try {
			dataN = new MaskFormatter("####-##-##");
			dataN.setPlaceholder("yyyy-DD-mm");
			dataN.setValidCharacters("0123456789");
		} catch (ParseException e1) {
			e1.printStackTrace();	
		}
		
		ftfDataNa = new JFormattedTextField();
		ftfDataNa.setBorder(null);
		ftfDataNa.setHorizontalAlignment(SwingConstants.LEFT);
		ftfDataNa.setBounds(172, 54, 138, 20);
		panel.add(ftfDataNa);
		
		lblDataNx = new JLabel("Data Naixament");
		lblDataNx.setBounds(172, 39, 137, 14);
		panel.add(lblDataNx);
		
		MaskFormatter dni = null;
		try {
			dni = new MaskFormatter("########?");
			dni.setPlaceholder("00000000A");	
		} catch (ParseException e1) {
			e1.printStackTrace();	
		}		
		
		ftfDNI = new JFormattedTextField(dni);
		ftfDNI.setBorder(null);
		ftfDNI.setHorizontalAlignment(SwingConstants.LEFT);
		ftfDNI.setBounds(23, 54, 138, 20);
		panel.add(ftfDNI);
		
		tfCognoms = new JTextField();
		tfCognoms.setBorder(null);
		tfCognoms.setColumns(10);
		tfCognoms.setBounds(23, 140, 286, 20);
		panel.add(tfCognoms);
		
		lblCognoms = new JLabel("Cognoms");
		lblCognoms.setBounds(23, 126, 228, 14);
		panel.add(lblCognoms);
		
		tfTelefon = new JTextField();
		tfTelefon.setBorder(null);
		tfTelefon.setColumns(10);
		tfTelefon.setBounds(172, 97, 138, 20);
		panel.add(tfTelefon);
		
		lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(172, 82, 137, 14);
		panel.add(lblTelefon);
		
		tfAdreca = new JTextField();
		tfAdreca.setBorder(null);
		tfAdreca.setColumns(10);
		tfAdreca.setBounds(23, 185, 286, 20);
		panel.add(tfAdreca);
		
		lblAdreca = new JLabel("Adreça");
		lblAdreca.setBounds(23, 171, 228, 14);
		panel.add(lblAdreca);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(23, 216, 228, 14);
		panel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBorder(null);
		tfEmail.setColumns(10);
		tfEmail.setBounds(23, 230, 286, 20);
		panel.add(tfEmail);
		
		tfContrasenya = new JTextField();
		tfContrasenya.setBorder(null);
		tfContrasenya.setColumns(10);
		tfContrasenya.setBounds(172, 276, 138, 20);
		panel.add(tfContrasenya);
		
		lblContrasenya = new JLabel("Contrasenya");
		lblContrasenya.setBounds(172, 261, 137, 14);
		panel.add(lblContrasenya);
		
		lblUsuari = new JLabel("Usuari");
		lblUsuari.setBounds(23, 261, 107, 14);
		panel.add(lblUsuari);
		
		tfUsuari = new JTextField();
		tfUsuari.setBorder(null);
		tfUsuari.setColumns(10);
		tfUsuari.setBounds(23, 276, 138, 20);
		panel.add(tfUsuari);
		
		lblCodi = new JLabel("Codi :");
		lblCodi.setBounds(95, 10, 35, 21);
		panel.add(lblCodi);
		
		cbDepts = new JComboBox<String>();
		cbDepts.setBounds(172, 7, 137, 22);
		panel.add(cbDepts);
		
		
		if (mode != MODIUSER) {
			Departament departaments[] = controllerView.getControlOper().llistarDepartament("", "", "");
			cbDepts.removeAllItems();
	
	        //Omplir el combo box
			cbDepts.addItem("<--DEPARTAMENT-->");
	        for(int i = 0; i < departaments.length ; i++ ){
	            cbDepts.addItem(String.valueOf(departaments[i].getCodi()) +"-" + departaments[i].getNomDepartament());
	        }
	        cbDepts.setSelectedIndex(0);
	        
		}else{
			setTitle("Gestio dades usuari");
			cbDepts.setVisible(false);
			lblDepartament = new JLabel("");
			lblDepartament.setBounds(172, 7, 137, 22);
			panel.add(lblDepartament);
		}
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

	public JCheckBox getCkActiu() {
		return ckActiu;
	}

	public void setCkActiu(JCheckBox ckActiu) {
		this.ckActiu = ckActiu;
	}

	public JLabel getLblContrasenya() {
		return lblContrasenya;
	}

	public void setLblContrasenya(JLabel lblContrasenya) {
		this.lblContrasenya = lblContrasenya;
	}

	public JLabel getLblUsuari() {
		return lblUsuari;
	}

	public void setLblUsuari(JLabel lblUsuari) {
		this.lblUsuari = lblUsuari;
	}

	public JFormattedTextField getFtfDNI() {
		return ftfDNI;
	}

	public void setFtfDNI(JFormattedTextField ftfDNI) {
		this.ftfDNI = ftfDNI;
	}

	public JFormattedTextField getFtfDataNa() {
		return ftfDataNa;
	}

	public void setFtfDataNa(JFormattedTextField ftfDataNa) {
		this.ftfDataNa = ftfDataNa;
	}

	public JTextField getTfnom() {
		return tfnom;
	}

	public void setTfnom(JTextField tfnom) {
		this.tfnom = tfnom;
	}

	public JTextField getTfCodi() {
		return tfCodi;
	}

	public void setTfCodi(JTextField tfCodi) {
		this.tfCodi = tfCodi;
	}

	public JTextField getTfCognoms() {
		return tfCognoms;
	}

	public void setTfCognoms(JTextField tfCognoms) {
		this.tfCognoms = tfCognoms;
	}

	public JTextField getTfTelefon() {
		return tfTelefon;
	}

	public void setTfTelefon(JTextField tfTelefon) {
		this.tfTelefon = tfTelefon;
	}

	public JTextField getTfAdreca() {
		return tfAdreca;
	}

	public void setTfAdreca(JTextField tfAdreca) {
		this.tfAdreca = tfAdreca;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JTextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public JTextField getTfContrasenya() {
		return tfContrasenya;
	}
	
	public void setTfContrasenya(JTextField tfContrasenya) {
		this.tfContrasenya = tfContrasenya;
	}

	public JTextField getTfUsuari() {
		return tfUsuari;
	}

	public void setTfUsuari(JTextField tfUsuari) {
		this.tfUsuari = tfUsuari;
	}

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}

	public JComboBox<String> getCbDepts() {
		return cbDepts;
	}

	public void setCbDepts(JComboBox<String> cbDepts) {
		this.cbDepts = cbDepts;
	}

	public JLabel getLblCodi() {
		return lblCodi;
	}

	public void setLblCodi(JLabel lblCodi) {
		this.lblCodi = lblCodi;
	}


	public JLabel getLblDepartament() {
		return lblDepartament;
	}


	public void setLblDepartament(JLabel lblDepartament) {
		this.lblDepartament = lblDepartament;
	}
	

}
