package org.cam.utilities.statistics;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Define quartilers to have statistics analysis on data.
 * 
 * @author Hongbing Kou
 * @version $Id$
 */
public class Quartiles {
  /** Data sequence of the quartiles. */
  //private DataSequence dataSequence;
  
  /** Array of double values. */
  private double[] values;

  /** First quartile. */
  private double q1;

  /** Third quartile. */
  private double q3;

  /**
   * Instantiates a quartiles object with data sequence.
   * 
   * @param data Data sequence.
   */
  public Quartiles(DataSequence data) {
    int index = 0;
    this.values = new double[data.getSize()];
    
    for (Iterator i = data.iterator(); i.hasNext();) {
      Number num = (Number) i.next();
      values[index++] = num.doubleValue();
    }
    
    // Sort the array.
    Arrays.sort(values);
    
    // First half
    int halfSize = data.getSize() / 2;
    double[] firstHalf = new double[halfSize];
    
    for (index = 0; index < halfSize; index++) {
      firstHalf[index] = this.values[index];
    }
    this.q1 = this.getMedium(firstHalf);
    
    // second half
    double[] secondHalf = new double[halfSize];
    int position;
    if (data.getSize() % 2 == 0) {
      position = halfSize;
    }
    else { // skip the middle one
      position = halfSize + 1;
    }
    
    for (index = 0; position < data.getSize(); index++, position++) {
      secondHalf[index] = this.values[position];
    }
    
    this.q3 = this.getMedium(secondHalf);
    //this.dataSequence = data;
  }

  /**
   * Gets medium to the sorted values.
   * 
   * @param sortedValues Sorted double values. 
   * @return Medium value of the sorted array.
   */
  private double getMedium(double[] sortedValues) {
    // empty 
    if (sortedValues == null || sortedValues.length == 0) {
      return Double.NaN;
    }
    else { // non-empty
      int len = sortedValues.length;
      if (sortedValues.length % 2 == 0) {
        double average = sortedValues[len / 2 - 1] + sortedValues[len / 2];
        average /= 2.0;
        return average;
      }
      else {
        return sortedValues[len / 2];
      }
    }   
  }

  /**
   * Get first quartile.
   * 
   * @return First quartile.
   */
  public double getQ1() {
    return this.q1;
  }

  /**
   * Gets third quartile.
   * 
   * @return Third quartile.
   */
  public double getQ3() {
    return this.q3;
  }

  /**
   * Gets the medium value of the value list.
   * 
   * @return Medium of the data array.
   */
  public double getMedium() {
    return this.getMedium(this.values);
  }

  /**
   * Gets IQR value.
   * 
   * @return Q3 - Q1.
   */
  public double getIQR() {
    return this.q3 - this.q1;
  }

  /**
   * Checks to see whether a given data is the outlier value.
   * 
   * @param data A given value.
   * @return True if it is an outlier.
   */
  public boolean isOutlier(double data) {
    if (data > this.q3) {
      return data - this.q3 > this.getIQR() * 1.5;
    }
    else if (data < this.q1) {
      return this.q1 - data > this.getIQR() * 1.5;
    }
    else {
      return false;
    }
  }
}
