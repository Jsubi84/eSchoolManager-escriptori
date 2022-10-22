package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ControllerView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;


public class MainView extends JFrame {


	private static final long serialVersionUID = -9079607942999779149L;
	
	/**
	 * 
	 */
	ControllerView controllerView;
	JLabel lblNomEmpleat, lblNomDepartament, lblLogo;
	JButton btnServei, btnBeca, btnSessions, btnEstudiant, btnEmpleats, btnDepartament, btnEscola;
	private JPanel dades;

	/**
	 * Create MainView.
	 */
	public MainView() {
		setSize(new Dimension(1000, 600));
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(0);
		borderLayout.setHgap(0);
		
		setTitle("eSchoolManager");
		
		JPanel General = new JPanel();
		General.setBackground(new Color(255, 255, 255));
		getContentPane().add(General, BorderLayout.NORTH);
		
		JButton btnSortirSessio = new JButton("Sortir Sessio");
		btnSortirSessio.setForeground(new Color(255, 255, 255));
		btnSortirSessio.setBackground(new Color(255, 0, 0));
		btnSortirSessio.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSortirSessio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortirSessio();
			}
			
		});
		General.setLayout(new BorderLayout(0, 0));
		General.add(btnSortirSessio, BorderLayout.EAST);
		
		JPanel panelCapçalera = new JPanel();
		General.add(panelCapçalera, BorderLayout.WEST);
		
		JLabel lblLabelUsuari = new JLabel("Usuari : ");
		lblLabelUsuari.setBackground(new Color(255, 255, 255));
		panelCapçalera.add(lblLabelUsuari);
		
		
		lblNomEmpleat = new JLabel("");
		lblNomEmpleat.setBackground(new Color(0, 255, 127));
		panelCapçalera.add(lblNomEmpleat);		
		
		JLabel lblLabelEmpleat = new JLabel("   Departament: ");
		panelCapçalera.add(lblLabelEmpleat);
		
		lblNomDepartament = new JLabel("");
		lblNomDepartament.setBackground(new Color(0, 255, 127));
		panelCapçalera.add(lblNomDepartament);
		
		JPanel menu = new JPanel();
		menu.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		menu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().add(menu, BorderLayout.WEST);
		
		btnEscola = new JButton("Escola");
		btnEscola.setBounds(new Rectangle(0, 0, 60, 20));
		btnEscola.setVisible(false);
		
		btnDepartament = new JButton("Departament");
		btnDepartament.setBounds(new Rectangle(0, 0, 60, 20));
		btnDepartament.setVisible(false);
		
		btnEmpleats = new JButton("Empleat");
		btnEmpleats.setBounds(new Rectangle(0, 0, 60, 20));
		btnEmpleats.setVisible(false);
		
		btnEstudiant = new JButton("Estudiant");
		btnEstudiant.setBounds(new Rectangle(0, 0, 60, 20));
		btnEstudiant.setVisible(false);
		
		btnSessions = new JButton("Sessions");
		btnSessions.setBounds(new Rectangle(0, 0, 60, 20));
		btnSessions.setVisible(false);
		
		btnBeca = new JButton("Beca");
		btnBeca.setBounds(new Rectangle(0, 0, 60, 20));
		btnBeca.setVisible(false);
		
		btnServei = new JButton("Servei");
		btnBeca.setBounds(new Rectangle(0, 0, 60, 20));
		btnServei.setVisible(false);
		
		lblLogo = new JLabel("");
		lblLogo.setSize(new Dimension(150, 120));
		
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/pictures/logo_school_small.png"));
        Image imgEscalada = imgIcon.getImage().getScaledInstance(lblLogo.getWidth(),
                lblLogo.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        lblLogo.setIcon(iconoEscalado);
		
		GroupLayout gl_menu = new GroupLayout(menu);
		gl_menu.setHorizontalGroup(
			gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menu.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEscola, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDepartament, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEmpleats, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEstudiant, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBeca, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnServei, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSessions, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_menu.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_menu.setVerticalGroup(
			gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnEscola)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDepartament)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEmpleats)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEstudiant)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBeca)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnServei)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSessions)
					.addPreferredGap(ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_menu.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnEscola, btnDepartament, btnEmpleats, btnEstudiant, btnSessions, btnBeca, btnServei, lblLogo});
		menu.setLayout(gl_menu);
		
		dades = new JPanel();
		dades.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dades.setBackground(new Color(255, 255, 255));
		getContentPane().add(dades, BorderLayout.CENTER);
		
	}

	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}
	
	
	public void setTextLblNomEmpleat(String lblNomEmpleat) {
		this.lblNomEmpleat.setText(lblNomEmpleat);
	}

	public void setTextLblNomDepartament(String lblNomDepartament) {
		this.lblNomDepartament.setText(lblNomDepartament);
	}
	
	public void opcionsAdministrador() {
		btnServei.setVisible(true);
		btnBeca.setVisible(true);
		btnSessions.setVisible(true);
		btnEstudiant.setVisible(true);
		btnEmpleats.setVisible(true);
		btnDepartament.setVisible(true);
		btnEscola.setVisible(true);
	}
	
	public void opcionsAdministratiu() {
		btnServei.setVisible(true);
		btnBeca.setVisible(true);
		btnSessions.setVisible(true);
		btnEstudiant.setVisible(true);
		btnEmpleats.setVisible(true);
	}
	
	public void opcionsDocent() {
		btnSessions.setVisible(true);
	}
	
	public void opcionsReset() {
		btnServei.setVisible(false);
		btnBeca.setVisible(false);
		btnSessions.setVisible(false);
		btnEstudiant.setVisible(false);
		btnEmpleats.setVisible(false);
		btnDepartament.setVisible(false);
		btnEscola.setVisible(false);
	}
	
	public void sortirSessio() {
		controllerView.getControlOper().sortirSessio();
		controllerView.getMainview().setVisible(false);		
		controllerView.getLoginview().setVisible(true);
		opcionsReset();
	}
}
