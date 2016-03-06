package com.bhop.game.states;

import static com.bhop.game.util.singleton.SingletonManager.*;
import static com.bhop.game.gameobjects.timecounter.GameEndWatcher.*;
import static com.bhop.game.util.GameUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.bunny.Bunny;
import com.bhop.game.gameobjects.bunny.dummy.DummyBunny;
import com.bhop.game.gameobjects.coloroptions.ColorOptionManager;
import com.bhop.game.gameobjects.coloroptions.ColorOption.BunnyColor;
import com.bhop.game.gameobjects.environment.LightObject;
import com.bhop.game.gameobjects.environment.Sky;
import com.bhop.game.gameobjects.environment.background.BackgroundGenerator;
import com.bhop.game.gameobjects.environment.cloud.CloudGenerator;
import com.bhop.game.gameobjects.gameinformation.DetailedInfo;
import com.bhop.game.gameobjects.gameinformation.InfoIcon;
import com.bhop.game.gameobjects.ground.Ground;
import com.bhop.game.sound.SoundIcon;

public class Menu extends BasicGameState
{
	public static final int ID = 0;
	
	private static boolean playerHasPickedColor;
	
	private List<GameObject> gameObjects;
	
	public Menu()
	{
		playerHasPickedColor = false;
		
		clearSingletons();
		restartGame();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		game.addState(new Play());
		game.getState(Play.ID).init(game.getContainer(), game);
		
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(getSingleton(Sky.class));
		gameObjects.add(getSingleton(LightObject.class));
		gameObjects.add(getSingleton(CloudGenerator.class));
		gameObjects.add(getSingleton(BackgroundGenerator.class));
		gameObjects.add(getSingleton(Ground.class));
		gameObjects.add(getSingleton(ColorOptionManager.class));
		gameObjects.add(getSingleton(DummyBunny.class));
		gameObjects.add(getSingleton(SoundIcon.class));
		gameObjects.add(getSingleton(InfoIcon.class));
		gameObjects.add(getSingleton(DetailedInfo.class));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		renderGameObjects(gameObjects);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		if (InfoIcon.isPlayerIsReadingInfo())
		{
			getSingleton(InfoIcon.class).update(container.getInput());
			getSingleton(SoundIcon.class).update(container.getInput());
		}
		else
		{
			checkForExitGame(container);
			enterPlayStateIfPlayerHasPickedColor(game);
	
			updateGameObjects(gameObjects, container.getInput());
		}
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
			game.enterState(Play.ID);
		}
	}
	
	public static void informPlayerHasPickedColor(BunnyColor bunnyColor) throws SlickException
	{
		getSingleton(Bunny.class).createBunnyAnimation(bunnyColor);
		
		playerHasPickedColor = true;
	}
}
