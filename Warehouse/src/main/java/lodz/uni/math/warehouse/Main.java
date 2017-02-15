package lodz.uni.math.warehouse;

import org.apache.log4j.Logger;

import lodz.uni.math.warehouse.pojo.Package;
import lodz.uni.math.warehouse.pojo.TypeOfPackage;
import lodz.uni.math.warehouse.pojo.Warehouse;

/**
 * Hello world!
 *
 */
public class Main {
	private static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		Warehouse warehouse = new Warehouse(5, 7, 4);
		Package package1 = new Package(TypeOfPackage.CARPARTS, "Pierścienie do Poloneza", "pol10", 1);
		// logger.info(warehouse.showStock());
		warehouse.addPackage(package1, 0, 2);

		package1 = new Package(TypeOfPackage.CARPARTS, "Wentyle do Stara", "st10", 1);
		warehouse.addPackage(package1, 0, 2);
		logger.info(warehouse.showStock());

		package1 = new Package(TypeOfPackage.TOYS, "Jessie", "ToyStoryJess", 2);
		warehouse.addPackage(package1, 0, 2);
		logger.info(warehouse.showStock());

		package1 = new Package(TypeOfPackage.CARPARTS, "Dwumas do Passata B5", "pb5dw", 3);
		warehouse.addPackage(package1, 0, 2);
		logger.info(warehouse.showStock());

		logger.info(warehouse.showColumn(0, 2));

		warehouse.getAllPackagesByType(TypeOfPackage.CARPARTS);

		warehouse.getPackageByNumber("pol10");
		logger.info(warehouse.showColumn(0, 0));
		logger.info(warehouse.showColumn(1, 0));
		logger.info(warehouse.showColumn(0, 2));
		logger.info(warehouse.getHistoryOfLastGetPackage());
	}
}
