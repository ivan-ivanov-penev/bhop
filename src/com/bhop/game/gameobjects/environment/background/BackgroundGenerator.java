package com.bhop.game.gameobjects.environment.background;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.SlickException;

import com.bhop.game.util.generator.GeneratedObject;
import com.bhop.game.util.generator.Generator;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class BackgroundGenerator extends Generator implements Singleton
{
	
	private BackgroundGenerator() throws SlickException{}

	protected GeneratedObject createGeneratedObject(float x) throws SlickException
	{
		return new Background(x);
	}
	
	@Override
	protected void fillGeneratedObjects() throws SlickException
	{
		for (int i = 0; i < 6; i++)
		{
			if (i == 1)
			{
				generatedObjects.add(new SpecialBackground(i * (WINDOW_WIDTH - 1)));
				continue;
			}
			generatedObjects.add(createGeneratedObject(i * (WINDOW_WIDTH - 1)));
		}
	}
	
	@Override
	protected void manageGeneratedObjects() throws SlickException
	{
		attemtToRemoveLast();
		
		super.manageGeneratedObjects();
	}
	
	private void attemtToRemoveLast() throws SlickException
	{
		if (generatedObjects.get(0).getX() >= 0)
		{
			generatedObjects.remove(generatedObjects.size() - 1);
			generatedObjects.add(0, new Background(-WINDOW_WIDTH));
		}
	}

}
