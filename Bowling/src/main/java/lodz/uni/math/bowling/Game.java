package lodz.uni.math.bowling;

import lodz.uni.math.bowling.exceptions.InvalidFramesNumberException;

public class Game {

	private static final int FRAMES_NUMBER_WITHOUT_BONUS = 10;
	private static final int MAX_FRAMES_NUMBER_WITH_BONUS = 12;

	private Frame[] frames = new Frame[MAX_FRAMES_NUMBER_WITH_BONUS];
	private int framesNumber = 0;
	private int score = 0;

	public int initGame(Frame[] frames) {
		framesCounter(frames);
		validateFramesNumber(frames);
		this.frames = frames;
		return getScore();
	}

	private void framesCounter(Frame[] frames) {
		for (int i = 0; i < frames.length; i++) {
			checkFrame(frames[i]);
		}
	}

	private void checkFrame(Frame frame) {
		if (frame != null) {
			framesNumber++;
		}
	}

	private int getScore() {
		calculateScore();
		for (int i = 0; i < framesNumber; i++) {
			score += frames[i].getScore();
		}
		return score;
	}

	private void calculateScore() {
		for (int i = 0; i < 9; i++) {
			checkBonusPoints(i);
		}
	}

	private void checkBonusPoints(int currentFrameNumber) {
		if (frames[currentFrameNumber].isSpare()) {
			spareSum(frames[currentFrameNumber], frames[currentFrameNumber + 1]);
		} else if (frames[currentFrameNumber].isStrike()) {
			strikeSum(frames[currentFrameNumber], frames[currentFrameNumber + 1], frames[currentFrameNumber + 2]);

		}
	}

	private void spareSum(Frame currentFrame, Frame nextFrame) {
		currentFrame.setScore(currentFrame.getScore() + nextFrame.getFirstRollScore());
	}

	private void strikeSum(Frame currentFrame, Frame nextFrame, Frame anotherFrame) {
		if (nextFrame.isStrike()) {
			currentFrame.setScore(
					currentFrame.getScore() + nextFrame.getFirstRollScore() + anotherFrame.getFirstRollScore());
		} else {
			currentFrame
					.setScore(currentFrame.getScore() + nextFrame.getFirstRollScore() + nextFrame.getSecondRollScore());
		}
	}

	private void validateFramesNumber(Frame[] frames) {
		if (framesNumber < FRAMES_NUMBER_WITHOUT_BONUS || framesNumber > MAX_FRAMES_NUMBER_WITH_BONUS) {
			throw new InvalidFramesNumberException();
		}
		validateFirstBonusFrame(frames);
		validateSecondBonusFrame(frames);
	}

	private void validateFirstBonusFrame(Frame[] frames) {
		if (frames[10] != null && !frames[9].isSpare() && !frames[9].isStrike()) {
			throw new InvalidFramesNumberException();
		}
		if (frames[9].isSpare() && frames[10].getSecondRoll() != null) {
			throw new InvalidFramesNumberException();
		}
	}

	private void validateSecondBonusFrame(Frame[] frames) {
		if (frames[10] != null && frames[11] != null && (!frames[9].isStrike() || !frames[10].isStrike())) {
			throw new InvalidFramesNumberException();
		}
	}
}
