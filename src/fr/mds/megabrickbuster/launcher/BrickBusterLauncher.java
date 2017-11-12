package fr.mds.megabrickbuster.launcher;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.game.EndGame;
import fr.mds.megabrickbuster.game.Game;
import fr.mds.megabrickbuster.game.Menu;
import fr.mds.megabrickbuster.game.MultiGameClient;
import fr.mds.megabrickbuster.game.MultiGameServer;
import fr.mds.megabrickbuster.game.MultiplayerMenu;
import fr.mds.megabrickbuster.game.Scores;

public class BrickBusterLauncher extends StateBasedGame {

    public static int WINDOW_SIZE_X = 964;
    public static int WINDOW_SIZE_Y = 600;
    
    public static final String name = "Mega BrickBuster";
    public static final int menu = 0;
    public static final int solo = 1;
    public static final int multiserver = 2;
    public static final int multiclient = 7;
    public static final int scores = 3;
    public static final int option = 4;
    public static final int multiMenu = 5;
    public static final int endgame = 6;
    
    public static int score;

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());

        try {
            AppGameContainer app = new AppGameContainer(new BrickBusterLauncher(name));
            
            app.setDisplayMode(WINDOW_SIZE_X, WINDOW_SIZE_Y, false);
            app.setTargetFrameRate(60);
            app.setVSync(true);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public BrickBusterLauncher(String name) {
        super(name);
        this.addState(new Menu(menu, WINDOW_SIZE_X, WINDOW_SIZE_Y));
        this.addState(new Game(solo, WINDOW_SIZE_X, WINDOW_SIZE_Y));
        this.addState(new MultiGameServer(multiserver, WINDOW_SIZE_X, WINDOW_SIZE_Y));
        this.addState(new MultiGameClient(multiclient, WINDOW_SIZE_X, WINDOW_SIZE_Y));
        this.addState(new Scores(scores));
        this.addState(new MultiplayerMenu(multiMenu, WINDOW_SIZE_X, WINDOW_SIZE_Y));
        this.addState(new EndGame(endgame));
    }

    @Override
    public void initStatesList(GameContainer arg0) throws SlickException {
        this.enterState(menu);
    }

}
