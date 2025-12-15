package JUEGO;
public class NodoCola<E> implements Position<E> {
	
	NodoCola<E> siguiente;
	E dato;
	
	public NodoCola(E item) {
		super();
		this.dato = item;
		this.siguiente = null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return dato;
	}
	
}
