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
public class PatronDistancia {
    
   public Patron patron;
   public double distancia;
   
    public PatronDistancia(Patron p1, double Distancia){ 
       this.patron = p1;
       this.distancia=Distancia;  
   }
   
   public PatronDistancia(Patron p1){ 
       this.patron = p1;
       this.distancia=0;  
   }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
   
    
}
