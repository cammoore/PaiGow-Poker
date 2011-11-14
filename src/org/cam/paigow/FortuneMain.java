/**
 * 
 */
package org.cam.paigow;

import org.cam.utilities.statistics.DataSequence;
import org.cam.utilities.statistics.LinearRegression;
import org.cam.utilities.statistics.LogarithmicRegression;

/**
 * @author Cam Moore
 * 
 */
public class FortuneMain {

  /**
   * @param args
   */
  /**
   * @param args
   */
  public static void main(String[] args) {
    PaiGowTable table = new PaiGowTable();
    int numHands = 0;
    int envyBetter = 0;
    int rounds = 0;
    DataSequence[] seatFortuneSequence = new DataSequence[PaiGowTable.NUM_SEATS];
    LinearRegression[] seatLinear = new LinearRegression[PaiGowTable.NUM_SEATS];
    LogarithmicRegression[] seatLog = new LogarithmicRegression[PaiGowTable.NUM_SEATS];
    for (int i = 0; i < PaiGowTable.NUM_SEATS; i++) {
      seatFortuneSequence[i] = new DataSequence();
      seatLinear[i] = new LinearRegression();
      seatLog[i] = new LogarithmicRegression();
    }
    for (int j = 0; j < 20; j++) {
      table = new PaiGowTable();
      rounds = (10 + j * 5);
      numHands += rounds;
      table.playRounds(rounds);
      int hands = 0;
      int tableFortune = 0;
      int envy = 0;
      Integer seatFortune[] = new Integer[PaiGowTable.NUM_SEATS];
      for (int i = 0; i < PaiGowTable.NUM_SEATS; i++) {
        seatFortune[i] = 0;
      }
      for (int i = 0; i < PaiGowTable.NUM_SEATS; i++) {
        PaiGowSeat seat = table.getSeat(i);
        hands = seat.getResults().size();
        FortuneResult res = FortuneCalculatorFactory.getInstance().calcFortune(seat);
        envy = res.getEnvy();
        if (envy > 0) {
          System.out.println("Envy hand " + j + " seat "+ i + " = " + envy);
          for (int k = 0; k < PaiGowTable.NUM_SEATS; k++) {
            if (k != i) {
              seatFortuneSequence[k].add(envy);
            }
          }
        }
        seatFortuneSequence[i].add(res.getFortune());
        seatLinear[i].add(Integer.valueOf(rounds), res.getFortune());
        seatLog[i].add(Integer.valueOf(rounds), res.getFortune());
        tableFortune += res.getFortune();
        seatFortune[i] = res.getFortune();
      }
      // System.out.print(rounds + ": table fortune = " + tableFortune);
      // for (int i = 0; i < 6; i++) {
      // System.out.print(" " + (i + 1) + ": " + seatFortune[i]);
      // }
      // System.out.println();
    }
    for (int i = 0; i < 6; i++) {
      System.out
          .format(
              "Seat %d: #%d N %d mean %4.4f stdev %4.4f max %4.0f min %4.0f (linear A %4.4f linear B %4.4f R^2 %4.4f) (log A %4.4f log B %4.4f R^2 %4.4f)\n",
              i + 1, numHands, seatFortuneSequence[i].getSize(), seatFortuneSequence[i].getMean(),
              seatFortuneSequence[i].getStandardDeviation(), seatFortuneSequence[i].getMax(),
              seatFortuneSequence[i].getMin(), seatLinear[i].getA(), seatLinear[i].getB(),
              seatLinear[i].getRSquared(), seatLog[i].getA(), seatLog[i].getB(),
              seatLog[i].getRSquared());

    }
  }
}
