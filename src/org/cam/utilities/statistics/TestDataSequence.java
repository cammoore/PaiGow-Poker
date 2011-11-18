package org.cam.utilities.statistics;

import java.util.Iterator;
import java.util.StringTokenizer;

import junit.framework.TestCase;

/**
 * Tests the DataSequence class.
 *
 * @author Joy M. Agustin
 * @version $Id: TestDataSequence.java,v 1.1.1.1 2005/10/20 23:56:48 johnson Exp $
 */
public class TestDataSequence extends TestCase {

  /**
   * Tests the constructor in the DataSeq class.
   */
  public void testListConstructor() {
    // fill in the sequence
    DataSequence sequence = new DataSequence();
    sequence.add(new Float(4.0));
    sequence.add(new Integer(3));
    sequence.add(new Long(1));
    sequence.add(new Double(2));

    assertEquals("Checking the size of sequence", 4, sequence.getSize());
    assertEquals("Checking the sum of sequence", 10, sequence.getSum(), 0);
    assertEquals("Checking the mean of sequence", 2.5, sequence.getMean(), 0);
    assertEquals("Checking the min of sequence", 1, sequence.getMin(), 0);
    assertEquals("Checking the max of sequence", 4, sequence.getMax(), 0);
    assertEquals("Checking the standard deviation of sequence", 1.265,
                 sequence.getStandardDeviation(), 0.05);
    assertEquals("Checking the sum of squares of sequence", 30, sequence.getSumOfSquares(), 0);

    Iterator i = sequence.iterator();
    assertEquals("Checking the first element in sequence", 4,
                 ((Number) i.next()).intValue());

    // check the toString method
    String toString = sequence.toString();
    StringTokenizer tokens = new StringTokenizer(toString, ",");
    assertEquals("Checking the amount of tokens present", 4, tokens.countTokens());
    // check each token individually
    tokens = new StringTokenizer(toString, " , ");
    // throw away the first one because it should be '[  4.0'
    tokens.nextToken();
    assertEquals("Checking the tokens", 3, new Float(tokens.nextToken()).intValue());
    assertEquals("Checking the tokens", 1, new Float(tokens.nextToken()).intValue());
  }
  
  /**
   * Tests the constructor in the DataSeq class.
   */
  public void testEmptyDataSequence() {
    // fill in the sequence
    DataSequence sequence = new DataSequence();
    assertEquals("checking size of data sequence", 0, sequence.getSize());
    try {
      sequence.getSum();
      fail("Exception should have been thrown from getSum().");
    }
    catch (IllegalStateException e) {
      assertTrue("Exception expected", true);
    }
    try {
      sequence.getMean();
      fail("Exception should have been thrown from getMean().");
    }
    catch (IllegalStateException e) {
      assertTrue("Exception expected", true);
    }
    try {
      sequence.getMin();
      fail("Exception should have been thrown from getMin().");
    }
    catch (IllegalStateException e) {
      assertTrue("Exception expected", true);
    }
    try {
      sequence.getMax();
      fail("Exception should have been thrown from getMax().");
    }
    catch (IllegalStateException e) {
      assertTrue("Exception expected", true);
    }
    try {
      sequence.getStandardDeviation();
      fail("Exception should have been thrown from getStandardDeviation().");
    }
    catch (IllegalStateException e) {
      assertTrue("Exception expected", true);
    }
    try {
      sequence.getSumOfSquares();
      fail("Exception should have been thrown from getSumOfSquares().");
    }
    catch (IllegalStateException e) {
      assertTrue("Exception expected", true);
    }
  }
  
}