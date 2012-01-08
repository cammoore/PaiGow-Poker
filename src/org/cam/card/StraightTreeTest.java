/**
 *   StraightTreeTest.java - Copyright (C) Cam Moore 2011.
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

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;

/**
 * @author Cam Moore
 * 
 */
public class StraightTreeTest {

	private PaiGowHand hand;
	private ArrayList<ICard> cards;
	private StraightTree tree;

	@Test
	public void test() {
		cards = new ArrayList<ICard>();
		cards.add(new Card(ICard.SPADES, ICard.QUEEN));
		cards.add(new Card(ICard.DIAMONDS, ICard.QUEEN));
		cards.add(new Card(ICard.SPADES, ICard.KING));
		cards.add(new Card(ICard.SPADES, ICard.JACK));
		cards.add(new Card(ICard.SPADES, ICard.ACE));
		cards.add(new Card(ICard.SPADES, ICard.TEN));
		cards.add(new Card(ICard.CLUBS, ICard.JACK));
		Collections.sort(cards);
		System.out.println(cards);
		tree = new StraightTree(cards);
		System.out.println(tree);
		CardNode root = tree.getRootElements().get(0);
		assertTrue("2 != " + root.getNumberOfChildren(),
				root.getNumberOfChildren() == 2);
		for (CardNode c : root.getChildren()) {
			assertTrue("2 != " + c.getNumberOfChildren(),
					c.getNumberOfChildren() == 2);
			for (CardNode c1 : c.getChildren()) {
				assertTrue(c1 + "1 != " + c1.getNumberOfChildren(),
						c1.getNumberOfChildren() == 1);
			}
		}
		assertTrue("4 != " + tree.getNumStraights(), tree.getNumStraights() == 4);
		System.out.println(tree.getStraights());
		assertTrue("4 != " + tree.getStraights().size(), tree.getStraights().size() == 4);
	}

	@Test
  public void testAceTwoStraight() {
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.TWO));
    cards.add(new Card(ICard.CLUBS, ICard.FIVE));
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.ACE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.HEARTS, ICard.TEN));
    cards.add(new Card(ICard.CLUBS, ICard.JACK));
    Collections.sort(cards);
    System.out.println(cards);
    tree = new StraightTree(cards);
    assertTrue("Got " + tree.getStraights().size() + " want 1", tree
        .getStraights().size() == 1);
    System.out.println(tree.getStraights());

    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.CLUBS, ICard.TWO));
    cards.add(new Card(ICard.DIAMONDS, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.HEARTS, ICard.FOUR));
    cards.add(new Card(ICard.HEARTS, ICard.FIVE));
    cards.add(new Card(ICard.CLUBS, ICard.JACK));
    Collections.sort(cards);
    System.out.println(cards);
    tree = new StraightTree(cards);
    System.out.println(tree);
    assertTrue("2 straights", tree.getStraights().size() == 2);
    
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.DIAMONDS, ICard.ACE));
    cards.add(new Card(ICard.SPADES, ICard.TWO));
    cards.add(new Card(ICard.SPADES, ICard.THREE));
    cards.add(new Card(ICard.SPADES, ICard.FOUR));
    cards.add(new Card(ICard.SPADES, ICard.FIVE));
    cards.add(new Card(ICard.CLUBS, ICard.FOUR));
		Collections.sort(cards);
		System.out.println(cards);
		tree = new StraightTree(cards);
		System.out.println("tree: " + tree);
		System.out.println("straights " + tree.getStraights());
		assertTrue(tree.getNumStraights() + " != 4", tree.getNumStraights() == 4);
	}

	@Test
	public void testMultipleAceTwoStraight() {
    cards = new ArrayList<ICard>();
    cards.add(new Card(ICard.SPADES, ICard.ACE));

    
    
    cards.add(new Card(ICard.SPADES, ICard.TEN));
    cards.add(new Card(ICard.CLUBS, ICard.JACK));
    Collections.sort(cards);
    System.out.println(cards);
    tree = new StraightTree(cards);
    System.out.println(tree);
    CardNode root = tree.getRootElements().get(0);
    assertTrue("2 != " + root.getNumberOfChildren(), root.getNumberOfChildren() == 2);
    for (CardNode c : root.getChildren()) {
      assertTrue("2 != " + c.getNumberOfChildren(), c.getNumberOfChildren() == 2);
      for (CardNode c1 : c.getChildren()) {
        assertTrue(c1 + "1 != " + c1.getNumberOfChildren(), c1.getNumberOfChildren() == 1);
      }
    }
    assertTrue("4 != "+ tree.getNumStraights(), tree.getNumStraights() == 4);
    System.out.println(tree.getStraights());
    assertTrue(tree.getStraights().size() == 4);
  }
	
	@Test
	public void testPlainStraight() {
		cards = new ArrayList<ICard>();
		cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
		cards.add(new Card(ICard.CLUBS, ICard.SEVEN));
		cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
		cards.add(new Card(ICard.SPADES, ICard.EIGHT));
		cards.add(new Card(ICard.HEARTS, ICard.NINE));
		cards.add(new Card(ICard.HEARTS, ICard.TEN));
		cards.add(new Card(ICard.CLUBS, ICard.JACK));
		Collections.sort(cards);
		System.out.println(cards);
		tree = new StraightTree(cards);
		// three straights, 7, 6, and 5 cards.
		assertTrue(tree.getStraights().size() == 3);
		System.out.println(tree.getStraights());
	}
    
  @Test
	public void testRandomCards() {
		cards = new ArrayList<ICard>();
		cards.add(new Card(ICard.DIAMONDS, ICard.FIVE));
		cards.add(new Card(ICard.CLUBS, ICard.JACK));
		cards.add(new Card(ICard.DIAMONDS, ICard.SIX));
		cards.add(new Card(ICard.SPADES, ICard.JACK));
		cards.add(new Card(ICard.HEARTS, ICard.NINE));
		cards.add(new Card(ICard.HEARTS, ICard.NINE));
		cards.add(new Card(ICard.CLUBS, ICard.SIX));
		Collections.sort(cards);
		System.out.println(cards);
		tree = new StraightTree(cards);
		System.out.println(tree.getRootElements());
		// no straights in hand
		assertTrue(tree.getStraights().size() == 0);
	}
  
}
