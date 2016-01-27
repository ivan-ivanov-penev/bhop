package com.bhop.game.objects.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.util.GameUtils;

public class LogGenerator implements GameObject
{

	private final List<Log> logs;

	private final Random random;

	public LogGenerator()
	{
		random = new Random();
		logs = new ArrayList<Log>();
	}

	public void manage() throws SlickException
	{
		attemptToCreateLog();
		clearLogsOutsideScrren();
	}

	private void attemptToCreateLog() throws SlickException
	{
		if (random.nextInt(20) == 1)
		{
			if (logs.isEmpty())
			{
				logs.add(new Log());
			}
			else if (logs.get(logs.size() - 1).getX() < GameUtils.WINDOW_WIDTH - 100)
			{
				logs.add(new Log());
			}
		}
	}

	private void clearLogsOutsideScrren()
	{
		if (!logs.isEmpty() && logs.get(0).getX() < -100)
		{
			logs.remove(0);
		}
	}

	@Override
	public void render() throws SlickException
	{
		for (Log log : logs)
		{
			log.render();
		}
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		manage();
		
		for (Log log : logs)
		{
			log.update(null);
		}
	}

}
