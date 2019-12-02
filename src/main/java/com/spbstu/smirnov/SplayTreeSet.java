package main.java.com.spbstu.smirnov;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SplayTreeSet<E extends Comparable<E>> extends AbstractSet<E> implements SortedSet<E> {

    class Node<E> {

        Node<E> left;
        Node<E> right;
        Node<E> parent;
        E element;

        Node(Node<E> left, Node<E> right, Node<E> parent, E element) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.element = element;
        }
    }

    Node<E> root = null;
    private int size = 0;

    public void insert(E element) {
        if (element == null) {
            return;
        }

        Node<E> addedElement = root;
        Node<E> parent = null;

        while (addedElement != null) {
            parent = addedElement;
            if (element.compareTo(parent.element) > 0) {
                addedElement = addedElement.right;
            } else {
                addedElement = addedElement.left;
            }
        }
        addedElement = new Node<>(null, null, parent, element);

        if (parent == null) {
            root = addedElement;
        } else if (element.compareTo(parent.element) > 0) {
            parent.right = addedElement;
        } else {
            parent.left = addedElement;
        }
        splay(addedElement);
        size++;
    }

    private void makeRightChildParent(Node<E> child, Node<E> parent) {
        if ((child == null) || (parent == null) || (parent.right != child) || (child.parent != parent)) {
            throw new IllegalArgumentException();
        }
        if (parent.parent != null) {
            if (parent == parent.parent.left) {
                parent.parent.left = child;
            } else {
                parent.parent.right = child;
            }
        }
        if (child.left != null) {
            child.left.parent = parent;
        }
        child.parent = parent.parent;
        parent.parent = child;
        parent.right = child.left;
        child.left = parent;
    }

    private void makeLeftChildParent(Node<E> child, Node<E> parent) {
        if ((child == null) || (parent == null) || (parent.left != child) || (child.parent != parent)) {
            throw new IllegalArgumentException();
        }
        if (parent.parent != null) {
            if (parent == parent.parent.left) {
                parent.parent.left = child;
            } else {
                parent.parent.right = child;
            }
        }
        if (child.right != null) {
            child.right.parent = parent;
        }
        child.parent = parent.parent;
        parent.parent = child;
        parent.left = child.right;
        child.right = parent;
    }

    private void splay(Node<E> splayNode) {
        while (splayNode.parent != null) {

            Node<E> parent = splayNode.parent;
            Node<E> grandParent = parent.parent;

            if (grandParent == null) {
                if (splayNode == parent.left) {
                    makeLeftChildParent(splayNode, parent);
                } else {
                    makeRightChildParent(splayNode, parent);
                }
            } else {
                if (splayNode == parent.left) {
                    if (parent == grandParent.left) {
                        makeLeftChildParent(parent, grandParent);
                        makeLeftChildParent(splayNode, parent);
                    } else {
                        makeLeftChildParent(splayNode, splayNode.parent);
                        makeRightChildParent(splayNode, splayNode.parent);
                    }
                } else {
                    if (parent == grandParent.left) {
                        makeRightChildParent(splayNode, splayNode.parent);
                        makeLeftChildParent(splayNode, splayNode.parent);
                    } else {
                        makeRightChildParent(parent, grandParent);
                        makeRightChildParent(splayNode, parent);
                    }
                }
            }
        }
        root = splayNode;
    }

    public boolean find(E searchElement) {
        return search(searchElement) != null;
    }

    private Node<E> search(E searchElement) {

        Node<E> current = root;
        Node<E> parent = null;

        if (root == null || searchElement == null) {
//            throw new IllegalArgumentException();
            return null;
        }
        while (current.element.compareTo(searchElement) != 0) {

            if (searchElement.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
                if (current == null) {
                    return null;
                }
            }
            if (searchElement.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
                if (current == null) {
                    return null;
                }
            }
        }
        if (searchElement.compareTo(current.element) == 0) {
            splay(current);
            return current;
        }
        if (parent != null) {
            splay(parent);
        }
        return null;
    }

    private void remove(E element) {

        Node node = search(element);

        if (element == null) {
            return;
        }

        if((node.left != null) && (node.right !=null)) {
            Node min = node.left;

            while(min.right != null) {
                min = min.right;
            }
            min.right = node.right;
            node.right.parent = min;
            node.left.parent = null;
            root = node.left;
        } else if (node.right != null) {
            node.right.parent = null;
            root = node.right;
        } else if( node.left !=null) {
            node.left.parent = null;
            root = node.left;
        } else {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
        node = null;
        size--;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }


    @Override
    public boolean contains(Object o) {
        E ele = (E) o;
        Node<E> closest = search(ele);
        return closest != null && ele.compareTo(closest.element) == 0;
    }


    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new SplayTreeSetIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Nullable
    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<E> subSet(E e, E e1) {
        return new SubSplayTreeSet<E>(this, e, e1);
    }

    @NotNull
    @Override
    public SortedSet<E> headSet(E e) {
        return new SubSplayTreeSet<E>(this, null, e);
    }

    @NotNull
    @Override
    public SortedSet<E> tailSet(E e) {
        return new SubSplayTreeSet<E>(this, e, null);
    }

    @Override
    public E first() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        Node<E> current = root;

        while (current.left != null) {
            current = current.left;
        }
        return current.element;
    }

    @Override
    public E last() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        Node<E> current = root;

        while (current.right != null) {
            current = current.right;
        }
        return current.element;
    }

    public class SplayTreeSetIterator implements Iterator<E> {

        private Node<E> next;
        private Deque<Node<E>> stack;

        private SplayTreeSetIterator() {
            stack = new ArrayDeque<>();
            next = root;
            while (next != null) {
                stack.offer(next);
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Node<E> node = stack.pop();
            next = node;

            if (node.right != null) {
                Node<E> right = node.right;
                while (right != null) {
                    if (!stack.contains(right)) {
                        stack.push(right);
                    }
                    right = right.left;
                }
            }
            return next.element;
        }

        @Override
        public void remove() {
            SplayTreeSet.this.remove(next.element);
        }
    }
}




