package org.cam.utilities.statistics;

import java.util.Iterator;

/**
 * Calculates power regression using the method of least squares. Methods implement common
 * statistical operations.  To perform statistical operations, pairs of (X,Y) values need to be
 * added to the set of data.
 * <p>
 * This class uses the power regression model
 * <pre>
 *   y = a * (x ^ b)
 * </pre>
 * where <code>a</code> and <code>b</code> are the estimated regression coefficients.
 * <code>a</code> and <code>b</code> are also respectively known as the slope and power of
 * the line and are set to the following formulas:
 * <pre>
 *   a = Sxy / Sxx
 * 
 *   b = e ^ ((sum of x) / n - a * (sum of y) / n)
 * </pre>
 * where <code>e</code> is Euler's number and
 * <pre>
 * Sxx = (sum of (x^2)) - (sum of x) ^ 2 / n
 * 
 * Sxy = (sum of (x * y)) - ((sum of x) * (sum of y)) / n
 * </pre>
 *
 * @author Philip Johnson
 * @author <a href="mailto:cmoore@orinconhi.com">Cam Moore</a>
 * @author Joy M. Agustin
 * @version $Id: PowerRegression.java,v 1.1.1.1 2005/10/20 23:56:48 johnson Exp $
 */
public class PowerRegression extends Regression {
  /** Holds the original x values */
  private DataSequence originalXValues;
  /** Holds the original y values */
  private DataSequence originalYValues;

  /** Constructs a new PowerRegression object. */
  public PowerRegression() {
    super();
    this.originalXValues = new DataSequence();
    this.originalYValues = new DataSequence();
  }

  /**
   * Adds this (x,y) value to the dataset used to compute this regression.
   * 
   * @param x the x value.
   * @param y the y value.
   */
  public void add(Number x, Number y) {
    // store the x and y values
    this.originalXValues.add(x);
    this.originalYValues.add(y);
    // transform and store the x value
    double xValueLogArg = (x.doubleValue() == 0.0) ? 0.5 : x.doubleValue();
    double xValue = Math.log(xValueLogArg);
    this.xDataSequence.add(new Double(xValue));
    // transform and store the y value
    double yValueLogArg = (y.doubleValue() == 0.0) ? 0.5 : y.doubleValue();
    double yValue = Math.log(yValueLogArg);
    this.yDataSequence.add(new Double(yValue));
    // sum the product of the new x and y values
    this.sumOfXY += xValue * yValue;
  }
    
  /**
   * Adds this (x,y) value to the dataset used to compute this regression.
   * 
   * @param x the x value.
   * @param y the y value.
   */
  public void add(double x, double y) {
    this.add(new Double(x), new Double(y));
  }

  /**
   * Returns the <code>a</code> value (the slope) of this regression line.
   *
   * @return the <code>a</code> value.
   */
  public double getA() {
    return super.nSxy() / super.nSxx();
  }
  
  /**
   * Returns the <code>b</code> value (the power) of this regression line.
   *
   * @return the <code>b</code> value.
   */
  public double getB() {
    return Math.exp(this.yDataSequence.getMean() - (getA() * this.xDataSequence.getMean()));
  }

  /**
   * Returns the predicted y value corresponding to the passed x value.
   *
   * @param x a float value.
   * @return the predicted y value.
   */
  public double getPredictedY(double x) {
    return ((x < 1.1) ?
           (getB() * Math.exp(getA() * Math.log(1.1))) :
           (getB() * Math.exp(getA() * Math.log(x))));
  }

  /**
   * Calculates the 70% prediction range given a <code>prediction</code> value.
   *
   * @param prediction the predicted value.
   * @return the 70% prediction range.
   */
  public double getSeventyRange(double prediction) {
    double tRange70 = 0;  /* 70% prediction range */
    double mean = this.originalXValues.getMean();
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

    for (Iterator<Number> j = this.originalXValues.iterator(); j.hasNext(); ) {
      double next = j.next().doubleValue();
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

    Iterator<Number> i = this.xDataSequence.iterator();
    Iterator<Number> j = this.originalYValues.iterator();
    while (j.hasNext()) {
      vTemp = j.next().doubleValue() - 
        getPredictedY(i.next().doubleValue());
      vHold = vHold + (vTemp * vTemp);
    }
    return (1 / (double) (getSize() - 2)) * vHold;
  }
}
