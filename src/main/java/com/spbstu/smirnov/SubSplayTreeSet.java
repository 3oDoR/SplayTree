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

    public String toString() {
        return tree.toString();
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
        E first = null;
        for (E e : tree) {
            if (inRange(e)) {
                if (first == null) {
                    first = e;
                }
                if (first.compareTo(e) > 0) {
                    first = e;
                }
            }
        }
        return first;
    }

    @Override
    public E last() {
        E last = null;
        for (E e : tree) {
            if (inRange(e)) {
                if (last == null) {
                    last = e;
                }
                if (last.compareTo(e) < 0) {
                    last = e;
                }
            }
        }
        return last;
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
        Object[] massive = new Object[size()];
        int count = 0;
        for (E e : tree) {
            if (inRange(e)) {
                massive[count] = e;
                count++;
            }

        }
        return massive;
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] ts) {
        //Работает только при верном размере массива.(Не понял как создать массив T)
        int count = 0;
        for (E e : tree) {
            if (inRange(e)) {
                ts[count] = (T) e;
                count++;
            }
        }
        return ts;
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
        if (inRange(removeItem)) {
            if (tree.contains(removeItem)) {
                tree.remove(removeItem);
                return true;
            }
        }
        return false;
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
//        //Проверка на успешное добавление всех элементов
//        for (Object e : collection) {
//            E elements = (E) e;
//            if (inRange(elements)) {
//                if (contains(elements)) {
//                    return false;
//                }
//            } else return false;
//        }
//
//        for (Object e : collection) {
//            E elements = (E) e;
//            add(elements);
//        }
//        return true;
//    }

    @Override
    public boolean retainAll(@NotNull Collection<?> collection) {
        int lastSize = size();
        for (Object e : tree) {
            E elements = (E) e;
            if (!collection.contains(elements)) {
                remove(elements);
            }
        }
        if (lastSize != size()) {
            return true;
        }
        return false;
    }


//        for (Object e : collection) {
//            E elements = (E) e;
//            if (inRange(elements)) {
//                if (contains(elements)) {
//                    return false;
//                }
//            } else return false;
//        }
//        for (Object e : collection) {
//            E elements = (E) e;
//            if (inRange(elements)) {
//                if (!contains(elements)) {
//                    remove(elements);
//                }
//            } else return false;
//        }
//        return true;


    @Override
    public boolean removeAll(@NotNull Collection<?> collection) {
        int lastSize = size();
        for (Object e : collection) {
            E elements = (E) e;
            if (contains(elements)) {
                remove(elements);
            }
        }
        if (lastSize != size()) {
            return true;
        }
        return false;
    }


//        for (Object e : collection) {
//            E elements = (E) e;
//            if (inRange(elements)) {
//                if (contains(elements)) {
//                    remove(elements);
//                }
//            } else return false;
//        }
//        return true;
//    }

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

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            while (iterator.hasNext()) {
                E e = iterator.next();
                if (inRange(e))
                    return e;
            }
            return null;
        }
    }


}
