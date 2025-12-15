package JUEGO;

import JUEGOexceptions.BoundaryViolationException;
import JUEGOexceptions.EmptyListException;
import JUEGOexceptions.InvalidPositionException;

public interface PositionList<E> extends Position<E> {
    int size();
    boolean isEmpty();
    Position<E> first() throws EmptyListException;
    Position<E> last() throws EmptyListException;
    Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
    void addFirst(E e);
    void addLast(E e);
    void addAfter(Position<E> p, E e) throws InvalidPositionException;
    E remove(Position<E> p) throws InvalidPositionException;
    E set(Position<E> p, E e) throws InvalidPositionException;

}