package structures;
import metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinHeapTest {
    @Test
    void insertKeepsMinimumAtRoot() {
        MinHeap heap = new MinHeap();

        heap.insert(12);
        assertTrue(heap.hasValidHeapProperty());

        heap.insert(4);
        assertTrue(heap.hasValidHeapProperty());

        heap.insert(9);
        assertTrue(heap.hasValidHeapProperty());

        heap.insert(4);
        assertTrue(heap.hasValidHeapProperty());

        heap.insert(20);
        assertTrue(heap.hasValidHeapProperty());

        assertEquals(4, heap.peekMin());
    }

    @Test
    void extractMinReturnsElementsInSortedOrder() {
        MinHeap heap = new MinHeap();

        heap.insert(12);
        assertTrue(heap.hasValidHeapProperty());

        heap.insert(4);
        assertTrue(heap.hasValidHeapProperty());

        heap.insert(9);
        assertTrue(heap.hasValidHeapProperty());

        heap.insert(4);
        assertTrue(heap.hasValidHeapProperty());

        heap.insert(20);
        assertTrue(heap.hasValidHeapProperty());

        assertEquals(4, heap.extractMin());
        assertTrue(heap.hasValidHeapProperty());

        assertEquals(4, heap.extractMin());
        assertTrue(heap.hasValidHeapProperty());

        assertEquals(9, heap.extractMin());
        assertTrue(heap.hasValidHeapProperty());

        assertEquals(12, heap.extractMin());
        assertTrue(heap.hasValidHeapProperty());

        assertEquals(20, heap.extractMin());
        assertTrue(heap.hasValidHeapProperty());
    }

    @Test
    void extractMinOnEmptyHeapThrowsIllegalStateException() {
        MinHeap heap = new MinHeap();

        assertThrows(IllegalStateException.class, () -> heap.extractMin());
    }

    @Test
    void countsArrayReadsAndShifts() {
        Metrics metrics = new Metrics();
        DynamicArray array = new DynamicArray(metrics);

        array.add(10);
        array.add(20);
        array.add(1, 15);

        metrics.reset();

        assertEquals(15, array.get(1));
        assertEquals(1, metrics.steps);

        metrics.reset();

        assertEquals(15, array.remove(1));
        assertEquals(2, metrics.steps);
        assertEquals(1, metrics.moves);
    }
}