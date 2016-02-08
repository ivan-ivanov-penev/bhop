package com.bhop.game.objects.log;

import java.util.Set;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.BasicGameObject;
import com.bhop.game.objects.PixelLocation;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.ImageUtils;

public class Log extends BasicGameObject
{
	
	public static final int IMAGE_WIDTH = 64;
	
	public static final int IMAGE_HEIGHT = 85;
	
	private final Set<PixelLocation> imagePixelLocations;
	
	private boolean isFalling;
	
	private float speed = 0.7f;

	public Log() throws SlickException
	{
		super("res/obsticales/log4.png");
		
		x = GameUtils.WINDOW_WIDTH;
//		y = GameUtils.WINDOW_HEIGHT - 240;
		y = GameUtils.WINDOW_HEIGHT - 40;
		
		imagePixelLocations = ImageUtils.getPixelsLocations(image);
	}

	@Override
	public void render() throws SlickException
	{
		image.draw(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
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

//		if (y > GameUtils.WINDOW_HEIGHT - 240)
//		{
//			isFalling = false;
//		}

		if (y > GameUtils.WINDOW_HEIGHT - 40)
		{
			isFalling = false;
		}
	}
	
	public Set<PixelLocation> getImagePixelLocations()
	{
		return imagePixelLocations;
	}

}
