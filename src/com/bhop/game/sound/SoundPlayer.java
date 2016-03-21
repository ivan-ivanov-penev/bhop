package com.bhop.game.sound;

import static com.bhop.game.util.SoundUtils.*;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundPlayer
{
	
	private Sound sound;
	
	private boolean soundPlayed;

	public SoundPlayer(String soundName) throws SlickException
	{
		sound = createSound(soundName);
	}
	
	public void playSoundContinuously()
	{
		playSound(sound);
	}
	
	public void playSoundOnce()
	{
		if (!soundPlayed)
		{
			playSound(sound);
			
			soundPlayed = true;
		}
	}
	
	public void alertSoundHasToBePlayed()
	{
		soundPlayed = false;
	}

}
