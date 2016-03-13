package com.bhop.game.gameobjects.bunny.dummy;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class DummyBunny implements GameObject, Singleton
{
	
	private float x;

	private float y;
	
	private final DummyBunnyAnimation dummyBunnyAnimation;
	
	private DummyBunny() throws SlickException
	{
		dummyBunnyAnimation = new DummyBunnyAnimation();
		
		x = BUNNY_STARTING_X;
		y = WINDOW_HEIGHT - 215;
	}

	@Override
	public void render()
	{
		dummyBunnyAnimation.render(x, y);
	}

	@Override
	public void update(Input input) throws SlickException
	{
		dummyBunnyAnimation.update(input);
	}

}
