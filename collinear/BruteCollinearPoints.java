/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private ArrayList<ArrayList<Point>> pointsInOneLine;
    //pierwszy i ostatni w danej linii
    //private ArrayList<LineSegment> lineSegments;


    public BruteCollinearPoints(Point[] points) {

        checkElements(points);

        pointsInOneLine = new ArrayList<>();
       // lineSegments = new ArrayList<>();
        int size = points.length;
        //copy element
        Point[] copy = new Point[size];
        for (int i = 0; i < size; i++) {
            copy[i] = points[i];
        }
        Arrays.sort(copy);


        for (int i = 0; i < size; i++) {
            // current positions
            double slopeX, slopeY, slopeZ;

            for (int j = i + 1; j < size; j++) {
                slopeX = copy[i].slopeTo(copy[j]);
                for (int k = j + 1; k < size; k++) {
                    slopeY = copy[i].slopeTo(copy[k]);
                    // porównuje pierwszy, drugi i trzeci
                    if (slopeX == slopeY) {
                        for (int m = k + 1; m < size; m++) {
                            // porównuje pierwszy i czwarty
                            slopeZ = copy[i].slopeTo(copy[m]);
                            if (slopeX == slopeZ) {

                                ArrayList<Point> newPoints = new ArrayList<>();
                                newPoints.add(copy[i]);
                                newPoints.add(copy[j]);
                                newPoints.add(copy[k]);
                                newPoints.add(copy[m]);
                                combine(newPoints);

                            }
                        }
                    }
                }

            }
        }

    }

    private void combine(ArrayList<Point> newPoints) {

        //copy of actual pointsInOneLine
        ArrayList<ArrayList<Point>> tempSegments = new ArrayList<>();
        for (ArrayList<Point> piol : pointsInOneLine) {
            tempSegments.add(piol);
        }

        //check if exists mutual slope
        if (!pointsInOneLine.isEmpty()) {
            double slopeNew = newPoints.get(0).slopeTo(newPoints.get(newPoints.size() - 1));

            //iteration over actual object
            //actual elements
            boolean exists = false;

            for (ArrayList<Point> segment : tempSegments) {
                exists = false;
                double slopeCurrent = segment.get(0).slopeTo(segment.get(segment.size() - 1));
                //same line
                if (slopeNew == slopeCurrent) {
                    exists = true;
                    //check if list contain element
                    for (Point point : newPoints) {
                        if (!segment.contains(point)) {
                            segment.add(point);
                        }
                    }
                }
            }

            if (!exists) {
                pointsInOneLine.add(newPoints);
            }

        }
        else {
            pointsInOneLine.add(newPoints);
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

    public int numberOfSegments() {
        // the number of line segments
        return pointsInOneLine.size();

    }

    public LineSegment[] segments() {
        // the line segments
        // The method segments() should include each line segment containing 4 points exactly once.
        // If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p
        // (but not both) and you should not include subsegments such as p→r or q→r.
        // For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
        LineSegment[] ls = new LineSegment[numberOfSegments()];
        int counter = 0;
        for (ArrayList<Point> p : pointsInOneLine) {
            ls[counter] = new LineSegment(p.get(0), p.get(p.size() - 1));
            counter++;
        }
        return ls;
    }


}
