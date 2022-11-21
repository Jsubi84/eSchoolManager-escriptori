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
	 * @return int valor del codi departament escollit
	 */
	public static int splitCombo(String valorCombo) {
		String select = valorCombo;
		String[] codiDept = select.split("-");
		String comboCodiDept = codiDept[0];
		return Integer.parseInt(comboCodiDept);
	}
	
	
}
