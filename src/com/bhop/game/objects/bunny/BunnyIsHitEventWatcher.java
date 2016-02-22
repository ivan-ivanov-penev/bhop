package com.bhop.game.objects.bunny;

import java.util.HashSet;
import java.util.Set;

public abstract class BunnyIsHitEventWatcher
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
