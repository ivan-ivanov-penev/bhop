package com.bhop.game.sound;

public final class SoundWatcher
{
	
	private static final SoundInfoManager SOUND_INFO_MANAGER = new SoundInfoManager();
	
	private static boolean soundEnabled = SOUND_INFO_MANAGER.isSoundEnabled();
	
	private SoundWatcher() {}

	public static boolean isSoundEnabled()
	{
		return soundEnabled;
	}

	static void alertPlayerHasClickedIcon()
	{
		soundEnabled = !soundEnabled;
		
		SOUND_INFO_MANAGER.writeSoundInformation(soundEnabled);
	}

}
