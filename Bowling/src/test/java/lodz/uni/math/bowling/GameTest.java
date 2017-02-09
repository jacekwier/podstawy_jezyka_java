package lodz.uni.math.bowling;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lodz.uni.math.bowling.Frame;
import lodz.uni.math.bowling.Game;
import lodz.uni.math.bowling.Roll;
import lodz.uni.math.bowling.exceptions.InvalidFramesNumberException;
import lodz.uni.math.bowling.exceptions.InvalidPinsNumberException;

public class GameTest {

	private Frame[] frames = new Frame[12];
	private Frame[] frames2 = new Frame[13];

	private Roll worstRoll = new Roll(0);
	private Roll openFrameRollA = new Roll(4);
	private Roll openFrameRollB = new Roll(5);
	private Roll spareRollA = new Roll(4);
	private Roll spareRollB = new Roll(6);
	private Roll strikeRoll = new Roll(10);

	@Test
	public void WorstGameTest() {
		Game game = new Game();
		for (int i = 0; i < 10; i++) {
			frames[i] = new Frame(worstRoll, worstRoll);
		}
		frames[10] = null;
		frames[11] = null;
		assertEquals(game.initGame(frames), 0);
	}

	@Test
	public void openFrameGameTest() {
		Game game = new Game();
		for (int i = 0; i < 10; i++) {
			frames[i] = new Frame(openFrameRollA, openFrameRollB);
		}
		frames[10] = null;
		frames[11] = null;
		assertEquals(game.initGame(frames), 90);
	}

	@Test
	public void allSpareGameTest() {
		Game game = new Game();
		for (int i = 0; i < 10; i++) {
			frames[i] = new Frame(spareRollA, spareRollB);
		}
		frames[10] = new Frame(spareRollA, null);
		frames[11] = null;
		assertEquals(game.initGame(frames), 140);
	}

	@Test
	public void mixedGameTest() {
		Game game = new Game();
		for (int i = 0; i < 5; i++) {
			frames[i] = new Frame(strikeRoll, null);
		}
		for (int i = 5; i < 10; i++) {
			frames[i] = new Frame(spareRollA, spareRollB);
		}
		frames[10] = new Frame(strikeRoll, null);
		assertEquals(game.initGame(frames), 210);
	}

	@Test
	public void PerfectGameTest() {
		Game game = new Game();
		for (int i = 0; i < 12; i++) {
			frames[i] = new Frame(strikeRoll, null);
		}
		assertEquals(game.initGame(frames), 300);
	}

	@Test(expected = InvalidFramesNumberException.class)
	public void gameValidationTest() {
		Game game = new Game();
		for (int i = 0; i < 9; i++) {
			frames[i] = new Frame(strikeRoll, null);
		}
		game.initGame(frames);
	}

	@Test(expected = InvalidFramesNumberException.class)
	public void gameValidationTest2() {
		Game game = new Game();
		for (int i = 0; i < 13; i++) {
			frames2[i] = new Frame(strikeRoll, null);
		}
		game.initGame(frames2);
	}

	@Test(expected = InvalidFramesNumberException.class)
	public void gameValidationTest3() {
		Game game = new Game();
		for (int i = 0; i < 9; i++) {
			frames[i] = new Frame(strikeRoll, null);
		}
		frames[9] = new Frame(openFrameRollA, openFrameRollB);
		frames[10] = new Frame(strikeRoll, null);
		game.initGame(frames);
	}

	@Test(expected = InvalidFramesNumberException.class)
	public void gameValidationTest4() {
		Game game = new Game();
		for (int i = 0; i < 9; i++) {
			frames[i] = new Frame(strikeRoll, null);
		}
		frames[9] = new Frame(spareRollA, spareRollB);
		frames[10] = new Frame(spareRollA, spareRollB);
		game.initGame(frames);
	}

	@Test(expected = InvalidFramesNumberException.class)
	public void gameValidationTest5() {
		Game game = new Game();
		for (int i = 0; i < 10; i++) {
			frames[i] = new Frame(strikeRoll, null);
		}
		frames[10] = new Frame(spareRollA, spareRollB);
		frames[11] = new Frame(strikeRoll, null);
		game.initGame(frames);
	}

}
