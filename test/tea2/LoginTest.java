package tea2;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.Login;


/**
 * @author Jordi Subirana
 *
 */
public class LoginTest {
	
	
	@Test
	public void test1() {
		
		JSONObject json = new JSONObject();		
		json.put("resposta", "OK");	
		JSONObject dades = new JSONObject();	
		dades.put("codiSessio","dsadsf34");			
		dades.put("nom", "jordi");
		dades.put("codiDepartament", 1);
		json.put("dades", dades);
		String resposta = json.toString();
		
		Login login = new Login ("jordi","jordi");		
		Assertions.assertTrue(login.evaluaResposta(resposta));
	}

	
	
	@Test
	public void test2() {
		JSONObject json = new JSONObject();		
		json.put("resposta", "NOK");	
		json.put("missatge", "Credencials no valides");
		String resposta = json.toString();

		Login login = new Login ("jordi","jordi");		
		Assertions.assertFalse(login.evaluaResposta(resposta));
	}

}
