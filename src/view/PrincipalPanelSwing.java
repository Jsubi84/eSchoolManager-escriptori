package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.Crida;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Dimension;

public class PrincipalPanelSwing extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9079607942999779149L;
	/**
	 * 
	 */
	private String missatge;

	/**
	 * Create the dialog.
	 */
	public PrincipalPanelSwing() {
		setSize(new Dimension(1000, 600));
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Sortir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				missatge = Crida.logoutJSon("dfasdagasdf");
				System.out.println(missatge);
				
				setVisible(false);				
				LoginMainSwing login = new LoginMainSwing();
				login.setVisible(true);
				dispose();
				
			}
			
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Opcio 1");
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Opcio 2");
		panel_1.add(btnNewButton_2);
		
		setLocationRelativeTo(null);
		
	}

}
