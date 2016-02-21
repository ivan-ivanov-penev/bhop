package com.bhop.game.objects.timecounter;

import java.util.HashSet;
import java.util.Set;

public abstract class GameEndWatcher
{
	
	protected boolean gameEnded;
	
	private static final Set<GameEndWatcher> WATCHERS = new HashSet<GameEndWatcher>();
	
	protected GameEndWatcher()
	{
		WATCHERS.add(this);
	}
	
	static void alertWatchersGameHasEnded()
	{
		for (GameEndWatcher watcher : WATCHERS)
		{
			watcher.gameEnded = true;
		}
	}
	
	public static void clearWatchers()
	{
		WATCHERS.clear();
	}

}
