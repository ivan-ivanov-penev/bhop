package com.bhop.game.objects;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.physics.Gravity;

public class Bunny
{
	
	private Image image;
	
	private float x;
	
	private float y;
	
	private final Gravity gravity;
	
	public Bunny() throws SlickException
	{
		image = new Image("res/bunny/1.png");
		x = WINDOW_WIDTH / 6;
		y = WINDOW_HEIGHT / 3;
		gravity = new Gravity();
	}

	public Image getAnimation()
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
	
	public void move(Ground ground, Input input) throws SlickException
	{
		fallDown();

		if (y > WINDOW_HEIGHT - 200)
		{
			if (input.isMousePressed(0) || input.isKeyPressed(Input.KEY_SPACE))
			{
				gravity.resetGravity();

				y += gravity.getForce();

				ground.increaseSpeedFactor();

				gravity.biggerJump();
			}
			else
			{
				ground.decreaseSpeedFactor();

				gravity.smallerJump();
			}
		}
	}

	private void fallDown()
	{
		if (y < WINDOW_HEIGHT - 200)
		{
			gravity.increaseGravity();
			y += gravity.getForce();
		}
	}

}
