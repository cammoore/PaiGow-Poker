/**
 *   StraightTree.java - Copyright (C) Cam Moore 2011.
 * 
 *   This file is part of PaiGowPoker.
 *
 *   PaiGowPoker is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License.
 *
 *   PaiGowPoker is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with PaiGowPoker.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cam.card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Straight Tree of ICards. A straight of cards might have multiple
 * combinations (e.g. C9, S10, D10, HJ, CQ, HK, SK) has four possible straights.
 * 
 *                              C9
 *               S10                            D10
 *               HJ                             HJ
 *               CQ                             CQ
 *          HK         SK                 HK          SK
 *          
 *          
 * 
 * @author Cam Moore
 * 
 */
public class StraightTree {

  private CardNode rootNode;

  public StraightTree(ICard card) {
    super();
    this.rootNode = new CardNode(card);
  }

  /**
   * Return the root Node of the tree.
   * 
   * @return the root element.
   */
  public CardNode getRootElement() {
    return this.rootNode;
  }

  /**
   * Adds the given ICard to the correct place in the tree. This might add the
   * card multiple times.
   * 
   * @param card The ICard to add to the tree.
   * @return true if the card is added false if it doesn't fit in the tree.
   */
  public boolean addCard(ICard card) {
    boolean ret = false;
    int cardRank = card.getRank();
    int rootRank = rootNode.getCard().getRank();
    if (cardRank + 1 == rootRank) {
      CardNode newRoot = new CardNode(card);
      newRoot.addChild(rootNode);
      rootNode = newRoot;
      ret = true;
    }
    if (cardRank == rootRank + 1) {
      rootNode.addChild(new CardNode(card));
      ret = true;
    }
//    for (CardNode c : rootNode.getChildren()
    return ret;
  }
  
  public int maxStraightLength() {
    return rootNode.depth();
  }
  
  
}
