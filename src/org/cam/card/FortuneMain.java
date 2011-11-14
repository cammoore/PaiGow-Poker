/**
 * 
 */
package org.cam.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Cam Moore
 * 
 */
public class FortuneMain {

  /**
   * @param args ignored.
   */
  public static void main(String[] args) {
    int startingFortuneStake = 100;
    int result = startingFortuneStake;
    int numTries = 100;
    int runs = 10;
    int round = 0;
    int bet = 1;
//    int goodReturns = 0;
    double totalReturns = 0;
    double aveReturn = 0;
//    double aveGoodReturn = 0;
    Map<String, List<PaiGowFortunePayout>> payouts = 
      new HashMap<String, List<PaiGowFortunePayout>>();
    Map<String, List<PaiGowFortunePayout>> losses = 
      new HashMap<String, List<PaiGowFortunePayout>>();
    for (int i = 0; i < runs; i++) {
//      goodReturns = 0;
      round = 0;
      result = startingFortuneStake;
      int payout;
      while (round < numTries) {
        PaiGowHand hand = new PaiGowHand(new PaiGowDeck());
        PaiGowFortunePayout pay = new PaiGowFortunePayout(hand);
        // System.out.println("fortune payout = " + pay.getPays());
        if (pay.getPays() > 0) {
          List<PaiGowFortunePayout> list = payouts.get(pay.getType());
          if (list == null) {
            list = new ArrayList<PaiGowFortunePayout>();
            payouts.put(pay.getType(), list);
          }
          list.add(pay);
        }
        else {
          List<PaiGowFortunePayout> list = losses.get(pay.getType());
          if (list == null) {
            list = new ArrayList<PaiGowFortunePayout>();
            losses.put(pay.getType(), list);
          }
          list.add(pay);

        }
        payout = PaiGowFortunePayout.payout(hand, bet);
        // System.out.println(hand + " " + hand.description() + " payed " +
        // payout);
        result += payout;
        round++;
      }
      totalReturns += result;
      // if (i % 100 == 0) {
      // System.out.println("result = " + result);
      // for (String key : payouts.keySet()) {
      // System.out.println(key + ":");
      // List<PaiGaoFortunePayout> payoutList = payouts.get(key);
      // for (PaiGaoFortunePayout p : payoutList) {
      // System.out.println(" " + p.getHand().cards + " played " + p.getHand());
      // }
      // }
      // }
      aveReturn += result;
      // aveGoodReturn += goodReturns;
      if (runs < 20) {
        System.out.println("result = " + result);
        for (Entry<String, List<PaiGowFortunePayout>> e : payouts.entrySet()) {
          System.out.println(e.getKey() + ":");
          List<PaiGowFortunePayout> payoutList = e.getValue();
          for (PaiGowFortunePayout p : payoutList) {
            System.out.println(" " + p.getHand().cards + " played " + p.getHand());
          }
        }
      }
    }
    System.out.println("Average return = " + (totalReturns / runs));
  }

}