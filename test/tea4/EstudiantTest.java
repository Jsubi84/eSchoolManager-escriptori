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
public class EstudiantTest {
	
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
		Assertions.assertTrue(cO.altaEstudiant(estudiant));
	}	
	
	/**
	 * Test
	 * Modificació d'un estudiant a la BD
	 */	
	@Test 
	@Order(2)
	public void modificarEstudiant() {	
				
		Estudiant estudiantP = new Estudiant(); 
		estudiantP.setCodi(23);
		estudiantP.setNom("Josep");		
		estudiantP.setCognoms("Foronall");		
		estudiantP.setAdreca("barcelona 18");
		estudiantP.setDataNaixament("1992-09-11");
		estudiantP.setDni("66666666S");;
		estudiantP.setMatriculat(false);
		estudiantP.setTelefon("333333333");
		estudiantP.setEmail("josep@gmail.com");
		
		cO.modiEstudiant(estudiantP);
		Estudiant estudian = cO.consultaIndEstudiant(23);
				
		Assertions.assertEquals(estudiantP.getNom(), estudian.getNom());
		Assertions.assertEquals(estudiantP.getCognoms(), estudian.getCognoms());
		Assertions.assertEquals(estudiantP.getAdreca(), estudian.getAdreca());
		Assertions.assertEquals(estudiantP.getDataNaixament(), estudian.getDataNaixament());
		Assertions.assertEquals(estudiantP.getDni(), estudian.getDni());
		Assertions.assertEquals(estudiantP.getMatriculat(), estudian.getMatriculat());
		Assertions.assertEquals(estudiantP.getTelefon(), estudian.getTelefon());
		Assertions.assertEquals(estudiantP.getEmail(), estudian.getEmail());
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
	@Order(8)
	public void baixaEstudiants() {		
		Assertions.assertTrue(cO.baixaEstudiant(22));
		Assertions.assertTrue(cO.baixaEstudiant(23));
		Assertions.assertTrue(cO.baixaEstudiant(27));
	}
	
	
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
		
    	estudiant = new Estudiant("77723953G", "Joel", "Canbrai", "1958-12-05", "c/torrent 15", "67375637",
    			"jcanbrai@hotmail.com", false); //Codi 24
    	estudiant2 = new Estudiant("55555444A", "Jony", "Deep", "1956-11-06", "c/brodway bcn", "54643374",
    			"jonyDeep@hotmail.com", true); //Codi 25
    }
}
