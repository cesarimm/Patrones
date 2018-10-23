/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaimagenes;

import clasificadoresNoSupervisados.CMeans;
import clasificadoresNoSupervisados.MinMax;
import herramientas.HerramientasClasificadores;
import herramientas.IOImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import objetos.Patron;

/**
 *
 * @author CESAR IVAN MTZ
 */
public class ClusterMinMax {
    
    private Image imagenOriginal;
    private MinMax clasificador;
    
       public Image calcularClusters(Image imagen, double alpha){
        this.imagenOriginal =imagen;
        // extraer de la imagen la info para generar las instancias
        ArrayList<Patron> instancias = generarInstancias();
        ArrayList<Patron> instancias2 = (ArrayList<Patron>) instancias.clone();
        
        
        ArrayList<Patron> centroidesIniciales = calculaPixelesCentroidesIniciales(instancias2, alpha);
        
//        for(int c=0;c<centroidesIniciales.size();c++){
//           System.out.println(centroidesIniciales.get(c).getCaracteristicas()[0]+" "
//                           +centroidesIniciales.get(c).getCaracteristicas()[1]+" "
//                            +centroidesIniciales.get(c).getCaracteristicas()[2]); 
//            
//        }
            
        //Patron[] centroidesIniciales = calculaPixelesCentroidesIniciales(instancias);
        this.clasificador = new MinMax(instancias, alpha);
        this.clasificador.clasificaImagenes(centroidesIniciales, instancias2);
        
         //modificamos los colores en base a la clasificacion 
        for(Patron patron: instancias){
        PatronPixel aux = (PatronPixel)patron;
        aux.modificarCaracteristicas();
        }
        return generarImagen(instancias);
    }
        
   

    private ArrayList<Patron> generarInstancias() {
       ArrayList<Patron> instancias = new ArrayList<>();
        // necesitamos leer los pixeles 
        BufferedImage bi = IOImage.toBufferedImage(imagenOriginal);
        // recorrer la imagen por pixel 
        for(int x=0;x<bi.getWidth();x++)
            for(int y=0;y<bi.getHeight();y++){
            Color color = new Color(bi.getRGB(x, y));
            double r = color.getRed();
            double g = color.getGreen();
            double b = color.getBlue();
            double[] c = new double[]{r,g,b};
            PatronPixel px = new PatronPixel(c,"Desconocida",x,y);
            instancias.add(px);
            }
       return instancias;
    }

    private Image generarImagen(ArrayList<Patron> instancias) {
       BufferedImage imagenNueva = new BufferedImage(this.imagenOriginal.getWidth(null),
                                                     this.imagenOriginal.getHeight(null),BufferedImage.TYPE_INT_RGB);
       for(Patron patron: instancias){
       PatronPixel aux = (PatronPixel)patron;
       int rgb = Integer.parseInt(aux.getClaseOriginal());
       imagenNueva.setRGB(aux.getX(),aux.getY(), rgb);
       
       }
       return IOImage.toImage(imagenNueva);
    }

    
    private ArrayList<Patron> calculaPixelesCentroidesIniciales(ArrayList<Patron> instancias, double alpha) {
       // generar los dos  centroidesIniciales iniciales 
    
     //Patron[] centroidesIniciales = new Patron[numClusters];
     ArrayList<Patron> centroidesIniciales = new ArrayList<>();
     
     double[] colorAux = new double[3];
     
     for (int x=0;x<instancias.size();x++){
       //int pos = ran.nextInt(instancias.size());
//        Color color = new Color(
//                      (int)instancias.get(pos).getCaracteristicas()[0],
//                      (int)instancias.get(pos).getCaracteristicas()[1], 
//                      (int)instancias.get(pos).getCaracteristicas()[2]);

     colorAux[0] += (int)instancias.get(x).getCaracteristicas()[0];
     colorAux[1] += (int)instancias.get(x).getCaracteristicas()[1]; 
     colorAux[2] += (int)instancias.get(x).getCaracteristicas()[2];
      // String nombre = color.getRGB()+"";
      // centroidesIniciales[x] = new Patron(instancias.get(pos).getCaracteristicas().clone(),nombre);
     }
     
     for(int i=0;i<3;i++)  colorAux[i]=colorAux[i]/instancias.size();  
     
     ///Patron promedio     
     Patron patronProm = new Patron(3);
     patronProm.setCaracteristicas(colorAux);
     
     //centroidesIniciales.add(patronProm);
     double distancia, disAux;
     int pos=0;
     
     distancia = HerramientasClasificadores.calcularDistEucli(patronProm, instancias.get(0));
     
     for(int i=1;i<instancias.size();i++){
         disAux =  HerramientasClasificadores.calcularDistEucli(patronProm, instancias.get(i));   
         if(distancia<disAux){
             distancia = disAux;
            //  System.out.println("Dis1: "+distancia);
             pos = i;
         }
     }
     
     centroidesIniciales.add((Patron) instancias.get(pos));
     instancias.remove(pos);
     
     //Segundo patron
     pos=0;
     distancia = HerramientasClasificadores.calcularDistEucli(centroidesIniciales.get(0), instancias.get(0));
     
     for(int i=1;i<instancias.size();i++){
         disAux =  HerramientasClasificadores.calcularDistEucli(centroidesIniciales.get(0), instancias.get(i));   
         if(distancia<disAux){
             distancia = disAux;
            // System.out.println("Dis2: "+distancia);
             pos = i;
         }
     }
     
     centroidesIniciales.add((Patron) instancias.get(pos));
     instancias.remove(pos);
     ///
        
     //Generar los demas patrones
    
     
       return centroidesIniciales;
    }
    
    public static void main(String args[]){
        Image imagenOriginal = IOImage.abrirImagen();
        JFrameImagen fo = new JFrameImagen(imagenOriginal);
        fo.setVisible(true);
        ClusterMinMax ci = new ClusterMinMax();
        
        Image imagenRes = ci.calcularClusters(imagenOriginal, .001);
        
        System.out.println("");
        JFrameImagen fr = new JFrameImagen(imagenRes);
        fr.setVisible(true);
        System.out.println();
        
    }
}
