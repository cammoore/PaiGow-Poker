/**
 * 
 */
package org.cam.paigow;

import java.util.ArrayList;
import java.util.List;
import org.cam.card.PaiGowFortunePayout;
import org.cam.card.PaiGowHand;

/**
 * @author Cam Moore
 *
 */
public class PaiGowSeat {
  private List<PaiGowHandResult> results;
  private PaiGowBetting betting;
  
  public PaiGowSeat() {
    results = new ArrayList<PaiGowHandResult>();
    betting = new PaiGowBetting(100, 10);
  }
  
  /**
   * @return The betting value a PaiGaoBetting.
   */
  public PaiGowBetting getBetting() {
    return this.betting;
  }

  /**
   * Sets the betting value.
   * @param betting the new betting value.
   */
  public void setBetting(PaiGowBetting betting) {
    this.betting = betting;
  }

  public void addRoundInfo(PaiGowHandResult res) {
    results.add(res);
    betting.update(res);
  }

  /**
   * @return The results value a List<HandResult>.
   */
  public List<PaiGowHandResult> getResults() {
    List<PaiGowHandResult> ret = new ArrayList<PaiGowHandResult>();
    for (PaiGowHandResult res : this.results) {
      ret.add(res);
    }
    return ret;
  }

  /**
   * @param playerHand
   */
  public void addEnvy(PaiGowHand playerHand) {
    PaiGowFortunePayout pay = new PaiGowFortunePayout(playerHand);
    betting.addEnvy(1.0 * pay.getEnvy());
  }
  
  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append("[");
    buf.append(results);
    buf.append("]");
    return buf.toString();
  }
}
