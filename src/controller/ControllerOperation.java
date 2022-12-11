package controller;

import java.io.IOException;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Beca;
import model.Departament;
import model.Empleat;
import model.Escola;
import model.Estudiant;
import model.Login;
import model.Servei;
import model.Sessio;
import util.Convert;
import util.TalkToServer;

/**
 * @author Jordi Subirana
 *
 * Classe per poder executar les operacions
 */

public class ControllerOperation {
	
	private static final String RESPOSTA_OK= "OK";
	
	private Login login;
	private ControllerView controlView;
	private Departament depts[];
	private Servei serveis[];
	private Empleat empleats[];
	private Estudiant estudiants[];
	private Beca beques[];
	private Sessio sessions[];
	
	
	/**
	 * Constructor per defecte
	 */
	public ControllerOperation() {
	}
	
	/**
	 * Constructor per test
	 */	
	public ControllerOperation(ControllerView controlView) {
		this.controlView = controlView;
	}

	
	/**
	 * Setters i Getters
	 */
	public Login getLogin() {
		return login;
	}	

	public void setLogin(Login login) {
		this.login = login;
	}

	public ControllerView getControlView() {
		return controlView;
	}

	public void setControlView(ControllerView controlView) {
		this.controlView = controlView;
	}
	
	
	/**
	 * Operació iniciar sessió d'usuari a l'aplicació.
	 * @param usuari 
	 * @param contrasenya
	 * @return retorna true si s'ha pogut iniciar sessió, false si per el contrari les credencials no eren correctes.
	 */
	public Boolean iniciarSessio(String usuari, String contrasenya) {
		login = new Login (usuari, contrasenya);
		return login.CheckLogin();
	}
	
	/**
	 * Operació sortir sessió. 
	 * Ens retorna a la pantalla d'inici de sessió.
	 * Destrueix el registre de login. 
	 */
	public void sortirSessio() {
		try {
			TalkToServer.connection(login.logoutJSon());
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		login = null; 
	}
	
	
	/**
	 * Metode per agrupar l'enviament de les crides per a Create, Update, Delete.
	 * @param crida
	 * @return resposta
	 */
	private Boolean enviarCridaSimple(String crida) {
		String resposta="";
		try {
			 resposta = TalkToServer.connection(crida);
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			 
			 
		JSONObject jsonUsuari = new JSONObject(resposta);
		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			return true;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return false;
		}				
			
	}
	
	
	/**
	 * Metode per agrupar l'enviament de les crides Read.
	 * @param crida
	 * @return resposta
	 */
	public JSONArray enviarCridaRetornObjectes(String crida) {
		String resposta="";
		try {
			resposta = TalkToServer.connection(crida);
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();	
		}
			
	   	JSONObject jsonUsuari = new JSONObject(resposta);	
	   	JSONArray jsonArray = new JSONArray();

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			JSONObject dades = jsonUsuari.getJSONObject("dades");
						
			Iterator<String> x = dades.keys();
			while (x.hasNext()){
			    String key = (String) x.next();
			    jsonArray.put(dades.get(key));
			}
		
			return jsonArray;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}				
	}
	
	
	
	/**
	 * METODES DEPARTAMENTS
	 */
	
	/**
	 * Metode per donar d'alta un Departament confeccionant la crida i enviant-la
	 * @param departament. Rep un departament a donar d'alta.
	 * @return Retorna resposta afirmativa si ha s'ha donat d'alta
	 */
	public Boolean altaDepartament(Departament departament) {
		return enviarCridaSimple(departament.altaJSon(login.getCodiSessio()));
	}
	

	/**
	 * Metode per donar de baixa un Departament confeccionant la crida i enviant-la
	 * @param codi. Rep un codi del quan es el id del registre a borrar.
	 * @return Retorna resposta afirmativa si s'ha pogut borrar.
	 */
	public Boolean baixaDepartament(int codi) {		
		return enviarCridaSimple(Departament.baixaJSon(login.getCodiSessio(), codi));	
	}
	
