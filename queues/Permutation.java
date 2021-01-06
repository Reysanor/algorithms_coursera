/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> strings = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            //  String temp = StdIn.readString();
            // System.out.println(temp);
            strings.enqueue(StdIn.readString());
        }
        while (k > 0) {
            System.out.println(strings.dequeue());
            k--;
        }
    }
}
