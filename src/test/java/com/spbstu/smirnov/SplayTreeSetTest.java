package test.java.com.spbstu.smirnov;

import main.java.com.spbstu.smirnov.SplayTreeSet;
import main.java.com.spbstu.smirnov.SubSplayTreeSet;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeSetTest {

    @Test
    void testInsertAndSize() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        Random random = new Random();
        Set<Integer> treeSet = new TreeSet<>();


        for (int i = 0; i <= 100; i++) {
            int toInsert = random.nextInt(100);
            treeSet.add(toInsert);
            splayTreeSet.insert(toInsert);

            System.out.println("Inserting " + toInsert);
            System.out.println("[SplayTreeSet] " + splayTreeSet);
            System.out.println("[TreeSet]      " + treeSet);

            assertTrue(treeSet.contains(toInsert));
            assertTrue(treeSet.containsAll(splayTreeSet));
            assertTrue(splayTreeSet.contains(toInsert));
            assertEquals(treeSet.size(), splayTreeSet.size());
        }
    }

    @Test
    void testRemoveAndSize() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        Random random = new Random();
        Set<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i <= 100; i++) {
            int toInsert = random.nextInt(100);
            treeSet.add(toInsert);
            splayTreeSet.insert(toInsert);
        }

        for (int i = 0; i <= 50; i++) {
            int toRemove = random.nextInt(100);

            while (!treeSet.contains(toRemove)) {
                toRemove = random.nextInt(100);
            }
            splayTreeSet.remove(toRemove);
            treeSet.remove(toRemove);
            assertEquals(treeSet.size(), splayTreeSet.size());
            assertTrue(treeSet.containsAll(splayTreeSet));
            System.out.println(("Removing " + toRemove));
            System.out.println("[SplayTreeSet] " + splayTreeSet);
            System.out.println("[TreeSet]      " + treeSet);
        }
    }

    @Test
    void testContains() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        Random random = new Random();
        Set<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i <= 100; i++) {
            int toInsert = random.nextInt(100);
            treeSet.add(toInsert);
            splayTreeSet.insert(toInsert);
        }

        for (int i = 0; i <= 50; i++) {
            int toContains = random.nextInt(100);

            while (!treeSet.contains(toContains)) {
                toContains = random.nextInt(100);
            }

            assertTrue(treeSet.contains(toContains));
            assertTrue(splayTreeSet.contains(toContains));

            System.out.println(("Contains " + toContains));
            System.out.println("[SplayTreeSet] " + splayTreeSet);
            System.out.println("[TreeSet]      " + treeSet);
        }
    }

    @Test
    void testIsEmpty() {
        SplayTreeSet<Integer> emptySplayTreeSet = new SplayTreeSet<>();
        SplayTreeSet<Integer> filledSplayTreeSet = new SplayTreeSet<>();
        filledSplayTreeSet.insert(1);

        assertTrue(emptySplayTreeSet.isEmpty());
        assertFalse(filledSplayTreeSet.isEmpty());

        System.out.println(("isEmpty for emptySplayTreeSet " + "[emptySplayTreeSet] " + emptySplayTreeSet + " " + emptySplayTreeSet.isEmpty()));
        System.out.println(("isEmpty for filledSplayTreeSet " + "[filledSplayTreeSet] " + filledSplayTreeSet + " " + filledSplayTreeSet.isEmpty()));
    }


    @Test
    void SubSplayTreeFirstAndLast() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i <= 50; i++) {
            splayTreeSet.insert(i);
            treeSet.add(i);
        }

        assertEquals(treeSet.first(), splayTreeSet.first());
        assertEquals(treeSet.last(), splayTreeSet.last());

        assertEquals(treeSet.subSet(10, 40).first(), splayTreeSet.subSet(10, 40).first());
        assertEquals(treeSet.subSet(10, 40).last(), splayTreeSet.subSet(10, 40).last());
    }

    @Test
    void SubSplayTreeSize() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();

        for (int i = 0; i < 10; i++) {
            splayTreeSet.insert(i);
        }

        assertEquals(10, splayTreeSet.size());
        assertEquals(5, splayTreeSet.subSet(5, 10).size());
    }

    @Test
    void SplayTreeSetSub() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        Random random = new Random();

        for (int i = 0; i <= 100; i++) {
            int toInsert = random.nextInt(100);
            treeSet.add(toInsert);
            splayTreeSet.insert(toInsert);
        }

        assertEquals(splayTreeSet.subSet(3, 30).size(), treeSet.subSet(3, 30).size());
    }

    @Test
    void SubSplayToArray() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        Random random = new Random();

        for (int i = 0; i <= 100; i++) {
            int toInsert = random.nextInt(100);
            treeSet.add(toInsert);
            splayTreeSet.insert(toInsert);
        }

        assertEquals(splayTreeSet.subSet(3, 30).size(), splayTreeSet.subSet(3, 30).toArray().length);
        assertEquals(treeSet.subSet(3, 30).size(), treeSet.subSet(3, 30).toArray().length);
    }

    @Test
    void SubSplayContainsAll() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        Random random = new Random();
        List list = new ArrayList();
        for (int i = 0; i <= 100; i++) {
            int toInsert = random.nextInt(100);
            treeSet.add(toInsert);
            splayTreeSet.insert(toInsert);
            if (list.size() != 10) {
                list.add(toInsert);
            }
        }


        assertFalse(treeSet.subSet(3, 30).containsAll(list));
        assertFalse(splayTreeSet.subSet(3, 30).containsAll(list));

        SplayTreeSet<Integer> splayTreeSet2 = new SplayTreeSet<>();
        TreeSet<Integer> treeSet2 = new TreeSet<>();
        Random random2 = new Random();
        List list2 = new ArrayList();
        for (int i = 0; i <= 100; i++) {
            int toInsert = random2.nextInt(20);
            treeSet2.add(toInsert);
            splayTreeSet2.insert(toInsert);
            if (list2.size() != 10) {
                list2.add(toInsert);
            }
        }


        assertTrue(treeSet2.subSet(0, 30).containsAll(list2));
        assertTrue(splayTreeSet2.subSet(0, 30).containsAll(list2));
    }

    @Test
    void SubSplayAddAll() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        List list = new ArrayList();
        for (int i = 0; i <= 25; i++) {
            treeSet.add(i);
            splayTreeSet.insert(i);
        }
        list.add(11);
        list.add(33);

        assertTrue(treeSet.subSet(0, 60).addAll(list));
        assertTrue(splayTreeSet.subSet(0, 60).addAll(list));
        assertEquals(treeSet.subSet(0, 60).size(), splayTreeSet.subSet(0, 60).size());
    }

    @Test
    void SubSplayRetainAll() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        List list = new ArrayList();

        for (int i = 0; i <= 100; i++) {
            treeSet.add(i);
            splayTreeSet.insert(i);
        }

        list.add(1);
        list.add(8);
        list.add(10);

        assertTrue(treeSet.subSet(0, 100).retainAll(list));
        assertTrue(splayTreeSet.subSet(0, 100).retainAll(list));
        assertEquals(splayTreeSet.subSet(0, 100).size(), 3);
        assertEquals(treeSet.subSet(0, 100).size(), 3);

        SplayTreeSet<Integer> splayTreeSet2 = new SplayTreeSet<>();
        TreeSet<Integer> treeSet2 = new TreeSet<>();
        List list2 = new ArrayList();

        for (int i = 0; i <= 100; i++) {
            treeSet2.add(i);
            splayTreeSet2.insert(i);
        }

        list2.add(1);
        list2.add(111);
        list2.add(10);

        assertTrue(treeSet2.subSet(0, 100).retainAll(list2));
        assertTrue(splayTreeSet2.subSet(0, 100).retainAll(list2));

        assertEquals(splayTreeSet2.subSet(0, 100).size(), 2);
        assertEquals(treeSet2.subSet(0, 100).size(), 2);
    }

    @Test
    void SubSplayRemoveAll() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        List list = new ArrayList();

        for (int i = 0; i <= 100; i++) {
            treeSet.add(i);
            splayTreeSet.insert(i);
            if (i != 1 && i != 8 && i != 10) {
                list.add(i);
            }
        }


        assertTrue(treeSet.subSet(0, 100).removeAll(list));
        assertTrue(splayTreeSet.subSet(0, 100).removeAll(list));
        assertEquals(splayTreeSet.subSet(0, 100).size(), 3);
        assertEquals(treeSet.subSet(0, 100).size(), 3);
    }


    @Test
    void SubSplayClearAndIsEmpty() {
        SplayTreeSet<Integer> splayTreeSet = new SplayTreeSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        Random random = new Random();

        for (int i = 0; i <= 100; i++) {
            int toInsert = random.nextInt(100);
            treeSet.add(toInsert);
            splayTreeSet.insert(toInsert);
        }

        assertFalse(treeSet.subSet(0, 50).isEmpty());
        assertFalse(splayTreeSet.subSet(0, 50).isEmpty());

        treeSet.subSet(0, 50).clear();
        splayTreeSet.subSet(0, 50).clear();

        assertTrue(treeSet.subSet(0, 50).isEmpty());
        assertTrue(splayTreeSet.subSet(0, 50).isEmpty());
        assertFalse(treeSet.isEmpty());
        assertFalse(splayTreeSet.isEmpty());
    }


}
