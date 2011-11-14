/**
 * 
 */
package org.cam.paigow;

/**
 * @author Cam Moore
 *
 */
public class RoundResults {
  private Boolean[] wins;
  private Boolean[] pushes;
  private Boolean[] losses;
  private Integer[] fortunes;
  private Integer[] envies;
  
  public RoundResults() {
    wins = new Boolean[6];
    pushes = new Boolean[6];
    losses = new Boolean[6];
    fortunes = new Integer[6];
    envies = new Integer[6];
    for (int i = 0; i < 6; i++) {
      wins[i] = Boolean.FALSE;
      pushes[i] = Boolean.FALSE;
      losses[i] = Boolean.FALSE;
      fortunes[i] = -1;
      envies[i] = -1;
    }
  }
  
  public Boolean isWin(int seat) {
    return wins[seat];
  }
  
  public void setWin(int seat) {
    wins[seat] = Boolean.TRUE;
    pushes[seat] = Boolean.FALSE;
    losses[seat] = Boolean.FALSE;
  }

  public Boolean isPush(int seat) {
    return pushes[seat];
  }
  
  public void setPush(int seat) {
    wins[seat] = Boolean.FALSE;
    pushes[seat] = Boolean.TRUE;
    losses[seat] = Boolean.FALSE;
  }
  
  public Boolean isLoss(int seat) {
    return losses[seat];
  }

  public void setLoss(int seat) {
    wins[seat] = Boolean.FALSE;
    pushes[seat] = Boolean.FALSE;
    losses[seat] = Boolean.TRUE;
  }

  public void setFortune(int seat, int value) {
    fortunes[seat] = value;
  }
  
  public void setEnvy(int seat, int value) {
    envies[seat] = value;
  }
  
  public String toString() {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < 6; i++) {
      buf.append("Seat ");
      buf.append(i);
      if (wins[i]) {
        buf.append(" won");
      }
      if (pushes[i]) {
        buf.append(" pushed");
      }
      if (losses[i]) {
        buf.append(" lost");
      }
      buf.append(". Fortune = ");
      buf.append(fortunes[i]);
      buf.append(" Envy = ");
      buf.append(envies[i]);
      buf.append(".\n");
    }
    return buf.toString();
  }
}
