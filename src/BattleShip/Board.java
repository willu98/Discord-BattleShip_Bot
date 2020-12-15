package BattleShip;

import java.util.Random;

public class Board {
	private static int[][] gameSpaces = new int[10][10];
	private static Ship battleShips[] = new Ship[5];
	Random rand = new Random();
	private static final int EMPTY = 0;
	private static final int SHIP = 1;
	private static final int MISS = 2;
	private static final int HIT = 3;
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
		if(battleShip.getOrientation() == 0) {
			placingHelper(battleShip.getPosX(), battleShip.getPosY(), battleShip);
		}
		else {
			placingHelper(battleShip.getPosY(), battleShip.getPosX(), battleShip);
		}
	}
	
	private void placingHelper(int pos1, int pos2, Ship battleShip) {
		boolean overlap = true;
		while(overlap) {
			overlap = false;
			battleShip.setPos(rand.nextInt(10), rand.nextInt(battleShip.getLen()));			
			for(int i = pos1; i < pos1 + battleShip.getLen(); i++) {
				if(gameSpaces[i][pos2] == 1) {
					overlap = true;
				}
			}			
		}
		
		for(int i = pos1; i < pos1 + battleShip.getLen(); i++) {
			gameSpaces[i][pos2] = 1;
		}	
	}
}
