package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.Ground;
import com.bhop.game.objects.bunny.BunnyPhysics.BunnyJump;

class BunnyMovement
{

	private float x;

	private float y;

	private final Ground ground;

	private final BunnyPhysics physics;

	private final BunnyJump jump;

	public BunnyMovement(Ground ground)
	{
		this.ground = ground;
		physics = new BunnyPhysics();
		jump = new BunnyJump();
		x = WINDOW_WIDTH / 6;
		y = WINDOW_HEIGHT / 3;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	private boolean isOnTopOfAnObject()
	{
		normalizeHeight();

		return y == WINDOW_HEIGHT - 200;
	}

	private void normalizeHeight()
	{
		if (y > WINDOW_HEIGHT - 200)
		{
			y = WINDOW_HEIGHT - 200;
		}
	}

	private void updateHeightPosition()
	{
		y += physics.getGravityForce();
	}

	public void move(Input input) throws SlickException
	{
		if (isOnTopOfAnObject())
		{
			physics.setGravityToFalling();

			checkInput(input.isMousePressed(0) || input.isKeyPressed(Input.KEY_SPACE));
		}
		else
		{
			physics.increaseGravityPullingForce();

			updateHeightPosition();
		}
	}

	private void checkInput(boolean isButtonPressed)
	{
		if (isButtonPressed)
		{
			jump();
		}
		else
		{
			run();
		}
	}

	private void run()
	{
		jump.decreaseNextJumpHeight();

		decreaseSpeed();
		setAnimationToRuning();
	}

	private void jump()
	{
		jump.increaseNextJumpHeight();

		physics.setGravityToJumping(jump.getJumpHeight());

		updateHeightPosition();
		increaseSpeed();
		setJumpAnimation();
	}

	private void decreaseSpeed()
	{
		ground.decreaseSpeedFactor();
	}

	private void setAnimationToRuning()
	{
	}

	private void increaseSpeed()
	{
		ground.increaseSpeedFactor();
	}

	private void setJumpAnimation()
	{
	}

}
