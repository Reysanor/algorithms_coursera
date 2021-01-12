/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class WeightedQuickUnionPathCompressionUF {

    private int[] parent; // parent[i] = parent of i
    private int[] size; // liczba elementów mających korzeń o wartości size[i]
    private int count; // liczba elementów w sumie

    //inicjalizacja
    public WeightedQuickUnionPathCompressionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            //domyślnie element jest korzeniem siebie
            parent[i] = i;
            //domyślnie rozmiar każdego drzewa to 1
            size[i] = 1;
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
        //różne wysokości - podłączam mniejsze do większeo i zwiększam rozmiar większego
        if (size[i] < size[j]) {
            parent[i] = j;
            size[j] += size[i];
        }
        else {
            parent[j] = i;
            size[i] += size[j];
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

}
