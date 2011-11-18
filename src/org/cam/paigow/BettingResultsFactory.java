/**
 * 
 */
package org.cam.paigow;

import org.cam.card.HandResult;

/**
 * @author Cam Moore
 *
 */
public class BettingResultsFactory {
  private static BettingResultsFactory instance;
  
  private BettingResultsFactory() {
    
  }
  
  public static BettingResultsFactory getInstance() {
    if (instance == null) {
      instance = new BettingResultsFactory();
    }
    return instance;
  }
  
  public PaiGowBetting calcStats(PaiGowSeat seat, Integer stake, Integer minBet) {
    PaiGowBetting result = new PaiGowBetting(stake, minBet);
    for (PaiGowHandResult res : seat.getResults()) {
      result.update(res);
    }
    return result;
  }
}
