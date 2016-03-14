package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.SlickException;

import com.bhop.game.bonuscolor.BonusSkinUnlocker;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

@SingletonClass
public class SkinUnlockerInfo extends BasicPopup implements Singleton
{
	
	private boolean playerHasUnlockedBonus;
	
	private boolean hasToPopUp;
	
	private int frameCounter;
	
	private SkinUnlockerInfo() throws SlickException
	{
		playerHasUnlockedBonus = SingletonManager.getSingleton(BonusSkinUnlocker.class).playerHasUnlockedBonus();
	}

	@Override
    protected boolean hasToPopup()
    {
		if (!hasToPopUp)
		{
			hasToPopUp = !playerHasUnlockedBonus && carrotManager.playerJustUnlockedBonus();
		}
		
	    return hasToPopUp;
    }

	@Override
	protected void attemptPopup()
	{
		if (frameCounter < FPS * 5)
		{
			frameCounter += 1;
			
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
		return "You unlocked new bonus skin for bunny!";
	}

}
