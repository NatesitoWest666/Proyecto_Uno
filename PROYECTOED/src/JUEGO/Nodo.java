package JUEGO;
public class Nodo {
	//GENERA LOS ATRIBUTOS DE LA CLASE NODO
    private Object dato; //ATRIBUTO: Dato - tipo Objeto
    private Nodo siguiente; 
    private Nodo anterior;

    
    //Click Dere -> acción de codig fuente, generate constructor
    public Nodo(Object dato, Nodo siguiente, Nodo anterior) {
        this.dato = dato;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    //GETTERS Y SETTERS
    public Object getDato() {
        return dato;
    }


    public void setDato(Object dato) {
        this.dato = dato;
    }


    public Nodo getSiguiente() {
        return siguiente;
        
    }


    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }


    public Nodo getAnterior() {
        return anterior;
    }


    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    





}
