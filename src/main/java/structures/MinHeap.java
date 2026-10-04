package structures;

public class MinHeap {
    private int[] data;
    private int size;

    public MinHeap() {
        data = new int[2];
        size = 0;
    }

    public void insert(int value) {
        ensureCapacity();

        int index = size;
        data[index] = value;
        size++;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (data[parent] <= data[index]) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int[] bigger = new int[data.length * 2];

            for (int i = 0; i < data.length; i++) {
                bigger[i] = data[i];
            }

            data = bigger;
        }
    }

    private void swap(int first, int second) {
        int temporary = data[first];
        data[first] = data[second];
        data[second] = temporary;
    }

    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        return data[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int minimum = data[0];
        data[0] = data[size - 1];
        size--;

        int index = 0;

        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left >= size) {
                break;
            }

            int smallerChild = left;

            if (right < size && data[right] < data[left]) {
                smallerChild = right;
            }

            if (data[index] <= data[smallerChild]) {
                break;
            }

            swap(index, smallerChild);
            index = smallerChild;
        }

        return minimum;
    }

    boolean hasValidHeapProperty() {
        for (int child = 1; child < size; child++) {
            int parent = (child - 1) / 2;

            if (data[parent] > data[child]) {
                return false;
            }
        }

        return true;
    }

}