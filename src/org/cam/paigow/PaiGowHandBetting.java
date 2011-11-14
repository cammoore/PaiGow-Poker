/**
 * 
 */
package org.cam.paigow;

/**
 * @author ALX
 *
 */
public abstract class PaiGowHandBetting extends BaseHandBetting {

	public PaiGowHandBetting(Double start, Double min) { 
		super(start, min);
	}
	
	/* (non-Javadoc)
	 * @see org.cam.paigao.BaseHandBetting#calcWinnings()
	 */
	@Override
	public Double calcWinnings() {
		return 0.95 * getCurrentBet();
	}

}
