package com.bhop.game.util;

import java.awt.Font;
import java.io.File;
import java.util.Calendar;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public final class GameUtils
{

	private GameUtils() {}
	
	public static final TrueTypeFont FONT_TYPE = new TrueTypeFont(new Font("Verdana", Font.ITALIC, 30), true);;

	public static final int WINDOW_WIDTH = 640;
	
	public static final int WINDOW_HEIGHT = 480;

	public static final String GAME_NAME = "BHop";
	
	public static final int FPS = 120;

	public static Image[] createImageArrayFromDirectory(String directoryName) throws SlickException
	{
		File[] allImages = new File(directoryName).listFiles();

		Image[] animation = new Image[allImages.length];

		for (int i = 0; i < allImages.length; i++)
		{
			animation[i] = new Image(allImages[i].getAbsolutePath());
		}

		return animation;
	}
	
	public static Image getImageAccordingToTimePeriod(String path) throws SlickException
	{
		int hoursOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		
		if (hoursOfDay > 5 && hoursOfDay < 12)
		{
			return new Image(path + "dawn.png");
		}
		
		if (hoursOfDay < 20)
		{
			return new Image(path + "day.png");
		}
		
		return new Image(path + "night.png");
	}

}
