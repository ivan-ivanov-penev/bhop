package com.bhop.game.util;

import java.io.InputStream;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.bhop.game.sound.SoundWatcher;

public final class SoundUtils
{
	
	private static Map<String, Sound> soundsByName;
	
	private SoundUtils() {}
	
	public static Sound createSound(String fileName) throws SlickException
	{
		InputStream is = SoundUtils.class.getResourceAsStream("/res/sound/" + fileName + ".wav");
		
		return new Sound(is, fileName);
	}
	
	public static void playSound(Sound sound)
	{
		if (SoundWatcher.isSoundEnabled() && !sound.playing())
		{
			sound.play();
		}
	}
	
	public static void playSound(String soundName)
	{
		playSound(soundsByName.get(soundName));
	}
	
	// TODO: load sounds
	public static void loadSounds()
	{
//		putSoundInMap("");
	}
	
	// TODO: make this method private
	void putSoundInMap(String soundName)
	{
		try
        {
	        soundsByName.put(soundName, createSound(soundName));
        }
        catch (SlickException e)
        {
	        e.printStackTrace();
        }
	}

}
