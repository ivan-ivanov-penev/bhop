package com.bhop.game.states;

import static com.bhop.game.util.singleton.SingletonManager.getSingleton;
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
import com.bhop.game.objects.bunny.Bunny;
import com.bhop.game.objects.carrot.CarrotManager;
import com.bhop.game.objects.gameinformation.AgainButton;
import com.bhop.game.objects.gameinformation.GameInformation;
import com.bhop.game.objects.ground.Ground;
import com.bhop.game.objects.indexator.Indexator;
import com.bhop.game.objects.log.LogGenerator;
import com.bhop.game.objects.timecounter.GameEndWatcher;
import com.bhop.game.objects.timecounter.TimeCounter;

public class Play extends BasicGameState
{
	public static final int ID = 1;
	
	private List<GameObject> gameObjects;
	
	private static boolean playerWantsToRestart;
	
	public Play()
	{
		playerWantsToRestart = false;
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
		gameObjects.add(getSingleton(LogGenerator.class));
		gameObjects.add(getSingleton(Bunny.class));
		gameObjects.add(getSingleton(CarrotManager.class));
		gameObjects.add(getSingleton(Indexator.class));
		gameObjects.add(getSingleton(GameInformation.class));
		gameObjects.add(getSingleton(TimeCounter.class));
		gameObjects.add(getSingleton(AgainButton.class));
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
		enterMenuStateIfPlayerWantsToRestart(game);
		
		if (GameEndWatcher.isGameEnd())
		{
			getSingleton(AgainButton.class).update(container.getInput());
			return;
		}

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
	
	private void enterMenuStateIfPlayerWantsToRestart(StateBasedGame game) throws SlickException
	{
		if (playerWantsToRestart)
		{
			gameObjects.clear();
			
			game.addState(new Menu());
			game.getState(Menu.ID).init(game.getContainer(), game);
			game.enterState(Menu.ID);
		}
	}
	
	public static void alertPlayerWantsToRestart()
	{
		playerWantsToRestart = true;
	}
	
}
