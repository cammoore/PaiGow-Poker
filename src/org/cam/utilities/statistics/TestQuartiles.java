package org.cam.utilities.statistics;

import junit.framework.TestCase;

/**
 * Test quartiles of a series of data.
 * 
 * @author Hongbing Kou
 * @version $Id$
 */
public class TestQuartiles extends TestCase {
  /**
   * Tests quartiles of the data sequence.
   * 
   * @throws Exception If error in test.
   */
  public void testQuartiles() throws Exception {
    DataSequence dataSequence = new DataSequence();
    
    Quartiles quartiles = new Quartiles(dataSequence);
    assertTrue("Test first quartile without any value", Double.isNaN(quartiles.getQ1()));
    assertTrue("Test third quartile without any value", Double.isNaN(quartiles.getQ3()));
    
    dataSequence.add(new Double(1.5));
    dataSequence.add(new Double(2.3));
    dataSequence.add(new Double(8.7));
    
    Quartiles quartiles2 = new Quartiles(dataSequence);
    assertEquals("Test medium with odd numbers", 2.3, quartiles2.getMedium(), 0.1);
    
    dataSequence.add(new Double(3.5));
    Quartiles quartiles3 = new Quartiles(dataSequence);
    assertEquals("Test medium with even numbers", 2.9, quartiles3.getMedium(), 0.1);
    assertEquals("Test first quartile", 1.9, quartiles3.getQ1(), 0.1);
    assertEquals("Test third quartile", 6.1, quartiles3.getQ3(), 0.1);
    
    assertEquals("Test IQR", 4.2, quartiles3.getIQR(), 0.1);
    assertTrue("Test ourlier 1 ", quartiles3.isOutlier(-10));
    assertFalse("Test ourlier 2 ", quartiles3.isOutlier(10.6));
    assertFalse("Test ourlier 3 ", quartiles3.isOutlier(12.1));
    assertTrue("Test ourlier 4 ", quartiles3.isOutlier(12.42));
  }
}