	/**
	 * Metode per modificar un Departament confeccionant la crida i enviant-la
	 * @param departament. Rep un departament el qual sera modificara al actual registre persistent.
	 * @return Retorna resposta afirmativa si ha s'ha pogut modificar
	 */
	public Boolean modiDepartament(Departament departament) {
		return enviarCridaSimple(departament.modiJSon(login.getCodiSessio()));	
	}
	
	
	
	/**
	 * Metode per llistar departament confeccionant la crida i enviant-la
	 * si no rebem els parametres buits retornem tots els registres
	 * @param camp. Rep un parametre com camp on s'ha de buscar
	 * @param valor. Rep un parametre com a valor a buscar
	 * @param ordre. Rep un parametre per llistar en aquell ordre
	 * @return Retorna en forma d'array de Departaments
	 */
	public Departament[] llistarDepartament(String camp, String valor, String ordre) {
		JSONArray arr = enviarCridaRetornObjectes(Departament.llistatJSon(login.getCodiSessio(), camp, valor, ordre));
		if (arr == null) {
			return null;
		}else {
			depts = new Departament[arr.length()];
			for(int i=0; i<arr.length(); i++){   
				  JSONObject o = arr.getJSONObject(i);
				  depts[i]= new Departament();
				  depts[i].setCodi(o.getInt("codiDepartament"));
				  depts[i].setNomDepartament(o.getString("nomDepartament"));
			}
			return depts;			
		}
	}
	
	
	
	/**
	 * Metode per consultar un departament confeccionant la crida i enviant-la
	 * @param codi. Rep codi del qual es el codi del Departament a consultar
	 * @return Retorna el departament a recuperat
	 */
	public Departament consultaIndDepartaments(int codi) {
		
		String resposta="";
		try {
			resposta = TalkToServer.connection(Departament.consultaJSon(login.getCodiSessio(), codi));
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	   	JSONObject jsonUsuari = new JSONObject(resposta);	

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			Departament dept= new Departament();			
			
			JSONObject dades = jsonUsuari.getJSONObject("dades");
			dept.setCodi(dades.getInt("codiDepartament"));
			dept.setNomDepartament(dades.getString("nomDepartament"));
			
			JSONObject permisos = dades.getJSONObject("permisos");
			dept.setEscola(permisos.getBoolean("escola"));
			dept.setDepartament(permisos.getBoolean("departament"));
			dept.setEmpleat(permisos.getBoolean("empleat"));
			dept.setEstudiant(permisos.getBoolean("estudiant"));
			dept.setServei(permisos.getBoolean("servei"));
			dept.setBeca(permisos.getBoolean("beca"));
			dept.setSessio(permisos.getBoolean("sessio"));
			dept.setFactura(permisos.getBoolean("factura"));
					
			return dept;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}				
	
	}
	
	
	
	
	/**
	 * METODES SERVEIS
	 */
	
	
	/**
	 * Metode per donar d'alta un servei confeccionant la crida i enviant-la
	 * @param servei. Rep un servei a donar d'alta.
	 * @return Retorna resposta afirmativa si ha s'ha donat d'alta
	 */
	public Boolean altaServei(Servei servei) {
		return enviarCridaSimple(servei.altaJSon(login.getCodiSessio()));
	}
	

	/**
	 * Metode per donar de baixa un servei confeccionant la crida i enviant-la
	 * @param codi. Rep un codi del quan es el id del registre a borrar.
	 * @return Retorna resposta afirmativa si s'ha pogut borrar.
	 */
	public Boolean baixaServei(int codi) {		
		return enviarCridaSimple(Servei.baixaJSon(login.getCodiSessio(), codi));	
	}
	
	/**
	 * Metode per modificar un servei confeccionant la crida i enviant-la
	 * @param servei. Rep un servei el qual sera modificara al actual registre persistent.
	 * @return Retorna resposta afirmativa si ha s'ha pogut modificar
	 */
	public Boolean modiServei(Servei servei) {
		return enviarCridaSimple(servei.modiJSon(login.getCodiSessio()));	
	}
	
