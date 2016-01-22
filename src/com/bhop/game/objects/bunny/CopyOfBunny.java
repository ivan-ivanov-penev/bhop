package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.Ground;
import com.bhop.game.physics.Gravity;

public class CopyOfBunny
{
	
	private Image image;
	
	private float x;
	
	private float y;
	
	private final Gravity gravity;
	
	public CopyOfBunny() throws SlickException
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
		if (isInTheAir())
		{
			fallDown();
		}
		else
		{
			checkInput(ground, input);
		}
	}

	private void checkInput(Ground ground, Input input)
    {
	    if (input.isMousePressed(0) || input.isKeyPressed(Input.KEY_SPACE))
	    {
	    	gravity.resetGravity();

			fallDown();

	    	ground.increaseSpeedFactor();

	    	gravity.biggerJump();
	    }
	    else
	    {
	    	ground.decreaseSpeedFactor();

	    	gravity.smallerJump();
	    }
    }

	private void fallDown()
	{
		gravity.increaseGravity();
		y += gravity.getForce();
	}

	private boolean isInTheAir()
	{
		return y < WINDOW_HEIGHT - 200;
	}

}
