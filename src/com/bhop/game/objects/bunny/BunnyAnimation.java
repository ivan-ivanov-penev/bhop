package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.ground.GroundPhysics.RunSpeedBoost;
import com.bhop.game.util.GameUtils;

import static com.bhop.game.objects.ground.GroundPhysics.*;

class BunnyAnimation
{

	private Map<Image, RunSpeedBoost> speedBoostsForFrame;

	private final Image[] jumpImages;
	
	private final Image[] runImages;
	
	private Image currentFrame;
	
	private Animation a;

	private boolean isRunning;

	private int frameCounter;
	
	private int fps;

	BunnyAnimation() throws SlickException
	{
		jumpImages = GameUtils.createImageArrayFromDirectory("res/bunny/jump");
		runImages = GameUtils.createImageArrayFromDirectory("res/bunny/run");
		a = new Animation(runImages, 60);

		initializeSpeedBoostsForFrame();
	}

	private void initializeSpeedBoostsForFrame()
	{
		speedBoostsForFrame = new HashMap<Image, RunSpeedBoost>();
		speedBoostsForFrame.put(runImages[1], RunSpeedBoost.MAX);
		speedBoostsForFrame.put(runImages[0], RunSpeedBoost.AVERAGE);
		speedBoostsForFrame.put(runImages[5], RunSpeedBoost.MIN);
	}

	void draw(float x, float y, float height, float width)
	{
		if (isRunning)
		{
			a.draw(x, y, width, height);
			return;
		}

		incrementFrameCounter();

		currentFrame.draw(x, y, width, height);
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

	void update(float gravityForce, float y, float speedFactor, boolean isOnTopOfAnObject) throws SlickException
	{
		adjustSpeed(speedFactor);

		if (gravityForce < -6)
		{
			isRunning = false;
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
		else if (y > WINDOW_HEIGHT - 235)
		{
			currentFrame = jumpImages[5];
		}
		else
		{
			currentFrame = jumpImages[4];
		}
	}
	
	private void adjustSpeed(float speedFactor)
	{
		if (speedFactor < ((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) / 3) + MIN_SPEED_FACTOR)
		{
			a.setSpeed(0.8f);
			fps = 3;
		}
		else if (speedFactor < ((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) / 3 * 2) + MIN_SPEED_FACTOR)
		{
			a.setSpeed(1.0f);
			fps = 2;
		}
		else
		{
			a.setSpeed(1.2f);
			fps = 1;
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
			// currentFrame = getNextRunFrame();
			isRunning = true;
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
//		return currentFrame.equals(runImages[1]) && frameCounter == fps;
		return a.getCurrentFrame().equals(runImages[1]);
	}

	RunSpeedBoost getSpeedBoost()
	{
		return speedBoostsForFrame.get(currentFrame);
	}

}
