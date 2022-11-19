package util;

public class Convert {

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
	
	
	public static boolean isNotNumericDouble(String cadena) {
        boolean result;
        try {
            Integer.parseInt(cadena);
            result = false;
        } catch (NumberFormatException excepcion) {
            result = true;
        }
        return result;
    }
	
}
