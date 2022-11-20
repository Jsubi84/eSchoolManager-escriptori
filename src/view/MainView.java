package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import controller.ControllerView;
import model.Login;
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
import java.awt.Component;
import javax.swing.border.EtchedBorder;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Toolkit;



/**
 * @author Jordi Subirana 
 *
 */

public class MainView extends JFrame {


	private static final long serialVersionUID = -1L;
	
	private ControllerView controllerView;
	private JLabel lblNomEmpleat, lblNomDepartament, lblLogo;
	private JButton btnServei, btnBeca, btnSessio, btnEstudiant, btnEmpleat, btnDepartament, btnEscola, btnConfigUsuari, btnSortirSessio;
	private JPanel dades;
	private Boolean isServei, isBeca, isSessio, isEstudiant, isEmpleat, isDepartament, isEscola;
	private EscolaUpdateForm escolaForm;
	private DepartamentFilterForm departamentForm;
	private SessioFilterForm sessioForm;
	private BecaFilterForm becaForm;
	private EstudiantFilterForm estudiantForm;
	private ServeiFilterForm serveiForm;
	private EmpleatFilterForm empleatForm;
	

	/**
	 * Create MainViewModern.
	 */
	public MainView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/pictures/logo_icon_XL_.png")));
		isServei = false;
		isBeca = false;
		isSessio = false; 
		isEstudiant= false; 
		isEmpleat= false; 
		isDepartament = false; 
		isEscola = false;
		
		
		setResizable(false);
		setSize(new Dimension(1000, 600));
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(0);
		borderLayout.setHgap(0);
		
		setTitle("eSchoolManager");
		
		JPanel capçalera = new JPanel();
		capçalera.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		capçalera.setBackground(new Color(255, 255, 255));
		getContentPane().add(capçalera, BorderLayout.NORTH);
		capçalera.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCapçalera = new JPanel();
		panelCapçalera.setBackground(Color.WHITE);
		capçalera.add(panelCapçalera, BorderLayout.WEST);
		
		JLabel lblLabelUsuari = new JLabel("Usuari : ");
		lblLabelUsuari.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblLabelUsuari.setBackground(new Color(255, 255, 255));
		panelCapçalera.add(lblLabelUsuari);
		
		
		lblNomEmpleat = new JLabel("");
		lblNomEmpleat.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblNomEmpleat.setBackground(new Color(0, 255, 127));
		panelCapçalera.add(lblNomEmpleat);		
		
		JLabel lblLabelEmpleat = new JLabel("   Departament: ");
		lblLabelEmpleat.setFont(new Font("Dubai", Font.PLAIN, 14));
		panelCapçalera.add(lblLabelEmpleat);
		
		lblNomDepartament = new JLabel("");
		lblNomDepartament.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblNomDepartament.setBackground(new Color(0, 255, 127));
		panelCapçalera.add(lblNomDepartament);
		
		JPanel panelConfigOut = new JPanel();
		panelConfigOut.setBackground(Color.WHITE);
		capçalera.add(panelConfigOut, BorderLayout.EAST);
		
		btnConfigUsuari = new JButton("");
		btnConfigUsuari.setFocusable(false);
		btnConfigUsuari.setSize(new Dimension(25, 25));
		btnConfigUsuari.setBorder(null);
		btnConfigUsuari.setPreferredSize(new Dimension(25, 25));
		btnConfigUsuari.setIcon(setIcons("/pictures/settings.png", btnConfigUsuari));
		btnConfigUsuari.setHorizontalAlignment(SwingConstants.RIGHT);
		btnConfigUsuari.setForeground(Color.WHITE);
		btnConfigUsuari.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnConfigUsuari.setBorder(null);
		btnConfigUsuari.setBackground(Color.WHITE);
		panelConfigOut.add(btnConfigUsuari);
		
