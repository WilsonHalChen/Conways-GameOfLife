package gameEngine;

public class Tile {

	private int positionX;
	private int positionY;
	
	public Tile(int x, int y){
		positionX = x;
		positionY = y;
	}
	
	public int getPosX(){
		return positionX;
	}
	
	public int getPosY(){
		return positionY;
	}
}
