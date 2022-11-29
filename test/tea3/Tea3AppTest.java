package tea3;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import controller.ControllerOperation;
import controller.ControllerView;
import model.Departament;
import model.Escola;
import model.Login;


/**
 * @author Jordi Subirana
 */
public class Tea3AppTest {
	
	//Variables necesaries per al controllerOperation
	private ControllerView controlView = new ControllerView() ;
	private ControllerOperation cO = new ControllerOperation(controlView); 
	private Login login;
	private Escola escola1;
	private Departament dept;
	
	/**
	 * Test
	 * Generació de classes necesaries per els test
	 */
	@Before
	public void setUp(){		
		generaDades();
	}

	
	/**
	 * Test
	 * Alta d'un departament a la BD
	 */	
	@Test 
	public void altaDepartament() {		
		Assertions.assertTrue(cO.altaDepartament(dept));
	}
	
	
	/**
	 * Test
	 * Modificació d'un departament a la BD
	 */	
	@Test 
	public void modiDepartament() {	
		
		dept = cO.consultaIndDepartaments(22);
		
		dept.setEscola(false);
		dept.setDepartament(false);
		
		Assertions.assertTrue(cO.modiDepartament(dept));
		Assertions.assertNotEquals(dept.getEscola(), true);
		Assertions.assertNotEquals(dept.getDepartament(), true);
	}
	
	
	/**
	 * Test
	 * Baixa d'un departament a la BD
	 */	
	@Test 
	public void baixaDepartament() {		
		Assertions.assertTrue(cO.baixaDepartament(22));
	}
	
	
	/**
	 * Test
	 * verifiacar que podem modificar i consultar les dades d'escola
	 */
	@Test 
	public void actualitzarnomEscola() {		
		Escola escolaOriginal = cO.consultaIndEscola(escola1.getCodi());
		cO.actualitzarEscola(escola1);
		escolaOriginal = cO.consultaIndEscola(escola1.getCodi());
		
		assertEquals(escola1.getNom(), escolaOriginal.getNom());
	}
	
	
	
	   /**
     * Genera dades d'exemple a la base de dades
     */
    private void generaDades() {
    	
    	//Generem credencials 
		login = new Login ("p.gomez","passtest1");
		login.CheckLogin();
		
		cO.setLogin(login);
    	
    	escola1 = new Escola();
    	escola1.setNom("Escola 1");
    	escola1.setAdreca("c/sol, 3");
    	escola1.setTelefon("935545859");
    	escola1.setCodi(1);
    	
    	dept = new Departament("Generic",true,true,true,true,true,true,true, true);
    	
    }

}
