/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
/* Successor with delete.
 * Given a set of N integers S={0,1,...,N-1} and a sequence of requests of the following form:
 *  - Remove x from S
 *  - Find the successor of x: the smallest y in S such that y>=x.
 * design a data type so that all operations (except construction) should take logarithmic time or better.
 */
public class Q3 {

    /*
    private int[] numbers;
    private int[] succesor;

    public Q3(int size) {
        numbers = new int[size];
        succesor = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i;
            succesor[i] = i + 1;
        }
        succesor[size - 1] = size - 1;
    }

    public void delete(int remove) {
        System.out.println("number to remove: " + numbers[remove] + " succesor: " + succesor[remove]);
        numbers[remove] = -1;
        succesor[remove - 1] = succesor[remove];
        System.out.println(
                "new succesor of: " + numbers[remove - 1] + " is: " + +succesor[remove - 1]);
        succesor[remove] = -1;
    }

    public int getSuccesor(int value) {
        return succesor[value];
    }
*/
    public static void main(String[] args) {
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(10);
        uf.remove(0);
        uf.remove(6);
        uf.remove(2);
        uf.remove(3);
        uf.remove(9);
        System.out.println(uf.succesor(3));



    }

}
