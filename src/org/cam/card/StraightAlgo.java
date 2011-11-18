/**
 * 
 */
package org.cam.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Cam Moore
 *
 */
public class StraightAlgo {

  public static boolean hasStraight(PaiGowHand hand) {
    boolean ret = false;
    if (isSimpleSevenCardStraight(hand)) {
      return true;
    }
    if (hasSimpleFiveCardStraight(hand)) {
      return true;
    }
    return hasFiveCardStraightWithJoker(hand);
  }
  
  public static boolean isSimpleSevenCardStraight(PaiGowHand hand) {
    return Card.isStraight(hand.getCards());
  }
  
  public static boolean isSimpleSevenCardStraightFlush(PaiGowHand hand) {
    return Card.isStraightFlush(hand.getCards());
  }
  
  public static boolean hasSimpleFiveCardStraight(PaiGowHand hand) {
    List<ICard> cards = hand.getCards();
    // sort the cards
    Collections.sort(cards);
    // Check for simple 5 card straight.
    for (int i = 0; i < 3; i++) {
      ArrayList<ICard> temp = new ArrayList<ICard>();
      for (int j = 0; j < 5; j++) {
        temp.add(cards.get(i + j));
      }
      if (Card.isStraight(temp)) {
        return true;
      }
    }
    
    return false;
  }
  
  public static boolean hasFiveCardStraightWithJoker(PaiGowHand hand) {
    if (hand.hasJoker()) {
      List<ICard> cards = hand.getCards();
      // sort the cards
      Collections.sort(cards);
      boolean ret = false;
      boolean usedJokerp = false;
      int startRank = -1;
      for(int i = 0; i < 3; i++) {
        startRank = cards.get(i).getRank();
        System.out.println("start rank = " + startRank);
        int runCount = 1;
        for (int j = 0; j < 4; j++) { // looking for the next four cards
          if (handHasNextRank(startRank, cards)) {
            startRank++;
            runCount++;
          } else if (hasJokerFillNextRank(startRank, cards) && !usedJokerp) {
            usedJokerp = true;
            startRank++;
            runCount++;
          }
        }
        if (runCount == 5) {
          return true;
        }
      }
      if (!ret && cards.get(cards.size() -1).getRank() == Card.ACE) {
        
      }
      return ret;
    } else {
      return false;
    }
  }
  
  private static boolean handHasNextRank(int rank, List<ICard> cards) {
    for (ICard card : cards) {
      if (card.getRank() == rank + 1) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean hasJokerFillNextRank(int rank, List<ICard> cards) {
    for (ICard card : cards) {
      if (card.getRank() == rank + 2 ||
          card.getRank() == rank - 2) {
        return true;
      }
    }
    return false;
  }
}
