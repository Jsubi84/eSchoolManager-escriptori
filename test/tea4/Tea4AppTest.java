package tea4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import controller.ControllerOperation;
import controller.ControllerView;
import model.Beca;
import model.Departament;
import model.Escola;
import model.Estudiant;
import model.Login;
import model.Servei;
import model.Sessio;



/**
 * @author Jordi Subirana 
 * Classe de test per a TEA4 del projecte final
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class Tea4AppTest {
	
	//Variables necesaries per al controllerOperation
	private ControllerView controlView = new ControllerView() ;
	private ControllerOperation cO = new ControllerOperation(controlView); 
	private Login login;
	private Estudiant estudiant, estudiant2;
	private Beca beca, beca2;
	private Sessio sessio, sessio2;
	private Servei servei, servei2;
	
	
	/**
	 * Test
	 * Generació de classes necesaries per els test
	 */
	@BeforeAll
	public void setUp(){		
		generaDades();
	}

	
	
	
	/**
	 * TEST ESTUDIANT
	 */	
	
	/**
	 * Test
	 * Alta d'un estudiant a la BD
	 */	
	@Test 
	@Order(1)
	public void altaEstudiant() {		
		Assertions.assertTrue(cO.altaEstudiant(estudiant2));
		Assertions.assertTrue(cO.altaEstudiant(estudiant));
	}	
	
	/**
	 * Test
	 * Modificació d'un estudiant a la BD
	 */	
	@Test 
	@Order(2)
	public void modificarEstudiant() {	
		
		Estudiant[] estudiants = cO.llistarEstudiant("", "", "");
		while (estudiants.length == 0){
			System.out.println("Buggg");
		}
		Estudiant estudiantPerModi = cO.consultaIndEstudiant(estudiants[1].getCodi());
		
		estudiantPerModi.setNom("Josep");		
		estudiantPerModi.setCognoms("Foronall");		
		estudiantPerModi.setAdreca("barcelona 18");
		estudiantPerModi.setDataNaixament("1992-09-11");
		estudiantPerModi.setDni("66666666S");;
		estudiantPerModi.setMatriculat(false);
		estudiantPerModi.setTelefon("333333333");
		estudiantPerModi.setEmail("josep@gmail.com");
		
		cO.modiEstudiant(estudiantPerModi);
		estudiantPerModi = cO.consultaIndEstudiant(estudiantPerModi.getCodi());
				
		Assertions.assertNotEquals(estudiantPerModi.getNom(), estudiant.getNom());
		Assertions.assertNotEquals(estudiantPerModi.getCognoms(), estudiant.getCognoms());
		Assertions.assertNotEquals(estudiantPerModi.getAdreca(), estudiant.getAdreca());
		Assertions.assertNotEquals(estudiantPerModi.getDataNaixament(), estudiant.getDataNaixament());
		Assertions.assertNotEquals(estudiantPerModi.getDni(), estudiant.getDni());
		Assertions.assertNotEquals(estudiantPerModi.getMatriculat(), estudiant.getMatriculat());
		Assertions.assertNotEquals(estudiantPerModi.getTelefon(), estudiant.getTelefon());
		Assertions.assertNotEquals(estudiantPerModi.getEmail(), estudiant.getEmail());
	}
	
	/**
	 * Test
	 * Llistar d'un estudiant a la BD
	 */	
	@Test
	@Order(3)
	public void llistarIConsultarEstudiant() {
		Estudiant[] estudiants = cO.llistarEstudiant("", "", "");
		Estudiant[] estudiantsConsultats = new Estudiant[estudiants.length];
		for(int i = 0; i < estudiants.length; ++i){
			estudiantsConsultats[i] = cO.consultaIndEstudiant(estudiants[i].getCodi());
		}		
		
		for(int i = 0; i < estudiants.length; ++i){
			Assertions.assertEquals(estudiants[i].getNom(), estudiantsConsultats[i].getNom());
			Assertions.assertEquals(estudiants[i].getCognoms(), estudiantsConsultats[i].getCognoms());
			Assertions.assertEquals(estudiants[i].getCodi(), estudiantsConsultats[i].getCodi());
		}		
	}
	
	
	/**
	 * Test
	 * Baixa d'un estudiant a la BD
	 * Borrem tota la taula d'estudiants, comprovem que tenim la taula buida.
	 */	
	@Test 
	@Order(4)
	public void baixaEstudiants() {
		Estudiant[] estudiants = cO.llistarEstudiant("", "", "");
		for(Estudiant e : estudiants){
			cO.baixaEstudiant(e.getCodi());			
		}
		estudiants = cO.llistarEstudiant("", "", "");
		
		Assertions.assertEquals(estudiants.length, 0);
	}
	
	
	
	/**
	 * TEST BECA
	 */	
	
	/**
	 * Test
	 * Alta d'uns beca a la BD
	 */	
	@Test 
	@Order(5)
	public void altaBeca() {
		cO.altaEstudiant(estudiant);
		cO.altaEstudiant(estudiant2);
		cO.altaServei(servei);
		cO.altaServei(servei2);		
		
		Estudiant[] estudiants = cO.llistarEstudiant("", "", "");
		
		estudiant.setCodi(estudiants[0].getCodi());
		estudiant2.setCodi(estudiants[1].getCodi());
		
		beca.setCodiEstudiant(estudiants[0].getCodi());
		beca2.setCodiEstudiant(estudiants[1].getCodi());
		
		Servei[] serveis = cO.llistarServei("", "", "");
		
		servei.setCodi(serveis[0].getCodi());
		servei2.setCodi(serveis[1].getCodi());
		
		beca.setCodiServei(serveis[0].getCodi());
		beca2.setCodiServei(serveis[1].getCodi());
		
		Assertions.assertTrue(cO.altaBeca(beca));
		Assertions.assertTrue(cO.altaBeca(beca2));
	}	
	
	/**
	 * Test
	 * Modificació d'una beca a la BD
	 */	
	@Test 
	@Order(6)
	public void modificarBeca() {
		Beca[] beques = cO.llistarBeca("", "", "");
		Beca becaPerModi = cO.consultaIndBeca(beques[beques.length - 1].getCodi());
			
		becaPerModi.setAdjudicant("Restrillo");	
		becaPerModi.setCodiEstudiant(estudiant2.getCodi());
		becaPerModi.setCodiServei(0);
		becaPerModi.setImportInicial(700.40);

		
		cO.modiBeca(becaPerModi);
		becaPerModi = cO.consultaIndBeca(becaPerModi.getCodi());
				
		Assertions.assertNotEquals(becaPerModi.getAdjudicant(), beca2.getAdjudicant());
		Assertions.assertNotEquals(becaPerModi.getCodiEstudiant(), beca2.getCodiEstudiant());
		Assertions.assertNotEquals(becaPerModi.getCodiServei(), beca2.getCodiServei());
		Assertions.assertNotEquals(becaPerModi.getImportInicial(), beca2.getImportInicial());
	}
	
	/**
	 * Test
	 * Llistar d'un beca a la BD
	 */	
	@Test
	@Order(7)
	public void llistarIConsultarBeca() {
		Beca[] beques = cO.llistarBeca("", "", "");
		Beca[] bequesConsultades = new Beca[beques.length];
		for(int i = 0; i < beques.length; ++i){
			bequesConsultades[i] = cO.consultaIndBeca(beques[i].getCodi());
		}		
		for(int i = 0; i < beques.length; ++i){
			Assertions.assertEquals(beques[i].getCodi(), bequesConsultades[i].getCodi());
			Assertions.assertEquals(beques[i].getCodiEstudiant(), bequesConsultades[i].getCodiEstudiant());
			Assertions.assertEquals(beques[i].getCodiServei(), bequesConsultades[i].getCodiEstudiant());
			Assertions.assertEquals(beques[i].getImportInicial(), bequesConsultades[i].getImportInicial());
			Assertions.assertEquals(beques[i].getAdjudicant(), bequesConsultades[i].getAdjudicant());
		}		
	}
	
	
	/**
	 * Test
	 * Baixa d'una beca a la BD
	 * Borrem tota la taula de beques, comprovem que tenim la taula buida.
	 */	
	@Test 
	@Order(8)
	public void baixaBeca() {
		Beca[] beques = cO.llistarBeca("", "", "");
		for(Beca b : beques){
			cO.baixaBeca(b.getCodi());			
		}
		beques = cO.llistarBeca("", "", "");
		
		Assertions.assertEquals(beques.length, 0);
	}
	
	
	
	
	
