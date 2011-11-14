/**
 * Simple program to see if playing the Fortune Bonus makes any sense.
 */
package org.cam.paigow;

import java.util.logging.Logger;
import org.cam.card.PaiGowDeck;
import org.cam.card.PaiGowFortunePayout;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 * 
 */
public class DoesFortunePay {

  private static final Logger LOGGER = Logger.getLogger("org.cam.paigao");
  private static final int numRuns = 100;

  /**
   * @param args
   */
  public static void main(String[] args) {
    double ave = 0.0;
    int maxResult = Integer.MIN_VALUE;
    int minResult = Integer.MAX_VALUE;
    int positiveResults = 0;
    int bigWins = 0;
    for (int j = 0; j < numRuns; j++) {
      int fortuneSum = 0;
      for (int i = 0; i < numRuns; i++) {
        PaiGowHand hand = new PaiGowHand(new PaiGowDeck());
        PaiGowFortunePayout pay = new PaiGowFortunePayout(hand);
        // LOGGER.info("fortune payout for " + pay.getHand() + " = " +
        // pay.getPays());
        fortuneSum += pay.getPays();
        if (pay.getPays() >= 50) {
          bigWins++;
          LOGGER.info("Big Fortune win " + pay.getHand() + " pays " + pay.getPays());
        }
      }
      if (fortuneSum < minResult) {
        minResult = fortuneSum;
      }
      if (fortuneSum > maxResult) {
        maxResult = fortuneSum;
      }
      if (fortuneSum > 0) {
        positiveResults++;
      }
      // LOGGER.info("fortune result = " + fortuneSum);
      ave += fortuneSum;
    }
    ave = ave / numRuns;
    LOGGER.info("There were " + positiveResults + " times out of " + numRuns + " fortune payed more than it cost.\nAnd "
        + bigWins + " big fortunes.\nMax fortune = " + maxResult + ", min = " + minResult
        + " Ave = " + ave);
  }

}
