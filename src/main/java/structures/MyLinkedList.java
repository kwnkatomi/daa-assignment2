package structures;

import metrics.Metrics;

public class MyLinkedList {
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }
    private final Metrics metrics;

    public Node head;
    public Node tail;
    private int size;

    public MyLinkedList() {
        this(new Metrics());
    }

    public MyLinkedList(Metrics metrics) {
        this.metrics = metrics;
    }

    public void add(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            metrics.moves++;
            head = newNode;
        } else {
            metrics.moves++;
            tail.next = newNode;
        }

        metrics.moves++;
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
            metrics.steps++;
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
            metrics.moves++;
            newNode.next = head;
            metrics.moves++;
            head = newNode;

            if (size == 0) {
                metrics.moves++;
                tail = newNode;
            }
        } else {
            Node previous = head;

            for (int i = 0; i < index - 1; i++) {
                metrics.steps++;
                previous = previous.next;
            }

            newNode.next = previous.next;
            metrics.moves++;
            previous.next = newNode;

            if (index == size) {
                metrics.moves++;
                tail = newNode;
            }
        }

        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        metrics.steps++;

        Node removed;

        if (index == 0) {
            removed = head;
            metrics.moves++;
            head = head.next;

            if (size == 1) {
                metrics.moves++;
                tail = null;
            }
        } else {
            Node previous = head;

            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
                metrics.steps++;
            }

            removed = previous.next;
            metrics.moves++;
            previous.next = removed.next;

            if (removed == tail) {
                metrics.moves++;
                tail = previous;
            }
        }

        size--;
        return removed.value;
    }

    public boolean contains(int value) {
        Node current = head;

        while (current != null) {
            metrics.comparisons++;

            if (current.value == value) {
                return true;
            }

            if (current.next != null) {
                metrics.steps++;
            }

            current = current.next;
        }

        return false;
    }
}