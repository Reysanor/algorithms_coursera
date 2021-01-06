/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class QuickUnionUF {

    private int[] parent;
    private int[] sz;


    public QuickUnionUF(int N) {
        parent = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            sz[i]=1;
        }

    }

    //jeżeli podana wartość jest inna niż jej identyfikator to nie jest korzeniem
    //więc zmieniam szukaną na rodzica w kółko aż będzie taka sama i zwracam
    //to jest find(korzeń)
    private int root(int i) {
        while (i != parent[i]) {
            //kompresja ścieżek
            parent[i] = parent[parent[i]];
            i = parent[i];

        }
        return i;
    }

    //czy korzenie są takie same
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    //łączenie drzew
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        //wagi
        if (i == j) return;
        if (sz[i] < sz[j]) {
            parent[i] = j;
            sz[j] += sz[i];
        }
        else {
            parent[j] = i;
            sz[i] += sz[j];
        }

        //korzeń drzewa z elementem p staję się gałęzią korzenia drzewa q
       // id[i] = j;
    }
    public void print(){
        for(int i = 0; i< parent.length; i++){
            System.out.println("korzeń: "+root(i) + " wartość: "+ i + " wielkość zbioru: " + sz[i]);
        }
    }

    public int find(int i){
        //korzeń przeszukiwanego drzewa
        int kernel = root(i);
        int highest = i;
        int counter =0;
        while (sz[i]>counter) {
            //kompresja ścieżek
            parent[i] = parent[parent[i]];
            i = parent[i];

        }
        return i;
    }

    public int delete(int size){
        //constructor




        return 0;
    }

}
