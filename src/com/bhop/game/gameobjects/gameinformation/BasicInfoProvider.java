package com.bhop.game.gameobjects.gameinformation;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class BasicInfoProvider extends BasicPopup implements Singleton
{
	
	private BasicInfoProvider() throws SlickException {}

	@Override
    public void update(Input input) throws SlickException
    {
		if (!carrotManager.gameHasBegan())
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
		return "Get the carrot before the time runs out!";
	}

	@Override
	protected void setOthersMustWait() {}

}
