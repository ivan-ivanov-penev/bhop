package com.bhop.game.objects.bunny;

public class CopyOfBunnyMovement
{
	float speed;

	float y;

	private float gravityForce;

	private float jumpHeight;

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
			resetFallingGravityForce();

			checkInput(isButtonPressed);
		}
		else
		{
			updateHeightPosition();
		}
	}
	
	private void resetFallingGravityForce()
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
		resetJumpingGravityForce();
		increaseSpeed();
		increaseNextJumpHeight();
		setJumpAnimation();
	}

	private void resetJumpingGravityForce()
	{
		gravityForce = -(8 * jumpHeight);
	}

	private void decreaseSpeed()
	{
		speed -= 1;
	}
	
	private void decreaseNextJumpHeight()
	{
		if (jumpHeight > 1)
		{
			jumpHeight -= 0.6;
		}
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
		if (jumpHeight < 2)
		{
			jumpHeight += 0.2;
		}
	}
	
	private void setJumpAnimation()
	{
	}
}
