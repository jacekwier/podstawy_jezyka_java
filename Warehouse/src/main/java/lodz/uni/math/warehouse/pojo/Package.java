package lodz.uni.math.warehouse.pojo;

import java.util.Date;

import lodz.uni.math.warehouse.exceptions.WrongPriorityParameter;

public class Package {
	private TypeOfPackage type;
	private String description;
	private int movesCounter;
	private String number;
	private int priority;
	private int positionX;
	private int positionY;
	private int positionZ;
	private Date addDate;
	
	public Package(TypeOfPackage type, String description, String number, int priority){
		checkPriority(priority);
		this.type=type;
		this.setDescription(description);
		if(description==null){
			this.setDescription("");
		}
		this.number=number;
		this.priority=priority;
		this.movesCounter=0;
	}
	
	private void checkPriority(int priority){
		if(!(priority==1 || priority==2 || priority==3)){
			throw new WrongPriorityParameter();
		}
	}

	public TypeOfPackage getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMovesCounter() {
		return movesCounter;
	}

	public void setMovesCounter(int movesCounter) {
		this.movesCounter = movesCounter;
	}

	public String getNumber() {
		return number;
	}

	public int getPriority() {
		return priority;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionZ() {
		return positionZ;
	}

	public void setPositionZ(int positionZ) {
		this.positionZ = positionZ;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	public void addOneMove(){
		this.movesCounter+=1;
	}

}
