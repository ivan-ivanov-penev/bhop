package com.bhop.game.states;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.singleton.SingletonManager.getSingleton;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.loadingscreen.LoadingScreenManager;

public class LoadingScreen extends BasicGameState
{
	
	public static final int ID = 0;
	
	private static final int LOADING_TIME_SECONDS = RANDOM.nextInt(3) + 1;
	
	private List<GameObject> gameObjects;
	
	private int counter;

	@Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(getSingleton(LoadingScreenManager.class));
    }

	@Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
		renderGameObjects(gameObjects);
    }

	@Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
		updateGameObjects(gameObjects, container.getInput());
		
		if (counter == FPS * LOADING_TIME_SECONDS)
		{
			game.getState(Menu.ID).init(game.getContainer(), game);
			game.enterState(Menu.ID);
		}
		else
		{
			counter += 1;
		}
    }

	@Override
    public int getID()
    {
	    return 0;
    }

}
