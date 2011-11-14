/**
 * 
 */
package org.cam.paigow;

import org.cam.card.PaiGowDeck;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 *
 */
public class PaiGowTable {
  public static final int NUM_SEATS = 6;
  private PaiGowSeat[] seats;
  private PaiGowDeck deck;
  private int iterIndex;
  
  
  public PaiGowTable() {
  	this.iterIndex = 0;
    this.seats = new PaiGowSeat[6];
    for (int i = 0; i < 6; i++) {
      seats[i] = new PaiGowSeat();
    }
    this.deck = new PaiGowDeck();
  }
  
  public void playRounds(int numRounds) {
    for (int i = 0; i < numRounds; i++) {
      playRound();
    }
  }
  
  public PaiGowSeat getSeat(int seatNum) {
    if (seatNum > -1 && seatNum < 6)  {
      return seats[seatNum];
    }
    else { 
      return null;
    }
  }
  
  public static void main(String[] args) {
    PaiGowTable table = new PaiGowTable();
    table.playRounds(5);
    for (int i = 0; i < 6; i++) {
      PaiGowSeat seat = table.getSeat(i);
      System.out.println("Seat " + i + ": " + seat);
      System.out.println(seat.getBetting());
    }
  }
  
  private void playRound() {
    // find the first seat delt
    int firstSeat = (int) Math.floor((Math.random() * 7));
    PaiGowHand[] hands = new PaiGowHand[7];
    this.deck = new PaiGowDeck();
    PaiGowHandResult[] results = new PaiGowHandResult[6];
    // deal the hands
    for (int i = 0; i < 7; i++) {
      hands[firstSeat] = new PaiGowHand(deck);
      firstSeat++;
      if (firstSeat > 6) {
        firstSeat = 0;
      }
    }
    // calculate the results
    PaiGowHand dealerHand = hands[0];
    PaiGowHand playerHand;
    for (int i = 1; i < 7; i++) {
      playerHand = hands[i];
      PaiGowHandResult result = new PaiGowHandResult(playerHand.beats(dealerHand), playerHand);
      results[i - 1] = result;
      seats[i - 1].addRoundInfo(result);
      for (int j = 0; j < 6; j++) {
        if (i != j) {
          seats[j].addEnvy(playerHand);
        }
      }
    }
    if (false) {
      StringBuffer buf = new StringBuffer();
      buf.append("Dealer: ");
      buf.append(dealerHand + " " + dealerHand.description());
      buf.append("\n");
      for (int j = 0; j < 6; j++) {
        buf.append("Seat " + (j+1) + ": ");
        buf.append(results[j]);
        buf.append("\n");
      }
      System.out.println(buf.toString());
    }
  }


}
