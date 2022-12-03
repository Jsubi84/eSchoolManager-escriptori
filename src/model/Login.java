package model;

import java.io.IOException;
import org.json.JSONObject;
import util.TalkToServer;


/**
 * @author Jordi Subirana
 *
 * Classe Login per gestionar l'acces a l'aplicació
 */
public class Login {
	
	private static final String RESPOSTA_OK= "OK";
	private static final String DATA_MISSING= "Falta introduir alguna dada per poder entrar";
	private static final String CONNECTION_MISSING= "No s'ha pogut establir la connexió";

	private String usuari, contrasenya, codiSessio, nom, nomDepartament, incidencia;
	private Boolean pEscola, pDepartament, pEmpleat, pEstudiant, pServei, pBeca, pSessio, pInforme;
	private int codiEmpleat;
	
	
	/**
	 * Constructor per defecte.
	 */
	public Login() {
	}

	
	/**
	 * Constructor inicial
	 * @param usuari
	 * @param contrasenya
	 */
	public Login (String usuari, String contrasenya){
		this.setUsuari(usuari);
		this.setContrasenya(contrasenya);
	}
	
	
	/**
	 * Setters i Getters
	 */
	public String getCodiSessio() {
		return codiSessio;
	}

	public void setCodiSessio(String codiSessio) {
		this.codiSessio = codiSessio;
	}

	public String getNomDepartament() {
		return nomDepartament;
	}

	public void setNomDepartament(String nomDepartament) {
		this.nomDepartament = nomDepartament;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	public String getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	public Boolean getpEscola() {
		return pEscola;
	}

	public void setpEscola(Boolean pEscola) {
		this.pEscola = pEscola;
	}

	public Boolean getpDepartament() {
		return pDepartament;
	}

	public void setpDepartament(Boolean pDepartament) {
		this.pDepartament = pDepartament;
	}

	public Boolean getpEmpleat() {
		return pEmpleat;
	}

	public void setpEmpleat(Boolean pEmpleat) {
		this.pEmpleat = pEmpleat;
	}

	public Boolean getpEstudiant() {
		return pEstudiant;
	}

	public void setpEstudiant(Boolean pEstudiant) {
		this.pEstudiant = pEstudiant;
	}

	public Boolean getpServei() {
		return pServei;
	}

	public void setpServei(Boolean pServei) {
		this.pServei = pServei;
	}

	public Boolean getpBeca() {
		return pBeca;
	}

	public void setpBeca(Boolean pBeca) {
		this.pBeca = pBeca;
	}

	public Boolean getpSessio() {
		return pSessio;
	}

	public void setpSessio(Boolean pSessio) {
		this.pSessio = pSessio;
	}

	public Boolean getpInforme() {
		return pInforme;
	}

	public void setpInforme(Boolean pInforme) {
		this.pInforme = pInforme;
	}

	public int getCodiEmpleat() {
		return codiEmpleat;
	}

	public void setCodiEmpleat(int codiEmpleat) {
		this.codiEmpleat = codiEmpleat;
	}


/**
 * Metode encarregat de verificar les credencials per accedir.
 * 
 * @return LoginOK En retorna si el Login ha estat exitos.
 */
public Boolean CheckLogin(){
	
		String crida = null, resposta = null;
		incidencia = "";
		
		if (!(getUsuari().isBlank() || getContrasenya().isBlank()) ) {
			
			//Exectuar conversor i obtenir missatge
			crida = this.loginJSon();		
			
			//Tranferencia crida / recepcio de resposta
			try {
				resposta = TalkToServer.connection(crida);
			} catch (IOException e) { 
				System.out.println(e.getMessage());
			}
			
			if (resposta != null) {	
				
				JSONObject jsonUsuari = new JSONObject(resposta);
				
				if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
					JSONObject dades = (JSONObject) jsonUsuari.get("dades");
					
					setCodiSessio((String) dades.get("codiSessio"));
					setNomDepartament((String)dades.get("nomDepartament"));
					setNom((String) dades.get("nom"));
					setCodiEmpleat((int) dades.get("codiEmpleat"));
					
					
					JSONObject permisos = (JSONObject) dades.get("permisos");
					
					setpEscola((Boolean) permisos.get("escola"));
					setpDepartament((Boolean) permisos.get("departament"));
					setpEmpleat((Boolean) permisos.get("empleat"));
					setpEstudiant((Boolean) permisos.get("estudiant"));
					setpServei((Boolean) permisos.get("servei"));
					setpBeca((Boolean) permisos.get("beca"));
					setpSessio((Boolean) permisos.get("sessio"));
					setpInforme((Boolean) permisos.get("informe"));								
					return true;
					
				} else {
					//Missatge d'error en la part del servidor
					setIncidencia((String)jsonUsuari.get("missatge"));
					return false;
				}
				
			} else {
				//Missatge de conexio perduda o no resposta
				setIncidencia(CONNECTION_MISSING);
				return false;
			}

		}else {
			//Missatge informatiu falta alguna dada
			setIncidencia(DATA_MISSING);
			return false;
		}	
	}


	/**
	 * @param usuari Usuari passat a pantalla login
	 * @param password Password passat a pantalla login
	 * @return crida Envia String en format JSON que conte la crida per fer el Login
	 */
	public String loginJSon () {
		JSONObject json = new JSONObject();		
		json.put("crida", "LOGIN");	
		JSONObject dades = new JSONObject();	
		dades.put("usuari", this.getUsuari());			
		dades.put("contrasenya", this.getContrasenya());	
		json.put("dades", dades);
		return json.toString();
	}
	
	
	
	/**
	 * @param codisessio
	 * @return crida String en format JSON que conte la crida per fer el Logout
	 */
	public String logoutJSon () {
		
		// Create Json and serialize
		JSONObject json = new JSONObject();
			json.put("crida", "LOGOUT");
			json.put("codiSessio", this.getCodiSessio());
		return json.toString();
	}
}



