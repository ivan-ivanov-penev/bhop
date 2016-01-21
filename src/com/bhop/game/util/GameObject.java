package com.bhop.game.util;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class GameObject
{
	
	protected Image image;
	
	protected float x;
	
	protected float y;

	public GameObject(String path) throws SlickException
	{
		image = new Image(path);
	}

	public Image getImage()
	{
		return image;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}
	
	public void draw()
	{
		image.draw(x, y);
	}
	
	public void move()
	{
		x += GameUtils.CAMERA_SPEED;
	}

}
