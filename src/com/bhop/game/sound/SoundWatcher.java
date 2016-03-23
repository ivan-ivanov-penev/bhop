package com.bhop.game.sound;

import org.newdawn.slick.GameContainer;

public final class SoundWatcher
{
	
	private static final SoundInfoManager SOUND_INFO_MANAGER = new SoundInfoManager();
	
	private static boolean soundEnabled = SOUND_INFO_MANAGER.isSoundEnabled();
	
	private static GameContainer gameContainer;
	
	private SoundWatcher() {}

	public static boolean isSoundEnabled()
	{
		return soundEnabled;
	}

	static void alertPlayerHasClickedIcon()
	{
		soundEnabled = !soundEnabled;
		
		gameContainer.setMusicOn(soundEnabled);
		
		SOUND_INFO_MANAGER.writeSoundInformation(soundEnabled);
	}
	
	public static void setMusicSettings(GameContainer gameContainer)
    {
	    SoundWatcher.gameContainer = gameContainer;
	    
	    gameContainer.setMusicVolume(0.1f);
    }

}
