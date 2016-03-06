package com.bhop.game.gameobjects.bunny;

import static com.bhop.game.gameobjects.bunny.CameraMovement.*;

public class BunnyPhysics
{

	private float gravityForce;

	private static final float BASE_FORCE = 8;

	private static final float FORCE_INCREMENTATION = 0.325f;
	
	void increaseGravityPullingForce()
	{
		gravityForce += FORCE_INCREMENTATION;
	}

	void setGravityToJumping(float jumpHeight)
	{
		gravityForce = -((BASE_FORCE) * jumpHeight);
	}

	void resetGravityFallingBaseForce()
	{
		gravityForce = BASE_FORCE;
	}
	
	float getGravityForce()
    {
	    return gravityForce;
    }

	static class BunnyJump
	{
		
		private float jumpHeight;

		public BunnyJump()
		{
			jumpHeight = 1.0f;
		}

		float getJumpHeightAccordingToSpeed(float speedFactor)
		{
			if (speedFactor < MIN_SPEED_FACTOR + 0.5f && speedFactor > 0)
			{
				return jumpHeight * 1.1f;
			}
			
			return jumpHeight;
		}

	}

}
