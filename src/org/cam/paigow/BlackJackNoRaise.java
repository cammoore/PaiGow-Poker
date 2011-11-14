package org.cam.paigow;

import org.cam.card.HandResult;

public class BlackJackNoRaise extends BetScheme {

	public BlackJackNoRaise(Double startStake, Double minBet) {
		super(startStake, minBet);
	}

	@Override
	public void update(HandResult result) {
		if (!isBroke()) {
			handsPlayed++;
			switch (result) {
			case WIN:
				stake += currentBet;
				if (stake > 2 * startStake) {
					setDoubled(Boolean.TRUE);
				}
				break;
			case PUSH:
				// do nothing.
				break;
			case LOSE:
				stake -= currentBet;
				if (stake <= 0.0) {
					setBroke(Boolean.TRUE);
				}
				break;
			default:
				// do nothing.
			}
		}
	}

}
