package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ControllerView;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.CompoundBorder;


/**
 * @author Jordi Subirana
 *
 */
public class LoginView extends JFrame {

	/**
	 * Declaració variables
	 */
	private static final long serialVersionUID = -4402339031261816900L;
	private JPanel contentPane;
	private JTextField txtUsuari;
	private JPasswordField txtContrasenya;
	private ControllerView controllerView;
	

	/**
	 * Creació de la pantalla.
	 */
	public LoginView() {
		
		setResizable(false);
		setBounds(new Rectangle(0, 0, 460, 550));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 460, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setSize(new Dimension(200, 200));
		lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("/pictures/logo_school.png")));
		lblLogo.setBounds(24, 28, 385, 226);
		panel.add(lblLogo);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setFont(new Font("Dubai", Font.PLAIN, 15));
		txtContrasenya.setBorder(null);
		txtContrasenya.setBounds(38, 380, 385, 30);
		panel.add(txtContrasenya);
		
		JButton btnIniciSessio = new JButton("INICIAR SESSIÓ");
		btnIniciSessio.setForeground(new Color(255, 255, 255));
		btnIniciSessio.setFont(new Font("Dubai", Font.PLAIN, 15));
		btnIniciSessio.setBackground(new Color(61, 9, 205));
		btnIniciSessio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIniciarSessio ();
			}
		});
		btnIniciSessio.setBounds(38, 446, 385, 30);
		panel.add(btnIniciSessio);
		
		JLabel lblUsuari = new JLabel("Usuari  ");
		lblUsuari.setBorder(new CompoundBorder());
		lblUsuari.setFont(new Font("Dubai", Font.PLAIN, 15));
		lblUsuari.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuari.setBounds(38, 282, 385, 30);
		panel.add(lblUsuari);
		
		JLabel lblContrasenya = new JLabel("Contrasenya");
		lblContrasenya.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasenya.setFont(new Font("Dubai", Font.PLAIN, 15));
		lblContrasenya.setBounds(38, 351, 385, 30);
		panel.add(lblContrasenya);
		
		JSeparator separatorUsuari = new JSeparator();
		separatorUsuari.setBounds(38, 340, 385, 2);
		panel.add(separatorUsuari);
		
		txtUsuari = new JTextField();
		txtUsuari.setFont(new Font("Dubai", Font.PLAIN, 15));
		txtUsuari.setBorder(null);
		txtUsuari.setBounds(38, 310, 385, 30);
		panel.add(txtUsuari);
		txtUsuari.setColumns(10);
		
		JSeparator separatorContrasenya = new JSeparator();
		separatorContrasenya.setBounds(38, 410, 385, 2);
		panel.add(separatorContrasenya);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}


	/**
	 * Setters i Getters
	 */
	public ControllerView getControllerView() {
		return controllerView;
	}

	public void setControllerView(ControllerView controllerView) {
		this.controllerView = controllerView;
	}	
	
	
	/**
	 * Metode per inicia verificació i access a l'aplicació.
	 */
	public void btnIniciarSessio() {
		Boolean loginOK = controllerView.getControlOper()
				.iniciarSessio(txtUsuari.getText(), new String(txtContrasenya.getPassword()));
		txtUsuari.setText("");
		txtContrasenya.setText("");
		
		//Verificar que el login es correcte pero obrir pantalla principal
		if (loginOK) {
			controllerView.getLoginview().setVisible(false);	
			
			controllerView.carregarOpcionsUsuari();
			controllerView.getMainview().setVisible(true);				
		}else{
			//Visualització del missatge d'error en la classe vista
			JOptionPane.showMessageDialog(null, (String)controllerView.getControlOper().getLogin().getIncidencia());
		}
	}
}
