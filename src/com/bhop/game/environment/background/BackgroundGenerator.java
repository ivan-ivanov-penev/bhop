package com.bhop.game.environment.background;

import org.newdawn.slick.SlickException;

import com.bhop.game.util.generator.GeneratedObject;
import com.bhop.game.util.generator.Generator;
import com.bhop.game.util.singleton.Singleton;

public class BackgroundGenerator extends Generator implements Singleton
{
	
	private BackgroundGenerator() throws SlickException
	{
		fillGeneratedObjects();
	}

	protected GeneratedObject createGeneratedObject(float x) throws SlickException
	{
		return new Background(x);
	}

}
