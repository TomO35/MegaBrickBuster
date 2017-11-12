package fr.mds.megabrickbuster.game;

public class MultiGame extends Game {

	private static int STATE = 2;
	
	public MultiGame(int state, int windowSizeX, int windowSizeY) {
		super(state, windowSizeX, windowSizeY);
	}
	
	//TODO EVERYTHING
	
	@Override
	public int getID() {
		return MultiGame.STATE;
	}
}
