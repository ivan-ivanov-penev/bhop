package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.GameUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class BonusBackgroundInfoProvider extends BasicPopup implements Singleton
{
	
	static boolean othersMustWait;
	
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
	public void update(Input input) throws SlickException
	{
		if (justUnlockedBonusBackground)
		{
			frameCounter += 1;
			
			atemtPopup();
		}
		else
		{
			frameCounter = 0;
		}
	}

	private void atemtPopup()
	{
		if (frameCounter < GameUtils.FPS * 5 && !SkinUnlockerInfo.othersMustWait)
		{
			othersMustWait = true;
			
			popup();
		}
		else
		{
			hide();
		}
	}

	protected void hide()
    {
		if (!movesLeft)
		{
			if (x > -image.getWidth())
			{
				x -= 10;
			}
			else
			{
				if (othersMustWait)
				{
					justUnlockedBonusBackground = false;
				}
				
				othersMustWait = false;
			}
		}
		else if (x < WINDOW_WIDTH - image.getWidth())
		{
			x += 3;
		}
		else
		{
			movesLeft = false;
		}
    }

	@Override
	protected void setOthersMustWait()
	{
		othersMustWait = false;
	}

	@Override
	protected String setMessage()
	{
		return "You unlocked new special background!";
	}

}
