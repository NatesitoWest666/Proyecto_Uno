package JUEGO;

import java.util.EmptyStackException;

public interface TDACola<E> {

	public int size();
	public boolean isEmpty();
	
	public void enqueue(E item) throws IllegalStateException;
	public E dequeue() throws EmptyStackException;
	public E first() throws EmptyStackException;
	
	
}
