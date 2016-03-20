package com.bhop.game.util.generator;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.ImageUtils;

/**
 * 
 * @author Ivan Penev
 *
 */
public abstract class GeneratedObject
{
	
	protected final Image image;
	
	protected float x;
	
	protected GeneratedObject(float x, String path) throws SlickException
	{
		image = ImageUtils.getImageAccordingToTimePeriod(path);
		
		this.x = x;
	}
	
	public float getX()
	{
		return x;
	}

	protected void render() throws SlickException
	{
		image.draw(x, 0);
	}
	
	protected void update(float cameraSpeed)
	{
		x -= cameraSpeed / 25;
	}

}
