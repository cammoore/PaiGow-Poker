package org.cam.utilities.statistics;

import java.util.Iterator;

/**
 * Describes regressional analysis on a pair of data sequences.
 * <p>
 * The true regression line is
 * <pre>
 *   y = beta + alpha * x
 * </pre>
 * where <code>alpha</code> and <code>beta</code> are the population regression coefficients. This
 * class uses the estimated regression line
 * <pre>
 *   y = b + a * x
 * </pre>
 * where <code>a</code> and <code>b</code> are the estimated regression coefficients.
 * <p>
 * Values for the xDataSequence, yDataSequence, and sumOfXY are set by the children
 * of this class in the <code>add</code> method. Values for <code>a</code> and <code>b</code> are
 * calculated by the children of this class in the <code>getA</code> and <code>getB</code> methods.
 *
 * @author Cam Moore
 * @author Joy M. Agustin
 * @version $Id: Regression.java,v 1.1.1.1 2005/10/20 23:56:48 johnson Exp $
 */
public abstract class Regression {
  /** x's data sequence */
  protected DataSequence xDataSequence;
  /** y's data sequence */
  protected DataSequence yDataSequence;
  /** Sum of x * y */
  protected double sumOfXY;

  /**
   * Constructs a new, empty Regression object.
   */
  protected Regression() {
    this.xDataSequence = new DataSequence();
    this.yDataSequence = new DataSequence();
    this.sumOfXY = 0;
  }

  /**
   * Adds this (x,y) value to the dataset used to compute this regression.
   * 
   * @param x the x value.
   * @param y the y value.
   */
  public abstract void add(Number x, Number y) ;
  
  /**
   * Adds this (x,y) value to the dataset used to compute this regression.
   * 
   * @param x the x value.
   * @param y the y value.
   */
  public abstract void add(double x, double y) ;

  /**
   * Returns the <code>a</code> value of this regression line.
   *
   * @return the <code>a</code> value.
   */
  public abstract double getA() ;

  /**
   * Returns the <code>b</code> value of this regression line.
   *
   * @return the <code>b</code> value.
   */
  public abstract double getB() ;

  /**
   * Returns the number of (x, y) data points used to compute this regression.
   *
   * @return the number of elements.
   */
  public int getSize() { //NOPMD
    return this.xDataSequence.getSize();
  }

  /**
   * Returns the value of n * Sxx.
   * <pre>
   *       Sxx = (sum of (x^2)) - (sum of x) ^ 2 / n
   * 
   *   n * Sxx = n * (sum of (x^2)) - (sum of x) ^ 2
   * </pre>
   * where x and y are the pair of data sequences.  Use in conjunction with <code>nSxy</code> and
   * <code>nSyy</code> to avoid possibile truncation errors.
   *
   * @return the value of n * Sxx.
   */
  protected double nSxx() {
    return (this.getSize() * this.xDataSequence.getSumOfSquares())
            - Math.pow(this.xDataSequence.getSum(), 2);
  }

  /**
   * Returns the value of n * Sxy.
   * <pre>
   *      Sxy = (sum of (x * y)) - ((sum of x) * (sum of y)) / n
   * 
   *   n * Sxy = n * (sum of (x * y)) - (sum of x) * (sum of y)
   * </pre>
   * where x and y are the pair of data sequences.  Use in conjunction with <code>nSxx</code> and
   * <code>nSyy</code> to avoid possibile truncation errors.
   *
   * @return the value of n * Sxy.
   */
  protected double nSxy() {
    return (this.getSize() * this.sumOfXY) 
            - (this.xDataSequence.getSum() * this.yDataSequence.getSum());
  }

  /**
   * Returns the value of n * Syy.
   * <pre>
   *       Syy = (sum of (y^2)) - (sum of y) ^ 2 / n
   * 
   *   n * Syy = n * (sum of (y^2)) - (sum of y) ^ 2
   * </pre>
   * where x and y are the pair of data sequences.  Use in conjunction with <code>nSxx</code> and
   * <code>nSxy</code> to avoid possibile truncation errors.
   *
   * @return the value of n * Syy.
   */
  protected double nSyy() {
    return (this.getSize() * this.yDataSequence.getSumOfSquares())
            - Math.pow(this.yDataSequence.getSum(), 2);
  }

  /**
   * Returns the r^2 value for this regression.
   * <pre>
   *     r = Sxy / (square root of (Sxx * Syy))
   * 
   *   r^2 = (Sxy / (square root of (Sxx * Syy))) ^ 2
   * </pre>
   *
   * @return the value of r ^ 2.
   */
  public double getRSquared() { //NOPMD
    // r = n * Sxy / n * (square root of (Sxx * Syy))
    // r = n * Sxy / (square root of ((n ^ 2) * Sxx * Syy))
    // r = n * Sxy / (square root of ((n * Sxx) * (n * Syy)))
    double r = nSxy() / Math.sqrt(nSxx() * nSyy());
    return Math.pow(r, 2);
  }

  /**
   * Returns the predicted y value corresponding to the passed x value.
   *
   * @param x a guess for the x value.
   * @return the predicted y value.
   */
  public abstract double getPredictedY(double x) ;
  /**
   * Calculates the 70% confidence range.
   *
   * @param prediction the predicted value.
   * @return the 70% confidence.
   */
  public double getSeventyRange(double prediction) { //NOPMD
    // 70% prediction range
    double tRange70 = 0;
    double mean = this.xDataSequence.getMean();
    double[] t70 = {
      1.963, 1.386, 1.25, 1.19, 1.156, 1.134, 1.119, 1.108, 1.1, 1.093, 1.074, 1.064, 1.055, 1.036
    };
    int i = getSize() - 3;

    if (i < 0) {
      i = 0;
    }

    if ((i >= 11) && (i <= 15)) {
      i = 10;
    }
    else if ((i >= 16) && (i <= 20)) {
      i = 11;
    }
    else if ((i >= 21) && (i <= 30)) {
      i = 12;
    }
    else if (i > 30) {
      i = 13;
    }

    double tValue70 = t70[i];

    double varianceVal = getVariance();
    double stdDevVal = Math.sqrt(varianceVal);

    for (Iterator j = this.xDataSequence.iterator(); j.hasNext(); ) {
      double next = ((Number) j.next()).doubleValue();
      tRange70 += (next - mean) * (next - mean);
    }

    try {
      tRange70 = ((prediction - mean) * (prediction - mean)) / tRange70;

      tRange70 = Math.sqrt((1 + (1 / (double) getSize()) + tRange70));

      tRange70 = Math.round(tValue70 * stdDevVal * tRange70);
    }
    catch (ArithmeticException badMath) {
      badMath.printStackTrace();
      tRange70 = 1;
    }

    return tRange70;
  }

  /**
   * Returns the variance for this regression.
   *
   * @return the variance.
   */
  public double getVariance() {
    double vTemp = 0;
    double vHold = 0;
    
    Iterator i = this.xDataSequence.iterator();
    Iterator j = this.yDataSequence.iterator();
    while (i.hasNext()) {
      vTemp = (((Number) j.next()).doubleValue() - 
        getPredictedY(((Number) i.next()).doubleValue()));
      vHold = vHold + (vTemp * vTemp);
    }
    return (1 / (double) (getSize() - 2)) * vHold;
  }
}
