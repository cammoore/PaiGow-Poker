/**
 * FlushMap.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cam Moore
 *
 */
public class FlushMap {

  private ICard joker = null;
  private Map<Integer, List<ICard>> flushes;
  
  public FlushMap(List<ICard> cards) {
  	this.flushes = new HashMap<Integer, List<ICard>>();
  	this.flushes.put(ICard.SPADES, new ArrayList<ICard>());
  	this.flushes.put(ICard.HEARTS, new ArrayList<ICard>());
  	this.flushes.put(ICard.DIAMONDS, new ArrayList<ICard>());
  	this.flushes.put(ICard.CLUBS, new ArrayList<ICard>());
  	
  	for (ICard c : cards) {
  		if (c.getRank() == ICard.JOKER) {
  			this.joker = c;
  			flushes.get(ICard.SPADES).add(c);
  			flushes.get(ICard.HEARTS).add(c);
  			flushes.get(ICard.DIAMONDS).add(c);
  			flushes.get(ICard.CLUBS).add(c);
  		}
  		else {
    		List<ICard> f = flushes.get(c.getSuit());
    		f.add(c);
  		}
  	}
  }
  
  public List<ICard> getFlush() {
  	List<ICard> ret = new ArrayList<ICard>();
  	for (Integer s : flushes.keySet()) {
  		if (flushes.get(s).size() >= 5) {
  			ret = flushes.get(s);
  		}
  	}
  	
  	return ret;
  }
  
  public Map<Integer, List<ICard>> getFlushes() {
  	return flushes;
  }
}
