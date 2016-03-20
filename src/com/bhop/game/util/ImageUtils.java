package com.bhop.game.util;

import static com.bhop.game.util.GameUtils.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.PixelLocation;

/**
 * 
 * @author Ivan Penev
 *
 */
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
		
		if (is != null)
		{
			return new Image(is, imagePath, false);
		}
		
		return new Image(SPRITE_DIR + imagePath);
	}
	
	public static Animation createAnimation(String animationPath)
	{
		try
		{
			return new Animation(createImageArrayFromDirectorySafely(animationPath), FPS, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
	}

	public static Image[] createImageArrayFromDirectorySafely(String directoryName)
	{
		try
		{
			return createImageArrayFromDirectory(directoryName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
	}

	private static Image[] createImageArrayFromDirectory(String directoryName) throws Exception
	{
		String[] frameNames = getFrameFileNamesFromDirectory(directoryName);
		
		Arrays.sort(frameNames);
		
		Image[] animation = new Image[frameNames.length];

		for (int i = 0; i < frameNames.length; i++)
		{
			animation[i] = createImage(directoryName + frameNames[i]);
		}

		return animation;
	}

	private static String[] getFrameFileNamesFromDirectory(String directoryName) throws Exception
	{
		try
		{
			return getResourceListing(ImageUtils.class, SPRITE_DIR + directoryName);
		}
		catch (Exception e) {}
		
		String[] list = new File(SPRITE_DIR + directoryName).list();
		
		for (int i = 0; i < list.length; i++)
		{
			list[i] = "/" + list[i];
		}
		
		return list;
	}

	private static String[] getResourceListing(Class<?> clazz, String path) throws Exception
	{
		URL dirURL = clazz.getClassLoader().getResource(path);
		if (dirURL != null && dirURL.getProtocol().equals("file"))
		{
			/* A file path: easy enough */
			return new File(dirURL.toURI()).list();
		}

		if (dirURL == null)
		{
			/*
			 * In case of a jar file, we can't actually find a directory. Have
			 * to assume the same jar as clazz.
			 */
			String me = clazz.getName().replace(".", "/") + ".class";
			dirURL = clazz.getClassLoader().getResource(me);
		}

		if (dirURL.getProtocol().equals("jar"))
		{
			/* A JAR path */
			String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); // strip out only the JAR file
			
			JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
			
			Enumeration<JarEntry> entries = jar.entries(); // gives ALL entries in jar
			
			Set<String> result = new HashSet<String>(); // avoid duplicates in case it is a subdirectory
			while (entries.hasMoreElements())
			{
				String name = entries.nextElement().getName();
	          
				if (name.startsWith(path))
				{ // filter according to the path
//					String entry = name.substring(path.length());
//					int checkSubdir = entry.indexOf("/");
//					if (checkSubdir >= 0)
//					{
//						// if it is a subdirectory, we just return the directory
//						// name
//						entry = entry.substring(0, checkSubdir);
//					}
//					result.add(entry);
					if (name.length() > path.length() + 2)
					{
						result.add(name.replace(path, ""));
					}
				}
			}
			
			jar.close();
	        
	        return result.toArray(new String[result.size()]);
	      } 

	      throw new UnsupportedOperationException("Cannot list files for URL "+dirURL);
	  }
	
	public static Image getImageAccordingToTimePeriod(String path) throws SlickException
	{
		InputStream is = ImageUtils.class.getResourceAsStream("/" + SPRITE_DIR + path + getTimePeriod() + ".png");
		
		if (is != null)
		{
			return new Image(is, path + getTimePeriod() + ".png", false);
		}
		
		return new Image(SPRITE_DIR + path + getTimePeriod() + ".png");
	}

}
