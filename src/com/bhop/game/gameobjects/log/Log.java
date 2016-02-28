package com.bhop.game.gameobjects.log;

import java.util.Set;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.PixelLocation;
import com.bhop.game.gameobjects.bunny.Collidable;
import com.bhop.game.gameobjects.carrot.Carrot;
import com.bhop.game.util.ImageUtils;

import static com.bhop.game.util.GameUtils.*;

public class Log extends BasicGameObject implements Collidable
{
	
	public static final int IMAGE_WIDTH = 64;
	
	public static final int IMAGE_HEIGHT = 85;
	
	private final Set<PixelLocation> imagePixelLocations;
	
	private boolean isFalling;
	
	private float verticalSpeed = 0.0f;
	
	private float horizontalSpeed = 0.0f;

	public Log(Carrot carrot) throws SlickException
	{
		super(SPRITE_DIR + "obsticales/rolling_log.png");
		
		setHorizontalSpeed(getRandomElement(LogType.values()));
		setXWithoutCarrotCollision(carrot);
		
		y = WINDOW_HEIGHT - 200;
		
		imagePixelLocations = ImageUtils.getPixelsLocations(new Image(image.getResourceReference().replace(".png", "_collision.png")));
	}
	
	private void setHorizontalSpeed(LogType logType) throws SlickException
	{
		switch (logType)
        {
			case LEVITATING:
				
				verticalSpeed = 1.0f;
				horizontalSpeed = 0.0f;
				
				break;
				
			case ROLLING:
				
				verticalSpeed = 0.0f;
				horizontalSpeed = 1.0f;
				
				break;
				
			case STATIC:
				
				verticalSpeed = 0.0f;
				horizontalSpeed = 0.0f;
				image = new Image(SPRITE_DIR + "obsticales/log.png");
				
				break;
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
	}
	
	public Set<PixelLocation> getImagePixelLocations()
	{
		return imagePixelLocations;
	}
	
	static enum LogType
	{
		STATIC,
		ROLLING,
		LEVITATING;
	}

}
