package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.GameUtils;

import static com.bhop.game.objects.bunny.CameraMovement.*;

class BunnyAnimation
{

	private Map<Image, RunSpeedBoost> speedBoostsForFrame;

	private final Image[] jumpImages;
	
	private final Image[] runImages;
	
	private Image currentFrame;
	
	private int frameCounter;
	
	private int fps;

	BunnyAnimation() throws SlickException
	{
		jumpImages = GameUtils.createImageArrayFromDirectory("res/bunny/jump");
		runImages = GameUtils.createImageArrayFromDirectory("res/bunny/run");
		currentFrame = runImages[0];
		fps = 3;

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
		main();

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

	// XXX Consider dividing this class in three subclasses - jump, run, hit
	void update(float gravityForce, float y, float speedFactor, boolean isOnTopOfAnObject) throws SlickException
	{
		adjustSpeed(speedFactor);

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

	private void main()
	{
		try
		{
			main2();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.exit(0);
	}

	private void main2() throws Exception
	{
		Image image = new Image("res/from_drago/tst.png");
		System.out.println(image.getTexture().getTextureData().length);
		
		File file = new File("res/log.txt");

		if (!file.exists())
		{
			file.createNewFile();
		}

		PrintWriter out = new PrintWriter(file);

//		Image image = new Image("res/from_drago/cross.png");

		byte[] array = image.getTexture().getTextureData();

		byte[][] b = new byte[32][8];

		int c = 3;

		int ones = 0;

		for (int i = 0; i < b.length; i++)
		{
			for (int j = 0; j < b[i].length; j++)
			{
				b[i][j] = array[c];

				if (array[c] == 0)
				{
					out.print(" " + array[c]);
				}
				else
				{
					out.print(" " + -array[c]);
				}

				if (array[c] == -1)
				{
					ones++;
				}

				c += 4;
			}
			out.println();
		}

		out.println();
		out.println("number of ones: " + ones);
		out.println(c + " : " + array.length);
		out.println();
		out.println(image.getHeight());
		out.println(image.getTexture().getHeight());
		out.println(image.getTextureHeight());
		out.println(image.getTexture().getImageHeight());
		out.println("----------------------------------------------------");

		Image image2 = new Image("res/from_drago/dots.png");

		out.println();
		out.println(image2.getHeight());
		out.println(image2.getTexture().getHeight());
		out.println(image2.getTextureHeight());
		out.println(image2.getTexture().getImageHeight());
		out.println(image2.getTexture());
		out.println(image2.getTexture().getTextureData().length);
		out.println("----------------------------------------------------");
		out.println();
		out.println(currentFrame.getWidth());
		out.println(currentFrame.getHeight());
		out.println(currentFrame.getTexture().getHeight());
		out.println(currentFrame.getTexture().getWidth());
		out.println(currentFrame.getTextureHeight());
		out.println(currentFrame.getTexture().getImageHeight());
		out.println(currentFrame.getTexture().getTextureData().length);
		out.println("----------------------------------------------------");
		out.close();
//		System.out.println();
//		for (int i = 0; i < array.length; i++)
//		{
//			System.out.println(array[i]);
//		}
//
//		System.out.println(array.length);

	}

}
