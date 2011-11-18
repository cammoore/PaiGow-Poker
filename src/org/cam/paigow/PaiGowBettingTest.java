/**
 * 
 */
package org.cam.paigow;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.cam.card.Card;
import org.cam.card.HandResult;
import org.cam.card.ICard;
import org.cam.card.PaiGowHand;
import org.junit.Test;

/**
 * @author Cam Moore
 * 
 */
public class PaiGowBettingTest {

  private PaiGowHand hand;
  private ArrayList<ICard> cards;
  private List<ICard> low;
  private List<ICard> high;

  /**
   * Test method for
   * {@link org.cam.paigow.PaiGowBetting#PaiGaoBetting(java.lang.Integer, java.lang.Integer)}
   * .
   */
  @Test
  public void testPaiGaoBetting() {
    PaiGowBetting b = new PaiGowBetting(100, 10);
    for (BettingScheme scheme : BettingScheme.values()) {
      assertTrue("Wrong stake", (100.0 - (Double) b.getStake(scheme)) < .00001);
      assertTrue("Wrong bet", (10.0 - b.getCurrentBet(scheme)) < 0.00001);
    }
  }

  /**
   * Test method for
   * {@link org.cam.paigow.PaiGowBetting#update(org.cam.paigow.PaiGowHandResult)}
   * .
   */
  @Test
  public void testUpdate() {
    // five aces
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.CLUBS, ICard.KING));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    hand = new PaiGowHand(cards);
    PaiGowBetting b = new PaiGowBetting(100, 10);
    b.update(new PaiGowHandResult(HandResult.WIN, hand));
    for (BettingScheme scheme : BettingScheme.values()) {
      assertTrue("Wrong stake " + scheme, Math.abs(109.5 - (Double) b.getStake(scheme)) < .00001);
      assertTrue("Wrong bet", Math.abs(10.0 - b.getCurrentBet(scheme)) < 0.00001);
    }
    
  }

}
