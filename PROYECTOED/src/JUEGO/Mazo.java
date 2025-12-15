package JUEGO;

import java.util.ArrayList;
import java.util.Collections;
//clase utilitaria collections que tiene funciones para operar listas 
import java.util.LinkedList;
import java.util.Stack;

public class Mazo {

    //cambio a cola
    private ColaConLista<Carta> mazoPrincipal;
    private Pila<Carta> centro;

    public Mazo() {
        mazoPrincipal = new ColaConLista<>();
        centro = new Pila<>(108);
        generarMazoCompleto();
        barajar();
    }

    //BARAJA DEL JUEGO ORIGINAL DEL UNO CON TODAS LAS CARTAS
    private void generarMazoCompleto() {

        String[] colores = {"ROJO", "VERDE", "AZUL", "AMARILLO"};

        for (String color : colores) {

            //LLENADO DEL MAZO PRINCIPAL
            
            for (int i = 0; i <= 9; i++) {
                //PARA LOS CEROS 0
                mazoPrincipal.enqueue(new Carta(color, "NUMERO", i)); //CARTA TIPO NÚMERO
                
                if (i != 0) {
                    //PARA LAS CARTAS 1-9 SIN CERO
                    mazoPrincipal.enqueue(new Carta(color, "NUMERO", i));
                }
            }
            //(10*4)+(9*4)= 76

            //=> 72 + 4 = 76 cartas totales
            
            //CARTAS ESPECIALES FALTANTES
            mazoPrincipal.enqueue(new Carta(color, "BLOQUEO", -1)); //CARTA TIPO BLOQUEO
            mazoPrincipal.enqueue(new Carta(color, "BLOQUEO", -1));
            //=> 4 * 2 = 8
            mazoPrincipal.enqueue(new Carta(color, "REVERSA", -1)); //CARTA TIPO REVERSA
            mazoPrincipal.enqueue(new Carta(color, "REVERSA", -1));
            //=> 4 * 2 = 8
            mazoPrincipal.enqueue(new Carta(color, "MAS2", -1)); //CARTA TIPO +2
            mazoPrincipal.enqueue(new Carta(color, "MAS2", -1));
            //=> 4 * 2 = 8
        } // 24 + 76 = 100

        //LLENADO CARTAS ESPECIALES NEGRAS
        for (int i = 0; i < 4; i++) {
            mazoPrincipal.enqueue(new Carta("NEGRO", "CAMBIO_COLOR", -1)); //Cambio de color
            mazoPrincipal.enqueue(new Carta("NEGRO", "MAS4", -1)); //+4 * 2
        }
        // = 8
    }

    //TOTAL 108 CARTAS YIEAH


 public void barajar() {
        // 1. Extraer todas las cartas a una lista
        ArrayList<Carta> lista = new ArrayList<>();
        while (!mazoPrincipal.isEmpty()) {
            lista.add(mazoPrincipal.dequeue());
        }

        // 2. Mezclar aleatoriamente
        Collections.shuffle(lista);

        // 3. Volver a insertar en la cola principal
        for (Carta c : lista) {
            mazoPrincipal.enqueue(c);
        }
    }

 public Carta robarCarta() {
	    if (mazoPrincipal.isEmpty()) {
	        // Si el mazo está vacío, intentamos reciclar del centro
	        if (centro.esVacia()) {
	            return null; // Caso extremo: no hay cartas en ningún lado
	        }

	        // Dejar la carta que está arriba en la mesa y reciclar el resto
	        Carta cartaVisible = centro.eliminar();
	        
	        while (!centro.esVacia()) {
	            mazoPrincipal.enqueue(centro.eliminar());
	        }
	        
	        centro.insertar(cartaVisible);
	        barajar();
	        System.out.println("\n[SISTEMA] El mazo se ha agotado. Barajando el descarte...");
	    }
	    return mazoPrincipal.dequeue();
	}

    public void ponerEnCentro(Carta c) {
        centro.insertar(c);
    }

    public Carta verTopeCentro() {
    if (centro.esVacia()) return null;

    Carta cima = centro.eliminar();
    centro.insertar(cima);
    return cima;
    }
    
}
