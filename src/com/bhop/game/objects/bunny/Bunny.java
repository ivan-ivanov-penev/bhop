package com.bhop.game.objects.bunny;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.Ground;

public class Bunny
{
	
	private Image image;
	
	private BunnyMovement movement;
	
	public Bunny(Ground ground) throws SlickException
	{
		movement = new BunnyMovement(ground);
		image = new Image("res/bunny/1.png");
	}

	public Image getAnimation()
	{
		return image;
	}

	public float getX()
	{
		return movement.getX();
	}

	public float getY()
	{
		return movement.getY();
	}
	
	public void draw()
	{
		image.draw(movement.getX(), movement.getY());
	}

	public void move(Input input) throws SlickException
	{
		movement.move(input);
	}

}
