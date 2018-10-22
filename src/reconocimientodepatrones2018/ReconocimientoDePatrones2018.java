/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///Desarrollado Por mí!!!!

package reconocimientodepatrones2018;

import clasificadoresNoSupervisados.CMeans;
import clasificadoresSupervisados.MinimaDistancia;
import clasificadoresSupervisados.knn;
import herramientas.GeneradorInstancias;
import herramientas.Grafica;
import herramientas.HerramientasClasificadores;
import herramientas.Punto;
import herramientas.Tokenizador;
import java.util.ArrayList;
import javax.swing.text.html.MinimalHTMLWriter;
import objetos.Patron;
///Este lo programe io!!!!!

/**
 *
 * @author Roberto Cruz Leija
 */

import clasificadoresNoSupervisados.CMeans;
import clasificadoresSupervisados.knn;
import herramientas.GeneradorInstancias;
import herramientas.Grafica;
import herramientas.IOImage;
import herramientas.Punto;
import herramientas.Tokenizador;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import objetos.Patron;
import practicaimagenes.ClusterImagenes;
import practicaimagenes.JFrameImagen;
import practicaimagenes.PatronPixel;

/**
 *
 * @author Roberto Cruz Leija
 */
public class ReconocimientoDePatrones2018 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Image imagenOriginal = IOImage.abrirImagen();
        JFrameImagen fo = new JFrameImagen(imagenOriginal);
        fo.setVisible(true);
        ClusterImagenes ci = new ClusterImagenes();
        Image imagenRes = ci.calcularClusters(imagenOriginal, 100);
        JFrameImagen fr = new JFrameImagen(imagenRes);
        fr.setVisible(true);
        System.out.println();


//        Tokenizador.leerDatos();
  // CMeans cmeans = new CMeans(Tokenizador.instancias, 3);
//        cmeans.clasifica();
//        Grafica grafica = new Grafica("clasificacion","x1","x2");
//        grafica.agregarSerie("Centroide0");
//        grafica.agregarSerie("Centroide1");
//        grafica.agregarSerie("Centroide2");
//        ///grafica.agregarSerie("Centroide3");
//        
//        for(Patron patron: Tokenizador.instancias){
//          Punto p = new Punto(patron.getCaracteristicas()[0],
//                     patron.getCaracteristicas()[1]);
//          grafica.agregarPunto(patron.getClaseOriginal(), p);
//        }
//        
//        grafica.crearGraficaPuntos();
        
        
   }
    
}

//public class ReconocimientoDePatrones2018 {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        Tokenizador.leerDatos();
//        CMeans cmeans = new CMeans(Tokenizador.instancias, 3);
//        cmeans.clasifica();
//        Grafica grafica = new Grafica("clasificacion","x1","x2");
//        grafica.agregarSerie("Centroide0");
//        grafica.agregarSerie("Centroide1");
//        grafica.agregarSerie("Centroide2");
//        
//        for(Patron patron: Tokenizador.instancias){
//          Punto p = new Punto(patron.getCaracteristicas()[0],
//                     patron.getCaracteristicas()[1]);
//          grafica.agregarPunto(patron.getClaseOriginal(), p);
//        }
//        
//        grafica.crearGraficaPuntos();
//        
//        
//     //  Tokenizador.leerDatos();
//
////     //  ArrayList<Patron> aux = GeneradorInstancias.generarInstancias(new byte[]{0,0,1,1});
////     // knn kn = new knn(2);
////     
////    // kn.entrena(Tokenizador.instancias);
////   //   kn.clasifica(Tokenizador.instancias.get(0));
////  //  kn.clasificaConjunto(Tokenizador.instancias);
////    
////        ArrayList<Patron> aux = GeneradorInstancias.generarInstancias(new byte[]{1,1,0,1,0,0});
////        
//////       MinimaDistancia md = new MinimaDistancia();
//////       md.entrena(Tokenizador.instancias);
//////       md.clasificaConjunto(Tokenizador.instancias);
////
//////     MinimaDistancia md = new MinimaDistancia();
//////     md.entrena((ArrayList<Patron>) aux.clone());
//////     md.clasificaConjunto((ArrayList<Patron>) aux.clone());
////
//////for(int i=0;i<aux.size();i++){
//////   aux.get(i).setClaseResultante(""); 
//////}
//////
//////
////  knn kn = new knn(3);
////  kn.entrena(aux);
////  kn.clasificaConjunto(aux);
////    
////System.out.println("");
//
//    }
//    
//}
