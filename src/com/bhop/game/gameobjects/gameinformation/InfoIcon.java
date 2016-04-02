package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.sound.SoundPlayer;
import com.bhop.game.util.InputUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class InfoIcon extends BasicGameObject implements Singleton
{
	
	private static boolean playerIsReadingInfo;
	
	private final SoundPlayer soundPlayer;
	
	private InfoIcon() throws SlickException
	{
		super("info_icon/info_icon.png");
		
		soundPlayer = new SoundPlayer("res/sound/button.wav");
		
		x = 8;
		y = 7;
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		if (mouseIsOverImage(input, this) && InputUtils.isLeftMouseButtonPressed())
		{
			playerIsReadingInfo = !playerIsReadingInfo;
			
			soundPlayer.playSoundOnce();
		}
		else
		{
			soundPlayer.alertSoundHasToBePlayed();
		}
	}
	
	public static boolean isPlayerIsReadingInfo()
	{
		return playerIsReadingInfo;
	}

}
