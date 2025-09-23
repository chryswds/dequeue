package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node{
        Item item;
        Node next;
        Node previous;
        public Node(Item item){
            this.item = item;
        }
    }


    private Node first, last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }



    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        Node newNode = new Node(item);
        size++;
        if (first == null){
            first = newNode;
            last = newNode;
            return;
        }
        Node oldFirst = first;

        newNode.next = oldFirst;

        oldFirst.previous = newNode;

        first = newNode;

    }

    public void removeFirst(){
        if (first == null) throw new NoSuchElementException("Cannot remove from empty deque");
        size--;
        if(first.next == null){
            Item item = first.item;
            first = null;
            last = null;
            return;
        }
        Node oldFirst = first.next;
        first= first.next;
        first.previous = null;
        oldFirst.next = null;
    }

    public void removeLast(){
        if (last == null) throw new NoSuchElementException("Cannot remove from empty deque");
        size--;
        if(last.previous == null){
            Item item = last.item;
            last = null;
            first = null;
            return;
        }
        Node oldLast = last.previous.next;
        last= last.previous;
        last.next = null;
        oldLast.previous = null;
    }

    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }

        public Item next(){
            if(!hasNext()){ throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
}
