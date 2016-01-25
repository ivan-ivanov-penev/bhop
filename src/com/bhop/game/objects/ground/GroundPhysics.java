package com.bhop.game.objects.ground;

import com.bhop.game.objects.bunny.BunnyAnimation.RunSpeedBoost;

class GroundPhysics
{

	private static final float MAX_SPEED_FACTOR = 3.5f;

	private static final float MIN_SPEED_FACTOR = 1;

//	private static final float SPEED_FACTOR_INCREMENT = 0.5f;

	private static final float SPEED_FACTOR_DECREMENT = 0.025f;

//	private long start;

	private float speedFactor;

	public GroundPhysics()
	{
		speedFactor = MIN_SPEED_FACTOR;
	}

	void increaseSpeedFactor(RunSpeedBoost runSpeedBoost)
	{
		System.out.println(convertBoostToSpeedFactor(runSpeedBoost));
		speedFactor += convertBoostToSpeedFactor(runSpeedBoost);

		if (speedFactor > MAX_SPEED_FACTOR)
		{
			speedFactor = MAX_SPEED_FACTOR;
//			start = System.currentTimeMillis();
		}
	}
	
	private float convertBoostToSpeedFactor(RunSpeedBoost runSpeedBoost)
	{
		switch (runSpeedBoost)
        {
			case MAX:
				return 1f;
				
			case AVERAGE:
				return 0.5f;

			default:
				return 0.1f;
		}
	}

	void decreaseSpeedFactor()
	{
		speedFactor -= SPEED_FACTOR_DECREMENT;

		if (speedFactor < MIN_SPEED_FACTOR)
		{
			speedFactor = MIN_SPEED_FACTOR;

//			if (start != 0)
//			{
//				System.out.println((System.currentTimeMillis() - start) + " milisec");
//				System.exit(0);
//			}
		}
	}

	float getSpeedFactor()
	{
		return speedFactor;
	}

}
