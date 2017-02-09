package lodz.uni.math.bowling;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lodz.uni.math.bowling.Frame;
import lodz.uni.math.bowling.Roll;
import lodz.uni.math.bowling.exceptions.InvalidFrameScoreException;

public class FrameTest {

	private Roll roll1A = new Roll(2);
	private Roll roll1B = new Roll(5);
	
	private Roll roll2A = new Roll(5);
	private Roll roll2B = new Roll(5);
	
	private Roll roll3A = new Roll(10);
	private Roll roll3B = null;
	
	private Roll roll4A = new Roll(6);
	private Roll roll4B = new Roll(9);

	private Frame frame;
	private Frame frame2;
	private Frame frame3;
	private Frame frame4;

	@Before
	public void initObjects() {
		frame = new Frame(roll1A, roll1B);
		frame2 = new Frame(roll2A, roll2B);
		frame3 = new Frame(roll3A, roll3B);
	}

	@Test
	public void getFrameScoreTest() {
		assertEquals(frame.getScore(), 7);
	}

	@Test
	public void getFrameScoreTest2() {
		assertEquals(frame2.getScore(), 10);
	}

	@Test
	public void getFrameScoreTest3() {
		assertEquals(frame3.getScore(), 10);
	}

	@Test
	public void spareFrameTest() {
		assertEquals(frame2.isSpare(), true);
	}

	@Test
	public void spareFrameTest2() {
		assertEquals(frame.isSpare(), false);
	}

	@Test
	public void strikeFrameTest() {
		assertEquals(frame3.isStrike(), true);
	}

	@Test
	public void strikeFrameTest2() {
		assertEquals(frame.isStrike(), false);
	}

	@Test(expected = InvalidFrameScoreException.class)
	public void frameScoreValidationTest() {
		frame4 = new Frame(roll4A, roll4B);
	}

}