		btnSortirSessio = new JButton("");
		btnSortirSessio.setFocusable(false);
		panelConfigOut.add(btnSortirSessio);
		btnSortirSessio.setPreferredSize(new Dimension(25, 25));
		btnSortirSessio.setBorder(null);
		btnSortirSessio.setSize(new Dimension(25, 25));
		btnSortirSessio.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnSortirSessio.setSize(new Dimension(91, 23));
		btnSortirSessio.setIcon(setIcons("/pictures/logout.png", btnSortirSessio));
		btnSortirSessio.setForeground(new Color(255, 255, 255));
		btnSortirSessio.setBackground(Color.WHITE);
		btnSortirSessio.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSortirSessio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortirSessio();
			}
		});
		
		JPanel menu = new JPanel();
		menu.setSize(new Dimension(150, 0));
		menu.setBackground(Color.WHITE);
		menu.setBorder(null);
		menu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().add(menu, BorderLayout.WEST);
		
		

		//Button ESCOLA
		btnEscola = new JButton("Escola");
		btnEscola.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnEscola.setFocusable(false);
		btnEscola.setPreferredSize(new Dimension(60, 60));
		btnEscola.setSize(new Dimension(60, 60));
		btnEscola.setBackground(Color.WHITE);
		btnEscola.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEscola.setBorder(null);
		btnEscola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcioEscola();
			}
		});
		
		btnEscola.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEscola.setBackground(Color.lightGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				if (isEscola) {
					btnEscola.setBackground(Color.lightGray);
				}else {
					btnEscola.setBackground(Color.WHITE);	
				}	
			}
		});
		
		
		//Button DEPARTAMENT
		btnDepartament = new JButton("Departament");
		btnDepartament.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnDepartament.setFocusable(false);
		btnDepartament.setPreferredSize(new Dimension(60, 60));
		btnDepartament.setSize(new Dimension(60, 60));
		btnDepartament.setBackground(Color.WHITE);
		btnDepartament.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDepartament.setBorder(null);
		btnDepartament.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcioDepartament();
			}
		});
		
		btnDepartament.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDepartament.setBackground(Color.lightGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				if (isDepartament) {
					btnDepartament.setBackground(Color.lightGray);
				}else {
					btnDepartament.setBackground(Color.WHITE);	
				}	
			}
		});
		

		
		//Button EMPLEAT
		btnEmpleat = new JButton("Empleat");
		btnEmpleat.setPreferredSize(new Dimension(40, 40));
		btnEmpleat.setSize(new Dimension(40, 40));		
		//btnEmpleat.setIcon(new ImageIcon(MainView.class.getResource("/pictures/estudiant.png")));		
		btnEmpleat.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnEmpleat.setFocusable(false);
		btnEmpleat.setBackground(Color.WHITE);
		btnEmpleat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEmpleat.setBorder(null);
		btnEmpleat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcioEmpleat();
			}
		});
		
		btnEmpleat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEmpleat.setBackground(Color.lightGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				if (isEmpleat) {
					btnEmpleat.setBackground(Color.lightGray);
				}else {
					btnEmpleat.setBackground(Color.WHITE);	
				}	
			}
		});
		
		
		//Button ESTUDIANT
		btnEstudiant = new JButton("Estudiant");
		btnEstudiant.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnEstudiant.setFocusable(false);
		btnEstudiant.setPreferredSize(new Dimension(60, 60));
		btnEstudiant.setSize(new Dimension(60, 60));
		btnEstudiant.setBackground(Color.WHITE);
		btnEstudiant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstudiant.setBorder(null);
		btnEstudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcioEstudiant();
			}
		});
		
		btnEstudiant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEstudiant.setBackground(Color.lightGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				if (isEstudiant) {
					btnEstudiant.setBackground(Color.lightGray);
				}else {
					btnEstudiant.setBackground(Color.WHITE);	
				}	
			}
		});
		
		
		//Button SESSIO
		btnSessio = new JButton("Sessio");
		btnSessio.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnSessio.setFocusable(false);
		btnSessio.setPreferredSize(new Dimension(60, 60));
		btnSessio.setSize(new Dimension(60, 60));
		btnSessio.setBackground(Color.WHITE);
		btnSessio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSessio.setBorder(null);
		btnSessio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcioSessio();
			}
		});
		
		btnSessio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSessio.setBackground(Color.lightGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				if (isSessio) {
					btnSessio.setBackground(Color.lightGray);
				}else {
					btnSessio.setBackground(Color.WHITE);	
				}	
			}
		});
		
		
		//Button BECA
		btnBeca = new JButton("Beca");
		btnBeca.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnBeca.setFocusable(false);
		btnBeca.setPreferredSize(new Dimension(60, 60));
		btnBeca.setSize(new Dimension(60, 60));
		btnBeca.setBackground(Color.WHITE);
		btnBeca.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBeca.setBorder(null);
		btnBeca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcioBeca();
			}
		});
		
		btnBeca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBeca.setBackground(Color.lightGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				if (isBeca) {
					btnBeca.setBackground(Color.lightGray);
				}else {
					btnBeca.setBackground(Color.WHITE);	
				}	
			}
		});
		
		
		//Button SERVI
		btnServei = new JButton("Servei");
		btnServei.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnServei.setFocusable(false);
		btnServei.setPreferredSize(new Dimension(60, 60));
		btnServei.setSize(new Dimension(60, 60));
		btnServei.setBackground(Color.WHITE);
		btnServei.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServei.setBorder(null);
		btnServei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcioServei();
			}
		});
		
		btnServei.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnServei.setBackground(Color.lightGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				if (isServei) {
					btnServei.setBackground(Color.lightGray);
				}else {
					btnServei.setBackground(Color.WHITE);	
				}	
			}
		});
		
		
		lblLogo = new JLabel("");
		lblLogo.setSize(new Dimension(160, 100));
		
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/pictures/logo_school.png"));
        Image imgEscalada = imgIcon.getImage().getScaledInstance(lblLogo.getWidth(),
                lblLogo.getHeight(), Image.SCALE_DEFAULT);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        lblLogo.setIcon(iconoEscalado);
		
		GroupLayout gl_menu = new GroupLayout(menu);
		gl_menu.setHorizontalGroup(
			gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_menu.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnEscola, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDepartament, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEmpleat, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEstudiant, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBeca, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnServei, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSessio, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))				
						.addGap(0))
					.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 170 ,Short.MAX_VALUE )
		);
		gl_menu.setVerticalGroup(
			gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnEscola)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDepartament)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEmpleat)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEstudiant)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnBeca)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnServei)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSessio)
						.addPreferredGap(ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
						.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
		);
		gl_menu.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnEscola, btnDepartament, btnEmpleat, btnEstudiant, btnSessio, btnBeca, btnServei});
		menu.setLayout(gl_menu);
		
		dades = new JPanel();
		dades.setBackground(Color.WHITE);
		getContentPane().add(dades, BorderLayout.CENTER);
		dades.setLayout(new CardLayout(0, 0));

		setLocationRelativeTo(null);
	}
	
	
	/**
	 * Setters i Getters
	 */
	
	
	
	public ControllerView getControllerView() {
		return controllerView;
	}

	public JPanel getDades() {
		return dades;
	}


	public void setDades(JPanel dades) {
		this.dades = dades;
	}


	public EscolaUpdateForm getEscolaForm() {
		return escolaForm;
	}


	public void setEscolaForm(EscolaUpdateForm escolaForm) {
		this.escolaForm = escolaForm;
	}


	public DepartamentFilterForm getDepartamentForm() {
		return departamentForm;
	}


	public void setDepartamentForm(DepartamentFilterForm departamentForm) {
		this.departamentForm = departamentForm;
	}


	public SessioFilterForm getSessioForm() {
		return sessioForm;
	}


	public void setSessioForm(SessioFilterForm sessioForm) {
		this.sessioForm = sessioForm;
	}


	public BecaFilterForm getBecaForm() {
		return becaForm;
	}


	public void setBecaForm(BecaFilterForm becaForm) {
		this.becaForm = becaForm;
	}


	public EstudiantFilterForm getEstudiantForm() {
		return estudiantForm;
	}


	public void setEstudiantForm(EstudiantFilterForm estudiantForm) {
		this.estudiantForm = estudiantForm;
	}


	public ServeiFilterForm getServeiForm() {
		return serveiForm;
	}


	public void setServeiForm(ServeiFilterForm serveiForm) {
		this.serveiForm = serveiForm;
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
	 * Permisos per cada tipus d'usuari carrega de opcions visibles
	 */
	public void permisos(Login login) {
		btnServei.setVisible(login.getpServei());
		btnBeca.setVisible(login.getpBeca());
		btnSessio.setVisible(login.getpSessio());
		btnEstudiant.setVisible(login.getpEstudiant());
		btnEmpleat.setVisible(login.getpEmpleat());
		btnDepartament.setVisible(login.getpDepartament());
		btnEscola.setVisible(login.getpEscola());
	}
	
	/**
	 * Metode per sortir de la sessio i tornar a la pantalla de login.
	 */
	public void sortirSessio() {
		controllerView.getControlOper().sortirSessio();
		controllerView.getMainview().setVisible(false);		
		controllerView.getLoginview().setVisible(true);
	}
	
	
	public void resetIsSelectedMenu() {
		isServei = false;
		isBeca = false;
		isSessio = false; 
		isEstudiant = false;
		isEmpleat = false; 
		isDepartament = false;
		isEscola = false;
		btnServei.setBackground(Color.WHITE);	
		btnBeca.setBackground(Color.WHITE);
		btnSessio.setBackground(Color.WHITE);
		btnEstudiant.setBackground(Color.WHITE);
		btnEmpleat.setBackground(Color.WHITE);
		btnDepartament.setBackground(Color.WHITE);
		btnEscola.setBackground(Color.WHITE);
	}
	
	
	
	public void resetDades() {
		resetIsSelectedMenu();
	}

	public void opcioEscola() {
		resetDades();
		isEscola = true;
		btnEscola.setBackground(Color.lightGray);
		
		escolaForm = new EscolaUpdateForm(getControllerView());
		carregarPanel(escolaForm);
		escolaForm.llegirEscola();
	}
	
	public void opcioDepartament() {
		resetDades();
		isDepartament = true;
		btnDepartament.setBackground(Color.lightGray);
		
		departamentForm = new DepartamentFilterForm (getControllerView());
		carregarPanel(departamentForm);
		departamentForm.llistarDepartament();
	}	
	
	public void opcioEmpleat() {
		resetDades();
		isEmpleat = true; 
		btnEmpleat.setBackground(Color.lightGray);
		
		empleatForm = new EmpleatFilterForm(getControllerView());
		carregarPanel(empleatForm);
	}
	
	public void opcioEstudiant() {
		resetDades();
		isEstudiant = true;
		btnEstudiant.setBackground(Color.lightGray);
		
		estudiantForm = new EstudiantFilterForm();
		carregarPanel(estudiantForm);
	}
	
	public void opcioServei() {
		resetDades();		
		isServei = true;
		btnServei.setBackground(Color.lightGray);
		
		serveiForm = new ServeiFilterForm(getControllerView());
		carregarPanel(serveiForm);
		serveiForm.llistarServei();
	}
	
	public void opcioBeca() {
		resetDades();	
		isBeca = true;
		btnBeca.setBackground(Color.lightGray);
		
		becaForm = new BecaFilterForm();
		carregarPanel(becaForm);
	}
	
	public void opcioSessio() {
		resetDades();		
		isSessio = true; 
		btnSessio.setBackground(Color.lightGray);
		
		sessioForm = new SessioFilterForm();
		carregarPanel(sessioForm);
	}
	
	public void carregarPanel(JPanel panel) {
		dades.removeAll();
		dades.add(panel);
		dades.revalidate();
		dades.repaint();
	}
}
