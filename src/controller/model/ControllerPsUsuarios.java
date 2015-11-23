/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import controller.PsUsuariosJpaController;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import model.PsUsuarios;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Juan Camilo Giron
 */
public class ControllerPsUsuarios extends PlainDocument {

    ControllerFecha controllerFecha;
    PsUsuariosJpaController ctrlUsuariosJpaController;
    String[] sessionUsuario;

    public PsUsuarios crearUsuarios(String[] argsUsuario) {
        controllerFecha = new ControllerFecha();
        PsUsuarios psUsuarios;

        if (!argsUsuario[0].equals("") && !argsUsuario[1].equals("") && !argsUsuario[2].equals("") && !argsUsuario[3].equals("") && !argsUsuario[4].equals("") && !argsUsuario[5].equals("")) {
            if (argsUsuario[3].length() >= 5) {

                psUsuarios = new PsUsuarios();
                psUsuarios.setNombres(argsUsuario[0]);
                psUsuarios.setApellidos(argsUsuario[1]);
                psUsuarios.setUsuario(argsUsuario[2]);
                psUsuarios.setPassword(DigestUtils.sha1Hex(argsUsuario[3]));
                psUsuarios.setEmpresa(argsUsuario[4]);
                psUsuarios.setRol(argsUsuario[5]);
                psUsuarios.setFechaCreacion(controllerFecha.getFecha());
                psUsuarios.setFechaModificacion(controllerFecha.getFecha());

                return psUsuarios;
            }
            JOptionPane.showMessageDialog(null, "La contraseña debe tener como minimo 5 caracteres");

        } else {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            return null;
        }
        return null;
    }

    public PsUsuarios camposUsuarios(String[] argsLogin) {
        PsUsuarios psUsuariosLogin;
        // ctrlUsuariosJpaController = new PsUsuariosJpaController();
        if (!argsLogin[0].equals("") && !argsLogin[1].equals("") && !argsLogin[2].equals("")) {

            psUsuariosLogin = new PsUsuarios();
            psUsuariosLogin.setUsuario(argsLogin[0]);
            psUsuariosLogin.setPassword(argsLogin[1]);
            psUsuariosLogin.setEmpresa(argsLogin[2]);

            //System.out.println(""+psUsuariosLogin.getUsuario());
            //ctrlUsuariosJpaController.getUsuario(psUsuariosLogin);
            return psUsuariosLogin;
        }
        JOptionPane.showMessageDialog(null, "Por favor completar todos los campos");
        return null;
    }

    public Boolean validarUsuario(String[] login) {
        return login[0] != null;
    }

    public Boolean acceso(PsUsuarios usuario) {
        ctrlUsuariosJpaController = new PsUsuariosJpaController();
        Boolean acceso;

        if (usuario != null) {
            sessionUsuario = ctrlUsuariosJpaController.getUsuario(usuario);
            acceso = validarUsuario(sessionUsuario);
            if (acceso == true) {

                JOptionPane.showMessageDialog(null, "Bienvenido Sr(a). " + sessionUsuario[4] + " " + sessionUsuario[5]);

            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
                return false;
            }

        } else {

            return false;
        }

        return true;
    }

    public PsUsuarios session() {
        PsUsuarios psUsuariosSession = new PsUsuarios();
        psUsuariosSession.setNumero(Integer.parseInt(sessionUsuario[6]));
        psUsuariosSession.setUsuario(sessionUsuario[0]);
        psUsuariosSession.setEmpresa(sessionUsuario[2]);
        psUsuariosSession.setRol(sessionUsuario[3]);
        psUsuariosSession.setNombres(sessionUsuario[4]);
        psUsuariosSession.setApellidos(sessionUsuario[5]);

        return psUsuariosSession;
    }

    public Boolean rol(PsUsuarios psUsuariosSession) {
        return !psUsuariosSession.getRol().equals("Desarrollador");
    }

}
