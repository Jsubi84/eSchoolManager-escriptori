package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerView;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JTextField;
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
	private static final Boolean ALTA = true;
	private static final Boolean MODI = false;
	
	private JTable table;
	private JTextField textField_2;
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
		
		// Posar header i dades buides
		model = new DefaultTableModel(new Object[][] {}, 
				new String[] { "Id","Nom Departament", "Escola", "Departament", "Empleat","Estudiant", "Servei", "Beca","Sessio", "Informe" }); 
		
		table = new JTable(model);
		table.setBounds(0, 181, 818, 343);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		panel.add(btnAdd);
		btnAdd.setIcon(setIcons("/pictures/add.png", btnAdd));
		
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
		panel.add(btnEdit);
		btnEdit.setIcon(setIcons("/pictures/edit.png", btnEdit));
		
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
		panel.add(btnDelete);
		btnDelete.setIcon(setIcons("/pictures/remove.png", btnDelete));
		
		JLabel lblNewLabel = new JLabel("Filtratge :");
		lblNewLabel.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 86, 146, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Consulta individual :");
		lblNewLabel_1.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 140, 146, 14);
		panel.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(172, 134, 96, 20);
		panel.add(textField_2);
		
		JButton btnSearchFilter = new JButton("");
		btnSearchFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearchFilter.setSize(new Dimension(40, 40));
		btnSearchFilter.setPreferredSize(new Dimension(40, 40));
		btnSearchFilter.setFocusable(false);
		btnSearchFilter.setBorder(null);
		btnSearchFilter.setBackground(Color.WHITE);
		btnSearchFilter.setBounds(504, 86, 40, 40);
		panel.add(btnSearchFilter);
		btnSearchFilter.setIcon(setIcons("/pictures/search.png", btnSearchFilter));
		
		JButton btnSearchInd = new JButton("");
		btnSearchInd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearchInd.setSize(new Dimension(40, 40));
		btnSearchInd.setPreferredSize(new Dimension(40, 40));
		btnSearchInd.setFocusable(false);
		btnSearchInd.setBorder(null);
		btnSearchInd.setBackground(Color.WHITE);
		btnSearchInd.setBounds(291, 130, 40, 40);
		panel.add(btnSearchInd);
		btnSearchInd.setIcon(setIcons("/pictures/search.png", btnSearchInd));
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setToolTipText("Camp");
//		comboBox.setBounds(172, 82, 212, 22);
//		panel.add(comboBox);
//		
//		JComboBox comboBox_1 = new JComboBox();
//		comboBox_1.setBounds(394, 82, 80, 22);
//		panel.add(comboBox_1);

		llistarDepartament();
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
		deptEditForm= new DepartamentEditForm(controllerView, ALTA);
		deptEditForm.setLocationRelativeTo(null);
		deptEditForm.setVisible(true);
	}
	
	/**
	 * Metode per donar de baixa l'item seleccionat a la taula.
	 */
	public void baixaDepartament() {
		try {
			int idSelec = (int) getTable().getValueAt(table.getSelectedRow(), 0);
			controllerView.baixaDepartament(idSelec);
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
			getTable().getValueAt(table.getSelectedRow(), 0);		
			// Ordre de la taula			
			// { "Id","Nom Departament", "Escola", "Departament", "Empleat","Estudiant", "Servei", "Beca","Sessio", "Informe" }
			
			deptEditForm= new DepartamentEditForm(controllerView, MODI);
			deptEditForm.getTfnomDep().setText((String)getTable().getValueAt(table.getSelectedRow(), 1));
			deptEditForm.getChckbxEscola().setSelected((boolean) getTable().getValueAt(table.getSelectedRow(), 2));
			deptEditForm.getChckbxDep().setSelected((boolean) getTable().getValueAt(table.getSelectedRow(), 3));
			deptEditForm.getChckbxEmpleat().setSelected((boolean) getTable().getValueAt(table.getSelectedRow(), 4));
			deptEditForm.getChckbxEstudiant().setSelected((boolean) getTable().getValueAt(table.getSelectedRow(), 5));
			deptEditForm.getChckbxServei().setSelected((boolean) getTable().getValueAt(table.getSelectedRow(), 6));
			deptEditForm.getChckbxBeca().setSelected((boolean) getTable().getValueAt(table.getSelectedRow(), 7));
			deptEditForm.getChckbxSessio().setSelected((boolean) getTable().getValueAt(table.getSelectedRow(), 8));
			deptEditForm.getChckbxInforme().setSelected((boolean) getTable().getValueAt(table.getSelectedRow(), 9));
			deptEditForm.setLocationRelativeTo(null);
			deptEditForm.setVisible(true);	
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia("No hi ha registre seleccionat per poder actualitzar");
		}
	}
	
	/**
	 * Metode per llistar els diferents items a la taula en forma de fila.
	 */
	public void llistarDepartament() {
		//TODO
		controllerView.llistarDepartament();
	}
	

}
