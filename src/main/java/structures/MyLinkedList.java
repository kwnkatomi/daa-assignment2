package structures;

public class MyLinkedList {
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public Node head;
    public Node tail;
    private int size;

    public void add(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;

        size++;
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node newNode = new Node(value);

        if (index == 0) {
            newNode.next = head;
            head = newNode;

            if (size == 0) {
                tail = newNode;
            }
        } else {
            Node previous = head;

            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }

            newNode.next = previous.next;
            previous.next = newNode;

            if (index == size) {
                tail = newNode;
            }
        }

        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node removed;

        if (index == 0) {
            removed = head;
            head = head.next;

            if (size == 1) {
                tail = null;
            }
        } else {
            Node previous = head;

            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }

            removed = previous.next;
            previous.next = removed.next;

            if (removed == tail) {
                tail = previous;
            }
        }

        size--;
        return removed.value;
    }

    public boolean contains(int value) {
        Node current = head;

        while (current != null) {
            if (current.value == value) {
                return true;
            }

            current = current.next;
        }

        return false;
    }
}