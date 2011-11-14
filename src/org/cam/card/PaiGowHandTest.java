/**
 * 
 */
package org.cam.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Cam Moore
 * 
 */
public class PaiGowHandTest extends TestCase {

  private PaiGowHand hand;
  private ArrayList<ICard> cards;
  private List<ICard> low;
  private List<ICard> high;

  /**
   * Test the different ways of setting five aces.
   */
  // @Test
  // public void testFiveAces() {
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // cards.add(new Card(ICard.HEARTS, ICard.ACE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // low = hand.getLowHand();
  // assertTrue("Have two cards", low.size() == 2);
  // assertTrue("split aces", low.get(0).getRank() == Card.ACE ||
  // low.get(0).getRank() == Card.JOKER);
  // assertTrue("split aces", low.get(1).getRank() == Card.ACE);
  // high = hand.getHighHand();
  // assertTrue("Have five cards", high.size() == 5);
  // assertTrue("Have three Aces in high hand", Card.hasThreeOfAKind(high));
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // cards.add(new Card(ICard.HEARTS, ICard.ACE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.KING));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // low = hand.getLowHand();
  // assertTrue("Have two cards", low.size() == 2);
  // assertTrue("keep aces", low.get(0).getRank() == Card.KING);
  // assertTrue("keep aces", low.get(1).getRank() == Card.KING);
  // high = hand.getHighHand();
  // assertTrue("Have five cards", high.size() == 5);
  // assertTrue("Have three Aces in high hand", Card.hasFourOfAKind(high));
  // }

