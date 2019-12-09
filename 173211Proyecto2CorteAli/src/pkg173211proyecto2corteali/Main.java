/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg173211proyecto2corteali;

/**
 *
 * @author Jorge Julian Sanchez
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
     Estacionamiento miEstacionamiento = new Estacionamiento(20); //Numero de cajones
     for (int i=0; i<1000; i++){
         Automovil miAuto = new Automovil(i,miEstacionamiento);
         Thread miHiloAuto = new Thread(miAuto,"Auto : " + (i+1));
         miHiloAuto.start();
         Thread.sleep(2000);
     }  
  }  
}
