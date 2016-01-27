package com.bhop.game.objects.ground;

public class GroundPhysics
{

	public static final float MAX_SPEED_FACTOR = 3.5f;

	public static final float MIN_SPEED_FACTOR = 1;

	private static final float SPEED_FACTOR_DECREMENT = 0.025f;

	private float speedFactor;

	public GroundPhysics()
	{
		speedFactor = MIN_SPEED_FACTOR;
	}

	void increaseSpeedFactor(RunSpeedBoost runSpeedBoost)
	{
		speedFactor += runSpeedBoost.speedFactor;

		if (speedFactor > MAX_SPEED_FACTOR)
		{
			speedFactor = MAX_SPEED_FACTOR;
		}
	}

	void decreaseSpeedFactor()
	{
		speedFactor -= SPEED_FACTOR_DECREMENT;

		if (speedFactor < MIN_SPEED_FACTOR)
		{
			speedFactor = MIN_SPEED_FACTOR;
		}
	}

	float getSpeedFactor()
	{
		return speedFactor;
	}

	public static enum RunSpeedBoost
	{

		MAX(1f),
		AVERAGE(0.75f),
		MIN(0.5f);

		private final float speedFactor;

		private RunSpeedBoost(float speedFactor)
		{
			this.speedFactor = speedFactor;
		}

	}

}
