package main;

import java.util.Iterator;

public class CustomArrayList <T> implements Iterable<T>{
    private static final int DEFAULT_SIZE = 10;
    private Node<T>[] list;

    CustomArrayList(int startSize){
        if(startSize<0)
            list = new Node[DEFAULT_SIZE];
        else
            list = new Node[startSize];
    }

    CustomArrayList(){
        list = new Node[DEFAULT_SIZE];
    }

    public void add(T item){
        for(int i = 0; i < list.length; i++){
            if(list[i]==null){
                list[i] = new Node<T>(item);
                return;
            }
        }

        int nextFreeIndex = list.length;
        expand();
        list[nextFreeIndex] = new Node<T>(item);
    }

    private void expand(){
        Node[] tempList = new Node[list.length*2];
        for(int i = 0; i < list.length; i++){
            tempList[i] = list[i];
        }
        this.list = tempList;
    }

    public void clear(){
        for(Object o : this){
            o = null;
        }
    }

    public Node getNode(int index){
        if (index >=0 && index < list.length)
            return list[index];
        else
            throw new IndexOutOfBoundsException("No element with such index");
    }

    public void setNode(int index, T item){
        if (index >=0 && index < list.length)
            list[index] = new Node<T>(item);
        else
            throw new IndexOutOfBoundsException("No element with such index");
    }

    public int size() {
        int length = 0;
        for(Node element : list){
            if (element != null) length++;
        }
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator<T>(list[0]);
    }
}
