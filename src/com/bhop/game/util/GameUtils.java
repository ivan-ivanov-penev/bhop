package com.bhop.game.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.GameObject;

/**
 * 
 * @author Ivan Penev
 *
 */
public final class GameUtils
{

	private GameUtils() {}
	
	public static final Random RANDOM = new Random();
	
	public static final int WINDOW_WIDTH = 640;
	
	public static final int WINDOW_HEIGHT = 480;
	
	public static final int BUNNY_STARTING_X = WINDOW_WIDTH / 8;
	
	public static final String GAME_NAME = "BHop";
	
	public static final int FPS = 120;
	
	private static String timePeriod = calculateTimePeriodAccordingToTimeOfDay();
	
	public static String getTimePeriod()
	{
		return timePeriod;
	}
	
	public static void reloadTimePeriod()
	{
		timePeriod = calculateTimePeriodAccordingToTimeOfDay();
	}
	
	private static String calculateTimePeriodAccordingToTimeOfDay()
	{
		int hoursOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		
		if (hoursOfDay > 5 && hoursOfDay < 12)
		{
			return "dawn";
		}
		
		if (hoursOfDay < 20)
		{
			return "day";
		}
		
		return "night";
	}
	
	public static boolean mouseIsOverImage(Input input, Image image, float imageX, float imageY)
	{
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		boolean horizontalCheck = mouseX > imageX && mouseX < imageX + image.getWidth();
		boolean verticalCheck = mouseY > imageY && mouseY < imageY + image.getHeight();
		
		return horizontalCheck && verticalCheck;
	}
	
	public static boolean mouseIsOverImage(Input input, BasicGameObject gameObject)
	{
		return mouseIsOverImage(input, gameObject.getImage(), gameObject.getX(), gameObject.getY());
	}
	
	public static void updateGameObjects(Collection<GameObject> gameObjects, Input input) throws SlickException
	{
		for (GameObject gameObject : gameObjects)
		{
			gameObject.update(input);
		}
	}
	
	public static void renderGameObjects(Collection<GameObject> gameObjects) throws SlickException
	{
		for (GameObject gameObject : gameObjects)
		{
			gameObject.render();
		}
	}
	
	public static <T>T getRandomElement(T[] elements)
	{
		return elements[RANDOM.nextInt(elements.length)];
	}
	 
	// TODO: delete this method
	public static void sleep(long sleepTime)
	{
		try
		{
			Thread.sleep(sleepTime);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void sleep()
	{
		sleep(100);
	}

}
