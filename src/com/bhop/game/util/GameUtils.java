package com.bhop.game.util;

import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public final class GameUtils
{

	private GameUtils() {}

	public static final int WINDOW_WIDTH = 640;
	
	public static final int WINDOW_HEIGHT = 480;

	public static final String GAME_NAME = "BHop";
	
	public static final int FPS = 60;

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

}
