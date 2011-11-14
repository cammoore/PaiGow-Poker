/**
 * 
 */
package org.cam.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Cam Moore
 *
 */
public class Deck implements Iterator<ICard> {

  protected List<ICard> cards;
  private int index;
  
  /**
   * Constructs a Deck instance.
   */
  public Deck() {
    this.cards = new ArrayList<ICard>();
    for (int suit = ICard.SPADES; suit <= ICard.CLUBS; suit++) {
      for (int rank = 2; rank <= 14; rank++) {
        cards.add(new Card(suit, rank));
      }
    }
    shuffle();
  }
  
  /**
   * Shuffles the deck.
   */
  private void shuffle() {
    Collections.shuffle(cards);
    index = 0;
  }

  /**
   * 
   * @return true if there are more cards in the deck.
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return index < cards.size();
  }

  /**
   * 
   * @return the next card in the deck. 
   * @see java.util.Iterator#next()
   */
  @Override
  public ICard next() {
    return cards.get(index++);
  }

  /**
   * 
   * @see java.util.Iterator#remove()
   */
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