//	/**
//	 * TEST SESSIO
//	 */	
//	
//	/**
//	 * Test
//	 * Alta d'una sessio a la BD
//	 */	
//	@Test 
//	public void altaSessio() {		
//	}	
//	
//	
//	/**
//	 * Test
//	 * Baixa d'una sessio a la BD
//	 * Borrem tota la taula de sessions, comprovem que tenim la taula buida.
//	 */	
//	@Test 
//	public void baixaSessio() {
//	}
//	
//
//	/**
//	 * Test
//	 * Modificació d'una sessio a la BD
//	 */	
//	@Test 
//	public void modificarSessio() {	
//	}
//	
	
	/**
	 * Genera dades d'exemple a la base de dades
	 */
    private void generaDades() {
    	
    	//Generem credencials 
		login = new Login ("p.gomez","passtest1");
		login.CheckLogin();
		
		//Afegim el login al controllerOperations per tal de passar les
		//credencials per fer peticions
		cO.setLogin(login);    		
		
		
		//Borrar les taules implicades posar-les sense registre
		
		//Borrat de beques
//		Beca[] beques = cO.llistarBeca("", "", "");
//		for(Beca b : beques){
//			cO.baixaBeca(b.getCodi());			
//		}
		
		//Borrat d'estudiants
		Estudiant[] estudiants = cO.llistarEstudiant("", "", "");
		for(Estudiant e : estudiants){
			cO.baixaEstudiant(e.getCodi());			
		}
		
		//Borrat de serveis
		Servei[] serveis = cO.llistarServei("", "", "");
		for(Servei s : serveis){
			cO.baixaServei(s.getCodi());			
		}
		
		
		//Generar estudiants
    	estudiant = new Estudiant("56562554A", "Jordi", "Agarrobo", "1978-10-04", "c/del sol 14 bcn", "354352435",
    			"algarrobo@gmail.com", true);
    	estudiant2 = new Estudiant("55555444A", "Jony", "Deep", "1956-11-06", "c/brodway bcn", "54643374",
    			"jonyDeep@hotmail.com", true);
    
    	
    	//Generar Becas
    	beca = new Beca ( 0 , 0 , "Generalitat", 600.00);
    	beca2 = new Beca ( 0 , 0 , "Diputacio", 650.00);
    	
    	
    	servei = new Servei("Logo", 30, 40.0);
    	servei2 = new Servei("Psico", 50, 80.0);
    	
    	//Generar Sessions
    	
    }
}
