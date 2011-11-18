package org.cam.paigow;


public class PaiGowNoRaise extends PaiGowHandBetting {

	public PaiGowNoRaise(Double startStake, Double minBet) {
		super(startStake, minBet);
	}

	@Override
	public Double calcStartBet() {
		return getMinBet();
	}

	@Override
	public Double calcBetAfterWin() {
		return getMinBet();
	}

	@Override
	public Double calcBetAfterPush() {
		return getMinBet();
	}

	@Override
	public Double calcBetAfterLoss() {
		return getMinBet();
	}

	

}
