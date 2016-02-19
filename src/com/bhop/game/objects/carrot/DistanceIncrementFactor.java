package com.bhop.game.objects.carrot;

public class DistanceIncrementFactor
{
	
	private static final int REPETITION = 3;
	
	private int counter;
	
	private float prevoiusCarrotX;
	
	private float numerator;

	private float denominator;

	DistanceIncrementFactor(float startingCarrotX)
	{
		numerator = 1f;
		denominator = 0f;
		prevoiusCarrotX = startingCarrotX;
	}
	
	float getNextCarrotSpawnDistance()
	{
		counter++;
		
		if (counter == REPETITION)
		{
			counter = 0;
			
			prevoiusCarrotX *= getNextSpawnDistanceIncrementFactor();
		}
		
		return prevoiusCarrotX;
	}

	private float getNextSpawnDistanceIncrementFactor()
    {
		numerator += 1;
		denominator += 1;
		
        return numerator / denominator;
    }
	
}