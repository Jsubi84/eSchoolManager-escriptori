package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Departament;
import model.Login;
import view.LoginView;
import view.MainView;


/**
 * @author Jordi Subirana
 *
 * Classe per poder controlar les vistes
 */
public class ControllerView {
	
	
	private static final String ALTA_OK= "Alta correcte";
	private static final String BAIXA_OK= "Baixa correcte";
	private static final String MODI_OK= "Actualització correcte";
	
	
		
	private LoginView loginview;
	private MainView mainview;
	private ControllerOperation controlOper;
	private String incidencia;
	
	
	public ControllerView() {
	}

	/**
	 * Setters i Getters
	 */
	public String getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}
	
	public LoginView getLoginview() {
		return loginview;
	}	

	public void setLoginview(LoginView loginview) {
		this.loginview = loginview;
	}

	public MainView getMainview() {
		return mainview;
	}

	public void setMainview(MainView mainview) {
		this.mainview = mainview;
	}

	public ControllerOperation getControlOper() {
		return controlOper;
	}

	public void setControlOper(ControllerOperation controlOper) {
		this.controlOper = controlOper;
	}
	
	
	/**
	 * Carregar opcions segons departament que pertany a l'usuari.
	 */
	public void carregarOpcionsUsuari() {
		Login login = controlOper.getLogin();
		mainview.permisos(login);

		mainview.setTextLblNomEmpleat(controlOper.getLogin().getNom().toUpperCase());
		mainview.setTextLblNomDepartament(controlOper.getLogin().getNomDepartament().toUpperCase());		
	}
	
	
	public void missatgeIncidencia(String missatge) {
		JOptionPane.showMessageDialog(null, missatge, "Informació", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void missatgeErrorIncidencia(String missatge) {
	     JOptionPane.showMessageDialog(null, missatge, "Alerta!", JOptionPane.ERROR_MESSAGE);
	}
	
	public JFrame retornarMainForm() {
		return this.getMainview();
	}
	
	
	
	/**
	 * 
	 */
	public void altaDepartament() {
		Departament dept= new Departament();
		dept.setNomDepartament(mainview.getDepartamentForm().getDeptEditForm().getTfnomDep().getText());
		dept.setEmpleat(mainview.getDepartamentForm().getDeptEditForm().getChckbxEmpleat().isSelected());
		dept.setDepartament(mainview.getDepartamentForm().getDeptEditForm().getChckbxDep().isSelected());
		dept.setServei(mainview.getDepartamentForm().getDeptEditForm().getChckbxServei().isSelected());
		dept.setBeca(mainview.getDepartamentForm().getDeptEditForm().getChckbxBeca().isSelected());
		dept.setSessio(mainview.getDepartamentForm().getDeptEditForm().getChckbxSessio().isSelected());
		dept.setEstudiant(mainview.getDepartamentForm().getDeptEditForm().getChckbxEstudiant().isSelected());
		dept.setEscola(mainview.getDepartamentForm().getDeptEditForm().getChckbxEscola().isSelected());
		dept.setInforme(mainview.getDepartamentForm().getDeptEditForm().getChckbxInforme().isSelected());
		
		if (controlOper.altaDepartament(dept)) {
			missatgeIncidencia(ALTA_OK);
			//Afegim la fila a la taula de la nova alta
		//	mainview.getDepartamentForm().getModel().insertRow(0, dept.getRow());
		}else{
			missatgeErrorIncidencia(incidencia);
		}
		
		
	}
	
	
	
	/**
	 * @param codi
	 */
	public void baixaDepartament(int codi) {
		if (controlOper.baixaDepartament(codi)) {
			missatgeIncidencia(BAIXA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * @param codi
	 */
	public void modiDepartament(int codi) {
		Departament dept= new Departament();
		dept.setCodi(codi);
		dept.setNomDepartament(mainview.getDepartamentForm().getDeptEditForm().getTfnomDep().getText());
		dept.setEmpleat(mainview.getDepartamentForm().getDeptEditForm().getChckbxEmpleat().isSelected());
		dept.setDepartament(mainview.getDepartamentForm().getDeptEditForm().getChckbxDep().isSelected());
		dept.setServei(mainview.getDepartamentForm().getDeptEditForm().getChckbxServei().isSelected());
		dept.setBeca(mainview.getDepartamentForm().getDeptEditForm().getChckbxBeca().isSelected());
		dept.setSessio(mainview.getDepartamentForm().getDeptEditForm().getChckbxSessio().isSelected());
		dept.setEstudiant(mainview.getDepartamentForm().getDeptEditForm().getChckbxEstudiant().isSelected());
		dept.setEscola(mainview.getDepartamentForm().getDeptEditForm().getChckbxEscola().isSelected());
		dept.setInforme(mainview.getDepartamentForm().getDeptEditForm().getChckbxInforme().isSelected());
		if (controlOper.modiDepartament(dept)) {
			missatgeIncidencia(MODI_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	public void llistarDepartament() {
		Departament depts[] = controlOper.llistarDepartament("", "", "");
		for(int i=0; i<depts.length; i++){
			mainview.getDepartamentForm().getModel().insertRow(0, depts[i].getRow());
		}
	}
	
	public void consultaIndDepartament() {
		// Faltar implementar els metodes correctament
		//controlOper.consultaIndDepartaments(MODI_OK, BAIXA_OK, ALTA_OK)
	}
	
}
