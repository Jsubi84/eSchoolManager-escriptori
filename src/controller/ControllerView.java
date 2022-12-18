package controller;

import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Beca;
import model.Departament;
import model.Empleat;
import model.Escola;
import model.Estudiant;
import model.Factura;
import model.Login;
import model.Servei;
import model.Sessio;
import util.Convert;
import view.LoginView;
import view.MainView;


/**
 * @author Jordi Subirana
 *
 * Classe per poder controlar les vistes
 */
public class ControllerView {
	
//	private static final String FACTURA_OK= "Factura generada correctament";
	private static final String ALTA_OK= "Alta correcte";
	private static final String BAIXA_OK= "Baixa correcte";
	private static final String MODI_OK= "Actualització correcte";
	private static final String PAGAT_OK= "S'ha actualitzat el pagament correctament";
	private static final String DESPAGAT_OK= "Factura no pagada";	
	private static final String NO_REGISTRES= "No hi ha registres";
	
		
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
	 * Metode controlar la part de la vista d'alta departament.
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
		dept.setFactura(mainview.getDepartamentForm().getDeptEditForm().getChckbxFactura().isSelected());
		
		if (controlOper.altaDepartament(dept)) {
			missatgeIncidencia(ALTA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista baixa departament.
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
	 * Metode controlar la part de la vista modificar departament.
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
		dept.setFactura(mainview.getDepartamentForm().getDeptEditForm().getChckbxFactura().isSelected());
		if (controlOper.modiDepartament(dept)) {
			missatgeIncidencia(MODI_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de llistar departament.
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
	 * Metode controlar la part de la vista de filtrar departament.
	 */
	public void filtrarDepartament(String camp, String valor, String ordre ) {						
		Departament depts[] = controlOper.llistarDepartament(camp, valor, ordre);
		if (depts == null) {
			missatgeErrorIncidencia(incidencia);
		}else if (depts.length == 0){
			missatgeIncidencia(NO_REGISTRES);
		}else {
			for(int i=0; i<depts.length; i++){
				mainview.getDepartamentForm().getModel().insertRow(0, depts[i].getRow());
			}			
		}
	}
	
	/**
	 * Metode controlar la part de la vista consulta individual que s'ha de buscar departament.
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
	 * Metode controlar la part de la vista d'alta servei.
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
	 * Metode controlar la part de la vista baixa d'un servei.
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
	 * Metode controlar la part de la vista modificar Servei.
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
	 * Metode controlar la part de la vista de llistar servei.
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
	 * Metode controlar la part de la vista de filtrar servei.
	 */
	public void filtrarServei(String camp, String valor, String ordre ) {						
		Servei serveis[] = controlOper.llistarServei(camp, valor, ordre);
		if (serveis == null) {
			missatgeErrorIncidencia(incidencia);
		}else if (serveis.length == 0){
			missatgeIncidencia(NO_REGISTRES);
		}else {
			for(int i=0; i<serveis.length; i++){
				mainview.getServeiForm().getModel().insertRow(0, serveis[i].getRow());
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista consulta individual que ha de buscar un servei.
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
	
	
	
	
	
	/**
	 * 	METODES PER A ESCOLA
	 */
	
	/**
	 * Metode controlar la part de la vista actualitzar Escola.
	 * @param codi. Codi del registre que s'ha de modificar.
	 */
	public void actualitzarEscola(int codi) {
		Escola esc= new Escola();
		esc.setCodi(codi);
		esc.setNom(mainview.getEscolaForm().getTfNom().getText());
		esc.setAdreca(mainview.getEscolaForm().getTfAdreca().getText());
		esc.setTelefon(mainview.getEscolaForm().getTfTelefon().getText());

		if (controlOper.actualitzarEscola(esc)) {
			missatgeIncidencia(MODI_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista consulta individual carrega dades Escola.
	 * @param codi. Codi del registre que s'ha de consultar.
	 * @return Escola. Retorna la consulta
	 */
	public Escola consultaIndEscola(int codi) {
		Escola esc = controlOper.consultaIndEscola(codi);
		if (esc == null) {
			missatgeErrorIncidencia(incidencia);
			return null;
		} else {
			return esc;
		}
	}
	
	
	
	
	
	/**
	 * 	METODES PER A EMPLEAT
	 */
	
	/**
	 * Metode controlar la part de la vista d'alta Empleat.
	 */
	public void altaEmpleat() {
		Empleat emp= new Empleat();
	
		emp.setDni(mainview.getEmpleatForm().getEmpleatEditForm().getFtfDNI().getText());
		emp.setNom(mainview.getEmpleatForm().getEmpleatEditForm().getTfnom().getText());
		emp.setCognoms(mainview.getEmpleatForm().getEmpleatEditForm().getTfCognoms().getText());
		emp.setDataNaixament(mainview.getEmpleatForm().getEmpleatEditForm().getFtfDataNa().getText());
		emp.setAdreca(mainview.getEmpleatForm().getEmpleatEditForm().getTfAdreca().getText());
		emp.setTelefon(mainview.getEmpleatForm().getEmpleatEditForm().getTfTelefon().getText());
		emp.setEmail(mainview.getEmpleatForm().getEmpleatEditForm().getTfEmail().getText());
		emp.setCodiDepartament(Convert.splitCombo((String) mainview.getEmpleatForm().getEmpleatEditForm().getCbDepts().getSelectedItem()));
		emp.setUsuari(mainview.getEmpleatForm().getEmpleatEditForm().getTfUsuari().getText());
		emp.setContrasenya(mainview.getEmpleatForm().getEmpleatEditForm().getTfContrasenya().getText());
		
		if (controlOper.altaEmpleat(emp)) {
			missatgeIncidencia(ALTA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista baixa d'un empleat.
	 * @param codi. Codi del registre que s'ha de borrar.
	 */
	public void baixaEmpleat(int codi) {
		if (controlOper.baixaEmpleat(codi)) {
			missatgeIncidencia(BAIXA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista modificar empleat.
	 * @param codi. Codi del registre que s'ha de modificar.
	 */
	public void modiEmpleat(int codi) {
		Empleat emp= new Empleat();
		
		emp.setCodi(codi);
		emp.setDni(mainview.getEmpleatForm().getEmpleatEditForm().getFtfDNI().getText());
		emp.setNom(mainview.getEmpleatForm().getEmpleatEditForm().getTfnom().getText());
		emp.setCognoms(mainview.getEmpleatForm().getEmpleatEditForm().getTfCognoms().getText());
		emp.setDataNaixament(mainview.getEmpleatForm().getEmpleatEditForm().getFtfDataNa().getText());
		emp.setAdreca(mainview.getEmpleatForm().getEmpleatEditForm().getTfAdreca().getText());
		emp.setTelefon(mainview.getEmpleatForm().getEmpleatEditForm().getTfTelefon().getText());
		emp.setEmail(mainview.getEmpleatForm().getEmpleatEditForm().getTfEmail().getText());
		emp.setCodiDepartament(Convert.splitCombo((String) mainview.getEmpleatForm().getEmpleatEditForm().getCbDepts().getSelectedItem()));
		emp.setUsuari(mainview.getEmpleatForm().getEmpleatEditForm().getTfUsuari().getText());
		emp.setContrasenya(mainview.getEmpleatForm().getEmpleatEditForm().getTfContrasenya().getText());
		emp.setActiu(mainview.getEmpleatForm().getEmpleatEditForm().getCkActiu().isSelected());
		
		if (controlOper.modiEmpleat(emp)) {
			missatgeIncidencia(MODI_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de llistar empleat.
	 */
	public void llistarEmpleat() {						
		Empleat empleats[] = controlOper.llistarEmpleat("", "", "");
		if (empleats == null) {
			missatgeErrorIncidencia(incidencia);
		}else {
			for(int i=0; i<empleats.length; i++){
				mainview.getEmpleatForm().getModel().insertRow(0, empleats[i].getRow());
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de filtrar empleat.
	 */
	public void filtrarEmpleat(String camp, String valor, String ordre ) {						
		Empleat emp[] = controlOper.llistarEmpleat(camp, valor, ordre);
		if (emp == null) {
			missatgeErrorIncidencia(incidencia);
		}else if (emp.length == 0){
			missatgeIncidencia(NO_REGISTRES);
		}else {
			for(int i=0; i<emp.length; i++){
				mainview.getEmpleatForm().getModel().insertRow(0, emp[i].getRow());
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista consulta individual que ha de buscar un empleat.
	 * @param codi. Codi del registre que s'ha de consultar.
	 * @return Empleat. Retorna la consulta
	 */
	public Empleat consultaIndEmpleat(int codi) {
		Empleat emp = controlOper.consultaIndEmpleat(codi);
		if (emp == null) {
			missatgeErrorIncidencia(incidencia);
			return null;
		} else {
			return emp;
		}
	}
	

	
	
	/**
	 * 	METODES PER A ESTUDIANT
	 */
	
	/**
	 * Metode controlar la part de la vista d'alta Estudiant.
	 */
	public void altaEstudiant() {
		Estudiant est= new Estudiant();
		// {"codiEstudiant","dni","nom","cognoms","dataNeixement","adreca", "telefon","email","matriculat"}
		est.setDni(mainview.getEstudiantForm().getEstudiantEditForm().getFtfDNI().getText());
		est.setNom(mainview.getEstudiantForm().getEstudiantEditForm().getTfnom().getText());
		est.setCognoms(mainview.getEstudiantForm().getEstudiantEditForm().getTfCognoms().getText());
		est.setDataNaixament(mainview.getEstudiantForm().getEstudiantEditForm().getFtfDataNa().getText());
		est.setAdreca(mainview.getEstudiantForm().getEstudiantEditForm().getTfAdreca().getText());
		est.setTelefon(mainview.getEstudiantForm().getEstudiantEditForm().getTfTelefon().getText());
		est.setEmail(mainview.getEstudiantForm().getEstudiantEditForm().getTfEmail().getText());
		est.setMatriculat(mainview.getEstudiantForm().getEstudiantEditForm().getCkMatriculat().isSelected());
		
		if (controlOper.altaEstudiant(est)) {
			missatgeIncidencia(ALTA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista baixa d'un estudiant.
	 * @param codi. Codi del registre que s'ha de borrar.
	 */
	public void baixaEstudiant(int codi) {
		if (controlOper.baixaEstudiant(codi)) {
			missatgeIncidencia(BAIXA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista modificar estudiant.
	 * @param codi. Codi del registre que s'ha de modificar.
	 */
	public void modiEstudiant(int codi) {
		Estudiant est= new Estudiant();
		
		est.setCodi(codi);
		est.setDni(mainview.getEstudiantForm().getEstudiantEditForm().getFtfDNI().getText());
		est.setNom(mainview.getEstudiantForm().getEstudiantEditForm().getTfnom().getText());
		est.setCognoms(mainview.getEstudiantForm().getEstudiantEditForm().getTfCognoms().getText());
		est.setDataNaixament(mainview.getEstudiantForm().getEstudiantEditForm().getFtfDataNa().getText());
		est.setAdreca(mainview.getEstudiantForm().getEstudiantEditForm().getTfAdreca().getText());
		est.setTelefon(mainview.getEstudiantForm().getEstudiantEditForm().getTfTelefon().getText());
		est.setEmail(mainview.getEstudiantForm().getEstudiantEditForm().getTfEmail().getText());
		est.setMatriculat(mainview.getEstudiantForm().getEstudiantEditForm().getCkMatriculat().isSelected());
		
		if (controlOper.modiEstudiant(est)) {
			missatgeIncidencia(MODI_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de llistar estudiant.
	 */
	public void llistarEstudiant() {						
		Estudiant estudiants[] = controlOper.llistarEstudiant("", "", "");
		if (estudiants == null) {
			missatgeErrorIncidencia(incidencia);
		}else {
			for(int i=0; i<estudiants.length; i++){
				mainview.getEstudiantForm().getModel().insertRow(0, estudiants[i].getRow());
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de filtrar estudiant.
	 */
	public void filtrarEstudiant(String camp, String valor, String ordre ) {						
		Estudiant est[] = controlOper.llistarEstudiant(camp, valor, ordre);
		if (est == null) {
			missatgeErrorIncidencia(incidencia);
		}else if (est.length == 0){
			missatgeIncidencia(NO_REGISTRES);
		}else {
			for(int i=0; i<est.length; i++){
				mainview.getEstudiantForm().getModel().insertRow(0, est[i].getRow());
			}			
		}
	}
	
	/**
	 * Metode controlar la part de la vista consulta individual que ha de buscar un estudiant.
	 * @param codi. Codi del registre que s'ha de consultar.
	 * @return Estudiant. Retorna la consulta
	 */
	public Estudiant consultaIndEstudiant(int codi) {
		Estudiant est = controlOper.consultaIndEstudiant(codi);
		if (est == null) {
			missatgeErrorIncidencia(incidencia);
			return null;
		} else {
			return est;
		}
	}
	
	
	

	/**
	 * 	METODES PER A BECA
	 */
	
	/**
	 * Metode controlar la part de la vista d'alta Beca.
	 */
	public void altaBeca() {
		
		Beca beca= new Beca();
		
		beca.setAdjudicant(mainview.getBecaForm().getBecaEditForm().getTfAdj().getText());
		beca.setImportInicial(Double.parseDouble(mainview.getBecaForm().getBecaEditForm().getTfImportIni().getText()));		
		beca.setCodiEstudiant(Convert.splitCombo((String) mainview.getBecaForm().getBecaEditForm().getCbEstudiant().getSelectedItem()));
		beca.setCodiServei(Convert.splitCombo((String) mainview.getBecaForm().getBecaEditForm().getCbServei().getSelectedItem()));
		
		if (controlOper.altaBeca(beca)) {
			missatgeIncidencia(ALTA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista baixa d'un beca.
	 * @param codi. Codi del registre que s'ha de borrar.
	 */
	public void baixaBeca(int codi) {
		if (controlOper.baixaBeca(codi)) {
			missatgeIncidencia(BAIXA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista modificar beca.
	 * @param codi. Codi del registre que s'ha de modificar.
	 */
	public void modiBeca(int codi) {
		
		Beca beca= new Beca();
		
		beca.setCodi(codi);
		beca.setAdjudicant(mainview.getBecaForm().getBecaEditForm().getTfAdj().getText());
		beca.setImportInicial(Double.parseDouble(mainview.getBecaForm().getBecaEditForm().getTfImportIni().getText()));	
		//beca.setImportRestant(Double.parseDouble(mainview.getBecaForm().getBecaEditForm().getTfImportRest().getText()));
		beca.setCodiEstudiant(Convert.splitCombo((String) mainview.getBecaForm().getBecaEditForm().getCbEstudiant().getSelectedItem()));
		beca.setCodiServei(Convert.splitCombo((String) mainview.getBecaForm().getBecaEditForm().getCbServei().getSelectedItem()));
		beca.setFinalitzada(mainview.getBecaForm().getBecaEditForm().getCkFinal().isSelected());
		
		if (controlOper.modiBeca(beca)) {
			missatgeIncidencia(MODI_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de llistar beca.
	 */
	public void llistarBeca() {						
		Beca beques[] = controlOper.llistarBeca("", "", "");
		if (beques == null) {
			missatgeErrorIncidencia(incidencia);
		}else {
			for(int i=0; i<beques.length; i++){
				mainview.getBecaForm().getModel().insertRow(0, beques[i].getRow(beques[i].getNomCognomsEstudiant(), beques[i].getNomServei()));
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de filtrar beca.
	 */
	public void filtrarBeca(String camp, String valor, String ordre ) {						
		Beca beques[] = controlOper.llistarBeca(camp, valor, ordre);
		if (beques == null) {
			missatgeErrorIncidencia(incidencia);
		}else if (beques.length == 0){
			missatgeIncidencia(NO_REGISTRES);
		}else {
			for(int i=0; i<beques.length; i++){
				mainview.getBecaForm().getModel().insertRow(0, beques[i].getRow(beques[i].getNomCognomsEstudiant(), beques[i].getNomServei()));
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista consulta individual que ha de buscar un beca.
	 * @param codi. Codi del registre que s'ha de consultar.
	 * @return Beca. Retorna la consulta
	 */
	public Beca consultaIndBeca(int codi) {
		Beca beca = controlOper.consultaIndBeca(codi);
		if (beca == null) {
			missatgeErrorIncidencia(incidencia);
			return null;
		} else {
			return beca;
		}
	}
	
	
	
	
	

	/**
	 * 	METODES PER A SESSIO
	 */
	
	/**
	 * Metode controlar la part de la vista d'una sessio.
	 */
	public void altaSessio() {
		
		Sessio sessio= new Sessio();
		
		sessio.setCodiEmpleat(Convert.splitCombo((String) mainview.getSessioForm().getSessioEditForm().getCbProfe().getSelectedItem()));
		sessio.setCodiEstudiant(Convert.splitCombo((String) mainview.getSessioForm().getSessioEditForm().getCbEstudiant().getSelectedItem()));
		sessio.setCodiServei(Convert.splitCombo((String) mainview.getSessioForm().getSessioEditForm().getCbServei().getSelectedItem()));
		
		String time = mainview.getSessioForm().getSessioEditForm().getTfHora().getText();
		String date = mainview.getSessioForm().getSessioEditForm().getTfData().getText();
		String[] t = time.split(":");
		String[] d = date.split("-");
		LocalDateTime ldt = LocalDateTime.of(Convert.toInt(d[0]), Convert.toInt(d[1]), Convert.toInt(d[2]),
				Convert.toInt(t[0]), Convert.toInt(t[1]));
		sessio.setDataIHora(ldt);
		
		if (controlOper.altaSessio(sessio)) {
			missatgeIncidencia(ALTA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista baixa d'una sessio.
	 * @param codi. Codi del registre que s'ha de borrar.
	 */
	public void baixaSessio(int codi) {
		if (controlOper.baixaSessio(codi)) {
			missatgeIncidencia(BAIXA_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista modificar sessio.
	 * @param codi. Codi del registre que s'ha de modificar.
	 */
	public void modiSessio(int codi) {
		
		Sessio sessio= new Sessio();
		
		sessio.setCodi(codi);
		sessio.setCodiEmpleat(Convert.splitCombo((String) mainview.getSessioForm().getSessioEditForm().getCbProfe().getSelectedItem()));
		sessio.setCodiEstudiant(Convert.splitCombo((String) mainview.getSessioForm().getSessioEditForm().getCbEstudiant().getSelectedItem()));
		sessio.setCodiServei(Convert.splitCombo((String) mainview.getSessioForm().getSessioEditForm().getCbServei().getSelectedItem()));
		
		String time = mainview.getSessioForm().getSessioEditForm().getTfHora().getText();
		String date = mainview.getSessioForm().getSessioEditForm().getTfData().getText();
		String[] t = time.split(":");
		String[] d = date.split("-");
		LocalDateTime ldt = LocalDateTime.of(Convert.toInt(d[0]), Convert.toInt(d[1]), Convert.toInt(d[2]),
				Convert.toInt(t[0]), Convert.toInt(t[1]));
		sessio.setDataIHora(ldt);
		
		if (controlOper.modiSessio(sessio)) {
			missatgeIncidencia(MODI_OK);
		}else{
			missatgeErrorIncidencia(incidencia);
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de llistar sessions.
	 */
	public void llistarSessio() {						
		Sessio sessions[] = controlOper.llistarSessio("", "", "");
		if (sessions == null) {
			missatgeErrorIncidencia(incidencia);
		}else {
			for(int i=0; i<sessions.length; i++){
				mainview.getSessioForm().getModel().insertRow(0, sessions[i].getRow());
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista de filtrar sessions.
	 */
	public void filtrarSessio(String camp, String valor, String ordre ) {						
		Sessio sessions[] = controlOper.llistarSessio(camp, valor, ordre);
		if (sessions == null) {
			missatgeErrorIncidencia(incidencia);
		}else if (sessions.length == 0){
			missatgeIncidencia(NO_REGISTRES);
		}else {
			for(int i=0; i<sessions.length; i++){
				mainview.getSessioForm().getModel().insertRow(0, sessions[i].getRow());
			}			
		}
	}
	
	
	/**
	 * Metode controlar la part de la vista consulta individual que ha de buscar una sessio.
	 * @param codi. Codi del registre que s'ha de consultar.
	 * @return Sessio. Retorna la consulta
	 */
	public Sessio consultaIndSessio(int codi) {
		Sessio sessio = controlOper.consultaIndSessio(codi);
		if (sessio == null) {
			missatgeErrorIncidencia(incidencia);
			return null;
		} else {
			return sessio;
		}
	}
	
	
	
	/**
	 * 	METODES PER A FACTURA
	 */
	
	/**
	 * Metode per generar una factura.
	 */
	public Factura generaFactura() {
		
		Factura factura= new Factura();
		
		factura.setCodiEstudiant(Convert.splitCombo((String) mainview.getFacturaForm().getCbEstudiant().getSelectedItem()));
		factura.setMes(mainview.getFacturaForm().getCbMes().getSelectedIndex());
		
		factura = controlOper.generarFactura(factura);
		
		if (factura == null) {
			missatgeErrorIncidencia(incidencia);
			return null;
		}else{
			return factura;
		}
	}
	
	
	/**
	 * Metode per pagar factura.
	 */
	public void pagarFactura(Boolean pagat, int codiF) {
		if (controlOper.pagarFactura(pagat, codiF)) {
			if (pagat) {
				missatgeIncidencia(PAGAT_OK);
			}else {
				missatgeIncidencia(DESPAGAT_OK);
			}
		}else {
			missatgeErrorIncidencia(incidencia);
		}
	}
}
