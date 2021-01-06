/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n = 0;
    private Node first, last;
    // private Item[] items;
    // private Node[] nodes;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {


    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;

    }

    // add the item
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) first = last;
        else oldlast.next = last;

        n++;
    }
    // remove and return a random item

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
            // last = null;
        }

        Node actual = first;
        // System.out.println("number of elements: " + n);
        // System.out.println("first item " + first.item);

        Item temp;
        int position = StdRandom.uniform(1, n + 1);
        for (int i = 0; i < position - 1; i++) {
            actual = actual.next;
        }
        // System.out.println("to remove :" + actual.item);
        temp = actual.item;

        if (position == 1) {
            // System.out.print("no previous, ");
            first = actual.next;
            //  first.prev = null;
            if (n == 1) {
                // System.out.println("to remove: " + temp + " now " + "first and last is empty");
                first = null;
                last = null;
            }
            else {
                // System.out.println("to remove: " + temp + " now " + "first is " + first.item);
            }

        }

        else if (position == n) {
            // temp = last.item;
            last = last.prev;
            last.next = null;
            // System.out.println("to remove: " + temp + " now last is " + last.item);

        }
        else {
            Node pre = actual.prev;
            Node nex = actual.next;
            if (nex != null) {
                pre.next = nex;
            }
            if (pre != null) {
                nex.prev = pre;
            }

            // System.out.println("to remove: " + temp + " now " + pre.item + " point to: "+ pre.next.item + " and mirror: " + nex.item + " is after "+ nex.prev.item);
            actual.item = null;
            actual.prev = null;
            actual.next = null;
        }
        // System.out.println("------");
        n--;
        return temp;
    }


    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
            // last = null;
        }

        Node previous = first;

        int position = StdRandom.uniform(1, n + 1);
        for (int i = 0; i < position - 2; i++) {
            previous = previous.next;

        }
        if (position != 1) previous = previous.next;
        return previous.item;


    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private final Item[] randoms = randomizer();
        private int lenght;

        public boolean hasNext() {
            return n > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return randoms[--n];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private Item[] randomizer() {
            // System.out.println(lenght + " "+ n);
            lenght = n;
            // System.out.println(lenght + " "+ n);
            // create array of nodes
            // random node one to another
            // iterate
            Node temp = first;
            Item[] items = (Item[]) new Object[lenght];
            for (int i = 0; i < lenght; i++) {
                items[i] = temp.item;
                temp = temp.next;
            }

            for (int i = items.length - 1; i > 0; i--) {
                int index = StdRandom.uniform(i);
                // swap
                Item tmp = items[index];
                items[index] = items[i];
                items[i] = tmp;
            }

            return items;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(47);
        rq.enqueue(1);
        rq.enqueue(45);
        rq.enqueue(37);
        rq.enqueue(14);
        rq.enqueue(18);
        System.out.println("-----");
        rq.iterator(); //    ==> [1, 18, 47, 37, 45, 14]
        System.out.println("-----");

        rq.enqueue(40);
        rq.enqueue(39);
        System.out.println("-----");
        rq.iterator(); //  ==> [40, 39]
        System.out.println("-----");


    }


}