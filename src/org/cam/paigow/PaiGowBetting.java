/**
 * 
 */
package org.cam.paigow;

import org.cam.card.HandResult;
import org.cam.card.PaiGowFortunePayout;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 * 
 */
public class PaiGowBetting {
  private Integer startStake;
  private Double[] stake;
  private Double[] doubledStake;
  private Integer minBet;
  private Integer[] currentBet;
  private Double[] walkAwayStake;
  private int lossStreak;
  private Boolean[] broke;
  private Boolean[] doubled;
  private Integer[] handsPlayed;
  
  public PaiGowBetting(Integer stake, Integer minBet) {
    this.startStake = stake;
    this.lossStreak = 0;
    int numSchemes = BettingScheme.values().length;
    this.stake = new Double[numSchemes];
    this.doubledStake = new Double[numSchemes];
    this.walkAwayStake = new Double[numSchemes];
    this.handsPlayed = new Integer[3 * numSchemes];
    this.minBet = minBet;
    currentBet = new Integer[numSchemes];
    this.broke = new Boolean[numSchemes];
    this.doubled = new Boolean[numSchemes];
    int ordinal = -1;
    for (BettingScheme scheme : BettingScheme.values()) {
      ordinal = scheme.ordinal();
      this.stake[ordinal] = 1.0 * stake;
      this.doubledStake[ordinal] = 0.0;
      this.walkAwayStake[ordinal] = 0.0;
      this.currentBet[ordinal] = minBet;
      this.broke[ordinal] = Boolean.FALSE;
      this.doubled[ordinal] = Boolean.FALSE;
      this.handsPlayed[ordinal * 3] = 0;
      this.handsPlayed[ordinal * 3 + 1] = 0;
      this.handsPlayed[ordinal * 3 + 2] = 0;
    }
  }

  public void addEnvy(Double envy) {
    for (BettingScheme scheme : BettingScheme.values()) {
      switch (scheme) {
      case NO_RAISE_W_5_FORTUNE:
      case RAISE_ONE_W_5_FORTUNE:
      case MAX_RAISE_W_5_FORTUNE:
        this.stake[scheme.ordinal()] += envy;
        break;
      default:
        // do nothing since don't get envy
      }
    }
  }

  public Double getStake(BettingScheme scheme) {
    return this.stake[scheme.ordinal()];
  }

  public Double getWalkAwayStake(BettingScheme scheme) {
    return this.walkAwayStake[scheme.ordinal()];
  }

  public Double getDoubledStake(BettingScheme scheme) {
    return this.doubledStake[scheme.ordinal()];
  }
  
  public Double getCombinedStake(BettingScheme scheme) {
    int ordinal = scheme.ordinal();
    Double ret = this.doubledStake[ordinal];
    if (ret == 0.0) {
      ret = this.walkAwayStake[ordinal];
      if (ret == 0.0) {
        ret = stake[ordinal];
      }
    }
    return ret;
  }
  
  public Integer getCurrentBet(BettingScheme scheme) {
    return this.currentBet[scheme.ordinal()];
  }
  
  public Integer[] getHandsPlayed() {
    return this.handsPlayed;
  }

