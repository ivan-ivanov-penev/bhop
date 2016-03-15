package com.bhop.game.util;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.bhop.game.sound.SoundWatcher;

public final class SoundUtils
{
	
	private SoundUtils() {}
	
	public static Sound createSound(String path) throws SlickException
	{
		return new Sound("res/sound/" + path);
	}
	
	public static void playSound(Sound sound)
	{
		if (SoundWatcher.isSoundEnabled() && !sound.playing())
		{
			sound.play();
		}
	}

}
