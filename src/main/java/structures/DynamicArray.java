package structures;
import metrics.Metrics;

public class DynamicArray {
    private int[] data;
    private int size;
    private final Metrics metrics;

    public DynamicArray() {
        this(new Metrics());
    }

    public DynamicArray(Metrics metrics) {
        this.metrics = metrics;
        data = new int[2];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            resize();
        }

        data[size] = value;
        metrics.moves++;
        size++;
    }

    private void resize() {
        int[] bigger = new int[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            metrics.steps++;
            metrics.moves++;
            bigger[i] = data[i];
        }

        data = bigger;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        metrics.steps++;
        return data[index];
    }

    public int size() {
        return size;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        if (size == data.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            metrics.steps++;
            metrics.moves++;
            data[i] = data[i - 1];
        }

        data[index] = value;
        metrics.moves++;
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        metrics.steps++;
        int removedValue = data[index];

        for (int i = index; i < size - 1; i++) {
            metrics.steps++;
            metrics.moves++;
            data[i] = data[i + 1];
        }

        size--;
        return removedValue;
    }

    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            metrics.steps++;
            metrics.comparisons++;

            if (data[i] == value) {
                return true;
            }
        }

        return false;
    }
}