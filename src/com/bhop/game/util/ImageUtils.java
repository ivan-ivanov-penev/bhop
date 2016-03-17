package com.bhop.game.util;

import static com.bhop.game.util.GameUtils.*;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.PixelLocation;

public final class ImageUtils
{
	
	public static final String SPRITE_DIR = "res/sprites/";
	
	private ImageUtils() {}
	
	public static Set<PixelLocation> getPixelsLocations(Image image)
	{
		Set<PixelLocation> locationToPixelPresence = new HashSet<PixelLocation>();
		
		fillSetWithLocations(image, locationToPixelPresence);
		
		return locationToPixelPresence;
	}

	private static void fillSetWithLocations(Image image, Set<PixelLocation> locationToPixelPresence)
	{
		int counter = 3;
		
		byte[] textureData = image.getTexture().getTextureData();

		for (int i = 0; i < image.getHeight() / image.getTextureHeight(); i++)
		{
			for (int j = 0; j < image.getWidth() / image.getTextureWidth(); j++)
			{
				if (textureData[counter] != 0)
				{
					locationToPixelPresence.add(new PixelLocation(j, i));
				}
				
				counter += 4;
			}
		}
	}
	
	public static Image createImage(String imagePath) throws SlickException
	{
		InputStream is = ImageUtils.class.getResourceAsStream("/" + SPRITE_DIR + imagePath);
		
		return new Image(is, imagePath, false);
	}
	
	public static Animation createAnimation(String animationPath) throws SlickException
	{
		return new Animation(createImageArrayFromDirectory(animationPath), FPS);
	}

	public static Image[] createImageArrayFromDirectory(String directoryName) throws SlickException
	{
		File[] allImages = new File(directoryName).listFiles();

		Image[] animation = new Image[allImages.length];

		for (int i = 0; i < allImages.length; i++)
		{
			animation[i] = createImage(directoryName);
		}

		return animation;
	}
	
	public static Image getImageAccordingToTimePeriod(String path) throws SlickException
	{
		return new Image(path + getTimePeriod() + ".png");
	}

}
