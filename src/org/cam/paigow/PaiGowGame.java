/**
 * 
 */
package org.cam.paigow;

import java.util.ArrayList;
import org.cam.card.HandResult;
import org.cam.card.PaiGowDeck;
import org.cam.card.PaiGowFortunePayout;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 *
 */
public class PaiGowGame {

  
  private void playRound(GameResults gameRes, BettingResults bet) {
    // find the first seat delt
    int firstSeat = (int) Math.floor((Math.random() * 7));
    PaiGowHand[] hands = new PaiGowHand[7];
    PaiGowDeck deck = new PaiGowDeck();
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
      HandResult winHand = playerHand.beats(dealerHand);
      switch (winHand) {
      case LOSE:
        gameRes.setLoss(i-1);
        bet.setLoss(i-1);
        break;
      case PUSH:
        gameRes.setPush(i-1);
        bet.setPush(i-1);
        break;
      case WIN:
        gameRes.setWin(i-1);
        bet.setWin(i-1);
        break;
      default:
        // logger.info("Unknown outcome.");
      }
      PaiGowFortunePayout pay = new PaiGowFortunePayout(playerHand);
      int fortune = pay.getPays();
      int envy = pay.getEnvy();
      gameRes.setFortune(i-1, fortune);
      bet.setFortune(i-1, fortune);
      if (envy > 0) {
        for (int j = 1; j < 7; j++) {
          if (j != i) {
            gameRes.setEnvy(i-1, envy);
            bet.setEnvy(i-1, envy);
          }
        }
      }
    }
  }

  
  public static void main(String[] args) {
    PaiGowGame game = new PaiGowGame();
    GameResults res = new GameResults();
    BettingResults bet = new BettingResults(100.0, 10.0);
    for (int i = 0; i < 20; i++) {
      game.playRound(res, bet);
    }
    System.out.println(bet);
  }
}
