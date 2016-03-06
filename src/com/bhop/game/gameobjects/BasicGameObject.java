package com.bhop.game.gameobjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.bunny.CameraMovement;
import com.bhop.game.util.singleton.SingletonManager;

public abstract class BasicGameObject implements GameObject
{
	
	protected Image image;
	
	protected float x;
	
	protected float y;
	
	protected final CameraMovement movement;

	public BasicGameObject(String path) throws SlickException
	{
		movement = SingletonManager.getSingleton(CameraMovement.class);
		image = new Image(path);
	}
	
	protected BasicGameObject()
	{
		movement = SingletonManager.getSingleton(CameraMovement.class);
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

	@Override
	public void render() throws SlickException
	{
		image.draw(x, y);
	}

	@Override
	public void update(Input input) throws SlickException
	{
		x -= movement.getMovementSpeed();
	}
	
	public int getImageWidth()
	{
		return image.getWidth();
	}
	
	public int getImageHeight()
	{
		return image.getHeight();
	}

}
