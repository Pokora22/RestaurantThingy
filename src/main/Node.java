package main;

public class Node <T> {
    private T content;
    public Node next;

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
}
