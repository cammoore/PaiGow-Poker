/**
 * 
 */
package org.cam.paigow;

/**
 * @author Cam Moore
 *
 */
public class GameResults {
  private Integer[] wins;
  private Integer[] winStreak;
  private Integer[] maxWinStreak;
  private Integer[] pushes;
  private Integer[] losses;
  private Integer[] lossStreak;
  private Integer[] maxLossStreak;
  private Integer[] fortunes;
  private Integer[] envies;
  
  public GameResults() {
    wins = new Integer[6];
    winStreak = new Integer[6];
    maxWinStreak = new Integer[6];
    pushes = new Integer[6];
    losses = new Integer[6];
    lossStreak = new Integer[6];
    maxLossStreak = new Integer[6];
    fortunes = new Integer[6];
    envies = new Integer[6];
    for (int i = 0; i < 6; i++) {
      wins[i] = 0;
      winStreak[i] = 0;
      maxWinStreak[i] = 0;
      pushes[i] = 0;
      losses[i] = 0;
      lossStreak[i] = 0;
      maxLossStreak[i] = 0;
      fortunes[i] = 0;
      envies[i] = 0;
    }
  }
  
  public Integer getLosses(int seat) {
    return losses[seat];
  }

  public Integer getMaxLossStreak(int seat) {
    return maxLossStreak[seat];
  }

  public Integer getMaxWinStreak(int seat) {
    return maxWinStreak[seat];
  }

  public Integer getPushes(int seat) {
    return pushes[seat];
  }
  
  public Integer getWins(int seat) {
    return wins[seat];
  }
  
  public void setEnvy(int seat, int value) {
    envies[seat] += value;
  }

  public void setFortune(int seat, int value) {
    fortunes[seat] += value;
  }

  public void setLoss(int seat) {
    losses[seat]++;
    lossStreak[seat]++;
    if (lossStreak[seat] > maxLossStreak[seat]) {
      maxLossStreak[seat] = lossStreak[seat];
    }
    winStreak[seat] = 0;
  }

  public void setPush(int seat) {
    pushes[seat]++;
  }
  
  public void setWin(int seat) {
    wins[seat]++;
    winStreak[seat]++;
    if (winStreak[seat] > maxWinStreak[seat]) {
      maxWinStreak[seat] = winStreak[seat];
    }
    lossStreak[seat] = 0;
  }
  
  public String toString() {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < 6; i++) {
      buf.append("Seat ");
      buf.append(i);
      buf.append(" won ");
      buf.append(wins[i]);
      buf.append(" (");
      buf.append(maxWinStreak[i]);
      buf.append(") pushed ");
      buf.append(pushes[i]);
      buf.append(" lost ");
      buf.append(losses[i]);
      buf.append(" (");
      buf.append(maxLossStreak[i]);
      buf.append("). Fortune = ");
      buf.append(fortunes[i]);
      buf.append(" Envy = ");
      buf.append(envies[i]);
      buf.append(".\n");
    }
    return buf.toString();
  }
}
