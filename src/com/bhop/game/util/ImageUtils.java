package com.bhop.game.util;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Image;

import com.bhop.game.gameobjects.PixelLocation;

public final class ImageUtils
{
	
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

}
