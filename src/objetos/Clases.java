/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author CESAR IVAN MTZ
 */
public class Clases {
    
    
   public int contador;
   public String nombreClase;
    
    
    
    public Clases(){
        
      this.contador=0;
      this.nombreClase="";
   }
    
    public Clases(String a){
        
        this.nombreClase=a;
        this.contador=0;
    }
    

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }
    
   
    
}
