package BattleShip;

import java.util.Random;

public class Board {
	private static final int EMPTY = 0;
	private static final int SHIP = 1;
	private static final int MISS = 2;
	private static final int HIT = 3;
	private static int BOARD_LEN = 10;
	private int[][] gameSpaces = new int[BOARD_LEN][BOARD_LEN];
	private Ship battleShips[] = new Ship[5];
	Random rand = new Random();

	public Board() {
		battleShips[0] = new Ship(5, rand.nextInt(2));
		battleShips[1] = new Ship(4, rand.nextInt(2));
		battleShips[2] = new Ship(3, rand.nextInt(2));
		battleShips[3] = new Ship(3, rand.nextInt(2));
		battleShips[4] = new Ship(2, rand.nextInt(2));
		
		
		for(int i = 0; i < battleShips.length; i++) {
			placeShip(battleShips[i]);
		}
	}
	
	
	//placing battleships randomly ono the map, avoiding overlap
	private void placeShip(Ship battleShip) {
		//System.out.println(battleShip.getOrientation());
		if(battleShip.getOrientation() == 0) {
			boolean overlap = true;
			while(overlap) {
				overlap = false;
				battleShip.setPos(rand.nextInt(battleShip.getLen()), rand.nextInt(BOARD_LEN));			
				for(int i = battleShip.getPosX(); i < battleShip.getPosX() + battleShip.getLen(); i++) {
					if(gameSpaces[i][battleShip.getPosY()] == 1) {
						overlap = true;
					}
				}			
			}
			
			for(int i = battleShip.getPosX(); i < battleShip.getPosX() + battleShip.getLen(); i++) {
				gameSpaces[i][battleShip.getPosY()] = 1;
			}	
			
		}
		else {
			boolean overlap = true;
			while(overlap) {
				overlap = false;
				battleShip.setPos(rand.nextInt(10), rand.nextInt(battleShip.getLen()));			
				for(int i = battleShip.getPosY(); i < battleShip.getPosY() + battleShip.getLen(); i++) {
					if(gameSpaces[battleShip.getPosX()][i] == 1) {
						overlap = true;
					}
				}			
			}
			
			for(int i = battleShip.getPosY(); i < battleShip.getPosY() + battleShip.getLen(); i++) {
				gameSpaces[battleShip.getPosX()][i]  = 1;
			}	
		}
	}
	
	public void setSpace(int posX, int posY) {
		if(this.gameSpaces[posX][posY] == 0) {
			this.gameSpaces[posX][posY] = MISS;
		}
		else if(this.gameSpaces[posX][posY] == 1) {
			this.gameSpaces[posX][posY] = HIT;
		}
	}
	
	public int[][] getBoardSpaces(){
		return this.gameSpaces;
	}

}
