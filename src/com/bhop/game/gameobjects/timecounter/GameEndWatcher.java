package com.bhop.game.gameobjects.timecounter;

import org.newdawn.slick.SlickException;

import com.bhop.game.sound.SoundPlayer;

public final class GameEndWatcher
{
	
	private static boolean gameEnd = false;
	
	private static SoundPlayer soundPlayer = createSoundPlayer();

	private static SoundPlayer createSoundPlayer()
	{
		try
		{
			return new SoundPlayer("res/sound/game_over2.wav");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
			
			return null;
		}
	}
	
	private GameEndWatcher() {}
	
	static void alertGameHasEnded()
	{
		gameEnd = true;
		
		soundPlayer.playSoundOnce();
	}
	
	public static boolean isGameEnd()
	{
		return gameEnd;
	}
	
	public static void restartGame()
	{
		gameEnd = false;
		
		soundPlayer.alertSoundHasToBePlayed();
	}

}
