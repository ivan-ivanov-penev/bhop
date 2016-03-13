package com.bhop.game.bonusbackground;

import java.io.Serializable;

public class BonusBackgroundLock implements Serializable
{

	private static final long serialVersionUID = 4L;
	
	private int totalCarrots;
	
	private int numberOfUnlockedSpecialBackgrounds;

	int getTotalCarrots()
	{
		return totalCarrots;
	}

	int getNumberOfSpecialBackgroundsUnlock()
	{
		return numberOfUnlockedSpecialBackgrounds;
	}

	void incrementTotalCarrots()
	{
		totalCarrots += 1;
	}

	void unlockedNewSpecialBackgrounds()
	{
		totalCarrots = 0;
		
		numberOfUnlockedSpecialBackgrounds += 1;
	}

}
