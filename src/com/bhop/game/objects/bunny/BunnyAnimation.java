package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

class BunnyAnimation
{
	private static final float FPS = 2;
	
	private final Image[] jumpImages;
	
	private final Image[] runImages;
	
	private Image currentFrame;
	
	private float frameCounter;
	
	public BunnyAnimation() throws SlickException
	{
		jumpImages = new Image[] {
				new Image("res/bunny/jump/sBunnyBlue_Jump_0.png"),
				new Image("res/bunny/jump/sBunnyBlue_Jump_1.png"),
				new Image("res/bunny/jump/sBunnyBlue_Jump_2.png"),
				new Image("res/bunny/jump/sBunnyBlue_Jump_3.png"),
				new Image("res/bunny/jump/sBunnyBlue_Jump_4.png"),
				new Image("res/bunny/jump/sBunnyBlue_Jump_5.png"),
				new Image("res/bunny/jump/sBunnyBlue_Jump_7.png")
		};
		
		runImages = new Image[] {
				new Image("res/bunny/run/sBunnyBlue_Run_0.png"),
				new Image("res/bunny/run/sBunnyBlue_Run_1.png"),
				new Image("res/bunny/run/sBunnyBlue_Run_2.png"),
				new Image("res/bunny/run/sBunnyBlue_Run_3.png"),
				new Image("res/bunny/run/sBunnyBlue_Run_4.png"),
				new Image("res/bunny/run/sBunnyBlue_Run_5.png")
		};
	}

	void draw(float x, float y, float height, float width)
	{
		incrementFrameCounter();
		
		currentFrame.draw(x, y, width, height);
	}

	private void incrementFrameCounter()
	{
		if (frameCounter > FPS)
		{
			frameCounter = 0;
		}
		else
		{
			frameCounter += 1;
		}
	}

	void update(float gravityForce, float y) throws SlickException
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
	
	private void preciseLandSprite()
	{
		if (currentFrame.equals(jumpImages[5]))
		{
			currentFrame = jumpImages[6];
		}
		else if (frameCounter == FPS)
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
		return currentFrame.equals(runImages[1]) && frameCounter == FPS;
	}

}
