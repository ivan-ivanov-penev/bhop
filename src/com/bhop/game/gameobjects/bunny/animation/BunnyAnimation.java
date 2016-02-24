package com.bhop.game.gameobjects.bunny.animation;

import static com.bhop.game.util.GameUtils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.PixelLocation;
import com.bhop.game.gameobjects.bunny.BunnyIsHitEventWatcher;
import com.bhop.game.states.Menu;
import com.bhop.game.util.ImageUtils;

import static com.bhop.game.gameobjects.bunny.CameraMovement.*;

public class BunnyAnimation extends BunnyIsHitEventWatcher
{
	
	public static final int IMAGE_WIDTH = 96;
	
	public static final int IMAGE_HEIGHT = 96;
	
	private static int runAnimationIndex;

	private Map<Image, RunSpeedBoost> speedBoostsForFrame;

	private Map<Image, Set<PixelLocation>> imagePixelLocations;
	
	private final Image[] hitImages;

	private final Image[] jumpImages;
	
	private final Image[] runImages;
	
	private final FrameCounter frameCounter;
	
	private final SpriteManager spriteManager;
	
	private Image currentFrame;

	public BunnyAnimation() throws SlickException
	{
		hitImages = createImageArrayFromDirectory("res/bunny/" + Menu.getSelectedBunnyColor().getColorName() + "/hit");
		jumpImages = createImageArrayFromDirectory("res/bunny/" + Menu.getSelectedBunnyColor().getColorName() + "/jump");
		runImages = createImageArrayFromDirectory("res/bunny/" + Menu.getSelectedBunnyColor().getColorName() + "/run");
		frameCounter = new FrameCounter();
		spriteManager = new SpriteManager();
		currentFrame = runImages[runAnimationIndex];

		initializeSpeedBoostsForFrame();
		initializeImagePixelLocations();
	}

	private void initializeSpeedBoostsForFrame()
	{
		speedBoostsForFrame = new HashMap<Image, RunSpeedBoost>();
		speedBoostsForFrame.put(runImages[1], RunSpeedBoost.MAX);
	}

	private void initializeImagePixelLocations() throws SlickException
	{
		imagePixelLocations = new HashMap<Image, Set<PixelLocation>>();
		putPixelLocationsForImageInMap(hitImages, createImageArrayFromDirectory("res/bunny/collision/hit"));
		putPixelLocationsForImageInMap(jumpImages, createImageArrayFromDirectory("res/bunny/collision/jump"));
		putPixelLocationsForImageInMap(runImages, createImageArrayFromDirectory("res/bunny/collision/run"));
	}
	
	private void putPixelLocationsForImageInMap(Image[] realImageArray, Image[] collisionImageArray)
	{ 
		for (int i = 0; i < collisionImageArray.length; i++)
		{
			imagePixelLocations.put(realImageArray[i], ImageUtils.getPixelsLocations(collisionImageArray[i]));
		}
	}

	public void draw(float x, float y)
	{
		currentFrame.draw(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	public void update(float gravityForce, float y, float speedFactor, boolean isOnTopOfAnObject) throws SlickException
	{
		frameCounter.adjustSpeed(speedFactor);
		
		if (bunnyIsHit)
		{
			spriteManager.preciseHitFrame(gravityForce, y);
		}
		else
		{
			spriteManager.preciseJumpFrame(gravityForce, y, isOnTopOfAnObject);
		}
	}
	
	public boolean isNotInTheAir()
	{
//		return (currentFrame.equals(runImages[1]) || currentFrame.equals(runImages[0])) && frameCounter == fps;
		return true;
	}

	public RunSpeedBoost getSpeedBoost()
	{
		return speedBoostsForFrame.get(currentFrame);
	}
	
	public Set<PixelLocation> getCurrentFramePixelLocations()
	{
		return imagePixelLocations.get(currentFrame);
	}
	
	public static void resetRunAnimationIndex()
	{
		runAnimationIndex = 0;
	}
	
	private class SpriteManager
	{
		
		private void preciseHitFrame(float gravityForce, float y)
		{
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
			else if (y > WINDOW_HEIGHT - 230)
			{
				currentFrame = hitImages[7];
			}
			else if (y > WINDOW_HEIGHT - 250)
			{
				currentFrame = hitImages[6];
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
			else if (gravityForce < 0)
			{
				currentFrame = jumpImages[2];
			}
			else if (gravityForce < 3)
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
			else if (frameCounter.animationHasEnded())
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
					runAnimationIndex = i + 1;
					
					return runImages[i + 1];
				}
			}
			
			runAnimationIndex = 0;
			
			return runImages[0];
		}
		
	}

}
