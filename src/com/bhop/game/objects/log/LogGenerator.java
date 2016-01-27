package com.bhop.game.objects.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.SlickException;

public class LogGenerator
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
		if (random.nextInt(200) == 1)
		{
			logs.add(new Log());
		}
	}

	private void clearLogsOutsideScrren()
	{
		if (logs.get(0).getX() < -100)
		{
			logs.remove(0);
		}
	}

	public void draw()
	{
		for (Log log : logs)
		{
			log.draw();
		}
	}

}
