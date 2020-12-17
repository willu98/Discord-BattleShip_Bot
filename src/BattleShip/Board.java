package BattleShip;

import java.util.Random;

public class Board {
	
	//constants used to determine what object occupies the space
	private static final int EMPTY = 0;
	private static final int SHIP = 1;
	private static final int MISS = 2;
	private static final int HIT = 3;
	
	//length of board is 10 units
	private static int BOARD_LEN = 10;
	
	//2d array that represents the board
	private int[][] gameSpaces = new int[BOARD_LEN][BOARD_LEN];
	
	//5 ships for player
	private Ship battleShips[] = new Ship[5];
	
	//random number generator
	Random rand = new Random();

	public Board() {
		//creates 5 ships for the user and are randomly placed on the board
		//user input to move the ships would be way to complicated using only emote input
		//thus randomly placed ship was ideal
		battleShips[0] = new Ship(5, rand.nextInt(2));
		battleShips[1] = new Ship(4, rand.nextInt(2));
		battleShips[2] = new Ship(3, rand.nextInt(2));
		battleShips[3] = new Ship(3, rand.nextInt(2));
		battleShips[4] = new Ship(2, rand.nextInt(2));
		
		//randomly placing the ships
		for(int i = 0; i < battleShips.length; i++) {
			placeShip(battleShips[i]);
		}
	}
	
	
	//placing battleships randomly ono the map, avoiding overlap
	private void placeShip(Ship battleShip) {
		
		//if the ship is horizontal
		if(battleShip.getOrientation() == 0) {
			boolean overlap = true;
			
			//checking for overlap, and if overlap is found randomly generate new position until no more overlap			
			while(overlap) {
				overlap = false;
				battleShip.setPos(rand.nextInt(battleShip.getLen()), rand.nextInt(BOARD_LEN));			
				for(int i = battleShip.getPosX(); i < battleShip.getPosX() + battleShip.getLen(); i++) {
					if(gameSpaces[i][battleShip.getPosY()] == 1) {
						overlap = true;
					}
				}			
			}
			
			//when position is found, setposition
			for(int i = battleShip.getPosX(); i < battleShip.getPosX() + battleShip.getLen(); i++) {
				gameSpaces[i][battleShip.getPosY()] = 1;
			}	
			
		}
		//repeating the same for the other orientation
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
	
	
	//setting the value of a space, important for when user is attacking
	public void setSpace(int posX, int posY) {
		if(this.gameSpaces[posX][posY] == 0) {
			this.gameSpaces[posX][posY] = MISS;
		}
		else if(this.gameSpaces[posX][posY] == 1) {
			this.gameSpaces[posX][posY] = HIT;
		}
	}
	
	
	//gettign the boardspaces arr
	public int[][] getBoardSpaces(){
		return this.gameSpaces;
	}

}
