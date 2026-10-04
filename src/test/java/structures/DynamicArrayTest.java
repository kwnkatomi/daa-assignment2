package structures;

import org.junit.jupiter.api.Test;
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

    @Test
    public void removeAtIndexRemovesValueAndShiftsElements() {
        DynamicArray array = new DynamicArray();
        array.add(10);
        array.add(20);
        array.add(30);

        int removedValue = array.remove(1);

        assertEquals(20, removedValue);
        assertEquals(2, array.size());
        assertEquals(10, array.get(0));
        assertEquals(30, array.get(1));
    }

    @Test
    public void containsReturnsTrueForExistingValue() {
        DynamicArray array = new DynamicArray();
        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(true, array.contains(20));
    }

    @Test
    public void containsReturnsFalseForNonExistingValue() {
        DynamicArray array = new DynamicArray();
        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(false, array.contains(40));
    }
}