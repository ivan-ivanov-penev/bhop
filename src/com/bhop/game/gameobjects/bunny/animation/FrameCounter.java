package com.bhop.game.gameobjects.bunny.animation;

import static com.bhop.game.gameobjects.bunny.CameraMovement.MAX_SPEED_FACTOR;
import static com.bhop.game.gameobjects.bunny.CameraMovement.MIN_SPEED_FACTOR;

import com.bhop.game.gameobjects.bunny.dummy.DummyBunnyAnimation;

public class FrameCounter
{
	
	private int frameCounter;
	
	private int fps;
	
	FrameCounter()
	{
		frameCounter = DummyBunnyAnimation.getFrameCounter();
	}
	
	void adjustSpeed(float speedFactor)
	{
		incrementFrameCounter();
		
		if (speedFactor < ((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) / 3 * 1) + MIN_SPEED_FACTOR)
		{
			fps = 6;
		}
		else if (speedFactor < ((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) / 3 * 2) + MIN_SPEED_FACTOR)
		{
			fps = 5;
		}
		else
		{
			fps = 4;
		}
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
	
	boolean animationHasEnded()
	{
		return frameCounter == fps;
	}

}
