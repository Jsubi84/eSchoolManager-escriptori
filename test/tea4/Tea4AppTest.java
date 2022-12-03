package tea4;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import controller.ControllerOperation;
import controller.ControllerView;
import model.Beca;
import model.Departament;
import model.Escola;
import model.Estudiant;
import model.Login;
import model.Sessio;



/**
 * @author Jordi Subirana 
 * Classe de test per a TEA4 del projecte final
 */
@TestMethodOrder(OrderAnnotation.class)
public class Tea4AppTest {
	
	//Variables necesaries per al controllerOperation
	private ControllerView controlView = new ControllerView() ;
	private ControllerOperation cO = new ControllerOperation(controlView); 
	private Login login;
	private Estudiant estudiant, estudiant2;
	private Beca beca;
	private Sessio sessio;
	
	
	/**
	 * Test
	 * Generació de classes necesaries per els test
	 */
	@BeforeEach
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
	@Order(2)
	public void altaEstudiant() {		
		Assertions.assertTrue(cO.altaEstudiant(estudiant));
		Assertions.assertTrue(cO.altaEstudiant(estudiant2));
	}	
	
	
	/**
	 * Test
	 * Baixa d'un estudiant a la BD
	 * Borrem tota la taula d'estudiants, comprovem que tenim la taula buida.
	 */	
	@Test 
	@Order(1)
	public void baixaEstudiants() {
		
		cO.altaEstudiant(estudiant);
		cO.altaEstudiant(estudiant2);

		Estudiant[] estudiants = cO.llistarEstudiant("", "", "");
		for(Estudiant e : estudiants){
			cO.baixaEstudiant(e.getCodi());			
		}
		estudiants = cO.llistarEstudiant("", "", "");
		
		Assertions.assertEquals(estudiants.length, 0);
	}
	

	/**
	 * Test
	 * Modificació d'un estudiant a la BD
	 */	
	@Test 
	@Order(3)
	public void modificarEstudiant() {	
		
		cO.altaEstudiant(estudiant);
		
		Estudiant[] estudiants = cO.llistarEstudiant("", "", "");
		Estudiant estudiantPerModi = cO.consultaIndEstudiant(estudiants[estudiants.length-1].getCodi());
		
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
	@Order(4)
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
	
	
	
//	/**
//	 * TEST BECA
//	 */	
//	
//	/**
//	 * Test
//	 * Alta d'uns beca a la BD
//	 */	
//	@Test 
//	public void altaBeca() {		
//	}	
//	
//	
//	/**
//	 * Test
//	 * Baixa d'una beca a la BD
//	 * Borrem tota la taula de beques, comprovem que tenim la taula buida.
//	 */	
//	@Test 
//	public void baixaBeca() {
//	}
//	
//
//	/**
//	 * Test
//	 * Modificació d'una beca a la BD
//	 */	
//	@Test 
//	public void modificarBeca() {	
//	}
//	
//	
//	
//	
//	
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

		//Generar estudiants
    	estudiant = new Estudiant("56562554A", "Jordi", "Agarrobo", "1978-10-04", "c/del sol 14 bcn", "354352435",
    			"algarrobo@gmail.com", true);
    	estudiant2 = new Estudiant("55555444A", "Jony", "Deep", "1956-11-06", "c/brodway bcn", "54643374",
    			"jonyDeep@hotmail.com", false);
    	
    	//Generar Becas
    	
    	//Generar Sessions
    	
    }
}