	/**
	 * Metode per llistar servei confeccionant la crida i enviant-la
	 * si no rebem els parametres buits retornem tots els registres
	 * @param camp. Rep un parametre com camp on s'ha de buscar
	 * @param valor. Rep un parametre com a valor a buscar
	 * @param ordre. Rep un parametre per llistar en aquell ordre
	 * @return Retorna en forma d'array de Serveis
	 */
	public Servei[] llistarServei(String camp, String valor, String ordre) {
		JSONArray arr = enviarCridaRetornObjectes(Servei.llistatJSon(login.getCodiSessio(), camp, valor, ordre));
		if (arr == null) {
			return null;
		}else {
			serveis = new Servei[arr.length()];
			for(int i=0; i<arr.length(); i++){   
				  JSONObject o = arr.getJSONObject(i);
				  serveis[i]= new Servei();
				  serveis[i].setCodi(o.getInt("codiServei"));
				  serveis[i].setNom(o.getString("nomServei"));
				  serveis[i].setDurada(o.getInt("durada"));
				  serveis[i].setCost(o.getDouble("cost"));
			}
			return serveis;			
		}
	}
	
	/**
	 * Metode per consultar un servei confeccionant la crida i enviant-la
	 * @param codi. Rep codi del qual es el codi del servei a consultar
	 * @return Retorna un servei el qual volem consultar
	 */
	public Servei consultaIndServei(int codi) {
		
		String resposta="";
		try {
			resposta = TalkToServer.connection(Servei.consultaJSon(login.getCodiSessio(), codi));
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
			
	   	JSONObject jsonUsuari = new JSONObject(resposta);	

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			Servei servei= new Servei();			
			
			JSONObject dades = jsonUsuari.getJSONObject("dades");
			servei.setCodi(dades.getInt("codiServei"));
			servei.setNom(dades.getString("nomServei"));
			servei.setDurada(dades.getInt("durada"));
			servei.setCost(dades.getDouble("cost"));
			
			return servei;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}			
			
	}
	
	
	
	
	/**
	 * METODES ESCOLA
	 */
	
	/**
	 * Metode per actualitzar el formulari de l'escola confeccionant la crida i enviant-la
	 * @param escola. Rep una escola el qual sera actualitzada al actual registre persistent.
	 * @return Retorna resposta afirmativa si ha s'ha pogut actualitzar
	 */
	public Boolean actualitzarEscola(Escola escola) {
		return enviarCridaSimple(escola.modiJSon(login.getCodiSessio()));	
	}
	
	
	/**
	 * Metode per consultar escola confeccionant la crida i enviant-la
	 * @param codi. Rep codi de l'escola a consultar
	 * @return Retorna les dades de l'escola
	 */
	public Escola consultaIndEscola(int codi) {
		String resposta="";
		try {
			resposta = TalkToServer.connection(Escola.consultaJSon(login.getCodiSessio(), codi));
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
			
		JSONObject jsonUsuari = new JSONObject(resposta);	

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			Escola escola= new Escola();			
			
			JSONObject dades = jsonUsuari.getJSONObject("dades");
			escola.setCodi(1);
			escola.setNom(dades.getString("nomEscola"));
			escola.setAdreca(dades.getString("adreca"));
			escola.setTelefon(dades.getString("telefon"));
			
			return escola;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}				
		
	}
	
	
	
	
	/**
	 * METODES EMPLEAT
	 */
	
	
	/**
	 * Metode per donar d'alta un empleat confeccionant la crida i enviant-la
	 * @param empleat. Rep un empleat a donar d'alta.
	 * @return Retorna resposta afirmativa si ha s'ha donat d'alta
	 */
	public Boolean altaEmpleat(Empleat empleat) {
		return enviarCridaSimple(empleat.altaJSon(login.getCodiSessio()));
	}
	
	/**
	 * Metode per donar de baixa un empleat confeccionant la crida i enviant-la
	 * @param codi. Rep un codi del quan es el id del registre a borrar.
	 * @return Retorna resposta afirmativa si s'ha pogut borrar.
	 */
	public Boolean baixaEmpleat(int codi) {		
		return enviarCridaSimple(Empleat.baixaJSon(login.getCodiSessio(), codi));	
	}
	
