/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n = 0;
    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node prev;

    }


    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        }
        else {
            oldFirst.prev = first;
            first.next = oldFirst;

        }
        n++;
    }


    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
            // last = null;
        }
        Item item = first.item;
        first = first.next;

        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            n--;
        }
        // System.out.println(last.item);

        Item item = last.item;
        // last = last.prev;
        // System.out.println(last.item);
        if (last.prev != null) {
            last = last.prev;
            last.next = null;
        }
        else {
            last = null;
            first = null;
        }
        return item;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        System.out.println(deque.iterator().hasNext());
        deque.removeLast(); //    ==> 1
        System.out.println(deque.iterator().hasNext());
        deque.addLast(3);
        System.out.println(deque.iterator().hasNext());
        deque.removeLast(); //    ==> 3
        System.out.println(deque.iterator().hasNext());


    }

}