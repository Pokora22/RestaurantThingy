package main;

import java.util.Iterator;

public class CustomIterator<T> implements Iterator<T> {
    private Node<T> currPos;

    public CustomIterator(Node<T> currPos){
        this.currPos = currPos;
    }

    @Override
    public boolean hasNext() {
        return currPos != null;
    } //WHY in the whole world are these method names referring 'next' when they check the current object ...

    @Override
    public T next() {
        Node<T> temp = currPos;
        currPos = currPos.next;
        return temp.getContent();
    }
}
