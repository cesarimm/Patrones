/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadoresNoSupervisados;

import herramientas.Grafica;
import herramientas.HerramientasClasificadores;
import herramientas.Punto;
import herramientas.Tokenizador;
import java.util.ArrayList;
import objetos.Patron;

/**
 *
 * @author CESAR IVAN MTZ
 */
public class MinMax {
    
    //d<T*alpha
    
    // conjunto de instancias
    private ArrayList<Patron> instancias;
    // numero de clusters
    private int c;
    // centroidesIniciales 
    private ArrayList<Patron> centroides;
    //alpha a
    private double alpha, delta;
    ///
    private ArrayList<Patron> instanciasAux;
    
   public MinMax(ArrayList<Patron> instancias, double alpha){
       
       this.instancias = instancias;
       this.instanciasAux = (ArrayList<Patron>) instancias.clone();
       this.alpha = alpha;
       this.centroides = new ArrayList<>();  
       this.delta=0;
    }
   
   public void clasifica(){
       this.obtenerRepresentativos();
      
         for(int i=0;i<this.centroides.size();i++){
          centroides.get(i).setClaseOriginal("Clase"+i);   
          }
         
          this.etiquetar();
   }
   
   private void obtenerRepresentativos(){
      
     //Obtiene el patron promedio    
     Patron p1 = new Patron(this.instancias.get(0).getCaracteristicas().length);
     double[] auxiliar = new double[this.instancias.get(0).getCaracteristicas().length];
     
     for(int i=0;i<this.instancias.size();i++){ 
         for(int j=0;j<p1.getCaracteristicas().length;j++){
             auxiliar[j]+=instancias.get(i).getCaracteristicas()[j];
         }    
     }
     
     for(int i=0;i<auxiliar.length;i++)
         auxiliar[i]=auxiliar[i]/this.instancias.size();
     
       p1.setCaracteristicas(auxiliar);
       // System.out.println(p1.getCaracteristicas()[0]+" "+p1.getCaracteristicas()[1]);
       this.calcularCentroidesIniciales(0, p1);
       this.calcularCentroidesIniciales(1, centroides.get(0));
       
         this.delta = alpha*(HerramientasClasificadores.calcularDistEucli(this.centroides.get(0), this.centroides.get(1))/2);
       this.CalcularOtrosCentroides();
         
    }
   
   
   
  private void calcularCentroidesIniciales(int j, Patron p1){
       double distancia, disAux;
       int y = 0;
       disAux = HerramientasClasificadores.calcularDistEucli(p1, 
               instanciasAux.get(0));
        
      centroides.add((Patron)instancias.get(j));
       
       for(int i=1;i<instanciasAux.size();i++){
           distancia = HerramientasClasificadores.calcularDistEucli(p1, 
                instanciasAux.get(i));
           
           if(distancia>disAux){
               disAux = distancia;
               centroides.set(j, (Patron)instanciasAux.get(i));
               y=i;
              // centroides.get(j).setClaseOriginal(Integer.toString(j));
           }
       }
       //Eliminando el que ya se agrego a los centroides
       instanciasAux.remove(y);   
   }
   
  private void CalcularOtrosCentroides(){
      
      double disAux, distancia, distanciaMayor=0;
      int pos=0;       
      //distancia = HerramientasClasificadores.calcularDistEucli(this.centroides.get(0), this.instanciasAux.get(0));
      
      for(int i=0;i<this.instanciasAux.size();i++){
          
           distancia = HerramientasClasificadores.calcularDistEucli(this.centroides.get(0), this.instanciasAux.get(i));
           
         // System.out.println(distancia);
           
          for(int j=1;j<this.centroides.size();j++){
          
          disAux = HerramientasClasificadores.calcularDistEucli(this.centroides.get(j), this.instanciasAux.get(i));
    
              //System.out.println(disAux);
              
         if(disAux < distancia){
             
             distancia = disAux;
                     }  
        }
         // System.out.println("Distancia Menor"+ distancia+"\n\n"); 
          if(distancia>distanciaMayor){
              distanciaMayor = distancia;
              pos = i;
          }
          
      }   
      
       // System.out.println("La distancia mayor "+distanciaMayor+" "+delta);
     
       if(this.instanciasAux.size()>0){
                if(distanciaMayor>this.delta){
             //  System.out.println("Nuevo Centroide");
               //this.instanciasAux.get(pos).setClaseOriginal(Integer.toString(this.centroides.size()));
               this.centroides.add(this.instanciasAux.get(pos));
               this.instanciasAux.remove(pos);
               this.c = centroides.size();
               this.CalcularOtrosCentroides();

            } 
        } 
   }
   

      
   
  
   private void etiquetar (){
    // recorrer las instancias y etiquetar 
    // cada una de ellas en base a distancias
     for(int i=0;i<this.instancias.size();i++){
         int pos = 0;
         double menorAux, menor=herramientas.HerramientasClasificadores.calcularDistEucli(this.instancias.get(i), this.centroides.get(0));
         
         for(int j=1;j<this.centroides.size();j++){
            
             menorAux=herramientas.HerramientasClasificadores.calcularDistEucli(this.instancias.get(i), this.centroides.get(j));
             
             if(menorAux<menor){
                 menor=menorAux;
                 pos=j;
             }
         }
         
         this.instancias.get(i).setClaseOriginal(this.centroides.get(pos).getClaseOriginal());
       //  System.out.println("Clase: "+this.instancias.get(i).getClaseOriginal());
     }
 }
  
   
   
   public static void main(String args[]){
       
       Tokenizador.leerDatos();
       MinMax mmx = new MinMax(Tokenizador.instancias, .3);
       mmx.clasifica();
       
      
   //    System.out.println("");
        Grafica grafica = new Grafica("clasificacion","x1","x2");
        
        for(Patron patron: mmx.centroides){
             grafica.agregarSerie(patron.getClaseOriginal());
        }
        
//        grafica.agregarSerie("Clase0");
//        grafica.agregarSerie("Clase1");
//        grafica.agregarSerie("Clase2");
//        grafica.agregarSerie("Clase3");
//        grafica.agregarSerie("Clase4");
//        grafica.agregarSerie("Clase5");
        
        for(Patron patron: Tokenizador.instancias){
          Punto p = new Punto(patron.getCaracteristicas()[0],
                     patron.getCaracteristicas()[1]);
          grafica.agregarPunto(patron.getClaseOriginal(), p);
            System.out.println(patron.getCaracteristicas()[0] + " " +
                    patron.getCaracteristicas()[1]+ " "+patron.getClaseOriginal());
            
        }
        
        grafica.crearGraficaPuntos();
   }
   
   
    
}
