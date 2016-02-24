package com.bhop.game.gameobjects.bunny;

class BunnyPhysics
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
		gravityForce = -(BASE_FORCE * jumpHeight);
	}

	void resetGravityFallingBaseForce()
	{
		gravityForce = BASE_FORCE;
	}
	
	float getGravityForce()
    {
	    return gravityForce;
    }

	static class BunnyJump extends BunnyIsHitEventWatcher
	{
		
		static final float MAX_JUMP_COEFFICIENT = 1.0f;

		static final float MIN_JUMP_COEFFICIENT = 1.0f;

		private static final float JUMP_COEFFICIENT_INCREMENT = 0.1f;

		private static final float JUMP_COEFFICIENT_DECREMENT = 0.004f;

		private float jumpHeight;

		public BunnyJump()
		{
			jumpHeight = MIN_JUMP_COEFFICIENT;
		}

		void increaseNextJumpHeight()
		{
			jumpHeight += JUMP_COEFFICIENT_INCREMENT;
			
			if (jumpHeight > MAX_JUMP_COEFFICIENT)
			{
				jumpHeight = MAX_JUMP_COEFFICIENT;
			}
		}

		void decreaseNextJumpHeight()
		{
			jumpHeight -= JUMP_COEFFICIENT_DECREMENT;
			
			if (jumpHeight < MIN_JUMP_COEFFICIENT)
			{
				jumpHeight = MIN_JUMP_COEFFICIENT;
			}
		}

		float getJumpHeight()
		{
			return jumpHeight;
		}
		
		@Override
		protected void alertBunnyIsHit()
		{
			jumpHeight = MIN_JUMP_COEFFICIENT;
		}

	}

}
