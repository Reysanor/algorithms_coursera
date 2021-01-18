/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

public class Q2 {

    public static void main(String[] args) {

        In in = new In(args[0]);      // input file
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(10);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            uf.union(i, j);
            //uf.print();

        }

        System.out.println(uf.find(1));
        System.out.println(uf.find(2));
        System.out.println(uf.find(6));
        System.out.println(uf.find(9));

        System.out.println(uf.find(0));
        System.out.println(uf.find(3));
        System.out.println(uf.find(4));
        System.out.println(uf.find(5));

        System.out.println(uf.find(7));
        System.out.println(uf.find(8));

    }
}
