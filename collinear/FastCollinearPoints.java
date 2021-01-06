/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments;

    /*
    A faster, sorting-based solution. Remarkably,
    it is possible to solve the problem much faster than the brute-force solution described above.
    Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.

    Think of p as the origin.

    For each other point q, determine the slope it makes with p.

    Sort the points according to the slopes they makes with p.

    Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
    If so, these points, together with p, are collinear.

    Applying this method for each of the n points in turn yields an efficient algorithm to the problem.
    The algorithm solves the problem because points that have equal slopes with respect to p are collinear,
    and sorting brings such points together. The algorithm is fast because the bottleneck operation is sorting.
     */
    public FastCollinearPoints(
            Point[] points) { // finds all line segments containing 4 or more points
        checkElements(points);
        lineSegments = new ArrayList<>();
        int size = points.length;
        Point[] copy = new Point[size];
        for (int i = 0; i < size; i++) {
            copy[i] = points[i];
        }
        Arrays.sort(copy);

        for (int i = 0; i < size - 3; i++) {
            //sort array for each iteration
            Arrays.sort(copy);
            //For each other point q, determine the slope it makes with p.
            //each time, p is the other element, so the elements after him is also other
            Comparator<Point> comparator = copy[i].slopeOrder();

            //Sort the points according to the slopes they makes with p using comparator for p.
            Arrays.sort(copy, comparator);

            //set p and two next elements
            int parent = 0;
            int first = 1;
            for (int second = 2; second < size; second++) {
                //check if position of second is still in array
                //and if all three elements is slope.
                //if yes, then move second to next element
                //(increase number of adjacent points)
                while (second < size && copy[parent].slopeTo(copy[first]) == copy[first]
                        .slopeTo(copy[second])) {
                    second++;
                }
                //Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
                //If so, these points, together with p, are collinear.
                if (second - first > 2
                        && copy[parent].compareTo(copy[first]) < 0) { //parent must be smaller
                    //position of parent and last adjacent
                    lineSegments.add(new LineSegment(copy[parent], copy[second - 1]));
                }
                first = second; // search for the next aligned points
            }

        }


    }

    // Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null,
    // if any point in the array is null, or if the argument to the constructor contains a repeated point.
    private void checkElements(Point[] points) {
        //nulls
        if (points == null) throw new IllegalArgumentException("Point array is null");
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("Points element is null");
            }
        }
        //duplicates
        Point[] temp = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            temp[i] = points[i];
        }
        Arrays.sort(temp);
        for (int i = 0; i < temp.length - 1; i++) {
            if (temp[i].equals(temp[i + 1]))
                throw new IllegalArgumentException("Points element is duplicated");
        }

    }

    public int numberOfSegments() { // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        // the line segments
        // The method segments() should include each line segment containing 4 points exactly once.
        // If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p
        // (but not both) and you should not include subsegments such as p→r or q→r.
        // For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
        LineSegment[] ls = new LineSegment[numberOfSegments()];
        for (int i = 0; i < numberOfSegments(); i++) {
            ls[i] = lineSegments.get(i);
        }
        return ls;
    }

}

