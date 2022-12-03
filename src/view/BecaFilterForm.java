package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ControllerView;
import model.Beca;

public class BecaFilterForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static final Short ALTA = 1;
	private static final Short MODI = 2;
	private static final Short LLEGIR = 3;
	private static final String NO_REGISTRE_PER_ACTUALITZAR = "No hi ha registre seleccionat per poder actualitzar";
	private static final String NO_REGISTRE_BAIXA = "No hi ha registre seleccionat per donar de baixa";
	private static final String NO_TROBAT = "No s'ha trobat aquest estudiant";
	
	private JTable table;
	private BecaEditForm becaEditForm;
	private ControllerView controllerView;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public BecaFilterForm(ControllerView controllerView) {
		
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
		
		JLabel lblTitolServei = new JLabel("Beca");
		lblTitolServei.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblTitolServei.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolServei.setFont(new Font("Dubai", Font.BOLD, 21));
		lblTitolServei.setBounds(10, 11, 798, 41);
		panel.add(lblTitolServei);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 181, 818, 343);
		panel.add(scrollPane);
		
		// Posar header i dades buides i fa que no siguin editables
		model = new DefaultTableModel(new Object[][] {}, 
				new String[] { "Id","Estudiant", "Servei", "Adjudicant", "Import inicial"}) {
			
			private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setBounds(0, 181, 818, 343);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaBeca();
			}
		});
		
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setFocusable(false);
		btnAdd.setBorder(null);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setPreferredSize(new Dimension(40, 40));
		btnAdd.setSize(new Dimension(40, 40));
		btnAdd.setBounds(644, 130, 40, 40);
		btnAdd.setIcon(setIcons("/pictures/add.png", btnAdd));		
		panel.add(btnAdd);

		
		JButton btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarBeca();
			}
		});
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdit.setSize(new Dimension(40, 40));
		btnEdit.setPreferredSize(new Dimension(40, 40));
		btnEdit.setFocusable(false);
		btnEdit.setBorder(null);
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(706, 130, 40, 40);
		btnEdit.setIcon(setIcons("/pictures/edit.png", btnEdit));		
		panel.add(btnEdit);

		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baixaBeca();
			}
		});
		
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setSize(new Dimension(40, 40));
		btnDelete.setPreferredSize(new Dimension(40, 40));
		btnDelete.setFocusable(false);
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(768, 130, 40, 40);
		btnDelete.setIcon(setIcons("/pictures/remove.png", btnDelete));		
		panel.add(btnDelete);

		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 llegirBeca();
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

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public BecaEditForm getBecaEditForm() {
		return becaEditForm;
	}

	public void setBecaEditForm(BecaEditForm becaEditForm) {
		this.becaEditForm = becaEditForm;
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
	 * Metode per donar d'alta una beca nova.
	 */
	public void altaBeca() {
		becaEditForm= new BecaEditForm(controllerView.getMainview(),true ,controllerView, ALTA);
		becaEditForm.setLocationRelativeTo(null);
		becaEditForm.setVisible(true);
	}
	
	/**
	 * Metode per donar de baixa l'item seleccionat a la taula.
	 */
	public void baixaBeca() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);
			controllerView.baixaBeca(idSelec);
			recarregarTaula();
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia(NO_REGISTRE_BAIXA);
		}
	}
	
	
	/**
	 * Metode per modificar l'item seleccionat a la taula
	 */
	public void modificarBeca() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);					
			// { "codiBeca","ImportInicial","nomEstudiant","cognomsEstudiant", "nomServei"}
			// Fer la consulta individual per omplir les dades
			becaEditForm= new BecaEditForm(controllerView.getMainview(),true ,controllerView, MODI);
			becaEditForm.setLocationRelativeTo(null);
			Beca beca = controllerView.consultaIndBeca(idSelec);
			if (beca != null) {
				becaEditForm.getTfCodi().setText(String.valueOf(beca.getCodi()));
				becaEditForm.getCbServei().setSelectedIndex(retIndexCombo(
						beca.getCodi(), becaEditForm.getCbServei()));
				becaEditForm.getCbEstudiant().setSelectedIndex(retIndexCombo(
						beca.getCodi(), becaEditForm.getCbEstudiant()));
				becaEditForm.getTfAdj().setText(String.valueOf(beca.getAdjudicant()));
				becaEditForm.getTfImportIni().setText(String.valueOf(beca.getImportInicial()));
				
				// Un cop carregat el formulari el fem visible
				becaEditForm.setVisible(true);				
			}else {
				controllerView.missatgeErrorIncidencia(NO_TROBAT);
				becaEditForm.dispose();
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia(NO_REGISTRE_PER_ACTUALITZAR);
			//serveiEditForm.dispose();
		}
	}
	
	
	/**
	 * Metode per llistar els diferents items a la taula en forma de fila.
	 */
	public void llistarBeca() {
		controllerView.llistarServei();
	}
	
	
	/**
	 * Metode per llegir l'item seleccionat a la taula
	 */
	public void llegirBeca() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);		
			// { "codiBeca","adjudicant","importInicial","importRestant", "codiEstudiant", 
				//"nomEstudiant", "cognomEstudiant", "codiServei", "nomServei", "finalitzada"}
			// Fer la consulta individual per omplir les dades
			becaEditForm= new BecaEditForm(controllerView.getMainview(),true ,controllerView, LLEGIR);
			becaEditForm.setLocationRelativeTo(null);
			Beca beca = controllerView.consultaIndBeca(idSelec);
			if (beca != null) {
				becaEditForm.getTfCodi().setText(String.valueOf(beca.getCodi()));
				becaEditForm.getTfCodi().setEnabled(false);
				becaEditForm.getTfCodi().setVisible(true);	
				becaEditForm.getLblCodi().setVisible(true);
				becaEditForm.getTfAdj().setText(String.valueOf(beca.getAdjudicant()));
				becaEditForm.getTfAdj().setEnabled(false);
				becaEditForm.getTfAdj().setVisible(true);				
				becaEditForm.getTfImportIni().setText(String.valueOf(beca.getImportInicial()));
				becaEditForm.getTfImportIni().setEnabled(false);	
				becaEditForm.getTfImportRest().setText(String.valueOf(beca.getImportRestant()));
				becaEditForm.getTfImportRest().setEnabled(false);
				becaEditForm.getTfImportRest().setVisible(true);
				becaEditForm.getLblImportRestant().setVisible(true);
				becaEditForm.getCkFinal().setSelected(beca.isFinalitzada());
				becaEditForm.getCkFinal().setEnabled(false);
				becaEditForm.getCkFinal().setVisible(true);
				
				becaEditForm.getCbServei().setSelectedIndex(retIndexCombo(
						beca.getCodi(), becaEditForm.getCbServei()));
				becaEditForm.getCbServei().setEnabled(false);
				becaEditForm.getCbEstudiant().setSelectedIndex(retIndexCombo(
						beca.getCodi(), becaEditForm.getCbEstudiant()));
				becaEditForm.getCbEstudiant().setEnabled(false);
				
				
				becaEditForm.getOkButton().setVisible(false);
				becaEditForm.getCancelButton().setText("Sortir");
				// Un cop carregat el formulari el fem visible
				becaEditForm.setVisible(true);				
			}else {
				controllerView.missatgeErrorIncidencia(NO_TROBAT);
				becaEditForm.dispose();
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia(NO_REGISTRE_PER_ACTUALITZAR);
			//serveiEditForm.dispose();
		}
	}
	
	
	/**
	 * Metode per refrescar la taula despres de fer una operacio
	 *
	 */	
	public void recarregarTaula(){
		JTable table = getControllerView().getMainview().getBecaForm().getTable();
		int filas =	table.getRowCount();
	    for (int i = 0;filas>i; i++) {
	    	getControllerView().getMainview().getBecaForm().getModel().removeRow(0);
	    }
		getControllerView().llistarBeca();
	}
	
	
	/**
	 * Metode per recuperar l'index del valor escollit del combobox
	 *
	 */	
	public int retIndexCombo(int valor, JComboBox<String> cB) {
		int index = 0;
        for(int i = 0; i < cB.getItemCount() ; i++ ){
        	String [] item = cB.getItemAt(i).split("-");
        	if(valor == Integer.parseInt(item[0])) {
        		index=i;
        		break;
        	}
        }
        return index;
	}
	
}
