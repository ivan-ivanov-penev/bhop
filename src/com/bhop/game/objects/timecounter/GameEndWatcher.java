package com.bhop.game.objects.timecounter;

public final class GameEndWatcher
{
	
	private static boolean gameEnd = false;
	
	private GameEndWatcher() {}
	
	static void alertGameHasEnded()
	{
		gameEnd = true;
	}
	
	public static boolean isGameEnd()
	{
		return gameEnd;
	}
	
	public static void restartGame()
	{
		gameEnd = false;
	}

}
