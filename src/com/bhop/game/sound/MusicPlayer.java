package com.bhop.game.sound;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.SoundUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.singleton.Singleton;

/**
 * 
 * @author Ivan Penev
 *
 */
public class MusicPlayer implements Singleton, GameObject
{
	
	private static Music musicToPlay;
	
	private final Music[] musicForTimePeriod;
	
	private MusicPlayer() throws SlickException
    {
		musicForTimePeriod = new Music[3];
		
		fillMusicForTimePeriod();
		
		if (musicToPlay == null)
		{
			musicToPlay = getRandomElement(musicForTimePeriod);
		}
    }

	private void fillMusicForTimePeriod() throws SlickException
    {
		String timePeriod = getTimePeriod();
		
		switch (timePeriod)
        {
			case "dawn":
				musicForTimePeriod[0] = createMusic("res/sound/music/dawn/Adrianna_Krikl_-_02_-_Glitch.wav");
				musicForTimePeriod[1] = createMusic("res/sound/music/dawn/Ketsa_-_02_-_Forgetfulness_world_mix.wav");
				musicForTimePeriod[2] = createMusic("res/sound/music/dawn/Ketsa_-_12_-_Long_Walk.wav");
				break;
				
			case "day":
				musicForTimePeriod[0] = createMusic("res/sound/music/day/Ketsa_-_01_-_Inexpress.wav");
				musicForTimePeriod[1] = createMusic("res/sound/music/day/Ketsa_-_05_-_Conscience_remastered.wav");
				musicForTimePeriod[2] = createMusic("res/sound/music/day/Ketsa_-_06_-_Sapphire_Sky.wav");
				break;

			default:
				musicForTimePeriod[0] = createMusic("res/sound/music/night/Ketsa_-_04_-_Wounds.wav");
				musicForTimePeriod[1] = createMusic("res/sound/music/night/Ketsa_-_08_-_Changing_Seasons.wav");
				musicForTimePeriod[2] = createMusic("res/sound/music/night/Ketsa_-_14_-_Far_From_Home.wav");
				break;
		}
    }

	@Override
    public void update(Input input) throws SlickException
    {
		if (SoundWatcher.isSoundEnabled() && !musicToPlay.playing())
		{
			setUnrepeateableMusicToPlay();

			musicToPlay.play();
		}
    }
	
	private void setUnrepeateableMusicToPlay()
	{
		Music musicToPlay = getRandomElement(musicForTimePeriod);
		
		while (MusicPlayer.musicToPlay == musicToPlay)
		{
			musicToPlay = getRandomElement(musicForTimePeriod);
		}
		
		MusicPlayer.musicToPlay = musicToPlay;
	}

	@Override
    public void render() throws SlickException {}

}
