package org.cam.utilities.statistics;

import junit.framework.TestCase;

/**
 * Tests the Regression subclasses.
 *
 * @author Joy M. Agustin
 * @version $Id: TestRegressionClasses.java,v 1.1.1.1 2005/10/20 23:56:48 johnson Exp $
 */
public class TestRegressionClasses extends TestCase {

  /** Holds the test data points */
  private Regression regression;

  /** Sets up the private instance variables for each test case. */
  private void fillInData() {
    this.regression.add(new Integer(3), new Integer(57));
    this.regression.add(new Integer(4), new Integer(78));
    this.regression.add(new Integer(4), new Integer(72));
    this.regression.add(new Integer(2), new Integer(58));
    this.regression.add(new Integer(5), new Integer(89));
    this.regression.add(new Integer(3), new Integer(63));
    this.regression.add(new Integer(4), new Integer(73));
    this.regression.add(new Integer(5), new Integer(84));
    this.regression.add(new Integer(3), new Integer(75));
    this.regression.add(new Integer(2), new Integer(48));
  }

  /** Tests the LinearRegression class. */
  public void testLinearRegression() {
    this.regression = new LinearRegression();
    fillInData();
    // conduct tests
    assertEquals("Checking a value", 10.90, this.regression.getA(), 0.05);
    assertEquals("Checking b value", 31.55, this.regression.getB(), 0.05);
    assertEquals("Checking number of elements", 10, this.regression.getSize());
    assertEquals("Checking  r^2 value", 0.83, this.regression.getRSquared(), 0.05);
    assertEquals("Checking predicted y value at x=6", 96.95, 
                 this.regression.getPredictedY((double) 6), 0.05);
    assertEquals("Checking seventy range value", 14,
                 this.regression.getSeventyRange((double) 10), 0.05);
  }

  /** Tests the ExponentialRegression class. */
  public void testExponentialRegression() {
    this.regression = new ExponentialRegression();
    fillInData();
    // conduct tests
    assertEquals("Checking a value", 0.16, this.regression.getA(), 0.05);
    assertEquals("Checking b value", 39.01, this.regression.getB(), 0.05);
    assertEquals("Checking number of elements", 10, this.regression.getSize());
    assertEquals("Checking  r^2 value", 0.83, this.regression.getRSquared(), 0.05);
    assertEquals("Checking predicted y value at x=6", 102.58,
                 this.regression.getPredictedY((double) 6), 0.05);
    assertEquals("Checking seventy range value", 186.0,
                 this.regression.getSeventyRange((double) 10), 0.05);
  }

  /** Tests the LogarithmicRegression class. */
  public void testLogarithmicRegression() {
    this.regression = new LogarithmicRegression();
    fillInData();
    // conduct tests
    assertEquals("Checking a value", 35.28, this.regression.getA(), 0.05);
    assertEquals("Checking b value", 27.15, this.regression.getB(), 0.05);
    assertEquals("Checking number of elements", 10, this.regression.getSize());
    assertEquals("Checking  r^2 value", 0.83, this.regression.getRSquared(), 0.05);
    assertEquals("Checking predicted y value at x=6", 90.37,
                  this.regression.getPredictedY((double) 6), 0.05);
    assertEquals("Checking predicted y value at x=1", 783.73,
                 this.regression.getPredictedY((double) 1), 0.05);
    assertEquals("Checking seventy range value", 1437.0,
                 this.regression.getSeventyRange((double) 10), 0.05);
  }
  
  /** Tests the PowerRegression class. */
  public void testPowerRegression() {
    this.regression = new PowerRegression();
    fillInData();
    // conduct tests
    assertEquals("Checking a value", 0.53, this.regression.getA(), 0.05);
    assertEquals("Checking b value", 36.31, this.regression.getB(), 0.05);
    assertEquals("Checking number of elements", 10, this.regression.getSize());
    assertEquals("Checking  r^2 value", 0.83, this.regression.getRSquared(), 0.05);
    assertEquals("Checking predicted y value at x=6", 93.37,
                 this.regression.getPredictedY((double) 6), 0.05);
    assertEquals("Checking predicted y value at x=1", 38.18,
                 this.regression.getPredictedY((double) 1), 0.05);
    assertEquals("Checking seventy range value", 84.0,
                 this.regression.getSeventyRange((double) 10), 0.05);
  }
}