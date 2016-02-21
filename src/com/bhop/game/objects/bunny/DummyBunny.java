package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.bunny.animation.BunnyAnimation;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonManager;

public class DummyBunny implements GameObject, Singleton
{
	
	private float x;

	private float y;

	private final CameraMovement movement;

	private final BunnyAnimation animation;

	private DummyBunny() throws SlickException
	{
		movement = SingletonManager.getSingleton(CameraMovement.class);
		animation = new BunnyAnimation();
		x = WINDOW_WIDTH / 6;
		y = WINDOW_HEIGHT - 215;
	}

	@Override
	public void render()
	{
		animation.draw(x, y);
	}

	@Override
	public void update(Input input) throws SlickException
	{
		animation.update(8, y, movement.getSpeedFactor(), true);
	}

}