	/**
	 * Metode per modificar un empleat confeccionant la crida i enviant-la
	 * @param empleat. Rep un empleat el qual sera modificara al actual registre persistent.
	 * @return Retorna resposta afirmativa si ha s'ha pogut modificar
	 */
	public Boolean modiEmpleat(Empleat empleat) {
		return enviarCridaSimple(empleat.modiJSon(login.getCodiSessio()));	
	}
	
	/**
	 * Metode per llistar empleats confeccionant la crida i enviant-la
	 * si no rebem els parametres buits retornem tots els registres
	 * @param camp. Rep un parametre com camp on s'ha de buscar
	 * @param valor. Rep un parametre com a valor a buscar
	 * @param ordre. Rep un parametre per llistar en aquell ordre
	 * @return Retorna en forma d'array de Empleats
	 */
	public Empleat[] llistarEmpleat(String camp, String valor, String ordre) {
		JSONArray arr = enviarCridaRetornObjectes(Empleat.llistatJSon(login.getCodiSessio(), camp, valor, ordre));
		if (arr == null) {
			return null;
		}else {
			empleats = new Empleat[arr.length()];
			for(int i=0; i<arr.length(); i++){   
				  JSONObject o = arr.getJSONObject(i);
				  empleats[i]= new Empleat();
				  empleats[i].setCodi(o.getInt("codiEmpleat"));
				  empleats[i].setNom(o.getString("nomEmpleat"));
				  empleats[i].setCognoms(o.getString("cognomsEmpleat"));
				  empleats[i].setCodiDepartament(o.getInt("codiDepartament"));
				  empleats[i].setNomDep(o.getString("nomDepartament"));
			}
			return empleats;			
		}
	}
	
	/**
	 * Metode per consultar un empleat confeccionant la crida i enviant-la
	 * @param codi. Rep codi del qual es el codi del empleat a consultar
	 * @return Retorna un empleat el qual volem consultar
	 */
	public Empleat consultaIndEmpleat(int codi) {
		String resposta="";
		try {
			resposta = TalkToServer.connection(Empleat.consultaJSon(login.getCodiSessio(), codi));
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
			
		JSONObject jsonUsuari= new JSONObject(resposta);	

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			Empleat empleat= new Empleat();			
			
			JSONObject dades = jsonUsuari.getJSONObject("dades");
			empleat.setCodi(dades.getInt("codiEmpleat"));
			empleat.setDni(dades.getString("dni"));
			empleat.setNom(dades.getString("nom"));
			empleat.setCognoms(dades.getString("cognoms"));
			empleat.setDataNaixament(dades.getString("dataNaixement"));
			empleat.setAdreca(dades.getString("adreca"));
			empleat.setTelefon(dades.getString("telefon"));
			empleat.setEmail(dades.getString("email"));
			empleat.setCodiDepartament(dades.getInt("codiDepartament"));
			empleat.setNomDep(dades.getString("nomDepartament"));
			empleat.setUsuari(dades.getString("usuari"));
			empleat.setActiu(dades.getBoolean("actiu"));
			
			return empleat;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}			
			
	}
	
	
	
	/**
	 * METODES ESTUDIANT
	 */
	
	
	/**
	 * Metode per donar d'alta un estudiant confeccionant la crida i enviant-la
	 * @param estudiant. Rep un estudiant a donar d'alta.
	 * @return Retorna resposta afirmativa si ha s'ha donat d'alta
	 */
	public Boolean altaEstudiant(Estudiant estudiant) {
		return enviarCridaSimple(estudiant.altaJSon(login.getCodiSessio()));
	}
	
	/**
	 * Metode per donar de baixa un estudiant confeccionant la crida i enviant-la
	 * @param codi. Rep un codi del quan es el id del registre a borrar.
	 * @return Retorna resposta afirmativa si s'ha pogut borrar.
	 */
	public Boolean baixaEstudiant(int codi) {		
		return enviarCridaSimple(Estudiant.baixaJSon(login.getCodiSessio(), codi));	
	}
	
