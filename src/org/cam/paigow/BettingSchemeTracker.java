/**
 * 
 */
package org.cam.paigow;

/**
 * @author Cam Moore
 *
 */
public class BettingSchemeTracker {
  private Double minBet;
  private Double[] currentBet;
  private Double[] stake;
  private Double[] fortuneStake;
  
  public BettingSchemeTracker(Double stake, Double minBet) {
    this.minBet = minBet;
    this.currentBet = new Double[BettingScheme.values().length];
    this.stake = new Double[BettingScheme.values().length];
    this.fortuneStake = new Double[BettingScheme.values().length];
    for (int i = 0; i < BettingScheme.values().length; i++) {
      currentBet[i] = minBet;
      this.stake[i] = stake;
      this.fortuneStake[i] = stake;
    }
  }
  
  public void wins() {
    Double winnings;
    for (BettingScheme scheme : BettingScheme.values()) {
      winnings = 0.95 * currentBet[scheme.ordinal()];
      this.stake[scheme.ordinal()] += winnings;
      this.fortuneStake[scheme.ordinal()] += winnings;
      switch(scheme) {
      case NO_RAISE:
      case NO_RAISE_W_1_FORTUNE:
      case NO_RAISE_W_5_FORTUNE:
        break;
      case RAISE_ONE:
      case RAISE_ONE_W_1_FORTUNE:
      case RAISE_ONE_W_5_FORTUNE:
        currentBet[scheme.ordinal()] += 5.0;
        break;
      case MAX_RAISE:
      case MAX_RAISE_W_1_FORTUNE:
      case MAX_RAISE_W_5_FORTUNE:
        currentBet[scheme.ordinal()] += ((int) Math.floor(winnings) / 5) * 5;
      }
    }
  }
    
  public void loses() {
    for (BettingScheme scheme : BettingScheme.values()) {
      stake[scheme.ordinal()] -= currentBet[scheme.ordinal()];
      fortuneStake[scheme.ordinal()] -= currentBet[scheme.ordinal()];
      currentBet[scheme.ordinal()] = minBet;
    }    
  }

  public void setFortune(int fortuneMultiple) {
    for (BettingScheme scheme : BettingScheme.values()) {
      switch(scheme) {
      case NO_RAISE_W_1_FORTUNE:
      case RAISE_ONE_W_1_FORTUNE:
      case MAX_RAISE_W_1_FORTUNE:
        stake[scheme.ordinal()] += Double.valueOf(fortuneMultiple);
        break;
      case NO_RAISE_W_5_FORTUNE:
      case RAISE_ONE_W_5_FORTUNE:
      case MAX_RAISE_W_5_FORTUNE:
        stake[scheme.ordinal()] += 5.0 * fortuneMultiple;
        break;
      default:
        stake[scheme.ordinal()] += 0;
      }

    }            
  }

  public void setEnvy(int envy) {
    for (BettingScheme scheme : BettingScheme.values()) {
      switch(scheme) {
      case NO_RAISE_W_5_FORTUNE:
      case RAISE_ONE_W_5_FORTUNE:
      case MAX_RAISE_W_5_FORTUNE:
        stake[scheme.ordinal()] += Double.valueOf(envy);
        break;
      default:
        stake[scheme.ordinal()] += 0;
      }
    }                
  }
  public String toString() {
    StringBuffer buf = new StringBuffer();
    for (BettingScheme scheme : BettingScheme.values()) {
      buf.append("Scheme ");
      buf.append(scheme.toString());
      buf.append(" stake ");
      buf.append(stake[scheme.ordinal()]);
      buf.append(" playing Fortune ");
      buf.append(fortuneStake[scheme.ordinal()]);
      buf.append(".\n");
    }

    return buf.toString();
  }

  public String schemeString(BettingScheme scheme) {
    StringBuffer buf = new StringBuffer();
    buf.append("Scheme ");
    buf.append(scheme.toString());
    buf.append(" stake ");
    buf.append(stake[scheme.ordinal()]);
    buf.append(".");
    return buf.toString();    
  }
}
