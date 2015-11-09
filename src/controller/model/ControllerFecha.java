/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Juan Camilo Giron
 *
 */
public class ControllerFecha {

    private Date fecha;

    public Date getFecha() {
        Date date=new Date();  
       fecha=date;
       
       return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

  

   

 
}
