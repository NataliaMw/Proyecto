/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.util;

import java.util.Iterator;

/**
 *
 * @author John
 */
public class ArrayList<E> implements List<E>,Iterable<E>{

    private E[] array;
    private int capacity;
    private int current; //cant efectiva

    public ArrayList() {
        capacity = 5;
        array = (E[]) new Object[capacity];
        current = 0;
    }
    
    @Override
    public boolean addFirst(E e) {
        if (e == null) return false;
        if (current == capacity) addCapacity();
        for (int i = current-1; i >= 0; i--) array[i+1] = array[i];            
        array[0] = e;
        current++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) return false;
        if (current == capacity) addCapacity();
        array[current++] = e;
        return true;
    }

    private void addCapacity(){
        E[] tmp = (E[]) new Object[(capacity*3)/2];
        for (int i = 0; i < current; i++) {
            tmp[i] = array[i]; //deep copy
        }
        array = tmp; //Shallow copy
        capacity = (capacity * 3) / 2;
    }
    
    @Override
    public E getFirst() {
        if (isEmpty()) throw new IllegalStateException("No es posible usar esta lista porque está vacía");
        return array[0];
    }

    @Override
    public E getLast() {
        if (isEmpty()) throw new IllegalStateException("No es posible usar esta lista porque está vacía");
        return array[current-1];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < current; i++) {
            if (array[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean removeLast() {
        if (isEmpty()) return false;
        array[current-1] = null;
        current--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) return false;
        array[0] = null;
        desplazar();
        current--;
        return true;
    }
    
    private void desplazar(){
        for (int i = 0; i < current; i++) {
            array[i] = array[i+1];
        }
        array[current-1]=null;
    }

    @Override
    public boolean insert(int index, E e) {
        if (current<=index || index<0) throw new IllegalStateException("Indice fuera de rango");
        if(current==capacity) addCapacity();

        for (int i = current; i >index; i--) {
            array[i] = array[i-1];
        }
        array[index] = e;
        current++;
        return true;
    }

    
    public boolean set(int index, E e) {
        if (current<=index || index<0) throw new IllegalStateException("Indice fuera de rango");
        array[index] = e;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return current == 0;
    }

    @Override
    public E get(int index) {
        if (current<=index || index<0) throw new IllegalStateException("Indice fuera de rango");
        return array[index];
                      
    }

    @Override
    public boolean contains(E e) {
        for (int i = 0; i < current; i++) {
            if (array[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    
    public boolean remove(int index) {
        if (current<=index || index<0) throw new IllegalStateException("Indice fuera de rango");
        array[index] = null;
        for (int i = index; i < current; i++) {
            array[i] =array[i+1];
        }
        
        current--;
        return true;
    }

    @Override
    public int size() {
        return current;
    }
    
    @Override
    public String toString(){
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < current-1; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        sb.append(array[current-1]);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            private int indArr = 0;
            @Override
            public boolean hasNext() {
                if(indArr<current){
                    return true;
                }
                return false;
            }

            @Override
            public E next() {
                indArr++;
                return  array[indArr-1];
            }
            
        };
    return it;
    }
  
}
