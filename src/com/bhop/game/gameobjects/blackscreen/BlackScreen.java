package com.bhop.game.gameobjects.blackscreen;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.timecounter.GameEndWatcher;
import com.bhop.game.util.singleton.Singleton;

public class BlackScreen implements Singleton, GameObject
{
	
	private static final float INCREMENT_VALUE = 0.01f;

	private final Image blackScreen;
	
	private float transpeencyIncrementor;
	
	private BlackScreen() throws SlickException
	{
		blackScreen = new Image(SPRITE_DIR + "black_screen/screen.png");
	}
	
	@Override
	public void update(Input input) throws SlickException {}

	@Override
	public void render() throws SlickException
	{
		if (GameEndWatcher.isGameEnd())
		{
			blackScreen.draw(0, 0, new Color(1, 1, 1, getTranspeencyIncrementor()));
		}
	}
	
	private float getTranspeencyIncrementor()
	{
		if (transpeencyIncrementor < 1)
		{
			transpeencyIncrementor += INCREMENT_VALUE;
		}
		
		return transpeencyIncrementor;
	}

}
