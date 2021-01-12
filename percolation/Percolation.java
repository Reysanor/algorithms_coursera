import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
public class Percolation {

    private boolean[][] grid;
    // używam WeightedQuickUnionUF
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int counterOfOpen = 0;
    private final int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        size = n;
        grid = new boolean[n][n];

        if (size < 1) {
            throw new IllegalArgumentException("place does not exists");
        }

        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row--;
        col--;
        gridPlace(row,col);

        if (!grid[row][col]) {
            grid[row][col] = true;
            counterOfOpen++;
        }

        if (row == 0) {
            weightedQuickUnionUF.union(0, position(row, col));

        }
        if (row == size - 1) {
            weightedQuickUnionUF.union(size * size + 1, position(row, col));
        }
        connectToNeighbors(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        gridPlace(row,col);

        return (grid[row][col]);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row--;
        col--;
        gridPlace(row,col);
        return (weightedQuickUnionUF.find(position(row, col)) == weightedQuickUnionUF.find(0));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return counterOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return (weightedQuickUnionUF.find(0) == weightedQuickUnionUF.find(size * size + 1));
    }

    private void gridPlace(int row, int col) {
        if (row > size || col > size || row < 0 || col <
                0) {
            throw new IllegalArgumentException("place does not exists");
        }
    }

    private void connectToNeighbors(int row, int col) {

        // lewy
        if (row > 0 && isOpen(row - 1 + 1, col + 1)) {
            weightedQuickUnionUF.union(position(row, col), position(row - 1, col));
        }
        // prawy
        if (row < size - 1 && isOpen(row + 1 + 1, col + 1)) {
            weightedQuickUnionUF.union(position(row, col), position(row + 1, col));

        }
        // górny
        if (col > 0 && isOpen(row + 1, col - 1 + 1)) {
            weightedQuickUnionUF.union(position(row, col), position(row, col - 1));

        }
        // dolny
        if (col < size - 1 && isOpen(row + 1, col + 1 + 1)) {
            weightedQuickUnionUF.union(position(row, col), position(row, col + 1));

        }
    }

    private int position(int row, int col) {
        return size * (row) + col + 1;
    }

    // test client (optional)
    public static void main(String[] args) {

        WeightedQuickUnionPathCompressionUF weightedQuickUnionPathCompressionUF
                = new WeightedQuickUnionPathCompressionUF(10);
        weightedQuickUnionPathCompressionUF.union(7, 0);
        weightedQuickUnionPathCompressionUF.union(7, 8);
        weightedQuickUnionPathCompressionUF.union(7, 3);
        weightedQuickUnionPathCompressionUF.union(7, 4);
        weightedQuickUnionPathCompressionUF.union(7, 5);

        weightedQuickUnionPathCompressionUF.union(1, 2);
        weightedQuickUnionPathCompressionUF.union(1, 6);
        weightedQuickUnionPathCompressionUF.union(1, 9);

        weightedQuickUnionPathCompressionUF.print();
        //System.out.println(quickUnionUF.find(6));
        /*

        Percolation percolation = new Percolation(10);

        percolation.open(0, 0);
        percolation.isOpen(11, 5);
        */

        /*


        percolation.open(1, 0);

        percolation.open(2, 2);
        percolation.open(3, 2);
        percolation.open(1, 2);
        percolation.open(2, 3);
        percolation.open(2, 1);

        System.out.println("2,2 " + percolation.isFull(2,2));
        System.out.println("4,0 " + percolation.isFull(4,0));
        System.out.println("is percolates: " + percolation.percolates());
        // łącze
        percolation.open(0, 2);

        System.out.println("2,2 " + percolation.isFull(2,2));
        System.out.println("4,0 " + percolation.isFull(4,0));
        System.out.println("is percolates: " + percolation.percolates());

        percolation.open(4,2);
        System.out.println("is percolates: " + percolation.percolates());
*/

        /*
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(2, 1);

        percolation.open(3, 3);
        percolation.open(4, 3);
        percolation.open(2, 3);
        percolation.open(3, 4);
        percolation.open(3, 2);

        System.out.println("2,2 " + percolation.isFull(3,3));
        System.out.println("4,0 " + percolation.isFull(5,1));
        System.out.println("is percolates: " + percolation.percolates());
        // łącze
        percolation.open(1, 3);

        System.out.println("2,2 " + percolation.isFull(3,3));
        System.out.println("4,0 " + percolation.isFull(5,1));
        System.out.println("is percolates: " + percolation.percolates());

        percolation.open(5,3);
        System.out.println("is percolates: " + percolation.percolates());


        // percolation.open(3,3);
        // percolation.open(4,4);
        // percolation.open(5,5);
        int temp = 1;
        for (int i = 0; i < percolation.size; i++) {
            for (int j = 0; j < percolation.size; j++) {
                System.out.print(percolation.weightedQuickUnionUF.find(temp) + " ");
                temp++;

            }
            System.out.println();
        }
*/
    }
}

