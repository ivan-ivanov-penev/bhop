package com.bhop.game.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.bunny.CameraMovement;

public abstract class BasicGameObject implements GameObject
{
	
	protected Image image;
	
	protected float x;
	
	protected float y;
	
	protected CameraMovement movement;

	public BasicGameObject(String path) throws SlickException
	{
		movement = CameraMovement.getInstance();
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

}
