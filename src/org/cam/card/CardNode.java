/**
 *   CardNode.java - Copyright (C) Cam Moore 2011.
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
 * @author Cam Moore
 *
 */
/**
 * Represents a node of the card tree. The CardNode is also a container,
 * and can be thought of as instrumentation to determine the location of the
 * ICard in the CardTree.
 */
public class CardNode implements ICardNodeElement {

  private ICard card;
  private CardNode parent;
  private List<CardNode> children;

  /**
   * Default constructor.
   */
  public CardNode() {
    super();
    this.children = new ArrayList<CardNode>();
  }

  /**
   * Convenience constructor to create a CardNode with an instance of ICard.
   * 
   * @param card an instance of ICard.
   */
  public CardNode(ICard card) {
    this();
    this.card = card;
  }
  
  /**
   * Constructs a CardNode instance.
   * @param cn the CardNode to copy.
   */
  public CardNode(CardNode cn) {
    this();
    this.card = cn.getCard();
  }

  /**
   * Adds a child to the list of children for this CardNode. The addition of the
   * first child will create a new List<CardNode>.
   * 
   * @param child a CardNode object to set.
   */
  public void addChild(CardNode child) {
    if (children == null) {
      children = new ArrayList<CardNode>();
    }
    children.add(child);
    child.parent = this;
  }

  /**
   * @return The card value a ICard.
   */
  public ICard getCard() {
    return this.card;
  }

  /**
   * Return the children of CardNode. The CardTree is represented by a single
   * root CardNode whose children are represented by a List<CardNode>. Each of
   * these CardNode elements in the List can have children. The getChildren()
   * method will return the children of a CardNode.
   * 
   * @return the children of CardNode
   */
  public List<CardNode> getChildren() {
    if (this.children == null) {
      return new ArrayList<CardNode>();
    }
    return this.children;
  }

  /**
   * Returns the number of immediate children of this CardNode.
   * 
   * @return the number of immediate children.
   */
  public int getNumberOfChildren() {
    if (children == null) {
      return 0;
    }
    return children.size();
  }

  /**
   * @return The parent of this CardNode.
   */
  public CardNode getParent() {
    return parent;
  }
  
  /**
   * @return The root parent of this node. The root parent doesn't
   * have a parent.
   */
  public CardNode getRootParent() {
    CardNode ret = this;
    while (ret.getParent() != null) {
      ret = ret.getParent();
    }
    return ret;
  }
  
  public boolean hasJokerAncestor() {
    boolean ret = false;
    CardNode r = this;
    while (r.getParent() != null) {
      r = r.getParent();
      ret |= r.getCard().isJoker();
    }
    return ret;
  }
  
  /**
   * @return the Rank of the card in this CardNode.
   */
  public int getRank() {
    return card.getRank();
  }
  
  public int depth() {
    int max = 0;
    for (CardNode c : getChildren()) {
      if (c.depth() > max) {
        max = c.depth();
      }
    }
    return max + 1;
  }

  public boolean hasJoker() {
    boolean ret = card.isJoker();
    for (CardNode c : getChildren()) {
      ret |= c.hasJoker();
    }
    return ret;
  }
  
  @Override
  public boolean equals(Object o) {
    try {
      if (o == null) {
        return false;
      } else {
        CardNode that = (CardNode) o;
        return card.equals(that.getCard());
      }
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * 
   * @return
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return card.hashCode();
  }

  public boolean isLeaf() {
    if (children == null) {
      return true;
    } else {
      return children.size() == 0;
    }
  }
  
  public void removeChild(CardNode child) {
    if (children != null) {
      children.remove(child);
    }
  }

  /**
   * Sets the card value.
   * 
   * @param card the new card value.
   */
  public void setCard(ICard card) {
    this.card = card;
  }

  /**
   * Sets the children of a CardNode object. See docs for getChildren() for more
   * information.
   * 
   * @param children the List<CardNode> to set.
   */
  public void setChildren(List<CardNode> children) {
    this.children = children;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{").append(getCard().toString()).append(",[");
    int i = 0;
    for (CardNode e : getChildren()) {
      if (i > 0) {
        sb.append(",");
      }
      sb.append(e.toString());
      i++;
    }
    sb.append("]").append("}");
    return sb.toString();
  }

  /**
   * 
   * @param visitor
   * @see org.cam.card.ICardNodeElement#accept(org.cam.card.ICardNodeVisitor)
   */
  @Override
  public void accept(ICardNodeVisitor visitor) {
    visitor.visit(this);
    for (CardNode c : getChildren()) {
      visitor.visit(c);
    }
  }
}