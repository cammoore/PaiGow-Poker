/**
 *   FlamingoHouseWay.java - Copyright (C) Cam Moore 2011.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.cam.card.Card;
import org.cam.card.Hand;
import org.cam.card.ICard;
import org.cam.card.IHouseWay;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 * 
 */
public class FlamingoHouseWay implements IHouseWay {
  private List<ICard> cards;
  private List<ICard> lowHand;
  private List<ICard> highHand;

  public FlamingoHouseWay() {
    this.cards = new ArrayList<ICard>();
    this.lowHand = new ArrayList<ICard>();
    this.highHand = new ArrayList<ICard>();
  }

  /**
   * Sets a 5 aces hand. Five aces: Split unless pair of kings can be played in
   * front.
   * 
   * @param hand the PaiGowHand to set.
   * @see org.cam.card.IHouseWay#playFiveAces(org.cam.card.PaiGowHand)
   */
  @Override
  public void playFiveAces(PaiGowHand hand) {
    // Five aces: Split unless pair of kings can be played in front.
    this.cards.clear();
    for (ICard c : hand.getCards()) {
      cards.add(c);
    }
    Collections.reverse(cards);
    lowHand.clear();
    highHand.clear();
    // know we have a joker
    if (cards.get(4).getRank() == ICard.KING && cards.get(5).getRank() == ICard.KING) {
      // keep together and play kings in front.
      lowHand.add(cards.get(4));
      lowHand.add(cards.get(5));
      highHand.add(cards.get(6)); // joker
      highHand.add(cards.get(0));
      highHand.add(cards.get(1));
      highHand.add(cards.get(2));
      highHand.add(cards.get(3));
    }
    else {
      // split the aces
      lowHand.add(cards.get(6)); // joker
      lowHand.add(cards.get(0));
      highHand.add(cards.get(1));
      highHand.add(cards.get(2));
      highHand.add(cards.get(3));
      highHand.add(cards.get(4));
      highHand.add(cards.get(5));
    }
    hand.setLowHand(lowHand);
    hand.setHighHand(highHand);
  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playFlushOrStraight(org.cam.card.PaiGowHand)
   */
  @Override
  public void playFlushOrStraight(PaiGowHand hand) {
    // TODO Auto-generated method stub

  }

  /**
   * Four of a kind: Play according to the rank of the four of a kind:
   * 
   * 2 through 6: Always keep together. 
   * 7 through 10: Split unless an ace and a face card or better can be 
   * played in front. 
   * Jack through king: Split unless hand also contains a pair of 10's 
   * or higher. 
   * Aces: Split unless a pair of 7's or higher can be played in front.
   * 
   * @param hand The PaiGowHand to set.
   * @see org.cam.card.IHouseWay#playFourOfAKind(org.cam.card.PaiGowHand)
   */
  @Override
  public void playFourOfAKind(PaiGowHand hand) {
    this.cards.clear();
    for (ICard c : hand.getCards()) {
      cards.add(c);
    }
    Collections.reverse(cards);
    lowHand.clear();
    highHand.clear();
    boolean jokerp = hand.hasJoker();
    int startIndex = -1;
    int rank = -1;

    for (int i = 0; i < cards.size() - 3; i++) {
      if (cards.get(i).getRank() == cards.get(i + 1).getRank()
          && cards.get(i + 1).getRank() == cards.get(i + 2).getRank()
          && cards.get(i + 2).getRank() == cards.get(i + 3).getRank()) {
        startIndex = i;
        rank = cards.get(i).getRank();
        break;
      }
    }
    if (startIndex == -1 && jokerp) {
      // aces w/ joker?
      for (int i = 0; i < cards.size() - 3; i++) {
        if (cards.get(i).getRank() == cards.get(i + 1).getRank()
            && cards.get(i + 1).getRank() == cards.get(i + 2).getRank()) {
          startIndex = i;
          rank = cards.get(i).getRank();
          // should check the rank to ensure it is an ACE
          if (rank != Card.ACE) {
            System.err.println("Wrong rank can't play 4 of a kind.");
          }
          break;
        }
      }
    }
    // check for pair
    boolean pair = false;
    int pairRank = -1;
    int pairIndex = -1;
    if (jokerp) {
      // check 3,4,5 joker is at end
      if (cards.get(3).getRank() == cards.get(4).getRank()) {
        pair = true;
        pairRank = cards.get(3).getRank();
        pairIndex = 3;
      }
      else if (cards.get(4).getRank() == cards.get(5).getRank()) {
        pair = true;
        pairRank = cards.get(4).getRank();
        pairIndex = 4;
      }
    }
    else {
      switch (startIndex) {
      case 0: // check last 3
        if (cards.get(4).getRank() == cards.get(5).getRank()) {
          pair = true;
          pairRank = cards.get(4).getRank();
          pairIndex = 4;
        }
        else if (cards.get(5).getRank() == cards.get(6).getRank()) {
          pair = true;
          pairRank = cards.get(5).getRank();
          pairIndex = 5;
        }
        break;
      case 1: // check last 2
        if (cards.get(5).getRank() == cards.get(6).getRank()) {
          pair = true;
          pairRank = cards.get(5).getRank();
          pairIndex = 5;
        }
        break;
      case 3: // check first 3
        if (cards.get(1).getRank() == cards.get(2).getRank()) {
          pair = true;
          pairRank = cards.get(1).getRank();
          pairIndex = 1;
        }
        break;
      case 2: // check first 2
        if (cards.get(0).getRank() == cards.get(1).getRank()) {
          pair = true;
          pairRank = cards.get(0).getRank();
          pairIndex = 0;
        }
        break;
      default:
        // shouldn't get here
      }
    }
    if (pair) {
      // System.out.println("Have 4oK of " + rank + " and  a Pair of " +
      // pairRank);
    }
    int lowCount = 0;
    int tempIndex;
    if (rank < ICard.SEVEN) {
      // * 2 through 6: Always keep together.
      highHand.add(cards.get(startIndex));
      highHand.add(cards.get(startIndex + 1));
      highHand.add(cards.get(startIndex + 1));
      highHand.add(cards.get(startIndex + 1));
      if (pair) {
        lowHand.add(cards.get(pairIndex));
        lowHand.add(cards.get(pairIndex + 1));
        // find the last card to add to highHand
        tempIndex = 0;
        if (startIndex < pairIndex) {
          // 0, 4, 6
          if (tempIndex < startIndex) {
            highHand.add(cards.get(tempIndex));
          }
          else {
            tempIndex = 4;
            if (tempIndex < pairIndex) {
              highHand.add(cards.get(tempIndex));
            }
            else {
              highHand.add(cards.get(6));
            }
          }
        }
        else {
          // 0, 2, 6
          if (tempIndex < pairIndex) {
            highHand.add(cards.get(tempIndex));
          }
          else {
            tempIndex = 2;
            if (tempIndex < startIndex) {
              highHand.add(cards.get(tempIndex));
            }
            else {
              highHand.add(cards.get(6));
            }
          }
        }
      }
      else {
        // need to add two highest cards
        for (int i = 0; i < startIndex; i++) {
          if (lowCount < 2) {
            lowHand.add(cards.get(i));
            lowCount++;
            tempIndex = i;
          }
          else {
            highHand.add(cards.get(i));
          }
        }
        for (int i = startIndex + 4; i < cards.size(); i++) {
          if (lowCount < 2) {
            lowHand.add(cards.get(i));
            lowCount++;
            tempIndex = i;
          }
          else {
            highHand.add(cards.get(i));
          }
        }
      }
    }
    else if (rank < ICard.JACK) {
      // * 7 through 10: Split unless an ace and a face card or better can be
      // played in front.
      if (pair) {
        highHand.add(cards.get(startIndex));
        highHand.add(cards.get(startIndex + 1));
        highHand.add(cards.get(startIndex + 2));
        highHand.add(cards.get(startIndex + 3));
        lowHand.add(cards.get(pairIndex));
        lowHand.add(cards.get(pairIndex + 1));
        // find the last card to add to highHand
        tempIndex = 0;
        if (startIndex < pairIndex) {
          // 0, 4, 6
          if (tempIndex < startIndex) {
            highHand.add(cards.get(tempIndex));
          }
          else {
            tempIndex = 4;
            if (tempIndex < pairIndex) {
              highHand.add(cards.get(tempIndex));
            }
            else {
              highHand.add(cards.get(6));
            }
          }
        }
        else {
          // 0, 2, 6
          if (tempIndex < pairIndex) {
            highHand.add(cards.get(tempIndex));
          }
          else {
            tempIndex = 2;
            if (tempIndex < startIndex) {
              highHand.add(cards.get(tempIndex));
            }
            else {
              highHand.add(cards.get(6));
            }
          }
        }
      }
      else { // no pair need ace and face card
        // check cards[0] if not ace split
        if (cards.get(0).getRank() == ICard.ACE && cards.get(1).getRank() > ICard.TEN) {
          lowHand.add(cards.get(0));
          lowHand.add(cards.get(1));
          highHand.add(cards.get(startIndex));
          highHand.add(cards.get(startIndex + 1));
          highHand.add(cards.get(startIndex + 2));
          highHand.add(cards.get(startIndex + 3));
          if (startIndex == 2) {
            highHand.add(cards.get(6));
          }
          else {
            highHand.add(cards.get(2));
          }
        }
        else {
          // split
          lowHand.add(cards.get(startIndex));
          lowHand.add(cards.get(startIndex + 1));
          highHand.add(cards.get(startIndex + 2));
          highHand.add(cards.get(startIndex + 3));
          for (int i = 0; i < startIndex; i++) {
            highHand.add(cards.get(i));
          }
          for (int i = startIndex + 4; i < cards.size(); i++) {
            highHand.add(cards.get(i));
          }
        }
      }

    }
    else if (rank < ICard.ACE) {
      // * Jack through king: Split unless hand also contains a pair of 10's
      // or higher.
      if (pair && pairRank > ICard.NINE) {
        lowHand.add(cards.get(pairIndex));
        lowHand.add(cards.get(pairIndex));
        highHand.add(cards.get(startIndex));
        highHand.add(cards.get(startIndex + 1));
        highHand.add(cards.get(startIndex + 2));
        highHand.add(cards.get(startIndex + 3));
        lowHand.add(cards.get(pairIndex));
        lowHand.add(cards.get(pairIndex + 1));
        // find the last card to add to highHand
        tempIndex = 0;
        if (startIndex < pairIndex) {
          // 0, 4, 6
          if (tempIndex < startIndex) {
            highHand.add(cards.get(tempIndex));
          }
          else {
            tempIndex = 4;
            if (tempIndex < pairIndex) {
              highHand.add(cards.get(tempIndex));
            }
            else {
              highHand.add(cards.get(6));
            }
          }
        }
        else {
          // 0, 2, 6
          if (tempIndex < pairIndex) {
            highHand.add(cards.get(tempIndex));
          }
          else {
            tempIndex = 2;
            if (tempIndex < startIndex) {
              highHand.add(cards.get(tempIndex));
            }
            else {
              highHand.add(cards.get(6));
            }
          }
        }
      }
      else {
        // split
        lowHand.add(cards.get(startIndex));
        lowHand.add(cards.get(startIndex + 1));
        highHand.add(cards.get(startIndex + 2));
        highHand.add(cards.get(startIndex + 3));
        for (int i = 0; i < startIndex; i++) {
          highHand.add(cards.get(i));
        }
        for (int i = startIndex + 4; i < cards.size(); i++) {
          highHand.add(cards.get(i));
        }
      }

    }
    else {
      // * Aces: Split unless a pair of 7's or higher can be played in front.
      if (pair && pairRank > ICard.SIX) {
        lowHand.add(cards.get(pairIndex));
        lowHand.add(cards.get(pairIndex + 1));
        highHand.add(cards.get(startIndex));
        highHand.add(cards.get(startIndex + 1));
        highHand.add(cards.get(startIndex + 2));
        if (jokerp) {
          highHand.add(cards.get(cards.size() - 1));
        }
        else {
          highHand.add(cards.get(startIndex + 3));
        }
        // find the last card to add to highHand
        tempIndex = 0;
        if (startIndex < pairIndex) {
          // 0, 4, 6
          if (tempIndex < startIndex) {
            highHand.add(cards.get(tempIndex));
          }
          else {
            tempIndex = 4;
            if (tempIndex < pairIndex) {
              highHand.add(cards.get(tempIndex));
            }
            else {
              highHand.add(cards.get(6));
            }
          }
        }
        else {
          // 0, 2, 6
          if (tempIndex < pairIndex) {
            highHand.add(cards.get(tempIndex));
          }
          else {
            tempIndex = 2;
            if (tempIndex < startIndex) {
              highHand.add(cards.get(tempIndex));
            }
            else {
              highHand.add(cards.get(6));
            }
          }
        }
      }
      else { // no pair
        // split
        if (jokerp) {
          lowHand.add(cards.get(startIndex)); // Ace
          lowHand.add(cards.get(startIndex + 1)); // Ace
          highHand.add(cards.get(startIndex + 2)); // Ace
          highHand.add(cards.get(6)); // / joker
          for (int i = startIndex + 3; i < cards.size() - 1; i++) {
            highHand.add(cards.get(i));
          }
        }
        else {
          lowHand.add(cards.get(startIndex));
          lowHand.add(cards.get(startIndex + 1));
          highHand.add(cards.get(startIndex + 2));
          highHand.add(cards.get(startIndex + 3));
          for (int i = 0; i < startIndex; i++) {
            highHand.add(cards.get(i));
          }
          for (int i = startIndex + 4; i < cards.size(); i++) {
            highHand.add(cards.get(i));
          }
        }
      }
    }
    System.out.println("low " + lowHand + " high "+ highHand);
    hand.setLowHand(lowHand);
    hand.setHighHand(highHand);
  }

  /**
   * Full house: Split except with pair of 2's and an ace/king can be played in
   * front.
   * 
   * Full house with three of a kind and two pairs: Play the highest pair in
   * front.
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playFullHouse(org.cam.card.PaiGowHand)
   */
  @Override
  public void playFullHouse(PaiGowHand hand) {
    // TODO Auto-generated method stub

  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playPaiGow(org.cam.card.PaiGowHand)
   */
  @Override
  public void playPaiGow(PaiGowHand hand) {
    this.cards.clear();
    for (ICard c : hand.getCards()) {
      cards.add(c);
    }
    Collections.reverse(cards);
    lowHand.clear();
    highHand.clear();
    // No pair: Place the second and third highest cards in the front.
    lowHand.add(cards.get(1));
    lowHand.add(cards.get(2));
    highHand.add(cards.get(0));
    highHand.add(cards.get(3));
    highHand.add(cards.get(4));
    highHand.add(cards.get(5));
    highHand.add(cards.get(6));
    hand.setLowHand(lowHand);
    hand.setHighHand(highHand);
  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playPair(org.cam.card.PaiGowHand)
   */
  @Override
  public void playPair(PaiGowHand hand) {
    // TODO Auto-generated method stub

  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playRoyalFlush(org.cam.card.PaiGowHand)
   */
  @Override
  public void playRoyalFlush(PaiGowHand hand) {
    // TODO Auto-generated method stub

  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playStraightFlush(org.cam.card.PaiGowHand)
   */
  @Override
  public void playStraightFlush(PaiGowHand hand) {
    // TODO Auto-generated method stub

  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playThreeOfAKind(org.cam.card.PaiGowHand)
   */
  @Override
  public void playThreeOfAKind(PaiGowHand hand) {
    // TODO Auto-generated method stub

  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playThreePair(org.cam.card.PaiGowHand)
   */
  @Override
  public void playThreePair(PaiGowHand hand) {
    // TODO Auto-generated method stub

  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#playTwoPair(org.cam.card.PaiGowHand)
   */
  @Override
  public void playTwoPair(PaiGowHand hand) {
    // TODO Auto-generated method stub

  }

  /**
   * 
   * @param hand
   * @see org.cam.card.IHouseWay#setHand(org.cam.card.PaiGowHand)
   */
  @Override
  public void setHand(PaiGowHand hand) {
    if (hand.hasFiveAces()) {
      playFiveAces(hand);
      // Five aces: Split unless pair of kings can be played in front.
    }
    else if (hand.hasRoyalFlush()) {
      playRoyalFlush(hand);
      // Royal Flush:
      //
      // Play the royal flush in back except play as a two pair with:
      // Aces and any other pair.
      // Both pairs tens or higher.
      //
      // Break up royal flush if a straight or flush can be played in back and a
      // king or better in front.
    }
    else if (hand.hasStraightFlush()) {
      playStraightFlush(hand);
      // Straight Flush:
      //
      // Play the straight flush in back except play as a two pair with:
      //
      // Aces and any other pair.
      // Both pairs tens or higher.
      // Both pairs tens or less and an ace singleton.
      //
      // Play a straight or flush instead if an ace and a face card or a pair
      // can be played in front.
    }
    else if (hand.hasFourOfAKind()) {
      playFourOfAKind(hand);
      // Four of a kind: Play according to the rank of the four of a kind:
      //
      // 2 through 6: Always keep together.
      // 7 through 10: Split unless an ace and a face card or better can be
      // played in front.
      // Jack through king: Split unless hand also contains a pair of 10's or
      // higher.
      // Aces: Split unless a pair of 7's or higher can be played in front.
    }
    else if (hand.hasFullHouse()) {
      playFullHouse(hand);
      // Full house: Split except with pair of 2's and an ace/king can be played
      // in front.
      //
      // Full house with three of a kind and two pairs: Play the highest pair in
      // front.
    }
    else if (hand.hasFlush() || hand.hasStraight()) {
      playFlushOrStraight(hand);
      // Straights and flushes:
      //
      // Always play straight or flush in the high hand except play as a two
      // pair
      // if one of the following:
      // Aces and any other pair.
      // Both pairs 10's or over.
      // Both pairs 10's or under with an ace singleton.
      //
      // With a six or seven card straight or flush put the highest hand
      // possible
      // in front while maintaining the straight or flush in back.
      //
      // If hand contains both a straight and a flush play put the hand in back
      // with the highest hand in front, as long as the front hand is a king or
      // better. Otherwise play flush in back.
    }
    else if (hand.hasThreeOfAKind()) {
      playThreeOfAKind(hand);
      // Three of a kind: Play three of a kind in back except break up three
      // aces.
      //
      // Three of a kind twice: Play higher pair in front.

    }
    if (hand.hasThreePair()) {
      playThreePair(hand);
      // Three pair: Play highest pair in front.
    }
    else if (hand.hasTwoPair()) {
      playTwoPair(hand);
      // Two pair: Split the two pair except for the following three situations
      // Both pairs are 6's or less.
      // Both pairs are 10's or less plus ace singleton.
      // One pair of face cards, one pair of 5's or less, and an ace singleton.
    }
    else if (hand.hasPair()) {
      playPair(hand);
      // One pair: Place the pair in back and the two highest singletons in the
      // front.
    }
    else if (hand.isPaiGow()) {
      playPaiGow(hand);
      // No pair: Place the second and third highest cards in the front.
    }

  }

}
