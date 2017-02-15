import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lodz.uni.math.warehouse.exceptions.WrongPriorityParameter;
import lodz.uni.math.warehouse.pojo.Package;
import lodz.uni.math.warehouse.pojo.TypeOfPackage;

public class PackageTest {
	private static Package testPackage;

	@BeforeClass
	public static void initObjects(){
		testPackage=new Package(TypeOfPackage.CARPARTS, "Pierścienie do Poloneza", "pol10", 1);
	}
	@Before
	public void reset(){
		testPackage=new Package(TypeOfPackage.CARPARTS, "Pierścienie do Poloneza", "pol10", 1);
	}
	
	@Test 
	public void testPackageWithNullDescription(){
		testPackage=new Package(TypeOfPackage.CARPARTS, null, "pol10", 1);
	}
	
	@Test(expected=WrongPriorityParameter.class)
	public void testPeselValidationLetters(){
		testPackage=new Package(TypeOfPackage.CARPARTS, "Pierścienie do Poloneza", "pol10", 8);
	}
	
	@Test
	public void testPackagePriority(){
		testPackage=new Package(TypeOfPackage.CARPARTS, "Pierścienie do Poloneza", "pol10", 2);
		testPackage=new Package(TypeOfPackage.CARPARTS, "Pierścienie do Poloneza", "pol10", 3);
	}
	
	@Test
	public void testGetType(){
		assertEquals(testPackage.getType(), TypeOfPackage.CARPARTS);
	}
	
	@Test
	public void testGetDescription(){
		assertEquals(testPackage.getDescription(), "Pierścienie do Poloneza");
	}
	
	@Test
	public void testGetMovesCounter(){
		assertEquals(testPackage.getMovesCounter(), 0);
	}
	
	@Test
	public void testSetMovesCounter(){
		testPackage.setMovesCounter(5);
		assertEquals(testPackage.getMovesCounter(), 5);
	}
	
	@Test
	public void testGetNumber(){
		assertEquals(testPackage.getNumber(), "pol10");
	}
	
	@Test
	public void testGetPriority(){
		assertEquals(testPackage.getPriority(), 1);
	}
	
	@Test
	public void testSetPositionX(){
		testPackage.setPositionX(3);
		assertEquals(testPackage.getPositionX(), 3);
	}
	
	@Test
	public void testSetPositionY(){
		testPackage.setPositionY(4);
		assertEquals(testPackage.getPositionY(), 4);
	}
	
	@Test
	public void testSetPositionZ(){
		testPackage.setPositionZ(5);
		assertEquals(testPackage.getPositionZ(), 5);
	}
	
	@Test
	public void testSetAddDateAndGet(){
		Date data=new Date();
		testPackage.setAddDate(data);
		assertEquals(testPackage.getAddDate(), data);
	}
	
	@Test
	public void testAddOneMove(){
		testPackage.addOneMove();
		assertEquals(testPackage.getMovesCounter(), 1);
	}

}
