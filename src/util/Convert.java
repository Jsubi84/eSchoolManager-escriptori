package util;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Convert {
	
	/**
	 * Funcio per parsejar si un valor no es int
	 * @param cadena
	 * @return resposta de si no es un valor int
	 */
	public static boolean isNotNumericInt(String cadena) {
        boolean result;
        try {
            Integer.parseInt(cadena);
            result = false;
        } catch (NumberFormatException excepcion) {
            result = true;
        }
        return result;
    }
	
	
	/**
	 * Funcio convertir string a int
	 * @param cadena
	 * @return int
	 */	
	public static int toInt (String cadena) {
        int result = 0;
        try {
            result = Integer.parseInt(cadena);
        } catch (NumberFormatException excepcion) {
        	return result;
        }
        return result;
    }
	
	
	/**
	 * Funcio per parsejar si un valor no es Double
	 * @param cadena
	 * @return resposta de si no es un valor Double
	 */
	public static boolean isNotNumericDouble(String cadena) {
        boolean result;
        try {
            Double.parseDouble(cadena);
            result = false;
        } catch (NumberFormatException excepcion) {
            result = true;
        }
        return result;
    }
	
	
	/**
	 * Extreiem el valor del camps combiants de la combobox
	 * @param valorCombo Entrada del valor de la combobox
	 * @return int valor del codi escollit
	 */
	public static int splitCombo(String valorCombo) {
		String select = valorCombo;
		String[] codi = select.split("-");
		String comboCodi = codi[0];
		return Integer.parseInt(comboCodi);
	}
	
	
	/**
	 * Funcio per adaptar l'icon al tamany adecuat del missatge
	 * @param ruta
	 * @return icon
	 */
	public Icon returIcon(String ruta) {
		Icon icon = null;
		try {
			ImageIcon imgIcon = new ImageIcon(getClass().getResource(ruta));
		    Image imgEscalada = imgIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		    icon = new ImageIcon(imgEscalada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return icon;
	}
}
