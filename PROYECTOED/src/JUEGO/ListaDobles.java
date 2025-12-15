package JUEGO;

public class ListaDobles<E> {

    private Nodo inicio;    // O(1)
    private Nodo fin;       // O(1)
    private int cantidad;   // O(1)

    public ListaDobles() {
        this.inicio = null;    // O(1)
        this.fin = null;       // O(1)
        this.cantidad = 0;     // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public boolean vacia(){
        return inicio == null; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public void agregarFinal(E dato){
        if(vacia()){ // O(1)
            inicio = fin = new Nodo(dato, null, null); // O(1)
        } else {
            Nodo nuevo = new Nodo(dato, null, fin); // O(1)
            fin.setSiguiente(nuevo); // O(1)
            fin = nuevo; // O(1)
        }
        cantidad++; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public void agregarInicio(E dato){
        if(vacia()){ // O(1)
            inicio = fin = new Nodo(dato, null, null); // O(1)
        } else {
            Nodo nuevo = new Nodo(dato, inicio, null); // O(1)
            inicio.setAnterior(nuevo); // O(1)
            inicio = nuevo; // O(1)
        }
        cantidad++; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public void eliminarInicio() {
        if(vacia()) return; // O(1)

        if (inicio == fin) { // O(1)
            inicio = fin = null; // O(1)
        } else {
            inicio = inicio.getSiguiente(); // O(1)
            inicio.setAnterior(null); // O(1)
        }
        cantidad--; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public void eliminarPorIndice(int index){
        if(vacia() || index < 0 || index >= cantidad) return; // O(1)

        Nodo aux = inicio; // O(1)
        for(int i = 0; i < index; i++){ // O(n)
            aux = aux.getSiguiente(); // O(1)
        }

        if(aux == inicio){ // O(1)
            eliminarInicio(); // O(1)
            return; // O(1)
        } else if(aux == fin){ // O(1)
            fin = aux.getAnterior(); // O(1)
            fin.setSiguiente(null); // O(1)
        } else {
            aux.getAnterior().setSiguiente(aux.getSiguiente()); // O(1)
            aux.getSiguiente().setAnterior(aux.getAnterior()); // O(1)
        }
        cantidad--; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public E obtenerPorIndice(int index){
        if(vacia() || index < 0 || index >= cantidad) return null; // O(1)

        Nodo aux = inicio; // O(1)
        for(int i = 0; i < index; i++){ // O(n)
            aux = aux.getSiguiente(); // O(1)
        }
        return (E) aux.getDato(); // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public boolean eliminarElemento(Object dato){
        Nodo aux = inicio; // O(1)
        int i = 0; // O(1)

        while(aux != null){ // O(n)
            if(aux.getDato().equals(dato)){ // O(1)
                eliminarPorIndice(i); // O(n)
                return true; // O(1)
            }
            aux = aux.getSiguiente(); // O(1)
            i++; // O(1)
        }
        return false; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n²) en el peor caso
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // MÉTODOS AUXILIARES
    public int getCantidad() {
        return cantidad; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public String mostrarInicioFin(){
        StringBuilder salida = new StringBuilder(); // O(1)
        Nodo aux = inicio; // O(1)

        while(aux != null){ // O(n)
            salida.append(aux.getDato()).append(", "); // O(1)
            aux = aux.getSiguiente(); // O(1)
        }
        return salida.length() > 0
                ? salida.substring(0, salida.length() - 2)
                : "";
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(n)

    @Override
    public String toString() { 
        String contenido = mostrarInicioFin(); // O(n)
        return "[ " + contenido + " ]"; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(n)
}
