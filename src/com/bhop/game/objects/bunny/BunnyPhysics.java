package com.bhop.game.objects.bunny;

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
		
		static final float MAX_JUMP_COEFFICIENT = 1.8f;

		static final float MIN_JUMP_COEFFICIENT = 1.0f;

		private static final float JUMP_COEFFICIENT_INCREMENT = 0.15f;

		private static final float JUMP_COEFFICIENT_DECREMENT = 0.025f;

		private float jumpHeight;

		public BunnyJump()
		{
			jumpHeight = MIN_JUMP_COEFFICIENT;
		}

		void increaseNextJumpHeight()
		{
			if (jumpHeight < MAX_JUMP_COEFFICIENT)
			{
				jumpHeight += JUMP_COEFFICIENT_INCREMENT;
			}
		}

		void decreaseNextJumpHeight()
		{
			if (jumpHeight > MIN_JUMP_COEFFICIENT)
			{
				jumpHeight -= JUMP_COEFFICIENT_DECREMENT;
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
