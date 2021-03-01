import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 *
 * Percolation. Given a composite systems comprised of randomly distributed insulating and metallic materials:
 * what fraction of the materials need to be metallic so that the composite system is an electrical conductor?
 * Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain
 * through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known
 * as percolation to model such situations.

The model. We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked.
* A full site is an open site that can be connected to an open site in the top row via a chain of neighboring
* (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row.
* In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site
* on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials,
* so that a system that percolates has a metallic path from top to bottom, with full sites conducting.
* For the porous substance example, the open sites correspond to empty space through which water might flow,
* so that a system that percolates lets water fill open sites, flowing from top to bottom.)
 **************************************************************************** */
public class Percolation {

    private boolean[][] grid;
    // używam WeightedQuickUnionUF
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int counterOfOpen = 0;
    private final int size;

    // creates n-by-n grid, with all sites initially blocked
    // tworze siatke z zablokowanymi polami
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
    //otwieram pole
    public void open(int row, int col) {
        row--;
        col--;
        gridPlace(row,col);

        if (!grid[row][col]) {
            grid[row][col] = true;
            counterOfOpen++;
        }
        //wirtualna góra - są do niej podłączone wszystkie elementy z pierwszego wiersza.
        if (row == 0) {
            weightedQuickUnionUF.union(0, position(row, col));

        }
        //wirtualny dół - są do niego podłączone wszystkie elementy z ostatniego wiersza.
        if (row == size - 1) {
            weightedQuickUnionUF.union(size * size + 1, position(row, col));
        }
        //aby sprawdzić czy następuje przesiąkanie (percolation) wystarczy sprawdzić czy te dwa pola mają wspólny korzeń.

        connectToNeighbors(row, col);
    }

    // is the site (row, col) open?
    //czy pole jest otwarte
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        gridPlace(row,col);

        return (grid[row][col]);
    }

    // is the site (row, col) full?
    // pole ma łączność z górą
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
    // czy góra i doł mają łączność?
    public boolean percolates() {
        return (weightedQuickUnionUF.find(0) == weightedQuickUnionUF.find(size * size + 1));
    }
    //sprawdzam czy pole istnieje
    private void gridPlace(int row, int col) {
        if (row > size || col > size || row < 0 || col <
                0) {
            throw new IllegalArgumentException("place does not exists");
        }
    }
    //sprawdzam czy nowo otwarte pole, a tym samym drzewo, można dołączyć do innego drzewa
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
    //zwracam indeks w tablicy
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

