package test.java.com.spbstu.smirnov;

import main.java.com.spbstu.smirnov.SplayTreeSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeSetTest {

    private SplayTreeSet<Integer> splayTreeSet;

    @Test
    void size() {
        splayTreeSet = new SplayTreeSet<>();
        assertEquals(0, splayTreeSet.size());
        splayTreeSet.insert(11);
        splayTreeSet.insert(2);
        splayTreeSet.insert(4);
        splayTreeSet.insert(21);
        splayTreeSet.insert(15);
        splayTreeSet.insert(0);
        splayTreeSet.insert(33 );
        assertEquals(7, splayTreeSet.size());
    }

    @Test
    void find() {
        splayTreeSet = new SplayTreeSet<>();
        assertEquals(0, splayTreeSet.size());
        splayTreeSet.insert(11);
        splayTreeSet.insert(2);
        splayTreeSet.insert(4);
        splayTreeSet.insert(21);
        splayTreeSet.insert(15);
        splayTreeSet.insert(0);
        splayTreeSet.insert(33 );

        assertTrue(splayTreeSet.find(11));
        assertTrue(splayTreeSet.find(2));
        assertTrue(splayTreeSet.find(4));
        assertTrue(splayTreeSet.find(21));
        assertTrue(splayTreeSet.find(15));
        assertTrue(splayTreeSet.find(0));
        assertTrue(splayTreeSet.find(33));

        assertFalse(splayTreeSet.find(200));
        assertFalse(splayTreeSet.find(54));
    }

    @Test
    void insert() {
        splayTreeSet = new SplayTreeSet<>();
        assertEquals(0, splayTreeSet.size());
        splayTreeSet.insert(11);
        assertEquals(1, splayTreeSet.size());
        assertTrue(splayTreeSet.contains(11));
        splayTreeSet.insert(2);
        assertTrue(splayTreeSet.contains(2));
        assertEquals(2, splayTreeSet.size());
        splayTreeSet.insert(4);
        assertEquals(3, splayTreeSet.size());
        assertTrue(splayTreeSet.contains(4));
    }

    @Test
    void remove() {
        splayTreeSet = new SplayTreeSet<>();
        assertEquals(0, splayTreeSet.size());

        splayTreeSet.insert(11);
        splayTreeSet.insert(2);
        splayTreeSet.insert(4);
        splayTreeSet.insert(21);
        splayTreeSet.insert(15);
        splayTreeSet.insert(0);
        splayTreeSet.insert(33);

        assertEquals(7,splayTreeSet.size());

        splayTreeSet.remove(33);
        assertEquals(6,splayTreeSet.size());






    }







}
