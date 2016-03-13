package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.bonuscolor.BonusSkinUnlocker;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

@SingletonClass
public class SkinUnlockerInfo extends BasicPopup implements Singleton
{
	
	static boolean othersMustWait;
	
	private boolean playerHasUnlockedBonus;
	
	private boolean hasToPopUp;
	
	private int frameCounter;
	
	private SkinUnlockerInfo() throws SlickException
	{
		playerHasUnlockedBonus = SingletonManager.getSingleton(BonusSkinUnlocker.class).playerHasUnlockedBonus();
	}

	@Override
    public void update(Input input) throws SlickException
    {
		if (!hasToPopUp)
		{
			hasToPopUp = !playerHasUnlockedBonus && carrotManager.playerJustUnlockedBonus();
		}
		
		if (hasToPopUp)
		{
			attemtpPopup();
		}
    }

	private void attemtpPopup()
	{
		if (frameCounter < FPS * 5 && !BonusBackgroundInfoProvider.othersMustWait)
		{
			frameCounter += 1;
			
			othersMustWait = true;
			
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

	@Override
	protected void setOthersMustWait()
	{
		othersMustWait = false;
	}

}
