package main;

public class Node <T> {
    private T content;
    public Node next;

    public Node(T item){
        this.content = item;
        this.next = null;
    }

    public Node(T item, Node next){
        this.content = item;
        this.next = next;
    }


    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
