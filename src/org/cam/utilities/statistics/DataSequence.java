package org.cam.utilities.statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Container for a single sequence of Numbers.  Methods implement common statistical operations.
 *
 * @author Philip Johnson
 * @author Cam Moore
 * @author Joy M. Agustin
 * @version $Id: DataSequence.java,v 1.1.1.1 2005/10/20 23:56:48 johnson Exp $
 */
public class DataSequence implements Iterable<Number> {
  /** List of numerical values as Number objects */
  private List<Number> sequence;
  /** Holds the sum of all the elements in sequence */
  private double sum;
  /** Holds the minimum value in sequence */ 
  private double min;
  /** Holds the maximum value in sequence */ 
  private double max;
  /** Holds the sum of the squares of all the elements in sequence */
  private double sumOfSquares;

  /**
   * Constructs a new, empty <code>DataSequence</code>.
   */
  public DataSequence() {
    this.sequence = new ArrayList<Number>();
    this.sum = 0;
    this.min = Double.MAX_VALUE;
    this.max = Double.MIN_VALUE;
    this.sumOfSquares = 0;
  }

  /**
   * Adds a Number to this DataSequence.
   * 
   * @param number the number to add to this DataSequence.
   */
  public void add(Number number) {
    // add number to the sequence
    this.sequence.add(number);
    // add number to the running total
    this.sum += number.doubleValue();
    // add number squared to the running total
    this.sumOfSquares += Math.pow(number.doubleValue(), 2);
    // check against the minimum value
    if (number.doubleValue() < this.min) {
      this.min = number.doubleValue(); 
    }
    // check against the maximum value
    if (number.doubleValue() > this.max) {
      this.max = number.doubleValue(); 
    }
  }

  /**
   * Returns the number of elements in this DataSequence.
   *
   * @return the number of elements in this DataSequence.
   */
  public int getSize() {
    return this.sequence.size();
  }

  /**
   * Returns the sum of all elements in this DataSequence.
   *
   * @return the sum, or 0 if this DataSequence is empty.
   */
  public double getSum() {
    this.checkIllegalState();
    return this.sum;
  }

  /**
   * Returns the mean (average) of this DataSequence.
   *
   * @return the mean, or 0 if this DataSequence is empty.
   */
  public double getMean() {
    this.checkIllegalState();
    return this.sum / this.sequence.size();
  }

  /**
   * Returns the minimum value in this DataSequence.
   *
   * @return the min, or Double.MAX_VALUE if this DataSequence is empty.
   */
  public double getMin() {
    this.checkIllegalState();
    return this.min;
  }

  /**
   * Returns the largest value in this DataSequence.
   *
   * @return the max, or Double.MIN_VALUE if this DataSequence is empty.
   */
  public double getMax() {
    this.checkIllegalState();
    return this.max;
  }

  /**
   * Returns the sum of the squares of the elements in this DataSequence.
   *
   * @return the sum of squres, or 0 if this DataSequence is empty.
   */
  public double getSumOfSquares() {
    this.checkIllegalState();
    return this.sumOfSquares;
  }

  /**
   * Returns the standard deviation of this DataSequence.
   *
   * @return the standard deviation, or 0 if this DataSequence is empty.
   */
  public double getStandardDeviation() {
    this.checkIllegalState();
    int n = this.getSize();
    // xbar = (sum of x) / n
    double xbar = this.getMean();
    double sumOfXMinusXbarSquared = 0;

    for (Number number : this) {
      // x - xbar
      double xMinusXbar = number.doubleValue() - xbar;

      // (x - xbar) ^ 2
      double xMinusXbarSquared = Math.pow(xMinusXbar, 2);

      // sum of ((x - xbar) ^ 2)
      sumOfXMinusXbarSquared += xMinusXbarSquared;
    }
    return Math.sqrt((sumOfXMinusXbarSquared / (n - 1)));
  }

  /**
   * Returns an iterator over the Number instances in this DataSequence.
   * 
   * @return an iterator.
   */
  public Iterator<Number> iterator() {
    return this.sequence.iterator();
  }

  /**
   * Returns the sequence in this DataSequence as a String.
   *
   * @return this DataSequence as a String.
   */
  public String toString() {
    return this.sequence.toString();
  }
  
  /**
   * Checks for a bad DataSequence.
   * @throws IllegalStateException If the datasequence is empty.
   */
  private void checkIllegalState() throws IllegalStateException {
    if (this.getSize() == 0) {
      throw new IllegalStateException("Empty DataSequence");
    }
  }    
}