	/**
	 * Metode per modificar un estudiant confeccionant la crida i enviant-la
	 * @param estudiant. Rep un estudiant el qual sera modificat al actual registre persistent.
	 * @return Retorna resposta afirmativa si ha s'ha pogut modificar
	 */
	public Boolean modiEstudiant(Estudiant estudiant) {
		return enviarCridaSimple(estudiant.modiJSon(login.getCodiSessio()));	
	}
	
	/**
	 * Metode per llistar estudiants confeccionant la crida i enviant-la
	 * si no rebem els parametres buits retornem tots els registres
	 * @param camp. Rep un parametre com camp on s'ha de buscar
	 * @param valor. Rep un parametre com a valor a buscar
	 * @param ordre. Rep un parametre per llistar en aquell ordre
	 * @return Retorna en forma d'array de Estudiants
	 */
	public Estudiant[] llistarEstudiant(String camp, String valor, String ordre) {
		JSONArray arr = enviarCridaRetornObjectes(Estudiant.llistatJSon(login.getCodiSessio(), camp, valor, ordre));
		if (arr == null) {
			return null;
		}else {
			estudiants = new Estudiant[arr.length()];
			for(int i=0; i < arr.length(); i++){   
				  JSONObject o = arr.getJSONObject(i);
				  estudiants[i]= new Estudiant();
				  estudiants[i].setCodi(o.getInt("codiEstudiant"));
				  estudiants[i].setNom(o.getString("nomEstudiant"));
				  estudiants[i].setCognoms(o.getString("cognoms"));
			}
			return estudiants;			
		}
	}
	
	/**
	 * Metode per consultar un estudiant confeccionant la crida i enviant-la
	 * @param codi. Rep codi del qual es el codi del estudiant a consultar
	 * @return Retorna un estudiant el qual volem consultar
	 */
	public Estudiant consultaIndEstudiant(int codi) {
		String resposta="";
		try {
			resposta = TalkToServer.connection(Estudiant.consultaJSon(login.getCodiSessio(), codi));
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
			
		JSONObject jsonUsuari= new JSONObject(resposta);	

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			Estudiant estudiant= new Estudiant();			
			
			JSONObject dades = jsonUsuari.getJSONObject("dades");
			estudiant.setCodi(dades.getInt("codiEstudiant"));
			estudiant.setDni(dades.getString("dni"));
			estudiant.setNom(dades.getString("nomEstudiant"));
			estudiant.setCognoms(dades.getString("cognoms"));
			estudiant.setDataNaixament(dades.getString("dataNaixement"));
			estudiant.setAdreca(dades.getString("adreca"));
			estudiant.setTelefon(dades.getString("telefon"));
			estudiant.setEmail(dades.getString("email"));
			estudiant.setMatriculat(dades.getBoolean("registrat"));
			
			return estudiant;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}			
			
	}
	
	

	
	/**
	 * METODES BECA
	 */
	
	
	/**
	 * Metode per donar d'alta una beca confeccionant la crida i enviant-la
	 * @param beca. Rep una beca a donar d'alta.
	 * @return Retorna resposta afirmativa si ha s'ha donat d'alta
	 */
	public Boolean altaBeca(Beca beca) {
		return enviarCridaSimple(beca.altaJSon(login.getCodiSessio()));
	}
	
	/**
	 * Metode per donar de baixa una beca confeccionant la crida i enviant-la
	 * @param codi. Rep un codi del quan es el id del registre a borrar.
	 * @return Retorna resposta afirmativa si s'ha pogut borrar.
	 */
	public Boolean baixaBeca(int codi) {		
		return enviarCridaSimple(Beca.baixaJSon(login.getCodiSessio(), codi));	
	}
	
	/**
	 * Metode per modificar un beca confeccionant la crida i enviant-la
	 * @param beca. Rep un beca el qual sera modificara al actual registre persistent.
	 * @return Retorna resposta afirmativa si ha s'ha pogut modificar
	 */
	public Boolean modiBeca(Beca beca) {
		return enviarCridaSimple(beca.modiJSon(login.getCodiSessio()));	
	}
	
