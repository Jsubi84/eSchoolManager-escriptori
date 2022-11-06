package tea2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.Login;


/**
 * @author Jordi Subirana
 */
public class LoginTest {
	
	/**
	 * Test per comprovar si les credencials son correstes!
	 * Comprova si la credencial es correcte 
	 */
	@Test
	public void credencialsCorrectes() {
		Login login = new Login ("jordi","jordi");	
		Assertions.assertTrue(login.CheckLogin());
	}

	/**
	 * Test per comprovar si les credencials son correstes!
	 * Comprova si la credencial es incorrecte 
	 */	
	@Test
	public void credencialsIncorrectes() {
		Login login = new Login ("jordi","jordi");		
		Assertions.assertFalse(login.CheckLogin());
	}

}
