package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.GameUtils;

public class BunnyAnimation
{
	private float fps;

	private final Image[] jumpImages;
	
	private final Image[] runImages;
	
	private Image currentFrame;
	
	private float frameCounter;
	
	BunnyAnimation() throws SlickException
	{
		jumpImages = GameUtils.createImageArrayFromDirectory("res/bunny/jump");
		runImages = GameUtils.createImageArrayFromDirectory("res/bunny/run");
		fps = 1.5f;
	}

	void draw(float x, float y, float height, float width)
	{
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
			frameCounter += 0.5;
		}
	}

	void update(float gravityForce, float y, float speedFactor) throws SlickException
	{
		setSpeed(speedFactor);

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
		else if (y >= WINDOW_HEIGHT - 220)
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
	
	private void setSpeed(float speedFactor)
	{
		if (speedFactor < 1.8)
		{
			fps = 1.5f;
		}
		else if (speedFactor < 2.7)
		{
			fps = 1.0f;
		}
		else
		{
			fps = 0.5f;
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
		if (currentFrame.equals(runImages[1]))
		{
			return RunSpeedBoost.MAX;
		}
		else if (currentFrame.equals(runImages[0]))
		{
			return RunSpeedBoost.AVERAGE;
		}
		else if (currentFrame.equals(runImages[5]))
		{
			return RunSpeedBoost.MIN;
		}
		else
		{
			return null;
		}
	}

	public static enum RunSpeedBoost
	{

		MIN,
		AVERAGE,
		MAX

	}

}
