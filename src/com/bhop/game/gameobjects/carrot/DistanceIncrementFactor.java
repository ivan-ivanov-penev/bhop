package com.bhop.game.gameobjects.carrot;

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
		if (counter == REPETITION && numerator < 8)
		{
			counter = 0;
			
			prevoiusCarrotX *= getNextSpawnDistanceIncrementFactor();
		}
		else
		{
			counter++;
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