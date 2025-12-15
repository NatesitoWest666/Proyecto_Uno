package JUEGO;

import java.util.EmptyStackException;

public class ColaConLista<E> implements TDACola<E> {

    // INICIALIZACIÓN DE VARIABLES
    private NodoCola<E> cabeza, cola; // O(1)
    private int tamaño; // O(1)

    public ColaConLista() {
        super(); // O(1) - INICIALIZA CLASE PADRE
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    @Override
    public int size() {
	return tamaño; // O(1) - RETORNO VARIABLE DE TAMAÑO FIJO
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    @Override
    public boolean isEmpty() {
        return cabeza==null; // O(1) - COMPROBACIÓN BÁSICA
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    @Override
    public void enqueue(E item) throws IllegalStateException {
        NodoCola<E> nuevo = new NodoCola<E>(item); // O(1) - Creación de un nodo
        if(cola == null) { // O(1) - Verificación
            cola = cabeza = nuevo; // O(1) - Encola el primer elemento
        }
        else {
            cola.siguiente = nuevo; // O(1) - Enlaza el nuevo nodo al final
            cola = nuevo; // O(1) - Mueve el puntero cola
        }
        tamaño++; // O(1) - Incrementa el tamaño
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    @Override
    public E dequeue() throws EmptyStackException {
        if(cola==null) { // O(1) - Comprobación de lista vacía
            throw new EmptyStackException(); // O(1)
        }
        E eliminado = cabeza.dato; // O(1) - Obtiene el dato a desencolar
        cabeza = cabeza.siguiente; // O(1) - Mueve el puntero cabeza (borrado lógico)
        
        //CASO DE QUE SOLO HAYA UN DATO
        if(cabeza==null) { // O(1) - Comprueba si la cola quedó vacía
            cola=null; // O(1) - Resetea el puntero cola
        }
        
        tamaño--; // O(1)
        
        return eliminado; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    @Override
    public E first() throws EmptyStackException {
        if(cabeza==null) { // O(1) - Comprobación de lista vacía
            throw new EmptyStackException(); // O(1)
        }
        
        return cabeza.dato; // O(1) - Retorna el dato sin modificar la estructura
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)
    
    public void clear() {
        cabeza=null; // O(1)
        cola=null; // O(1)
        tamaño=0; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    
    public String mostrar() {
        int cont2=0; // O(1)
        if(cabeza==null) { // O(1)
            return "ColaConLista vacía"; // O(1)
        }
        
        NodoCola<E> recorre = cabeza; // O(1)
        StringBuilder sb = new StringBuilder(); // O(1)
        sb.append("ColaConLista = {"); // O(1)
        
        // El bucle recorre los N elementos de la cola.
        while(recorre!=null) { // O(n)
            sb.append(recorre.dato); // O(1) por iteración
            recorre = recorre.siguiente; // O(1) por iteración
            cont2++; // O(1) por iteración
            
            if(cont2<tamaño) { // O(1) por iteración
                sb.append(" - "); // O(1) por iteración
            }
        }
        sb.append("}"); // O(1)
        
        return sb.toString(); // O(n) - La conversión final del StringBuilder a String toma tiempo lineal
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(n)
}