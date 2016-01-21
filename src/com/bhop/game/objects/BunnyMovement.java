package com.bhop.game.objects;

public class BunnyMovement
{
	float speed;

	float y;

	float gravityForce;

	float jumpHeight;

	private void updateHeightPosition()
	{
		y += gravityForce;
		increaseGravityForce();
	}

	private void increaseGravityForce()
	{
		gravityForce += 1;
	}

	private boolean isOnTopOfAnObject()
	{
		return true;
	}

	public void move(boolean isButtonPressed)
	{
		if (isOnTopOfAnObject())
		{
			resetGravityForce();

			checkInput(isButtonPressed);
		}
		else
		{
			updateHeightPosition();
		}
	}
	
	private void resetGravityForce()
	{
		gravityForce = 8;
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
		decreaseSpeed();
		decreaseNextJumpHeight();
		setAnimationToRuning();
	}
	
	private void jump()
	{
		increaseSpeed();
		increaseNextJumpHeight();
		setJumpAnimation();
	}

	private void decreaseSpeed()
	{
		speed -= 1;
	}
	
	private void decreaseNextJumpHeight()
	{

	}
	
	private void setAnimationToRuning()
	{
	}
	
	private void increaseSpeed()
	{
		speed += 1;
	}
	
	private void increaseNextJumpHeight()
	{
	}
	
	private void setJumpAnimation()
	{
	}
}
