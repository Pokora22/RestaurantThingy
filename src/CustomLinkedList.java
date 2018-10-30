import java.util.Iterator;

public class CustomLinkedList <T> implements Iterable<T>{
    public Node head;

    CustomLinkedList(){
        this.head = null;
    }

    CustomLinkedList(T item){
        this.head = new Node(item);
    }

    public void append(T item)  {
        if (head == null)
            throw new NullPointerException("Linked list head is null.");

        Node temp = head;
        while(temp.next != null)
            temp = temp.next;
        temp.next = new Node<T>(item);
    }

    public void insert(T item, T insertPoint){ //insert after the insert point
        if (head == null)
            throw new NullPointerException("Linked list head is null.");

        for(Node temp = head; temp.next != null; temp = temp.next) {
            if(temp.getContent().equals(insertPoint))
                temp.next = new Node<T>(item, temp.next);
            /*
            else if (temp.next.equals(insertPoint))
                 temp.next.next = new Node(item);
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
}
