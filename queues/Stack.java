/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Stack<Item> {

    private Item[] elements;
    private int N = 0;

    public Stack() {
        elements = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (N == elements.length) resize(2 * elements.length);
        elements[N++] = item;
    }

    public Item pop() {
        Item item = elements[--N];
        elements[N] = null;
        if (N > 0 && N == elements.length / 4) resize(elements.length / 2);
        return item;
    }

    public Item peek() {
        return elements[N - 1];
    }


    private void resize(int capacity) {
        // System.out.println("resizing: "+ N);
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = elements[i];
        }
        elements = copy;
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
}
