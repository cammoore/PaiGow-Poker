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
public class Card implements ICard {
  private int suit;
  private int rank;

  /**
   * Constructs a Card instance.
   * 
   * @param suit the suit.
   * @param rank the rank.
   */
  public Card(int suit, int rank) {
    assert (rank < 14);
    assert (rank > 1);
    assert (suit < 5);
    assert (suit > -1);
    this.suit = suit;
    this.rank = rank;
  }

  /**
   * 
   * @return The rank of the card.
   * @see org.cam.card.ICard#getRank()
   */
  @Override
  public int getRank() {
    return rank;
  }

  /**
   * 
   * @return the suit of the card.
   * @see org.cam.card.ICard#getSuit()
   */
  @Override
  public int getSuit() {
    return suit;
  }

  /**
   * 
   * @param o an ICard
   * @return -1 if less, 0 if equal, 1 if greater.
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(ICard o) {
    if (rank < o.getRank()) {
      return -1;
    }
    else if (rank > o.getRank()) {
      return 1;
    }
    if (suit < o.getSuit()) {
      return -1;
    }
    else if (suit > o.getSuit()) {
      return 1;
    }
    return 0;
  }
  
  /**
   * 
   * @param o another card.
   * @return true if the suit and rank of the two cards are the same.
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object o) {
    try {
      if (o == null) {
        return false;
      }
      else {
        Card that = (Card) o;
        return this.suit == that.suit && this.rank == that.rank;
      }
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * 
   * @return the hashcode for this card.
   * @see java.lang.Object#hashCode()
   */
  public int hashCode() {
    return suit ^ rank;
  }
  /**
   * @param a a Card
   * @param b a Card
   * @return true if they are a pair.
   */
  public static boolean isPair(ICard a, ICard b) {
    return a.getRank() == b.getRank();
  }

  /**
   * @param hand the hand.
   * @return true if the hand has a pair.
   */
  public static boolean hasPair(List<ICard> hand) {
    boolean ret = false;
    Collections.sort(hand);
    for (int i = 0; i < hand.size() - 1; i++) {
      if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
        ret = true;
      }
    }
    return ret;
  }

  /**
   * @param hand the hand
   * @return true if the hand has two pair.
   */
  public static boolean hasTwoPair(List<ICard> hand) {
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
   * @param hand the hand
   * @return true if the hand has three pair.
   */
  public static boolean hasThreePair(List<ICard> hand) {
    boolean ret = false;
    Collections.sort(hand);
    int firstRank = -1;
    int secondRank = -1;
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
        secondRank = hand.get(j).getRank();
        count = j;
        break;
      }
    }
    for (int k = count + 1; k < hand.size() - 1; k++) {
      if (hand.get(k).getRank() == hand.get(k + 1).getRank() &&
          firstRank != hand.get(k).getRank() && secondRank != hand.get(k).getRank()) {
        return true;
      }

    }
    return ret;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has three of a kind.
   */
  public static boolean hasThreeOfAKind(List<ICard> hand) {
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
   * @return true if the hand has a four of a kind.
   */
  public static boolean hasFourOfAKind(List<ICard> hand) {
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
  public static boolean isFlush(List<ICard> hand) {
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
   * @return true if the hand has a straight.
   */
  public static boolean isStraight(List<ICard> hand) {
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
   * @return true if the hand has a straigh flush.
   */
  public static boolean isStraightFlush(List<ICard> hand) {
    return isFlush(hand) && isStraight(hand);
  }

  /**
   * @param hand the hand
   * @return true if the hand has a royal flush
   */
  public static boolean isRoyalFlush(List<ICard> hand) {
    Collections.sort(hand);
    return isStraightFlush(hand) && hand.get(hand.size() - 1).getRank() == 14;
  }

  /**
   * @param hand the hand.
   * @return true if the hand has a joker.
   */
  public static boolean hasJoker(List<ICard> hand) {
    for (ICard c : hand) {
      if (c.getSuit() == ICard.PAI_GAO_JOKER) {
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * @return true if this card is a joker.
   * @see org.cam.card.ICard#isJoker()
   */
  @Override
  public boolean isJoker() {
    return suit == ICard.PAI_GAO_JOKER;
  }

  /**
   * 
   * @return a String representation of the card.
   * @see java.lang.Object#toString()
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (suit == ICard.PAI_GAO_JOKER) {
      return "JK";
    }
    switch (suit) {
    case ICard.SPADES:
      sb.append("S");
      break;
    case ICard.HEARTS:
      sb.append("H");
      break;
    case ICard.DIAMONDS:
      sb.append("D");
      break;
    case ICard.CLUBS:
      sb.append("C");
      break;
    default:
      // do nothing.
    }
    switch (rank) {
    case 14:
      sb.append("A");
      break;
    case 11:
      sb.append("J");
      break;
    case 12:
      sb.append("Q");
      break;
    case 13:
      sb.append("K");
      break;
    default:
      sb.append(rank);
    }
    return sb.toString();
  }
}
