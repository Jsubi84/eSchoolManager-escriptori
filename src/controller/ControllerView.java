package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Departament;
import model.Login;
import model.Servei;
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
	 * 	METODES PER A DEPARTAMENT
	 */
	
	/**
	 * Metode controlar la part de la vist d'alta departament.
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
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vist baixa departament.
	 * @param codi. Codi del registre que s'ha de borrar.
	 */
	public void baixaDepartament(int codi) {
		if (controlOper.baixaDepartament(codi)) {
			missatgeIncidencia(BAIXA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	

	
	/**
	 * Metode controlar la part de la vist modificar departament.
	 * @param codi. Codi del registre que s'ha de modificar.
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
	
	
	/**
	 * Metode controlar la part de la vist de llistar departament.
	 */
	public void llistarDepartament() {						
		Departament depts[] = controlOper.llistarDepartament("", "", "");
		if (depts == null) {
			missatgeErrorIncidencia(incidencia);
		}else {
			for(int i=0; i<depts.length; i++){
				mainview.getDepartamentForm().getModel().insertRow(0, depts[i].getRow());
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vist consulta individual que s'ha de buscar departament.
	 * @param codi. Codi del registre que s'ha de consultar.
	 * @return Departament. Retorna la consulta
	 */
	public Departament consultaIndDepartament(int codi) {
		Departament dept = controlOper.consultaIndDepartaments(codi);
		if (dept == null) {
			missatgeErrorIncidencia(incidencia);
			return null;
		} else {
			return dept;
		}
	}
	
	
	
	/**
	 * 	METODES PER A SERVEI
	 */
	
	/**
	 * Metode controlar la part de la vist d'alta departament.
	 */
	public void altaServei() {
		Servei ser= new Servei();
		ser.setNom(mainview.getServeiForm().getServeiEditForm().getTfnomDep().getText());
		ser.setDurada(Integer.parseInt(mainview.getServeiForm().getServeiEditForm().getTfDurada().getText()));
		ser.setCost(Double.parseDouble(mainview.getServeiForm().getServeiEditForm().getTfCost().getText()));
		if (controlOper.altaServei(ser)) {
			missatgeIncidencia(ALTA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vist baixa d'un servei.
	 * @param codi. Codi del registre que s'ha de borrar.
	 */
	public void baixaServei(int codi) {
		if (controlOper.baixaServei(codi)) {
			missatgeIncidencia(BAIXA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	

	
	/**
	 * Metode controlar la part de la vist modificar departament.
	 * @param codi. Codi del registre que s'ha de modificar.
	 */
	public void modiServei(int codi) {
		Servei ser= new Servei();
		ser.setCodi(codi);
		ser.setNom(mainview.getServeiForm().getServeiEditForm().getTfnomServei().getText());
		ser.setDurada(Integer.parseInt(mainview.getServeiForm().getServeiEditForm().getTfDurada().getText()));
		ser.setCost(Double.parseDouble(mainview.getServeiForm().getServeiEditForm().getTfCost().getText()));
		if (controlOper.modiServei(ser)) {
			missatgeIncidencia(MODI_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vist de llistar servei.
	 */
	public void llistarServei() {						
		Servei serveis[] = controlOper.llistarServei("", "", "");
		if (serveis == null) {
			missatgeErrorIncidencia(incidencia);
		}else {
			for(int i=0; i<serveis.length; i++){
				mainview.getServeiForm().getModel().insertRow(0, serveis[i].getRow());
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vist consulta individual que s'ha de buscar servei.
	 * @param codi. Codi del registre que s'ha de consultar.
	 * @return Servei. Retorna la consulta
	 */
	public Servei consultaIndServei(int codi) {
		Servei ser = controlOper.consultaIndServei(codi);
		if (ser == null) {
			missatgeErrorIncidencia(incidencia);
			return null;
		} else {
			return ser;
		}
	}
	
}
