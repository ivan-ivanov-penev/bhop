package com.bhop.game.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.bhop.game.sound.SoundWatcher;

/**
 * 
 * @author Ivan Penev
 *
 */
public final class SoundUtils
{
	
	private static volatile Map<String, Sound> soundsByName;
	
	private SoundUtils() {}
	
	public static Sound createSound(String fileName) throws SlickException
	{
		InputStream is = SoundUtils.class.getResourceAsStream("/res/sound/" + fileName + ".wav");
		
		if (is != null)
		{
			return new Sound(is, fileName);
			
		}
		
		return new Sound(fileName);
	}
	
	public static void playSound(Sound sound)
	{
		if (SoundWatcher.isSoundEnabled() /*&& !sound.playing()*/)
		{
			sound.play();
		}
	}
	
	public static void playSound(String soundName)
	{
		playSound(soundsByName.get(soundName));
	}
	
	public static void loadSounds()
	{
		soundsByName = new HashMap<String, Sound>();
		
		putSoundInMap("res/sound/booster_collect.wav");
		putSoundInMap("res/sound/carrot_collect.wav");
//		putSoundInMap("res/sound/click.wav");
		putSoundInMap("res/sound/hitting_sound.wav");
		putSoundInMap("res/sound/jump.wav");
		putSoundInMap("res/sound/padane.wav");
		putSoundInMap("res/sound/pop_up.wav");
		putSoundInMap("res/sound/skachane_revert_padane.wav");
		putSoundInMap("res/sound/pop_up_hide.wav");
//		putSoundInMap("");
	}
	
	private static void putSoundInMap(String soundName)
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
	
	public static Music createMusic(String fileName) throws SlickException
	{
		return new Music(fileName);
	}
	
	public static void playMusic(Music music)
	{
		if (SoundWatcher.isSoundEnabled() && !music.playing())
		{
			music.play();
		}
	}

}
