package com.bhop.game.objects.log;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.BasicGameObject;
import com.bhop.game.util.GameUtils;

public class Log extends BasicGameObject
{
	
	private boolean isFalling;
	
	private float speed = 0.5f;

	public Log() throws SlickException
	{
		super("res/obsticales/log.png");
		
		x = GameUtils.WINDOW_WIDTH;
		y = GameUtils.WINDOW_HEIGHT - 240;
	}

	@Override
	public void render() throws SlickException
	{
		image.draw(x, y, 64, 96);
	}

	@Override
	public void update(Input input) throws SlickException
	{
		super.update(null);

		y += isFalling ? speed : -speed;

		if (y < GameUtils.WINDOW_HEIGHT - 360)
		{
			isFalling = true;
		}

		if (y > GameUtils.WINDOW_HEIGHT - 240)
		{
			isFalling = false;
		}
	}

}
