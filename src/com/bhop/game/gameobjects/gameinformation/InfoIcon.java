package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class InfoIcon extends BasicGameObject implements Singleton
{
	
	private static boolean playerIsReadingInfo;
	
	private InfoIcon() throws SlickException
	{
		super(SPRITE_DIR + "info_icon/info_icon.png");
		
		x = image.getWidth() * 0.5f;
		y = image.getHeight() * 0.5f;
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		if (mouseIsOverImage(input, this) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			playerIsReadingInfo = !playerIsReadingInfo;
		}
	}
	
	public static boolean isPlayerIsReadingInfo()
	{
		return playerIsReadingInfo;
	}

}
