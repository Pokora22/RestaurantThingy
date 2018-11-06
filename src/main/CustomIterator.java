package main;

import java.util.Iterator;

public class CustomIterator<T> implements Iterator {
    private Node<T> currPos;

    public CustomIterator(Node<T> currPos){
        this.currPos = currPos;
    }

    @Override
    public boolean hasNext() {
        return currPos.next != null;
    }

    @Override
    public T next() {
        Node<T> temp = currPos;
        currPos = currPos.next;
        return temp.getContent();
    }
}
