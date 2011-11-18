package org.cam.paigow;


public class PaiGowRaiseFive extends PaiGowHandBetting {

	public PaiGowRaiseFive(Double startStake, Double minBet) {
		super(startStake, minBet);
	}
	
	@Override
	public Double calcStartBet() {
		return getMinBet();
	}

	@Override
	public Double calcBetAfterWin() {
		return getCurrentBet() + 5.0;
	}

	@Override
	public Double calcBetAfterPush() {
		return getCurrentBet();
	}

	@Override
	public Double calcBetAfterLoss() {
		return getMinBet();
	}


}
