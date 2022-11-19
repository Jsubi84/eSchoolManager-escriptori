package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerView;
import model.Departament;

public class EmpleatFilterForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Short ALTA = 1;
	private static final Short MODI = 2;
	
	private JTable table;
	DepartamentEditForm deptEditForm;
	private ControllerView controllerView;
	DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public EmpleatFilterForm() {
		
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
				new String[] { "Id","DNI","Nom", "Cognoms"}) {
			
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
		deptEditForm= new DepartamentEditForm(controllerView.getMainview(),true, controllerView, ALTA);
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
			deptEditForm= new DepartamentEditForm(controllerView.getMainview(),true,controllerView, MODI);
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
	
	
}
