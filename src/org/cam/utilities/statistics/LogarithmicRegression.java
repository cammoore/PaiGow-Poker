package org.cam.utilities.statistics;

import java.util.Iterator;

/**
 * Calculates logarithmic regression using the method of least squares.  Methods implement
 * common statistical operations.  To perform statistical operations, pairs of (X,Y) values need to
 * be added to the set of data.
 * <p>
 * This class uses the logarithmic regression model
 * <pre>
 *   y = b + a * ln(x)
 * </pre>
 * where <code>a</code> and <code>b</code> are the estimated regression coefficients.
 * <code>a</code> and <code>b</code> are also respectively known as the slope and y-intercept of
 * the line and are set to the following values:
 * <pre>
 *   a = Sxy / Sxx
 * 
 *   b = (sum of x) / n - a * (sum of y) / n
 * </pre>
 * where
 * <pre>
 * Sxx = (sum of (x^2)) - (sum of x) ^ 2 / n
 * 
 * Sxy = (sum of (x * y)) - ((sum of x) * (sum of y)) / n
 * </pre>
 *
 * @author Philip Johnson
 * @author <a href="mailto:cmoore@orinconhi.com">Cam Moore</a>
 * @author Joy M. Agustin
 * @version $Id: LogarithmicRegression.java,v 1.1.1.1 2005/10/20 23:56:48 johnson Exp $
 */
public class LogarithmicRegression extends Regression {
  /** Holds the original x values */
  private DataSequence originalXValues;

  /** Constructs a new LogarithmicRegression object. */
  public LogarithmicRegression() {
    super();
    this.originalXValues = new DataSequence();
  }

  /**
   * Adds this (x,y) value to the dataset used to compute this regression.
   * 
   * @param x the x value.
   * @param y the y value.
   */
  public void add(Number x, Number y) {
    // store the x value
    this.originalXValues.add(x);
    // transform and store the x value
    
    double xValueLogArg = (x.doubleValue() == 0.0) ? 0.5 : x.doubleValue();
    double xValue = Math.log(xValueLogArg);
    this.xDataSequence.add(new Double(xValue));
    // store the y value
    this.yDataSequence.add(y);
    // sum the product of the new x and y values
    this.sumOfXY += xValue * y.doubleValue();
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
   * Returns the <code>b</code> value (the y-intercept) of this regression line.
   *
   * @return the <code>b</code> value.
   */
  public double getB() {  
    return (this.yDataSequence.getMean() - (getA() * this.xDataSequence.getMean()));
  }

  /**
   * Returns the predicted y value corresponding to the passed x value.
   *
   * @param x a float.
   * @return the predicted y value.
   */
  public double getPredictedY(double x) {
    return ((x < 1.1) ? 
           (getB() * Math.exp(getA() * Math.log(1.1))) :
           (getB() + (getA() * Math.log(x))));
  }

  /**
   * Calculates the 70% confidence range.
   *
   * @param prediction the predicted value.
   * @return the 70% confidence.
   */
  public double getSeventyRange(double prediction) {
    // 70% prediction range
    double tRange70 = 0;
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

    for (Iterator j = this.originalXValues.iterator(); j.hasNext(); ) {
      double next = ((Number) j.next()).doubleValue();
      tRange70 += ((next - mean) * (next - mean));
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
}
