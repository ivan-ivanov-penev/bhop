package com.bhop.game.objects.log;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Log
{

	private final Image image;

	private float x;

	private float y;

	public Log() throws SlickException
	{
		image = new Image("res/obsticales/log.png");
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

}
