/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class WeightedQuickUnionPathCompressionUF {

    private int[] parent; // parent[i] = parent of i
    private int[] size; // liczba elementów mających korzeń o wartości size[i]
    private int count; // liczba elementów w sumie

    //do Q1
    private int numberOfConnectedElements = 1; //liczba połączonych elementów
    //do Q2
    private int[] largest; //największy element w danym drzewie

    //inicjalizacja
    public WeightedQuickUnionPathCompressionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        largest = new int[n];
        for (int i = 0; i < n; i++) {
            //domyślnie element jest korzeniem siebie
            parent[i] = i;
            //domyślnie rozmiar każdego drzewa to 1
            size[i] = 1;
            //Q2, domyślna najwyższa wartość
            largest[i] = i;
        }

    }

    //łączenie elementów a tym samym i ich drzew
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        //wagi - porównuje się rozmiary drzew i podłącza mniejsze drzewo do większego
        //co zmniejsza wysokość i przyspiesza wyszukiwanie

        //takie same elementy - nic nie robie
        if (i == j) return;

        //Q1 - w przypadku łączenia drzew zwiększam liczbe połączonych
        numberOfConnectedElements++;

        //Q2 - aktualne wartości największych
        int largestI = largest[i];
        int largestJ = largest[j];
        //różne wysokości - podłączam mniejsze do większeo i zwiększam rozmiar większego
        if (size[i] < size[j]) {
            parent[i] = j;
            size[j] += size[i];
            //Q2 - porównuje najwyższe
            if (largestI > largestJ) {
                largest[j] = largest[i];
            }
        }
        else {
            parent[j] = i;
            size[i] += size[j];
            //Q2 - porównuje najwyższe
            if (largestJ > largestI) {
                largest[i] = largest[j];
            }
        }

    }


    //czy korzenie są wskazanych elementów takie same?
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }


    // zwraca liczbę elementów
    public int count() {
        return count;
    }


    //zwracam korzeń wskazanego elementu

    //to jest find(korzeń)
    private int root(int i) {
        // jeżeli element szukany i jest różny od swojego korzenia
        while (i != parent[i]) {
            //kompresja ścieżek - ustawiam korzeń sprawdzanego elementu nie będącego szukaną wartością
            //na jego korzeń dziadka
            //(1.1 Union Find str 38)
            parent[i] = parent[parent[i]];
            //zwracam do szukania korzeń dziadka
            i = parent[i];
        }
        return i;
    }

    //pomocnicze metody

    public void print() {
        for (int i = 0; i < parent.length; i++) {
            System.out.println(
                    "korzeń: " + root(i) + " wartość: " + i + " wielkość zbioru: " + size[i]);
        }
    }

    public int delete(int size) {
        //constructor
        return 0;
    }

    //do Q1
    public boolean allConnected() {
        return numberOfConnectedElements == count;
    }

    //do Q2
    public int find(int n){
        //największa wartość na korzenia wybranego elementu
        return largest[root(n)];
    }
    public static void main(String[] args) {


        /////////////////////////////////////////////
        /*
        //Q1, From file
        In in = new In(args[0]);      // input file
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(10);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            String date = in.readString();
            String time = in.readString();
            uf.union(i, j);
            uf.print();
            if (uf.allConnected()) {
                System.out.println(date + " " + time);
                break;
            }
        }
*/
        /*
        //Standard input
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q))
            {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }

         */
    }

}
