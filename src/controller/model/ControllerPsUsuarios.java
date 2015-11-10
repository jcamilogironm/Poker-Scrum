/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import controller.PsUsuariosJpaController;
import java.util.Date;
import javax.swing.JOptionPane;
import model.PsUsuarios;

/**
 *
 * @author Juan Camilo Giron
 */
public class ControllerPsUsuarios {

    ControllerFecha controllerFecha;
    PsUsuariosJpaController ctrlUsuariosJpaController;

    public PsUsuarios crearUsuarios(String[] argsUsuario) {
        controllerFecha = new ControllerFecha();
        PsUsuarios psUsuarios;

        if (!argsUsuario[0].equals("") && !argsUsuario[1].equals("") && !argsUsuario[2].equals("") && !argsUsuario[3].equals("") && !argsUsuario[4].equals("") && !argsUsuario[5].equals("")) {
            if (argsUsuario[3].length() >= 5) {

                psUsuarios = new PsUsuarios();
                psUsuarios.setNombres(argsUsuario[0]);
                psUsuarios.setApellidos(argsUsuario[1]);
                psUsuarios.setUsuario(argsUsuario[2]);
                psUsuarios.setPassword(argsUsuario[3]);
                psUsuarios.setEmpresa(argsUsuario[4]);
                psUsuarios.setRol(argsUsuario[5]);
                psUsuarios.setFechaCreacion(controllerFecha.getFecha());
                psUsuarios.setFechaModificacion(controllerFecha.getFecha());

                return psUsuarios;
            }
            JOptionPane.showMessageDialog(null, "La contraseña debe tener como minimo 5 caracteres");

        }
        JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
        return null;
    }

    public void loginUsuarios(String[] argsLogin) {

        PsUsuarios psUsuariosLogin;
        ctrlUsuariosJpaController=new PsUsuariosJpaController();
        if (!argsLogin[0].equals("") && !argsLogin[1].equals("") && !argsLogin[2].equals("")) {

            psUsuariosLogin = new PsUsuarios();
            psUsuariosLogin.setUsuario(argsLogin[0]);
            psUsuariosLogin.setPassword(argsLogin[1]);
            psUsuariosLogin.setEmpresa(argsLogin[2]);
            //System.out.println(""+psUsuariosLogin.getUsuario());
           
            ctrlUsuariosJpaController.getUsuario(psUsuariosLogin);
        }
        JOptionPane.showMessageDialog(null, "Por favor completar todos los campos");
       
    }

}
