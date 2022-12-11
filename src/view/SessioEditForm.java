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
import model.Empleat;
import model.Estudiant;
import model.Login;
import model.Servei;

public class SessioEditForm extends JDialog {


	private static final long serialVersionUID = 1L;
	
	private static final Short ALTA = 1;
	private static final Short MODIFICAR = 2;
	private static final Short LLEGIR = 3;
	private static final String PROFESSOR_ISBLACK = "No has seleccionat un professor";
	private static final String SERVEI_ISBLACK = "No has seleccionat un servei";
	private static final String ESTUDIANT_ISBLACK = "No has seleccionat un estudiant";
	private static final String DATA_ISBLACK = "S'ha de posar una data";
	private static final String HORA_ISBLACK = "S'ha de posar una hora";	
	
	
	private JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JComboBox<String> cbProfe, cbEstudiant, cbServei;
	private JButton okButton, cancelButton;
	private ControllerView controllerView;
	private JLabel lblServei,lblData, lblCodi, lblProfessor, lblEstudiant, lblHora;
	private JTextField tfCodi, tfData, tfHora; 
	

	/**
	 * Create the dialog.
	 */
	public SessioEditForm(MainView parent, boolean modal,ControllerView controllerView, Short mode) {
		super(parent, modal);		
		
		setResizable(false);
		setLocationRelativeTo(null);

		setControllerView(controllerView);
		setType(Type.UTILITY);
		setTitle("Sessio");
		setBounds(100, 100, 273, 271);
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
		panel.setBounds(0, 0, 259, 234);
		contentPanel.add(panel);
		panel.setLayout(null);
		

		okButton = new JButton("GUARDAR");
		okButton.setBounds(16, 197, 112, 23);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == ALTA) {
					if(getCbProfe().getSelectedIndex() == -1){  
						controllerView.missatgeIncidencia(PROFESSOR_ISBLACK);
					}else if(getCbServei().getSelectedIndex() == -1) {
						controllerView.missatgeIncidencia(SERVEI_ISBLACK);
					}else if(getCbEstudiant().getSelectedIndex() == -1) {
						controllerView.missatgeIncidencia(ESTUDIANT_ISBLACK);
					}else if(getTfData().getText().isBlank()) {
						controllerView.missatgeIncidencia(DATA_ISBLACK);
					}else if(getTfHora().getText().isBlank()) {
						controllerView.missatgeIncidencia(HORA_ISBLACK);
					}else {//Fem l'alta de beca borrem taula i refresquem
						getControllerView().altaSessio();
						getControllerView().getMainview().getSessioForm().recarregarTaula();
						tancarForm();
					}
				}else if (mode == MODIFICAR){
					if(getCbProfe().getSelectedIndex() == -1){  
						controllerView.missatgeIncidencia(PROFESSOR_ISBLACK);
					}else if(getCbServei().getSelectedIndex() == -1) {
						controllerView.missatgeIncidencia(SERVEI_ISBLACK);
					}else if(getCbEstudiant().getSelectedIndex() == -1) {
						controllerView.missatgeIncidencia(ESTUDIANT_ISBLACK);
					}else if(getTfData().getText().isBlank()) {
						controllerView.missatgeIncidencia(DATA_ISBLACK);
					}else if(getTfHora().getText().isBlank()) {
						controllerView.missatgeIncidencia(HORA_ISBLACK);
					}else {//Fem modificacio del beca
						getControllerView().modiSessio(Integer.parseInt(tfCodi.getText()));						
						getControllerView().getMainview().getSessioForm().recarregarTaula();							
						tancarForm();
					}
				}else if (mode == LLEGIR){ 
					getControllerView().getMainview().getSessioForm().llegirSessio();
				}
			}
		});
		panel.add(okButton);

		
		cancelButton = new JButton("CANCELAR");
		cancelButton.setBounds(132, 197, 112, 23);		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tancarForm();
			}
		});
		panel.add(cancelButton);
		
		tfCodi = new JTextField();
		tfCodi.setEditable(false);
		tfCodi.setVisible(false);
		tfCodi.setBorder(null);
		tfCodi.setHorizontalAlignment(SwingConstants.CENTER);
		tfCodi.setBounds(16, 27, 79, 20);
		panel.add(tfCodi);
		tfCodi.setColumns(10);
		
        tfData = new JTextField();
        tfData.setBorder(null);
        tfData.setHorizontalAlignment(SwingConstants.CENTER);
        tfData.setColumns(10);
        tfData.setBounds(16, 166, 112, 20);
        panel.add(tfData);		
        
        tfHora = new JTextField();
        tfHora.setBorder(null);
        tfHora.setHorizontalAlignment(SwingConstants.CENTER);
        tfHora.setColumns(10);
        tfHora.setBounds(132, 166, 112, 20);
        panel.add(tfHora);
        
		
		lblServei = new JLabel("Servei");
		lblServei.setBounds(16, 105, 228, 14);
		panel.add(lblServei);
		
		lblData = new JLabel("Data");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBounds(16, 151, 112, 14);
		panel.add(lblData);
		
		lblCodi = new JLabel("Codi");
		lblCodi.setVisible(false);
		lblCodi.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodi.setBounds(16, 13, 79, 14);
		panel.add(lblCodi);
		
        lblProfessor = new JLabel("Professor");
        lblProfessor.setBounds(124, 11, 120, 14);
        panel.add(lblProfessor);
        
        lblEstudiant = new JLabel("Estudiant");
        lblEstudiant.setBounds(16, 58, 228, 14);
        panel.add(lblEstudiant);		
		
        lblHora = new JLabel("Hora");
        lblHora.setHorizontalAlignment(SwingConstants.CENTER);
        lblHora.setBounds(132, 151, 112, 14);
        panel.add(lblHora);
        
        cbEstudiant = new JComboBox<String>();
		cbEstudiant.setBorder(null);        
        cbEstudiant.setSelectedIndex(-1);
        cbEstudiant.setBounds(16, 72, 228, 22);
        panel.add(cbEstudiant);        
        
		cbProfe = new JComboBox<String>();
		cbProfe.setBorder(null);
		cbProfe.setSelectedIndex(-1);
		cbProfe.setBounds(124, 25, 120, 22);
		panel.add(cbProfe);
		
        cbServei = new JComboBox<String>();
        cbServei.setBorder(null);
        cbServei.setSelectedIndex(-1);
        cbServei.setBounds(16, 118, 228, 22);
        panel.add(cbServei);
		
		
        //COMBOBOX PER PROFESSOR
		Login login = controllerView.getControlOper().getLogin();
        if (checkSessionOnly(login)) {
        	cbProfe.setEnabled(false);
        }else{
        	Empleat empleats[] = controllerView.getControlOper().llistarEmpleat("codiDepartament", "17", "");
			cbProfe.removeAllItems();        	
        	
	        //Omplir el combo box amb els professor
	        for(int i = 0; i < empleats.length ; i++ ){
	            cbProfe.addItem(String.valueOf(empleats[i].getCodi()) +"-"+ empleats[i].getNom()
	            		+" "+ empleats[i].getCognoms());}
	        cbProfe.setSelectedIndex(-1);        	
        }
        
        
        //COMBOBOX PER ESTUDIANT
		Estudiant estudiants[] = controllerView.getControlOper().llistarEstudiant("registrat", "true", "");
		cbEstudiant.removeAllItems();

        //Omplir el combo box amb els professor
        for(int i = 0; i < estudiants.length ; i++ ){
            cbEstudiant.addItem(String.valueOf(estudiants[i].getCodi()) +"-"+ estudiants[i].getNom()
            		+" "+ estudiants[i].getCognoms());}
        cbEstudiant.setSelectedIndex(-1);
       
        
        //COMBOBOX PER SERVEI
		Servei serveis[] = controllerView.getControlOper().llistarServei("", "", "");
		cbServei.removeAllItems();

        //Omplir el combo box amb el estudiant
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
	
	public JComboBox<String> getCbProfe() {
		return cbProfe;
	}

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}

	public void setCbProfe(JComboBox<String> cbProfe) {
		this.cbProfe = cbProfe;
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

	public JLabel getLblCodi() {
		return lblCodi;
	}

	public void setLblCodi(JLabel lblCodi) {
		this.lblCodi = lblCodi;
	}

	public JTextField getTfCodi() {
		return tfCodi;
	}

	public void setTfCodi(JTextField tfCodi) {
		this.tfCodi = tfCodi;
	}

	public JTextField getTfData() {
		return tfData;
	}

	public void setTfData(JTextField tfData) {
		this.tfData = tfData;
	}

	public JTextField getTfHora() {
		return tfHora;
	}

	public void setTfHora(JTextField tfHora) {
		this.tfHora = tfHora;
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
	
	
	private Boolean checkSessionOnly(Login login){
		Boolean sOnly = true;
		
		if (login.getpBeca()) {
			sOnly = false;
		}else if (login.getpDepartament()) {
			sOnly = false;
		}else if (login.getpEmpleat()) {
			sOnly = false;
		}else if (login.getpEscola()) {
			sOnly = false;
		}else if (login.getpServei()) {
			sOnly = false;
		}else if (login.getpEstudiant()){
			sOnly = false;
		}else if (login.getpFactura()) {
			sOnly = false;
		}
		return sOnly;
	}
	
}
