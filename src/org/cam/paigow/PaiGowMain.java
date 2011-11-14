/**
 * 
 */
package org.cam.paigow;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.cam.card.HandResult;
import org.cam.card.PaiGowDeck;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 * 
 */
public class PaiGowMain {

  private static final Logger LOGGER = Logger.getLogger("org.cam.paigao");

  
  /**
   * @param seat an int.
   * @param scheme the betting scheme.
   */
  public void play(int seat, int scheme) {
    // Create the PaiGaoDeck
    PaiGowDeck deck = null;
    ArrayList<PaiGowHand> hands = null;

    double startStake = 100.0;
    double stake = startStake;
    double minBet = 10.0;
    int maxTries = 100;
    int round = 0;
    double currentBet = minBet;
    int wins = 0;
    int losses = 0;
    double aveStake = 0.0;
    double aveRounds = 0;
    int limit = 100;
    int winStreak = 0;
    int maxWinStreak = 0;
    int loseStreak = 0;
    int maxLoseStreak = 0;
    double maxBet = 0;
    int winningRounds = 0;
    double maxStake = 0;
    double maxInHand = 0;
    double aveMaxInHand = 0;
    int smart = 0;
    int numMaxTries = 0;
    double roundWinnings;
    int betIncrease;
    int doubles = 0;
    for (int i = 0; i < limit; i++) {
      stake = startStake;
      round = 0;
      currentBet = minBet;
      maxWinStreak = 0;
      maxLoseStreak = 0;
      maxBet = 0;
      winStreak = 0;
      loseStreak = 0;
      maxInHand = 0;
      // run the game until run out of money or set # loops
      while (stake > minBet && round < maxTries) {
        deck = new PaiGowDeck();
        hands = new ArrayList<PaiGowHand>();
        // load the hands
        for (int j = 0; j < 7; j++) {
          hands.add(new PaiGowHand(deck));
        }
        int dealer = (int) Math.floor((Math.random() * 7));
        int player = (dealer + seat) % 7;
        PaiGowHand dealerHand = hands.get(dealer);
        PaiGowHand playerHand = hands.get(player);
        // logger.info("Dealer has " + adealerHand + " " +
        // dealerHand.description() + " "
        // + dealerHand.rawCards());
        // logger.info("Seat " + seat + " has " + playerHand + " " +
        // playerHand.description()
        // + " " + playerHand.rawCards());
        HandResult winHand = playerHand.beats(dealerHand);
        switch (winHand) {
        case LOSE:
          // logger.info("Player loses");
          break;
        case PUSH:
          // logger.info("Push");
          break;
        case WIN:
          // logger.info("Player wins");
          break;
        default:
          // logger.info("Unknown outcome.");
        }
        if (stake > 2.0 * startStake) { // doubled stake walk away
          doubles++;
          break;
        }
          // win
        if (winHand == HandResult.WIN) {
          wins++;
          roundWinnings = 0.95 * currentBet;
          stake += roundWinnings;
          switch (scheme) {
          case 0:
            betIncrease = 0;
            break;
          case 1:
            betIncrease = 5;
            break;
          case 2:
            betIncrease = ((int) Math.floor(roundWinnings) / 5) * 5;
            break;
          default:
            betIncrease = 0;
            // logger.info(betIncrease);
          }
          currentBet += betIncrease;
          if (maxBet < currentBet) {
            maxBet = currentBet;
          }
          winStreak++;
          if (maxWinStreak < winStreak) {
            maxWinStreak = winStreak;
          }
          loseStreak = 0;
          if (maxInHand < stake) {
            maxInHand = stake;
          }
        }
        else if (winHand == HandResult.LOSE) {
          // lose
          losses++;
          stake -= currentBet;
          currentBet = minBet;
          winStreak = 0;
          loseStreak++;
          if (maxLoseStreak < loseStreak) {
            maxLoseStreak = loseStreak;
          }
          if (loseStreak == 3 && stake > 100) {
            smart++;
            break;
          }
          if (loseStreak == 3) {
            break;
          }
        }
        round++;
      }
      if (round == maxTries) {
        numMaxTries++;
      }
      if (stake > 100) {
        winningRounds++;
      }
      if (maxStake < stake) {
        maxStake = stake;
      }
      aveStake += stake;
      aveRounds += round;
      aveMaxInHand += maxInHand;
    }
    aveStake /= limit;
    aveRounds /= limit;
    aveMaxInHand /= limit;
    // print out the results
    switch (scheme) {
    case 0:
      LOGGER.info("No bet increase: ");
      break;
    case 1:
      LOGGER.info("$5 bet increase: ");
      break;
    case 2:
      LOGGER.info("Max bet increase: ");
      break;
    default:
      LOGGER.info("Unknown scheme");
    }
    LOGGER.info("Ave. Stake = " + aveStake + " max bet = " + maxBet + " Ave Max Stake "
        + aveMaxInHand + "\nAve. round = " + aveRounds + " finished rounds = " + numMaxTries
        + "\nwinning rounds = " + winningRounds + " smart walk aways " + smart + " doubles "
        + doubles);
  }

  /**
   * @param args ignored
   */
  public static void main(String[] args) {
    int seat = 3;
    PaiGowMain m = new PaiGowMain();
    for (int i = 0; i < 3; i++) {
      m.play(seat, i);
    }
  }

}
