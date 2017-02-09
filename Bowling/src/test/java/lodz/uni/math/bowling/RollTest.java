package lodz.uni.math.bowling;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lodz.uni.math.bowling.Roll;
import lodz.uni.math.bowling.exceptions.InvalidPinsNumberException;

public class RollTest {

	private Roll roll;

	@Before
	public void initObjects() {
		roll = new Roll(5);
	}

	@Test
	public void getRollScoreTest() {
		assertEquals(roll.getScore(), 5);
	}

	@Test(expected = InvalidPinsNumberException.class)
	public void rollValidationTest() {
		Roll roll2 = new Roll(-1);
	}

	@Test(expected = InvalidPinsNumberException.class)
	public void pinsNumberValidationTest2() {
		Roll roll3 = new Roll(11);
	}

}
