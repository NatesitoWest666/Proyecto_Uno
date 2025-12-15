package JUEGO;

import java.util.*;

public class Jugador {
    private String nombre;
    private boolean dijoUno;
    private ListaDobles<Carta> mano;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.dijoUno = false;
        this.mano = new ListaDobles<>();
    }

    // ... (getters y setters se mantienen igual) ...

    public void agregarCarta(Carta c) {
        mano.agregarFinal(c);
    }

    public void ordenarMano() {
        int tamano = mano.getCantidad();
        if (tamano <= 1) return;

        // 1. Extraer a lista temporal evitando nulos
        ArrayList<Carta> listaTemp = new ArrayList<>();
        for (int i = 0; i < tamano; i++) {
            Carta c = mano.obtenerPorIndice(i);
            if (c != null) {
                listaTemp.add(c);
            }
        }

     // 2. Algoritmo Insertion Sort
        ComparadorCartas comp = new ComparadorCartas(); // O(1) - CREACIÓN DEL COMPARADOR

        for (int i = 1; i < listaTemp.size(); i++) { // O(n) ITERACIONES EXTERNAS
            Carta llave = listaTemp.get(i); // O(1) SI listaTemp ES ARRAY/ARRAYLIST
            int j = i - 1; // O(1)

            while (j >= 0 && comp.compare(listaTemp.get(j), llave) > 0) {
                // CONDICIÓN: j >= 0  O(1)
                // comp.compare(...) O(1)
                // listaTemp.get(j) O(1) (SI ES ARRAY/ARRAYLIST)

                listaTemp.set(j + 1, listaTemp.get(j)); // O(1)
                j--; // O(1)
            }

            listaTemp.set(j + 1, llave); // O(1)
        }


        // 3. RECONSTRUCCIÓN SEGURA:
        while (mano.getCantidad() > 0) {
            mano.eliminarPorIndice(0);
        }

        for (Carta c : listaTemp) {
            mano.agregarFinal(c);
        }
    }

    public void robarCarta(Mazo mazo) {
        Carta c = mazo.robarCarta();
        // SEGURIDAD: Si el mazo devuelve null, no hacemos nada (no dañamos la mano)
        if (c != null) {
            agregarCarta(c);
        }
    }
    // REEMPLAZO: Búsqueda lineal recursiva (la binaria fallaba por las reglas de Color/Número)
    public boolean tieneCartaValida(Carta cartaEnMesa) {
        return buscarValidaRec(cartaEnMesa, 0);
    }

    private boolean buscarValidaRec(Carta cima, int indice) {
        if (indice >= mano.getCantidad()) return false;
        
        if (ReglasUno.esCartaValida(mano.obtenerPorIndice(indice), cima)) {
            return true;
        }
        return buscarValidaRec(cima, indice + 1);
    }

    public Carta jugarCarta(int indice) {
        Carta jugada = mano.obtenerPorIndice(indice);
        mano.eliminarPorIndice(indice);
        return jugada;
    }

    public int getCantidadCartas() {
        return mano.getCantidad();
    }

    public String getNombre() { 
    	return nombre; 
    	}
    
    public ListaDobles<Carta> getMano() { 
    	return mano; 
    	}
}