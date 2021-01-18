/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
/*
Question 1

Social network connectivity. Given a social network containing n members and a log file
containing m timestamps at which times pairs of members formed friendships, design an algorithm to
determine the earliest time at which all members are connected
(i.e., every member is a friend of a friend of a friend ... of a friend).
Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
The running time of your algorithm should be mlogn or better and use extra space proportional to n.
 */

import edu.princeton.cs.algs4.In;

public class Q1 {

    public static void main(String[] args) {
        //From file
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


    }
}
