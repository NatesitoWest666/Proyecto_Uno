package JUEGOexceptions;
public class BoundaryViolationException extends RuntimeException {
    public BoundaryViolationException(String mensaje) {
        super(mensaje);
    }
}
