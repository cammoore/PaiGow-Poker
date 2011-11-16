package org.cam.paigow;

import org.cam.card.HandResult;

public abstract class BaseHandBetting {

	private Double startStake;
	private Double minBet;
	
	private Double stack;
	private Double currentBet;
	private Integer handsPlayed;
	
	public BaseHandBetting(Double start, Double min) {
		this.startStake = start;
		this.stack = start;
		this.currentBet = 0.0;
		this.minBet = min;
		this.currentBet = calcStartBet();
		this.stack = this.startStake - this.currentBet;
		this.handsPlayed = 0;
	}
	
	
	public abstract Double calcWinnings();
	public abstract Double calcStartBet();
	public abstract Double calcBetAfterWin();
	public abstract Double calcBetAfterPush();
	public abstract Double calcBetAfterLoss();
	
	public void update(HandResult result) {
		if (!isBroke()) {
			handsPlayed++;
			switch (result) {
			case WIN:
				// add the winnings to the stack
				stack += calcWinnings();
				// return the current bet to the stack
				stack += currentBet;
				// calculate the next bet
				currentBet = calcBetAfterWin();
				// pull that bet from the stack
				stack -= currentBet;
				break;
			case PUSH:
				// return the current bet to the stack
				stack += currentBet;
				// calculate the next bet
				currentBet = calcBetAfterPush();
				// pull that bet from the stack
				stack -= currentBet;				
				break;
			case LOSE:
				// calculate the next bet
				currentBet = calcBetAfterLoss();
				if (currentBet > stack) {
					// we're done can't bet
					currentBet = 0.0;
				}
				// pull that bet from the stack
				stack -= currentBet;
				break;
			default:
				// do nothing	
			}
		}
	}

	public Double getStake() {
		return this.currentBet + this.stack;
	}
	
	public Integer getHandsPlayed() {
		return handsPlayed;
	}

	public Double getCurrentBet() {
		return currentBet;
	}
	
	public Double getMinBet() {
		return minBet;
	}


	public boolean isBroke() {
		if (this.stack + this.currentBet > minBet) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean isDouble() {
		if (this.stack + this.currentBet >= 2.0 * startStake) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(this.getClass().getSimpleName());
		b.append(" hands played ");
		b.append(handsPlayed);
		b.append(" stake ");
		b.append(getStake());
		b.append(" current bet ");
		b.append(getCurrentBet());
		if(isBroke()) {
			b.append(" broke.");
		}
		return b.toString();
	}

}
