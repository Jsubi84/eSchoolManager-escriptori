package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4402339031261816900L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JSeparator separatorUser;
	private JSeparator separatorPassword;
	private static Login frame;
				
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		setResizable(false);
		setType(Type.UTILITY);
		setBounds(new Rectangle(0, 0, 380, 434));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 360, 400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIconUser = new JLabel("");
		lblIconUser.setSize(new Dimension(200, 200));
		lblIconUser.setIcon(new ImageIcon(Login.class.getResource("/pictures/user.png")));
		lblIconUser.setBounds(80, 5, 200, 217);
		panel.add(lblIconUser);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Dubai", Font.PLAIN, 15));
		txtUser.setBorder(null);
		txtUser.setBounds(80, 244, 200, 30);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Dubai", Font.PLAIN, 15));
		txtPassword.setBorder(null);
		txtPassword.setBounds(80, 295, 200, 30);
		panel.add(txtPassword);
		
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setFont(new Font("Dubai", Font.PLAIN, 15));
		btnEntrar.setBackground(new Color(80, 169, 245));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);				
				MainPanel principal = new MainPanel(); 
				principal.setVisible(true);
				dispose();
				
			}
		});
		btnEntrar.setBounds(80, 350, 200, 30);
		panel.add(btnEntrar);
		
		separatorUser = new JSeparator();
		separatorUser.setBounds(80, 282, 200, 8);
		panel.add(separatorUser);
		
		separatorPassword = new JSeparator();
		separatorPassword.setBounds(80, 335, 200, 8);
		panel.add(separatorPassword);
		
		setLocationRelativeTo(null);
	}
}
