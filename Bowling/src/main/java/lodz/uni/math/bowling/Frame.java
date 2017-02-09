package lodz.uni.math.bowling;

import lodz.uni.math.bowling.exceptions.InvalidFrameScoreException;

public class Frame {

	private static final int MAX_PINS_VALUE = 10;

	private Roll firstRoll;
	private Roll secondRoll;
	private boolean spare = false;
	private boolean strike = false;
	private int score = 0;

	public Frame(Roll firstRoll, Roll secondRoll) {
		setFirstRoll(firstRoll);
		if (!this.isStrike() && secondRoll!=null) {
			setSecondRoll(secondRoll);
		}
		if (score > MAX_PINS_VALUE) {
			throw new InvalidFrameScoreException();
		}
	}

	private void setFirstRoll(Roll firstRoll) {
		this.firstRoll = firstRoll;
		if (firstRoll.getScore() == MAX_PINS_VALUE) {
			strike = true;
		}
		setScore(firstRoll.getScore());
	}

	public int getFirstRollScore() {
		return firstRoll.getScore();
	}

	private void setSecondRoll(Roll secondRoll) {
		this.secondRoll = secondRoll;
		if (firstRoll.getScore() + secondRoll.getScore() == MAX_PINS_VALUE) {
			spare = true;
		}
		setScore(firstRoll.getScore() + secondRoll.getScore());
	}

	public int getSecondRollScore() {
		return secondRoll.getScore();
	}
	
	public Roll getSecondRoll() {
		return secondRoll;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public boolean isSpare() {
		return spare;
	}

	public boolean isStrike() {
		return strike;
	}

}
