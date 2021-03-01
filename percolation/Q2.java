/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
/* Union-find with specific canonical element.
 * Add a method find() to the union-find data type so that find(i)
 * returns the largest element in the connected component containing i.
 * The operations, union(), connected(), and find() should all take logarithmic time or better.
 *
 * For example, if one of the connected components is {1,2,6,9},
 * then the find() method should return 9 for each of the four elements in the connected components.
 */
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
