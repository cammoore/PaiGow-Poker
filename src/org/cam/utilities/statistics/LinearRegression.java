package org.cam.utilities.statistics;

/**
 * Calculates linear regression using the method of least squares.  Methods implement common
 * statistical operations.  To perform statistical operations, pairs of (X,Y) values need to be
 * added to the set of data.
 * <p>
 * This class uses the linear regression model
 * <pre>
 *   y = b + a * x
 * </pre>
 * where <code>a</code> and <code>b</code> are the estimated regression coefficients.
 * <code>a</code> and <code>b</code> are also respectively known as the slope and y-intercept of
 * the line and are calculated with the following formulas:
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
 * @version $Id: LinearRegression.java,v 1.1.1.1 2005/10/20 23:56:48 johnson Exp $
 */
public class LinearRegression extends Regression {
  /** Constructs a new LinearRegression Object. */
  public LinearRegression() {
    super();
  }    
   
  /**
   * Adds this (x,y) value to the dataset used to compute this regression.
   * 
   * @param x the x value.
   * @param y the y value.
   */
  public void add(Number x, Number y) {
    // store the x and y values
    this.xDataSequence.add(x);
    this.yDataSequence.add(y);
    // sum the product of the x and y values
    this.sumOfXY += x.doubleValue() * y.doubleValue();
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
    return this.yDataSequence.getMean() - (getA() * this.xDataSequence.getMean());
  }

  /**
   * Returns the predicted y value corresponding to the passed x value.
   *
   * @param x a guess for the x value.
   * @return the predicted y value.
   */
  public double getPredictedY(double x) {
    return (getB() + (getA() * x));
  }
}
