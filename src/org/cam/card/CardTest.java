/**
 * 
 */
package org.cam.card;

import java.util.ArrayList;
import org.junit.Test;
import junit.framework.TestCase;

/**
 * @author Cam Moore
 *
 */
public class CardTest extends TestCase {

  /**
   * Test method for {@link org.cam.card.Card#isPair(org.cam.card.ICard, org.cam.card.ICard)}.
   */
  @Test
  public void testIsPair() {
    Card a = new Card(ICard.SPADES, 2);
    Card b = new Card(ICard.DIAMONDS,2);
    Card c = new Card(ICard.HEARTS, 3);
    assertTrue("Expecting a pair of 2s", Card.isPair(a, b));
    assertFalse("2 != 3", Card.isPair(a, c));
  }

  /**
   * Test method for {@link org.cam.card.Card#isFlush(org.cam.card.ICard[])}.
   */
  @Test
  public void testIsFlush() {
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.CLUBS, 4));
    assertTrue("expecting a flush", Card.isFlush(hand));
    hand.add(new Card(ICard.CLUBS, 13));
    assertTrue("expecting a flush", Card.isFlush(hand));    
    hand.add(new Card(ICard.CLUBS, 14));
    assertTrue("expecting a flush", Card.isFlush(hand));
    hand.add(new Card(ICard.HEARTS, 4));
    assertFalse("no longer a flush", Card.isFlush(hand));
  }

  /**
   * Test method for {@link org.cam.card.Card#isStraight(org.cam.card.ICard[])}.
   */
  @Test
  public void testIsStraight() {
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.CLUBS, 4));
    hand.add(new Card(ICard.DIAMONDS, 8));
    hand.add(new Card(ICard.CLUBS, 5));
    hand.add(new Card(ICard.HEARTS, 7));
    hand.add(new Card(ICard.SPADES, 6));
    assertTrue("expecting straight", Card.isStraight(hand)); // NOPMD
    hand.add(new Card(ICard.SPADES, 10));
    assertFalse("no longer a straight", Card.isStraight(hand));
    hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.CLUBS, 14));
    hand.add(new Card(ICard.DIAMONDS, 13));
    hand.add(new Card(ICard.CLUBS, 10));
    hand.add(new Card(ICard.HEARTS, 12));
    hand.add(new Card(ICard.SPADES, 11));
    assertTrue("should have a straight", Card.isStraight(hand));
    assertSame("hand size should be 5", hand.size(), 5);
    hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.CLUBS, 14));
    hand.add(new Card(ICard.DIAMONDS, 3));
    hand.add(new Card(ICard.CLUBS, 4));
    hand.add(new Card(ICard.HEARTS, 2));
    hand.add(new Card(ICard.SPADES, 5));
    assertTrue("Should have a straight", Card.isStraight(hand));
    
  }

  /**
   * Test detection of a pair. 
   */
  @Test
  public void testHasPair() {
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.CLUBS, 4));
    hand.add(new Card(ICard.DIAMONDS, 8));
    hand.add(new Card(ICard.CLUBS, 5));
    hand.add(new Card(ICard.HEARTS, 7));
    hand.add(new Card(ICard.SPADES, 6));
    assertFalse("no pair", Card.hasPair(hand));
    hand.add(new Card(ICard.SPADES, 4));
    assertTrue("has pair", Card.hasPair(hand));
  }
  
  /**
   * Test detection of three of a kind. 
   */
  @Test 
  public void testHasThreeOfAKind() {
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.CLUBS, 4));
    hand.add(new Card(ICard.DIAMONDS, 4));
    hand.add(new Card(ICard.CLUBS, 5));
    hand.add(new Card(ICard.HEARTS, 7));
    hand.add(new Card(ICard.SPADES, 6));
    assertFalse("no three of a kind", Card.hasThreeOfAKind(hand));
    hand.add(new Card(ICard.SPADES, 4));
    assertTrue("has three of a kind", Card.hasThreeOfAKind(hand));
  }

  /**
   * Test detection of a straight flush. 
   */
  @Test
  public void testIsStraightFlush() {
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.CLUBS, 4));
    hand.add(new Card(ICard.CLUBS, 8));
    hand.add(new Card(ICard.CLUBS, 5));
    hand.add(new Card(ICard.CLUBS, 7));
    hand.add(new Card(ICard.CLUBS, 6));
    assertTrue("no straight flush", Card.isStraightFlush(hand));
    hand.add(new Card(ICard.SPADES, 9));
    assertFalse("has straight flush", Card.isStraightFlush(hand));
  }

  /**
   * Test detection of a royal flush. 
   */
  @Test
  public void testIsRoyalFlush() {
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.CLUBS, 10));
    hand.add(new Card(ICard.CLUBS, 14));
    hand.add(new Card(ICard.CLUBS, 13));
    hand.add(new Card(ICard.CLUBS, 11));
    hand.add(new Card(ICard.CLUBS, 12));
    assertTrue("has royal flush", Card.isRoyalFlush(hand));
    hand.add(new Card(ICard.SPADES, 9));
    assertFalse("no royal flush", Card.isRoyalFlush(hand));
    hand.remove(0);
    hand.add(new Card(ICard.CLUBS, 9));
    assertTrue("is royal flush", Card.isRoyalFlush(hand));
    assertTrue("is stright", Card.isStraight(hand));
    assertTrue("is flush", Card.isFlush(hand));
  }
 
  /**
   * Test detection of a full house. 
   */
  @Test
  public void testIsFullHouse() {
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(new Card(ICard.SPADES, 3));
    hand.add(new Card(ICard.DIAMONDS, 3));
    hand.add(new Card(ICard.HEARTS, 3));
    hand.add(new Card(ICard.SPADES, 8));
    hand.add(new Card(ICard.HEARTS, 8));
    hand.add(new Card(ICard.CLUBS, 12));
    hand.add(new Card(ICard.SPADES, 12));
    assertTrue("is full house", Card.hasFullHouse(hand));
  }

}
