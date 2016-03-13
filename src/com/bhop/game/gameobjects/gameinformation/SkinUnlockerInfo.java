package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class SkinUnlockerInfo extends BasicPopup implements Singleton
{
	
	private int frameCounter;
	
	private SkinUnlockerInfo() throws SlickException {}

	@Override
    public void update(Input input) throws SlickException
    {
		if (carrotManager.playerJustUnlockedBonus() || frameCounter > 0)
		{
			attemtpPopup();
		}
    }

	private void attemtpPopup()
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
		return "You unlocked new bonus color for bunny!";
	}

}
