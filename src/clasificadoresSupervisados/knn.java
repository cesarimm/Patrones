/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//9310095

package clasificadoresSupervisados;

import herramientas.HerramientasClasificadores;
import java.util.ArrayList;
import objetos.Clases;
import objetos.Patron;
import objetos.PatronDistancia;
import objetos.PatronRepresentativo;

/**
 *
 * @author CESAR IVAN MTZ
 */
public class knn implements ClasificadorSupervisado{
    
    
   public int k;
   public ArrayList<Clases> representativos; 
   private PatronDistancia[] datos;
   double eficacia;
    
    public knn(int k){
        
        this.k=k;
        this.representativos = new ArrayList<>();
        this.eficacia=0;
       
    }

    @Override
    public void entrena(ArrayList<Patron> instancias) {
         // agregamos el primer representativo      
         //this.caracteristicas = new double[dim]
         //System.out.println("Intancias "+instancias.size());
         
         this.datos = new PatronDistancia[instancias.size()];
         
         for(int j=0;j<instancias.size();j++){  
             //Patron p1 = instancias.get(j);
            datos[j] = new PatronDistancia(instancias.get(j));   
        }
         
         this.representativos.add(new Clases(instancias.get(0).getClaseOriginal()));
        // recorrer la coleccion de patrones 
    for (int i=1; i < instancias.size();i=i+1){
        
        String nameClass = instancias.get(i).getClaseOriginal();
        // Patron patron = instancias.get(i);
        // buscar en los presentativos
        buscayAcumula(nameClass);
    }          
    }
    
       
   private void buscayAcumula(String patron) {
        int m = -1;
        // buscar en la coleccion de represantes
        for (int i=0; i < this.representativos.size();i++){
            //verificamos que exista 
            if (patron.equals(
            this.representativos.get(i).getNombreClase())){
            m = i;
            break;
            }
            }
        
         if (m==-1){
         // agrega 
            this.representativos.add(new Clases(patron));  
         }
    }
    

   
   
    @Override
    public void clasifica(Patron patron) {
        
            for(int i=0;i<this.datos.length;i++){

                datos[i].distancia = HerramientasClasificadores.calcularDistEucli(patron, 
                            datos[i].getPatron());  
                
               // System.out.println("");
            }
            
            
//                   for(int a=1;a<datos.length;a++){
//    
//                    System.out.println("DATOS: "+a+" "+datos[a].patron.getClaseOriginal()+" "+datos[a].getDistancia());
//    
//                     }
                   
                   this.ordenar();
          
//                        for(int a=1;a<datos.length;a++){
//    
//                          System.out.println("DATOS: "+a+" "+datos[a].patron.getClaseOriginal()+" "+datos[a].getDistancia());
//    
//                          }
                        
//           for(int i=0;i<representativos.size();i++){
//               
//               
//               System.out.println(representativos.get(i).getNombreClase()+" "+datos[1].patron.getClaseOriginal());
//               
//               if(representativos.get(i).nombreClase.equals(datos[1].patron.getClaseOriginal())){
// 
//                   System.out.println("Hola");
//                   
//               }
////               if(representativos.get(i).getNombreClase()==datos[1].patron.getClaseOriginal()){
////                   
////                   System.out.println("Hola");
////                   representativos.get(i).contador=8;
////                   
////               }
//                 
//           }             
  
         
         for(int k=1;k<datos.length;k++){
             
             int m=-1;
             
             for(int l=0;l<this.representativos.size();l++){
                 
                 if(representativos.get(l).contador==this.k){ 
                     
                    patron.setClaseResultante(representativos.get(l).getNombreClase());
                     m=0;
                 }       
                 
                 if(m==0){
                     l=representativos.size();
                 }
             }
             
             if(m==-1){
                 
                    for(int t=0;t<this.representativos.size();t++){

                     if(representativos.get(t).getNombreClase().equals(datos[k].patron.getClaseOriginal())){
                         
                            representativos.get(t).contador++;
                     }     
                   }
                    
             }else{
                 
                 k=datos.length;
                 
             }
            
            
         }
         
         RegresarCuenta();
         

//

//        System.out.println("La nueva clase es: ");
//for(int a=1;a<datos.length;a++){
//    
//    System.out.println("DATOS: "+datos[a].patron.getClaseOriginal());
//}
         
//         System.out.println("Patron resultante: "+patron.getClaseResultante());
            
    }
    
    
    public void RegresarCuenta(){
        
         for(int p=0;p<representativos.size();p++){
             
             representativos.get(p).setContador(0);
             
         }
         
    }
    
     public void clasificaConjunto(ArrayList<Patron> instancias){
    // recorremos la coleccion a clasificar
        int total = instancias.size();
        // contador de clasificacion correctas
        int aux = 0;
        for (Patron patron: instancias){
            clasifica(patron);
            if(patron.getClaseResultante().equals(patron.getClaseOriginal()))aux++;
        }
       this.eficacia = aux*100/total;
         System.out.println("KNN Eficacia: "+this.eficacia);
    }
   
    private void ordenar(){
        
        //Aqui hay error
        
       PatronDistancia aux;
        
         for(int i=1;i<datos.length;i++){
            
            
            int j=i-1;
            
            aux= new PatronDistancia(datos[i].getPatron(), datos[i].getDistancia());
                  
            while(j > -1 && datos[j].getDistancia()>aux.getDistancia()){
                
                
                datos[j+1].patron= datos[j].patron;
                datos[j+1].distancia = datos[j].distancia;
               // datos[j+1]=datos[j];
                
                j--;
            }
            
            datos[j+1].distancia=aux.distancia; 
            datos[j+1].patron=aux.patron;
        
    }
         
//         for(int i=0;i<datos.length;i++){
//             
//             System.out.println("Distancia: "+i+" "+datos[i].getDistancia());
//             
//         }
}

    public int getK() {
        return k;
    }
    

    public double getEficacia() {
        return eficacia;
    }
    

    public void setK(int k) {
        this.k = k;
    }
    
}
