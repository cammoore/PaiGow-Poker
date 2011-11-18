/**
 * 
 */
package org.cam.card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Test;


/**
 * @author Cam Moore
 *
 */
public class PaiGowFortunePayoutTest {

  /**
   * tests the payout.
   */
  @Test
  public void testPayout() {
    ArrayList<ICard> cards = new ArrayList<ICard>();
    //[ DA, DJ, H10, D9, D8, S7, S5] Straight payed -1
    cards.add(new Card(ICard.SPADES, ICard.FIVE));
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.JACK));
    cards.add(new Card(ICard.SPADES, ICard.SEVEN));
    cards.add(new Card(ICard.HEARTS, ICard.TEN));
    cards.add(new Card(ICard.DIAMONDS, ICard.NINE));
    cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
    PaiGowHand hand = new PaiGowHand(cards);
//    System.out.println(hand);
    assertTrue("has straight", hand.hasStraight());
    int payout = PaiGowFortunePayout.payout(hand, 1);
    assertEquals("pays 2", 2, payout);

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.KING));
    cards.add(new Card(ICard.SPADES, ICard.KING));
    hand = new PaiGowHand(cards);
//    System.out.println(hand);
    assertTrue("five aces", hand.hasFiveAces());
  }
}
