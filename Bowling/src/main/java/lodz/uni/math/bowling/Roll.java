package lodz.uni.math.bowling;

import lodz.uni.math.bowling.exceptions.InvalidPinsNumberException;

public class Roll {

	private static final int MIN_PINS_VALUE = 0;
	private static final int MAX_PINS_VALUE = 10;

	private int score = 0;

	public Roll(int pins) {
		if (pins > MAX_PINS_VALUE || pins < MIN_PINS_VALUE) {
			throw new InvalidPinsNumberException();
		}
		score = pins;
	}

	public int getScore() {
		return score;
	}
}
