/**
 * 
 */
package org.cam.paigow;

/**
 * @author Cam Moore
 *
 */
public class PaiGowStats {
  private double aveStake = 0.0;
  private double maxBet = 0.0;
  private int wins = 0;
  private int losses = 0;
  private int rounds = 0;
  
  
  /**
   * Sets the aveStake value.
   * @param aveStake the new aveStake value.
   */
  public void setAveStake(double aveStake) {
    this.aveStake = aveStake;
  }

  /**
   * @return The aveStake value a double.
   */
  public double getAveStake() {
    return aveStake;
  }

  /**
   * Sets the maxBet value.
   * @param maxBet the new maxBet value.
   */
  public void setMaxBet(double maxBet) {
    this.maxBet = maxBet;
  }

  /**
   * @return The maxBet value a double.
   */
  public double getMaxBet() {
    return maxBet;
  }

  /**
   * Sets the wins value.
   * @param wins the new wins value.
   */
  public void setWins(int wins) {
    this.wins = wins;
  }

  /**
   * @return The wins value a int.
   */
  public int getWins() {
    return wins;
  }

  /**
   * Sets the losses value.
   * @param losses the new losses value.
   */
  public void setLosses(int losses) {
    this.losses = losses;
  }

  /**
   * @return The losses value a int.
   */
  public int getLosses() {
    return losses;
  }

  /**
   * Sets the rounds value.
   * @param rounds the new rounds value.
   */
  public void setRounds(int rounds) {
    this.rounds = rounds;
  }

  /**
   * @return The rounds value a int.
   */
  public int getRounds() {
    return rounds;
  }
}
