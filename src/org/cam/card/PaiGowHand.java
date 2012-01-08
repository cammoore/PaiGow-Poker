/**
 * 
 */
package org.cam.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author Cam Moore
 * 
 */
public final class PaiGowHand extends Hand {

	private List<ICard> lowHand;
	private List<ICard> highHand;
	
	private StraightTree straights;
	private FlushMap flushes;

	/**
	 * Constructs a PaiGaoHand instance.
	 * 
	 * @param c
	 *          the cards.
	 */
	public PaiGowHand(List<ICard> c) {
		assert (c.size() == 7);
		lowHand = new ArrayList<ICard>();
		highHand = new ArrayList<ICard>();
		for (ICard card : c) {
			cards.add(card);
		}
		straights = new StraightTree(c);
		flushes = new FlushMap(c);
		houseWay();
	}

	/**
	 * Constructs a PaiGaoHand instance. By dealing the next seven cards from the
	 * deck.
	 * 
	 * @param d
	 *          the deck to deal from.
	 */
	public PaiGowHand(PaiGowDeck d) {
		super(d, 7);
		lowHand = new ArrayList<ICard>();
		highHand = new ArrayList<ICard>();
		houseWay();
	}

	/**
	 * Compares this hand as the Banker's hand to the other hand. Returns the
	 * result as a HandResult.
	 * 
	 * @param other
	 *          a PaiGowHand, the other hand.
	 * @return The HandResult.
	 */
	public HandResult compareAsBanker(PaiGowHand other) {
		HandResult ret = null;
		try {
			int lowResult = 1;
			// compare low hands
			Collections.sort(lowHand);
			Collections.sort(other.lowHand);
			int end = 1;
			// check for pairs
			boolean lowPair = (lowHand.get(0).getRank() == lowHand.get(end).getRank())
					|| (lowHand.get(0).isJoker() && lowHand.get(end).getRank() == ICard.ACE);
			boolean otherLowPair = (other.lowHand.get(0).getRank() == other.lowHand
					.get(end).getRank())
					|| (other.lowHand.get(0).isJoker() && other.lowHand.get(end)
							.getRank() == ICard.ACE);
			if (lowPair && otherLowPair) {
				// check rank
				if (lowHand.get(0).getRank() >= other.lowHand.get(0).getRank()) {
					lowResult = 1;
				} else if (lowHand.get(0).getRank() < other.lowHand.get(0).getRank()) {
					lowResult = -1;
				}
			} else if (lowPair) {
				// we win
				lowResult = 1;
			} else if (otherLowPair) {
				// we lose
				lowResult = -1;
			} else {
				// no pair compare ranks
				if (lowHand.get(0).getRank() > other.lowHand.get(0).getRank()) {
					// our low is higher then other
					lowResult = 1;
				} else if (lowHand.get(0).getRank() < other.lowHand.get(0).getRank()) {
					// our low is less than other
					lowResult = -1;
				} else {
					// first low is equal check second card
					if (lowHand.get(end).getRank() > other.lowHand.get(end).getRank()) {
						// our low is higher then other
						lowResult = 1;
					} else if (lowHand.get(end).getRank() < other.lowHand.get(end)
							.getRank()) {
						// our low is less than other
						lowResult = -1;
					} else {
						// tie banker wins
						lowResult = 1;
					}
				}
				// System.out.println(lowHand + " vs " + other.lowHand + " result is " +
				// lowResult);
				// compare high hand
				int highResult = 0;
				if (PokerHand.getRanking(highHand) > PokerHand
						.getRanking(other.highHand)) {
					highResult = 1;
				} else if (PokerHand.getRanking(highHand) < PokerHand
						.getRanking(other.highHand)) {
					highResult = -1;
				} else { // ranking are same so compare
					end = 4;
					if (highHand.get(0).isJoker()) {
						if (other.highHand.get(end).getRank() == ICard.ACE) {
							if (highHand.get(end).getRank() > other.highHand.get(0).getRank()) {
								highResult = 1;
							} else if (highHand.get(end).getRank() < other.highHand.get(0)
									.getRank()) {
								highResult = -1;
							} else {
								highResult = 1;
							}
						} else {
							highResult = 1;
						}
					} else if (other.highHand.get(0).isJoker()) {
						if (highHand.get(end).getRank() == ICard.ACE) {
							if (highHand.get(0).getRank() > other.highHand.get(end).getRank()) {
								highResult = 1;
							} else if (highHand.get(0).getRank() < other.highHand.get(end)
									.getRank()) {
								highResult = -1;
							} else {
								highResult = 1;
							}
						} else {
							highResult = -1;
						}
					} else { // no joker
						if (highHand.get(end).getRank() > other.highHand.get(end).getRank()) {
							highResult = 1;
						} else if (highHand.get(end).getRank() < other.highHand.get(end)
								.getRank()) {
							highResult = -1;
						} else {
							if (highHand.get(0).getRank() > other.highHand.get(0).getRank()) {
								highResult = 1;
							} else if (highHand.get(0).getRank() < other.highHand.get(0)
									.getRank()) {
								highResult = -1;
							} else {
								highResult = 1;
							}
						}
					}
				}
				if (lowResult == 1 && highResult == 1) {
					return HandResult.WIN;
				} else if (lowResult == -1 && highResult == -1) {
					return HandResult.LOSE;
				} else {
					return HandResult.PUSH;
				}
			}
		} catch (Exception e) {
			System.err.println(this.cards + " " + this.toString() + " vs "
					+ other.toString());
			e.printStackTrace();
			return HandResult.PUSH;
		}
		// System.out.println(highHand + " vs " + other.highHand + " result is " +
		// highResult);
		// System.out.println(this.toString() + " vs " + other.toString() +
		// " result is " + ret);
		return ret;
	}

