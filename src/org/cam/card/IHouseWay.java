/**
 * HouseWay.java
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
	 * Sets the PaiGowHand from the next seven cards from the PaiGowDeck.
	 * 
	 * @param d
	 *          a PaiGowDeck.
	 * @return The set PaiGowHand.
	 */
	public PaiGowHand setHand(PaiGowDeck d);
}
