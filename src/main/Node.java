package main;

public class Node <T> {
    private T content;
    public Node<T> next;

    public Node(){
        this.content = null;
        this.next = null;
    }

    public Node(T content){
        this.content = content;
        this.next = null;
    }

    public Node(T content, Node next){
        this.content = content;
        this.next = next;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return getContent().toString();
    }
}
