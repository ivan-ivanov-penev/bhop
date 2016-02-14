package com.bhop.game.states;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.environment.BackgroundGenerator;
import com.bhop.game.environment.CloudGenerator;
import com.bhop.game.environment.LightObject;
import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.bunny.Bunny;
import com.bhop.game.objects.ground.Ground;
import com.bhop.game.objects.log.LogGenerator;

import static com.bhop.game.util.singleton.SingletonManager.*;

public class Play extends BasicGameState
{
	public static final int ID = 1;
	
	private List<GameObject> gameObjects;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(getSingleton(BackgroundGenerator.class));
		gameObjects.add(getSingleton(LightObject.class));
		gameObjects.add(getSingleton(CloudGenerator.class));
		gameObjects.add(getSingleton(Ground.class));
		gameObjects.add(getSingleton(LogGenerator.class));
		gameObjects.add(getSingleton(Bunny.class));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		for (GameObject gameObject : gameObjects)
		{
			gameObject.render();
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		checkForExitGame(container);

		for (GameObject gameObject : gameObjects)
		{
			gameObject.update(container.getInput());
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
}