  public void update(PaiGowHandResult res) {
    PaiGowFortunePayout pay = new PaiGowFortunePayout(res.getHand());
    Double fortune = 1.0 * pay.getPays();
//    Double envy = 1.0 * pay.getEnvy();
    switch (res.getResult()) {
    case WIN:
      this.lossStreak = 0;
      for (BettingScheme scheme : BettingScheme.values()) {
        int i = scheme.ordinal();
        if (!broke[i]) {
          Double winnings = currentBet[i] * 0.95;
          stake[i] += winnings;
          switch (scheme) {
          case NO_RAISE:
            // no changes
            break;
          case NO_RAISE_W_1_FORTUNE:
            stake[i] += fortune;
            break;
          case NO_RAISE_W_5_FORTUNE:
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            break;
          case RAISE_ONE:
            currentBet[i] += 5;
            break;
          case RAISE_ONE_W_1_FORTUNE:
            currentBet[i] += 5;
            stake[i] += fortune;
            break;
          case RAISE_ONE_W_5_FORTUNE:
            currentBet[i] += 5;
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            break;
          case MAX_RAISE:
            if (currentBet[i] < stake[i]) {
              currentBet[i] += currentBet[i];
            }
            else {
              currentBet[i] += Integer.valueOf((int) Math.floor(stake[i]));
            }
            break;
          case MAX_RAISE_W_1_FORTUNE:
            stake[i] += fortune;
            if (currentBet[i] < stake[i]) {
              currentBet[i] += currentBet[i];
            }
            else {
              currentBet[i] += Integer.valueOf((int) Math.floor(stake[i]));
            }
            break;
          case MAX_RAISE_W_5_FORTUNE:
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            if (currentBet[i] < stake[i]) {
              currentBet[i] += currentBet[i];
            }
            else {
              currentBet[i] += Integer.valueOf((int) Math.floor(stake[i]));
            }
            break;
          default:
            // do nothing
          }
        }

      }
      break;
    case PUSH:
      for (BettingScheme scheme : BettingScheme.values()) {
        int i = scheme.ordinal();
        if (!broke[i]) {
          switch (scheme) {
          case NO_RAISE:
            // no changes
            break;
          case NO_RAISE_W_1_FORTUNE:
            stake[i] += fortune;
            break;
          case NO_RAISE_W_5_FORTUNE:
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            break;
          case RAISE_ONE:
            break;
          case RAISE_ONE_W_1_FORTUNE:
            stake[i] += fortune;
            break;
          case RAISE_ONE_W_5_FORTUNE:
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            break;
          case MAX_RAISE:
            break;
          case MAX_RAISE_W_1_FORTUNE:
            stake[i] += fortune;
            break;
          case MAX_RAISE_W_5_FORTUNE:
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            break;
          default:
            // do nothing
          }
        }
        if ((stake[i]) <= 0.0) {
          broke[i] = Boolean.TRUE;
        }
      }
      break;
    case LOSE:
      this.lossStreak++;
      for (BettingScheme scheme : BettingScheme.values()) {
        int i = scheme.ordinal();
        if (currentBet[i] > stake[i] && !broke[i]) {
          System.err.println("current bet = " + currentBet[i] + " stake is " + stake[i]);
        }
        if (!broke[i]) {
          stake[i] -= currentBet[i];
          if ((stake[i] - minBet) <= 0.0) {
            broke[i] = Boolean.TRUE;
          }
          if (lossStreak == 3 && walkAwayStake[i] == 0.0) {
            walkAwayStake[i] = stake[i];
          }
          switch (scheme) {
          case NO_RAISE:
            // no changes
            break;
          case NO_RAISE_W_1_FORTUNE:
            stake[i] += fortune;
            break;
          case NO_RAISE_W_5_FORTUNE:
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            break;
          case RAISE_ONE:
            currentBet[i] = minBet;
            break;
          case RAISE_ONE_W_1_FORTUNE:
            currentBet[i] = minBet;
            stake[i] += fortune;
            break;
          case RAISE_ONE_W_5_FORTUNE:
            currentBet[i] = minBet;
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            break;
          case MAX_RAISE:
            currentBet[i] = minBet;
            break;
          case MAX_RAISE_W_1_FORTUNE:
            currentBet[i] = minBet;
            stake[i] += fortune;
            break;
          case MAX_RAISE_W_5_FORTUNE:
            currentBet[i] = minBet;
            if (fortune <= 0.0) {
              stake[i] += fortune;
            }
            else {
              stake[i] += 5.0 * fortune;
            }
            break;
          default:
            // do nothing
          }
        }
      }
      break;
    default:
      // do nothing
    }
    for (BettingScheme scheme : BettingScheme.values()) {
      int i = scheme.ordinal();
      if (currentBet[i] > stake[i] && !broke[i]) {
        broke[i] = Boolean.TRUE;
      }
      if (stake[i] >= 2.0 * startStake) {
        doubled[i] = Boolean.TRUE;
        if (doubledStake[i] == 0.0) {
          doubledStake[i] = stake[i];
        }
      }
      if (!broke[i]) {
        handsPlayed[i * 3]++;
      }
      if (!broke[i] && !doubled[i]) {
        handsPlayed[i * 3 + 1]++;
      }
      if (!broke[i] && walkAwayStake[i] <= 0.0) {
        handsPlayed[i * 3 + 2]++;
      }
    }
  }

  public String toString() {
    StringBuffer buf = new StringBuffer();
    for (BettingScheme scheme : BettingScheme.values()) {
      buf.append(scheme.toString());
      buf.append(": stake ");
      buf.append(stake[scheme.ordinal()]);
      buf.append(", bet ");
      buf.append(currentBet[scheme.ordinal()]);
      buf.append("\n");
    }
    return buf.toString();
  }
}