	/**
	 * Metode per llistar beques confeccionant la crida i enviant-la
	 * si no rebem els parametres buits retornem tots els registres
	 * @param camp. Rep un parametre com camp on s'ha de buscar
	 * @param valor. Rep un parametre com a valor a buscar
	 * @param ordre. Rep un parametre per llistar en aquell ordre
	 * @return Retorna en forma d'array de Beques
	 */
	public Beca[] llistarBeca(String camp, String valor, String ordre) {
		JSONArray arr = enviarCridaRetornObjectes(Beca.llistatJSon(login.getCodiSessio(), camp, valor, ordre));
		if (arr == null) {
			return null;
		}else {
			beques = new Beca[arr.length()];
			for(int i=0; i<arr.length(); i++){   
				  JSONObject o = arr.getJSONObject(i);
				  beques[i]= new Beca();
				  beques[i].setCodi(o.getInt("codiBeca"));
				  beques[i].setImportInicial(o.getDouble("importInicial"));
				  beques[i].setNomCognomsEstudiant(o.getString("nomEstudiant") + " " + o.getString("cognomsEstudiant"));
				  beques[i].setNomServei(o.getString("nomServei"));
				  beques[i].setAdjudicant(o.getString("adjudicant"));
				  beques[i].setImportRestant(o.getDouble("importRestant"));
				  beques[i].setFinalitzada(o.getBoolean("finalitzada"));
			}
			return beques;			
		}
	}
	
	/**
	 * Metode per consultar un beca confeccionant la crida i enviant-la
	 * @param codi. Rep codi del qual es el codi del beca a consultar
	 * @return Retorna un beca el qual volem consultar
	 */
	public Beca consultaIndBeca(int codi) {
		String resposta="";
		try {
			resposta = TalkToServer.connection(Beca.consultaJSon(login.getCodiSessio(), codi));
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
			
		JSONObject jsonUsuari= new JSONObject(resposta);	

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			Beca beca= new Beca();			
			
			JSONObject dades = jsonUsuari.getJSONObject("dades");
			beca.setCodi(dades.getInt("codiBeca"));
			beca.setAdjudicant(dades.getString("adjudicant"));
			beca.setImportInicial(dades.getDouble("importInicial"));
			beca.setImportRestant(dades.getDouble("importRestant"));
			beca.setCodiEstudiant(dades.getInt("codiEstudiant"));
			beca.setCodiServei(dades.getInt("codiServei"));
			beca.setFinalitzada(dades.getBoolean("finalitzada"));
			beca.setNomCognomsEstudiant(dades.getString("nomEstudiant") + " " + dades.getString("cognomsEstudiant"));
			beca.setNomServei(dades.getString("nomServei"));
		        	
			return beca;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}			
			
	}
	
	
	
	
	
	/**
	 * METODES SESSIO
	 */
	
	
	/**
	 * Metode per donar d'alta una sessio confeccionant la crida i enviant-la
	 * @param sessio. Rep una sessio a donar d'alta.
	 * @return Retorna resposta afirmativa si ha s'ha donat d'alta
	 */
	public Boolean altaSessio(Sessio sessio) {
		return enviarCridaSimple(sessio.altaJSon(login.getCodiSessio()));
	}

	/**
	 * Metode per donar de baixa una sessio confeccionant la crida i enviant-la
	 * @param codi. Rep un codi del quan es el id del registre a borrar.
	 * @return Retorna resposta afirmativa si s'ha pogut borrar.
	 */
	public Boolean baixaSessio(int codi) {		
		return enviarCridaSimple(Sessio.baixaJSon(login.getCodiSessio(), codi));	
	}
	
	/**
	 * Metode per modificar un beca confeccionant la crida i enviant-la
	 * @param sessio. Rep una sessio el qual sera modificara al actual registre persistent.
	 * @return Retorna resposta afirmativa si ha s'ha pogut modificar
	 */
	public Boolean modiSessio(Sessio sessio) {
		return enviarCridaSimple(sessio.modiJSon(login.getCodiSessio()));	
	}
	
