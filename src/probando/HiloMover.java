/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probando;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author Jonas
 */
public class HiloMover extends Thread{
    Transform3D movimiento = new Transform3D();
    TransformGroup figura = new TransformGroup();
     Clase3d objet = new Clase3d();
     int opc=0;
    HiloMover(Transform3D movimiento, TransformGroup figura, int opc )
    {
     this.figura=figura;        //ponemos todos los objetos que necesitamos para realizar los movimientos
     this.movimiento=movimiento;
     this.opc=opc;
    }
    
   public void run()
    {
      switch(opc)  //se hace un switch para escoger que boton se usara 
      {
          case 1:
              while(true) //ciclo infinito mientras presionen el boton del hilo
              {
               objet.boton2(movimiento,figura); //se pide movimiento y figura para con un objeto nuevo realizar los movimientos en pantalla
               dormir();
              }
          case 2:
              while(true)
              {
               objet.boton(movimiento,figura);
               dormir();
              }
          case 3:
              while(true)
              {
               objet.boton3(movimiento,figura);
               dormir();
              }
          case 4:
              while(true)
              {
               objet.botonCam1(movimiento,figura);
               dormir();
              }
          case 5:
              while(true)
              {
               objet.botonCam2(movimiento,figura);
               dormir();
              }
          case 6:
              while(true)
              {
               objet.botonCam3(movimiento,figura);
               dormir();
              }   
          case 7:
              while(true)
              {
               objet.botonCam4(movimiento,figura);
               dormir();
              }   
        }
      
    
    }
    
    void dormir()// dormir se pone aparte para no ocupar tanto codigo
    {
     try 
     {
      Thread.sleep(100);
     }
     catch (InterruptedException ex) 
     {
      
     }
    }
    
    
    
}
