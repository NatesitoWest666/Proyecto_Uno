package JUEGO;

public class Pila<T> {
    private T[] p; // O(1)
    private int tope, max; // O(1)

    @SuppressWarnings("unchecked")
    public Pila(int max) {
        this.max = max; // O(1)
        this.tope = -1; // O(1)
        this.p = (T[]) new Object[max]; // O(n) - RESERVA DE ESPACIO
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1) (solo inicializa variables)
    // COMPLEJIDAD TOTAL ESPACIAL: O(n) (debido a la creación del arreglo 'p' de tamaño 'max')

    public boolean esVacia() {
        return tope == -1; // O(1) - COMPARACIÓN
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public boolean esLlena() {
        return tope == max - 1; // O(1) - COMPARACIÓN
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public void insertar(T x) {
        if (esLlena()) { // O(1)
            System.out.println("Pila llena"); // O(1)
        } else {
            p[++tope] = x; // O(1) - ACCESO A UNA POSICIÓN
        }
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public T eliminar() {
        if (esVacia()) { // O(1)
            System.out.println("Pila vacia"); // O(1)
            return null; // O(1)
        } else {
            return p[tope--]; // O(1) - ACCESO A UNA POSICIÓN
        }
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public T cima() {
        if (esVacia()) return null; // O(1)
        return p[tope]; // O(1) - ACCESO A UNA POSICIÓN
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1)

    public void vaciarPila(Pila<T> otra) {
        
        while (!otra.esVacia()) { // O(n) ITERACIONES
            insertar(otra.eliminar()); // O(1) + O(1) = O(1) por iteración
        }
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1) (Solo usa variables locales y llamadas a métodos O(1))

    public void mostrar() {
        if (esVacia()) { // O(1)
            System.out.println("Pila vacía"); // O(1)
        } else {
            // Se crea una Pila Auxiliar del mismo tamaño, N
            Pila<T> aux = new Pila<>(max); // O(1) tiempo, O(n) espacio
            
            // Bucle 1: Pasa N elementos de la pila original a la auxiliar
            while (!esVacia()) { // O(n) ITERACIONES
                T x = eliminar(); // O(1)
                System.out.println(x); // O(1) (impresión por consola)
                aux.insertar(x); // O(1)
            }
            
            // Bucle 2: Regresa N elementos de la pila auxiliar a la original
            vaciarPila(aux); // O(n) (llama al método analizado arriba)
        }
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n)
    // COMPLEJIDAD TOTAL ESPACIAL: O(n)
}