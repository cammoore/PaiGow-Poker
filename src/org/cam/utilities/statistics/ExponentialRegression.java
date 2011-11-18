package org.cam.utilities.statistics;

/**
 * Calculates exponential regression using the method of least squares.  Methods implement
 * common statistical operations.  To perform statistical operations, pairs of (X,Y) values need to
 * be added to the set of data.
 * <p>
 * This class uses the exponential regression model
 * <pre>
 *   y = b * e ^ (a * x)
 * </pre>
 * where <code>a</code> and <code>b</code> are the estimated regression coefficients.
 * <code>a</code> and <code>b</code> are also respectively known as the growth rate and slope of
 * the line and are set to the following values:
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
 * @version $Id: ExponentialRegression.java,v 1.1.1.1 2005/10/20 23:56:48 johnson Exp $
 */
public class ExponentialRegression extends Regression {
  /** Constructs a new ExponentialRegression Object.  */
  public ExponentialRegression() {
    super();
  }

  /**
   * Adds this (x,y) value to the dataset used to compute this regression.
   * 
   * @param x the x value.
   * @param y the y value.
   */
  public void add(Number x, Number y) {
    // store the x value
    this.xDataSequence.add(x);
    // transform and store the y value
    double yValue = 0;
    if (y.doubleValue() == 0.0) {
      yValue = Math.log(0.5);
    }
    else {
      yValue = Math.log(y.doubleValue());
    }
    this.yDataSequence.add(new Double(yValue));
    // sum the product of the x and new y values
    this.sumOfXY += x.doubleValue() * yValue;
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
   * Returns the <code>a</code> value (the growth rate) of this regression line.
   *
   * @return the growth rate.
   */
  public double getA() {
    return super.nSxy() / super.nSxx();
  }
  
  /**
   * Returns the <code>b</code> value (the slope) of this regression line.
   *
   * @return the slope.
   */
  public double getB() {  
    return Math.exp((this.yDataSequence.getMean() - (getA() * this.xDataSequence.getMean())));
  }
  
  /**
   * Returns the predicted y value corresponding to the passed x value.
   *
   * @param x a float.
   * @return the predicted y value as a float.
   */
  public double getPredictedY(double x) {
    return (getB() * Math.exp(getA() * x));
  }
}
