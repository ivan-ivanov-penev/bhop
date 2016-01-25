package com.bhop.game.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.GameUtils;

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
