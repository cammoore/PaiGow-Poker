/**
 * 
 */
package org.cam.paigow;

import org.cam.card.HandResult;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 *
 */
public class PaiGowHandResult {
  private HandResult result;
  private PaiGowHand hand;
  
  public PaiGowHandResult(HandResult result, PaiGowHand hand) {
    this.result = result;
    this.hand = hand;
  }

  /**
   * @return The result value a HandResult.
   */
  public HandResult getResult() {
    return this.result;
  }

  /**
   * @return The hand value a PaiGaoHand.
   */
  public PaiGowHand getHand() {
    return this.hand;
  }
  

  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append(hand);
    buf.append(" ");
    buf.append(hand.description());
    buf.append(" ");
    buf.append(result);
    return buf.toString();
  }
}
