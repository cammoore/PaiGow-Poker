/**
 * 
 */
package org.cam.paigow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.cam.utilities.statistics.Binner;
import org.cam.utilities.statistics.DataSequence;

/**
 * @author Cam Moore
 * 
 */
public class BettingMain {
  public static final int STAKE = 100;

  /**
   * @param args
   */
  public static void main(String[] args) {
    PaiGowTable table = new PaiGowTable();
    BettingResultsFactory factory = BettingResultsFactory.getInstance();
    DataSequence[] seatSequence = new DataSequence[3 * PaiGowTable.NUM_SEATS];
    DataSequence[] tenAntiSequence = new DataSequence[3];
    DataSequence[] twentyFiveAntiSequence = new DataSequence[3];
    DataSequence[] combinedSequence = new DataSequence[3 * PaiGowTable.NUM_SEATS];
    DataSequence[] ten = new DataSequence[3];
    DataSequence[] twenty = new DataSequence[3];
    for (int i = 0; i < 3; i++) {
      tenAntiSequence[i] = new DataSequence();
      twentyFiveAntiSequence[i] = new DataSequence();
      ten[i] = new DataSequence();
      twenty[i] = new DataSequence();
    }
    for (int i = 0; i < 3 * PaiGowTable.NUM_SEATS; i++) {
      seatSequence[i] = new DataSequence();
      combinedSequence[i] = new DataSequence();
    }
    int rounds = 0;
    for (int j = 0; j < 20; j++) {
      table = new PaiGowTable();
      rounds = (10 + j * 5);
      table.playRounds(rounds);
      for (int i = 0; i < PaiGowTable.NUM_SEATS; i++) {
        PaiGowSeat seat = table.getSeat(i);
        PaiGowBetting res = factory.calcStats(seat, STAKE, 10);
        PaiGowBetting twentyFive = factory.calcStats(seat, STAKE, 25);
        int k = 0;
        ten[k].add(res.getCombinedStake(BettingScheme.NO_RAISE));
        twenty[k].add(twentyFive.getCombinedStake(BettingScheme.NO_RAISE));
        tenAntiSequence[k].add(res.getCombinedStake(BettingScheme.NO_RAISE));
        twentyFiveAntiSequence[k].add(twentyFive.getCombinedStake(BettingScheme.NO_RAISE));
        seatSequence[i * 3 + k].add(twentyFive.getCombinedStake(BettingScheme.NO_RAISE));
        combinedSequence[i * 3 + k].add(res.getCombinedStake(BettingScheme.NO_RAISE));
        k = 1;
        ten[k].add(res.getCombinedStake(BettingScheme.RAISE_ONE));
        twenty[k].add(twentyFive.getCombinedStake(BettingScheme.RAISE_ONE));
        tenAntiSequence[k].add(res.getCombinedStake(BettingScheme.RAISE_ONE));
        twentyFiveAntiSequence[k].add(twentyFive.getCombinedStake(BettingScheme.RAISE_ONE));
        seatSequence[i * 3 + k].add(twentyFive.getCombinedStake(BettingScheme.RAISE_ONE));
        combinedSequence[i * 3 + k].add(res.getCombinedStake(BettingScheme.RAISE_ONE));
        k = 2;
        ten[k].add(res.getCombinedStake(BettingScheme.MAX_RAISE));
        twenty[k].add(twentyFive.getCombinedStake(BettingScheme.MAX_RAISE));
        tenAntiSequence[k].add(res.getCombinedStake(BettingScheme.MAX_RAISE));
        twentyFiveAntiSequence[k].add(twentyFive.getCombinedStake(BettingScheme.MAX_RAISE));
        seatSequence[i * 3 + k].add(twentyFive.getStake(BettingScheme.MAX_RAISE));
        combinedSequence[i * 3 + k].add(res.getCombinedStake(BettingScheme.MAX_RAISE));
      }
    }
    int k = 0;
    System.out
        .format(
            "NO RAISE 25 anti mean %4.2f stdev %4.2f max %4.0f min %4.0f  10 anti mean %4.2f stdev %4.2f max %4.0f min %4.0f\n",
            twentyFiveAntiSequence[k].getMean(), twentyFiveAntiSequence[k].getStandardDeviation(),
            twentyFiveAntiSequence[k].getMax(), seatSequence[k].getMin(),
            tenAntiSequence[k].getMean(), tenAntiSequence[k].getStandardDeviation(),
            tenAntiSequence[k].getMax(), tenAntiSequence[k].getMin());
    System.out.println();
    System.out.println(new Binner(ten[k], 8));
    System.out.println();
    System.out.println(new Binner(twenty[k], 8));
    k++;
    System.out
        .format(
            "RAISE ONE 25 anti mean %4.2f stdev %4.2f max %4.0f min %4.0f  10 anti mean %4.2f stdev %4.2f max %4.0f min %4.0f\n",
            twentyFiveAntiSequence[k].getMean(), twentyFiveAntiSequence[k].getStandardDeviation(),
            twentyFiveAntiSequence[k].getMax(), twentyFiveAntiSequence[k].getMin(),
            tenAntiSequence[k].getMean(), tenAntiSequence[k].getStandardDeviation(),
            tenAntiSequence[k].getMax(), tenAntiSequence[k].getMin());
    System.out.println();
    System.out.println(new Binner(ten[k], 8));
    System.out.println();
    System.out.println(new Binner(twenty[k], 8));
    k++;
    System.out
        .format(
            "MAX RAISE 25 anti mean %4.2f stdev %4.2f max %4.0f min %4.0f  10 anti mean %4.2f stdev %4.2f max %4.0f min %4.0f\n",
            twentyFiveAntiSequence[k].getMean(), twentyFiveAntiSequence[k].getStandardDeviation(),
            twentyFiveAntiSequence[k].getMax(), twentyFiveAntiSequence[k].getMin(),
            tenAntiSequence[k].getMean(), tenAntiSequence[k].getStandardDeviation(),
            tenAntiSequence[k].getMax(), tenAntiSequence[k].getMin());
    System.out.println(new Binner(ten[k], 8));
    System.out.println(new Binner(twenty[k], 8));
    try {
      String fileName = System.getProperty("user.dir") + File.separator + "Results.csv";
      System.out.println(fileName);
      PrintWriter pw = new PrintWriter(new File(fileName));
      k = 0;
      System.out.format("$10 No Raise sum %4.2f, mean %4.2f, min %4.2f, max %4.2f\n",
          ten[k].getSum(), ten[k].getMean(), ten[k].getMin(), ten[k].getMax());
      System.out.format("$25 No Raise sum %4.2f, mean %4.2f, min %4.2f, max %4.2f\n",
          twenty[k].getSum(), twenty[k].getMean(), twenty[k].getMin(), twenty[k].getMax());
      pw.println("$10 " + BettingScheme.NO_RAISE + ", " + new Binner(ten[k], 8).toCSV());
      pw.println("$25 " + BettingScheme.NO_RAISE + ", " + new Binner(twenty[k], 8).toCSV());
      k++;
      System.out.format("$10 Raise one sum %4.2f, mean %4.2f, min %4.2f, max %4.2f\n",
          ten[k].getSum(), ten[k].getMean(), ten[k].getMin(), ten[k].getMax());
      System.out.format("$25 Raise one sum %4.2f, mean %4.2f, min %4.2f, max %4.2f\n",
          twenty[k].getSum(), twenty[k].getMean(), twenty[k].getMin(), twenty[k].getMax());
      pw.println("$10 " + BettingScheme.RAISE_ONE + ", " + new Binner(ten[k], 8).toCSV());
      pw.println("$25 " + BettingScheme.RAISE_ONE + ", " + new Binner(twenty[k], 8).toCSV());
      k++;
      System.out.format("$10 Max Raise sum %4.2f, mean %4.2f, min %4.2f, max %4.2f\n",
          ten[k].getSum(), ten[k].getMean(), ten[k].getMin(), ten[k].getMax());
      System.out.format("$25 Max Raise sum %4.2f, mean %4.2f, min %4.2f, max %4.2f\n",
          twenty[k].getSum(), twenty[k].getMean(), twenty[k].getMin(), twenty[k].getMax());
      pw.println("$10 " + BettingScheme.MAX_RAISE + ", " + new Binner(ten[k], 8).toCSV());
      pw.println("$25 " + BettingScheme.MAX_RAISE + ", " + new Binner(twenty[k], 8).toCSV());
      pw.flush();
      pw.close();
    }
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
