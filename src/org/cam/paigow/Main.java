/**
 * 
 */
package org.cam.paigow;

/**
 * @author Cam Moore
 * 
 */
public class Main {

  /**
   * @param args ignored
   */
  public static void main(String[] args) {
    // get the initial state of the PaiGao game.
    // this includes:
    // Initial Stake
    // Minimum bet
    // Strategy
    double startStake = 100.0;
    double stake = startStake;
    double minBet = 10.0;
    int maxTries = 100;
    int round = 0;
    double result = 0;
    double currentBet = minBet;
    int wins = 0;
    int losses = 0;
    double aveStake = 0.0;
    double aveRounds = 0;
    int limit = 10000;
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
    for (int scheme = 0; scheme < 3; scheme++) {
      winningRounds = 0;
      smart = 0;
      doubles = 0;
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
          result = Math.random();
          // System.out.print(round + " $" + stake + " : " + currentBet);
          if (stake > 2.0 * startStake) { // doubled stake walk away
            doubles++;
            break;
          }
          if (result > 0.5) {
            // System.out.println(" w");
            // win
            wins++;
            roundWinnings = 0.95 * currentBet;
            stake += roundWinnings;
            // stake += currentBet;
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
              // System.out.println(betIncrease);
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
          else {
            // System.out.println(" l");
            // lose
            losses++;
            stake -= currentBet;
            currentBet = minBet;
            winStreak = 0;
            loseStreak++;
            if (maxLoseStreak < loseStreak) {
              maxLoseStreak = loseStreak;
            }
            if (loseStreak == 3) {
              if (stake > 100) {
                smart++;
              }
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
        System.out.print("No bet increase: ");
        break;
      case 1:
        System.out.print("$5 bet increase: ");
        break;
      case 2:
        System.out.print("Max bet increase: ");
        break;
      default:
        System.out.println("Unknown scheme");
      }
      System.out.println("Ave. Stake = " + aveStake + " max bet = " + maxBet + " Ave Max Stake "
          + aveMaxInHand);
      System.out.println("Ave. round = " + aveRounds + " finished rounds = " + numMaxTries);
      System.out.println("winning rounds = "
          + winningRounds + " smart walk aways " + smart + " doubles " + doubles);
    }
  }

}
