package com.bhop.game.gameobjects.gameinformation;

import org.newdawn.slick.SlickException;

import com.bhop.game.util.GameUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class BonusBackgroundInfoProvider extends BasicPopup implements Singleton
{
	
	private static boolean justUnlockedBonusBackground;
	
	private int frameCounter;
	
	private BonusBackgroundInfoProvider() throws SlickException
	{
		justUnlockedBonusBackground = false;
	}
	
	public static void alertBonusBackroundIsUnlocked()
	{
		justUnlockedBonusBackground = true;
	}

	@Override
	protected void attemptPopup()
	{
		frameCounter += 1;
		
		if (frameCounter < GameUtils.FPS * 5)
		{
			popup();
		}
		else
		{
			hide();
		}
	}

	@Override
	protected String setMessage()
	{
		return "You unlocked new special background!";
	}

	@Override
    protected boolean hasToPopup()
    {
	    return justUnlockedBonusBackground;
    }

}
