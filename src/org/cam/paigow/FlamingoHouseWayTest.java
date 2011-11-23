/**
 *   FlamingoHouseWayTest.java - Copyright (C) Cam Moore 2011.
 * 
 *   This file is part of PaiGowPoker.
 *
 *   PaiGowPoker is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License.
 *
 *   PaiGowPoker is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with PaiGowPoker.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cam.paigow;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.cam.card.Card;
import org.cam.card.ICard;
import org.cam.card.PaiGowHand;
import org.junit.Test;

/**
 * @author Cam Moore
 * 
 */
public class FlamingoHouseWayTest {

  private PaiGowHand hand;
  private ArrayList<ICard> cards;
  private List<ICard> low;
  private List<ICard> high;
  private FlamingoHouseWay house = new FlamingoHouseWay();

  @Test
  public void testPaiGow() {
    // [D2, H3, D4, S5, H7, D8, C9] nine high
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
    cards.add(new Card(ICard.SPADES, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.SEVEN));
    cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
    cards.add(new Card(ICard.CLUBS, ICard.NINE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("9 high pai gow"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.EIGHT);
    assertTrue(low.get(1).getRank() == ICard.SEVEN);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.NINE);
    // [H3, D4, S5, H7, D8, D9, CA] ace high
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.NINE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
    cards.add(new Card(ICard.SPADES, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.SEVEN));
    cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Ace high pai gow"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.NINE);
    assertTrue(low.get(1).getRank() == ICard.EIGHT);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.ACE);

  }

