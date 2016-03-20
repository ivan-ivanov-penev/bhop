package com.bhop.game.gameobjects.log;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.ImageUtils.*;

import java.util.Set;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.PixelLocation;
import com.bhop.game.gameobjects.bunny.Collidable;
import com.bhop.game.gameobjects.carrot.Carrot;
import com.bhop.game.util.ImageUtils;

public class Log extends BasicGameObject implements Collidable
{
	
	public static final int IMAGE_WIDTH = 64;
	
	public static final int IMAGE_HEIGHT = 85;
	
	private final Set<PixelLocation> imagePixelLocations;
	
	private static int logKind;
	
	private boolean isFalling;
	
	private float verticalSpeed = 0.0f;
	
	private float horizontalSpeed = 0.0f;
	
	public Log(Carrot carrot) throws SlickException
	{
		y = WINDOW_HEIGHT - 190;
		
		String logType = getLogType(getRandomElement(LogType.values()));
		setXWithoutCarrotCollision(carrot);
		
		image.setCenterOfRotation(29.5f, 43.5f);

		imagePixelLocations = ImageUtils.getPixelsLocations(createImage(logType));
	}
	
	private String getLogType(LogType logType) throws SlickException
	{
		switch (logType)
        {
			case LEVITATING:
				verticalSpeed = 1.0f;
				horizontalSpeed = 0.0f;
				image = createImage("obsticales/levitating_log_kind" + logKind + ".png");
				
				return "obsticales/levitating_log_collision.png";
				
			case ROLLING:
				verticalSpeed = 0.0f;
				horizontalSpeed = 1.0f;
				image = createImage("obsticales/rolling_log_kind" + logKind + ".png");
				
				return "obsticales/rolling_log_collision.png";
				
			default:
				verticalSpeed = 0.0f;
				horizontalSpeed = 0.0f;
				image = createImage("obsticales/static_log_kind" + logKind + ".png");
				y = WINDOW_HEIGHT - 200;
				
				return "obsticales/static_log_collision.png";
		}
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
		x -= movement.getMovementSpeed() + horizontalSpeed;

		y += isFalling ? verticalSpeed : -verticalSpeed;

		if (y < WINDOW_HEIGHT - 360)
		{
			isFalling = true;
		}

		if (y > WINDOW_HEIGHT - 200)
		{
			isFalling = false;
		}
		
		if (horizontalSpeed != 0)
		{
			image.rotate(-2.5f);
		}
	}
	
	public Set<PixelLocation> getImagePixelLocations()
	{
		return imagePixelLocations;
	}
	
	static void setLogKind()
	{
		Log.logKind = RANDOM.nextInt(3);
	}
	
	static enum LogType
	{
		STATIC,
		ROLLING,
		LEVITATING;
	}

}
