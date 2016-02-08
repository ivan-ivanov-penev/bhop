package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.PixelLocation;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.ImageUtils;

import static com.bhop.game.objects.bunny.CameraMovement.*;

class BunnyAnimation
{
	
	public static final int IMAGE_WIDTH = 96;
	
	public static final int IMAGE_HEIGHT = 96;

	private Map<Image, RunSpeedBoost> speedBoostsForFrame;

	private Map<Image, Set<PixelLocation>> imagePixelLocations;
	
	private final Image[] hitImages;

	private final Image[] jumpImages;
	
	private final Image[] runImages;
	
	private Image currentFrame;
	
	private boolean bunnyIsHit;
	
	private int frameCounter;
	
	private int fps;

	BunnyAnimation() throws SlickException
	{
		hitImages = GameUtils.createImageArrayFromDirectory("res/bunny/hit_new");
		jumpImages = GameUtils.createImageArrayFromDirectory("res/bunny/jump");
		runImages = GameUtils.createImageArrayFromDirectory("res/bunny/run");
		currentFrame = runImages[0];
		fps = 3;

		initializeSpeedBoostsForFrame();
		initializeImagePixelLocations();
	}

	private void initializeSpeedBoostsForFrame()
	{
		speedBoostsForFrame = new HashMap<Image, RunSpeedBoost>();
		speedBoostsForFrame.put(runImages[1], RunSpeedBoost.MAX);
		speedBoostsForFrame.put(runImages[0], RunSpeedBoost.AVERAGE);
		speedBoostsForFrame.put(runImages[5], RunSpeedBoost.MIN);
	}

	private void initializeImagePixelLocations()
	{
		imagePixelLocations = new HashMap<Image, Set<PixelLocation>>();
		putPixelLocationsForImageInMap(hitImages);
		putPixelLocationsForImageInMap(jumpImages);
		putPixelLocationsForImageInMap(runImages);
	}
	
	private void putPixelLocationsForImageInMap(Image[] imageArray)
	{
		for (Image image : imageArray)
		{
			imagePixelLocations.put(image, ImageUtils.getPixelsLocations(image));
		}
	}

	void draw(float x, float y)
	{
		incrementFrameCounter();

		currentFrame.draw(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	private void incrementFrameCounter()
	{
		if (frameCounter > fps)
		{
			frameCounter = 0;
		}
		else
		{
			frameCounter += 1;
		}
	}

	// XXX Consider dividing this class in three subclasses - jump, run, hit
	void update(float gravityForce, float y, float speedFactor, boolean isOnTopOfAnObject) throws SlickException
	{
		adjustSpeed(speedFactor);
		
		if (bunnyIsHit)
		{
			preciseHitFrame(gravityForce, y);
		}
		else
		{
			preciseJumpFrame(gravityForce, y, isOnTopOfAnObject);
		}
	}
	
	private void adjustSpeed(float speedFactor)
	{
		if (speedFactor < ((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) / 3 * 1) + MIN_SPEED_FACTOR)
		{
			fps = 3;
		}
		else if (speedFactor < ((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) / 3 * 2) + MIN_SPEED_FACTOR)
		{
			fps = 2;
		}
		else
		{
			fps = 1;
		}
	}
	
	private void preciseHitFrame(float gravityForce, float y)
	{
//		try
//        {
//	        Thread.sleep(200);
//        }
//        catch (InterruptedException e)
//        {
//	        e.printStackTrace();
//        }
		
	    if (gravityForce < -6)
		{
			currentFrame = hitImages[0];
		}
		else if (gravityForce < -3)
		{
			currentFrame = hitImages[1];
		}
		else if (gravityForce < -1)
		{
			currentFrame = hitImages[2];
		}
		else if (gravityForce < 2)
		{
			currentFrame = hitImages[3];
		}
		else if (gravityForce < 5)
		{
			currentFrame = hitImages[4];
		}
		else if (gravityForce < 7)
		{
			currentFrame = hitImages[5];
		}
		else if (y > WINDOW_HEIGHT - 250)
		{
			currentFrame = hitImages[9];
		}
	}

	private void preciseJumpFrame(float gravityForce, float y, boolean isOnTopOfAnObject)
    {
	    if (gravityForce < -6)
		{
			currentFrame = jumpImages[0];
		}
		else if (gravityForce < -3)
		{
			currentFrame = jumpImages[1];
		}
		else if (gravityForce < 2)
		{
			currentFrame = jumpImages[2];
		}
		else if (gravityForce < 6)
		{
			currentFrame = jumpImages[3];
		}
		else if (isOnTopOfAnObject)
		{
			preciseLandSprite();
		}
		else if (y > WINDOW_HEIGHT - 230)
		{
			currentFrame = jumpImages[5];
		}
		else
		{
			currentFrame = jumpImages[4];
		}
    }

	private void preciseLandSprite()
	{
		if (currentFrame.equals(jumpImages[5]))
		{
			currentFrame = jumpImages[6];
		}
		else if (frameCounter == fps)
		{
			currentFrame = getNextRunFrame();
		}
	}

	private Image getNextRunFrame()
	{
		for (int i = 0; i < runImages.length - 1; i++)
		{
			if (currentFrame.equals(runImages[i]))
			{
				return runImages[i + 1];
			}
		}
		
		return runImages[0];
	}
	
	boolean isNotInTheAir()
	{
		return currentFrame.equals(runImages[1]) && frameCounter == fps;
	}

	RunSpeedBoost getSpeedBoost()
	{
		return speedBoostsForFrame.get(currentFrame);
	}

	float getImageWidth()
	{
		return currentFrame.getWidth();
	}
	
	Set<PixelLocation> getCurrentFramePixelLocations()
	{
		return imagePixelLocations.get(currentFrame);
	}
	
	void alertBunnyIsHit()
	{
		bunnyIsHit = true;
	}
	
	void bunnyHasRecovered()
	{
		bunnyIsHit = false;
	}

}
