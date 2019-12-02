package main.java.com.spbstu.smirnov;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

public class SubSplayTreeSet<E extends Comparable<E>> implements SortedSet<E> {

    private SplayTreeSet<E> tree;
    private E fromElement;
    private E toElement;

    SubSplayTreeSet(SplayTreeSet<E> tree, E fromElement, E toElement) {
        this.tree = tree;
        this.fromElement = fromElement;
        this.toElement = toElement;
    }

    private boolean inRange(E e) {
        if ((toElement == null || toElement.compareTo(e) > 0) && (fromElement == null || fromElement.compareTo(e) <= 0)) {
            return true;
        }
        return false;
    }


    @Nullable
    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<E> subSet(E e, E e1) {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<E> headSet(E e) {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<E> tailSet(E e) {
        return null;
    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }

    @Override
    public int size() {
        int size = 0;
        for (E e : tree) {
            if (inRange(e)) {
                size++;
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] ts) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    private class SubSplayTreeSetIterator implements Iterator<E> {
        Iterator<E> iterator = tree.iterator();
        E next;

        private SubSplayTreeSetIterator() {
            while (iterator.hasNext()) {
                E itNext = iterator.next();
                if (inRange(itNext)) {
                    this.next = itNext;
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            E result = next;
            if (iterator.hasNext()) {
                next = iterator.next();
            } else next = null;
            return result;
        }
    }
}
