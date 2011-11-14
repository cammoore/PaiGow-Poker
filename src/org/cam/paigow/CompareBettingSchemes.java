package org.cam.paigow;

import java.util.List;

import org.cam.card.HandResult;
import org.cam.card.HandResultConverter;
import org.cam.utilities.statistics.Binner;
import org.cam.utilities.statistics.DataSequence;
import org.cam.utilities.statistics.FixedWidthBinner;

public class CompareBettingSchemes {

	private PaiGowTable table;
	private BettingResultsFactory factory;
	private DataSequence pgnrData;
	private DataSequence pgrfData;
	private DataSequence pgmrData;

	private DataSequence pgnrDataMeta;
	private DataSequence pgrfDataMeta;
	private DataSequence pgmrDataMeta;

	private DataSequence pgnr25Data;
	private DataSequence pgrf25Data;
	private DataSequence pgmr25Data;

	private DataSequence pgnr25DataMeta;
	private DataSequence pgrf25DataMeta;
	private DataSequence pgmr25DataMeta;

	private DataSequence bhData;
	private DataSequence bhDataMeta;
	private DataSequence bh25Data;
	private DataSequence bh25DataMeta;
	
	private int nrWins;
	private int nrDoubles;
	private int rfWins;
	private int rfDoubles;
	private int mrWins;
	private int mrDoubles;

	private int nr25Wins;
	private int nr25Doubles;
	private int rf25Wins;
	private int rf25Doubles;
	private int mr25Wins;
	private int mr25Doubles;


	public CompareBettingSchemes() {
		this.table = new PaiGowTable();
		this.factory = BettingResultsFactory.getInstance();
		this.pgnrData = new DataSequence();
		this.pgrfData = new DataSequence();
		this.pgmrData = new DataSequence();
		this.pgnrDataMeta = new DataSequence();
		this.pgrfDataMeta = new DataSequence();
		this.pgmrDataMeta = new DataSequence();
		this.pgnr25Data = new DataSequence();
		this.pgrf25Data = new DataSequence();
		this.pgmr25Data = new DataSequence();
		this.pgnr25DataMeta = new DataSequence();
		this.pgrf25DataMeta = new DataSequence();
		this.pgmr25DataMeta = new DataSequence();
		this.bhData = new DataSequence();
		this.bhDataMeta = new DataSequence();
		this.bh25Data = new DataSequence();
		this.bh25DataMeta = new DataSequence();
		
		this.nrWins = 0;
		this.nrDoubles = 0;
		this.rfWins = 0;
		this.rfDoubles = 0;
		this.mrWins = 0;
		this.mrDoubles = 0;

		this.nr25Wins = 0;
		this.nr25Doubles = 0;
		this.rf25Wins = 0;
		this.rf25Doubles = 0;
		this.mr25Wins = 0;
		this.mr25Doubles = 0;
}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompareBettingSchemes cbs = new CompareBettingSchemes();
		for (int i = 0; i < 100; i++) {
			cbs.playNewRounds(20);
		}
		System.out.println();
		System.out.println("NR won " + cbs.nrWins + " RF won " + cbs.rfWins
				+ " max won " + cbs.mrWins);
		System.out.println("NR 2x " + cbs.nrDoubles + " RF 2x " + cbs.rfDoubles
				+ " max 2x " + cbs.mrDoubles);
		System.out.println("No raise: " + cbs.pgnrDataMeta.getSize()
				+ " hands mean = " + cbs.pgnrDataMeta.getMean() + " stdev " + cbs.pgnrDataMeta.getStandardDeviation() + " max "
				+ cbs.pgnrDataMeta.getMax() + " min " + cbs.pgnrDataMeta.getMin());
		System.out.println("Raise 5: " + cbs.pgrfDataMeta.getSize()
				+ " hands mean = " + cbs.pgrfDataMeta.getMean() + " stdev " + cbs.pgrfDataMeta.getStandardDeviation() +  " max "
				+ cbs.pgrfDataMeta.getMax() + " min " + cbs.pgrfDataMeta.getMin());
		System.out.println("Max Raise: " + cbs.pgmrDataMeta.getSize()
				+ " hands mean = " + cbs.pgmrDataMeta.getMean() + " stdev " + cbs.pgmrDataMeta.getStandardDeviation() +  " max "
				+ cbs.pgmrDataMeta.getMax() + " min " + cbs.pgmrDataMeta.getMin());
		System.out.println("Bet Half: " + cbs.bhDataMeta.getSize()
				+ " hands mean = " + cbs.bhDataMeta.getMean() + " stdev " + cbs.bhDataMeta.getStandardDeviation() +  " max "
				+ cbs.bhDataMeta.getMax() + " min " + cbs.bhDataMeta.getMin());

		
		System.out.println();
		System.out.println("25 NR won " + cbs.nr25Wins + " RF won " + cbs.rf25Wins
				+ " max won " + cbs.mr25Wins);
		System.out.println("25 NR 2x " + cbs.nr25Doubles + " RF 2x " + cbs.rf25Doubles
				+ " max 2x " + cbs.mr25Doubles);
		System.out.println("No raise: " + cbs.pgnr25DataMeta.getSize()
				+ " hands mean = " + cbs.pgnr25DataMeta.getMean() + " stdev " + cbs.pgnr25DataMeta.getStandardDeviation() +  " max "
				+ cbs.pgnr25DataMeta.getMax() + " min " + cbs.pgnr25DataMeta.getMin());
		System.out.println("Raise 5: " + cbs.pgrf25DataMeta.getSize()
				+ " hands mean = " + cbs.pgrf25DataMeta.getMean() + " stdev " + cbs.pgrf25DataMeta.getStandardDeviation() +  " max "
				+ cbs.pgrf25DataMeta.getMax() + " min " + cbs.pgrf25DataMeta.getMin());
		System.out.println("Max Raise: " + cbs.pgmr25DataMeta.getSize()
				+ " hands mean = " + cbs.pgmr25DataMeta.getMean() + " stdev " + cbs.pgmr25DataMeta.getStandardDeviation() +  " max "
				+ cbs.pgmr25DataMeta.getMax() + " min " + cbs.pgmr25DataMeta.getMin());
		System.out.println("Bet Half: " + cbs.bh25DataMeta.getSize()
				+ " hands mean = " + cbs.bh25DataMeta.getMean() + " stdev " + cbs.bh25DataMeta.getStandardDeviation() +  " max "
				+ cbs.bh25DataMeta.getMax() + " min " + cbs.bh25DataMeta.getMin());

