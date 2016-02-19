package com.bhop.game.objects.log;

import java.util.Set;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.BasicGameObject;
import com.bhop.game.objects.PixelLocation;
import com.bhop.game.objects.bunny.Collidable;
import com.bhop.game.objects.carrot.Carrot;
import com.bhop.game.util.ImageUtils;

import static com.bhop.game.util.GameUtils.*;

public class Log extends BasicGameObject implements Collidable
{
	
	public static final int IMAGE_WIDTH = 64;
	
	public static final int IMAGE_HEIGHT = 85;
	
	private final Set<PixelLocation> imagePixelLocations;
	
	private boolean isFalling;
	
	private float speed = 0.0f;

	public Log(Carrot carrot) throws SlickException
	{
		super("res/obsticales/log9.png");
		
		setXWithoutCarrotCollision(carrot);
		
		y = WINDOW_HEIGHT - 200;
//		y = GameUtils.WINDOW_HEIGHT - 40;
		
		imagePixelLocations = ImageUtils.getPixelsLocations(new Image("res/obsticales/log4collision.png"));
	}

	private void setXWithoutCarrotCollision(Carrot carrot)
    {
	    if (WINDOW_WIDTH + image.getWidth() > carrot.getX() && WINDOW_WIDTH < carrot.getX() + carrot.getImageWidth())
		{
			x = carrot.getX() + carrot.getImageWidth();
		}
		else
		{
			x = WINDOW_WIDTH;
		}
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

		if (y < WINDOW_HEIGHT - 360)
		{
			isFalling = true;
		}

//		if (y > GameUtils.WINDOW_HEIGHT - 200)
//		{
//			isFalling = false;
//		}

		if (y > WINDOW_HEIGHT - 40)
		{
			isFalling = false;
		}
	}
	
	public Set<PixelLocation> getImagePixelLocations()
	{
		return imagePixelLocations;
	}

}
