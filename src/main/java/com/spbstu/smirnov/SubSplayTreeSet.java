package main.java.com.spbstu.smirnov;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SubSplayTreeSet<E extends Comparable<E>> implements SortedSet<E> {

    private SplayTreeSet<E> tree;
    private E fromElement;
    private E toElement;

    public SubSplayTreeSet(SplayTreeSet<E> tree, E fromElement, E toElement) {
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
        return new SubSplayTreeSet<E>(tree, e, e1);
    }

    @NotNull
    @Override
    public SortedSet<E> headSet(E e) {
        return new SubSplayTreeSet<E>(tree, null, e);
    }

    @NotNull
    @Override
    public SortedSet<E> tailSet(E e) {
        return new SubSplayTreeSet<E>(tree, e, null);
    }

    @Override
    public E first() {
        for (E e : tree) {
            if (inRange(e)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public E last() {
        E lastElement = null;
        for (E last : tree) {
            if (inRange(last)) {
                if (lastElement == null) {
                    lastElement = last;
                }
                if (lastElement.compareTo(last) < 0) {
                    lastElement = last;
                }
            }
        }
        return lastElement;
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
        return size() == 0;
    }


    @Override
    public boolean contains(Object o) {
        if (inRange((E) o)) {
            return tree.contains(o);
        }
        return false;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new SubSplayTreeSetIterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        int massiveSize = 5;
        Object[] massive = new Object[massiveSize];
        int count = 0;
        for (E e : tree) {
            if (inRange(e)) {
                massive[count] = e;
                count++;
                if (count == massiveSize) {
                    massive = Arrays.copyOf(massive, massiveSize * 2);
                    massiveSize = massiveSize * 2;
                }
            }
        }
        return massive;
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] ts) {
        T[] mas = (T[]) Arrays.copyOf(ts, size(), ts.getClass());
        int count = 0;
        for (E e : tree) {
            if (inRange(e)) {
                mas[count] = (T) e;
                count++;
            }
        }
        if (ts.length < count + 1) {
            return mas;
        } else {
            System.arraycopy(mas, 0, ts, 0, mas.length);
            return ts;
        }
    }


    @Override
    public boolean add(E e) {
        if (inRange(e)) {
            return tree.insert(e);
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        E removeItem = (E) o;
        int lastSize = size();
        if (inRange(removeItem)) {
            tree.remove(removeItem);
        }
        return lastSize > size();
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> collection) {
        for (Object e : collection) {
            E elements = (E) e;
            if (inRange(elements)) {
                if (!contains(elements)) {
                    return false;
                }
            } else return false;
        }
        return true;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> collection) {
        int lastSize = size();
        for (Object e : collection) {
            E elements = (E) e;
            if (inRange(elements)) {
                add(elements);
            } else throw new IllegalArgumentException("key out of range");
        }
        return lastSize != size();
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> collection) {
        int lastSize = size();
        for (Object e : tree) {
            E elements = (E) e;
            if (!collection.contains(elements)) {
                remove(elements);
            }
        }
        return lastSize != size();
    }


    @Override
    public boolean removeAll(@NotNull Collection<?> collection) {
        int lastSize = size();
        for (Object e : collection) {
            E elements = (E) e;
            if (contains(elements)) {
                remove(elements);
            }
        }
        return lastSize != size();
    }

    @Override
    public void clear() {
        for (E e : tree) {
            if (inRange(e)) {
                tree.remove(e);
            }
        }
    }

    private class SubSplayTreeSetIterator implements Iterator<E> {
        Iterator<E> iterator = tree.iterator();
        Deque<E> stack = new ArrayDeque<>();

        SubSplayTreeSetIterator() {
            while (iterator.hasNext()) {
                E itNext = iterator.next();
                if (inRange(itNext)) {
                    stack.offer(itNext);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            return stack.pop();
        }

    }
}
