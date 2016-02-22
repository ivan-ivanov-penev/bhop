package com.bhop.game.states;

import static com.bhop.game.util.singleton.SingletonManager.*;
import static com.bhop.game.objects.timecounter.GameEndWatcher.*;
import static com.bhop.game.util.GameUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.environment.LightObject;
import com.bhop.game.environment.Sky;
import com.bhop.game.environment.background.BackgroundGenerator;
import com.bhop.game.environment.cloud.CloudGenerator;
import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.bunny.DummyBunny;
import com.bhop.game.objects.coloroptions.ColorOptionManager;
import com.bhop.game.objects.coloroptions.ColorOption.BunnyColor;
import com.bhop.game.objects.ground.Ground;

public class Menu extends BasicGameState
{
	public static final int ID = 0;
	
	private static BunnyColor bunnyColor;
	
	private static boolean playerHasPickedColor;
	
	private List<GameObject> gameObjects;
	
	public Menu()
	{
		bunnyColor = BunnyColor.BONUS;
		playerHasPickedColor = false;
		
		clearSingletons();
		restartGame();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(getSingleton(Sky.class));
		gameObjects.add(getSingleton(LightObject.class));
		gameObjects.add(getSingleton(CloudGenerator.class));
		gameObjects.add(getSingleton(BackgroundGenerator.class));
		gameObjects.add(getSingleton(Ground.class));
		gameObjects.add(getSingleton(DummyBunny.class));
		gameObjects.add(getSingleton(ColorOptionManager.class));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		renderGameObjects(gameObjects);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		checkForExitGame(container);
		enterPlayStateIfPlayerHasPickedColor(game);

		updateGameObjects(gameObjects, container.getInput());
	}

	@Override
	public int getID()
	{
		return ID;
	}
	
	private void checkForExitGame(GameContainer container)
	{
		if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
		{
			container.exit();
		}
	}
	
	private void enterPlayStateIfPlayerHasPickedColor(StateBasedGame game) throws SlickException
	{
		if (playerHasPickedColor)
		{
			game.addState(new Play());
			game.getState(Play.ID).init(game.getContainer(), game);
			game.enterState(Play.ID);
		}
	}
	
	public static void informPlayerHasPickedColor(BunnyColor bunnyColor)
	{
		Menu.bunnyColor = bunnyColor;
		
		playerHasPickedColor = true;
	}
	
	public static BunnyColor getSelectedBunnyColor()
	{
		return bunnyColor;
	}
}
