/**
 * 
 */
package org.cam.utilities.statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Cam Moore
 *
 */
public class Binner implements Iterable<Integer> {
  private int numBins = 1;
  private double binWidth = 0.0;
  private double start = 0.0;
  private List<Integer> counts;

  public Binner(DataSequence seq, int bins) {
    this.numBins = bins;
    this.counts = new ArrayList<Integer>();
    calcBins(seq);
  }
  
  private Binner() {
    
  }
  /**
   * 
   * @return
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<Integer> iterator() {
    return counts.iterator();
  }

  /**
   * @return The numBins value a int.
   */
  public int getNumBins() {
    return this.numBins;
  }

  public String toString() {
    StringBuilder buf = new StringBuilder();
    for(Integer i : counts) {
      buf.append("\t");
      buf.append(i);
      buf.append("\t");
    }
    buf.append("\n");
    for (int i = 0; i < numBins + 1; i++) {
      buf.append(String.format("%4.2f", start + (i * binWidth)));
      buf.append("\t\t");
    }
    return buf.toString();
  }
  /**
   * @return The binWidth value a double.
   */
  public double getBinWidth() {
    return this.binWidth;
  }
  
  public String toCSV() {
    StringBuilder b = new StringBuilder();
    for(Integer i : counts) {
      b.append(i);
      b.append(",");
    }
    b.append("\n");
    double d = start + (binWidth / 2.0);
    for(Integer i : counts) {
      b.append(d);
      b.append(",");
      d += binWidth;
    }    
    return b.toString();
  }

  /**
   * @param seq
   */
  private void calcBins(DataSequence seq) {
    this.counts = new ArrayList<Integer>();
    this.binWidth = (seq.getMax() - seq.getMin()) / numBins;
    this.start = seq.getMin();
    Integer[] bins = new Integer[numBins + 1];
    for (int i = 0; i < numBins + 1; i++) {
      bins[i] = 0;
    }
    double temp = 0;
    int index = -1;
    for (Number n : seq) {
      temp = n.doubleValue() - start;
      index = (int)Math.floor(temp / binWidth);
      bins[index]++;
    }
    for (int i = 0; i < numBins; i++) {
      counts.add(bins[i]);
    }
  }

}
