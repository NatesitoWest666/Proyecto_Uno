package JUEGOexceptions;
public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super("La lista está vacía");
    }

    public EmptyListException(String mensaje) {
        super(mensaje);
    }
}