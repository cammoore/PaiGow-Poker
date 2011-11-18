/**
 * 
 */
package org.cam.card;

/**
 * @author Cam Moore
 * 
 * Hand Value                     Fortune Bonus Payout      Envy Bonus(Bet of $5 or greater)    
 * 7-Card Straight Flush-No Joker   8,000 to 1               $5,000
 * Royal Flush Plus Royal Match     2,000 to 1               $1,000
 * 7-Card Straight Flush with Joker 1,000 to 1               $500
 * Five Aces                        400 to 1                 $250
 * Royal Flush                      150 to 1                 $50
 * Straight Flush                   50 to 1                  $20
 * Four of a Kind                   25 to 1                  $5
 * Full House                       5 to 1
 * Flush                            4 to 1      
 * Three of a Kind                  3 to 1      
 * Straight                         2 to 1
 */
public class PaiGowFortunePayout {

  private int pays;
  private int envy;
  private String type;
  private PaiGowHand hand;
  
  /**
   * Constructs a PaiGaoFortunePayout instance.
   * @param hand the hand.
   */
  public PaiGowFortunePayout(PaiGowHand hand) {
    pays = payout(hand, 1);
    envy = envy(hand);
    type = hand.description();
    this.hand = hand;
  }
  
  /**
   * @return The hand value a PaiGaoHand.
   */
  public PaiGowHand getHand() {
    return hand;
  }

  /**
   * @return The pays value a int.
   */
  public int getPays() {
    return pays;
  }
  
  public int getEnvy() {
    return envy;
  }
  /**
   * @return The type value a String.
   */
  public String getType() {
    return type;
  }

  /**
   * @param hand the hand.
   * @param bet the bet. 
   * @return the amount of money won.
   */
  public static int payout(PaiGowHand hand, int bet) {
    int payout = -bet;
    if (hand.hasStraight()) {
      payout = 2 * bet;
    }
    if (hand.hasThreeOfAKind()) {
      payout = 3 * bet; 
    }
    if (hand.hasFlush()) {
      payout = 4 * bet;
    }
    if (hand.hasFullHouse()) {
      payout = 5 * bet;
    }
    if (hand.hasFourOfAKind()) {
      payout = 25 * bet;
    }
    if (hand.hasStraightFlush()) {
      payout = 50 * bet;
    }
    if (hand.isRoyalFlush()) {
      payout = 150 * bet;
    }
    if (hand.hasFiveAces()) {
      payout = 400 * bet;
    }
//    * 7-Card Straight Flush with Joker 1,000 to 1               $500
//    * Royal Flush Plus Royal Match     2,000 to 1               $1,000
//    * 7-Card Straight Flush-No Joker   8,000 to 1               $5,000
    return payout;
  }

  public static int envy(PaiGowHand hand) {
    int payout = 0;    
    if (hand.hasFourOfAKind()) {
      payout = 5;
    }
    if (hand.hasStraightFlush()) {
      payout = 20;
    }    
    if (hand.hasRoyalFlush()) {
      payout = 50;
    }
    if (hand.hasFiveAces()) {
      payout = 250;
    }
    if (hand.has7CardStraightFlush() && hand.hasJoker()) {
      //* 7-Card Straight Flush with Joker 1,000 to 1               $500
      payout = 500;
    }
    //* Royal Flush Plus Royal Match     2,000 to 1               $1,000
    if (hand.has7CardStraightFlush() && !hand.hasJoker()) {
      //* 7-Card Straight Flush-No Joker   8,000 to 1               $5,000
      payout = 5000;
    }
    return payout;
  }
}
