package structures;

import org.junit.jupiter.api.Test;

@SuppressWarnings("ALL")
public class MyLinkedListTest {

    @Test
    public void testAdd() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void testSize() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        assert list.size() == 3;
    }

    @Test
    public void testGet() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        assert list.get(0) == 1;
        assert list.get(1) == 2;
        assert list.get(2) == 3;
    }

    @Test
    public void testAddAtIndex() {
        MyLinkedList list = new MyLinkedList();

        list.add(0, 1);
        assert list.get(0) == 1;

        list.add(0, 2);
        assert list.get(0) == 2;
        assert list.get(1) == 1;

        list.add(1, 3);
        assert list.get(0) == 2;
        assert list.get(1) == 3;
        assert list.get(2) == 1;

        list.add(3, 4);
        assert list.get(0) == 2;
        assert list.get(1) == 3;
        assert list.get(2) == 1;
        assert list.get(3) == 4;

        try {
            list.add(-1, 5);
            assert false;
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            list.add(5, 5);
            assert false;
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Test
    public void testRemoveOnlyElement() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        assert list.size() == 1;
        assert list.get(0) == 1;

        int removed = list.remove(0);
        assert removed == 1;
        assert list.size() == 0;

        assert list.head == null;
        assert list.tail == null;
    }

    @Test
    public void testRemove() {

        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);

        int removed = list.remove(1);
        assert removed == 2;
        assert list.size() == 3;
        assert list.get(0) == 1;
        assert list.get(1) == 3;
        assert list.get(2) == 2;

        removed = list.remove(2);
        assert removed == 2;
        assert list.size() == 2;
        assert list.get(0) == 1;
        assert list.get(1) == 3;

        try {
            list.remove(-1);
            assert false;
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            list.remove(2);
            assert false;
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Test
    public void containsTest() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        assert list.contains(1);
        assert list.contains(2);
        assert list.contains(3);
        assert !list.contains(4);
    }

}