  /**
   * Test the different ways of setting five aces.
   */
  // @Test
  // public void testRoyalFlush() {
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.CLUBS, ICard.JACK));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.TEN));
  // hand = new PaiGaoHand(cards);
  // assertTrue( hand.description().equals("Royal flush"));
  // System.out.println(hand + " " + hand.description());
  // low = hand.getLowHand();
  // assertTrue("Have two cards", low.size() == 2);
  // assertTrue("play royal", low.get(0).getRank() == Card.FOUR);
  // assertTrue("play royal", low.get(1).getRank() == Card.SEVEN);
  // high = hand.getHighHand();
  // assertTrue("Have five cards", high.size() == 5);
  // assertTrue("must be flush", Card.isFlush(high));
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.HEARTS, ICard.KING));
  // cards.add(new Card(ICard.HEARTS, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
  // cards.add(new Card(ICard.HEARTS, ICard.TEN));
  // cards.add(new Card(ICard.HEARTS, ICard.JACK));
  // cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // assertTrue(hand.description().equals("Royal flush"));
  // low = hand.getLowHand();
  // assertTrue("Have two cards", low.size() == 2);
  // assertTrue("play two pair", low.get(0).getRank() == Card.QUEEN);
  // assertTrue("play two pair", low.get(1).getRank() == Card.QUEEN);
  // high = hand.getHighHand();
  // assertTrue("Have five cards", high.size() == 5);
  // assertTrue("Have pair Aces in high hand", Card.hasPair(high));
  // assertTrue("aces", high.get(4).getRank() == Card.ACE);
  // assertTrue("aces", high.get(3).getRank() == Card.ACE);
  // }

  @Test
  public void testStraight() {
//     cards = new ArrayList<ICard>();
//     cards.add(new Card(ICard.CLUBS, ICard.TWO));
//     cards.add(new Card(ICard.SPADES, ICard.ACE));
//     cards.add(new Card(ICard.HEARTS, ICard.THREE));
//     cards.add(new Card(ICard.HEARTS, ICard.FIVE));
//     cards.add(new Card(ICard.HEARTS, ICard.JACK));
//     cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
//     cards.add(new Card(ICard.CLUBS, ICard.FOUR));
//     hand = new PaiGaoHand(cards);
//     System.out.println(hand + " " + hand.description());
//     assertTrue(hand.description().equals("Straight"));
//     low = hand.getLowHand();
//     assertTrue("Have two cards", low.size() == 2);
//     assertTrue("play two pair", low.get(0).getRank() == Card.JACK);
//     assertTrue("play two pair", low.get(1).getRank() == Card.QUEEN);
//     high = hand.getHighHand();
//     assertTrue("Have five cards", high.size() == 5);
//     assertTrue("aces", high.get(4).getRank() == Card.ACE);
//
//    // two pair w/ aces
//    cards = new ArrayList<ICard>();
//    cards.add(new Card(ICard.CLUBS, ICard.KING));
//    cards.add(new Card(ICard.SPADES, ICard.ACE));
//    cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
//    cards.add(new Card(ICard.HEARTS, ICard.TEN));
//    cards.add(new Card(ICard.HEARTS, ICard.JACK));
//    cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
//    cards.add(new Card(ICard.CLUBS, ICard.ACE));
//    hand = new PaiGaoHand(cards);
//    System.out.println(hand + " " + hand.description());
//    assertTrue(hand.description().equals("Straight"));
//    low = hand.getLowHand();
//    assertTrue("Have two cards", low.size() == 2);
//    assertTrue("play two pair", low.get(0).getRank() == Card.QUEEN);
//    assertTrue("play two pair", low.get(1).getRank() == Card.QUEEN);
//    high = hand.getHighHand();
//    assertTrue("Have five cards", high.size() == 5);
//    assertTrue("Have pair Aces in high hand", Card.hasPair(high));
//    assertTrue("aces", high.get(4).getRank() == Card.ACE);
//    assertTrue("aces", high.get(3).getRank() == Card.ACE);
//
//    // two pair w/ joker-ace
//    cards = new ArrayList<ICard>();
//    cards.add(new Card(ICard.CLUBS, ICard.KING));
//    cards.add(new Card(ICard.SPADES, ICard.ACE));
//    cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
//    cards.add(new Card(ICard.HEARTS, ICard.TEN));
//    cards.add(new Card(ICard.HEARTS, ICard.JACK));
//    cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
//    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
//    hand = new PaiGaoHand(cards);
//    System.out.println(hand + " " + hand.description());
//    low = hand.getLowHand();
//    assertTrue(hand.description().equals("Straight"));
//    assertTrue("play two pair", low.get(0).getRank() == Card.QUEEN);
//    assertTrue("play two pair", low.get(1).getRank() == Card.QUEEN);
//    high = hand.getHighHand();
//    Collections.sort(high);
//    assertTrue("Have five cards", high.size() == 5);
//    assertTrue("aces", high.get(4).getRank() == Card.ACE);
//    assertTrue("aces", high.get(0).getRank() == Card.JOKER);
//
//    // two pair over 10
//    cards = new ArrayList<ICard>();
//    cards.add(new Card(ICard.CLUBS, ICard.KING));
//    cards.add(new Card(ICard.SPADES, ICard.ACE));
//    cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
//    cards.add(new Card(ICard.HEARTS, ICard.TEN));
//    cards.add(new Card(ICard.HEARTS, ICard.JACK));
//    cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
//    cards.add(new Card(ICard.SPADES, ICard.KING));
//    hand = new PaiGaoHand(cards);
//    System.out.println(hand + " " + hand.description());
//    assertTrue(hand.description().equals("Straight"));
//    low = hand.getLowHand();
//    assertTrue("play two pair", low.get(0).getRank() == Card.QUEEN);
//    assertTrue("play two pair", low.get(1).getRank() == Card.QUEEN);
//    high = hand.getHighHand();
//    Collections.sort(high);
//    assertTrue("Have five cards", high.size() == 5);
//    assertTrue("Has pair", Card.hasPair(high));
//    assertTrue("aces", high.get(3).getRank() == Card.KING);
//    assertTrue("aces", high.get(2).getRank() == Card.KING);
//
//    // two pair over 10
//    cards = new ArrayList<ICard>();
//    cards.add(new Card(ICard.CLUBS, ICard.THREE));
//    cards.add(new Card(ICard.SPADES, ICard.FIVE));
//    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
//    cards.add(new Card(ICard.HEARTS, ICard.TWO));
//    cards.add(new Card(ICard.HEARTS, ICard.ACE));
//    cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
//    cards.add(new Card(ICard.SPADES, ICard.THREE));
//    hand = new PaiGaoHand(cards);
//    System.out.println(hand + " " + hand.description());
//    assertTrue(hand.description().equals("Straight"));
//    low = hand.getLowHand();
//    assertTrue("play ace", low.get(0).getRank() == Card.ACE);
//    assertTrue("play two pair", low.get(1).getRank() == Card.FIVE);
//    high = hand.getHighHand();
//    Collections.sort(high);
//    assertTrue("Have five cards", high.size() == 5);
//    assertTrue("Has pair", Card.hasTwoPair(high));
//    assertTrue("aces", high.get(4).getRank() == Card.FOUR);
//    assertTrue("aces", high.get(2).getRank() == Card.THREE);
//
//    // With a six or seven card straight or flush put the highest hand possible
//    // in front while maintaining the straight or flush in back.
//    cards = new ArrayList<ICard>();
//    cards.add(new Card(ICard.CLUBS, ICard.THREE));
//    cards.add(new Card(ICard.SPADES, ICard.FIVE));
//    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
//    cards.add(new Card(ICard.HEARTS, ICard.SIX));
//    cards.add(new Card(ICard.HEARTS, ICard.SEVEN));
//    cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
//    cards.add(new Card(ICard.SPADES, ICard.NINE));
//    hand = new PaiGaoHand(cards);
//    System.out.println(hand + " " + hand.description());
//    assertTrue(hand.description().equals("Straight"));
//    low = hand.getLowHand();
//    assertTrue("play ace", low.get(0).getRank() == Card.EIGHT);
//    assertTrue("play two pair", low.get(1).getRank() == Card.NINE);
//    high = hand.getHighHand();
//    Collections.sort(high);
//    assertTrue("Have five cards", high.size() == 5);
//    assertTrue("aces", high.get(4).getRank() == Card.SEVEN);
//    assertTrue("aces", high.get(2).getRank() == Card.FIVE);
//
//    cards = new ArrayList<ICard>();
//    cards.add(new Card(ICard.CLUBS, ICard.THREE));
//    cards.add(new Card(ICard.SPADES, ICard.FIVE));
//    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
//    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
//    cards.add(new Card(ICard.HEARTS, ICard.SEVEN));
//    cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
//    cards.add(new Card(ICard.SPADES, ICard.NINE));
//    hand = new PaiGaoHand(cards);
//    System.out.println(hand + " " + hand.description());
//    assertTrue(hand.description().equals("Straight"));
//    low = hand.getLowHand();
//    assertTrue("play ace", low.get(0).getRank() == Card.EIGHT);
//    assertTrue("play two pair", low.get(1).getRank() == Card.NINE);
//    high = hand.getHighHand();
//    Collections.sort(high);
//    assertTrue("Have five cards", high.size() == 5);
//    assertTrue("seven ", high.get(4).getRank() == Card.SEVEN);
//    assertTrue("joker", high.get(0).getRank() == Card.JOKER);
//    assertTrue("joker", high.get(1).getRank() == Card.THREE);
//
  }

  @Test
  public void testRoyalFlush() {
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    cards.add(new Card(ICard.CLUBS, ICard.TEN));
    cards.add(new Card(ICard.CLUBS, ICard.JACK));
    cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
    cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.SPADES, ICard.NINE));
    hand = new PaiGowHand(cards);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Royal Flush"));
    low = hand.getLowHand();
    assertTrue("play eight", low.get(0).getRank() == Card.FIVE);
    assertTrue("play nine", low.get(1).getRank() == Card.NINE);
    high = hand.getHighHand();
    assertTrue("top ace", high.get(4).getRank() == Card.ACE);
    assertTrue("bottom 10", high.get(0).getRank() == Card.TEN);    

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    cards.add(new Card(ICard.CLUBS, ICard.TEN));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
    cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.SPADES, ICard.NINE));
    hand = new PaiGowHand(cards);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Royal Flush"));
    low = hand.getLowHand();
    assertTrue("play eight", low.get(0).getRank() == Card.FIVE);
    assertTrue("play nine", low.get(1).getRank() == Card.NINE);
    high = hand.getHighHand();
    assertTrue("top ace", high.get(4).getRank() == Card.ACE);
    assertTrue("bottom 10", high.get(1).getRank() == Card.TEN);    
}
  
  @Test
  public void testSevenCardStraightFlush() {
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.CLUBS, ICard.SIX));
    cards.add(new Card(ICard.CLUBS, ICard.FIVE));
    cards.add(new Card(ICard.CLUBS, ICard.FOUR));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
    cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
    cards.add(new Card(ICard.CLUBS, ICard.NINE));
    hand = new PaiGowHand(cards);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Straight Flush"));
    low = hand.getLowHand();
    assertTrue("play eight", low.get(0).getRank() == Card.EIGHT);
    assertTrue("play nine", low.get(1).getRank() == Card.NINE);
    high = hand.getHighHand();
    Collections.sort(high);
    assertTrue("Have five cards", high.size() == 5);
    assertTrue("seven ", high.get(4).getRank() == Card.SEVEN);
    assertTrue("joker", high.get(0).getRank() == Card.JOKER);
    assertTrue("four", high.get(1).getRank() == Card.FOUR);
    
  }

  /**
   * Various hands.
   */
  // @Test
  // public void testHands() {
  // // [H2, D3, S4, C4, H4, S5, HA]
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.SPADES, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // assertSame("always true", cards.size(), 7);
  // }

  /**
   * Various four of a kind hands.
   */
  // @Test
  // public void testFourOfAKind() {
  // // Seat 3 has [S9, H9][C9, D9, S9, CK, C3, H3] Four of a kind [C3, H3, C9,
  // D9, S9, H9, CK]
  // ArrayList<ICard> cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.THREE));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.HEARTS, ICard.NINE));
  // cards.add(new Card(ICard.SPADES, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // PaiGaoHand hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.THREE));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.HEARTS, ICard.NINE));
  // cards.add(new Card(ICard.SPADES, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  //
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.THREE));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.ACE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // // [D4, S4, C4, H4, D9, CQ, SA]
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.SPADES, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // // [C3, C4, D4, S4, H4, H10, DQ]
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.TEN));
  // cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.SPADES, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.THREE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // assertSame("always true", cards.size(), 7);
  // }

  // @Test
  // public void testJoker() {
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.TEN));
  // cards.add(new Card(ICard.HEARTS, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // System.out.println(hand + " " + hand.description());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // assertTrue(hand.hasThreeOfAKind());
  // System.out.println(hand + " " + hand.description());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.ACE));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // assertTrue(hand.hasThreeOfAKind());
  // assertTrue(hand.hasFourOfAKind());
  // System.out.println(hand + " " + hand.description());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.ACE));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // assertTrue(hand.hasThreeOfAKind());
  // assertTrue(hand.hasFourOfAKind());
  // System.out.println(hand + " " + hand.description());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.ACE));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // assertTrue(hand.hasThreeOfAKind());
  // assertTrue(hand.hasFourOfAKind());
  // System.out.println(hand + " " + hand.description());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.SPADES, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.SPADES, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasFlush());
  // assertTrue(hand.hasThreeOfAKind());
  // System.out.println(hand + " " + hand.description());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.TWO));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.CLUBS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
  // cards.add(new Card(ICard.SPADES, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasStraight());
  // System.out.println(hand + " " + hand.description());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.CLUBS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
  // cards.add(new Card(ICard.SPADES, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasStraight());
  // System.out.println(hand + " " + hand.description());
  // // [ SA, H2, S5, H10, HJ, CQ, DK]
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.HEARTS, ICard.TWO));
  // cards.add(new Card(ICard.SPADES, ICard.FIVE));
  // cards.add(new Card(ICard.HEARTS, ICard.TEN));
  // cards.add(new Card(ICard.HEARTS, ICard.JACK));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // boolean straightp = hand.hasStraight();
  // assertTrue(straightp);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.HEARTS, ICard.TWO));
  // cards.add(new Card(ICard.SPADES, ICard.FIVE));
  // cards.add(new Card(ICard.HEARTS, ICard.TEN));
  // cards.add(new Card(ICard.HEARTS, ICard.JACK));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // straightp = hand.hasStraight();
  // assertTrue(straightp);
  //
  // }

  /**
   * Test method for
   * {@link org.cam.card.PaiGowHand#PaiGaoHand(org.cam.card.Deck)}.
   */
  // @Test
  // public void testPaiGaoHand() {
  // PaiGaoHand hand = new PaiGaoHand(new PaiGaoDeck());
  // System.out.println(hand.cards);
  // System.out.print(hand);
  // if (hand.isPaiGao()) {
  // if (hand.isAceHighPaiGao()) {
  // System.out.println(" is Ace high PaiGao");
  // }
  // if (hand.isKingHighPaiGao()) {
  // System.out.println(" is King high PaiGao");
  //
  // }
  // if (hand.isQueenHighPaiGao()) {
  // System.out.println(" is Queen high PaiGao");
  //
  // }
  // if (hand.isJackHighPaiGao()) {
  // System.out.println(" is Jack high PaiGao");
  //
  // }
  // if (hand.isTenHighPaiGao()) {
  // System.out.println(" is Ten high PaiGao");
  //
  // }
  // if (hand.isNineHighPaiGao()) {
  // System.out.println(" is Nine high PaiGao");
  //
  // }
  // }
  // if (hand.hasTwoPair()) {
  // System.out.println(" has two pair");
  // } else if (hand.hasPair()) {
  // System.out.println(" has pair");
  // }
  // if (hand.hasThreeOfAKind()) {
  // System.out.println(" has three of a kind");
  // }
  // if (hand.hasFlush()) {
  // System.out.println(" has flush");
  // }
  // // if (hand.hasFullHouse()) {
  // // System.out.println(" has full house");
  // // }
  // if (hand.hasStraight()) {
  // System.out.println(" has straight");
  // }
  // if (hand.hasStraightFlush()) {
  // System.out.println(" has straight flush");
  // }
  // // fail("Not yet implemented");
  // }

  // @Test
  // public void testFullHouse() {
  // // [ D4, D9, H9, S9, CJ, DQ, HK]
  // ArrayList<ICard> cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.NINE));
  // cards.add(new Card(ICard.HEARTS, ICard.NINE));
  // cards.add(new Card(ICard.SPADES, ICard.NINE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
  // cards.add(new Card(ICard.CLUBS, ICard.JACK));
  // cards.add(new Card(ICard.HEARTS, ICard.KING));
  // PaiGaoHand hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // assertTrue(hand.hasThreeOfAKind());
  // assertFalse(hand.hasFullHouse());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.NINE));
  // cards.add(new Card(ICard.HEARTS, ICard.NINE));
  // cards.add(new Card(ICard.SPADES, ICard.NINE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.HEARTS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // assertTrue(hand.hasThreeOfAKind());
  // assertTrue(hand.hasFullHouse());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.NINE));
  // cards.add(new Card(ICard.HEARTS, ICard.NINE));
  // cards.add(new Card(ICard.SPADES, ICard.NINE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.HEARTS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // assertTrue(hand.hasThreeOfAKind());
  // assertTrue(hand.hasFullHouse());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.NINE));
  // cards.add(new Card(ICard.HEARTS, ICard.NINE));
  // cards.add(new Card(ICard.SPADES, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.HEARTS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasPair());
  // assertTrue(hand.hasThreeOfAKind());
  // assertTrue(hand.hasFullHouse());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.SPADES, ICard.FIVE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.HEARTS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // assertTrue(hand.hasStraight());
  // }
  //
  // @Test
  // public void testHouseWay() {
  // // [DK, SK, DJ, CJ, S10, C7, C2]
  // ArrayList<ICard> cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.SPADES, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.JACK));
  // cards.add(new Card(ICard.CLUBS, ICard.JACK));
  // cards.add(new Card(ICard.SPADES, ICard.TEN));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.TWO));
  // PaiGaoHand hand = new PaiGaoHand(cards);
  // System.out.println(hand);
  // // H7, D7, C7, DQ, C10, D2, C2
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
  // cards.add(new Card(ICard.HEARTS, ICard.SEVEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.TEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.CLUBS, ICard.TWO));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand);
  // assertTrue(hand.hasStraight());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand);
  // assertTrue(hand.hasStraight());
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.HEARTS, ICard.TWO));
  // cards.add(new Card(ICard.SPADES, ICard.FIVE));
  // cards.add(new Card(ICard.HEARTS, ICard.TEN));
  // cards.add(new Card(ICard.HEARTS, ICard.JACK));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // System.out.println(hand + " " + hand.description());
  // assertTrue(hand.hasStraight());
  // }

  // @Test
  // public void testStraight() {
  // ArrayList<ICard> cards = new ArrayList<ICard>();
  // boolean foo = false;
  // PaiGaoHand hand = null;
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.HEARTS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.SPADES, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // hand = new PaiGaoHand(cards);
  // foo = hand.hasStraight();
  // assertFalse(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.SPADES, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.SPADES, ICard.ACE));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.TEN));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // // [SK][DA, H2, C3, C4, H5, DA] Straight [H2, C3, C4, H5, CK, SK, DA]
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.SPADES, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.SPADES, ICard.KING));
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // // cards = new ArrayList<ICard>();
  // // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // // cards.add(new Card(ICard.SPADES, ICard.FOUR));
  // // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // // hand = new PaiGaoHand(cards);
  // // System.out.println(cards + " => " + hand);
  // // foo = hand.hasStraight();
  // // assertTrue(foo);
  // // cards = new ArrayList<ICard>();
  // // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // // cards.add(new Card(ICard.SPADES, ICard.FOUR));
  // // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // // cards.add(new Card(ICard.HEARTS, ICard.JACK));
  // // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // // hand = new PaiGaoHand(cards);
  // // System.out.println(cards + " => " + hand);
  // // foo = hand.hasStraight();
  // // assertFalse(foo);
  // }
  //
  // @Test
  // public void testFlush() {
  // // TODO fix houseway play of flush
  // ArrayList<ICard> cards = new ArrayList<ICard>();
  // boolean foo = false;
  // PaiGaoHand hand = null;
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // hand = new PaiGaoHand(cards);
  // // System.out.println(cards + " => " + hand);
  // foo = hand.hasFlush();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // hand = new PaiGaoHand(cards);
  // // System.out.println(cards + " => " + hand);
  // foo = hand.hasFlush();
  // assertTrue(foo);
  //
  // }
  //
  // @Test
  // public void testBeats() {
  // ArrayList<ICard> cards = new ArrayList<ICard>();
  // boolean foo = false;
  // PaiGaoHand handOne = null;
  // PaiGaoHand handTwo = null;
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.HEARTS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.SPADES, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // handOne = new PaiGaoHand(cards);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // handTwo = new PaiGaoHand(cards);
  // assertEquals(1, handTwo.beats(handOne));
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.HEARTS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.SPADES, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // handOne = new PaiGaoHand(cards);
  // assertEquals(0, handTwo.beats(handOne));
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
  // cards.add(new Card(ICard.HEARTS, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.SPADES, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // handOne = new PaiGaoHand(cards);
  // assertEquals(0, handTwo.beats(handOne));
  //
  // // Dealer has [CK, S10][H8, C8, S5, S3, D2] Pair
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.CLUBS, ICard.KING));
  // cards.add(new Card(ICard.SPADES, ICard.TEN));
  // cards.add(new Card(ICard.HEARTS, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
  // cards.add(new Card(ICard.SPADES, ICard.FIVE));
  // cards.add(new Card(ICard.SPADES, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // handOne = new PaiGaoHand(cards);
  // // Seat 3 has [S2, H2][CQ, SQ, DK, D10, H9] Two Pair
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.SPADES, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.TWO));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.SPADES, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.DIAMONDS, ICard.TEN));
  // cards.add(new Card(ICard.HEARTS, ICard.NINE));
  // handTwo = new PaiGaoHand(cards);
  // // two beats one.
  // assertEquals(1, handTwo.beats(handOne));
  //
  // }
  // @Test
  // public void testHands() {
  // //[D4, H5, C5, H7, D8, H9, HQ]
  // ArrayList<ICard> cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.HEARTS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.SPADES, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // PaiGaoHand hand = new PaiGaoHand(cards);
  // boolean foo = hand.hasStraight();
  // assertFalse(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.HEARTS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.TWO));
  // cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
  // cards.add(new Card(ICard.SPADES, ICard.THREE));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.CLUBS, ICard.THREE));
  // hand = new PaiGaoHand(cards);
  // System.out.println(cards + " => " + hand);
  // foo = hand.hasStraight();
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.THREE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FOUR));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.THREE));
  // hand = new PaiGaoHand(cards);
  // foo = hand.hasStraight();
  // System.out.println(cards + " => " + hand);
  // assertFalse(foo);
  // // [H2, D4, H10, SJ, DQ, CK, HA] played
  // [H2, D4, SJ, D4, H2][H10, SJ, DQ, CK, HA, H10, SJ, DQ, CK, HA]
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.TEN));
  // cards.add(new Card(ICard.CLUBS, ICard.JACK));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // foo = hand.hasStraight();
  // System.out.println(cards + " => " + hand);
  // assertTrue(foo);
  // // [H2, S3, D4, D5, CQ, HA, CA]
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.CLUBS, ICard.ACE));
  // hand = new PaiGaoHand(cards);
  // foo = hand.hasStraight();
  // System.out.println(cards + " => " + hand);
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // foo = hand.hasStraight();
  // System.out.println(cards + " => " + hand);
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.HEARTS, ICard.FOUR));
  // cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.FIVE));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // foo = hand.hasStraight();
  // System.out.println(cards + " => " + hand);
  // assertTrue(foo);
  // cards = new ArrayList<ICard>();
  // cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
  // cards.add(new Card(ICard.HEARTS, ICard.FIVE));
  // cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
  // cards.add(new Card(ICard.CLUBS, ICard.NINE));
  // cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
  // cards.add(new Card(ICard.DIAMONDS, ICard.KING));
  // cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
  // hand = new PaiGaoHand(cards);
  // foo = hand.hasStraight();
  // System.out.println(hand.cards + " => " + hand);
  // assertTrue(foo);
  // }
  // [H10, C10][S10, D10, H10, H6, D6, D5] Four of a kind [D5, H6, D6, S10, D10,
  // H10, C10]
  // [D6, S6][C6, H6, D6, SQ, C2, D2] Four of a kind [C2, D2, C6, H6, D6, S6,
  // SQ]
  
  @Test
  public void testProblemHands() {
    // [D2, H3, D3, S4, H4, DA, CA] three pair
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.FOUR));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    hand = new PaiGowHand(cards);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Three Pair"));
    low = hand.getLowHand();
    // [D2, C2, S3, H3, SK, SA, DA] three pair
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
    cards.add(new Card(ICard.CLUBS, ICard.TWO));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.KING));
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    hand = new PaiGowHand(cards);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Three Pair"));
    // [S2, C2, H3, S4, C4, HA, CA]
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.CLUBS, ICard.TWO));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.FOUR));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    hand = new PaiGowHand(cards);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Three Pair"));
    low = hand.getLowHand();
  }
}
