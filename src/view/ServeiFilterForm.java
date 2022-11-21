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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ControllerView;
import model.Servei;


/**
 * @author Jordi Subirana
 *
 */
public class ServeiFilterForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Short ALTA = 1;
	private static final Short MODI = 2;
	private static final Short LLEGIR = 3;
	
	private JTable table;
	ServeiEditForm serveiEditForm;
	private ControllerView controllerView;
	DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public ServeiFilterForm(ControllerView controllerView) {
		
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
		
		JLabel lblTitolServei = new JLabel("Servei");
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
				new String[] { "Id","Nom", "Durada", "Cost"}) {
			
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
				altaServei();
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
				modificarServei();
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
				baixaServei();
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
				 llegirServei();
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

	public ServeiEditForm getServeiEditForm() {
		return serveiEditForm;
	}

	public void setServeiEditForm(ServeiEditForm serveiEditForm) {
		this.serveiEditForm = serveiEditForm;
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
	 * Metode per donar d'alta un servei nou.
	 */
	public void altaServei() {
		serveiEditForm= new ServeiEditForm(controllerView.getMainview(),true ,controllerView, ALTA);
		serveiEditForm.setLocationRelativeTo(null);
		serveiEditForm.setVisible(true);
	}
	
	/**
	 * Metode per donar de baixa l'item seleccionat a la taula.
	 */
	public void baixaServei() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);
			controllerView.baixaServei(idSelec);
			recarregarTaula();
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia("No hi ha registre seleccionat per donar de baixa");
		}
	}
	
	
	/**
	 * Metode per modificar l'item seleccionat a la taula
	 */
	public void modificarServei() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);					
			//  { "Id","Nom","Durada","Cost" }
			// Fer la consulta individual per omplir les dades
			serveiEditForm= new ServeiEditForm(controllerView.getMainview(),true ,controllerView, MODI);
			serveiEditForm.setLocationRelativeTo(null);
			Servei ser = controllerView.consultaIndServei(idSelec);
			if (ser != null) {
				serveiEditForm.getTfCodi().setText(String.valueOf(ser.getCodi()));
				serveiEditForm.getTfnomServei().setText(ser.getNom());
				serveiEditForm.getTfDurada().setText(String.valueOf(ser.getDurada()));
				serveiEditForm.getTfCost().setText(String.valueOf(ser.getCost()));
				
				// Un cop carregat el formulari el fem visible
				serveiEditForm.setVisible(true);				
			}else {
				controllerView.missatgeErrorIncidencia("No s'ha trobat aquest servei");
				serveiEditForm.dispose();
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia("No hi ha registre seleccionat per poder actualitzar");
			//serveiEditForm.dispose();
		}
	}
	
	
	/**
	 * Metode per llistar els diferents items a la taula en forma de fila.
	 */
	public void llistarServei() {
		controllerView.llistarServei();
	}
	
	
	/**
	 * Metode per llegir l'item seleccionat a la taula
	 */
	public void llegirServei() {
		try {
			int idSelec = (int) getTable().getValueAt(getTable().getSelectedRow(), 0);		
			//  { "Id","Nom","Durada","Cost" }
			// Fer la consulta individual per omplir les dades
			serveiEditForm= new ServeiEditForm(controllerView.getMainview(),true ,controllerView, LLEGIR);
			serveiEditForm.setLocationRelativeTo(null);
			Servei ser = controllerView.consultaIndServei(idSelec);
			if (ser != null) {
				serveiEditForm.getTfCodi().setText(String.valueOf(ser.getCodi()));
				serveiEditForm.getTfCodi().setEnabled(false);
				serveiEditForm.getTfCodi().setVisible(true);
				serveiEditForm.getTfCodi().setForeground(Color.BLACK);
				serveiEditForm.getTfCodi().setBackground(Color.WHITE);
				serveiEditForm.getTfCodi().setHorizontalAlignment(JTextField.CENTER);
				serveiEditForm.getTfnomServei().setText(ser.getNom());
				serveiEditForm.getTfnomServei().setEnabled(false);
				serveiEditForm.getTfnomServei().setForeground(Color.BLACK);
				serveiEditForm.getTfnomServei().setBackground(Color.WHITE);
				serveiEditForm.getTfDurada().setText(String.valueOf(ser.getDurada()));
				serveiEditForm.getTfDurada().setEnabled(false);
				serveiEditForm.getTfDurada().setForeground(Color.BLACK);
				serveiEditForm.getTfDurada().setBackground(Color.WHITE);
				serveiEditForm.getTfCost().setText(String.valueOf(ser.getCost()));
				serveiEditForm.getTfCost().setEnabled(false);
				serveiEditForm.getTfCost().setForeground(Color.BLACK);
				serveiEditForm.getTfCost().setBackground(Color.WHITE);
				
				
				serveiEditForm.getOkButton().setVisible(false);
				serveiEditForm.getCancelButton().setText("Sortir");
				// Un cop carregat el formulari el fem visible
				serveiEditForm.setVisible(true);				
			}else {
				controllerView.missatgeErrorIncidencia("No s'ha trobat aquest servei");
				serveiEditForm.dispose();
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia("No hi ha registre seleccionat per poder actualitzar");
			//serveiEditForm.dispose();
		}
	}
	
	
	/**
	 * Metode per refrescar la taula despres de fer una operacio
	 *
	 */	
	public void recarregarTaula(){
		JTable table = getControllerView().getMainview().getServeiForm().getTable();
		int filas =	table.getRowCount();
	    for (int i = 0;filas>i; i++) {
	    	getControllerView().getMainview().getServeiForm().getModel().removeRow(0);
	    }
		getControllerView().llistarServei();
	}

}
