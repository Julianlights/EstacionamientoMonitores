/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg173211proyecto2corteali;

import java.util.concurrent.ThreadLocalRandom;

public class Estacionamiento {
    int espacioslibres;
    int esperandoAutomovil;
    int esperandoAutomovil_parasalir;
    int lugaresMaximos;
    public Estacionamiento (int cajoneslibres){
        this.espacioslibres = cajoneslibres;
        this.esperandoAutomovil = 0;
        this.esperandoAutomovil_parasalir = 0;
        this.lugaresMaximos = cajoneslibres;
         //
         
        System.out.println ("Cajones disponibles  ====> : " + espacioslibres + " <==== ");
        
                              
    }
    
    public synchronized void acceder (int identificadorxd,Automovil auto) throws InterruptedException{
        System.out.println("El : " + Thread.currentThread().getName() + " esta en la entrada y quiere entrar\n ");
        //if (esperandoAutomovil_parasalir < 0 ){
            //System.out.println("====> hay : "+ esperandoAutomovil_parasalir + "autos esperando a salir ");
          //  this.wait();
        //}
        while (esperandoAutomovil_parasalir < 0){
            System.out.println("====>  Hay : "+ esperandoAutomovil_parasalir + "autos esperando a salir \n");
            this.wait();
        }
        if (espacioslibres > 0){
            auto.Verificaestacionado = true; 
            estacionar(identificadorxd);
            
            while (auto.Verificaestacionado){
                if (tiempo_aleatorio_para_salir(1,10) < 5){
                    auto.Verificaestacionado = false;
                    dejarEspacio(identificadorxd, auto);
                    
                }else{
                    if (espacioslibres == 0)
                        notify();
                    else
                            this.wait();
                    
                }
            }
        }
        else 
        {
            System.out.println("El : "+ Thread.currentThread().getName() + " no puede entrar porque no hay lugar \n");
            esperandoAutomovil++;
            System.out.println("Hay : "+ esperandoAutomovil + " carros esperando a entrar \n");
            this.wait();
        }
        if ( espacioslibres ==0){
            this.wait();
        }
        
    }    
    public void estacionar (int identificadorxd) throws InterruptedException {
      
       System.out.print("El : "+ Thread.currentThread().getName() + " se estaciono en el cajon  " + espacioslibres + "\n");
       espacioslibres--;
    }
    private void dejarEspacio(int identificadorxd, Automovil auto) throws InterruptedException {
       while (esperandoAutomovil > 0){
           System.out.println("===> Hay:  " +  esperandoAutomovil + " carros esperando a entrar  \n" );
           this.wait();
       }
       if (espacioslibres < lugaresMaximos ){
           Thread.currentThread().sleep(tiempo_aleatorio_para_salir(1000, 2000));
           espacioslibres++;
           System.out.println("El : "+ Thread.currentThread().getName() + "sale y hay : " + espacioslibres + "espacios disponibles \n");
       }
       if ( esperandoAutomovil == 0){
           this.notify();
       } else 
           esperandoAutomovil_parasalir++;
           
    }
    private int tiempo_aleatorio_para_salir(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


}
