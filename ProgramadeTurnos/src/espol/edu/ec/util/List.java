/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.util;

/**
 *
 * @author Cotrina
 */
public interface List <E> {
    boolean addFirst(E e); //agregar al principio de la lista
    boolean addLast (E e); //agregar al final de la lista
    E getFirst();
    E getLast();
    int indexOf(E e);
    int size();
    boolean removeLast(); 
    boolean removeFirst();
    boolean insert(int index, E e);
    boolean isEmpty();
    E get (int index);
    boolean contains (E e);
    
}
