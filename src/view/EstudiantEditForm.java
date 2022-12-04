package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerView;

public class EstudiantEditForm extends JDialog {


	private static final long serialVersionUID = 1L;
	
	private static final Short ALTA = 1;
	private static final Short MODIFICAR = 2;
	private static final Short LLEGIR = 3;
	private static final String NOM_ESTUDIANT_ISBLACK = "El nom del estudiant s'ha d'omplenar";
	private static final String DATA_N_ESTUDIANT_ISBLACK = "La data de neixament s'ha d'omplenar";
	
	
	private JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JButton okButton, cancelButton;
	private JCheckBox ckMatriculat;
	private ControllerView controllerView;
	private JLabel lblDataNx, lblCognoms, lblTelefon, lblAdreca,lblnom,lblEmail,lblCodi, lblDNI;
	private JFormattedTextField ftfDNI, ftfDataNa;
	private JTextField tfnom,tfCodi,tfCognoms,tfTelefon,tfAdreca,tfEmail; 
	

	/**
	 * Create the dialog.
	 */
	public EstudiantEditForm(MainView parent, boolean modal,ControllerView controllerView, Short mode) {
		super(parent, modal);		
		
		setResizable(false);
		setLocationRelativeTo(null);

		setControllerView(controllerView);
		setType(Type.UTILITY);
		setTitle("Estudiant");
		setBounds(100, 100, 280, 364);
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
		panel.setBounds(0, 0, 266, 327);
		contentPanel.add(panel);
		panel.setLayout(null);
		

		okButton = new JButton("GUARDAR");
		okButton.setBounds(23, 287, 107, 23);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == ALTA) {
					if(getTfnom().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_ESTUDIANT_ISBLACK);
					}else if(getFtfDNI().getText().isBlank()) {
						controllerView.missatgeIncidencia(DATA_N_ESTUDIANT_ISBLACK);
					}else {//Fem l'alta del estudiant borrem taula i refresquem
						getControllerView().altaEstudiant();
						getControllerView().getMainview().getEstudiantForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == MODIFICAR){
					if(getTfnom().getText().isBlank()){
						controllerView.missatgeIncidencia(NOM_ESTUDIANT_ISBLACK);
					}else if(getFtfDNI().getText().isBlank()) {
						controllerView.missatgeIncidencia(DATA_N_ESTUDIANT_ISBLACK);
					}else {
						getControllerView().modiEstudiant(Integer.parseInt(getTfCodi().getText()));
						getControllerView().getMainview().getEstudiantForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == LLEGIR){ 
					getControllerView().getMainview().getEstudiantForm().llegirEstudiant();
				}
			}
		});
		panel.add(okButton);

		
		cancelButton = new JButton("CANCELAR");
		cancelButton.setBounds(144, 287, 107, 23);		
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
		tfnom.setBorder(null);
		tfnom.setBounds(23, 95, 107, 20);
		panel.add(tfnom);
		tfnom.setColumns(10);

		ckMatriculat = new JCheckBox("Registrat");
		ckMatriculat.setBounds(23, 257, 90, 23);
		panel.add(ckMatriculat);
		
		tfCodi = new JTextField();
		tfCodi.setBorder(null);
		tfCodi.setEnabled(false);
		tfCodi.setHorizontalAlignment(SwingConstants.CENTER);
		tfCodi.setBounds(52, 8, 78, 20);
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
		ftfDataNa.setBorder(null);
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
		ftfDNI.setBorder(null);
		ftfDNI.setHorizontalAlignment(SwingConstants.CENTER);
		ftfDNI.setBounds(23, 52, 107, 20);
		panel.add(ftfDNI);
		
		tfCognoms = new JTextField();
		tfCognoms.setBorder(null);
		tfCognoms.setColumns(10);
		tfCognoms.setBounds(23, 140, 228, 20);
		panel.add(tfCognoms);
		
		lblCognoms = new JLabel("Cognoms");
		lblCognoms.setBounds(23, 126, 228, 14);
		panel.add(lblCognoms);
		
		tfTelefon = new JTextField();
		tfTelefon.setBorder(null);
		tfTelefon.setColumns(10);
		tfTelefon.setBounds(144, 95, 107, 20);
		panel.add(tfTelefon);
		
		lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(144, 80, 107, 14);
		panel.add(lblTelefon);
		
		tfAdreca = new JTextField();
		tfAdreca.setBorder(null);
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
		tfEmail.setBorder(null);
		tfEmail.setColumns(10);
		tfEmail.setBounds(23, 230, 228, 20);
		panel.add(tfEmail);
		
		lblCodi = new JLabel("Codi");
		lblCodi.setBounds(23, 11, 28, 14);
		panel.add(lblCodi);
		
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

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}

	public JLabel getLblCodi() {
		return lblCodi;
	}

	public void setLblCodi(JLabel lblCodi) {
		this.lblCodi = lblCodi;
	}

	public JCheckBox getCkMatriculat() {
		return ckMatriculat;
	}

	public void setCkMatriculat(JCheckBox ckMatriculat) {
		this.ckMatriculat = ckMatriculat;
	}

}
