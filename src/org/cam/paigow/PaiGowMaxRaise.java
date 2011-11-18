package org.cam.paigow;


public class PaiGowMaxRaise extends PaiGowHandBetting {


	public PaiGowMaxRaise(Double startStake, Double minBet) {
		super(startStake, minBet);

	}
	

	@Override
	public Double calcStartBet() {
		return getMinBet();
	}

	@Override
	public Double calcBetAfterWin() {
		Double temp = getCurrentBet();
		Integer tempI = temp.intValue();
		Integer val = (tempI / 100) + 1;
		return 2 * temp - (5.0 * val);
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
