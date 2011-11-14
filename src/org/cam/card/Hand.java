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
public class Hand {
  protected List<ICard> cards;

  /**
   * Constructs a Hand instance.
   */
  protected Hand() {
    this.cards = new ArrayList<ICard>();
  }
  
  /**
   * Constructs a Hand instance.
   * @param d The Deck to use.
   * @param numCards the number of cards in the hand.
   */
  public Hand(Deck d, int numCards) {
    cards = new ArrayList<ICard>();
    for (int i = 0; i < numCards; i++) {
      if (d.hasNext()) {
        cards.add(d.next());
      }
    }
  }

  
  /**
   * @return The cards value a List<ICard>.
   */
  public List<ICard> getCards() {
    ArrayList<ICard> ret = new ArrayList<ICard>();
    for (int i = 0; i < cards.size(); i++) {
      ret.add(cards.get(i));
    }
    return ret;
  }

  /**
   * @return true if the hand has a pair.
   */
  public boolean hasPair() {
    return Card.hasPair(cards);
  }

  /**
   * @return true if the hand has two pair.
   */
  public boolean hasTwoPair() {
    return Card.hasTwoPair(cards);
  }
  
  /**
   * @return true if the hand has three pair.
   */
  public boolean hasThreePair() {
    return Card.hasThreePair(cards);
  }
  
  /**
   * @return true if the hand has a three of a kind.
   */
  public boolean hasThreeOfAKind() {
    return Card.hasThreeOfAKind(cards);
  }

  /**
   * @return true if the hand has a full house.
   */
  public boolean hasFullHouse() {
    return Card.hasFullHouse(cards);
  }

  /**
   * @return true if the hand is a flush.
   */
  public boolean isFlush() {
    return Card.isFlush(cards);
  }

  /**
   * @return true if the hand is a straight.
   */
  public boolean isStraight() {
    return Card.isStraight(cards);
  }

  /**
   * @return true if the hand is a straight flush.
   */
  public boolean isStraightFlush() {
    return Card.isStraightFlush(cards);
  }

  /**
   * @return true if the hand is a royal flush.
   */
  public boolean isRoyalFlush() {
    return Card.isRoyalFlush(cards);
  }

  /**
   * @return true if the hand has a four of a kind.
   */
  public boolean hasFourOfAKind() {
    return Card.hasFourOfAKind(cards);
  }

  /**
   * 
   * @return the String representation of the Hand.
   * @see java.lang.Object#toString()
   */
  public String toString() {
    Collections.sort(cards);
    Collections.reverse(cards);
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (ICard c : cards) {
      sb.append(c);
      sb.append(", ");

    }
    return sb.substring(0, sb.toString().length() - 2) + "]";
  }
}
