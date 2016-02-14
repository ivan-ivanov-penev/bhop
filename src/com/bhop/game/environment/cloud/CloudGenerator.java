package com.bhop.game.environment.cloud;

import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.SlickException;

import com.bhop.game.util.generator.GeneratedObject;
import com.bhop.game.util.generator.Generator;
import com.bhop.game.util.singleton.Singleton;

public class CloudGenerator extends Generator implements Singleton
{
	
	private CloudGenerator() throws SlickException
	{
		super();
	}
	
	protected void fillGeneratedObjects() throws SlickException
	{
		for (int i = 0; i < 6; i++)
		{
			generatedObjects.add(createGeneratedObject(-i * (WINDOW_WIDTH - 1)));
		}
	}
	
	@Override
	protected GeneratedObject createGeneratedObject(float x) throws SlickException
	{
		float random = (float)(Math.random() + 0.3);
		
		x = x > -WINDOW_WIDTH ? x - WINDOW_WIDTH : x; 
		
		return new Cloud(x * random);
	}
	
	@Override
	protected void manageGeneratedObjects() throws SlickException
	{
		if (generatedObjects.get(0).getX() >= WINDOW_WIDTH * 3)
		{
			generatedObjects.remove(0);
			generatedObjects.add(createGeneratedObject(generatedObjects.get(generatedObjects.size() - 1).getX() - (WINDOW_WIDTH - 1)));
		}
	}

}
