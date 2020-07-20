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
public class SimplyLinkedList<E> implements List<E>, Iterable<E> {

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            private Node<E> p = first;
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E tmp = p.getData();
                p = p.getNext();
                return tmp;
            }
            
        };
    return it;
    }

    
    private class Node<E>{
        private E data;
        private Node<E> next;
        
        public Node(E data){
            this.data = data;
            this.next = null;
        }   

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }        
        
        public String toString(){
            return data.toString();
        }
        
    }
    
    private Node<E> first;
    private Node<E> last;
    private int current; 
    
    public SimplyLinkedList(){
        first = last = null;
        current = 0;
    }
    
    @Override
    public boolean addFirst(E e) {
        if (e == null) return false;
        Node<E> nuevo = new Node<>(e);
        if (isEmpty()) first = last = nuevo;
        else{
            nuevo.setNext(first);
            first = nuevo;
        }
        current++;
        return true;    
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) return false;
        Node<E> no = new Node<>(e);
        if(isEmpty()) first = last = no;
        else{
            last.setNext(no);
            last = no;
        }
        current++;
        return true;
    }
    
    @Override
    public E getFirst() {
        if (first == null & null == last) throw new NullPointerException("No hay elementos");
        return first.data;
    }

    @Override
    public E getLast() {
        if (first == null & null == last) throw new NullPointerException("No hay elementos");
        return last.data;
    }

    @Override
    public int indexOf(E e) {
        int index = 0;
        for (Node<E> q = first; q!=null; q = q.getNext()) {
            if (q.getData().equals(e)){
                return index;
            }
            index++;
        }
        throw new IllegalStateException("Elemento no encontrado");
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean removeLast() {
       if (isEmpty()) return false;
        else if (first == last){
            first.setData(null); //ayudar al Garbage collector
            last.setData(null);  //ayudar al Garbage collector
            first = last = null;            
        }else{
            Node<E> f = getPrevious(last);
            last.data = null;
            f.next = null;
            last = f;
        }
       current--;    
       return true;  
    }
    
    private Node<E> getPrevious(Node<E> p){
        if( p == first) return null;
        for (Node <E> q = first; q!=null;q = q.getNext()) {
            if (q.getNext() == p) return q;            
        }
        return null;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) return false;
        else if (first == last){
            first.setData(null); //ayudar al Garbage collector
            last.setData(null);  //ayudar al Garbage collector
            first = last = null;            
        }else{
            Node<E> n = first;
            first = first.getNext();
            n.setData(null);
            n.setNext(null);
        }
        current--;    
        return true;        
    }
    
    private Node<E> encontrarNodoInd(int index){
        if (isEmpty()|| current<=index) throw new IllegalStateException("Elemento no encontrado");
        int cont = 0;
        for (Node<E> q = first; q!=null; q = q.getNext()) {
            if (cont == index) return q;
            cont++;
        }
        throw new IllegalStateException("Elemento no encontrado");
    }
    
    @Override
    public boolean insert(int index, E e) {
        if (e == null) return false;
        
        if (isEmpty()|| current<=index) throw new IllegalStateException("indice fuera del rango");
        
        else if(index == 0) addFirst(e);
        else if(index == (current -1)) addLast(e);
        else{
            Node<E> nuevo = new Node<>(e);        
            Node<E> mover = encontrarNodoInd(index-1);
            nuevo.next = mover.next;
            mover.setNext(nuevo);       
            
        }
        current++;
        return true;
        
    }

    /**
     *
     * @param index
     * @param e
     * @return
     */
    public boolean set(int index, E e) {
        if (e == null) return false;
        if (isEmpty()|| current<=index) throw new IllegalStateException("indice fuera del rango");
        else{
            Node<E> editar = encontrarNodoInd(index);
            editar.data = e;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return first == null & last == null;
    }

    @Override
    public E get(int index) {
        if (isEmpty()|| current<=index) throw new IllegalStateException("Elemento no encontrado");
        int cont = 0;
        for (Node<E> q = first; q!=null; q = q.getNext()) {
            if (cont==index){
                return q.data;
            }
            cont++;
        }
        throw new IllegalStateException("Elemento no encontrado");
    }

    @Override
    public boolean contains(E e) {
        Node<E> n = first;
        while(n!=null && n.next!=e){
            if (n.getData().equals(e)) return true;
            n = n.getNext();
            
        }            
        return false;
    }

    public boolean remove(int index) {       
        if (isEmpty()|| current<=index) return false;
        else if (index==0) removeFirst();
        else if (index ==(current-1)) removeLast();
        Node<E> borrar = encontrarNodoInd(index);
        Node<E> anterior = getPrevious(borrar);
        anterior.setNext(borrar.next);
        borrar.setData(null);
        borrar.setNext(null);
        current--;
        return true;
    }
    
    @Override
    public String toString()
    {
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Node<E> p = first; p!=last; p=p.next)//hasta el penultimo
        {
            sb.append(p.data);
            sb.append(",");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }
    
}
