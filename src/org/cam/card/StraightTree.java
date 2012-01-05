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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a Straight Tree of ICards. A straight of cards might have multiple
 * combinations (e.g. C9, S10, D10, HJ, CQ, HK, SK) has four possible straights.
 * 
 * C9 S10 D10 HJ HJ CQ CQ HK SK HK SK
 * 
 * 
 * 
 * @author Cam Moore
 * 
 */
public class StraightTree {

  /** joker a ICard */
  private ICard joker = null;
  private List<CardNode> rootNodes;

  public StraightTree() {
    super();
  }

//  public StraightTree(ICard card) {
//    super();
//    rootNodes = new ArrayList<CardNode>();
//    if (card.getRank() == ICard.JOKER) {
//      this.joker = card;
//    }
//    else {
//      rootNodes.add(new CardNode(card));
//    }
//  }
  
  public StraightTree(List<ICard> cards) {
    super();
    rootNodes = new ArrayList<CardNode>();
    Collections.sort(cards);
    for (ICard c : cards) {
      addCard(c);
    }
  }

  /**
   * Return the list of root Nodes of the tree.
   * 
   * @return the root element.
   */
  public List<CardNode> getRootElements() {
    return this.rootNodes;
  }

  /**
   * Adds the given ICard to the correct place in the tree. This might add the
   * card multiple times.
   * 
   * @param card The ICard to add to the tree.
   * @return true if the card is added false if it doesn't fit in the tree.
   */
  public void addCard(ICard card) {
    CardNode c = new CardNode(card);
  	if (card.getRank() == ICard.ACE) {
  		// could be ACE, TWO, ...
  		List<CardNode> aceNodes = new ArrayList<CardNode>();
  		for (CardNode root : rootNodes) {
  			if (root.getCard().getRank() == ICard.TWO) {
  				c.addChild(root);
  				aceNodes.add(c);
  			}
  		}
  		for(CardNode ace : aceNodes) {
  			rootNodes.add(ace);
  		}
  		
  		
  		
  		// add card to previous roots
      for (CardNode root : rootNodes) {
        addCardNode(root, c);
      }
      // create new root with card
      if (!rootNodes.contains(c)) {
        rootNodes.add(c);
      }  		  		
  	} else {
      // add card to previous roots
      for (CardNode root : rootNodes) {
        addCardNode(root, c);
      }
      // create new root with card
      if (!rootNodes.contains(c)) {
        rootNodes.add(c);
      }  		
  	}
  }

  public int maxStraightLength() {
    int max = -1;
    for (CardNode root : rootNodes) {
      if (root.depth() > max) {
        max = root.depth();
      }
    }
    return max;
  }

  public int getNumStraights() {
    int sum = 0;
    for (CardNode root : rootNodes) {
      int temp = countLeaves(root);
      int depth = root.depth();
      if (depth >= 5) {
        sum += temp;
      }
    }
    return sum;
  }
  
  public Set<List<ICard>> getStraights() {
    Set<List<ICard>> ret = new HashSet<List<ICard>>();
    List<ICard> start = new ArrayList<ICard>();
    for (CardNode root : rootNodes) {
      start = new ArrayList<ICard>();
      getStraights(ret, start, root);
    }
    return ret;
  }

  public String toString() {
    return rootNodes.toString();
  }
  
  private boolean addCardNode(CardNode root, CardNode toAdd) {
    boolean ret = false;
    int rootRank = root.getCard().getRank();
    int cardRank = toAdd.getCard().getRank();
    if (rootRank + 1 == cardRank) {
      if (!root.getChildren().contains(toAdd)) {
        root.addChild(toAdd);
      }
      return true;
    }
    else {
      for (CardNode c : root.getChildren()) {
        ret &= addCardNode(c, toAdd);
      }
    }
    return ret;
  }

  private int countLeaves(CardNode root) {
    int leaves = 0;
    if (root.isLeaf()) {
      leaves = 1;
    }
    else {
      for (CardNode c : root.getChildren()) {
        leaves += countLeaves(c);
      }
    }
    return leaves;
  }

  private void getStraights(Set<List<ICard>> straights, List<ICard> currentStraight, CardNode node) {
    if (node.isLeaf()) {
      currentStraight.add(node.getCard());
      if (currentStraight.size() >= 5) { // only add straights of five or more cards
        straights.add(currentStraight);
      }
      return;
    } else {
      currentStraight.add(node.getCard());
      for (CardNode c: node.getChildren()) {
        List<ICard> copy = new ArrayList<ICard>();
        for (ICard card : currentStraight) {
          copy.add(card);
        }
        getStraights(straights, copy, c);
      }
    }
  }
}
