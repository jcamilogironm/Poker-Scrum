/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import model.PsUsuarios;

/**
 *
 * @author Juan Camilo Giron
 */
public class ControllerPsUsuarios {
    
    public PsUsuarios crearUsuarios(String[] argsUsuario){
    
    PsUsuarios psUsuarios;
    
    psUsuarios=new PsUsuarios();
    psUsuarios.setNombres(argsUsuario[0]);
    psUsuarios.setApellidos(argsUsuario[1]);
    psUsuarios.setUsuario(argsUsuario[2]);
    psUsuarios.setPassword(argsUsuario[3]);
    psUsuarios.setEmpresa(argsUsuario[4]);
    psUsuarios.setRol(argsUsuario[5]);
    
    return psUsuarios;
    
    }
    
    
}
