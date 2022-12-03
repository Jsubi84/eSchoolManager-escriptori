package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ControllerView;
import model.Estudiant;
import model.Servei;
import javax.swing.JCheckBox;

public class BecaEditForm extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Short ALTA = 1;
	private static final Short MODIFICAR = 2;
	private static final Short LLEGIR = 3;
	private static final String ADJUDICANT_ISBLACK = "L'adjudicant s'ha d'omplenar";
	private static final String SERVEI_ISBLACK = "El servei s'ha d'omplenar";
	private static final String ESTUDIANT_ISBLACK = "L'estudiant s'ha d'omplenar";
	private static final String IMPORT_INI_ISBLACK = "L'import inicial s'ha d'omplenar";
	
	
	private JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JComboBox<String> cbEstudiant, cbServei;
	private JButton okButton, cancelButton;
	private ControllerView controllerView;
	private JLabel lblAjudicant,lblImportIni, lblCodi, lblEstudiant, lblServei, lblImportRestant;
	private JTextField tfCodi,tfAdj, tfImportIni, tfImportRest; 
	JCheckBox ckFinal;
	

	/**
	 * Create the dialog.
	 */
	public BecaEditForm(MainView parent, boolean modal,ControllerView controllerView, Short mode) {
		super(parent, modal);		
		
		setResizable(false);
		setLocationRelativeTo(null);

		setControllerView(controllerView);
		setType(Type.UTILITY);
		setTitle("Beca");
		setBounds(100, 100, 280, 318);
		getContentPane().setLayout(new CardLayout(0, 0));
		contentPanel.setSize(new Dimension(280, 500));
		contentPanel.setPreferredSize(new Dimension(280, 500));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "name");
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setSize(new Dimension(240, 500));
		panel.setPreferredSize(new Dimension(240, 500));
		panel.setBorder(null);
		panel.setBounds(0, 0, 266, 281);
		contentPanel.add(panel);
		panel.setLayout(null);
		

		okButton = new JButton("GUARDAR");
		okButton.setBounds(21, 247, 112, 23);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == ALTA) {
					if(getTfAdj().getText().isBlank()){  
						controllerView.missatgeIncidencia(ADJUDICANT_ISBLACK);
					}else if(getCbServei().getSelectedIndex() == -1) {
						controllerView.missatgeIncidencia(SERVEI_ISBLACK);
					}else if(getCbEstudiant().getSelectedIndex() == -1) {
						controllerView.missatgeIncidencia(ESTUDIANT_ISBLACK);
					}else if(getTfImportIni().getText().isBlank()) {
						controllerView.missatgeIncidencia(IMPORT_INI_ISBLACK);
					}else {//Fem l'alta de beca borrem taula i refresquem
						getControllerView().altaBeca();
						getControllerView().getMainview().getBecaForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == MODIFICAR){
					if(getTfAdj().getText().isBlank()){  
						controllerView.missatgeIncidencia(ADJUDICANT_ISBLACK);
					}else if(getCbServei().getSelectedIndex() == -1) {
						controllerView.missatgeIncidencia(SERVEI_ISBLACK);
					}else if(getCbEstudiant().getSelectedIndex() == -1) {
						controllerView.missatgeIncidencia(ESTUDIANT_ISBLACK);
					}else if(getTfImportIni().getText().isBlank()) {
						controllerView.missatgeIncidencia(IMPORT_INI_ISBLACK);
					}else {//Fem modificacio del beca
						getControllerView().modiBeca(Integer.parseInt(tfCodi.getText()));						
						getControllerView().getMainview().getBecaForm().recarregarTaula();							
						tancarForm();
					}
				}else if (mode == LLEGIR){ 
					getControllerView().getMainview().getBecaForm().llegirBeca();
				}
			}
		});
		panel.add(okButton);

		
		cancelButton = new JButton("CANCELAR");
		cancelButton.setBounds(137, 247, 112, 23);		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tancarForm();
			}
		});
		panel.add(cancelButton);
		
		tfCodi = new JTextField();
		tfCodi.setVisible(false);
		tfCodi.setEnabled(false);
		tfCodi.setHorizontalAlignment(SwingConstants.CENTER);
		tfCodi.setBounds(21, 211, 112, 20);
		panel.add(tfCodi);
		tfCodi.setColumns(10);
		
		tfAdj = new JTextField();
		tfAdj.setColumns(10);
		tfAdj.setBounds(21, 120, 228, 20);
		panel.add(tfAdj);
		
        tfImportIni = new JTextField();
        tfImportIni.setHorizontalAlignment(SwingConstants.CENTER);
        tfImportIni.setColumns(10);
        tfImportIni.setBounds(21, 166, 112, 20);
        panel.add(tfImportIni);		
        
        tfImportRest = new JTextField();
        tfImportRest.setVisible(false);
        tfImportRest.setHorizontalAlignment(SwingConstants.CENTER);
        tfImportRest.setColumns(10);
        tfImportRest.setBounds(137, 166, 112, 20);
        panel.add(tfImportRest);
        
		
		lblAjudicant = new JLabel("Adjudicant");
		lblAjudicant.setBounds(21, 105, 228, 14);
		panel.add(lblAjudicant);
		
		lblImportIni = new JLabel("Import inicial");
		lblImportIni.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportIni.setBounds(21, 151, 112, 14);
		panel.add(lblImportIni);
		
		lblCodi = new JLabel("Codi");
		lblCodi.setVisible(false);
		lblCodi.setBounds(21, 197, 112, 14);
		panel.add(lblCodi);
		
        lblEstudiant = new JLabel("Estudiant");
        lblEstudiant.setBounds(21, 11, 228, 14);
        panel.add(lblEstudiant);
        
        lblServei = new JLabel("Servei");
        lblServei.setBounds(21, 58, 228, 14);
        panel.add(lblServei);		
		
        lblImportRestant = new JLabel("Import Restant");
        lblImportRestant.setVisible(false);
        lblImportRestant.setHorizontalAlignment(SwingConstants.CENTER);
        lblImportRestant.setBounds(137, 151, 112, 14);
        panel.add(lblImportRestant);
        
        ckFinal = new JCheckBox("Finalitzada");
        ckFinal.setVisible(false);
        ckFinal.setBounds(137, 207, 112, 23);
        panel.add(ckFinal);
        
        cbServei = new JComboBox<String>();
        cbServei.setSelectedIndex(-1);
        cbServei.setBounds(21, 72, 228, 22);
        panel.add(cbServei);        
        
		cbEstudiant = new JComboBox<String>();
		cbServei.setSelectedIndex(-1);
		cbEstudiant.setBounds(21, 25, 228, 22);
		panel.add(cbEstudiant);
		
		
		//COMBOBOX PER ESTUDIANT
		Estudiant estudiants[] = controllerView.getControlOper().llistarEstudiant("", "", "");
		cbEstudiant.removeAllItems();

        //Omplir el combo box amb els estudiants
        for(int i = 0; i < estudiants.length ; i++ ){
            cbEstudiant.addItem(String.valueOf(estudiants[i].getCodi()) +"-"+ estudiants[i].getNom()
            		+" "+ estudiants[i].getCognoms());}
        cbEstudiant.setSelectedIndex(-1);
        
        
        //COMBOBOX PER SERVEI
		Servei serveis[] = controllerView.getControlOper().llistarServei("", "", "");
		cbServei.removeAllItems();

        //Omplir el combo box amb el servei
        for(int i = 0; i < serveis.length ; i++ ){
            cbServei.addItem(String.valueOf(serveis[i].getCodi()) +"-"+ serveis[i].getNom());}
        cbServei.setSelectedIndex(-1);
    
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

	public JLabel getLblUsuari() {
		return lblImportIni;
	}

	public void setLblUsuari(JLabel lblUsuari) {
		this.lblImportIni = lblUsuari;
	}

	public JTextField getTfCodi() {
		return tfCodi;
	}

	public void setTfCodi(JTextField tfCodi) {
		this.tfCodi = tfCodi;
	}

	public JTextField getTfAdreca() {
		return tfAdj;
	}

	public void setTfAdreca(JTextField tfAdreca) {
		this.tfAdj = tfAdreca;
	}

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}

	public JComboBox<String> getCbDepts() {
		return cbEstudiant;
	}

	public void setCbDepts(JComboBox<String> cbDepts) {
		this.cbEstudiant = cbDepts;
	}

	public JLabel getLblCodi() {
		return lblCodi;
	}

	public void setLblCodi(JLabel lblCodi) {
		this.lblCodi = lblCodi;
	}

	public JComboBox<String> getCbEstudiant() {
		return cbEstudiant;
	}

	public void setCbEstudiant(JComboBox<String> cbEstudiant) {
		this.cbEstudiant = cbEstudiant;
	}

	public JComboBox<String> getCbServei() {
		return cbServei;
	}


	public void setCbServei(JComboBox<String> cbServei) {
		this.cbServei = cbServei;
	}

	public JTextField getTfAdj() {
		return tfAdj;
	}

	public void setTfAdj(JTextField tfAdj) {
		this.tfAdj = tfAdj;
	}


	public JTextField getTfImportIni() {
		return tfImportIni;
	}

	public void setTfImportIni(JTextField tfImportIni) {
		this.tfImportIni = tfImportIni;
	}

	public JLabel getLblImportRestant() {
		return lblImportRestant;
	}

	public void setLblImportRestant(JLabel lblImportRestant) {
		this.lblImportRestant = lblImportRestant;
	}

	public JTextField getTfImportRest() {
		return tfImportRest;
	}

	public void setTfImportRest(JTextField tfImportRest) {
		this.tfImportRest = tfImportRest;
	}

	public JCheckBox getCkFinal() {
		return ckFinal;
	}

	public void setCkFinal(JCheckBox ckFinal) {
		this.ckFinal = ckFinal;
	}
	
}
