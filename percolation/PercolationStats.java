/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_VALUE = 1.96;

    private final int size;
    private final int trials;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.size = n;
        this.trials = trials;
        if (size < 1 || trials < 1) {
            throw new IllegalArgumentException("parameters must be higher than 0");
        }
        results = new double[trials];

        randomizedPlace();
    }

    private void randomizedPlace() {
        Percolation percolation;
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(size);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, size + 1), StdRandom.uniform(1, size + 1));
            }
            results[i] = (percolation.numberOfOpenSites() * 1.0) / (size * size);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

        return mean() - ((CONFIDENCE_VALUE * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_VALUE * stddev()) / Math.sqrt(trials));
    }

    private void stats() {
        System.out.println("mean                    = " + mean());
        System.out.println("stddev                  = " + stddev());
        System.out.println(
                "95% confidence interval = [" + confidenceLo() + ", " + confidenceHi() + "]");
    }

    // test client (see below)
    public static void main(String[] args) {
        int sizeArg = Integer.parseInt(args[0]);
        int trialsArg = Integer.parseInt(args[1]);
        // Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(sizeArg, trialsArg);
        percolationStats.stats();
        // System.out.println("Elapsed time in miliseconds = " + stopwatch.elapsedTime());

    }

}