  @Test
  public void testFiveAces() {
    // [DA, HA, D4, SA, H7, JK, CA] five aces
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.SEVEN));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Five Aces"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.JOKER);
    assertTrue(low.get(1).getRank() == ICard.ACE);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.ACE);
    // [DA, HA, DK, SA, HK, JK, CA] five aces
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.KING));
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.KING));
    cards.add(new Card(ICard.PAI_GAO_JOKER, ICard.JOKER));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Five Aces"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.KING);
    assertTrue(low.get(1).getRank() == ICard.KING);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.JOKER);
  }

  @Test
  public void testFourOfAKind() {
    // * 2 through 6: Always keep together.
    // [D3, H3, DK, S3, HQ, H10, C3] five aces
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.KING));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
    cards.add(new Card(ICard.HEARTS, ICard.TEN));
    cards.add(new Card(ICard.CLUBS, ICard.THREE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Four of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.KING);
    assertTrue(low.get(1).getRank() == ICard.QUEEN);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.THREE);

    // [D3, H3, D10, S3, HQ, H10, C3] five aces
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.TEN));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
    cards.add(new Card(ICard.HEARTS, ICard.TEN));
    cards.add(new Card(ICard.CLUBS, ICard.THREE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Four of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.TEN);
    assertTrue(low.get(1).getRank() == ICard.TEN);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.THREE);
    // * 7 through 10: Split unless an ace and a face card or better can be
    // * played in front.
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
    cards.add(new Card(ICard.HEARTS, ICard.EIGHT));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.EIGHT));
    cards.add(new Card(ICard.HEARTS, ICard.TWO));
    cards.add(new Card(ICard.HEARTS, ICard.TEN));
    cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Four of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.EIGHT);
    assertTrue(low.get(1).getRank() == ICard.EIGHT);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.EIGHT);

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.EIGHT));
    cards.add(new Card(ICard.HEARTS, ICard.EIGHT));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.EIGHT));
    cards.add(new Card(ICard.HEARTS, ICard.JACK));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.CLUBS, ICard.EIGHT));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Four of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.ACE);
    assertTrue(low.get(1).getRank() == ICard.JACK);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.EIGHT);
    // * Jack through king: Split unless hand also contains a pair of 10's
    // * or higher.
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
    cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.QUEEN));
    cards.add(new Card(ICard.HEARTS, ICard.TWO));
    cards.add(new Card(ICard.HEARTS, ICard.TEN));
    cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Four of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.QUEEN);
    assertTrue(low.get(1).getRank() == ICard.QUEEN);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.QUEEN);

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
    cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
    cards.add(new Card(ICard.DIAMONDS, ICard.JACK));
    cards.add(new Card(ICard.SPADES, ICard.QUEEN));
    cards.add(new Card(ICard.HEARTS, ICard.JACK));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.CLUBS, ICard.QUEEN));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Four of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.JACK);
    assertTrue(low.get(0).getSuit() == ICard.DIAMONDS);
    assertTrue(low.get(1).getRank() == ICard.JACK);
    assertTrue(low.get(1).getSuit() == ICard.HEARTS);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.QUEEN);

    // * Aces: Split unless a pair of 7's or higher can be played in front.
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.TEN));
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.JACK));
    cards.add(new Card(ICard.HEARTS, ICard.TWO));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Four of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.ACE);
    assertTrue(low.get(1).getRank() == ICard.ACE);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.ACE);
    assertTrue(high.get(1).getRank() == ICard.ACE);

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.SEVEN));
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.JACK));
    cards.add(new Card(ICard.HEARTS, ICard.SEVEN));
    cards.add(new Card(ICard.CLUBS, ICard.ACE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Four of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.SEVEN);
    assertTrue(low.get(1).getRank() == ICard.SEVEN);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.ACE);
    assertTrue(high.get(1).getRank() == ICard.ACE);
  }

  @Test
  public void testFullHouse() {
    // Full house: Split except with pair of 2's and an ace/king can be played
    // in front.
    //
    // Full house with three of a kind and two pairs: Play the highest pair in
    // front.
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.KING));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.QUEEN));
    cards.add(new Card(ICard.HEARTS, ICard.TEN));
    cards.add(new Card(ICard.CLUBS, ICard.TEN));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Full House"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.TEN);
    assertTrue(low.get(1).getRank() == ICard.TEN);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.THREE);
    assertTrue(high.get(1).getRank() == ICard.THREE);
    assertTrue(high.get(2).getRank() == ICard.THREE);

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.KING));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.TWO));
    cards.add(new Card(ICard.CLUBS, ICard.TWO));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Full House"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.ACE);
    assertTrue(low.get(1).getRank() == ICard.KING);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.TWO);
    assertTrue(high.get(1).getRank() == ICard.TWO);
    assertTrue(high.get(2).getRank() == ICard.THREE);
    assertTrue(high.get(3).getRank() == ICard.THREE);
    assertTrue(high.get(4).getRank() == ICard.THREE);

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.FOUR));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.HEARTS, ICard.TWO));
    cards.add(new Card(ICard.CLUBS, ICard.TWO));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Full House"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.FOUR);
    assertTrue(low.get(1).getRank() == ICard.FOUR);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.THREE);
    assertTrue(high.get(1).getRank() == ICard.THREE);
    assertTrue(high.get(2).getRank() == ICard.THREE);
    assertTrue(high.get(3).getRank() == ICard.TWO);
    assertTrue(high.get(4).getRank() == ICard.TWO);
  }
  
  @Test
  public void testThreeOfAKind() {
    // Three of a kind: Play three of a kind in back except break up three
    // aces.
    //
    // Three of a kind twice: Play higher pair in front.
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.HEARTS, ICard.FIVE));
    cards.add(new Card(ICard.CLUBS, ICard.TWO));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Three of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.SIX);
    assertTrue(low.get(1).getRank() == ICard.FIVE);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.THREE);
    assertTrue(high.get(1).getRank() == ICard.THREE);
    assertTrue(high.get(2).getRank() == ICard.THREE);
    assertTrue(high.get(3).getRank() == ICard.FOUR);
    assertTrue(high.get(4).getRank() == ICard.TWO);

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.HEARTS, ICard.FIVE));
    cards.add(new Card(ICard.CLUBS, ICard.TWO));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Three of a Kind"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.ACE);
    assertTrue(low.get(1).getRank() == ICard.SIX);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.ACE);
    assertTrue(high.get(1).getRank() == ICard.ACE);
    assertTrue(high.get(2).getRank() == ICard.FIVE);
    assertTrue(high.get(3).getRank() == ICard.FOUR);
    assertTrue(high.get(4).getRank() == ICard.TWO);

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.HEARTS, ICard.SIX));
    cards.add(new Card(ICard.CLUBS, ICard.SIX));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Full House"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.SIX);
    assertTrue(low.get(1).getRank() == ICard.SIX);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.THREE);
    assertTrue(high.get(1).getRank() == ICard.THREE);
    assertTrue(high.get(2).getRank() == ICard.THREE);
    assertTrue(high.get(3).getRank() == ICard.SIX);
    assertTrue(high.get(4).getRank() == ICard.FOUR);

  }
  
  @Test
  public void testTwoPair() {
    // Two pair: Split the two pair except for the following three situations
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.JACK));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.JACK));
    cards.add(new Card(ICard.HEARTS, ICard.NINE));
    cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Two Pair"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.THREE);
    assertTrue(low.get(1).getRank() == ICard.THREE);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.JACK);
    assertTrue(high.get(1).getRank() == ICard.JACK);

    // No split Both pairs are 6's or less.
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.THREE));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.NINE));
    cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Two Pair"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.NINE);
    assertTrue(low.get(1).getRank() == ICard.SEVEN);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.FIVE);
    assertTrue(high.get(1).getRank() == ICard.FIVE);
    
    // No split Both pairs are 10's or less plus ace singleton.
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.TEN));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.TEN));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.NINE));
    cards.add(new Card(ICard.CLUBS, ICard.FIVE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Two Pair"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.ACE);
    assertTrue(low.get(1).getRank() == ICard.NINE);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.TEN);
    assertTrue(high.get(1).getRank() == ICard.TEN);
    assertTrue(high.get(2).getRank() == ICard.FIVE);

    // One pair of face cards, one pair of 5's or less, and an ace singleton.
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
    cards.add(new Card(ICard.HEARTS, ICard.JACK));
    cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
    cards.add(new Card(ICard.SPADES, ICard.JACK));
    cards.add(new Card(ICard.HEARTS, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.NINE));
    cards.add(new Card(ICard.CLUBS, ICard.FIVE));
    hand = new PaiGowHand(cards);
    house.setHand(hand);
    System.out.println(hand + " " + hand.description());
    assertTrue(hand.description().equals("Two Pair"));
    low = hand.getLowHand();
    assertTrue(low.size() == 2);
    assertTrue(low.get(0).getRank() == ICard.ACE);
    assertTrue(low.get(1).getRank() == ICard.NINE);
    high = hand.getHighHand();
    assertTrue(high.size() == 5);
    assertTrue(high.get(0).getRank() == ICard.JACK);
    assertTrue(high.get(1).getRank() == ICard.JACK);
    assertTrue(high.get(2).getRank() == ICard.FIVE);
}
}
