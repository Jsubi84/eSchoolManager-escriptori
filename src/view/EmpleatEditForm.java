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
	private static final String NOM_EMPLEAT_ISBLACK = "El nom del empleat s'ha d'omplenar";
	private static final String DATA_N_EMPLEAT_ISBLACK = "La data de neixament s'ha d'omplenar";
	
	
	private JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JComboBox<String> cbDepts;
	private JButton okButton, cancelButton;
	private JCheckBox ckActiu;
	private ControllerView controllerView;
	private JLabel lblDataNx, lblCognoms, lblTelefon, lblAdreca,lblnom,lblEmail,lblContrasenya,lblUsuari,lblCodi,lblDNI;
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
		setBounds(100, 100, 280, 400);
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
		panel.setBounds(0, 0, 266, 363);
		contentPanel.add(panel);
		panel.setLayout(null);
		

		okButton = new JButton("GUARDAR");
		okButton.setBounds(23, 329, 107, 23);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == ALTA) {
					if(getTfnom().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_EMPLEAT_ISBLACK);
					}else if(getFtfDNI().getText().isBlank()) {
						controllerView.missatgeIncidencia(DATA_N_EMPLEAT_ISBLACK);
					}else {//Fem l'alta del empleat borrem taula i refresquem
						getControllerView().altaEmpleat();
						getControllerView().getMainview().getEmpleatForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == MODIFICAR){
					if(getTfnom().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_EMPLEAT_ISBLACK);
					}else if(getFtfDNI().getText().isBlank()) {
						controllerView.missatgeIncidencia(DATA_N_EMPLEAT_ISBLACK);
					}else {
						getControllerView().modiEmpleat(Integer.parseInt(getTfCodi().getText()));
						getControllerView().getMainview().getEmpleatForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == LLEGIR){ 
					getControllerView().getMainview().getEmpleatForm().llegirEmpleat();
				}
			}
		});
		panel.add(okButton);

		
		cancelButton = new JButton("CANCELAR");
		cancelButton.setBounds(144, 329, 107, 23);		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tancarForm();
			}
		});
		panel.add(cancelButton);
		

		lblnom = new JLabel("Nom");
		lblnom.setBounds(23, 80, 107, 14);
		panel.add(lblnom);

		tfnom = new JTextField();
		tfnom.setBounds(23, 95, 107, 20);
		panel.add(tfnom);
		tfnom.setColumns(10);

		ckActiu = new JCheckBox("Actiu");
		ckActiu.setBounds(87, 7, 51, 23);
		panel.add(ckActiu);
		
		tfCodi = new JTextField();
		tfCodi.setEnabled(false);
		tfCodi.setHorizontalAlignment(SwingConstants.CENTER);
		tfCodi.setBounds(52, 8, 28, 20);
		panel.add(tfCodi);
		tfCodi.setColumns(10);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(23, 39, 107, 14);
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
		ftfDataNa.setHorizontalAlignment(SwingConstants.CENTER);
		ftfDataNa.setBounds(144, 52, 107, 20);
		panel.add(ftfDataNa);
		
		lblDataNx = new JLabel("Data Naixament");
		lblDataNx.setBounds(144, 37, 107, 14);
		panel.add(lblDataNx);
		
		MaskFormatter dni = null;
		try {
			dni = new MaskFormatter("########?");
			dni.setPlaceholder("00000000A");	
		} catch (ParseException e1) {
			e1.printStackTrace();	
		}		
		
		ftfDNI = new JFormattedTextField(dni);
		ftfDNI.setHorizontalAlignment(SwingConstants.CENTER);
		ftfDNI.setBounds(23, 52, 107, 20);
		panel.add(ftfDNI);
		
		tfCognoms = new JTextField();
		tfCognoms.setColumns(10);
		tfCognoms.setBounds(23, 140, 228, 20);
		panel.add(tfCognoms);
		
		lblCognoms = new JLabel("Cognoms");
		lblCognoms.setBounds(23, 126, 228, 14);
		panel.add(lblCognoms);
		
		tfTelefon = new JTextField();
		tfTelefon.setColumns(10);
		tfTelefon.setBounds(144, 95, 107, 20);
		panel.add(tfTelefon);
		
		lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(144, 80, 107, 14);
		panel.add(lblTelefon);
		
		tfAdreca = new JTextField();
		tfAdreca.setColumns(10);
		tfAdreca.setBounds(23, 185, 228, 20);
		panel.add(tfAdreca);
		
		lblAdreca = new JLabel("Adreça");
		lblAdreca.setBounds(23, 171, 228, 14);
		panel.add(lblAdreca);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(23, 216, 228, 14);
		panel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(23, 230, 228, 20);
		panel.add(tfEmail);
		
		tfContrasenya = new JTextField();
		tfContrasenya.setColumns(10);
		tfContrasenya.setBounds(144, 276, 107, 20);
		panel.add(tfContrasenya);
		
		lblContrasenya = new JLabel("Contrasenya");
		lblContrasenya.setBounds(144, 261, 99, 14);
		panel.add(lblContrasenya);
		
		lblUsuari = new JLabel("Usuari");
		lblUsuari.setBounds(23, 261, 107, 14);
		panel.add(lblUsuari);
		
		tfUsuari = new JTextField();
		tfUsuari.setColumns(10);
		tfUsuari.setBounds(23, 276, 107, 20);
		panel.add(tfUsuari);
		
		lblCodi = new JLabel("Codi");
		lblCodi.setBounds(23, 11, 28, 14);
		panel.add(lblCodi);
		
		cbDepts = new JComboBox<String>();
		cbDepts.setBounds(144, 7, 107, 22);
		panel.add(cbDepts);
		
		Departament departaments[] = controllerView.getControlOper().llistarDepartament("", "", "");
		cbDepts.removeAllItems();

        //Omplir el combo box
        for(int i = 0; i < departaments.length ; i++ ){
            cbDepts.addItem(String.valueOf(departaments[i].getCodi()) +"-" + departaments[i].getNomDepartament());
        }
 
        cbDepts.setSelectedIndex(departaments.length-1);
		
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
	
}
