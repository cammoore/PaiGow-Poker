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
public class FortuneResult {
  private Integer fortune;
  private Integer envy;
  
  public FortuneResult() {
    this.fortune = 0;
    this.envy = 0;
  }
  
  public void update(PaiGowHand hand) {
    PaiGowFortunePayout pay = new PaiGowFortunePayout(hand);
    this.fortune += pay.getPays();
    this.envy += pay.getEnvy();
  }

  /**
   * @return The fortune value a Integer.
   */
  public Integer getFortune() {
    return this.fortune;
  }

  /**
   * @return The envy value a Integer.
   */
  public Integer getEnvy() {
    return this.envy;
  }
  
}
