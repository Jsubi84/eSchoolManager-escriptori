package view;

import java.awt.Dimension;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ControllerView;
import model.Estudiant;
import model.Factura;
import model.LiniaFactura;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;



/**
 * @author Jordi Subirana
 *
 */
public class FacturaGeneForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private static final String SELECCIO_MES = "Selecciona un mes de la llista";
	private static final String SELECCIO_ESTUDIANT = "Selecciona un estudiant de la llista";
	private static final String SENSE_SESSIONS = "L'estudiant no te pagaments per aquest mes";
	
	
	private DefaultTableModel model = new DefaultTableModel();
	private String[] mesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
	private JComboBox<String> cbEstudiant, cbMes;
	private ControllerView controllerView;
	private JLabel lblFactura, lblTotalBeca, lblTotalEst, lblCodiT, lblCodiF, lblNomEstudiant;
	private JButton btnGene;
	private JPanel panel;
	private JTable tableLiniesFactura;
	private JToggleButton tglbtnPagat;
	private Factura factura;
	private JLabel lblTotalBeca_1;

	
	/**
	 * Create the panel.
	 */
	public FacturaGeneForm(ControllerView controllerView) {
		
		setControllerView(controllerView);
		setPreferredSize(new Dimension(818, 524));
		setSize(new Dimension(818, 524));
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 818, 524);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		lblFactura = new JLabel("Factura");
		lblFactura.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Dubai", Font.BOLD, 21));
		lblFactura.setBounds(10, 11, 798, 41);
		panel.add(lblFactura);
		
		btnGene = new JButton("GENERAR");
		btnGene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbMes.getSelectedIndex() == 0){
					controllerView.missatgeIncidencia(SELECCIO_MES);
				}else if(cbEstudiant.getSelectedIndex() == 0) {
					controllerView.missatgeIncidencia(SELECCIO_ESTUDIANT);
				}else {//Generar Factura amb la seleccio
					presentaFactura();
				}
			}
		});
		btnGene.setBounds(680, 63, 128, 23);
		panel.add(btnGene);
		
		cbEstudiant = new JComboBox<String>();
		cbEstudiant.setSelectedIndex(-1);
		cbEstudiant.setBorder(null);
		cbEstudiant.setBounds(10, 63, 422, 22);
		panel.add(cbEstudiant);
		
		cbMes = new JComboBox<String>();
		cbMes.setSelectedIndex(-1);
		cbMes.setBorder(null);
		cbMes.setBounds(442, 63, 228, 22);
		panel.add(cbMes);
		
		
		//COMBOBOX PER ESTUDIANT
		Estudiant estudiants[] = controllerView.getControlOper().llistarEstudiant("", "", "");
		cbEstudiant.removeAllItems();

        //Omplir el combo box amb els estudiants
		cbEstudiant.addItem("<--Selecciona estudiant-->");
        for(int i = 0; i < estudiants.length ; i++ ){
            cbEstudiant.addItem(String.valueOf(estudiants[i].getCodi()) +"-"+ estudiants[i].getNom()
            		+" "+ estudiants[i].getCognoms());}
        cbEstudiant.setSelectedIndex(0);
        
		//COMBOBOX PER MESOS DE L'ANY
        
        cbMes.addItem("<--Selecciona el mes-->");
        for(int i = 0; i < mesos.length ; i++ ){
            cbMes.addItem(mesos[i]);}	
        cbMes.setSelectedIndex(0);	
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 97, 798, 310);
        panel.add(scrollPane);
        
        
		// Posar header i dades buides i fa que no siguin editables
		model = new DefaultTableModel(new Object[][] {}, 
				new String[] { "Data i hora","Servei", "Import beca","Import estudiant"}) {
			
			private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	
        tableLiniesFactura = new JTable(model); 		
        tableLiniesFactura.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tableLiniesFactura.setBounds(0, 181, 818, 343);
        tableLiniesFactura.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tableLiniesFactura.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tableLiniesFactura.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tableLiniesFactura.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tableLiniesFactura.getColumnModel().getColumn(3).setCellRenderer(tcr);

		scrollPane.setViewportView(tableLiniesFactura);
        
        lblTotalBeca = new JLabel("");
        lblTotalBeca.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTotalBeca.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblTotalBeca.setBounds(499, 456, 100, 23);
        panel.add(lblTotalBeca);
        
        lblTotalEst = new JLabel("");
        lblTotalEst.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTotalEst.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblTotalEst.setBounds(680, 456, 128, 23);
        panel.add(lblTotalEst);
        
        lblCodiT = new JLabel("Codi ");
        lblCodiT.setBounds(10, 441, 53, 14);
        panel.add(lblCodiT);
        
        lblCodiF = new JLabel("");
        lblCodiF.setHorizontalAlignment(SwingConstants.CENTER);
        lblCodiF.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblCodiF.setBounds(10, 456, 53, 23);
        panel.add(lblCodiF);
        
        lblNomEstudiant = new JLabel("");
        lblNomEstudiant.setHorizontalAlignment(SwingConstants.LEFT);
        lblNomEstudiant.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblNomEstudiant.setBounds(73, 456, 228, 23);
        panel.add(lblNomEstudiant);
        
        tglbtnPagat = new JToggleButton("PAGAT");
        tglbtnPagat.setEnabled(false);
        tglbtnPagat.setBackground(Color.WHITE);
        tglbtnPagat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pagarFactura(); 
			}			
		});
        tglbtnPagat.setUI(new MetalToggleButtonUI() {
		    @Override
		    protected Color getSelectColor() {
		        return Color.GREEN;
		    }
		});
        
        tglbtnPagat.setBounds(10, 490, 291, 23);
        panel.add(tglbtnPagat);
        
        JLabel lblEstudiantTitol = new JLabel("Estudiant");
        lblEstudiantTitol.setBounds(73, 441, 228, 14);
        panel.add(lblEstudiantTitol);
        
        lblTotalBeca_1 = new JLabel("TOTAL BECA:");
        lblTotalBeca_1.setBounds(499, 441, 100, 14);
        panel.add(lblTotalBeca_1);
        
        JLabel lblTotalEst_1 = new JLabel("TOTAL ESTUDIANT:");
        lblTotalEst_1.setBounds(680, 441, 128, 14);
        panel.add(lblTotalEst_1);
       
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

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public String[] getMesos() {
		return mesos;
	}

	public void setMesos(String[] mesos) {
		this.mesos = mesos;
	}

	public JComboBox<String> getCbEstudiant() {
		return cbEstudiant;
	}

	public void setCbEstudiant(JComboBox<String> cbEstudiant) {
		this.cbEstudiant = cbEstudiant;
	}

	public JLabel getLblTotalBeca() {
		return lblTotalBeca;
	}

	public void setLblTotalBeca(JLabel lblTotalBeca) {
		this.lblTotalBeca = lblTotalBeca;
	}

	public JLabel getLblTotalEst() {
		return lblTotalEst;
	}

	public void setLblTotalEst(JLabel lblTotalEst) {
		this.lblTotalEst = lblTotalEst;
	}

	public JTable getTableLiniesFactura() {
		return tableLiniesFactura;
	}

	public void setTableLiniesFactura(JTable tableLiniesFactura) {
		this.tableLiniesFactura = tableLiniesFactura;
	}

	public JComboBox<String> getCbMes() {
		return cbMes;
	}

	public void setCbMes(JComboBox<String> cbMes) {
		this.cbMes = cbMes;
	}

	public JLabel getLblCodiF() {
		return lblNomEstudiant;
	}

	public void setLblCodiF(JLabel lblCodiF) {
		this.lblNomEstudiant = lblCodiF;
	}

	public JLabel getLblNomEstudiant() {
		return lblNomEstudiant;
	}

	public void setLblNomEstudiant(JLabel lblNomEstudiant) {
		this.lblNomEstudiant = lblNomEstudiant;
	}
	
	public JToggleButton getTglbtnPagat() {
		return tglbtnPagat;
	}
	
	public void setTglbtnPagat(JToggleButton tglbtnPagat) {
		this.tglbtnPagat = tglbtnPagat;
	}


	/**
	 * Metode per presentar la factura generada
	 *	per pantalla amb les diferents linies presentades en una taula
	 */	
	private void presentaFactura() {
		
		factura = getControllerView().generaFactura();
		
		if (factura != null) {
			if  (factura.getLinias().length != 0) {
				double tBeca = 0, tEstudiant = 0;
				
			    for (int i = 0; tableLiniesFactura.getRowCount() > i; i++) {
			    	model.removeRow(0);
			    }
			    LiniaFactura[] linies = factura.getLinias();
				for(int i=0; i < linies.length; i++){
					model.insertRow(0, linies[i].getRow()); 				
					tBeca += linies[i].getImportBeca();
					tEstudiant += linies[i].getImportEstudiant();
				}
				
				lblCodiF.setText(String.valueOf(factura.getCodiF()));
				lblNomEstudiant.setText(factura.getNomEstudiant() + " " + factura.getCognomEstudiant());
				tglbtnPagat.setSelected(factura.getPagat());
				
				lblTotalBeca.setText(String.valueOf(tBeca));
				lblTotalEst.setText(String.valueOf(tEstudiant));
				tglbtnPagat.setEnabled(true);
			}else{
				controllerView.missatgeIncidencia(SENSE_SESSIONS);
			}
		}
	}
	
	
	/**
	 * Metode per pagar factura
	 */	
	private void pagarFactura() {
		Boolean pagat;
		if(tglbtnPagat.isSelected()) {
			pagat = true;
		}else {
			pagat = false;
		}
		controllerView.pagarFactura(pagat, factura.getCodiF());
	}
}
