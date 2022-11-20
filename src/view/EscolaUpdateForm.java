package view;

import java.awt.Dimension;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import controller.ControllerView;
import model.Escola;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



/**
 * @author Jordi Subirana
 *
 */
public class EscolaUpdateForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NOM_ESCOLA_ISBLACK = "El nom de l'escola s'ha d'omplir";
	
	
	private JTextField tfNom;
	private JTextField tfAdreca;
	private JTextField tfTelefon;
	private JTextField tfCodi;
	private ControllerView controllerView;

	
	/**
	 * Create the panel.
	 */
	public EscolaUpdateForm(ControllerView controllerView) {
		
		setControllerView(controllerView);
		setPreferredSize(new Dimension(818, 524));
		setSize(new Dimension(818, 524));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 818, 524);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitolDepartament = new JLabel("Escola");
		lblTitolDepartament.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblTitolDepartament.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolDepartament.setFont(new Font("Dubai", Font.BOLD, 21));
		lblTitolDepartament.setBounds(10, 11, 798, 41);
		panel.add(lblTitolDepartament);
		
		JButton okButton = new JButton("ACTUALITZAR");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTfNom().getText().isBlank()){
					controllerView.missatgeIncidencia(NOM_ESCOLA_ISBLACK);
				}else {
					getControllerView().actualitzarEscola(Integer.parseInt(getTfCodi().getText()));
				}
			}
		});
		okButton.setBounds(284, 258, 250, 23);
		panel.add(okButton);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(284, 97, 250, 14);
		panel.add(lblNom);
		
		tfNom = new JTextField();
		tfNom.setColumns(10);
		tfNom.setBounds(284, 119, 250, 20);
		panel.add(tfNom);
		
		JLabel lblAdreça = new JLabel("Adreça");
		lblAdreça.setBounds(284, 146, 250, 14);
		panel.add(lblAdreça);
		
		tfAdreca = new JTextField();
		tfAdreca.setColumns(10);
		tfAdreca.setBounds(284, 171, 250, 20);
		panel.add(tfAdreca);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(284, 202, 250, 14);
		panel.add(lblTelefon);
		
		tfTelefon = new JTextField();
		tfTelefon.setColumns(10);
		tfTelefon.setBounds(284, 227, 250, 20);
		panel.add(tfTelefon);
		
		tfCodi = new JTextField();
		tfCodi.setVisible(false);
		tfCodi.setBounds(284, 66, 52, 20);
		panel.add(tfCodi);
		tfCodi.setColumns(10);
		
		//Carregar les dades de l'escola
		llegirEscola();
	}
	

	/**
	 * Getters i Setters
	 */
	public JTextField getTfNom() {
		return tfNom;
	}

	public void setTfNom(JTextField tfNom) {
		this.tfNom = tfNom;
	}

	public JTextField getTfAdreca() {
		return tfAdreca;
	}

	public void setTfAdreca(JTextField tfAdreca) {
		this.tfAdreca = tfAdreca;
	}

	public JTextField getTfTelefon() {
		return tfTelefon;
	}

	public void setTfTelefon(JTextField tfTelefon) {
		this.tfTelefon = tfTelefon;
	}

	public JTextField getTfCodi() {
		return tfCodi;
	}

	public void setTfCodi(JTextField tfCodi) {
		this.tfCodi = tfCodi;
	}

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}
		
	
	/**
	 * Metode per emplenar el formulari d'escola
	 */
	public void llegirEscola() {
		try {	
			Escola esc = controllerView.consultaIndEscola(1);
			if (esc != null) {
				getTfCodi().setText(String.valueOf(esc.getCodi()));
				getTfNom().setText(esc.getNom());
				getTfAdreca().setText(esc.getAdreca());
				getTfTelefon().setText(esc.getTelefon());
			}else {
				controllerView.missatgeErrorIncidencia("No s'ha trobat aquesta escola");
			}
		} catch (Exception e) {
			e.getMessage();
			controllerView.missatgeErrorIncidencia("No s'han pogut llegir les dades de l'escola");
		}
	}
	
	
	
}
