package org.cam.paigow;

public class PaiGowBetHalf extends PaiGowHandBetting {

	public PaiGowBetHalf(Double start, Double min) {
		super(start, min);
	}
	
	@Override
	public Double calcStartBet() {
		return roundDownToFive(getStake() / 2.0);
	}

	@Override
	public Double calcBetAfterWin() {
		return roundDownToFive(getStake() / 2.0);
	}

	@Override
	public Double calcBetAfterPush() {
		return roundDownToFive(getStake() / 2.0);
	}

	@Override
	public Double calcBetAfterLoss() {
		return roundDownToFive(getStake() / 2.0);
	}

	private Double roundDownToFive(Double val) {
		Integer iVal = val.intValue();
		int div = iVal / 5;
		return 5.0 * iVal;
	}
}