	/**
	 * Compares this hand to the other hand. If this hand beats the other hand
	 * returns 1, 0 if they push, -1 if this hand loses.
	 * 
	 * @param other
	 *          a PaiGaohand.
	 * @return 1, 0, or -1 if this hand beats, pushes or loses to the other hand.
	 */
	public HandResult compareToBanker(PaiGowHand other) {
		try {
			int lowResult = -1;
			// compare low hands
			Collections.sort(lowHand);
			Collections.sort(other.lowHand);
			int end = 1;
			// check for pairs
			boolean lowPair = (lowHand.get(0).getRank() == lowHand.get(end).getRank())
					|| (lowHand.get(0).isJoker() && lowHand.get(end).getRank() == ICard.ACE);
			boolean otherLowPair = (other.lowHand.get(0).getRank() == other.lowHand
					.get(end).getRank())
					|| (other.lowHand.get(0).isJoker() && other.lowHand.get(end)
							.getRank() == ICard.ACE);
			if (lowPair && otherLowPair) {
				// check rank
				if (lowHand.get(0).getRank() >= other.lowHand.get(0).getRank()) {
					lowResult = 1;
				} else if (lowHand.get(0).getRank() < other.lowHand.get(0).getRank()) {
					lowResult = -1;
				}
			} else if (lowPair) {
				// we win
				lowResult = 1;
			} else if (otherLowPair) {
				// we lose
				lowResult = -1;
			} else {
				// no pair compare ranks
				if (lowHand.get(0).getRank() > other.lowHand.get(0).getRank()) {
					// our low is higher then other
					lowResult = 1;
				} else if (lowHand.get(0).getRank() < other.lowHand.get(0).getRank()) {
					// our low is less than other
					lowResult = -1;
				} else {
					// first low is equal check second card
					if (lowHand.get(end).getRank() > other.lowHand.get(end).getRank()) {
						// our low is higher then other
						lowResult = 1;
					} else if (lowHand.get(end).getRank() < other.lowHand.get(end)
							.getRank()) {
						// our low is less than other
						lowResult = -1;
					} else {
						// tie banker wins
						lowResult = -1;
					}
				}
			}
			// System.out.println(lowHand + " vs " + other.lowHand + " result is " +
			// lowResult);
			// compare high hand
			int highResult = -1;
			if (PokerHand.getRanking(highHand) > PokerHand.getRanking(other.highHand)) {
				highResult = 1;
			} else if (PokerHand.getRanking(highHand) < PokerHand
					.getRanking(other.highHand)) {
				highResult = -1;
			} else { // ranking are same so compare
				end = 4;
				if (highHand.get(0).isJoker()) {
					if (other.highHand.get(end).getRank() == ICard.ACE) {
						if (highHand.get(end).getRank() > other.highHand.get(0).getRank()) {
							highResult = 1;
						} else if (highHand.get(end).getRank() < other.highHand.get(0)
								.getRank()) {
							highResult = -1;
						} else {
							highResult = -1;
						}
					} else {
						highResult = 1;
					}
				} else if (other.highHand.get(0).isJoker()) {
					if (highHand.get(end).getRank() == ICard.ACE) {
						if (highHand.get(0).getRank() > other.highHand.get(end).getRank()) {
							highResult = 1;
						} else if (highHand.get(0).getRank() < other.highHand.get(end)
								.getRank()) {
							highResult = -1;
						} else {
							highResult = -1;
						}
					} else {
						highResult = -1;
					}
				} else { // no joker
					if (highHand.get(end).getRank() > other.highHand.get(end).getRank()) {
						highResult = 1;
					} else if (highHand.get(end).getRank() < other.highHand.get(end)
							.getRank()) {
						highResult = -1;
					} else {
						if (highHand.get(0).getRank() > other.highHand.get(0).getRank()) {
							highResult = 1;
						} else if (highHand.get(0).getRank() < other.highHand.get(0)
								.getRank()) {
							highResult = -1;
						} else {
							highResult = -1;
						}
					}
				}
			}
			if (lowResult == 1 && highResult == 1) {
				return HandResult.WIN;
			} else if (lowResult == -1 && highResult == -1) {
				return HandResult.LOSE;
			} else {
				return HandResult.PUSH;
			}
		} catch (Exception e) {
			System.err.println(this.cards + " " + this.toString() + " vs "
					+ other.toString());
			e.printStackTrace();
			return HandResult.PUSH;
		}
		// System.out.println(highHand + " vs " + other.highHand + " result is " +
		// highResult);
		// System.out.println(this.toString() + " vs " + other.toString() +
		// " result is " + ret);
	}

	/**
	 * @return a String description of the hand.
	 */
	public String description() {
		StringBuilder sb = new StringBuilder();
		if (isPaiGow()) {
			if (isNineHighPaiGao()) {
				sb.append("9 high pai gow");
			}
			if (isTenHighPaiGao()) {
				sb.append("10 high pai gow");
			}
			if (isJackHighPaiGao()) {
				sb.append("Jack high pai gow");
			}
			if (isQueenHighPaiGao()) {
				sb.append("Queen high pai gow");
			}
			if (isKingHighPaiGao()) {
				sb.append("King high pai gow");
			}
			if (isAceHighPaiGao()) {
				sb.append("Ace high pai gow");
			}
		} else {
			if (hasFiveAces()) {
				sb.append("Five Aces");
			} else if (hasRoyalFlush()) {
				sb.append("Royal Flush");
			} else if (hasStraightFlush()) {
				sb.append("Straight Flush");
			} else if (hasFourOfAKind()) {
				sb.append("Four of a Kind");
			} else if (hasFullHouse()) {
				sb.append("Full House");
			} else if (hasFlush()) {
				sb.append("Flush");
			} else if (hasThreeOfAKind()) {
				sb.append("Three of a Kind");
			} else if (hasStraight()) {
				sb.append("Straight");
			} else if (hasThreePair()) {
				sb.append("Three Pair");
			} else if (hasTwoPair()) {
				sb.append("Two Pair");
			} else if (hasPair()) {
				sb.append("Pair");
			}
		}
		return sb.toString();
	}

	/**
	 * @return the high hand as a List of ICards.
	 */
	public List<ICard> getHighHand() {
		ArrayList<ICard> ret = new ArrayList<ICard>();
		ret.add(highHand.get(0));
		ret.add(highHand.get(1));
		ret.add(highHand.get(2));
		ret.add(highHand.get(3));
		ret.add(highHand.get(4));
		return ret;
	}

	/**
	 * @return the high hand as a List of ICards.
	 */
	public List<ICard> getLowHand() {
		ArrayList<ICard> ret = new ArrayList<ICard>();
		ret.add(lowHand.get(0));
		ret.add(lowHand.get(1));
		return ret;
	}

	public boolean has7CardStraightFlush() {
		return super.isStraightFlush();
		// boolean ret = false;
		// Collections.sort(cards);
		// ArrayList<ICard> temp = new ArrayList<ICard>();
		// for (int j = 0; j < 7; j++) {
		// temp.add(cards.get(j));
		// }
		// if (Card.isStraight(temp) && Card.isFlush(temp)) {
		// ret = true;
		// }
		// return ret;
	}

	/**
	 * @return true if the hand has five aces.
	 */
	public boolean hasFiveAces() {
		boolean ret = false;
		if (hasFourOfAKind() && hasJoker()) {
			int aces = numAces();
			if (aces == 4) {
				ret = true;
			}
		}
		return ret;
	}

	public boolean hasFiveCardStraightWithJoker() {
		if (hasJoker()) {
			// sort the cards
			Collections.sort(cards);
			boolean ret = false;
			boolean usedJokerp = false;
			int startRank = -1;
			for (int i = 0; i < 3; i++) {
				startRank = cards.get(i).getRank();
				int runCount = 1;
				for (int j = 0; j < 4; j++) { // looking for the next four cards
					if (handHasNextRank(startRank)) {
						startRank++;
						runCount++;
					} else if (hasJokerFillNextRank(startRank) && !usedJokerp) {
						usedJokerp = true;
						startRank++;
						runCount++;
					}
				}
				if (runCount == 5) {
					return true;
				}
			}
			return ret;
		} else {
			return false;
		}
	}

