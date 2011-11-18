/**
 * 
 */
package org.cam.card;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Cam Moore
 *
 */
public class StraightAlgoTest {
  
  private PaiGowHand hand;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.SEVEN));
    hand = new PaiGowHand(cards);
  }

  @Test 
  public void testHasStraight() {
    assertTrue(StraightAlgo.hasStraight(hand));
  }
  @Test
  public void testHasStraight2() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasStraight(hand));
  }
  @Test
  public void testHasStraight3() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.KING));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasStraight(hand));
  }
  @Test
  public void testHasStraight4() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasStraight(hand));
  }
  @Test
  public void testHasStraight5() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.FOUR));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasStraight(hand));
  }

  @Test 
  public void testIsSimpleSevenCardStraight() {
    assertTrue(StraightAlgo.isSimpleSevenCardStraight(hand));
  }
  @Test
  public void testHasSimpleFiveCardStraight() {
    assertTrue(StraightAlgo.hasSimpleFiveCardStraight(hand));
  }
  @Test
  public void testHasSimpleFiveCardStraight2() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasSimpleFiveCardStraight(hand));
  }
  @Test
  public void testJoker1() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.KING));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasFiveCardStraightWithJoker(hand));
  }
  @Test
  public void testJoker2() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasFiveCardStraightWithJoker(hand));
  }
  @Test
  public void testJoker3() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.CLUBS, ICard.FOUR));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasFiveCardStraightWithJoker(hand));
  }

  @Test
  public void testJoker4() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.KING));
    cards.add(new Card(ICard.CLUBS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
    cards.add(new Card(ICard.SPADES, ICard.EIGHT));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    hand = new PaiGowHand(cards);
    assertTrue(StraightAlgo.hasFiveCardStraightWithJoker(hand));
  }
}
