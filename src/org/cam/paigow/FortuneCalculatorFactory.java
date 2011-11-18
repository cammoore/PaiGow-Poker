/**
 * 
 */
package org.cam.paigow;

import org.cam.card.PaiGowFortunePayout;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 *
 */
public class FortuneCalculatorFactory {
  private static FortuneCalculatorFactory instance;
  
  private FortuneCalculatorFactory() {
    
  }
  
  public static FortuneCalculatorFactory getInstance() {
    if (instance == null) {
      instance = new FortuneCalculatorFactory();
    }
    return instance;
  }
  
  public FortuneResult calcFortune(PaiGowSeat seat) {
    FortuneResult result = new FortuneResult();
    for (PaiGowHandResult res : seat.getResults()) {
      result.update(res.getHand());
    }
    return result;
  }
}