		double stop = cbs.pgrfDataMeta.getMax();
		Binner noRaiseB = new Binner(cbs.pgnrDataMeta, 7);
		Binner noRaise25B = new Binner(cbs.pgnr25DataMeta, 7);
		System.out.println("No Raise:");
		System.out.println(noRaiseB);
		System.out.println(noRaise25B);
		Binner raiseFiveB = new Binner(cbs.pgrfDataMeta, 7);
		System.out.println("Raise Five:");
		System.out.println(raiseFiveB);
		Binner maxRaiseB = new Binner(cbs.pgmrDataMeta, 7);
		System.out.println("Max Raise:");
		System.out.println(maxRaiseB);

		FixedWidthBinner noRaiseFWB = new FixedWidthBinner(cbs.pgnrDataMeta, 0.0,
				stop, 25);
		FixedWidthBinner raiseFiveFWB = new FixedWidthBinner(cbs.pgrfDataMeta, 0.0,
				stop, 25);
		FixedWidthBinner maxRaiseFWB = new FixedWidthBinner(cbs.pgmrDataMeta, 0.0,
				stop, 25);
//		System.out.println("No Raise:");
//		System.out.println(noRaiseFWB);
//		System.out.println("Raise Five:");
//		System.out.println(raiseFiveFWB);
//		System.out.println("Max Raise:");
//		System.out.println(maxRaiseFWB);

	}

	private void playRounds(int numRounds) {
		table.playRounds(numRounds);
		this.pgnrData = new DataSequence();
		this.pgrfData = new DataSequence();
		this.pgmrData = new DataSequence();
		for (int i = 0; i < PaiGowTable.NUM_SEATS; i++) {
			List<HandResult> results = HandResultConverter.convertPaiGao2Hand(table
					.getSeat(i).getResults());
			PaiGowNoRaise pgnr = new PaiGowNoRaise(100.0, 10.0);
			PaiGowRaiseFive pgrf = new PaiGowRaiseFive(100.0, 10.0);
			PaiGowMaxRaise pgmr = new PaiGowMaxRaise(100.0, 10.0);
			PaiGowNoRaise pgnr25 = new PaiGowNoRaise(100.0, 25.0);
			PaiGowRaiseFive pgrf25 = new PaiGowRaiseFive(100.0, 25.0);
			PaiGowMaxRaise pgmr25 = new PaiGowMaxRaise(100.0, 25.0);
			PaiGowBetHalf bh = new PaiGowBetHalf(100.0, 10.0);
			PaiGowBetHalf bh25 = new PaiGowBetHalf(100.0, 25.0);
			
			for (HandResult h : results) {
				// System.out.print(h);
				// System.out.print(" ");
				pgnr.update(h);
				pgrf.update(h);
				pgmr.update(h);
				pgnr25.update(h);
				pgrf25.update(h);
				pgmr25.update(h);
				bh.update(h);
				bh25.update(h);
			}
			pgnrData.add(pgnr.getStake());
			pgrfData.add(pgrf.getStake());
			pgmrData.add(pgmr.getStake());
			pgnrDataMeta.add(pgnr.getStake());
			pgrfDataMeta.add(pgrf.getStake());
			pgmrDataMeta.add(pgmr.getStake());

			pgnr25Data.add(pgnr25.getStake());
			pgrf25Data.add(pgrf25.getStake());
			pgmr25Data.add(pgmr25.getStake());
			pgnr25DataMeta.add(pgnr25.getStake());
			pgrf25DataMeta.add(pgrf25.getStake());
			pgmr25DataMeta.add(pgmr25.getStake());

			bhData.add(bh.getStake());
			bhDataMeta.add(bh.getStake());
			bh25Data.add(bh25.getStake());
			bh25DataMeta.add(bh25.getStake());
			
			if (pgnr.isDouble()) {
				nrDoubles++;
			}
			if (pgrf.isDouble()) {
				rfDoubles++;
			}
			if (pgmr.isDouble()) {
				mrDoubles++;
			}

			if (pgnr25.isDouble()) {
				nr25Doubles++;
			}
			if (pgrf25.isDouble()) {
				rf25Doubles++;
			}
			if (pgmr25.isDouble()) {
				mr25Doubles++;
			}

			// System.out.println();
			// System.out.println(bjnr);
			// System.out.println(bjrf);
			// System.out.println(pgnr);
			// System.out.println(pgrf);
			// System.out.println(pgmr);
			// System.out.println();
		}
		if (pgnrData.getMean() > pgrfData.getMean()
				&& pgnrData.getMean() > pgmrData.getMean()) {
			nrWins++;
		}
		if (pgrfData.getMean() > pgnrData.getMean()
				&& pgrfData.getMean() > pgmrData.getMean()) {
			rfWins++;
		}
		if (pgmrData.getMean() > pgrfData.getMean()
				&& pgmrData.getMean() > pgnrData.getMean()) {
			mrWins++;
		}

		if (pgnr25Data.getMean() > pgrf25Data.getMean()
				&& pgnr25Data.getMean() > pgmr25Data.getMean()) {
			nr25Wins++;
		}
		if (pgrf25Data.getMean() > pgnr25Data.getMean()
				&& pgrf25Data.getMean() > pgmr25Data.getMean()) {
			rf25Wins++;
		}
		if (pgmr25Data.getMean() > pgrf25Data.getMean()
				&& pgmr25Data.getMean() > pgnr25Data.getMean()) {
			mr25Wins++;
		}

		// System.out.println();
		// System.out.println("PGNR: " + pgnrData.getSize() + " mean = "
		// + pgnrData.getMean() + " max " + pgnrData.getMax() + " min "
		// + pgnrData.getMin());
		// System.out.println("PGRF: " + pgrfData.getSize() + " mean = "
		// + pgrfData.getMean() + " max " + pgrfData.getMax() + " min "
		// + pgrfData.getMin());
		// System.out.println("PGMR: " + pgmrData.getSize() + " mean = "
		// + pgmrData.getMean() + " max " + pgmrData.getMax() + " min "
		// + pgmrData.getMin());
	}

	private void playNewRounds(int numRounds) {
		table = new PaiGowTable();
		playRounds(numRounds);
	}

	private PaiGowBetting calcBetting(int seatNum, int stake, int minBet) {
		PaiGowSeat seat = table.getSeat(seatNum);

		return factory.calcStats(seat, stake, minBet);
	}

	private List<PaiGowHandResult> getResults(int seatNum) {
		PaiGowSeat seat = table.getSeat(seatNum);
		return seat.getResults();
	}
}
