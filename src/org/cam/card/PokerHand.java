/**
 * 
 */
package org.cam.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Cam Moore
 * 
 */
public class PokerHand {
  /** ROYAL_FLUSH a int. */
  public static final int ROYAL_FLUSH = 10;
  /** STRAIGHT_FLUSH a int. */
  public static final int STRAIGHT_FLUSH = 9;
  /** FOUR_OF_A_KIND a int. */
  public static final int FOUR_OF_A_KIND = 8;
  /** FULL_HOUSE a int. */
  public static final int FULL_HOUSE = 7;
  /** FLUSH a int. */
  public static final int FLUSH = 6;
  /** STRAIGHT a int. */
  public static final int STRAIGHT = 5;
  /** THREE_OF_A_KIND a int. */
  public static final int THREE_OF_A_KIND = 4;
  /** TWO_PAIR a int. */
  public static final int TWO_PAIR = 3;
  /** PAIR a int. */
  public static final int PAIR = 2;
  /** HIGH_CARD a int. */
  public static final int HIGH_CARD = 1;

  /**
   * @param hand the hand.
   * @return The ranking of the hand.
   */
  public static int getRanking(List<ICard> hand) {
    int ret = 0;
    if (hasRoyalFlush(hand)) {
      ret = ROYAL_FLUSH;
    }
    else if (hasStraightFlush(hand)) {
      ret = STRAIGHT_FLUSH;
    }
    else if (hasFourOfAKind(hand)) {
      ret = FOUR_OF_A_KIND;
    }
    else if (hasFullHouse(hand)) {
      ret = FULL_HOUSE;
    }
    else if (hasFlush(hand)) {
      ret = FLUSH;
    }
    else if (hasStraight(hand)) {
      ret = STRAIGHT;
    }
    else if (hasThreeOfAKind(hand)) {
      ret = THREE_OF_A_KIND;
    }
    else if (hasTwoPair(hand)) {
      ret = TWO_PAIR;
    }
    else if (hasPair(hand)) {
      ret = PAIR;
    }
    else {
      ret = HIGH_CARD;
    }
    return ret;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has a royal flush.
   */
  public static boolean hasRoyalFlush(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards."); //NOPMD
    }
    Collections.sort(hand);
    return hasStraightFlush(hand) && hand.get(hand.size() - 1).getRank() == ICard.ACE;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has a straight flush.
   */
  public static boolean hasStraightFlush(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards.");
    }
    return hasFlush(hand) && hasStraight(hand);
  }

  /**
   * @param hand the hand.
   * @return true if the hand has four of a kind.
   */
  public static boolean hasFourOfAKind(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards.");
    }
    boolean ret = false;
    Collections.sort(hand);
    for (int i = 0; i < hand.size() - 3; i++) {
      if (hand.get(i).getRank() == hand.get(i + 1).getRank()
          && hand.get(i).getRank() == hand.get(i + 2).getRank()
          && hand.get(i).getRank() == hand.get(i + 3).getRank()) {
        ret = true;
      }
    }
    return ret;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has a full house.
   */
  public static boolean hasFullHouse(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards.");
    }
    assert (hand.size() > 4);
    boolean pairp = hasPair(hand);
    boolean tripp = hasThreeOfAKind(hand);
    if (pairp && tripp) {
      HashMap<Integer, List<Integer>> matches = new HashMap<Integer, List<Integer>>();
      for (int i = 0; i < hand.size() - 1; i++) {
        if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
          List<Integer> positions = matches.get(hand.get(i).getRank());
          if (positions == null) {
            positions = new ArrayList<Integer>();
          }
          if (!positions.contains(i)) {
            positions.add(i);
          }
          if (!positions.contains(i + 1)) {
            positions.add(i + 1);
          }
          matches.put(hand.get(i).getRank(), positions);
        }
      }
      // System.out.print(hand + ": ");
      // System.out.println(matches);
      return matches.keySet().size() >= 2;
    }
    return false;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has a flush.
   */
  public static boolean hasFlush(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards.");
    }
    boolean ret = true;
    if (hand.size() < 2) {
      return true;
    }
    int startSuit = hand.get(0).getSuit();
    for (ICard c : hand) {
      ret &= startSuit == c.getSuit();
    }
    return ret;
  }

  /**
   * @param hand the hand.
   * @return true if the hand is a straight.
   */
  public static boolean hasStraight(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards.");
    }
    Collections.sort(hand);
    boolean ret = true;
    for (int i = 0; i < hand.size() - 1; i++) {
      ret &= hand.get(i).getRank() + 1 == hand.get(i + 1).getRank();
    }
    if (!ret && hand.get(hand.size() - 1).getRank() == 14) {
      // ended with an ace so remove it, replace it with a 1
      List<ICard> temp = new ArrayList<ICard>();
      temp.addAll(hand);
      ICard ace = temp.remove(temp.size() - 1);
      temp.add(new Card(ace.getSuit(), 1));
      Collections.sort(temp);
      boolean tempCheck = true;
      for (int i = 0; i < temp.size() - 1; i++) {
        tempCheck &= temp.get(i).getRank() + 1 == temp.get(i + 1).getRank();
      }
      ret = tempCheck;
    }
    return ret;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has three of a kind.
   */
  public static boolean hasThreeOfAKind(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards.");
    }
    boolean ret = false;
    Collections.sort(hand);
    for (int i = 0; i < hand.size() - 2; i++) {
      if (hand.get(i).getRank() == hand.get(i + 1).getRank()
          && hand.get(i).getRank() == hand.get(i + 2).getRank()) {
        ret = true;
      }
    }
    return ret;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has two pair.
   */
  public static boolean hasTwoPair(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards.");
    }
    boolean ret = false;
    Collections.sort(hand);
    int firstRank = -1;
    int count = 0;
    for (int i = 0; i < hand.size() - 1; i++) {
      if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
        firstRank = hand.get(i).getRank();
        count = i;
        break;
      }
    }
    for (int j = count + 1; j < hand.size() - 1; j++) {
      if (hand.get(j).getRank() == hand.get(j + 1).getRank() &&
          firstRank != hand.get(j).getRank()) {
        ret = true;
      }
    }
    return ret;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has a pair.
   */
  public static boolean hasPair(List<ICard> hand) {
    if (hand.size() != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards.");
    }
    boolean ret = false;
    Collections.sort(hand);
    for (int i = 0; i < hand.size() - 1; i++) {
      if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
        ret = true;
      }
    }
    return ret;
  }

}
