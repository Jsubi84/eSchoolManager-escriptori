package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Login;

import java.awt.Color;
import javax.swing.JLabel;
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

public class LoginMainSwing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4402339031261816900L;
	private JPanel contentPane;
	private JTextField txtUsuari;
	private JPasswordField txtContrasenya;
	private JSeparator separatorUser;
	private JSeparator separatorContrasenya;
	private static LoginMainSwing frame;
	private String contrasenya, usuari;
	
				
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginMainSwing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginMainSwing() {
		setResizable(false);
		setBounds(new Rectangle(0, 0, 461, 545));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 461, 545);
		contentPane.add(panel);
		panel.setLayout(null);
		
		separatorUser = new JSeparator();
		separatorUser.setBounds(140, 336, 280, 8);
		panel.add(separatorUser);
		
		separatorContrasenya = new JSeparator();
		separatorContrasenya.setBounds(140, 388, 280, 8);
		panel.add(separatorContrasenya);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setSize(new Dimension(200, 200));
		lblLogo.setIcon(new ImageIcon(LoginMainSwing.class.getResource("/pictures/logo_school.png")));
		lblLogo.setBounds(38, 47, 385, 226);
		panel.add(lblLogo);
		
		txtUsuari = new JTextField();
		txtUsuari.setFont(new Font("Dubai", Font.PLAIN, 15));
		txtUsuari.setBorder(null);
		txtUsuari.setBounds(140, 306, 283, 30);
		panel.add(txtUsuari);
		txtUsuari.setColumns(10);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setFont(new Font("Dubai", Font.PLAIN, 15));
		txtContrasenya.setBorder(null);
		txtContrasenya.setBounds(140, 357, 283, 30);
		panel.add(txtContrasenya);
		
		JButton btnIniciSessio = new JButton("INICIAR SESSIÓ");
		btnIniciSessio.setForeground(new Color(255, 255, 255));
		btnIniciSessio.setFont(new Font("Dubai", Font.PLAIN, 15));
		btnIniciSessio.setBackground(new Color(61, 9, 205));
		btnIniciSessio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Agafar usuari i contrasenya 
				usuari = txtUsuari.getText();
				contrasenya = new String(txtContrasenya.getPassword());
				
				//Crear un login
				Login login =new Login(usuari, contrasenya);
				
				//Verificar que el login es correcte
				if (login.CheckLogin()) {
					setVisible(false);				
					PrincipalPanelSwing principal = new PrincipalPanelSwing(); 
					principal.setVisible(true);
					dispose();					
				}
				
			}
		});
		btnIniciSessio.setBounds(38, 439, 385, 30);
		panel.add(btnIniciSessio);
		
		JLabel lblUsuari = new JLabel("Usuari  ");
		lblUsuari.setBorder(new CompoundBorder());
		lblUsuari.setFont(new Font("Dubai", Font.PLAIN, 15));
		lblUsuari.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuari.setBounds(38, 306, 92, 30);
		panel.add(lblUsuari);
		
		JLabel lblContrasenya = new JLabel("Contrasenya");
		lblContrasenya.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasenya.setFont(new Font("Dubai", Font.PLAIN, 15));
		lblContrasenya.setBounds(38, 365, 92, 30);
		panel.add(lblContrasenya);
		
		setLocationRelativeTo(null);
	}
}
