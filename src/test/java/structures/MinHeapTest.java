package structures;

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
}