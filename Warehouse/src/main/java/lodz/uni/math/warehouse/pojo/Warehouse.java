package lodz.uni.math.warehouse.pojo;

import java.util.ArrayList;
import java.util.Date;

import lodz.uni.math.warehouse.exceptions.*;

public class Warehouse {
	private int sizeX;
	private int sizeY;
	private int sizeZ;
	private ArrayList<Package> packagesList;
	private Package[][][] packagesStore;
	private int reserved1x;
	private int reserved1y;
	private int reserved2x;
	private int reserved2y;
	private StringBuilder historyOfLastGetPackage;
	private Boolean addToHistory = false;

	public Warehouse(int sizeX, int sizeY, int sizeZ) {
		checkSize(sizeX);
		checkSize(sizeY);
		checkSize(sizeZ);
		checkArea(sizeX, sizeY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
		reservePlaces(this.sizeX);
		this.packagesList = new ArrayList<Package>();
		this.packagesStore = new Package[this.sizeX][this.sizeY][this.sizeY];
	}

	private void reservePlaces(int sizeX) {
		reserved1x = 0;
		reserved1y = 0;
		if (sizeX > 1) {
			reserved2x = 1;
			reserved2y = 0;
		} else {
			reserved2x = 0;
			reserved2y = 1;
		}

	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public int getSizeZ() {
		return sizeZ;
	}

	public ArrayList<Package> getPackages() {
		return packagesList;
	}

	private void checkSize(int size) {
		if (size < 1) {
			throw new WrongSize();
		}
	}

	public StringBuffer showStock() {
		StringBuffer toReturn = new StringBuffer();
		toReturn.append("\t");
		for (int i = 0; i < sizeX; i++) {
			toReturn.append("\t" + i);
		}
		toReturn.append("\n");
		for (int i = 0; i < sizeX + 1; i++) {
			toReturn.append("\tX");
		}
		for (int i = 0; i < sizeY; i++) {
			toReturn.append("\n" + i + "\tX");
			for (int j = 0; j < sizeX; j++) {
				if (j == reserved1x && i == reserved1y) {
					toReturn.append("\tR");
					continue;
				} else if (j == reserved2x && i == reserved2y) {
					toReturn.append("\tR");
					continue;
				}
				for (int k = 0; k < sizeZ; k++) {
					if (packagesStore[j][i][k] == null) {
						toReturn.append("\t" + k);
						break;
					} else if (k == sizeZ - 1) {
						toReturn.append("\t" + sizeZ);
					}
				}
			}
		}
		toReturn.append("\n");
		return toReturn;
	}

	public Package[][][] getPackagesStore() {
		return packagesStore;
	}

	public void addPackage(Package newPackage, int x, int y) {
		chceckNumberNewPackage(newPackage);
		checkPlaceParameters(x, y);
		checkIsReserved(x, y);
		Package lastPackageOnStos = getTopPackageOnStos(x, y);
		if (lastPackageOnStos == null) {
			putNewPackageOnStos(newPackage, x, y, 0);
			newPackage.addOneMove();
		} else {
			if (lastPackageOnStos.getPositionZ() == sizeZ - 1) {
				throw new CurrentStosIsFull();
			} else if (lastPackageOnStos.getPriority() > newPackage.getPriority()) {// if
																					// is
																					// place
																					// but
																					// is
																					// bad
																					// priority
																					// order
				while (lastPackageOnStos.getPriority() > newPackage.getPriority()) {
					if (lastPackageOnStos.getPriority() == 2) {
						movePackage(lastPackageOnStos, reserved2x, reserved2y);
					} else if (lastPackageOnStos.getPriority() == 3) {
						movePackage(lastPackageOnStos, reserved1x, reserved1y);
					}
					lastPackageOnStos = getTopPackageOnStos(x, y);
				}
				putNewPackageOnStos(newPackage, x, y, lastPackageOnStos.getPositionZ() + 1);
				newPackage.addOneMove();
				// cleaning reserved places
				Package lastPackageOnReserved = getTopPackageOnStos(reserved2x, reserved2y);
				while (lastPackageOnReserved != null) {
					movePackage(lastPackageOnReserved, x, y);
					lastPackageOnReserved = getTopPackageOnStos(reserved2x, reserved2y);
				}
				lastPackageOnReserved = getTopPackageOnStos(reserved1x, reserved1y);
				while (lastPackageOnReserved != null) {
					movePackage(lastPackageOnReserved, x, y);
					lastPackageOnReserved = getTopPackageOnStos(reserved1x, reserved1y);
				}
			} else {
				putNewPackageOnStos(newPackage, x, y, lastPackageOnStos.getPositionZ() + 1);
				newPackage.addOneMove();
			}
		}
	}

	private void putNewPackageOnStos(Package newPackage, int x, int y, int z) {
		newPackage.setAddDate(new Date());
		newPackage.setPositionX(x);
		newPackage.setPositionY(y);
		newPackage.setPositionZ(z);
		packagesList.add(newPackage);
		packagesStore[x][y][z] = newPackage;
	}

	private void checkIsReserved(int x, int y) {
		if (x == reserved1x && y == reserved1y) {
			throw new ReservedPlace();
		} else if (x == reserved2x && y == reserved2y) {
			throw new ReservedPlace();
		}
	}

	private void movePackage(Package packageToMove, int newX, int newY) {
		if (!packagesList.contains(packageToMove)) {
			throw new PackageNotExsists();
		}
		if (packageToMove.getPositionZ() != sizeZ - 1 && packagesStore[packageToMove.getPositionX()][packageToMove
				.getPositionY()][packageToMove.getPositionZ() + 1] != null) {
			throw new CantMovePackage();
		}

		if (packagesStore[newX][newY][sizeZ - 1] != null) {
			throw new CurrentStosIsFull();
		}

		else if (packagesStore[newX][newY][0] == null) {
			if (addToHistory) {
				historyOfLastGetPackage
						.append("- " + packageToMove.getNumber() + ": from (" + packageToMove.getPositionX() + ","
								+ packageToMove.getPositionY() + "," + packageToMove.getPositionZ() + ") to (");
				historyOfLastGetPackage.append(newX + "," + newY + ",0)\n");
			}
			packagesStore[packageToMove.getPositionX()][packageToMove.getPositionY()][packageToMove
					.getPositionZ()] = null;
			packagesStore[newX][newY][0] = packageToMove;
			packageToMove.addOneMove();
			packageToMove.setPositionX(newX);
			packageToMove.setPositionY(newY);
			packageToMove.setPositionZ(0);
		} else {
			Package lastPackageOnStos = getTopPackageOnStos(newX, newY);
			if (lastPackageOnStos.getPriority() > packageToMove.getPriority()) {
				throw new PriorityError();
			} else {
				if (addToHistory) {
					historyOfLastGetPackage
							.append("- " + packageToMove.getNumber() + ": from (" + packageToMove.getPositionX() + ","
									+ packageToMove.getPositionY() + "," + packageToMove.getPositionZ() + ") to (");
				}
				packagesStore[packageToMove.getPositionX()][packageToMove.getPositionY()][packageToMove
						.getPositionZ()] = null;
				packagesStore[newX][newY][lastPackageOnStos.getPositionZ() + 1] = packageToMove;
				packageToMove.addOneMove();
				packageToMove.setPositionX(newX);
				packageToMove.setPositionY(newY);
				packageToMove.setPositionZ(lastPackageOnStos.getPositionZ() + 1);
				if (addToHistory) {
					historyOfLastGetPackage.append(newX + "," + newY + "," + packageToMove.getPositionZ() + ")\n");
				}
			}
		}
	}

	private void chceckNumberNewPackage(Package newPackage) {
		for (Package p : packagesList) {
			if (newPackage.getNumber().equals(p.getNumber())) {
				throw new PackageExsists();
			}
		}
	}

	public Package getTopPackageOnStos(int x, int y) {
		if (packagesStore[x][y][0] == null) {
			return null;
		} else {
			for (int i = 0; i < sizeZ; i++) {
				if (packagesStore[x][y][i] == null) {
					return packagesStore[x][y][i - 1];
				}
			}
			return packagesStore[x][y][sizeZ - 1];
		}
	}

	private void checkPlaceParameters(int x, int y) {
		if (x < 0 || x >= sizeX) {
			throw new WrongPlaceParameters();
		}
		if (y < 0 || y >= sizeY) {
			throw new WrongPlaceParameters();
		}
	}

	public StringBuffer showColumn(int x, int y) {
		checkPlaceParameters(x, y);
		StringBuffer toReturn = new StringBuffer();
		for (int i = sizeZ - 1; i >= 0; i--) {
			if (packagesStore[x][y][i] != null) {
				Package currentPackage = packagesStore[x][y][i];
				toReturn.append("\n" + currentPackage.getType() + " - ");
				toReturn.append("Priority: " + currentPackage.getPriority() + ", ");
				toReturn.append("Number: " + currentPackage.getNumber() + ", ");
				toReturn.append(currentPackage.getDescription() + ", ");
				toReturn.append("Date: " + currentPackage.getAddDate() + ", ");
				toReturn.append("Moves: " + currentPackage.getMovesCounter());
			}
		}
		if (toReturn.equals(new StringBuffer())) {
			toReturn.append("Column: x=" + x + ", y=" + y + " is EMPTY");
		} else {
			toReturn = new StringBuffer("Column: x=" + x + ", y=" + y + toReturn);
		}
		toReturn.append("\n");
		return toReturn;
	}

	private void checkArea(int x, int y) {
		if (x * y < 3) {
			throw new TooSmallPlaceToWarehouse();
		}
	}

	public ArrayList<Package> getAllPackagesByType(TypeOfPackage typeOfPackage) {
		ArrayList<Package> listToReturn = new ArrayList<Package>();
		for (Package p : packagesList) {
			if (p.getType() == typeOfPackage) {
				listToReturn.add(p);
			}
		}
		return listToReturn;
	}

	public Package getPackageByNumber(String packageNumber) {
		Package packageToGet = null;
		for (Package p : packagesList) {
			if (p.getNumber().equals(packageNumber)) {
				packageToGet = p;
			}
		}
		if (packageToGet == null) {
			throw new PackageNotExsists();
		}

		addToHistory = true;
		historyOfLastGetPackage = new StringBuilder("History of get: " + packageNumber + "\n");
		if (packageToGet.getPositionZ() == sizeZ - 1) {// if is on top
			historyOfLastGetPackage.append("- " + packageToGet.getNumber() + ": from (" + packageToGet.getPositionX()
					+ "," + packageToGet.getPositionY() + "," + packageToGet.getPositionZ() + ") to (");
			historyOfLastGetPackage.append("OUT OF WAREHOUSE\n");
			removeFromWarehouse(packageToGet);
			addToHistory = false;
			return packageToGet;
		} else {// digging
			Package packageOnReserved = null;
			Package packageOnTop = getTopPackageOnStos(packageToGet.getPositionX(), packageToGet.getPositionY());
			while (packageOnTop != packageToGet) {
				if (packageOnTop.getPriority() == 3) {
					movePackage(packageOnTop, reserved1x, reserved1y);
				} else if (packageOnTop.getPriority() == 2) {
					movePackage(packageOnTop, reserved2x, reserved2y);
				} else if (packageOnTop.getPriority() == 1) {
					packageOnReserved = getTopPackageOnStos(reserved1x, reserved1y);
					if (packageOnReserved != null) {
						while (packageOnReserved.getPriority() != 2) {
							movePackage(packageOnReserved, reserved2x, reserved2y);
							packageOnReserved = getTopPackageOnStos(reserved1x, reserved1y);
							if (packageOnReserved == null) {
								break;
							}
						}
					}
					movePackage(packageOnTop, reserved1x, reserved1y);
				} else {
					throw new PriorityError();
				}
				packageOnTop = getTopPackageOnStos(packageToGet.getPositionX(), packageToGet.getPositionY());
			}
			historyOfLastGetPackage.append("- " + packageToGet.getNumber() + ": from (" + packageToGet.getPositionX()
					+ "," + packageToGet.getPositionY() + "," + packageToGet.getPositionZ() + ") to ");
			historyOfLastGetPackage.append("OUT OF WAREHOUSE\n");
			removeFromWarehouse(packageToGet);// remove , this is when i get
												// this package

			do {// cleaning reserved places
				packageOnReserved = getTopPackageOnStos(reserved1x, reserved1y);
				if (packageOnReserved != null) {
					movePackage(packageOnReserved, packageToGet.getPositionX(), packageToGet.getPositionY());
				} else {
					break;
				}
			} while (packageOnReserved.getPriority() == 1);
			do {
				packageOnReserved = getTopPackageOnStos(reserved2x, reserved2y);
				if (packageOnReserved != null) {
					if (packageOnReserved.getPriority() == 3) {
						movePackage(packageOnReserved, reserved1x, reserved1y);
					} else if (packageOnReserved.getPriority() == 2) {
						movePackage(packageOnReserved, packageToGet.getPositionX(), packageToGet.getPositionY());
					}
				}
			} while (packageOnReserved != null);
			do {
				packageOnReserved = getTopPackageOnStos(reserved1x, reserved1y);
				if (packageOnReserved != null) {
					if (packageOnReserved.getPriority() == 3) {
						movePackage(packageOnReserved, packageToGet.getPositionX(), packageToGet.getPositionY());
					}
				}
			} while (packageOnReserved != null);

			addToHistory = false;
			return packageToGet;
		}

	}

	private void removeFromWarehouse(Package packageToRemove) {
		packageToRemove.addOneMove();
		packagesStore[packageToRemove.getPositionX()][packageToRemove.getPositionY()][packageToRemove
				.getPositionZ()] = null;
	}

	public StringBuilder getHistoryOfLastGetPackage() {
		return historyOfLastGetPackage;
	}
}
