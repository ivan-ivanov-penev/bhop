package com.bhop.game.gameobjects.bunny.animation;

import static com.bhop.game.util.GameUtils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.PixelLocation;
import com.bhop.game.gameobjects.bunny.BunnyIsHitEventWatcher;
import com.bhop.game.gameobjects.bunny.dummy.DummyBunnyAnimation;
import com.bhop.game.gameobjects.coloroptions.ColorOption.BunnyColor;
import com.bhop.game.util.ImageUtils;

import static com.bhop.game.gameobjects.bunny.CameraMovement.*;

public class BunnyAnimation extends BunnyIsHitEventWatcher
{
	
	public static final int IMAGE_WIDTH = 96;
	
	public static final int IMAGE_HEIGHT = 96;
	
	private Map<Image, RunSpeedBoost> speedBoostsForFrame;

	private Map<Image, Set<PixelLocation>> imagePixelLocations;
	
	private final Image[] hitImages;

	private final Image[] jumpImages;
	
	private final Image[] runImages;
	
	private final FrameCounter frameCounter;
	
	private final SpriteManager spriteManager;
	
	private Image currentFrame;

	public BunnyAnimation(BunnyColor bunnyColor) throws SlickException
	{
		hitImages = createImageArrayFromDirectory(SPRITE_DIR + "bunny/" + bunnyColor.getColorName() + "/hit");
		jumpImages = createImageArrayFromDirectory(SPRITE_DIR + "bunny/" + bunnyColor.getColorName() + "/jump");
		runImages = createImageArrayFromDirectory(SPRITE_DIR + "bunny/" + bunnyColor.getColorName() + "/run");
		frameCounter = new FrameCounter();
		spriteManager = new SpriteManager();
		currentFrame = runImages[DummyBunnyAnimation.getFrameIndex() >= runImages.length ? 0 : DummyBunnyAnimation.getFrameIndex()];

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
		
		putPixelLocationsForImageInMap(hitImages, createImageArrayFromDirectory(SPRITE_DIR + "bunny/collision/hit"));
		putPixelLocationsForImageInMap(jumpImages, createImageArrayFromDirectory(SPRITE_DIR + "bunny/collision/jump"));
		putPixelLocationsForImageInMap(runImages, createImageArrayFromDirectory(SPRITE_DIR + "bunny/collision/run"));
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

	public RunSpeedBoost getSpeedBoost()
	{
		return speedBoostsForFrame.get(currentFrame);
	}
	
	public Set<PixelLocation> getCurrentFramePixelLocations()
	{
		return imagePixelLocations.get(currentFrame);
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
					return runImages[i + 1];
				}
			}
			
			return runImages[0];
		}
		
	}

}
