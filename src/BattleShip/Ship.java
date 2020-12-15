package BattleShip;

public class Ship {
	private int length;
	private int orientation;
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
		return posX;
	}
	
	public int getPosX() {
		return orientation;
	}
	
	public int getPosY() {
		return posY;
	}
	
	
	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		
	}
}
