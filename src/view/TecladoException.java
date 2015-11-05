package vista;

import javax.swing.JOptionPane;

/**
 *
 * @author luis mendez
 */
public class TecladoException {

    /**
     * Valida que el numero a parsear sea un entero, si se produce la excepcion retorna un -1
     * @param cadena
     * @param campo
     * @return 
     */
    public static int getEntero(String cadena, String campo) {
        int num;
        try {
            num = Integer.parseInt(cadena);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en el campo : " + campo + " no es correcto","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            num = -1;
        }
        return num;
    }

    public static long getLong(String cadena, String campo) {
        long num;
        try {
            num = Long.parseLong(cadena);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en el campo : " + campo + " no es correcto");
            num = -1;
        } catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en el campo : " + campo + " no es correcto");
            num = -1;
        }
        return num;
    }

    public static double getDouble(String cadena, String campo) {
        double num;
        try {
            num = Double.parseDouble((cadena));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en el campo : " + campo + " no es correcto");
            num = -1;
        } catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en el campo : " + campo + " no es correcto");
            num = -1;
        }
        return num;
    }

    public static float getFloat(String cadena, String campo) {
        float num;
        try {
            num = Float.parseFloat((cadena));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en el campo : " + campo + " no es correcto");
            num = -1;
        } catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en el campo : " + campo + " no es correcto");
            num = -1;
        }
        return num;
    }

    public static char getChar(String cadena) {
        char a;
        a = '0';
        try {
            a = (cadena).charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Usted no ha ingresado ningun valor ");
        }
        return a;
    }

    public static String getCadena(String cadena) {
        String a = "";
        do {
            a = JOptionPane.showInputDialog(cadena);
            if (a.length() == 0) {
                JOptionPane.showMessageDialog(null, "No ha ingresado ningun valor!");
            }
        } while (a.length() == 0);
        return a;
    }
}
