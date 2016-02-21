package com.bhop.game.bonuscolor;

import java.io.Serializable;

public class BonusLock implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private boolean bonusUnlocked;
	
	boolean isBonusUnlocked()
	{
		return bonusUnlocked;
	}
	
	void unlockBonus()
	{
		bonusUnlocked = true;
	}

}
