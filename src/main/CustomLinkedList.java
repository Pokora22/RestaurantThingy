package main;

import java.util.AbstractList;
import java.util.Iterator;

public class CustomLinkedList <T> extends AbstractList<T> implements Iterable<T>{
    public Node<T> head;

    CustomLinkedList(){
        this.head = null;
    }

    CustomLinkedList(T item){
        this.head = new Node(item);
    }

    public boolean add(T item)  {
        if (head == null){
            head = new Node(item);
            return true;
        }

        Node<T> temp = head;
        while(temp.next != null)
            temp = temp.next;
        temp.next = new Node(item);
        return true;
    }

    @Override
    public boolean remove(Object item) {
        for (Node temp = head; temp.next != null; temp = temp.next) {
            if (temp.next.equals(item)){
                while(temp.next != null){
                    temp.next = temp.next.next;
                    temp = temp.next;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        Node<T> node = head;
        for(int i = 0; i < index; i++) node = node.next;
        return node.getContent();
    }

    public void insert(T item, T insertPoint){ //insert after the insert point
        if (head == null)
            throw new NullPointerException("Linked list head is null.");

        for(Node temp = head; temp.next != null; temp = temp.next) {
            if(temp.getContent().equals(insertPoint))
                temp.next = new Node<T>(item, temp.next);
            /*
            else if (temp.next.equals(insertPoint))
                 temp.next.next = new main.Node(item);
             */
        }
    }

    @Override
    public String toString(){
        StringBuilder list = new StringBuilder("");
        if (head == null)
            throw new NullPointerException("Linked list head is null.");

        for(Node temp = head; temp != null; temp = temp.next)
            list.append(temp.getContent().toString() + "\n");
        return list.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator<T>(head);
    }

    @Override
    public int size() {
        if(head == null) return 0;

        int size = 0;
        for(T element : this){
            if (element != null) size++;
            else break;
        }
        return size;
    }

}
