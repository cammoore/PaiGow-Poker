/**
 * IHouseWay.java
 * Copyright (C) 2011  Cam Moore
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cam.card;

/**
 * @author Cam Moore
 * 
 */
public interface IHouseWay {

	/**
	 * Sets the PaiGowHand from the raw seven cards in the PaiGowHand.
	 * 
	 * @param hand
	 *          a PaiGowHand.
	 */
	public void setHand(PaiGowHand hand);
	
	public void playFiveAces(PaiGowHand hand);
	public void playRoyalFlush(PaiGowHand hand);
	public void playStraightFlush(PaiGowHand hand);
	public void playFourOfAKind(PaiGowHand hand);
	public void playFullHouse(PaiGowHand hand);
	public void playFlushOrStraight(PaiGowHand hand);
	public void playThreeOfAKind(PaiGowHand hand);
	public void playThreePair(PaiGowHand hand);
	public void playTwoPair(PaiGowHand hand);
	public void playPair(PaiGowHand hand);
	public void playPaiGow(PaiGowHand hand);
}
