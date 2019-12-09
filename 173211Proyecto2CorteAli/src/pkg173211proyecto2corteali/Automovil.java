package pkg173211proyecto2corteali;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge Julian Sanchez
 */
public class Automovil implements Runnable{
    long identificadorxd;
    Estacionamiento estacionamiento;
    boolean Verificaestacionado;
    public Automovil(int id,Estacionamiento estacionar){
        this.estacionamiento = estacionar;
        this.identificadorxd = id;
        this.Verificaestacionado = false;
    }
    @Override
    public void run() {
        try {
            this.estacionamiento.acceder((int)identificadorxd, this);
        } catch (InterruptedException ex) {
            Logger.getLogger(Automovil.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}