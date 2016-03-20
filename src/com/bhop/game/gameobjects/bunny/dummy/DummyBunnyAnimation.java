package com.bhop.game.gameobjects.bunny.dummy;

import static com.bhop.game.util.ImageUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class DummyBunnyAnimation
{
	
	private final Image[] runImages;
	
	private Image currentFrame;
	
	private static int frameCounter;
	
	private static int frameIndex;
	
	DummyBunnyAnimation() throws SlickException
	{
		runImages = createImageArrayFromDirectorySafely("bunny/bonus/run");
		
		currentFrame = runImages[0];
	}

	public void update(Input input) throws SlickException
	{
		frameCounter += 1;
		
		if (frameCounter  > 7)
		{
			currentFrame = getNextRunFrame();
			
			frameCounter = 0;
		}
	}

	private Image getNextRunFrame()
	{
		for (int i = 0; i < runImages.length - 1; i++)
		{
			if (currentFrame.equals(runImages[i]))
			{
				frameIndex += 1;
				
				return runImages[i + 1];
			}
		}
		
		frameIndex = 0;
		
		return runImages[0];
	}

	public void render(float x, float y)
	{
		currentFrame.draw(x, y);
	}
	
	public static int getFrameCounter()
	{
		return frameCounter;
	}
	
	public static int getFrameIndex()
	{
		return frameIndex;
	}

}
