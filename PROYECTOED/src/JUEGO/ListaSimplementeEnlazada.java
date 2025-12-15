package JUEGO;

import JUEGOexceptions.BoundaryViolationException;
import JUEGOexceptions.EmptyListException;
import JUEGOexceptions.InvalidPositionException;

class ListaSimplementeEnlazada<E> implements PositionList<E>, Iterable<E> {

    // ---------------------------------------------------------------
    // CLASE NODO
    // ---------------------------------------------------------------
    private static class Nodo<E> implements Position<E> {
        private E dato;              // O(1)
        private Nodo<E> siguiente;   // O(1)

        public Nodo(E dato, Nodo<E> siguiente) {
            this.dato = dato;        // O(1)
            this.siguiente = siguiente; // O(1)
        }

        @Override
        public E element() {
            return dato;             // O(1)
        }
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    private Nodo<E> cabeza;          // O(1)
    private int longitud;            // O(1)

    public ListaSimplementeEnlazada() {
        cabeza = null;               // O(1)
        longitud = 0;                // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // MÉTODO GET POR ÍNDICE
    // ---------------------------------------------------------------
    public E get(int index) {
        if (index < 0 || index >= longitud) { // O(1)
            throw new IndexOutOfBoundsException("Índice inválido: " + index); // O(1)
        }

        Nodo<E> nodo = cabeza;       // O(1)
        for (int i = 0; i < index; i++) { // O(n)
            nodo = nodo.siguiente;   // O(1)
        }
        return nodo.dato;            // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // MÉTODO ADD
    // ---------------------------------------------------------------
    public void add(E e) {
        addLast(e);                  // O(n)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // ITERADOR PARA FOR-EACH
    // ---------------------------------------------------------------
    @Override
    public java.util.Iterator<E> iterator() {
        return new java.util.Iterator<E>() {
            private Nodo<E> actual = cabeza; // O(1)

            @Override
            public boolean hasNext() {
                return actual != null;       // O(1)
            }

            @Override
            public E next() {
                E dato = actual.dato;        // O(1)
                actual = actual.siguiente;   // O(1)
                return dato;                 // O(1)
            }
        };
    }
    // COMPLEJIDAD TEMPORAL POR OPERACIÓN: O(1)
    // COMPLEJIDAD ESPACIAL: O(1)

    // ---------------------------------------------------------------
    public int size() {
        return longitud;             // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    @Override
    public boolean isEmpty() {
        return cabeza == null;       // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // PRIMER ELEMENTO
    // ---------------------------------------------------------------
    @Override
    public Position<E> first() throws EmptyListException {
        if (isEmpty()) throw new EmptyListException(); // O(1)
        return cabeza;             // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // ÚLTIMO ELEMENTO
    // ---------------------------------------------------------------
    @Override
    public Position<E> last() throws EmptyListException {
        if (isEmpty()) throw new EmptyListException(); // O(1)
        Nodo<E> nodo = cabeza;      // O(1)
        while (nodo.siguiente != null) { // O(n)
            nodo = nodo.siguiente;  // O(1)
        }
        return nodo;                // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // POSICIÓN SIGUIENTE
    // ---------------------------------------------------------------
    @Override
    public Position<E> next(Position<E> p)
            throws InvalidPositionException, BoundaryViolationException {

        Nodo<E> nodo = validarPosicion(p); // O(1)
        if (nodo.siguiente == null)        // O(1)
            throw new BoundaryViolationException("No hay siguiente"); // O(1)

        return nodo.siguiente;             // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // AÑADIR AL INICIO
    // ---------------------------------------------------------------
    @Override
    public void addFirst(E e) {
        cabeza = new Nodo<>(e, cabeza); // O(1)
        longitud++;                     // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // AÑADIR AL FINAL
    // ---------------------------------------------------------------
    @Override
    public void addLast(E e) {
        if (isEmpty()) {            // O(1)
            addFirst(e);            // O(1)
        } else {
            Nodo<E> nodo = cabeza;  // O(1)
            while (nodo.siguiente != null) { // O(n)
                nodo = nodo.siguiente; // O(1)
            }
            nodo.siguiente = new Nodo<>(e, null); // O(1)
            longitud++;              // O(1)
        }
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // AÑADIR DESPUÉS DE UNA POSICIÓN
    // ---------------------------------------------------------------
    @Override
    public void addAfter(Position<E> p, E e) throws InvalidPositionException {
        Nodo<E> nodo = validarPosicion(p); // O(1)
        nodo.siguiente = new Nodo<>(e, nodo.siguiente); // O(1)
        longitud++;                        // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // REMOVER ELEMENTO
    // ---------------------------------------------------------------
    @Override
    public E remove(Position<E> p) throws InvalidPositionException {
        Nodo<E> nodo = validarPosicion(p); // O(1)
        E dato = nodo.dato;                // O(1)

        if (nodo == cabeza) {              // O(1)
            cabeza = nodo.siguiente;       // O(1)
        } else {
            Nodo<E> anterior = cabeza;     // O(1)
            while (anterior.siguiente != nodo) { // O(n)
                anterior = anterior.siguiente;   // O(1)
            }
            anterior.siguiente = nodo.siguiente; // O(1)
        }

        longitud--;                         // O(1)
        return dato;                        // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // REEMPLAZAR ELEMENTO
    // ---------------------------------------------------------------
    @Override
    public E set(Position<E> p, E e) throws InvalidPositionException {
        Nodo<E> nodo = validarPosicion(p); // O(1)
        E antiguo = nodo.dato;             // O(1)
        nodo.dato = e;                     // O(1)
        return antiguo;                    // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    // ---------------------------------------------------------------
    // VALIDAR POSICIÓN
    // ---------------------------------------------------------------
    private Nodo<E> validarPosicion(Position<E> p) throws InvalidPositionException {
        if (!(p instanceof Nodo))          // O(1)
            throw new InvalidPositionException("Posición inválida"); // O(1)

        Nodo<E> nodo = (Nodo<E>) p;        // O(1)

        if (nodo.siguiente == null && nodo != cabeza) // O(1)
            throw new InvalidPositionException("Posición eliminada"); // O(1)

        return nodo;                       // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    @Override
    public E element() {
        return null; // O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)
}