	/**
	 * @return true if the hand has a flush.
	 */
	public boolean hasFlush() {
		int numSpades = 0;
		int numHearts = 0;
		int numDiamonds = 0;
		int numClubs = 0;
		for (ICard c : cards) {
			switch (c.getSuit()) {
			case ICard.SPADES:
				numSpades++;
				break;
			case ICard.HEARTS:
				numHearts++;
				break;
			case ICard.DIAMONDS:
				numDiamonds++;
				break;
			case ICard.CLUBS:
				numClubs++;
				break;
			default:
				// do nothing.
			}
		}
		if (numSpades >= 5 || numHearts >= 5 || numDiamonds >= 5 || numClubs >= 5) {
			return true;
		}
		if ((numSpades >= 4 || numHearts >= 4 || numDiamonds >= 4 || numClubs >= 4)
				&& Card.hasJoker(cards)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return true if has a four of a kind.
	 * @see org.cam.card.Hand#hasFourOfAKind()
	 */
	@Override
	public boolean hasFourOfAKind() {
		boolean ret = super.hasFourOfAKind();
		if (hasJoker() && !ret) {
			// count the aces
			int aces = numAces();
			ret = aces == 3;
		}
		return ret;
	}

	/**
	 * 
	 * @return true if has a full house.
	 * @see org.cam.card.Hand#hasFullHouse()
	 */
	@Override
	public boolean hasFullHouse() {
		boolean ret = super.hasFullHouse();
		if (hasJoker() && !ret) {
			int aces = numAces();
			if (super.hasTwoPair() && aces == 2) {
				ret = true;
			} else if (super.hasThreeOfAKind() && aces == 1) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * @return true if hand has the joker.
	 */
	public boolean hasJoker() {
		for (ICard c : cards) {
			if (c.getSuit() == ICard.PAI_GAO_JOKER) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return true if has a pair.
	 * @see org.cam.card.Hand#hasPair()
	 */
	@Override
	public boolean hasPair() {
		boolean ret = super.hasPair();
		if (hasJoker() && !ret) {
			int aces = numAces();
			if (aces == 1) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * @return true if has a royal flush.
	 */
	public boolean hasRoyalFlush() {
		boolean straightp = false;
		List<ICard> straightFlush = getStraightFlush();
		Collections.sort(straightFlush);
		int size = straightFlush.size();
//		System.out.println("Straight Flush cards " + straightFlush);
		if (straightFlush.size() >= 5) {
			ICard topCard = straightFlush.get(size - 1);
			ICard nextCard = straightFlush.get(size - 2);
			if (topCard.getRank() == Card.ACE && nextCard.getRank() == ICard.KING) {
				straightp = true;
			} else if (topCard.getRank() == Card.KING
					&& straightFlush.get(0).getRank() == Card.JOKER) {
				straightp = true; // use joker for ace.
			}
		}
		return straightp;
	}

	// Collections.sort(cards);
	// // System.out.println(cards);
	// // check for simple 5 card straights
	// for (int i = 0; i < 3; i++) {
	// ArrayList<ICard> temp = new ArrayList<ICard>();
	// for (int j = 0; j < 5; j++) {
	// temp.add(cards.get(i + j));
	// }
	// if (Card.isStraight(temp)) {
	// return true;
	// }
	// }
	// // move ace if any check 1,2,3,4,5
	// if (cards.get(cards.size() - 1).getRank() == Card.ACE) {
	// for (int i = 0; i < 3; i++) {
	// ArrayList<ICard> temp = new ArrayList<ICard>();
	// temp.add(cards.get(cards.size() - 1));
	// for (int j = 0; j < 4; j++) {
	// temp.add(cards.get(i + j));
	// }
	// if (Card.isStraight(temp)) {
	// return true;
	// }
	// }
	// }
	//
	// this.runMap = new HashMap<Integer, ArrayList<Integer>>();
	// this.runIndexMap = new HashMap<Integer, ArrayList<Integer>>();
	// this.gapIndexMap = new HashMap<Integer, ArrayList<Integer>>();
	// ArrayList<Integer> runs = new ArrayList<Integer>();
	// ArrayList<Integer> gaps = new ArrayList<Integer>();
	// ArrayList<Integer> runsIndex = new ArrayList<Integer>();
	// ArrayList<Integer> gapsIndex = new ArrayList<Integer>();
	// for (int i = 0; i < cards.size() - 1; i++) {
	// if (cards.get(i).getRank() + 1 == cards.get(i + 1).getRank()) {
	// if (!gaps.isEmpty()) {
	// // gapMap.put(gaps.get(0), gaps);
	// gapIndexMap.put(gaps.get(0), gapsIndex);
	// gaps = new ArrayList<Integer>();
	// gapsIndex = new ArrayList<Integer>();
	// }
	// if (!runs.contains(cards.get(i).getRank())) {
	// runs.add(cards.get(i).getRank());
	// runsIndex.add(i);
	// }
	// if (!runs.contains(cards.get(i + 1).getRank())) {
	// runs.add(cards.get(i + 1).getRank());
	// runsIndex.add(i + 1);
	// }
	// }
	// else {
	// if (!runs.isEmpty()) {
	// runMap.put(runs.get(0), runs);
	// runIndexMap.put(runs.get(0), runsIndex);
	// runs = new ArrayList<Integer>();
	// runsIndex = new ArrayList<Integer>();
	// }
	// if (!gaps.contains(cards.get(i).getRank())) {
	// gaps.add(cards.get(i).getRank());
	// gapsIndex.add(i);
	// }
	// if (!gaps.contains(cards.get(i + 1).getRank())) {
	// gaps.add(cards.get(i + 1).getRank());
	// gapsIndex.add(i + 1);
	// }
	// }
	// }
	// if (!runs.isEmpty()) {
	// runMap.put(runs.get(0), runs);
	// runIndexMap.put(runs.get(0), runsIndex);
	// }
	// if (!gaps.isEmpty()) {
	// // gapMap.put(gaps.get(0), gaps);
	// gapIndexMap.put(gaps.get(0), gapsIndex);
	// }
	// // System.out.println(cards);
	// // System.out.println("runs " + runs);
	// // System.out.println("gaps " + gaps);
	// // System.out.println("r" + runMap);
	// // System.out.println("g" + gapMap);
	// int last = -1;
	// int size = 0;
	// for (ArrayList<Integer> run : runMap.values()) {
	// last = run.get(run.size() - 1);
	// if (runMap.containsKey(last)) {
	// ArrayList<Integer> next = runMap.get(last);
	// int nextLast = next.get(next.size() - 1);
	// if (runMap.containsKey(nextLast)) {
	// size = run.size() + next.size() - 1 + runMap.get(nextLast).size() - 1;
	// }
	// else {
	// size = run.size() + next.size() - 1;
	// }
	// }
	// if (run.size() >= 5 || size >= 5) {
	// return true;
	// }
	// }
	// if (cards.get(0).getRank() == 2 && cards.get(cards.size() - 1).getRank() ==
	// 14) {
	// // start with an 2 and end with ace
	// // find the start of a possible straight
	// runs = new ArrayList<Integer>();
	// gaps = new ArrayList<Integer>();
	// for (int i = 0; i < cards.size() - 1; i++) {
	// if (cards.get(i).getRank() + 1 == cards.get(i + 1).getRank()) {
	// if (!runs.contains(cards.get(i).getRank())) {
	// runs.add(cards.get(i).getRank());
	// }
	// if (!runs.contains(cards.get(i + 1).getRank())) {
	// runs.add(cards.get(i + 1).getRank());
	// }
	// }
	// else {
	// if (!gaps.contains(cards.get(i).getRank())) {
	// gaps.add(cards.get(i).getRank());
	// }
	// if (!gaps.contains(cards.get(i + 1).getRank())) {
	// gaps.add(cards.get(i + 1).getRank());
	// }
	// }
	// }
	// if (runs.contains(2) && runs.contains(3) && runs.contains(4) &&
	// runs.contains(5)) {
	// return true;
	// }
	// }
	// if (hasJoker()) {
	// // find the start of a possible straight
	// runs = new ArrayList<Integer>();
	// gaps = new ArrayList<Integer>();
	// for (int i = 0; i < cards.size() - 1; i++) {
	// if (cards.get(i).getRank() + 1 == cards.get(i + 1).getRank()) {
	// if (!runs.contains(cards.get(i).getRank())) {
	// runs.add(cards.get(i).getRank());
	// }
	// if (!runs.contains(cards.get(i + 1).getRank())) {
	// runs.add(cards.get(i + 1).getRank());
	// }
	// }
	// else {
	// if (!gaps.contains(cards.get(i).getRank())) {
	// gaps.add(cards.get(i).getRank());
	// }
	// if (!gaps.contains(cards.get(i + 1).getRank())) {
	// gaps.add(cards.get(i + 1).getRank());
	// }
	// }
	// }
	// for (int i : runs) {
	// gaps.remove(Integer.valueOf(i));
	// }
	// // System.out.println("runs:" + runs);
	// // System.out.println("gaps:" + gaps);
	// if (runs.size() == 4) {
	// return true;
	// }
	// for (int i = 0; i < runs.size(); i++) {
	// for (int j = 0; j < gaps.size(); j++) {
	// if (Math.abs(runs.get(i).intValue() - gaps.get(j).intValue()) == 2) {
	// return true;
	// }
	// }
	// }
	// }
	// return false;
	// }

	/**
	 * @return true if the hand has a straight.
	 */
	public boolean hasStraight() {
		Set<List<ICard>> s = getStraight();
		return s.size() > 0;
	}

	/**
	 * @return true if the hand has a straight flush.
	 */
	public boolean hasStraightFlush() {
		boolean straightp = false;
		List<ICard> straightFlush = getStraightFlush();
		Collections.sort(straightFlush);
		if (straightFlush.size() >= 5) {
			straightp = true;
		}
		return straightp;
	}

	/**
	 * @return true if hand is seven card straight flush with a joker.
	 */
	public boolean hasSevenCardStraightFlushWJoker() {
		boolean straightp = false;
		List<ICard> straightFlush = getStraightFlush();
		Collections.sort(straightFlush);
		if (straightFlush.size() == 7 && hasJoker()) {
			straightp = true;
		}
		return straightp;
	}

	/**
	 * @return true if hand is seven card straight flush w/o a joker.
	 */
	public boolean hasSevenCardStraightFlushNoJoker() {
		boolean straightp = false;
		List<ICard> straightFlush = getStraightFlush();
		Collections.sort(straightFlush);
		if (straightFlush.size() == 7 && !hasJoker()) {
			straightp = true;
		}
		return straightp;
	}

	/**
	 * 
	 * @return true is has three of a kind.
	 * @see org.cam.card.Hand#hasThreeOfAKind()
	 */
	@Override
	public boolean hasThreeOfAKind() {
		boolean ret = super.hasThreeOfAKind();
		if (hasJoker() && !ret) {
			int aces = numAces();
			if (aces == 2) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * @return true if the hand has three pair.
	 */
	public boolean hasThreePair() {
		boolean ret = super.hasThreePair();
		if (hasJoker() && !ret) {
			int aces = numAces();
			ret = super.hasTwoPair() && aces == 1;
		}
		return ret;
	}

	/**
	 * 
	 * @return true if the hand has two pair.
	 * @see org.cam.card.Hand#hasTwoPair()
	 */
	@Override
	public boolean hasTwoPair() {
		boolean ret = super.hasTwoPair();
		if (hasJoker() && !ret) {
			int aces = numAces();
			ret = super.hasPair() && aces == 1;
		}
		return ret;
	}

	/**
	 * @return true if the hand is a ace high paigao.
	 */
	public boolean isAceHighPaiGao() {
		Collections.sort(cards);
		return cards.get(cards.size() - 1).getRank() == 14 & isPaiGow();
	}

	/**
	 * 
	 * @return true if the hand has a flush.
	 * @see org.cam.card.Hand#isFlush()
	 */
	@Override
	public boolean isFlush() {
		return hasFlush();
	}

	/**
	 * @return true if the hand is jack high paigao.
	 */
	public boolean isJackHighPaiGao() {
		Collections.sort(cards);
		return !isAceHighPaiGao() && cards.get(cards.size() - 1).getRank() == 11
				& isPaiGow();
	}

	/**
	 * @return true if the hand is a king high paigao.
	 */
	public boolean isKingHighPaiGao() {
		Collections.sort(cards);
		return !isAceHighPaiGao() && cards.get(cards.size() - 1).getRank() == 13
				& isPaiGow();
	}

	/**
	 * @return true if hand is nine high paigao.
	 */
	public boolean isNineHighPaiGao() {
		Collections.sort(cards);
		return !isAceHighPaiGao() && cards.get(cards.size() - 1).getRank() == 9
				& isPaiGow();
	}

	/**
	 * @return true if hand is paigao.
	 */
	public boolean isPaiGow() {
		boolean ret = false;
		ret = ret || hasPair();
		ret = ret || hasThreeOfAKind();
		ret = ret || hasFullHouse();
		ret = ret || hasStraight();
		ret = ret || hasFlush();
		return !ret;
	}

	/**
	 * @return true if hand is queen high paigao.
	 */
	public boolean isQueenHighPaiGao() {
		Collections.sort(cards);
		return !isAceHighPaiGao() && cards.get(cards.size() - 1).getRank() == 12
				& isPaiGow();
	}

	/**
	 * 
	 * @return true if hand has a royal flush.
	 * @see org.cam.card.Hand#isRoyalFlush()
	 */
	@Override
	public boolean isRoyalFlush() {
		return hasRoyalFlush();
	}

	public boolean isSimpleSevenCardStraight() {
		return Card.isStraight(cards);
	}

	public boolean isSimpleSevenCardStraightFlush() {
		return Card.isStraightFlush(cards);
	}

	/**
	 * 
	 * @return true if hand has a straight.
	 * @see org.cam.card.Hand#isStraight()
	 */
	@Override
	public boolean isStraight() {
		return hasStraight();
	}

	/**
	 * 
	 * @return true if hand has a straight flush.
	 * @see org.cam.card.Hand#isStraightFlush()
	 */
	@Override
	public boolean isStraightFlush() {
		return hasStraightFlush();
	}

	/**
	 * @return true if hand is a ten high paigao.
	 */
	public boolean isTenHighPaiGao() {
		Collections.sort(cards);
		return !isAceHighPaiGao() && cards.get(cards.size() - 1).getRank() == 10
				& isPaiGow();
	}

	/**
	 * @return The raw String representation.
	 */
	public String rawCards() {
		StringBuilder sb = new StringBuilder();
		sb.append(cards);
		return sb.toString();
	}

	public void setHighHand(List<ICard> high) {
		assert (high.size() == 5);
		assert (cards.containsAll(high));
		highHand.clear();
		highHand.addAll(high);
	}

	public void setLowHand(List<ICard> low) {
		assert (low.size() == 2);
		assert (cards.containsAll(low));
		lowHand.clear();
		lowHand.addAll(low);
	}

	/**
	 * 
	 * @return The String representation of the hand.
	 * @see org.cam.card.Hand#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(lowHand);
		sb.append(highHand);
		return sb.toString();
	}

	/**
	 * @return A list of the flush cards. The list will be empty if their isn't
	 * a five card flush.
	 */
	private List<ICard> getFlush() {
		return flushes.getFlush();
	}

	private Set<List<ICard>> getStraight() {
		return straights.getStraights();
	}


	public List<ICard> getStraightFlush() {
		Set<List<ICard>> straightCards = getStraight();
		List<ICard> flushCards = getFlush();
//		System.out.println("Straight Cards " + straightCards);
//		System.out.println("Flush Cards " + flushCards);
		List<ICard> both = new ArrayList<ICard>();
		List<ICard> max = new ArrayList<ICard>();
		for (List<ICard> sCards : straightCards) {
			for (ICard card : sCards) {
				if (flushCards.contains(card) && !both.contains(card)) {
					both.add(card);
				}
			}
		}
		Collections.sort(both);
		return both;
	}


	private boolean handHasNextRank(int rank) {
		for (ICard card : cards) {
			if (card.getRank() == rank + 1) {
				return true;
			}
		}
		return false;
	}

	private boolean hasJokerFillNextRank(int rank) {
		for (ICard card : cards) {
			if (card.getRank() == rank + 2 || card.getRank() == rank - 2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Set the hand the house way.
	 */
	private void houseWay() {
		try {
			if (isPaiGow()) {
				playPaiGao();
			} else {
				if (hasFiveAces()) {
					playFiveAces();
				} else if (isRoyalFlush()) {
					playRoyalFlush();
				} else if (isStraightFlush()) {
					playStraightFlush();
				} else if (hasFourOfAKind()) {
					playFourOfAKind();
				} else if (hasFullHouse()) {
					playFullHouse();
				}
				/*
				 * Straights and flushes: Always play straight or flush in the high hand
				 * except play as a two pair if one of the following:
				 * 
				 * Aces and any other pair. Both pairs 10's or over. Both pairs 10's or
				 * under with an ace singleton.
				 * 
				 * With a six or seven card straight or flush put the highest hand
				 * possible in front while maintaining the straight or flush in back.
				 * 
				 * If hand contains both a straight and a flush play put the hand in
				 * back with the highest hand in front, as long as the front hand is a
				 * king or better. Otherwise play flush in back.
				 */
				else if (hasFlush()) {
					if (hasTwoPair()) {
						playTwoPair();
					} else {
						playFlush();
					}
				} else if (hasStraight()) {
					playStraight();
				} else if (hasThreeOfAKind()) {
					playThreeOfAKind();
				} else if (hasThreePair()) {
					playThreePair();
				} else if (hasTwoPair()) {
					playTwoPair();
				} else if (hasPair()) {
					// Always fivecard the pair and twocard the two remaining highest
					// cards.
					Collections.reverse(cards);
					// System.out.println(cards);
					int index = -1;
					for (int i = 0; i < cards.size() - 1; i++) {
						if (cards.get(i).getRank() == cards.get(i + 1).getRank()) {
							index = i;
						}
					}
					if (index > -1) {
						highHand.add(cards.get(index));
						highHand.add(cards.get(index + 1));
						if (index > 1) {
							lowHand.add(cards.get(0));
							lowHand.add(cards.get(1));
							for (int i = 2; i < index; i++) {
								highHand.add(cards.get(i));
							}
							for (int i = index + 2; i < cards.size(); i++) {
								highHand.add(cards.get(i));
							}
						} else if (index == 1) {
							lowHand.add(cards.get(0));
							lowHand.add(cards.get(index + 2));
							for (int i = index + 3; i < cards.size(); i++) {
								highHand.add(cards.get(i));
							}
						} else if (index == 0) {
							lowHand.add(cards.get(index + 2));
							lowHand.add(cards.get(index + 3));
							for (int i = index + 4; i < cards.size(); i++) {
								highHand.add(cards.get(i));
							}
						}
					} else {
						// had pair of aces made w/ joker and one ace
						lowHand.add(cards.get(1));
						lowHand.add(cards.get(2));
						highHand.add(cards.get(0)); // the ace
						highHand.add(cards.get(6)); // the joker
						highHand.add(cards.get(3));
						highHand.add(cards.get(4));
						highHand.add(cards.get(5));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Couldn't play " + rawCards());
			e.printStackTrace();
		}
	}

	/**
	 * @return the number of aces in the hand.
	 */
	private int numAces() {
		int aces = 0;
		for (ICard c : cards) {
			if (c.getRank() == ICard.ACE) {
				aces++;
			}
		}
		return aces;
	}

	/**
	 * Set the hand playing five aces.
	 * 
	 * Five aces: Split unless pair of kings can be played in front.
	 */
	private void playFiveAces() {
		Collections.sort(cards);
		// System.out.println(cards);
		// Joker is at 0
		// check if have kings
		if (cards.get(1).getRank() == Card.KING
				&& cards.get(2).getRank() == Card.KING) {
			lowHand.add(cards.get(1));
			lowHand.add(cards.get(2));
			highHand.add(cards.get(0)); // joker
			for (int i = 3; i < cards.size(); i++) {
				highHand.add(cards.get(i));
			}
		} else {
			lowHand.add(cards.get(0));
			lowHand.add(cards.get(cards.size() - 1));
			for (int i = 1; i < cards.size() - 1; i++) {
				highHand.add(cards.get(i));
			}

		}

	}

	/**
	 * Set the hand playing a flush.
	 */
	private void playFlush() {
		HashMap<Integer, ArrayList<ICard>> suits = new HashMap<Integer, ArrayList<ICard>>();
		for (int i = 0; i < cards.size(); i++) {
			ICard c = cards.get(i);
			ArrayList<ICard> suitCards = suits.get(c.getSuit());
			if (suitCards == null) {
				suitCards = new ArrayList<ICard>();
				suits.put(c.getSuit(), suitCards);
			}
			suitCards.add(c);
		}
		for (Entry<Integer, ArrayList<ICard>> e : suits.entrySet()) {
			List<ICard> suitCards = e.getValue();
			if (suitCards.size() == 4 && hasJoker()) {
				// highHand.add(cards.get(0)); // joker
				highHand.addAll(suitCards);
			} else if (suitCards.size() < 5 && lowHand.size() < 2) {
				lowHand.addAll(suitCards);
			} else if (suitCards.size() > 5) {
				for (int i = 0; i < 5; i++) {
					highHand.add(suitCards.get(i));
				}
				for (int i = 5; i < suitCards.size(); i++) {
					lowHand.add(suitCards.get(i));
				}
			} else {
				highHand.addAll(suitCards);
			}
		}
	}

	/**
	 * Set the hand to play four of a kind.
	 */
	private void playFourOfAKind() {
		// Four of a kind: Play according to the rank of the four of a kind:
		// * 2 through 6: Always keep together.
		// * 7 through 10: Split unless an ace and a face card or better can be
		// played in front.
		// * Jack through king: Split unless hand also contains a pair of 10's or
		// higher.
		// * Aces: Split unless a pair of 7's or higher can be played in front.
		Collections.reverse(cards);
		boolean jokerp = hasJoker();
		int startIndex = -1;
		int rank = -1;

		for (int i = 0; i < cards.size() - 3; i++) {
			if (cards.get(i).getRank() == cards.get(i + 1).getRank()
					&& cards.get(i + 1).getRank() == cards.get(i + 2).getRank()
					&& cards.get(i + 2).getRank() == cards.get(i + 3).getRank()) {
				startIndex = i;
				rank = cards.get(i).getRank();
				break;
			}
		}
		if (startIndex == -1 && jokerp) {
			// aces w/ joker?
			for (int i = 0; i < cards.size() - 3; i++) {
				if (cards.get(i).getRank() == cards.get(i + 1).getRank()
						&& cards.get(i + 1).getRank() == cards.get(i + 2).getRank()) {
					startIndex = i;
					rank = cards.get(i).getRank();
					// should check the rank to ensure it is an ACE
					if (rank != Card.ACE) {
						System.err.println("Wrong rank can't play 4 of a kind.");
					}
					break;
				}
			}
		}
		// check for pair
		boolean pair = false;
		int pairRank = -1;
		int pairIndex = -1;
		if (jokerp) {
			// check 3,4,5 joker is at end
			if (cards.get(3).getRank() == cards.get(4).getRank()) {
				pair = true;
				pairRank = cards.get(3).getRank();
				pairIndex = 3;
			} else if (cards.get(4).getRank() == cards.get(5).getRank()) {
				pair = true;
				pairRank = cards.get(4).getRank();
				pairIndex = 4;
			}
		} else {
			switch (startIndex) {
			case 0: // check last 3
				if (cards.get(4).getRank() == cards.get(5).getRank()) {
					pair = true;
					pairRank = cards.get(4).getRank();
					pairIndex = 4;
				} else if (cards.get(5).getRank() == cards.get(6).getRank()) {
					pair = true;
					pairRank = cards.get(5).getRank();
					pairIndex = 5;
				}
				break;
			case 1: // check last 2
				if (cards.get(5).getRank() == cards.get(6).getRank()) {
					pair = true;
					pairRank = cards.get(5).getRank();
					pairIndex = 5;
				}
				break;
			case 3: // check first 3
				if (cards.get(1).getRank() == cards.get(2).getRank()) {
					pair = true;
					pairRank = cards.get(1).getRank();
					pairIndex = 1;
				}
				break;
			case 2: // check first 2
				if (cards.get(0).getRank() == cards.get(1).getRank()) {
					pair = true;
					pairRank = cards.get(0).getRank();
					pairIndex = 0;
				}
				break;
			default:
				// shouldn't get here
			}
		}
		if (pair) {
			// System.out.println("Have 4oK of " + rank + " and  a Pair of " +
			// pairRank);
		}
		int lowCount = 0;
		int tempIndex;
		if (rank < ICard.SEVEN) {
			// * 2 through 6: Always keep together.
			highHand.add(cards.get(startIndex));
			highHand.add(cards.get(startIndex + 1));
			highHand.add(cards.get(startIndex + 1));
			highHand.add(cards.get(startIndex + 1));
			if (pair) {
				lowHand.add(cards.get(pairIndex));
				lowHand.add(cards.get(pairIndex + 1));
				// find the last card to add to highHand
				tempIndex = 0;
				if (startIndex < pairIndex) {
					// 0, 4, 6
					if (tempIndex < startIndex) {
						highHand.add(cards.get(tempIndex));
					} else {
						tempIndex = 4;
						if (tempIndex < pairIndex) {
							highHand.add(cards.get(tempIndex));
						} else {
							highHand.add(cards.get(6));
						}
					}
				} else {
					// 0, 2, 6
					if (tempIndex < pairIndex) {
						highHand.add(cards.get(tempIndex));
					} else {
						tempIndex = 2;
						if (tempIndex < startIndex) {
							highHand.add(cards.get(tempIndex));
						} else {
							highHand.add(cards.get(6));
						}
					}
				}
			} else {
				// need to add two highest cards
				for (int i = 0; i < startIndex; i++) {
					if (lowCount < 2) {
						lowHand.add(cards.get(i));
						lowCount++;
						tempIndex = i;
					} else {
						highHand.add(cards.get(i));
					}
				}
				for (int i = startIndex + 4; i < cards.size(); i++) {
					if (lowCount < 2) {
						lowHand.add(cards.get(i));
						lowCount++;
						tempIndex = i;
					} else {
						highHand.add(cards.get(i));
					}
				}
			}
		} else if (rank < ICard.JACK) {
			// * 7 through 10: Split unless an ace and a face card or better can be
			// played in front.
			if (pair) {
				highHand.add(cards.get(startIndex));
				highHand.add(cards.get(startIndex + 1));
				highHand.add(cards.get(startIndex + 2));
				highHand.add(cards.get(startIndex + 3));
				lowHand.add(cards.get(pairIndex));
				lowHand.add(cards.get(pairIndex + 1));
				// find the last card to add to highHand
				tempIndex = 0;
				if (startIndex < pairIndex) {
					// 0, 4, 6
					if (tempIndex < startIndex) {
						highHand.add(cards.get(tempIndex));
					} else {
						tempIndex = 4;
						if (tempIndex < pairIndex) {
							highHand.add(cards.get(tempIndex));
						} else {
							highHand.add(cards.get(6));
						}
					}
				} else {
					// 0, 2, 6
					if (tempIndex < pairIndex) {
						highHand.add(cards.get(tempIndex));
					} else {
						tempIndex = 2;
						if (tempIndex < startIndex) {
							highHand.add(cards.get(tempIndex));
						} else {
							highHand.add(cards.get(6));
						}
					}
				}
			} else { // no pair need ace and face card
				// check cards[0] if not ace split
				if (cards.get(0).getRank() == ICard.ACE
						&& cards.get(1).getRank() > ICard.TEN) {
					lowHand.add(cards.get(0));
					lowHand.add(cards.get(1));
					highHand.add(cards.get(startIndex));
					highHand.add(cards.get(startIndex + 1));
					highHand.add(cards.get(startIndex + 2));
					highHand.add(cards.get(startIndex + 3));
					if (startIndex == 2) {
						highHand.add(cards.get(6));
					} else {
						highHand.add(cards.get(2));
					}
				} else {
					// split
					lowHand.add(cards.get(startIndex));
					lowHand.add(cards.get(startIndex + 1));
					highHand.add(cards.get(startIndex + 2));
					highHand.add(cards.get(startIndex + 3));
					for (int i = 0; i < startIndex; i++) {
						highHand.add(cards.get(i));
					}
					for (int i = startIndex + 4; i < cards.size(); i++) {
						highHand.add(cards.get(i));
					}
				}
			}

		} else if (rank < ICard.ACE) {
			// * Jack through king: Split unless hand also contains a pair of 10's
			// or
			// higher.
			if (pair && pairRank > ICard.NINE) {
				lowHand.add(cards.get(pairIndex));
				lowHand.add(cards.get(pairIndex));
				highHand.add(cards.get(startIndex));
				highHand.add(cards.get(startIndex + 1));
				highHand.add(cards.get(startIndex + 2));
				highHand.add(cards.get(startIndex + 3));
				lowHand.add(cards.get(pairIndex));
				lowHand.add(cards.get(pairIndex + 1));
				// find the last card to add to highHand
				tempIndex = 0;
				if (startIndex < pairIndex) {
					// 0, 4, 6
					if (tempIndex < startIndex) {
						highHand.add(cards.get(tempIndex));
					} else {
						tempIndex = 4;
						if (tempIndex < pairIndex) {
							highHand.add(cards.get(tempIndex));
						} else {
							highHand.add(cards.get(6));
						}
					}
				} else {
					// 0, 2, 6
					if (tempIndex < pairIndex) {
						highHand.add(cards.get(tempIndex));
					} else {
						tempIndex = 2;
						if (tempIndex < startIndex) {
							highHand.add(cards.get(tempIndex));
						} else {
							highHand.add(cards.get(6));
						}
					}
				}
			} else {
				// split
				lowHand.add(cards.get(startIndex));
				lowHand.add(cards.get(startIndex + 1));
				highHand.add(cards.get(startIndex + 2));
				highHand.add(cards.get(startIndex + 3));
				for (int i = 0; i < startIndex; i++) {
					highHand.add(cards.get(i));
				}
				for (int i = startIndex + 4; i < cards.size(); i++) {
					highHand.add(cards.get(i));
				}
			}

		} else {
			// * Aces: Split unless a pair of 7's or higher can be played in front.
			if (pair && pairRank > ICard.SIX) {
				lowHand.add(cards.get(pairIndex));
				lowHand.add(cards.get(pairIndex + 1));
				highHand.add(cards.get(startIndex));
				highHand.add(cards.get(startIndex + 1));
				highHand.add(cards.get(startIndex + 2));
				if (jokerp) {
					highHand.add(cards.get(cards.size() - 1));
				} else {
					highHand.add(cards.get(startIndex + 3));
				}
				// find the last card to add to highHand
				tempIndex = 0;
				if (startIndex < pairIndex) {
					// 0, 4, 6
					if (tempIndex < startIndex) {
						highHand.add(cards.get(tempIndex));
					} else {
						tempIndex = 4;
						if (tempIndex < pairIndex) {
							highHand.add(cards.get(tempIndex));
						} else {
							highHand.add(cards.get(6));
						}
					}
				} else {
					// 0, 2, 6
					if (tempIndex < pairIndex) {
						highHand.add(cards.get(tempIndex));
					} else {
						tempIndex = 2;
						if (tempIndex < startIndex) {
							highHand.add(cards.get(tempIndex));
						} else {
							highHand.add(cards.get(6));
						}
					}
				}
			} else { // no pair
				// split
				if (jokerp) {
					lowHand.add(cards.get(startIndex)); // Ace
					lowHand.add(cards.get(startIndex + 1)); // Ace
					highHand.add(cards.get(startIndex + 2)); // Ace
					highHand.add(cards.get(6)); // / joker
					for (int i = startIndex + 3; i < cards.size() - 1; i++) {
						highHand.add(cards.get(i));
					}
				} else {
					lowHand.add(cards.get(startIndex));
					lowHand.add(cards.get(startIndex + 1));
					highHand.add(cards.get(startIndex + 2));
					highHand.add(cards.get(startIndex + 3));
					for (int i = 0; i < startIndex; i++) {
						highHand.add(cards.get(i));
					}
					for (int i = startIndex + 4; i < cards.size(); i++) {
						highHand.add(cards.get(i));
					}
				}
			}
		}
	}

	/**
	 * Sets the hand with a full house. Full house: Split except with pair of 2's
	 * and an ace/king can be played in front. Full house with three of a kind and
	 * two pairs: Play the highest pair in front.
	 */
	private void playFullHouse() {
		// Always fivecard the three of a kind and twocard the pair.
		Collections.reverse(cards);
		int tripIndex = -1;
		int pairIndex = -1;
		for (int i = 0; i < cards.size() - 2; i++) {
			if (cards.get(i).getRank() == cards.get(i + 1).getRank()
					&& cards.get(i + 1).getRank() == cards.get(i + 2).getRank()) {
				tripIndex = i;
				break;
			}
		}
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getRank() == cards.get(i + 1).getRank()
					&& i != tripIndex && i != tripIndex + 1) {
				pairIndex = i;
				break;
			}
		}
		lowHand.add(cards.get(pairIndex));
		lowHand.add(cards.get(pairIndex + 1));
		highHand.add(cards.get(tripIndex));
		highHand.add(cards.get(tripIndex + 1));
		highHand.add(cards.get(tripIndex + 2));
		if (pairIndex < tripIndex) {
			for (int i = 0; i < pairIndex; i++) {
				highHand.add(cards.get(i));
			}
			for (int i = pairIndex + 2; i < tripIndex; i++) {
				highHand.add(cards.get(i));
			}
			for (int i = tripIndex + 3; i < cards.size(); i++) {
				highHand.add(cards.get(i));
			}
		} else {
			for (int i = 0; i < tripIndex; i++) {
				highHand.add(cards.get(i));
			}
			for (int i = tripIndex + 3; i < pairIndex; i++) {
				highHand.add(cards.get(i));
			}
			for (int i = pairIndex + 2; i < cards.size(); i++) {
				highHand.add(cards.get(i));
			}
		}
	}

	/**
	 * Sets the hand with a paigao.
	 */
	private void playPaiGao() {
		Collections.reverse(cards);
		// Always fivecard the highest card and twocard the second- and
		// third-highest cards
		lowHand.add(cards.get(1));
		lowHand.add(cards.get(2));
		highHand.add(cards.get(0));
		highHand.add(cards.get(3));
		highHand.add(cards.get(4));
		highHand.add(cards.get(5));
		highHand.add(cards.get(6));
	}

	/**
	 * Set the hand for a Royal Flush.
	 * 
	 * Royal Flush:
	 * 
	 * Play the royal flush in back except play as a two pair with:
	 * 
	 * Aces and any other pair. Both pairs tens or higher.
	 * 
	 * Break up royal flush if a straight or flush can be played in back and a
	 * king or better in front.
	 */
	private void playRoyalFlush() {
		Collections.sort(cards);
		List<ICard> royalFlush = getStraightFlush();
		// System.out.println("Royal flush " + cards);
		// System.out.println(royalFlush);
		if (hasTwoPair()) {
			playTwoPair(); // if two pair must be higher than 10.
		}
		// play royal flush
		// check for joker
		else if (cards.get(0).getRank() == Card.JOKER) {
			lowHand.add(cards.get(1));
			lowHand.add(cards.get(2));
			highHand.add(cards.get(0));
			for (int i = 3; i < cards.size(); i++) {
				highHand.add(cards.get(i));
			}
		} else {
			lowHand.add(cards.get(0));
			lowHand.add(cards.get(1));
			for (int i = 2; i < cards.size(); i++) {
				highHand.add(cards.get(i));
			}
		}
	}

	/**
	 * Set the hand to play a straight. Straights and flushes: Always play
	 * straight or flush in the high hand except play as a two pair if one of the
	 * following:
	 * 
	 * Aces and any other pair. Both pairs 10's or over. Both pairs 10's or under
	 * with an ace singleton.
	 * 
	 * With a six or seven card straight or flush put the highest hand possible in
	 * front while maintaining the straight or flush in back.
	 * 
	 * If hand contains both a straight and a flush play put the hand in back with
	 * the highest hand in front, as long as the front hand is a king or better.
	 * Otherwise play flush in back.
	 */
	private void playStraight() {
		Collections.sort(cards);
		// List<ICard> straight = getStraight();
		// Collections.reverse(straight);
		if (hasThreePair()) {
			playThreePair();
		} else if (hasTwoPair()) {
			ICard p11 = null;
			ICard p12 = null;
			ICard p21 = null;
			ICard p22 = null;
			ICard ace = null;
			if (cards.get(cards.size() - 1).getRank() == Card.ACE) {
				ace = cards.get(cards.size() - 1);
			}
			boolean haveFirst = false;
			// System.out.println("cards " + cards);
			for (int i = 0; i < cards.size() - 1; i++) {
				if (cards.get(i).getRank() == cards.get(i + 1).getRank() && !haveFirst) {
					p11 = cards.get(i);
					p12 = cards.get(i + 1);
					haveFirst = true;
				} else if (cards.get(i).getRank() == cards.get(i + 1).getRank()
						&& haveFirst) {
					p21 = cards.get(i);
					p22 = cards.get(i + 1);
				} else if (cards.get(0).getRank() == Card.JOKER
						&& cards.get(i + 1).getRank() == Card.ACE && haveFirst) {
					p21 = cards.get(0);
					p22 = cards.get(i + 1);
				}
			}
			// System.out.println(p11 + " " + p12 + " " + p21 + " " + p22);
			// Aces and any other pair.
			if (p22.getRank() == Card.ACE) {
				playTwoPair();
				return;
			} else if (p11.getRank() >= Card.TEN) {
				playTwoPair();
				return;
			} else if (ace != null && !p22.equals(ace)) {
				// have two pair with an ace
				playTwoPair();
				return;
			}
		}
		// play the Straight
		List<ICard> temp = new ArrayList<ICard>();
		for (ICard card : cards) {
			temp.add(card);
		}
		// Collections.sort(straight);
		for (int i = 0; i < 5; i++) {
			// ICard c = straight.get(i);
			// highHand.add(c);
			// temp.remove(c);
		}
		lowHand.add(temp.get(0));
		lowHand.add(temp.get(1));
	}

	/**
	 * Set the hand for a Straight Flush. Straight Flush:
	 * 
	 * Play the straight flush in back except play as a two pair with:
	 * 
	 * Aces and any other pair. Both pairs tens or higher. Both pairs tens or less
	 * and an ace singleton.
	 * 
	 * Play a straight or flush instead if an ace and a face card or a pair can be
	 * played in front.
	 */
	private void playStraightFlush() {
		Collections.sort(cards);
		List<ICard> straightFlush = getStraightFlush();
		// System.out.println("straight flush " + straightFlush);
		// System.out.println(royalFlush);

		if (hasTwoPair()) {
			playTwoPair(); // if two pair must be higher than 10.
		} else if (straightFlush.size() == 7) {
			lowHand.add(cards.get(5));
			lowHand.add(cards.get(6));
			for (int i = 0; i < 5; i++) {
				highHand.add(cards.get(i));
			}
		}
		// play royal flush
		// check for joker
		else if (cards.get(0).getRank() == Card.JOKER) {
			lowHand.add(cards.get(1));
			lowHand.add(cards.get(2));
			highHand.add(cards.get(0));
			for (int i = 3; i < cards.size(); i++) {
				highHand.add(cards.get(i));
			}
		} else {
			lowHand.add(cards.get(0));
			lowHand.add(cards.get(1));
			for (int i = 2; i < cards.size(); i++) {
				highHand.add(cards.get(i));
			}
		}
	}

	/**
	 * Set the hand for a three of a kind.
	 */
	private void playThreeOfAKind() {
		// Fivecard the three of a kind unless you have three aces, in which
		// case fivecard a pair of aces and twocard an ace.
		Collections.reverse(cards);
		// System.out.println(cards);
		int index = -1;
		for (int i = 0; i < cards.size() - 2; i++) {
			if (cards.get(i).getRank() == cards.get(i + 1).getRank()
					&& cards.get(i + 1).getRank() == cards.get(i + 2).getRank()) {
				index = i;
				break;
			}
		}
		if (index > -1) {
			highHand.add(cards.get(index));
			highHand.add(cards.get(index + 1));
			highHand.add(cards.get(index + 2));
			if (index > 1) {
				lowHand.add(cards.get(0));
				lowHand.add(cards.get(1));
				for (int i = 2; i < index; i++) {
					highHand.add(cards.get(i));
				}
				for (int i = index + 3; i < cards.size(); i++) {
					highHand.add(cards.get(i));
				}
			} else if (index == 1) {
				lowHand.add(cards.get(0));
				lowHand.add(cards.get(index + 3));
				for (int i = index + 4; i < cards.size(); i++) {
					highHand.add(cards.get(i));
				}
			} else if (index == 0) {
				lowHand.add(cards.get(index + 3));
				lowHand.add(cards.get(index + 4));
				for (int i = index + 5; i < cards.size(); i++) {
					highHand.add(cards.get(i));
				}
			}
		} else {
			// made three of a kind with joker
			lowHand.add(cards.get(2));
			lowHand.add(cards.get(3));
			highHand.add(cards.get(0));
			highHand.add(cards.get(1));
			highHand.add(cards.get(6));
			highHand.add(cards.get(4));
			highHand.add(cards.get(5));
		}
	}

	/**
   * 
   */
	private void playThreePair() {
		Collections.sort(cards);
		int firstRank = -1;
		int firstPairStart = -1;
		int secondRank = -1;
		int secondPairStart = -1;
		int thirdPairStart = -1;
		int count = 0;
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getRank() == cards.get(i + 1).getRank()) {
				firstRank = cards.get(i).getRank();
				firstPairStart = i;
				count = i;
				break;
			}
		}
		for (int j = count + 1; j < cards.size() - 1; j++) {
			if (cards.get(j).getRank() == cards.get(j + 1).getRank()
					&& firstRank != cards.get(j).getRank()) {
				secondRank = cards.get(j).getRank();
				secondPairStart = j;
				count = j;
				break;
			}
		}
		for (int k = count + 1; k < cards.size() - 1; k++) {
			if (cards.get(k).getRank() == cards.get(k + 1).getRank()
					&& firstRank != cards.get(k).getRank()
					&& secondRank != cards.get(k).getRank()) {
				thirdPairStart = k;
				break;
			}
		}
		if (thirdPairStart == -1 && firstPairStart > 0
				&& cards.get(0).getRank() == ICard.ACE
				&& cards.get(6).getRank() == ICard.JOKER) {
			// third pair is aces w/ joker
			lowHand.add(cards.get(0));
			lowHand.add(cards.get(6));
			highHand.add(cards.get(firstPairStart));
			highHand.add(cards.get(firstPairStart + 1));
			highHand.add(cards.get(secondPairStart));
			highHand.add(cards.get(secondPairStart + 1));
			if (firstPairStart > 1) {
				highHand.add(cards.get(1));
			} else if (secondPairStart > firstPairStart + 2) {
				highHand.add(cards.get(firstPairStart + 3));
			} else {
				for (int i = 0; i < firstPairStart; i++) {
					highHand.add(cards.get(i));
				}
				highHand.add(cards.get(firstPairStart));
				highHand.add(cards.get(firstPairStart + 1));
				for (int i = firstPairStart + 2; i < secondPairStart; i++) {
					highHand.add(cards.get(i));
				}
				highHand.add(cards.get(secondPairStart));
				highHand.add(cards.get(secondPairStart + 1));
				for (int i = secondPairStart + 2; i < thirdPairStart; i++) {
					highHand.add(cards.get(i));
				}
				lowHand.add(cards.get(thirdPairStart));
				lowHand.add(cards.get(thirdPairStart + 1));
				for (int i = thirdPairStart + 2; i < cards.size(); i++) {
					highHand.add(cards.get(i));
				}
			}
		}
	}

	/**
	 * Set the hand with two pair.
	 */
	private void playTwoPair() {
		// Fivecard one pair and twocard one pair unless neither pair is jacks
		// or higher and you have an ace or joker, in which case fivecard two
		// pair.

		ICard p11 = null;
		ICard p12 = null;
		ICard p21 = null;
		ICard p22 = null;
		ICard ace = null;
		Collections.sort(cards);
		ArrayList<ICard> temp = new ArrayList<ICard>();
		for (ICard card : cards) {
			temp.add(card);
		}
		if (cards.get(cards.size() - 1).getRank() == Card.ACE) {
			ace = cards.get(cards.size() - 1);
		}
		boolean haveFirst = false;
		// System.out.println("cards " + cards);
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getRank() == cards.get(i + 1).getRank() && !haveFirst) {
				p11 = cards.get(i);
				p12 = cards.get(i + 1);
				haveFirst = true;
			} else if (cards.get(i).getRank() == cards.get(i + 1).getRank()
					&& haveFirst) {
				p21 = cards.get(i);
				p22 = cards.get(i + 1);
			} else if (cards.get(0).getRank() == Card.JOKER
					&& cards.get(i + 1).getRank() == Card.ACE && haveFirst) {
				p21 = cards.get(0);
				p22 = cards.get(i + 1);
			}
		}
		// System.out.println("two pair " + p11 + "," + p12 + " " + p21 + "," +
		// p22);
		if (p22.getRank() < Card.SIX) { // both pairs less than six
			// keep together
			highHand.add(p21);
			temp.remove(p21);
			highHand.add(p22);
			temp.remove(p22);
			highHand.add(p11);
			temp.remove(p11);
			highHand.add(p12);
			temp.remove(p12);
			Collections.sort(temp);
			highHand.add(temp.get(0));
			lowHand.add(temp.get(2));
			lowHand.add(temp.get(1));
		} else if (p22.getRank() < Card.TEN && ace != null) { // both pairs less
																													// than
																													// 10 with ace
			// keep together
			highHand.add(p21);
			temp.remove(p21);
			highHand.add(p22);
			temp.remove(p22);
			highHand.add(p11);
			temp.remove(p11);
			highHand.add(p12);
			temp.remove(p12);
			lowHand.add(ace);
			temp.remove(ace);
			Collections.sort(temp);
			lowHand.add(temp.get(1));
			highHand.add(temp.get(0));
		} else if (p22.getRank() > Card.TEN && p11.getRank() <= Card.FIVE
				&& ace != null) { // One
													// pair
													// of
													// face
													// cards,
													// one
													// pair
													// of
													// 5's
													// or
													// less,
													// and
													// an
													// ace
													// singleton.
			// keep together
			highHand.add(p21);
			temp.remove(p21);
			highHand.add(p22);
			temp.remove(p22);
			highHand.add(p11);
			temp.remove(p11);
			highHand.add(p12);
			temp.remove(p12);
			lowHand.add(ace);
			temp.remove(ace);
			Collections.sort(temp);
			highHand.add(temp.get(1));
			lowHand.add(temp.get(0));
		} else { // split the pairs
			highHand.add(p21);
			temp.remove(p21);
			highHand.add(p22);
			temp.remove(p22);
			lowHand.add(p11);
			temp.remove(p11);
			lowHand.add(p12);
			temp.remove(p12);
			Collections.sort(temp);
			highHand.add(temp.get(2));
			highHand.add(temp.get(1));
			highHand.add(temp.get(0));
		}
	}
}
