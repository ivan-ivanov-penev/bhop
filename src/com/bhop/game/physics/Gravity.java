package com.bhop.game.physics;

public class Gravity
{
	
	private float force;
	
	private float jumpScale;
	
	public Gravity()
	{
		force = -6;
	}

	public float getForce()
	{
		return force;
	}
	
	public void increaseGravity()
	{
		force += 0.4;
	}
	
	public void resetGravity()
	{
		force = -7 + jumpScale;
	}
	
	public void biggerJump()
	{
		if (jumpScale > -2.4)
		{
			jumpScale -= 0.3;
		}
	}
	
	public void smallerJump()
	{
		if (jumpScale < 0)
		{
			jumpScale += 0.04;
		}
		else
		{
			jumpScale = 0;
		}
	}

}
