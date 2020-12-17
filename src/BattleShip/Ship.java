package BattleShip;

public class Ship {
	//length of ship
	private int length;
	
	//orientation of ship, 0=>horizontal and 1=>vertical
	private int orientation;
	
	//x and y position of the ship
	private int posX;
	private int posY;
	
	
	public Ship(int length, int orientation) {
		this.length = length;
		this.orientation = orientation;
	}
	
	public int getLen() {
		return length;
	}
	public int getOrientation() {
		return orientation;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	
	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		
	}
}