	/**
	 * Metode per llistar sessions confeccionant la crida i enviant-la
	 * si no rebem els parametres buits retornem tots els registres
	 * @param camp. Rep un parametre com camp on s'ha de buscar
	 * @param valor. Rep un parametre com a valor a buscar
	 * @param ordre. Rep un parametre per llistar en aquell ordre
	 * @return Retorna en forma d'array de sessions
	 */
	public Sessio[] llistarSessio(String camp, String valor, String ordre) {
		JSONArray arr = enviarCridaRetornObjectes(Sessio.llistatJSon(login.getCodiSessio(), camp, valor, ordre));
		if (arr == null) {
			return null;
		}else {
			sessions = new Sessio[arr.length()];
			for(int i=0; i<arr.length(); i++){   
				  JSONObject o = arr.getJSONObject(i);
				  sessions[i]= new Sessio();
				  sessions[i].setCodi(o.getInt("codiSessio"));
				  sessions[i].setNomEmpleat(o.getString("nomProfessor"));
				  sessions[i].setCognomEmpleat(o.getString("cognomsProfessor"));
				  sessions[i].setNomEstudiant(o.getString("nomEstudiant"));
				  sessions[i].setCognomsEstudiant(o.getString("cognomsEstudiant"));
				  sessions[i].setNomServei(o.getString("nomServei"));
				  
				  String dataIHora = o.getString("dataIHora");
				  String[] dataHora = dataIHora.split(" ");  	//[0]data [1]hora
				  String [] d =dataHora[0].split("-");  		//[0]any [1]mes [2]dia
				  String [] t =dataHora[1].split(":");		//[0]hora [1]min [2]seg
				  
				  LocalDateTime ldt = LocalDateTime.of(Convert.toInt(d[0]), Convert.toInt(d[1]), 
						  Convert.toInt(d[2]) , Convert.toInt(t[0]), Convert.toInt(t[1]));
				  sessions[i].setDataIHora( ldt );
			}
			return sessions;			
		}
	}
	
	/**
	 * Metode per consultar un sessio confeccionant la crida i enviant-la
	 * @param codi. Rep codi del qual es el codi de la sessio a consultar
	 * @return Retorna un sessio el qual volem consultar
	 */
	public Sessio consultaIndSessio(int codi) {
		String resposta="";
		try {
			resposta = TalkToServer.connection(Sessio.consultaJSon(login.getCodiSessio(), codi));
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
			
		JSONObject jsonUsuari= new JSONObject(resposta);	

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			Sessio sessio= new Sessio();			
			
			JSONObject dades = jsonUsuari.getJSONObject("dades");
			sessio.setCodi(dades.getInt("codiSessio"));
			sessio.setCodiEmpleat(dades.getInt("codiProfessor"));
			sessio.setNomEmpleat(dades.getString("nomProfessor"));
			sessio.setCognomEmpleat(dades.getString("cognomsProfessor"));
			sessio.setCodiEstudiant(dades.getInt("codiEstudiant"));
			sessio.setNomEstudiant(dades.getString("nomEstudiant"));
			sessio.setCognomsEstudiant(dades.getString("cognomsEstudiant"));
			sessio.setCodiServei(dades.getInt("codiServei"));
			sessio.setNomServei(dades.getString("nomServei"));
			  
			String dataIHora = dades.getString("dataIHora");
			String[] dataHora = dataIHora.split(" ");  	//[0]data [1]hora
			String [] d =dataHora[0].split("-");  		//[0]any [1]mes [2]dia
			String [] t =dataHora[1].split(":");		//[0]hora [1]min [2]seg
			  
			LocalDateTime ldt = LocalDateTime.of(Convert.toInt(d[0]), Convert.toInt(d[1]), 
				Convert.toInt(d[2]) , Convert.toInt(t[0]), Convert.toInt(t[1]));
			sessio.setDataIHora( ldt );
		        	
			return sessio;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}			
			
	}
}
