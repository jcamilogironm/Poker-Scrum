/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import controller.PsUsuariosJpaController;
import javax.swing.JOptionPane;
import model.PsTareas;
import model.PsUsuarios;
import view.TecladoException;

/**
 *
 * @author Juan Camilo Giron
 */
public class ControllerPsTareas {

    ControllerFecha controllerFecha;

    public PsTareas crearTarea(String[] argsTareas, PsUsuarios psUsuSesion) {
        controllerFecha = new ControllerFecha();
        PsUsuariosJpaController ctrlControllerPsUsuarios = new PsUsuariosJpaController();
        PsTareas psTareas;
        int minutosC, segundosC, minutosD, segundosD, numero;
        System.out.println(numero = psUsuSesion.getNumero());

        minutosC = TecladoException.getEntero(argsTareas[2], "minutos de calificacion");
        segundosC = TecladoException.getEntero(argsTareas[3], "segundos de claificacion");
        minutosD = TecladoException.getEntero(argsTareas[4], "minutos para el debate");
        segundosD = TecladoException.getEntero(argsTareas[5], "segundos para el debate");

        if (!argsTareas[0].equals("") && !argsTareas[1].equals("") && !argsTareas[2].equals("") && !argsTareas[3].equals("") && !argsTareas[4].equals("") && !argsTareas[5].equals("")) {
            if (minutosC <= 60 && segundosC <= 59) {
                if (minutosD <= 30 && segundosD <= 59) {
                    PsUsuarios psUsuariosfind = ctrlControllerPsUsuarios.findPsUsuarios(psUsuSesion.getNumero());
                   

                        psTareas = new PsTareas();
                        psTareas.setDescripcion(argsTareas[1]);
                        psTareas.setTempPuntuacion(argsTareas[2]+""+ argsTareas[3]);
                        psTareas.setTempDebate(argsTareas[4] + "" + argsTareas[5]);
                       // psTareas.setTbUsuariosCodigo(psUsuariosfind);
                        psTareas.setFechaApertura(controllerFecha.getFecha());
                        psTareas.setFechaModificacion(controllerFecha.getFecha());
                       // psTareas.setCreador(psUsuariosfind.getNombres() + " " + psUsuariosfind.getApellidos());
                        psTareas.setEstado(1);
                        psTareas.setPsTareasCalificadasList(null);

                        return psTareas;
                   
                }
                JOptionPane.showMessageDialog(null, "El tiempo para el debate no puede superar los 30:00 minutos");

            }
            JOptionPane.showMessageDialog(null, "El tiempo para la calificacion de la tarea no pueder superar los 60 minutos");

        }
        JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
        return null;
    }

}
