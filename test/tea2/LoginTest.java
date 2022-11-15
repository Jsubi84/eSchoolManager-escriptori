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
		Login login = new Login ("jordi","jordi");	// Modificar per valors correctes de la BD
		Assertions.assertTrue(login.CheckLogin());
	}

	/**
	 * Test per comprovar si les credencials son correstes!
	 * Comprova si la credencial es incorrecte 
	 */	
	@Test
	public void credencialsIncorrectes() {
		Login login = new Login ("jordi","noemi");		
		Assertions.assertFalse(login.CheckLogin());
	}

	
	/**
	 * Test per comprovar si sense posar res ens conecta!
	 * Comprova si els valors estan buits no ens deixa conectar 
	 */	
	@Test
	public void valorsBuits() {
		Login login = new Login ("","");		
		Assertions.assertFalse(login.CheckLogin());
	}
	
	/**
	 * Test per comprovar si te el valor del camp d'usuari buit!
	 */	
	@Test
	public void valorUsuariBuit() {
		Login login = new Login ("","Jordi");		
		Assertions.assertFalse(login.CheckLogin());
	}

	/**
	 * Test per comprovar si te el valor del camp contrasenya buit!
	 */	
	@Test
	public void valorContrasenyaBuit() {
		Login login = new Login ("Jordi","");		
		Assertions.assertFalse(login.CheckLogin());
	}
}
