package structures;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DynamicArrayTest {
    @Test
    public void addAndGetReturnValuesAtTheirIndices() {
        DynamicArray array = new DynamicArray();
        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(3, array.size());
        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    public void addAtIndexInsertsValueAtCorrectPosition() {
        DynamicArray array = new DynamicArray();
        array.add(10);
        array.add(30);
        array.add(1, 20);

        assertEquals(3, array.size());
        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    public void indexedAddRejectsIndexGreaterThanSize() {
        DynamicArray array = new DynamicArray();

        assertThrows(IndexOutOfBoundsException.class, () -> array.add(1, 99));
    }
}