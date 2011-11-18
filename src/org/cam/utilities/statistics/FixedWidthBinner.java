package org.cam.utilities.statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FixedWidthBinner implements Iterable<Integer> {

	private int numBins = 1;
	private double binWidth = 0.0;
	private double start = 0.0;
	private List<Integer> counts;
	
	public FixedWidthBinner(DataSequence seq, Number start, Number end,
			Number width) {
		this.binWidth = width.doubleValue();
		this.start = start.doubleValue();
		this.numBins = (end.intValue() - start.intValue()) / width.intValue();
    this.counts = new ArrayList<Integer>();
    calcBins(seq);
	}

	private void calcBins(DataSequence seq) {
		this.counts = new ArrayList<Integer>();
		Integer[] bins = new Integer[numBins + 1];
    for (int i = 0; i < numBins + 1; i++) {
      bins[i] = 0;
    }
    double temp = 0;
    int index = -1;
    for (Number n : seq) {
      temp = n.doubleValue() - start;
      index = (int)Math.floor(temp / binWidth);
      if (index > -1 && index < numBins) {
      	bins[index]++;
      }
    }
    for (int i = 0; i < numBins; i++) {
      counts.add(bins[i]);
    }
	}

	@Override
	public Iterator<Integer> iterator() {
    return counts.iterator();
	}

	@Override
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

}
