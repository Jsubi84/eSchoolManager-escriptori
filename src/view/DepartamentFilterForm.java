package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ControllerView;
import model.Departament;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Jordi Subirana
 *
 */
public class DepartamentFilterForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Short ALTA = 1;
	private static final Short MODI = 2;
	private static final Short LLEGIR = 3;
	
	private JTable table;
	DepartamentEditForm deptEditForm;
	private ControllerView controllerView;
	DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public DepartamentFilterForm(ControllerView controllerView) {
		
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
		
		JLabel lblTitolDepartament = new JLabel("Departament");
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
				new String[] { "Id","Nom departament"}) {
			
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
		
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaDepartament();
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
				modificarDepartament();
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
				baixaDepartament();
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
				 llegirDepartament();
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
	
	public DepartamentEditForm getDeptEditForm() {
		return deptEditForm;
	}

	public void setDeptEditForm(DepartamentEditForm deptEditForm) {
		this.deptEditForm = deptEditForm;
	}

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
	 * Metode per donar d'alta un departament nou.
	 */
	public void altaDepartament() {
		deptEditForm= new DepartamentEditForm(controllerView.getMainview(),true ,controllerView, ALTA);
		deptEditForm.setLocationRelativeTo(null);
		deptEditForm.setVisible(true);
	}
	
	/**
	 * Metode per donar de baixa l'item seleccionat a la taula.
	 */
	public void baixaDepartament() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);
			controllerView.baixaDepartament(idSelec);
			recarregarTaula();
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia("No hi ha registre seleccionat per donar de baixa");
		}
	}
	
	/**
	 * Metode per modificar l'item seleccionat a la taula
	 */
	public void modificarDepartament() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);		
			// Ordre de la taula			
			// { "Id","Nom Departament", "Escola", "Departament", "Empleat","Estudiant", "Servei", "Beca","Sessio", "Informe" }
			// Fer la consulta individual per omplir les dades
			deptEditForm= new DepartamentEditForm(controllerView.getMainview(),true ,controllerView, MODI);
			deptEditForm.setLocationRelativeTo(null);
			Departament dept = controllerView.consultaIndDepartament(idSelec);
			if (dept != null) {
				deptEditForm.getTfCodi().setText(String.valueOf(dept.getCodi()));
				deptEditForm.getTfnomDep().setText(dept.getNomDepartament());
				deptEditForm.getChckbxBeca().setSelected(dept.getBeca());     
				deptEditForm.getChckbxEscola().setSelected(dept.getEscola());
				deptEditForm.getChckbxDep().setSelected(dept.getDepartament());
				deptEditForm.getChckbxEmpleat().setSelected(dept.getEmpleat());
				deptEditForm.getChckbxEstudiant().setSelected(dept.getEstudiant());
				deptEditForm.getChckbxServei().setSelected(dept.getServei());
				deptEditForm.getChckbxSessio().setSelected(dept.getSessio());
				deptEditForm.getChckbxInforme().setSelected(dept.getInforme());
				// Un cop carregat el formulari el fem visible
				deptEditForm.setVisible(true);				
			}else {
				controllerView.missatgeErrorIncidencia("No s'ha trobat aquest departament");
				deptEditForm.dispose();
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia("No hi ha registre seleccionat per poder actualitzar");
			deptEditForm.dispose();
		}
	}
	
	/**
	 * Metode per llistar els diferents items a la taula en forma de fila.
	 */
	public void llistarDepartament() {
		controllerView.llistarDepartament();
	}
	
	/**
	 * Metode per llegir l'item seleccionat a la taula
	 */
	public void llegirDepartament() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);		
			// Ordre de la taula			
			// { "Id","Nom Departament", "Escola", "Departament", "Empleat","Estudiant", "Servei", "Beca","Sessio", "Informe" }
			// Fer la consulta individual per omplir les dades
			deptEditForm= new DepartamentEditForm(controllerView.getMainview(),true ,controllerView, LLEGIR);
			deptEditForm.setLocationRelativeTo(null);
			Departament dept = controllerView.consultaIndDepartament(idSelec);
			if (dept != null) {
				deptEditForm.getTfCodi().setText(String.valueOf(dept.getCodi()));
				deptEditForm.getTfCodi().setEnabled(false);
				deptEditForm.getTfCodi().setVisible(true);
				deptEditForm.getTfCodi().setForeground(Color.BLACK);
				deptEditForm.getTfCodi().setBackground(Color.WHITE);
				deptEditForm.getTfCodi().setHorizontalAlignment(JTextField.CENTER);
				deptEditForm.getTfnomDep().setText(dept.getNomDepartament());
				deptEditForm.getTfnomDep().setEnabled(false);
				deptEditForm.getTfnomDep().setForeground(Color.BLACK);
				deptEditForm.getTfnomDep().setBackground(Color.WHITE);
				deptEditForm.getChckbxBeca().setSelected(dept.getBeca());    
				deptEditForm.getChckbxBeca().setEnabled(false);
				deptEditForm.getChckbxEscola().setSelected(dept.getEscola());
				deptEditForm.getChckbxEscola().setEnabled(false);
				deptEditForm.getChckbxDep().setSelected(dept.getDepartament());
				deptEditForm.getChckbxDep().setEnabled(false);
				deptEditForm.getChckbxEmpleat().setSelected(dept.getEmpleat());
				deptEditForm.getChckbxEmpleat().setEnabled(false);
				deptEditForm.getChckbxEstudiant().setSelected(dept.getEstudiant());
				deptEditForm.getChckbxEstudiant().setEnabled(false);
				deptEditForm.getChckbxServei().setSelected(dept.getServei());
				deptEditForm.getChckbxServei().setEnabled(false);
				deptEditForm.getChckbxSessio().setSelected(dept.getSessio());
				deptEditForm.getChckbxSessio().setEnabled(false);
				deptEditForm.getChckbxInforme().setSelected(dept.getInforme());
				deptEditForm.getChckbxInforme().setEnabled(false);
				
				deptEditForm.getOkButton().setVisible(false);
				deptEditForm.getCancelButton().setText("Sortir");
				// Un cop carregat el formulari el fem visible
				deptEditForm.setVisible(true);				
			}else {
				controllerView.missatgeErrorIncidencia("No s'ha trobat aquest departament");
				deptEditForm.dispose();
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia("No hi ha registre seleccionat per poder actualitzar");
			deptEditForm.dispose();
		}
	}
	
	
	/**
	 * Metode per refrescar la taula despres de fer una operacio
	 *
	 */	
	public void recarregarTaula(){
		JTable table = getControllerView().getMainview().getDepartamentForm().getTable();
		int filas =	table.getRowCount();
	    for (int i = 0;filas>i; i++) {
	    	getControllerView().getMainview().getDepartamentForm().getModel().removeRow(0);
	    }
		getControllerView().llistarDepartament();
	}	
}
