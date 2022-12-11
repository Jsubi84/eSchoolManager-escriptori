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
public class BecaTest {
	
	//Variables necesaries per al controllerOperation
	private ControllerView controlView = new ControllerView() ;
	private ControllerOperation cO = new ControllerOperation(controlView); 
	private Login login;
	private Estudiant estudiant, estudiant2;
	private Beca beca, beca2;

	
	/**
	 * Test
	 * Generació de classes necesaries per els test
	 */
	@BeforeAll
	public void setUp(){		
		generaDades();
	}

	
	/**
	 * TEST BECA
	 */	
	
	/**
	 * Test
	 * Alta d'uns beca a la BD
	 */	
	@Test 
	@Order(4)
	public void altaBeca() {		
		Assertions.assertTrue(cO.altaBeca(beca));
	}	
	
	/**
	 * Test
	 * Modificació d'una beca a la BD
	 */	
	@Test 
	@Order(5)
	public void modificarBeca() {
		Beca[] beques = cO.llistarBeca("", "", "");
		Beca becaPerModi = cO.consultaIndBeca(beques[beques.length - 1].getCodi());
			
		becaPerModi.setAdjudicant("Restrillo");	
		becaPerModi.setCodiEstudiant(22);
		becaPerModi.setCodiServei(20);
		becaPerModi.setImportInicial(700.40);

		
		cO.modiBeca(becaPerModi);
		beca2 = cO.consultaIndBeca(becaPerModi.getCodi());
				
		Assertions.assertEquals(becaPerModi.getAdjudicant(), beca2.getAdjudicant());
		Assertions.assertEquals(becaPerModi.getCodiEstudiant(), beca2.getCodiEstudiant());
		Assertions.assertEquals(becaPerModi.getCodiServei(), beca2.getCodiServei());
		Assertions.assertEquals(becaPerModi.getImportInicial(), beca2.getImportInicial());
	}
	
	/**
	 * Test
	 * Llistar d'un beca a la BD
	 */	
	@Test
	@Order(6)
	public void llistarIConsultarBeca() {
		Beca[] beques = cO.llistarBeca("", "", "");
		Beca becaConsulta = new Beca();
		for(int i = 0; i < beques.length; ++i){
			becaConsulta = cO.consultaIndBeca(beques[i].getCodi());
			Assertions.assertEquals(beques[i].getCodi(), becaConsulta.getCodi());
			Assertions.assertEquals(beques[i].getImportInicial(), becaConsulta.getImportInicial());
			Assertions.assertEquals(beques[i].getAdjudicant(), becaConsulta.getAdjudicant());			
		}			
	}
	
	
	/**
	 * Test
	 * Baixa d'una beca a la BD
	 * Borrem tota la taula de beques, comprovem que tenim la taula buida.
	 */	
	@Test 
	@Order(7)
	public void baixaBeca() {		
		Assertions.assertTrue(cO.baixaBeca(24));
		Assertions.assertTrue(cO.baixaBeca(25));
		Assertions.assertTrue(cO.baixaBeca(26));
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
		
    	beca = new Beca ( 22 , 20 , "Generalitat", 600.00); //
    }
}
