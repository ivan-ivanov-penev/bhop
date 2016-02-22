package com.bhop.game.objects.bunny;

import static com.bhop.game.objects.timecounter.GameEndWatcher.isGameEnd;

import com.bhop.game.util.singleton.Singleton;


public class CameraMovement extends BunnyIsHitEventWatcher implements Singleton
{
	
	public static final float CAMERA_SPEED = 2.0f;

	public static final float MAX_SPEED_FACTOR = 3.0f;

	public static final float MIN_SPEED_FACTOR = 1;

	private static final float SPEED_FACTOR_DECREMENT = 0.01f;
	
	private float speedFactor;

	private CameraMovement()
	{
		speedFactor = MIN_SPEED_FACTOR;
	}

	void increaseSpeedFactor(RunSpeedBoost runSpeedBoost)
	{
		speedFactor += runSpeedBoost.getSpeedFactor();

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

	@Override
	protected void bunnyHasRecovered()
	{
		if (bunnyIsHit)
		{
			speedFactor = MIN_SPEED_FACTOR;
		}
		
		bunnyIsHit = false;
	}

	float getSpeedFactor()
	{
		return speedFactor;
	}

	public float getMovementSpeed()
	{
		float speed = CAMERA_SPEED * speedFactor;
		float reverseSpeed = speedFactor < MIN_SPEED_FACTOR * 1.5 ? -1.5f : -1f;
		
		return isGameEnd() ? 0 : bunnyIsHit ? speed * reverseSpeed: speed;
	}
	
	public float getCameraSpeed()
	{
		return isGameEnd() ? 0 : bunnyIsHit ? -CAMERA_SPEED : CAMERA_SPEED;
	}

	public static enum RunSpeedBoost
	{

		MAX(1.25f),
		MIN(0.4f);

		private final float speedFactor;

		private RunSpeedBoost(float speedFactor)
		{
			this.speedFactor = speedFactor;
		}
		
		private float getSpeedFactor()
        {
	        return speedFactor;
        }

	}

}
