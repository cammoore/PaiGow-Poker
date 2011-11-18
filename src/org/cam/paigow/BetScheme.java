package org.cam.paigow;

import org.cam.card.HandResult;

public abstract class BetScheme {
	
	protected Double startStake;
	protected Double stake;
	protected Double minBet;
	protected Double currentBet;
	protected Integer handsPlayed;
	private Boolean broke;
	private Boolean doubled;
	
	public BetScheme(Double startStake, Double minBet) {
		this.stake = startStake;
		this.startStake = startStake;
		this.minBet = minBet;
		this.currentBet = minBet;
		this.handsPlayed = 0;
		if (startStake > 0) {
			setBroke(Boolean.FALSE);
		} else {
			setBroke(Boolean.TRUE);
		}
		setDoubled(Boolean.FALSE);
	}

	public abstract void update(HandResult result);
	
	public Boolean isBroke() {
		return broke;
	}
	public void setBroke(Boolean broke) {
		this.broke = broke;
	}
	public Boolean isDoubled() {
		return doubled;
	}
	public void setDoubled(Boolean doubled) {
		this.doubled = doubled;
	}
	
	
	public Double getStake() {
		return stake;
	}

	public Double getCurrentBet() {
		return currentBet;
	}

	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(this.getClass().getSimpleName());
		b.append(" hands played ");
		b.append(handsPlayed);
		b.append(" stake ");
		b.append(stake);
		b.append(" current bet ");
		b.append(currentBet);
		if(isDoubled()) {
			b.append(" doubled.");
		}
		if(isBroke()) {
			b.append(" broke.");
		}
		return b.toString();
	}
	
	
}
