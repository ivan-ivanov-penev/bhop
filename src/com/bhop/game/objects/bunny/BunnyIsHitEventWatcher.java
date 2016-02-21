package com.bhop.game.objects.bunny;

import java.util.HashSet;
import java.util.Set;

import com.bhop.game.objects.timecounter.GameEndWatcher;

public abstract class BunnyIsHitEventWatcher extends GameEndWatcher
{
	
	private static final Set<BunnyIsHitEventWatcher> WATCHERS = new HashSet<BunnyIsHitEventWatcher>();
	
	protected boolean bunnyIsHit;
	
	public BunnyIsHitEventWatcher()
    {
		WATCHERS.add(this);
    }
	
	static void alertWatchersBunnyIsHit()
	{
		for (BunnyIsHitEventWatcher watcher : WATCHERS)
		{
			watcher.alertBunnyIsHit();
		}
	}
	
	static void alertWatchersBunnyHasRecovered()
	{
		for (BunnyIsHitEventWatcher watcher : WATCHERS)
		{
			watcher.bunnyHasRecovered();
		}
	}
	
	protected void alertBunnyIsHit()
	{
		bunnyIsHit = true;
	}
	
	protected void bunnyHasRecovered()
	{
		bunnyIsHit = false;
	}
	
	public static void clearWatchers()
	{
		WATCHERS.clear();
	}

}
