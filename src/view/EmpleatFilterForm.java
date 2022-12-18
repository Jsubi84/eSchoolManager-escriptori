package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerView;
import model.Empleat;
import util.Convert;

/**
 * @author Jordi Subirana
 *
 */

public class EmpleatFilterForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Short ALTA = 1;
	private static final Short MODI = 2;
	private static final Short LLEGIR = 3;
	private static final Short MODIUSER = 4;
	private static final String NO_REGISTRE_PER_LLEGIR = "No hi ha registre seleccionat per poder llegir";
	private static final String NO_REGISTRE_PER_ACTUALITZAR = "No hi ha registre seleccionat per poder actualitzar";
	private static final String NO_REGISTRE_BAIXA = "No hi ha registre seleccionat per donar de baixa";
	private static final String NO_TROBAT = "No s'ha trobat aquest empleat";
	
	private JTable table;
	EmpleatEditForm empleatEditForm;
	private ControllerView controllerView;
	DefaultTableModel model;
	private JTextField tfValor;
	
	private JComboBox<String> cbCamp, cbOrdre;
	private String[] campCombo = {"Codi del empleat","DNI", "Nom", "Cognoms","Telefont", "E-mail", "Adreça", "Codi del departament"};
	private String[] camp = {" ","codi","dni","nom", "cognoms", "telefon", "email", "adreca", "codiDepartament"};
	private String[] ordre = {"DESC","ASC"};

	/**
	 * Create the panel.
	 */
	public EmpleatFilterForm(ControllerView controllerView) {
		
		setControllerView(controllerView);
		setPreferredSize(new Dimension(818, 524));
		setSize(new Dimension(818, 524));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(60, 60));
		panel.setPreferredSize(new Dimension(60, 60));
		panel.setBounds(0, 0, 818, 524);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		
		JLabel lblTitolDepartament = new JLabel("Empleat");
		lblTitolDepartament.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblTitolDepartament.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolDepartament.setFont(new Font("Dubai", Font.BOLD, 21));
		lblTitolDepartament.setBounds(10, 11, 798, 41);
		panel.add(lblTitolDepartament);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 181, 818, 343);
		panel.add(scrollPane);
		
		// Posar header i dades buides i fa que no siguin editables
		model = new DefaultTableModel(new Object[][] {}, 
				new String[] { "ID","Nom","Cognoms", "Codi Departament","Nom Departament"}) {
	
			private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	
		table = new JTable(model);
		table.setBounds(0, 181, 818, 343);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaEmpleat();
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setFocusable(false);
		btnAdd.setBorder(null);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setPreferredSize(new Dimension(40, 40));
		btnAdd.setSize(new Dimension(40, 40));
		btnAdd.setBounds(644, 130, 40, 40);
		panel.add(btnAdd);
		btnAdd.setIcon(setIcons("/pictures/add.png", btnAdd));
		
		JButton btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarEmpleat();
			}
		});
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdit.setSize(new Dimension(40, 40));
		btnEdit.setPreferredSize(new Dimension(40, 40));
		btnEdit.setFocusable(false);
		btnEdit.setBorder(null);
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(706, 130, 40, 40);
		panel.add(btnEdit);
		btnEdit.setIcon(setIcons("/pictures/edit.png", btnEdit));
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baixaEmpleat();
			}
		});
		
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setSize(new Dimension(40, 40));
		btnDelete.setPreferredSize(new Dimension(40, 40));
		btnDelete.setFocusable(false);
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(768, 130, 40, 40);
		panel.add(btnDelete);
		btnDelete.setIcon(setIcons("/pictures/remove.png", btnDelete));
		
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 llegirEmpleat();
			}
		});
		
		btnSearch.setSize(new Dimension(40, 40));
		btnSearch.setPreferredSize(new Dimension(40, 40));
		btnSearch.setFocusable(false);
		btnSearch.setBorder(null);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(10, 130, 40, 40);
		btnSearch.setIcon(setIcons("/pictures/search.png", btnSearch));		
		panel.add(btnSearch);
		
		
		JButton btnFilter = new JButton("");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarEmpleat();
			}
		});
		
		btnFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFilter.setSize(new Dimension(20, 20));
		btnFilter.setPreferredSize(new Dimension(20, 20));
		btnFilter.setFocusable(false);
		btnFilter.setBorder(null);
		btnFilter.setBackground(Color.WHITE);
		btnFilter.setBounds(472, 76, 20, 20);
		btnFilter.setIcon(setIcons("/pictures/filter.png", btnFilter));	
		panel.add(btnFilter);
		
		cbCamp = new JComboBox<String>();
		cbCamp.setSelectedIndex(-1);
		cbCamp.setBorder(null);
		cbCamp.setBounds(10, 74, 160, 22);
		panel.add(cbCamp);
		
        //Omplir el combo box amb els camps
		cbCamp.addItem("");
        for(int i = 0; i < campCombo.length ; i++ ){
            cbCamp.addItem(campCombo[i]);}
        cbCamp.setSelectedIndex(0);
		
        tfValor = new JTextField();
		tfValor.setColumns(10);
		tfValor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tfValor.setBounds(180, 74, 202, 22);
		panel.add(tfValor);
		
		cbOrdre = new JComboBox<String>();
		cbOrdre.setSelectedIndex(-1);
		cbOrdre.setBorder(null);
		cbOrdre.setBounds(392, 74, 70, 22);
		panel.add(cbOrdre);
		
        //Omplir el combo box amb els ordre
		cbOrdre.addItem("");
        for(int i = 0; i < ordre.length ; i++ ){
            cbOrdre.addItem(ordre[i]);}
        cbOrdre.setSelectedIndex(0);
		
		JLabel lblCamp = new JLabel("Camp a filtrar");
		lblCamp.setBounds(10, 56, 160, 20);
		panel.add(lblCamp);
		
		JLabel lblValor = new JLabel("Valor a trobar");
		lblValor.setBounds(180, 56, 202, 20);
		panel.add(lblValor);
		
		JLabel lblOrdre = new JLabel("Ordre");
		lblOrdre.setBounds(392, 56, 70, 20);
		panel.add(lblOrdre);
	}

	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	public EmpleatEditForm getEmpleatEditForm() {
		return empleatEditForm;
	}

	public void setEmpleatEditForm(EmpleatEditForm empleatEditForm) {
		this.empleatEditForm = empleatEditForm;
	}


	/**
	 * Escalat dels icones dels botons de selecció
	 * @param url
	 * @param button
	 */
	public Icon setIcons (String url, JButton button) {	
	    ImageIcon imgIcon = new ImageIcon(getClass().getResource(url));
	    Image imgEscalada = imgIcon.getImage().getScaledInstance(button.getHeight(),
	            button.getHeight(), Image.SCALE_SMOOTH);
	    Icon icon = new ImageIcon(imgEscalada);
	    return icon;
	}
	
	
	
	/**
	 * Metode per donar d'alta un empleat nou.
	 */
	public void altaEmpleat() {
		empleatEditForm= new EmpleatEditForm(controllerView.getMainview(), true, controllerView, ALTA);
		empleatEditForm.setLocationRelativeTo(null);
		empleatEditForm.getLblCodi().setVisible(false);
		empleatEditForm.getTfCodi().setVisible(false);
		empleatEditForm.getCkActiu().setVisible(false);
		empleatEditForm.setVisible(true);
	}
	
	/**
	 * Metode per donar de baixa l'item seleccionat a la taula.
	 */
	public void baixaEmpleat() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);			
			int seleccion = JOptionPane.showOptionDialog(
					   this,
					   "Estar segur que vols borrar el registre d'Empleat amb codi " + idSelec + "  ",
					   "Borrar Registre",
					   JOptionPane.YES_NO_OPTION,
					   JOptionPane.QUESTION_MESSAGE,
					   new Convert().returIcon("/pictures/alert.png"), // icon d'advertencia
					   new Object[] { "Acceptar", "Cancelar"},
					   "Acceptar");

			if (seleccion == 0) {
				controllerView.baixaEmpleat(idSelec);
				recarregarTaula();				
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia(NO_REGISTRE_BAIXA);
		}
	}
	
	/**
	 * Metode per modificar l'item seleccionat a la taula
	 */
	public void modificarEmpleat() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);		
			String nomDepartament = (String) getTable().getValueAt(getTable().getSelectedRow(), 4);
			// {"codiEmpleat","dni","nom","cognoms","dataNeixement","adreca", "telefon","email","codiDepartament","contrasenya","actiu"}
			// Fer la consulta individual per omplir les dades
			empleatEditForm= new EmpleatEditForm(controllerView.getMainview(),true, controllerView, MODI);
			empleatEditForm.setLocationRelativeTo(null);
			Empleat emp = controllerView.consultaIndEmpleat(idSelec);
			if (emp != null) {
				empleatEditForm.getTfCodi().setText(String.valueOf(emp.getCodi()));
				empleatEditForm.getFtfDNI().setText(emp.getDni());
				empleatEditForm.getTfnom().setText(emp.getNom());
				empleatEditForm.getTfCognoms().setText(emp.getCognoms());
				empleatEditForm.getFtfDataNa().setText(emp.getDataNaixament());
				empleatEditForm.getTfAdreca().setText(emp.getAdreca());
				empleatEditForm.getTfTelefon().setText(emp.getTelefon());
				empleatEditForm.getTfEmail().setText(emp.getEmail());
				empleatEditForm.getCbDepts().setSelectedIndex(retIndexCombo(
						emp.getCodiDepartament() +"-" + nomDepartament, empleatEditForm.getCbDepts()));
				empleatEditForm.getTfUsuari().setText(emp.getUsuari());
				empleatEditForm.getTfContrasenya().setText("");
				empleatEditForm.getCkActiu().setSelected(emp.getActiu());
				
				// Un cop carregat el formulari el fem visible
				empleatEditForm.setVisible(true);				
			}else {
				controllerView.missatgeErrorIncidencia(NO_TROBAT);
				empleatEditForm.dispose();
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia(NO_REGISTRE_PER_ACTUALITZAR);
			empleatEditForm.dispose();
		}
	}
	
	
	/**
	 * Metode per llistar els diferents items a la taula en forma de fila.
	 */	
	public void modificarEmpleatPropi(int codiEmpleatPropi) {
		// {"codiEmpleat","dni","nom","cognoms","dataNeixement","adreca", "telefon","email","codiDepartament","contrasenya","actiu"}
		// Fer la consulta individual per omplir les dades
		empleatEditForm= new EmpleatEditForm(controllerView.getMainview(),true, controllerView, MODIUSER);
		empleatEditForm.setLocationRelativeTo(null);
		Empleat emp = controllerView.consultaIndEmpleat(codiEmpleatPropi);
		if (emp != null) {
			empleatEditForm.getTfCodi().setText(String.valueOf(emp.getCodi()));
			empleatEditForm.getTfCodi().setEditable(false);
			empleatEditForm.getFtfDNI().setText(emp.getDni());
			empleatEditForm.getTfnom().setText(emp.getNom());
			empleatEditForm.getTfCognoms().setText(emp.getCognoms());
			empleatEditForm.getFtfDataNa().setText(emp.getDataNaixament());
			empleatEditForm.getTfAdreca().setText(emp.getAdreca());
			empleatEditForm.getTfTelefon().setText(emp.getTelefon());
			empleatEditForm.getTfEmail().setText(emp.getEmail());
			empleatEditForm.getCbDepts().setSelectedIndex(retIndexCombo(
					emp.getCodiDepartament() +"-" + emp.getNomDep(), empleatEditForm.getCbDepts()));
			empleatEditForm.getCbDepts().setEnabled(false);			
			empleatEditForm.getTfUsuari().setText(emp.getUsuari());
			empleatEditForm.getTfContrasenya().setText("");
			empleatEditForm.getCkActiu().setSelected(emp.getActiu());
			empleatEditForm.getCkActiu().setEnabled(false);
			
			// Un cop carregat el formulari el fem visible
			empleatEditForm.setVisible(true);				
		}else {
			controllerView.missatgeErrorIncidencia(NO_TROBAT);
			empleatEditForm.dispose();
		}		
	}
	
	
	/**
	 * Metode per llistar els diferents items a la taula en forma de fila.
	 */
	public void llistarEmpleat() {
		borrarTaulaEmpleat();
		controllerView.llistarEmpleat();
	}
	
	
	/**
	 * Metode per filtar els registres  
	 *
	 */	
	public void filtrarEmpleat(){
		borrarTaulaEmpleat();
		cbCamp.getSelectedIndex();
		String campF = camp[cbCamp.getSelectedIndex()];
		String valor = tfValor.getText();
		String ordre = (String)cbOrdre.getSelectedItem();
		getControllerView().filtrarEmpleat(campF, valor, ordre);
	}
	
	
	/**
	 * Metode per llegir l'item seleccionat a la taula
	 */
	public void llegirEmpleat() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);
			String nomDepartament = (String) getTable().getValueAt(getTable().getSelectedRow(), 4);
			// {"codiEmpleat","dni","nom","cognoms","dataNeixement","adreca", "telefon","email","codiDepartament","contrasenya","actiu"}
			// Fer la consulta individual per omplir les dades
			empleatEditForm= new EmpleatEditForm(controllerView.getMainview(),true ,controllerView, LLEGIR);
			empleatEditForm.setLocationRelativeTo(null);
			Empleat emp = controllerView.consultaIndEmpleat(idSelec);
			if (emp != null) {
				empleatEditForm.getTfCodi().setText(String.valueOf(emp.getCodi()));
				empleatEditForm.getFtfDNI().setText(emp.getDni());
				empleatEditForm.getTfnom().setText(emp.getNom());
				empleatEditForm.getTfCognoms().setText(emp.getCognoms());
				empleatEditForm.getFtfDataNa().setText(emp.getDataNaixament());
				empleatEditForm.getTfAdreca().setText(emp.getAdreca());
				empleatEditForm.getTfTelefon().setText(emp.getTelefon());
				empleatEditForm.getTfEmail().setText(emp.getEmail());
				empleatEditForm.getCbDepts().setSelectedIndex(retIndexCombo(
						emp.getCodiDepartament() +"-" + nomDepartament, empleatEditForm.getCbDepts()));
				empleatEditForm.getTfUsuari().setText(emp.getUsuari());
				empleatEditForm.getTfContrasenya().setText("*************");
				empleatEditForm.getCkActiu().setSelected(emp.getActiu());
				empleatEditForm.getTfCodi().setEditable(false);
				empleatEditForm.getFtfDNI().setEditable(false);
				empleatEditForm.getTfnom().setEditable(false);
				empleatEditForm.getTfCognoms().setEditable(false);
				empleatEditForm.getFtfDataNa().setEditable(false);
				empleatEditForm.getTfAdreca().setEditable(false);
				empleatEditForm.getTfEmail().setEditable(false);
				empleatEditForm.getCbDepts().setEnabled(false);
				empleatEditForm.getTfUsuari().setEditable(false);
				empleatEditForm.getTfTelefon().setEditable(false);
				empleatEditForm.getTfContrasenya().setEditable(false);
				empleatEditForm.getCkActiu().setEnabled(false);
				
				// Un cop carregat el formulari el fem visible					
				empleatEditForm.getOkButton().setVisible(false);
				empleatEditForm.getCancelButton().setText("Sortir");
				empleatEditForm.setVisible(true);				
			}else {
				controllerView.missatgeErrorIncidencia(NO_TROBAT);
				empleatEditForm.dispose();
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia(NO_REGISTRE_PER_LLEGIR);
			//empleatEditForm.dispose();
		}
	}
	
	
	/**
	 * Metode per refrescar la taula despres de fer una operació
	 *
	 */	
	public void recarregarTaula(){
		JTable table = getControllerView().getMainview().getEmpleatForm().getTable();
		int filas =	table.getRowCount();
	    for (int i = 0;filas>i; i++) {
	    	getControllerView().getMainview().getEmpleatForm().getModel().removeRow(0);
	    }
		getControllerView().llistarEmpleat();
	}
	
	
	/**
	 * Metode per recuperar l'index del valor escollit del combobox
	 *
	 */	
	public int retIndexCombo(String valor, JComboBox<String> cB) {
		int index = 0;
        for(int i = 0; i < cB.getItemCount() ; i++ ){   	
        	if(valor.equals(cB.getItemAt(i))) {
        		index=i;
        		break;
        	}
        }
		return index;
	}
	
	
	/**
	 * Metode per borrar a la taula 
	 *
	 */	
	public void borrarTaulaEmpleat(){
		JTable table = getControllerView().getMainview().getEmpleatForm().getTable();
		int filas =	table.getRowCount();
	    for (int i = 0;filas>i; i++) {
	    	getControllerView().getMainview().getEmpleatForm().getModel().removeRow(0);
	    }
	}
	
}
