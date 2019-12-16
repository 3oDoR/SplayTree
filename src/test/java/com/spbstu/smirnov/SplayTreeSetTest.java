package test.java.com.spbstu.smirnov;

import main.java.com.spbstu.smirnov.SplayTreeSet;
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

            assertTrue(treeSet.containsAll(splayTreeSet));
            assertTrue(treeSet.contains(toInsert));
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
}
