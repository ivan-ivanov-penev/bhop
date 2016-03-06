package com.bhop.game.gameobjects.bunny.dummy;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

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
	
//	private final Animation run;
	
	private DummyBunny() throws SlickException
	{
//	    run = new Animation(createImageArrayFromDirectory(SPRITE_DIR + "bunny/bonus/run"), FPS, true);
//		run.setLooping(true);
//		run.setSpeed(1.75f);
		
		dummyBunnyAnimation = new DummyBunnyAnimation();
		
		x = WINDOW_WIDTH / 6;
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
